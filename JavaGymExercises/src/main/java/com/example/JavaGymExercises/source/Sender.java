package com.example.JavaGymExercises.source;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class Sender {
    @Autowired
    private RabbitTemplate template;

    @Autowired
    private Queue queue;

    @Autowired
    private DirectExchange directExchange;

    public void ask_to_sub(int userId, int GymId) {
        String msg = "getMember " + userId + " " +GymId;
        this.template.convertAndSend(queue.getName(),msg);
    }


    public boolean login(int userid, int code) {
        boolean response = Boolean.parseBoolean((String)template.convertSendAndReceive(directExchange.getName(),"rpc","login "+userid+" "+code));
        return response;
    }

    public List<Integer> get_subscriptions(int userid) {
        String response = (String) template.convertSendAndReceive(directExchange.getName(),"rpc", "membership "+userid);

        if (response.equals("")) return null;
        List<Integer> ids = new ArrayList<>();

        for (String s : response.split(" ")){
            ids.add(Integer.parseInt(s));
        }

        return ids;
    }
}
