package com.example.JavaAccounts.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
@Document(collection = "accounts")
@JsonIgnoreProperties(ignoreUnknown = true)
public class account {


    @Field("username")
    private String username;
    @Field("id")
    private int id;
    @Field("GymMembership")
    private List<Integer> memberShip;
    @Field("code")
    private int code;
    public int getCode() {
        return code;
    }


    public void setCode(int code) {
        this.code = code;
    }


    public account(String username, int id) {
        this.username = username;
        this.id = id;
        this.memberShip = new ArrayList<>();
        this.code = (new Random()).nextInt(8999)+1000;
    }


    public String getUsername() {
        return username;
    }

    public void setName(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public account() {

    }
    public account(String username, int id, List<Integer> memberShip) {

        this.username = username;

        this.id = id;

        this.memberShip = memberShip;

        this.code = (new Random()).nextInt(8999)+1000;
    }


    public List<Integer> getMemberShip() {
        return memberShip;
    }

    public void setMemberShip(List<Integer> memberShip) {
        this.memberShip = memberShip;
    }


    @Override
    public String toString() {

        String str = "User #"+ id + ": "+username+"\n";
        str += "Subscriptions : [";

        for (Integer id : memberShip) {
            str += id + " ";

        }
        str += "]";
        return str;
    }
}
