package com.example.JavaAccounts.source;


import com.example.JavaAccounts.model.account;
import com.example.JavaAccounts.service.AccountIntrSer;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

@RabbitListener(queues = "mainQueue")
public class Receiver {

    @Autowired
    private AccountIntrSer accountIntrSer;

    @RabbitHandler
    public void receive(String in) {

        if (in.startsWith("GetMember")) {
            String[] params = in.split(" ");
            int userid = Integer.parseInt(params[1]);
            int courseid = Integer.parseInt(params[2]);
            accountIntrSer.addMembership(userid,courseid);
        }

        System.out.println("Received: "+ in);
    }

    @RabbitListener(queues = "rpc-ex.requests")
    public String rpc_requests(String in) {

        if (in.startsWith("login")) {
            String[] params = in.split(" ");
            int userid = Integer.parseInt(params[1]);
            int code = Integer.parseInt(params[2]);
            account user = accountIntrSer.getAccount(userid);

            if (user == null) return "false";
            return "true";
        }
        else if (in.startsWith("membership")) {
            String[] params = in.split(" ");
            int userid = Integer.parseInt(params[1]);
            account user = accountIntrSer.getAccount(userid);

            if (user == null) return "";
            String response = "";

            for (Integer i : user.getMemberShip()) {
                response += i + " ";
            }
            return response.trim();
        }

        return null;
    }

    @RabbitListener(queues = "rpc-ex.requests")
    public String get_memberships(String in) {

        if (in.startsWith("memberships")) {
            String[] params = in.split(" ");
            int userid = Integer.parseInt(params[1]);
            account user = accountIntrSer.getAccount(userid);

            if (user == null) return "";
            String response = "";

            for (Integer i : user.getMemberShip()) {
                response += i + " ";
            }

            return response.trim();
        }
        return "";
    }
}
