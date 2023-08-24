package com.market.carrot.Controller;

import com.market.carrot.dto.ItemSaveDto;
import com.market.carrot.dto.ItemUpdateDto;
import com.market.carrot.dto.UserSaveDto;
import com.market.carrot.service.ItemsService;
import com.market.carrot.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ApiController {

    private final ItemsService itemsService;
    private final UserService userService;

    @PostMapping("/api/v1/items")
    public Long save(@RequestBody ItemSaveDto itemSaveDto){
        return itemsService.save(itemSaveDto);
    }

    @PutMapping("/api/v1/items/{id}")
    private Long update(@PathVariable Long id, @RequestBody ItemUpdateDto updateDto){
        return itemsService.update(id,updateDto);
    }

    @DeleteMapping("/api/v1/items/{id}")
    private Long delete(@PathVariable Long id){
        itemsService.delete(id);
        return id;
    }

    @PostMapping("/api/v1/join")
    public Long userSave(@Valid @RequestBody UserSaveDto userSaveDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new IllegalStateException("유효성 오류가 있습니다.");
        }
        return userService.join(userSaveDto);
    }


}
