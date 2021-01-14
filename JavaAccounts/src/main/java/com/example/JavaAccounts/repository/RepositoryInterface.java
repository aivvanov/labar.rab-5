package com.example.JavaAccounts.repository;
import com.example.JavaAccounts.model.account;
import java.util.List;

public interface RepositoryInterface {
    List<account> read();
    void write(List<account> accounts);




}
