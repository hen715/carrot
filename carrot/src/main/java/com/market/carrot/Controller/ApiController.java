package com.market.carrot.Controller;

import com.market.carrot.dto.ItemSaveDto;
import com.market.carrot.service.ItemsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ApiController {

    private final ItemsService itemsService;

    @PostMapping("/api/v1/items")
    public Long save(@RequestBody ItemSaveDto itemSaveDto){
        return itemsService.save(itemSaveDto);
    }


}
