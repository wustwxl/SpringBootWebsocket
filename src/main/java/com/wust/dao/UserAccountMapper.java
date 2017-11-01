package com.wust.dao;

import com.wust.entity.UserAccount;
import org.apache.ibatis.annotations.*;


import java.util.List;


@Mapper
public interface UserAccountMapper {

    /**
     * 新增一个用户
     */
    @Insert("INSERT INTO user_account(tel, pass, nickname) VALUES(#{tel}, #{pass}, #{nickname})")
    boolean putUserAccount(UserAccount userAccount);

    /**
     * 根据手机号删除一个用户
     */
    @Delete("DELETE FROM user_account WHERE tel =#{tel}")
    boolean deleteUserAccountByTel(@Param("tel") String tel);

    /**
     * 获取用户总量
     */
    @Select("SELECT count(1) FROM user_account")
    Integer getUserAccountsNum();

    /**
     * 根据手机号获取指定用户总量
     */
    @Select("SELECT count(1) FROM user_account WHERE tel =#{tel}")
    Integer getUserAccountsNumByTel(@Param("tel") String tel);

    /**
     * 根据手机号获取一个用户
     */
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "tel", column = "tel"),
            @Result(property = "nickname", column = "nickname"),
            @Result(property = "device", column = "device"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "delflag", column = "delflag")
    })
    @Select("SELECT id, tel, nickname, device, create_time, delflag FROM user_account WHERE tel =#{tel}")
    UserAccount getUserAccountByTel(@Param("tel") String tel);

    /**
     * 根据手机号更新一个用户的信息
     */
    //@Update("<script>UPDATE user_account SET <if test=\"pass !=null \"> pass=#{pass},</if> <if test=\"nickname !=null \"> nickname=#{nickname},</if> tel=#{tel} where tel=#{tel}</script>")
    @Update("UPDATE user_account SET pass=#{pass}, nickname=#{nickname}, device=#{device} where tel=#{tel}")
    void updateUserAccountByTel(UserAccount user);

    /**
     * 更换手机号
     * 当只传一个参数到sql语句时，可以直接写参数名，当传多个参数时，应当这样写(多个参数已#{0}开始)：
     */
    @Update("UPDATE user_account SET tel=#{newTel} where tel=#{oldTel}")
    void updateUserAccountTelByTel(@Param("newTel") String newTel,@Param("oldTel") String oldTel);

    /**
     * 获取所有用户的帐号信息
     */
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "tel", column = "tel"),
            @Result(property = "nickname", column = "nickname"),
            @Result(property = "device", column = "device"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "delflag", column = "delflag")
    })
    @Select("SELECT id, tel, nickname, device, create_time, delflag FROM user_account")
    List<UserAccount> getAllUserAccounts();

}