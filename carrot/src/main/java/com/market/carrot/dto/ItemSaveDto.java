package com.market.carrot.dto;

import com.market.carrot.domain.Item;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ItemSaveDto {
    private String title;
    private String description;
    private String owner;
    private String place;
    private Long price;
    private String image;

    @Builder
    public ItemSaveDto(String title, String description, String owner, String place, Long price, String image){
        this.title = title;
        this.description =description;
        this.owner = owner;
        this.place = place;
        this.price = price;
        this.image = image;
    }
    public Item toEntity(){
        return Item.builder().
                title(title)
                .description(description)
                .owner(owner)
                .place(place)
                .price(price)
                .image(image)
                .build();
    }
}
