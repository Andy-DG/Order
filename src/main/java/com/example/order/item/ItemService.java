package com.example.order.item;

import com.example.order.orders.Order;
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

    public void addItem(AddItemDTO addItemDTO) {
        Validate.objectIsNotNull(addItemDTO);
        Item item = itemMapper.toEntity(addItemDTO);
        itemRepository.addItem(item);
    }

    public void updateStock(Order order) {
        order.getItemGroups().forEach(itemGroup -> itemGroup.getItem().setStock(itemGroup.getItem().getStock() - itemGroup.getAmount()));
    }
}
