package com.market.carrot.service;

import com.market.carrot.domain.ItemRepository;
import com.market.carrot.dto.ItemListDto;
import com.market.carrot.dto.ItemSaveDto;
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
    public Long save(ItemSaveDto itemSaveDto){
        return itemRepository.save(itemSaveDto.toEntity()).getId();
    }

    @Transactional
    public List<ItemListDto> findAll(){
        return itemRepository.findAll().stream().map(ItemListDto::new).collect(Collectors.toList());
    }

}
