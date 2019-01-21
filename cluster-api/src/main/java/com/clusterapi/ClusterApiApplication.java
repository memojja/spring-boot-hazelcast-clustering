package com.clusterapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class ClusterApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClusterApiApplication.class, args);
    }

    @RestController
    class HealtCheckController{
        @RequestMapping("/healt")
        public String a(){
            return "UP !";
        }
    }
}

