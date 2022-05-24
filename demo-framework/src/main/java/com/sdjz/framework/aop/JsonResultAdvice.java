package com.sdjz.framework.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sdjz.common.core.domain.vo.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.annotation.Annotation;


/**
 *  返回结果自动包装，并解决重复包装问题。
 *
 * @author eddy zhang
 * @date  2021年4月24日 最后一次更新
 * @jdkVersion JDK1.8
 * @version 1.0.0
 */
@RestControllerAdvice
@Slf4j
public class JsonResultAdvice implements ResponseBodyAdvice<Object> {

    private static final Class<? extends Annotation> JSON_RESULT_CONTROLLER = JsonResultController.class;

    private final ObjectMapper objectMapper;


    @Autowired
    public JsonResultAdvice(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * 判断类或者方法是否使用了 @JsonResultController
     */
    @Override
    public boolean supports(MethodParameter returnType,
                            Class<? extends HttpMessageConverter<?>> converterType) {
        return AnnotatedElementUtils.hasAnnotation(returnType.getContainingClass(), JSON_RESULT_CONTROLLER) ||
                returnType.hasMethodAnnotation(JSON_RESULT_CONTROLLER);
    }


    /**
     * 当类或者方法使用了 @JsonResultController 就会调用这个方法
     */
    @Override
    public Object beforeBodyWrite(
            Object body,
            MethodParameter returnType,
            MediaType selectedContentType,
            Class<? extends HttpMessageConverter<?>> selectedConverterType,
            ServerHttpRequest request,
            ServerHttpResponse response) {

        // 二进制文件，应该直接返回
        if (body instanceof byte[]){
            return body;
        }
        else {
            // 设置 Header
            response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

            // 防止重复包裹的问题出现
            if ( body instanceof JsonResult) {
                return body;
            }

            /**
             * @RestController / @ResponseBody
             *      不会对String类型进行包装，也就是不会使用 MessageConverter进行转换。
             *      如果方法的返回值是String，而下面直接 return 一个 JsonResult对象，就会出现错误：
             *      Object: JsonResult cannot be cast to java.lang.String
             *      因此需要将方法返回的string数据先包装成JsonResult，
             *      再将JsonResult手动转换成 Json格式的 String 返回。
             */
            if (body instanceof String){
                try {
                    response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
                    return objectMapper.writeValueAsString(JsonResult.success(body));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }

            return JsonResult.success(body);
        }
    }
}
