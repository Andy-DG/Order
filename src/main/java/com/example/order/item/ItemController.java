package com.example.order.item;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    //@PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public AddItemDTO addItem(@RequestBody AddItemDTO addItemDTO) {
        this.itemService.addItem(addItemDTO);
        return addItemDTO;
    }
}
