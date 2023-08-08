package com.market.carrot.dto;

import com.market.carrot.domain.Item;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ItemListDto {

    private Long id;
    private String title;
    private String place;
    private Long price;
    private String image;
    private LocalDateTime modifiedDate;

    public ItemListDto(Item entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.place = entity.getPlace();
        this.price = entity.getPrice();
        this.image = entity.getImage();
        this.modifiedDate = entity.getModifiedDate();
    }
}
