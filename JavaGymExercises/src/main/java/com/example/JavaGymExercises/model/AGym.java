package com.example.JavaGymExercises.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "Gym")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AGym {

    @Field("Gym Name")
    private String GymName;

    @Field("Instructor")
    private String instructor;

    @Field("id")
    private int id;

    @Field("level Of Preparation")
    private List<String> lvlOfPreparation;

    @Field("Exercises")
    private List<String> exercises;

    public String getGymName() {
        return GymName;
    }
    public void setGymName(String GymName) {
        this.GymName = GymName;
    }
    public String getInstructor() {
        return instructor;
    }
    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }


    public List<String> getLvlOfPreparation() {
        return lvlOfPreparation;
    }

    public void setLvlOfPreparation(List<String> lvlOfPreparation) {
        this.lvlOfPreparation = lvlOfPreparation;
    }

    public List<String> getExercises() {
        return exercises;
    }

    @Override
    public String toString() {

        return "Gym Name '" + GymName + "\'\n" +
                "Instructor: " + instructor + '\n' +
                "Level of Preparation: " + lvlOfPreparation +
                "\nExercises: " + exercises +
                '\n';
    }
    public void setExercises(List<String> exercises) {
        this.exercises = exercises;
    }
}
