package com.example.order.orders;

import com.example.order.item.AddItemDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    //@PreAuthorize("hasAuthority('CUSTOMER')")
    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public AddOrderDTO addItem(@RequestBody AddOrderDTO addOrderDTO) {
        this.orderService.addOrder(addOrderDTO);
        return addOrderDTO;
    }
}
