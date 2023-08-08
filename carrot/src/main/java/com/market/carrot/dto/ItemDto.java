package com.market.carrot.dto;

import com.market.carrot.domain.Item;
import lombok.Getter;

@Getter
public class ItemDto {

    private Long id;
    private String title;
    private String description;
    private String owner;
    private String place;
    private Long price;
    private String image;

    public ItemDto(Item entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.description = entity.getDescription();
        this.owner = entity.getOwner();
        this.place = entity.getPlace();
        this.price = entity.getPrice();
        this.image = entity.getImage();
    }
}
