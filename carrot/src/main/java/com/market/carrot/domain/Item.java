package com.market.carrot.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Item extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT" , nullable = false)
    private String description;

    @Column(nullable = false)
    private String owner;

    @Column(nullable = false)
    private String place;

    @Column(nullable = false)
    private Long price;

    private String image;

    @Builder
    public Item(String title, String description, String owner, String place, Long price, String image){
        this.title =title;
        this.description = description;
        this.owner =owner;
        this.place = place;
        this.price = price;
        this.image = image;
    }
}
