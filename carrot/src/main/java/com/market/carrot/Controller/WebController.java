package com.market.carrot.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class WebController {

    @GetMapping("/")
    public String main(){
        return "main";
    }

    @GetMapping("/items/save")
    public String save(){
        return "items-save";
    }
}
