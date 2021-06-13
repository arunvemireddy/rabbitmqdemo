package com.example.rabbitmq.RabbitMQDemo.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author arun vemireddy
 */

@Configuration
public class MessageConfig {

    public static final String ARUN = "arun";
    public static final String ARUN_EXCHANGE = "arun_exchange";
    public static final String ARUN_ROUTINGKEY = "arun_routingkey";

    @Bean
    public Queue queue(){
        return new Queue(ARUN);
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(ARUN_EXCHANGE);
    }

    @Bean
    public Binding binding(Queue queue,TopicExchange exchange){
     return BindingBuilder.bind(queue).to(exchange).with(ARUN_ROUTINGKEY);
    }

    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }

}
