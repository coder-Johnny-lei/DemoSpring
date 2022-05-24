package com.sdjz.framework.aop;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.annotation.*;

/**
 * JsonResult 格式返回值注解
 *
 * @author Eddy Zhang
 * @date 2021/9/15
 * @version 1.0
 * @className JsonResultController
 * @description
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@ResponseBody
@Controller
public @interface JsonResultController {

    /**
     * 是否自动将结果封装为：JsonResult对象。
     * 默认：true
     * @author Eddy Zhang
     * @date 2021/4/27
     * @return
     */
    boolean autoWrap() default true;

}

