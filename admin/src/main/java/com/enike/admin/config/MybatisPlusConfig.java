package com.enike.admin.config;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description  mybatis-plus配置
 * @Date 2020/1/3 22:13
 **/
@Configuration
@MapperScan("com.enike.admin.dao")	// 扫描DAO
public class MybatisPlusConfig {

   /**
    * @Description  开启分页插件
    * @Date 2020/1/3 22:14
    **/
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor().setDialectType("mysql");
    }
}
