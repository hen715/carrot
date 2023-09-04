package com.market.carrot.Controller;

import com.market.carrot.domain.Item;
import com.market.carrot.domain.user.User;
import com.market.carrot.dto.ItemDto;
import com.market.carrot.dto.ItemSaveDto;
import com.market.carrot.dto.ItemUpdateDto;
import com.market.carrot.dto.UserSaveDto;
import com.market.carrot.service.ItemsService;
import com.market.carrot.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ApiController {

    private final ItemsService itemsService;
    private final UserService userService;

    @PostMapping("/api/v1/items")
    public Long save(@RequestBody ItemSaveDto itemSaveDto, Principal principal){
        User user = this.userService.getUser(principal.getName());
        itemSaveDto.setOwner(user);
        return itemsService.save(itemSaveDto);
    }

    @PutMapping("/api/v1/items/{id}")
    private Long update(@PathVariable Long id, @RequestBody ItemUpdateDto updateDto,Principal principal){
        ItemDto itemOwner = this.itemsService.findById(id);
        User user = this.userService.getUser(principal.getName());
        if(itemOwner.getOwner().getId()==user.getId()){
            return itemsService.update(id,updateDto);
        }
        else{
            throw new IllegalStateException("본인의 게시글이 아닌 게시글을 수정할 수 없습니다.");
        }

    }

    @DeleteMapping("/api/v1/items/{id}")
    private Long delete(@PathVariable Long id, Principal principal){
        ItemDto itemOwner = this.itemsService.findById(id);
        User user = this.userService.getUser(principal.getName());
        if(itemOwner.getOwner().getId()==user.getId()){
            itemsService.delete(id);
            return id;
        }
        else{
            throw new IllegalStateException("본인의 게시글이 아닌 게시글은 삭제할 수 없습니다.");
        }

    }

    @PostMapping("/api/v2/join")
    public Long userSave(@Valid @RequestBody UserSaveDto userSaveDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new IllegalStateException("유효성 오류가 있습니다.");
        }
        return userService.join(userSaveDto);
    }


}
