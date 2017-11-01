package com.wust.utils;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: F7687967
 * @date: 2017/10/16
 * @time: 下午 04:05
 * @description: 统一异常处理, 通过使用@ControllerAdvice定义统一的异常处理类，而不是在每个Controller中逐个定义
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * @Description: @ExceptionHandler用来定义函数针对的异常类型，最后将Exception对象和请求URL映射到error.ftl中
     */
    //以下是 返回HTML页面 的异常处理
    /*
    @ExceptionHandler(value = MyException.class)
    public ModelAndView myErrorHandler(MyException ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("code", ex.getCode());
        modelAndView.addObject("msg", ex.getMsg());
        return modelAndView;
    }
    */


    //以下是 返回json数据 的异常处理

    //全局异常捕捉处理
    @ResponseBody
    // 如果全部异常处理返回json，那么可以使用 @RestControllerAdvice 代替 @ControllerAdvice ，这样在方法上就可以不需要添加 @ResponseBody。
    @ExceptionHandler(value = Exception.class)
    public Map errorHandler(Exception ex) {
        Map map = new HashMap();
        map.put("code", 100);
        map.put("msg", ex.getMessage());
        return map;
    }

    // 拦截捕捉自定义异常 MyException.class
    @ResponseBody
    @ExceptionHandler(value = MyException.class)
    public Map myErrorHandler(MyException ex) {
        Map map = new HashMap();
        map.put("code", ex.getCode());
        map.put("msg", ex.getMsg());
        return map;
    }

}