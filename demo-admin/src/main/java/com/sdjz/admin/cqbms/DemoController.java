package com.sdjz.admin.cqbms;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RequestMapping("/demo")
@RestController
public class DemoController {

    @GetMapping("/test")
    public String demo(String a){
        return a;
    }
}
