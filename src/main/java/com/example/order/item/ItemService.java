package com.example.order.item;

import com.example.order.orders.item_group.ItemGroup;
import com.example.order.util.Validate;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    public ItemService(ItemRepository itemRepository, ItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
    }

    public ItemDTO addItem(AddItemDTO addItemDTO) {
        Validate.objectIsNotNull(addItemDTO);
        Item item = itemMapper.toEntity(addItemDTO);
        ItemDTO itemDTO = itemMapper.toDTO(item);
        itemRepository.addItem(item);
        return itemDTO;
    }

    public void subtractOrderAmountFromStock(ItemGroup itemGroup) {
        itemRepository.subtractOrderAmountFromStock(itemGroup);
    }
}
