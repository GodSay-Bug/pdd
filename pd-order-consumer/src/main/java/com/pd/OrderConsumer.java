package com.pd;

import com.pd.pojo.PdOrder;
import com.pd.service.OrderService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author WL
 * @Date 2020-11-4 10:39
 * @Version 1.0
 * 从RabbitMQ接受订单
 * 收到订单后，调用OrderServiceImpl。saveOrder（）把订单存储到数据库
 * <p>
 * spring 封装rabbitmq，接收消息只需要注解配置就可以
 * 自动创建实例，自动启动消费者连接rabbitmq，开始等待接收消息，收到的订单交给RabbitHandler处理
 */

@Component
@RabbitListener(queues = "orderQueue")  // 接收到的是一个序列化的数据
public class OrderConsumer {

    @Autowired
    OrderService orderService;

    @RabbitHandler   //指定消息处理方法，一个类只能有一个 @RabbitHandler
    public void receiveOrder(PdOrder pdOrder) throws Exception {//    对接收的数据自动进行转换
        orderService.saveOrder(pdOrder);
        System.out.println("订单已存储");

    }

}
