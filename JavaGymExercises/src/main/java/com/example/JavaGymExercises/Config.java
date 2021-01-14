package com.example.JavaGymExercises;

import com.example.JavaGymExercises.model.GymMassive;
import com.example.JavaGymExercises.repository.Repository;
import com.example.JavaGymExercises.repository.RepositoryInterface;
import com.example.JavaGymExercises.service.GymIntSer;
import com.example.JavaGymExercises.service.GymIntrSerBD;
import com.example.JavaGymExercises.source.Sender;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class Config {

    @Bean
    public GymIntSer getAccountSer() throws IOException{
        return new GymIntrSerBD();
    }

    @Bean
    public RepositoryInterface getRepository() {
        return new Repository();
    }

    @Bean
    public GymMassive getGymMassive() {
        return new GymMassive(getRepository().read());
    }

    @Bean
    public Queue getMainQueue(){
        return new Queue("mainQueue");
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange("rpc-ex");
    }

    @Bean
    public Sender send() {
        return new Sender();
    }


}
