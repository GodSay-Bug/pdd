package com.pd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;



@SpringBootApplication
@MapperScan("com.pd.mapper")
public class RunPdAPP extends WebMvcConfigurerAdapter
        implements EmbeddedServletContainerCustomizer {

    public static void main(String[] args) {
        SpringApplication.run(RunPdAPP.class, args);
    }

    @Override
    public void configureContentNegotiation
            (ContentNegotiationConfigurer configurer) {
        //设置可以用*.html访问json
        configurer.favorPathExtension(false);
    }

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        container.setSessionTimeout(60 * 30);//单位为秒 0永不过期
    }

    /*
    spring 封装Queue实例，包含队列的信息
    RabbitAutoConfigration 自动配置类，会自动发现spring容器中的queue实例，
    根据queue实例中的信息，连接rabbitmq，定义这个队列
     */
	@Bean
    public Queue getQueue() {
        Queue q = new Queue("orderQueue", true);
        return q;
    }

}
