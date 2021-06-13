package com.example.rabbitmq.RabbitMQDemo.consumer;

import com.example.rabbitmq.RabbitMQDemo.config.MessageConfig;
import com.example.rabbitmq.RabbitMQDemo.dto.OrderStatus;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

/**
 * @author arun vemireddy
 */

@Configuration
public class User {

    @RabbitListener(queues = MessageConfig.ARUN)
    public void consumemsgfrmq(OrderStatus status){
        System.out.println("Msg received from queue"+status);
    }
}
