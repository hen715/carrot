package com.market.carrot.dto;

import com.market.carrot.domain.Item;
import com.market.carrot.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ItemSaveDto {
    private String title;
    private String description;
    private User owner;
    private String place;
    private Long price;
    private String image;

    @Builder
    public ItemSaveDto(String title, String description, User owner, String place, Long price, String image){
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
