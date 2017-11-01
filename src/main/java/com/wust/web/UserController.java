package com.wust.web;

import com.wust.dao.UserAccountMapper;
import com.wust.entity.UserAccount;
import com.wust.utils.MessageVo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: F7687967
 * Date: 2017/10/16
 * Time: 上午 08:10
 * Description: UserAccount相关API
 */

@RestController
@RequestMapping(value = "/useraccount")
public class UserController {


    private Logger logger = Logger.getLogger(UserController.class.getName());

    @Resource
    private UserAccountMapper userAccountMapper;

    @ApiOperation(value = "申请帐号", notes = "创建用户帐号")
    @ApiImplicitParam(name = "UserAccount", value = "用户帐号", required = true, dataType = "UserAccount")
    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public MessageVo postUserAccount(@RequestBody UserAccount userAccount) {

        MessageVo mesVo = new MessageVo();

        /**
         * @Description: 注册手机号应该唯一, 注册前先判断手机号是否可用
         */
        int telNum = userAccountMapper.getUserAccountsNumByTel(userAccount.getTel());

        if (telNum >= 1) {
            mesVo.setCode("0");
            mesVo.setInfo("手机号已被注册,添加用户帐号失败!");
        } else {
            boolean postState = userAccountMapper.putUserAccount(userAccount);

            if (postState) {
                mesVo.setCode("1");
                String currtel = userAccount.getTel();
                UserAccount currUserAccount = userAccountMapper.getUserAccountByTel(currtel);
                mesVo.setData(currUserAccount);
                mesVo.setInfo("添加用户帐号成功!");
            } else {
                mesVo.setCode("0");
                mesVo.setInfo("添加用户帐号失败!");
            }
        }
        return mesVo;
    }

    @ApiOperation(value = "所有用户帐号", notes = "显示所有用户帐号")
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public MessageVo getAllUserAccount() {

        MessageVo mesVo = new MessageVo();

        List<UserAccount> userAccountList = userAccountMapper.getAllUserAccounts();

        mesVo.setCode("1");
        mesVo.setData(userAccountList);
        mesVo.setInfo("获取所有用户帐号成功!");

        return mesVo;
    }

    @ApiOperation(value = "帐号数目", notes = "获取所有用户帐号总量")
    @RequestMapping(value = "/num", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public MessageVo getUserAccountNum() {

        MessageVo mesVo = new MessageVo();

        int userAccountsNum = userAccountMapper.getUserAccountsNum();

        mesVo.setCode("1");
        mesVo.setData("用户帐号总量为:" + userAccountsNum);
        mesVo.setInfo("获取用户帐号总量成功!");

        return mesVo;
    }

    @ApiOperation(value = "指定用户帐号", notes = "显示指定用户帐号")
    @ApiImplicitParam(name = "UserAccount", value = "用户帐号", required = true, dataType = "UserAccount")
    @RequestMapping(value = "/{tel}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public MessageVo getUserAccount(@PathVariable("tel") String tel) {

        MessageVo mesVo = new MessageVo();

        UserAccount currUserAccount = userAccountMapper.getUserAccountByTel(tel);

        mesVo.setCode("1");
        mesVo.setData(currUserAccount);
        mesVo.setInfo("获取指定用户帐号成功!");

        return mesVo;
    }

    @ApiOperation(value = "更新指定用户帐号信息", notes = "根据指定Tel来指定更新对象，并根据传过来的userAccount信息来更新用户帐号信息")
    @ApiImplicitParam(name = "userAccount", value = "用户帐号实体", required = true, dataType = "UserAccount")
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public MessageVo putUserAccount(@RequestBody UserAccount userAccount) {

        MessageVo mesVo = new MessageVo();
        userAccountMapper.updateUserAccountByTel(userAccount);
        UserAccount currUserAccount = userAccountMapper.getUserAccountByTel(userAccount.getTel());

        mesVo.setCode("1");
        mesVo.setData(currUserAccount);
        mesVo.setInfo("修改指定用户帐号信息成功");
        return mesVo;
    }

    @ApiOperation(value = "更新指定用户手机号", notes = "根据指定Tel来指定更新对象的手机号")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "oldTel", value = "用户帐号更换前手机号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "newTel", value = "用户帐号更换后手机号", required = true, dataType = "String")
    })
    @RequestMapping(value = "/{oldTel}", method = RequestMethod.PUT)
    public MessageVo putUserAccountTel(@PathVariable("oldTel") String oldTel, @RequestBody UserAccount userAccount) {
        //@PathVariable是获取url上数据的
        MessageVo mesVo = new MessageVo();
        /**
         * @Description: 注册手机号应该唯一, 注册前先判断手机号是否可用
         */
        String newTel = userAccount.getTel();
        int telNum = userAccountMapper.getUserAccountsNumByTel(newTel);

        if (telNum >= 1) {
            mesVo.setCode("0");
            mesVo.setInfo("手机号已被注册,添加用户帐号失败!");
        } else {
            System.out.println(oldTel);
            userAccountMapper.updateUserAccountTelByTel(newTel, oldTel);

            UserAccount currUserAccount = userAccountMapper.getUserAccountByTel(newTel);

            mesVo.setCode("1");
            mesVo.setData(currUserAccount);
            mesVo.setInfo("修改指定用户帐号信息成功");
        }
        return mesVo;
    }

    @ApiOperation(value = "删除指定用户", notes = "根据Tel来删除指定对象")
    @ApiImplicitParam(name = "tel", value = "用户帐号手机号", required = true, dataType = "String")
    @RequestMapping(value = "/{tel}", method = RequestMethod.DELETE)
    public MessageVo deleteUserAccount(@PathVariable("tel") String tel) {
        MessageVo mesVo = new MessageVo();
        boolean postState = userAccountMapper.deleteUserAccountByTel(tel);

        if (postState) {
            mesVo.setCode("1");
            mesVo.setInfo("删除指定用户帐号成功!");
        } else {
            mesVo.setCode("0");
            mesVo.setInfo("删除指定用户帐号失败!");
        }
        return mesVo;
    }

}
