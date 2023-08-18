package com.market.carrot.Controller;

import com.market.carrot.service.ItemsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    @GetMapping("/item/save")
    public String save(){
        return "item-save";
    }

    @GetMapping("/item/update/{id}")
    public String update(@PathVariable Long id, Model model){
        model.addAttribute("item",itemsService.findById(id));
        return "item-update";
    }

    @GetMapping("/itemShow/{id}")
    public String itemPage(@PathVariable Long id, Model model){
        model.addAttribute("item",itemsService.findById(id));
        return "itemPage";
    }
}
