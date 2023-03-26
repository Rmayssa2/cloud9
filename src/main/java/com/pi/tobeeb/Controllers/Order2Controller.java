package com.pi.tobeeb.Controllers;

import com.pi.tobeeb.Entities.Order2;
import com.pi.tobeeb.Services.Order2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class Order2Controller {

    @Autowired
    private Order2Service order2Service;

    @GetMapping
    public List<Order2> getAllOrders() {
        return order2Service.getAllOrders();
    }

    @GetMapping("/{id}")
    public Order2 getOrderById(@PathVariable int id) {
        return order2Service.getOrderById(id);
    }


    @PostMapping
    public Order2 createOrder(@RequestBody Order2 order) {
        return order2Service.createOrder(order);
    }

    @PutMapping("/{id}")
    public Order2 updateOrder(@PathVariable int id, @RequestBody Order2 updatedOrder) {
        return order2Service.updateOrder(id, updatedOrder);
    }

    @DeleteMapping("/{id}")
    public String  deleteOrder(@PathVariable int id) {
        order2Service.deleteOrder(id);
        return "order deleted succefully";
    }
}
