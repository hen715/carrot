package com.market.carrot.Controller;

import com.market.carrot.service.ItemsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class WebController {

    private final ItemsService itemsService;
    @GetMapping("/")
    public String main(Model model)
    {
        model.addAttribute("items",itemsService.findAll());
        return "main";
    }

    @GetMapping("/items/save")
    public String save(){
        return "items-save";
    }
}
