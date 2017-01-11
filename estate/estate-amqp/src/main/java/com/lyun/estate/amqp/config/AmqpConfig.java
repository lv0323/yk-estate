package com.lyun.estate.amqp.config;

import com.lyun.estate.amqp.exceptions.AmqpBizException;
import com.lyun.estate.amqp.exceptions.AmqpFetalException;
import com.lyun.estate.amqp.spec.config.AmqpSpecConfig;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.ConditionalRejectingErrorHandler;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.lyun.estate")
@Import(AmqpSpecConfig.class)
@PropertySource("classpath:/estate/amqp/server.properties")
@PropertySource(value = "file:${LVJINSUO_HOME}/conf/estate/amqp/server.properties", ignoreResourceNotFound = true)
@EnableRabbit
public class AmqpConfig {

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory = new SimpleRabbitListenerContainerFactory();
        simpleRabbitListenerContainerFactory.setConnectionFactory(connectionFactory);
        simpleRabbitListenerContainerFactory.setConcurrentConsumers(2);
        simpleRabbitListenerContainerFactory.setMaxConcurrentConsumers(5);
        simpleRabbitListenerContainerFactory.setMessageConverter(new Jackson2JsonMessageConverter());
        simpleRabbitListenerContainerFactory.setErrorHandler(conditionalRejectingErrorHandler());
        return simpleRabbitListenerContainerFactory;
    }

    private ConditionalRejectingErrorHandler conditionalRejectingErrorHandler() {
        return new ConditionalRejectingErrorHandler(new ConditionalRejectingErrorHandler.DefaultExceptionStrategy() {
            @Override
            protected boolean isUserCauseFatal(Throwable cause) {
                if (cause instanceof AmqpFetalException
                        || cause instanceof AmqpBizException) {
                    return true;
                }
                return false;
            }
        });
    }

}
