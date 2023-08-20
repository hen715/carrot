package com.market.carrot.service;

import com.market.carrot.domain.Item;
import com.market.carrot.domain.ItemRepository;
import com.market.carrot.domain.user.UserRepository;
import com.market.carrot.dto.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ItemsService {

    private final ItemRepository itemRepository;

    @Transactional
    public Long save(ItemSaveDto itemSaveDto) {
        return itemRepository.save(itemSaveDto.toEntity()).getId();
    }

    @Transactional
    public List<ItemListDto> findAll() {
        return itemRepository.findAll().stream().map(ItemListDto::new).collect(Collectors.toList());
    }

    @Transactional
    public ItemDto findById(Long id) {
        Item dto = itemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 상품이 없습니다."));
        return new ItemDto(dto);
    }

    @Transactional
    public Long update(Long id,ItemUpdateDto updateDto) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 상품이 없습니다."));
        item.update(updateDto.getTitle(),updateDto.getDescription(),updateDto.getPlace(),updateDto.getPrice(),updateDto.getImage());

        return id;
    }

    @Transactional
    public void delete(Long id){
        Item item = itemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 상품이 없습니다."));
        itemRepository.delete(item);
    }


}

