package com.example.order.orders.service;

import com.example.order.item.domain.Item;
import com.example.order.item.domain.ItemRepository;
import com.example.order.item_group.api.dto.CreateItemGroupDTO;
import com.example.order.item_group.domain.ItemGroup;
import com.example.order.item_group.service.ItemGroupMapper;
import com.example.order.orders.api.dto.CreateOrderDTO;
import com.example.order.orders.api.dto.OrderDTO;
import com.example.order.orders.domain.Order;
import com.example.order.orders.domain.OrderRepository;
import com.example.order.user.customer.domain.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OrderService {
    private final ItemRepository itemRepository;
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final ItemGroupMapper itemGroupMapper;

    public OrderService(ItemRepository itemRepository, CustomerRepository customerRepository, OrderRepository orderRepository, OrderMapper orderMapper, ItemGroupMapper itemGroupMapper) {
        this.itemRepository = itemRepository;
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.itemGroupMapper = itemGroupMapper;
    }

    public OrderDTO createOrder(CreateOrderDTO createOrderDTO) {
        assertCustomerExists(createOrderDTO.getCustomerId());
        assertItemsExist(createOrderDTO.getCreateItemGroupDTOList());
        List<ItemGroup> newItemGroupList = createOrderDTO.getCreateItemGroupDTOList()
                .stream()
                .map(createItemGroupDTO -> itemGroupMapper.toEntity(itemRepository.getReferenceById(createItemGroupDTO.getItemId()), createItemGroupDTO.getAmount()))
                .toList();
        orderItems(newItemGroupList);
        Order order = orderMapper.toEntity(customerRepository.getReferenceById(createOrderDTO.getCustomerId()), newItemGroupList);
        orderRepository.save(order);
        return orderMapper.toDTO(order);
    }

    private void assertCustomerExists(String customerId) {
        if (!customerRepository.existsById(customerId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unable to find customer for id " + customerId);
        }
    }

    private void assertItemsExist(List<CreateItemGroupDTO> createItemGroupDTOList) {
        for (CreateItemGroupDTO item : createItemGroupDTOList) {
            String createItemId = item.getItemId();
            if (!itemRepository.existsById(createItemId)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No item found for id " + createItemId);
            }
        }
    }

    private void orderItems(List<ItemGroup> itemGroupList) {
        for (ItemGroup itemGroup : itemGroupList) {
            Item orderedItem = itemRepository.getReferenceById(itemGroup.getItem().getId());
            orderedItem.updateStock(orderedItem.getStock() - itemGroup.getAmount());
            itemRepository.save(orderedItem);
        }
    }

    public int findAmountForItem(String itemId) {
        if (!itemRepository.existsById(itemId)) {
            throw new IllegalArgumentException("No item found for id " + itemId);
        }
        return itemRepository.getReferenceById(itemId).getStock();
    }


}
