package com.PKstarter.PKstarter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class helloController {

    @GetMapping("/")
    public String hi(){
        return "index.html";
    }
}
