package com.young;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Youngni
 */
@SpringBootApplication
@RestController
@EnableSwagger2
public class DemoApplication {

    public static void main(String[] args){
        SpringApplication.run(DemoApplication.class , args);
    }

    @GetMapping( value = "/hello")
    public String hello(){
        return "Hello spring security , /hello";
    }
    @GetMapping( value = "/hello/{id}")
    public String hello1(@PathVariable Integer id){
        return "Hello spring security,/hello/"+id;
    }

}
