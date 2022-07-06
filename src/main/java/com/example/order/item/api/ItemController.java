package com.example.order.item.api;

import com.example.order.item.api.dto.ItemDTO;
import com.example.order.item.service.ItemService;
import com.example.order.item.api.dto.CreateItemDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    //@PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDTO addItem(@RequestBody CreateItemDTO createItemDTO) {
        return itemService.createItem(createItemDTO);
    }
}
