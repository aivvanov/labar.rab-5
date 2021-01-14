package com.example.JavaGymExercises.repository;

import com.example.JavaGymExercises.model.AGym;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Repository implements RepositoryInterface {

    private static final String DB_FILENAME = "gym.json";


    public List<AGym> createDBFile() throws IOException {
        File db = new File(DB_FILENAME);
        ObjectMapper mapper = new ObjectMapper();
        db.createNewFile();

        List<AGym> gymArrayList = new ArrayList<>();
        FileWriter fileWriterr = new FileWriter(db,false);
        AGym c = new AGym();
        ArrayList<String> lvlOfPreparation = new ArrayList<>();
        ArrayList<String> exercises = new ArrayList<>();
        c.setInstructor("Dwayne Johnson");
        c.setId(0);
        c.setGymName("Workd Class gym");
        lvlOfPreparation.add("Beginning Athlete");
        exercises.add("Enhanced Training");
        c.setLvlOfPreparation(lvlOfPreparation);
        c.setExercises(exercises);
        gymArrayList.add(c);
        c = new AGym();
        lvlOfPreparation = new ArrayList<>();
        exercises = new ArrayList<>();
        c.setInstructor("Arnold Schwarzenegger");
        c.setId(1);
        c.setGymName("The HOUSTONIAN CLUB");
        lvlOfPreparation.clear();
        exercises.clear();
        lvlOfPreparation.add("Advanced Athlete");
        exercises.add("Light Training");
        c.setLvlOfPreparation(lvlOfPreparation);
        c.setExercises(exercises);
        gymArrayList.add(c);
        mapper.writeValue(fileWriterr, gymArrayList);

        return gymArrayList;
    }

    @Override
    public List<AGym> read() {
        File db = new File(DB_FILENAME);
        try {

            if (!db.exists()) {
                return createDBFile();
            }
            else {
                ObjectMapper map = new ObjectMapper();
                List<AGym> gymArrayList = map.readValue(db, new TypeReference<ArrayList<AGym>>() {});
                return gymArrayList;
            }
        }
        catch (IOException e) {
            System.out.println("The file if failed");

            return null;
        }

    }

    @Override
    public void write(List<AGym> accounts) {
        try {
            File db = new File(DB_FILENAME);
            ObjectMapper map = new ObjectMapper();
            FileWriter fileWriterr = new FileWriter(db, false);
            map.writeValue(fileWriterr, accounts);
        }
        catch (IOException e) {

            System.out.println("Writing a file");
        }
    }
}
