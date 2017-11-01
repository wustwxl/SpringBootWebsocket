package com.wust.utils;

import org.springframework.beans.BeanUtils;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: F7687967
 * @date: 2017/10/20
 * @time: 下午 02:37
 * @description: 类型转换工具类, 此Demo未用到
 * 主要用于添加新的对象时，将前端传过来的DTO类或者VO类对象转换为数据库对应的Entity实体类对象
 */
public class TypeConversionUtil {
    /**
     * @Description: 代码优化
     * 将接口API中的参数对象 利用浅复制转换为实体类对应的对象
    */
    public static void VoConvertToEntity(Object vo, Object entity) {
        BeanUtils.copyProperties(vo,entity);
    }

}
