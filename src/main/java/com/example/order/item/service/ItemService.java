package com.example.order.item.service;

import com.example.order.item.api.dto.CreateItemDTO;
import com.example.order.item.api.dto.ItemDTO;
import com.example.order.item.domain.Item;
import com.example.order.item.domain.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Transactional
public class ItemService {
    private final Logger itemServiceLogger = LoggerFactory.getLogger(ItemService.class);

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    public ItemService(ItemRepository itemRepository, ItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
    }

    public ItemDTO createItem(CreateItemDTO createItemDTO) {
        assertUniqueName(createItemDTO.getName());
        Item item = itemMapper.toEntity(createItemDTO);
        itemRepository.save(item);
        return itemMapper.toDTO(item);
    }

    private void assertUniqueName(String name) {
        if (itemRepository.findAll().stream()
                .anyMatch(item -> item.getName().equalsIgnoreCase(name))) {
            itemServiceLogger.error("Unable to create item: an item with this name already exists.");
            throw new IllegalArgumentException("Unable to create item: an item with this name already exists.");
        }
        itemServiceLogger.info("Item name validated as unique.");
    }
}
