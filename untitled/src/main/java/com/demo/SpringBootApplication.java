package com.demo;

import com.demo.controller.Student;
import com.demo.mapper.StudentMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@EnableAutoConfiguration //开启自动配置

@MapperScan("com.demo.mapper")
@org.springframework.boot.autoconfigure.SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class SpringBootApplication {
    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplication.class, args);
    }

    @SpringBootTest
    public class SbvApplicationTests {
        @Autowired
        private StudentMapper studentMapper;

        public void ceshi() {
            for (Student student : studentMapper.selectList(null)) {
                System.out.println(student.toString());
            }
        }

    }
}
