package com.enike.admin;

import com.enike.admin.dao.UserMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@MapperScan("com.enike.admin.dao")
public class AdminApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext app = SpringApplication.run(AdminApplication.class, args);
    }

}
