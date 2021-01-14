package com.example.JavaAccounts.service;

import com.example.JavaAccounts.model.account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.ArrayList;
import java.util.List;

public class AccountIntrSerDB implements AccountIntrSer {

    @Autowired
    MongoTemplate mTemplate;

    @Override
    public void addMembership(int accId, int gymId) {

        account acc = getAccount(accId);
        List<Integer> subs = acc.getMemberShip();
        subs.add(gymId);
        mTemplate.updateFirst(Query.query(Criteria.where("id").is(accId)), Update.update("Membership", subs), account.class);
    }

    @Override
    public void removeMembership(int accntId, Integer gymId) {

        account accnt = getAccount(accntId);
        List<Integer> subs = accnt.getMemberShip();
        subs.remove(gymId);
        mTemplate.updateFirst(Query.query(Criteria.where("id").is(accntId)), Update.update("Membership", subs), account.class);
    }

    @Override
    public void addAccount(account accnt) {

        Query q = new Query();
        q.with(Sort.by(Sort.Direction.DESC, "id"));
        q.limit(1);
        account maxObject = mTemplate.findOne(q, account.class);
        int id = maxObject.getId();
        accnt.setId(id+1);
        accnt.setMemberShip(new ArrayList<>());
        mTemplate.insert(accnt);
    }

    @Override
    public account getAccount(int id) {

        account accnt = mTemplate.query(account.class).matching(Query.query(Criteria.where("id").is(id))).oneValue();
        return accnt;


    }
}