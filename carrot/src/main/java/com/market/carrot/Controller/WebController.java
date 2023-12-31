package com.market.carrot.Controller;

import com.market.carrot.domain.user.User;
import com.market.carrot.dto.UserDto;
import com.market.carrot.service.ItemsService;
import com.market.carrot.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.h2.engine.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequiredArgsConstructor
@Controller
public class WebController {

    private final ItemsService itemsService;
    private final UserService userService;
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
    @GetMapping("/join/user")
    public String joinUser(){
        return "join";
    }

    @GetMapping("/loginFail")
    public String loginFail(){
        return "loginFail";
    }

/*    @PostMapping("/user/login")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password){
        UserDto user = userService.login(email,password);
        if(user == null){
            return "main";
        }
        return "main";
    }*/

}
