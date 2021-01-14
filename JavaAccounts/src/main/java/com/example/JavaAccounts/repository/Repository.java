package com.example.JavaAccounts.repository;
import com.example.JavaAccounts.model.account;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Repository implements RepositoryInterface {


    private static final String DB_FILENAME = "accounts.json";
    private static final String[] NAMES = {"Angel Murphy", "Miguel White", "Sarah Sanders", "Heather Smith",
            "Ronald Young", "Robert Brown", "Laura Rogers", "Christopher Phillips"};


    private static final int DB_BASEUSERS = 10;


    public List<account> createDBFile() throws IOException {

        File db = new File(DB_FILENAME);
        ObjectMapper mapper = new ObjectMapper();
        db.createNewFile();
        List<account> accounts = new ArrayList<>();
        FileWriter fileWriterr = new FileWriter(db, false);

        for (int i = 0; i < DB_BASEUSERS; i++) {
            accounts.add(new account(NAMES[i], i));
        }
        mapper.writeValue(fileWriterr, accounts);

        return accounts;
    }


    @Override
    public List<account> read() {
        File db = new File(DB_FILENAME);

        try {
            if (!db.exists()) {
                return createDBFile();
            }
            else {
                ObjectMapper mapper = new ObjectMapper();
                List<account> accounts = mapper.readValue(db, new TypeReference<ArrayList<account>>() {
                });

                return accounts;
            }
        } catch (IOException e) {

            System.out.println("File is failed");
            return null;
        }
    }

    @Override
    public void write(List<account> accounts) {

        try {
            File db = new File(DB_FILENAME);
            ObjectMapper mapper = new ObjectMapper();
            FileWriter fileWriterr = new FileWriter(db, false);
            mapper.writeValue(fileWriterr, accounts);
        }
        catch (IOException e) {
            System.out.println("Writing a file");
        }
    }



}
