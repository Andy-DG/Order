package com.example.order.item;

import com.example.order.orders.Order;
import com.example.order.orders.item_group.ItemGroup;
import com.example.order.util.Validate;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void updateStock(ItemGroup itemGroup) {
        Item item = itemRepository.getItemById(itemGroup.getSelectedItem().id());
        int amount = itemGroup.getAmount();
        itemRepository.getItemById(item.getId()).setStock(subtractAmountFromStock(item, amount));
    }

    private int subtractAmountFromStock(Item item, int amount) {
        if (item.getStock() < amount) {
            return 0;
        }
        return item.getStock() - amount;
    }
}
