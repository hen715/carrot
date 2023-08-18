package com.market.carrot.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ItemUpdateDto {
    private String title;
    private String description;
    private String place;
    private Long price;
    private String image;

    @Builder
    public ItemUpdateDto(String title, String description, String place,Long price, String image){
        this.title=title;
        this.description=description;
        this.place=place;
        this.price=price;
        this.image=image;
    }
}
