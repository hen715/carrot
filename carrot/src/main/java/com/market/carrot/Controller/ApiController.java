package com.market.carrot.Controller;

import com.market.carrot.dto.ItemSaveDto;
import com.market.carrot.dto.ItemUpdateDto;
import com.market.carrot.service.ItemsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ApiController {

    private final ItemsService itemsService;

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


}
