package com.example.JavaAccounts.service;

import com.example.JavaAccounts.model.account;

public interface AccountIntrSer {

    void addMembership(int accntId, int gymId);

    void removeMembership(int accId, Integer gymId);

    void addAccount(account accnt);

    account getAccount(int id);
}
