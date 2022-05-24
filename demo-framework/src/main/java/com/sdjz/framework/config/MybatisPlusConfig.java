package com.sdjz.framework.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.sdjz.common.constant.CmnConst;
import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Date;

@Configuration
@MapperScan("com.sdjz.**.mapper")
public class MybatisPlusConfig {


    /**
     * 设置分页插件
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }


    /**
     * 功能：
     *      解决 insert/update 数据时默认值问题
     * 用法：
     *      只需要将 MetaObjectHandler 的实现类 注册到 Spring 容器中，让Spring 自动生成一个 Bean，就回起作用。
     *      使用 @Component 或 @Bean 创建 Bean 实例都可以。
     */
    @Bean
    public MetaObjectHandler metaObjectHandler() {
        return new MetaObjectHandler() {
            @Override
            public void insertFill(MetaObject metaObject) {
                String username = this.loginUserName();
                this.setFieldValByName("createUser", username, metaObject);   // 属性名，不是字段名
                this.setFieldValByName("createTime", new Date(), metaObject);
                this.setFieldValByName("updateUser", username, metaObject);
                this.setFieldValByName("updateTime", new Date(), metaObject);
                this.setFieldValByName("deleteFlag", CmnConst.FALSE, metaObject);
            }
            @Override
            public void updateFill(MetaObject metaObject) {
                String username =  this.loginUserName();
                this.setFieldValByName("updateUser", username, metaObject);  // 属性名，不是字段名
                this.setFieldValByName("updateTime", new Date(), metaObject);
            }

            public String loginUserName(){
                SecurityContext context = SecurityContextHolder.getContext();
                String username = "SYSTEM";
                if (null != context && null != context.getAuthentication()){
                    username = context.getAuthentication().getName();
                }
                return username;
            }
        };
    }




}
