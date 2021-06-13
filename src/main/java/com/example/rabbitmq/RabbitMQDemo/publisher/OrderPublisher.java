package com.example.rabbitmq.RabbitMQDemo.publisher;

import com.example.rabbitmq.RabbitMQDemo.config.MessageConfig;
import com.example.rabbitmq.RabbitMQDemo.dto.Order;
import com.example.rabbitmq.RabbitMQDemo.dto.OrderStatus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author arun vemireddy
 */

@RestController
@RequestMapping("/order")
public class OrderPublisher {

    public static final String SUCCESS = "Success";
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/{restaurantname}")
    private String foodOrder(@RequestBody Order order,@PathVariable  String restaurantname){

        order.setOrderId(UUID.randomUUID().toString());
        OrderStatus status = new OrderStatus(order,"Progress","order placed successfully"+restaurantname);
        rabbitTemplate.convertAndSend(MessageConfig.ARUN_EXCHANGE,MessageConfig.ARUN_ROUTINGKEY,status);
        return SUCCESS;
    }
}
