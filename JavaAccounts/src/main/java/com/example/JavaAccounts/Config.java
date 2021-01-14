package com.example.JavaAccounts;

import com.example.JavaAccounts.model.AccountMassive;
import com.example.JavaAccounts.repository.Repository;
import com.example.JavaAccounts.repository.RepositoryInterface;
import com.example.JavaAccounts.service.AccountIntrSer;
import com.example.JavaAccounts.service.AccountIntrSerDB;
import com.example.JavaAccounts.source.Receiver;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.beans.BeanProperty;
import java.io.IOException;

@Configuration
public class Config {

    @Bean
    public AccountIntrSer getAccService() throws IOException{
        return new AccountIntrSerDB();
    }

    @Bean
    public RepositoryInterface getRepository() {
        return new Repository();
    }

    @Bean
    public AccountMassive getStore() {
        return new AccountMassive(getRepository().read());
    }

    @Bean
    public Receiver getReceiver() {
        return new Receiver();
    }

    @Bean
    public Queue queue() {
        return new Queue("rpc-ex.requests");
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange("rpc-ex");
    }

    @Bean
    public Binding binding(DirectExchange exchange, Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with("rpc");
    }
}
