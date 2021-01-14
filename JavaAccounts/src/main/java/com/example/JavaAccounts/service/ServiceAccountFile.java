package com.example.JavaAccounts.service;

import com.example.JavaAccounts.model.account;
import com.example.JavaAccounts.model.AccountMassive;
import com.example.JavaAccounts.repository.RepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


public class ServiceAccountFile implements AccountIntrSer {

    @Autowired
    public AccountMassive s;

    @Autowired
    public RepositoryInterface rep;

    @Override
    public void addMembership(int accntId, int gymId) {

        account username = getAccount(accntId);
        List<Integer> subscriptions = username.getMemberShip();
        subscriptions.add(gymId);
        username.setMemberShip(subscriptions);
        rep.write(s.accountList);

    }

    @Override
    public void removeMembership(int accntId, Integer gymId) {

        account user = getAccount(accntId);
        List<Integer> memberships = user.getMemberShip();

        if (memberships.contains(gymId)) {
            memberships.remove(gymId);
            user.setMemberShip(memberships);
            rep.write(s.accountList);
        }
    }


    @Override
    public void addAccount(account accnt) {

        int maxID = 0;
        for (com.example.JavaAccounts.model.account account: s.accountList) {

            if (account.getId() > maxID) maxID = account.getId();
        }
        accnt.setId(maxID + 1);
        accnt.setMemberShip(new ArrayList<>());
        s.accountList.add(accnt);
        rep.write(s.accountList);
    }


    @Override
    public account getAccount(int id) {
        for (account acnt: s.accountList){

            if (acnt.getId() == id) {
                return acnt;
            }
        }
        return null;
    }


}