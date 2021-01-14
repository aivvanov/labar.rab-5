package com.example.JavaGymExercises.service;

import com.example.JavaGymExercises.model.AGym;
import com.example.JavaGymExercises.model.GymMassive;
import com.example.JavaGymExercises.repository.RepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


public class FileGymService implements GymIntSer {


    @Autowired
    public GymMassive gymMassive;

    @Autowired
    public RepositoryInterface repositoryInterface;

    @Override
    public List<AGym> getAllGymName() {

        return gymMassive.GymList;
    }

    @Override
    public List<AGym> getGymName(List<Integer> gymNameID) {
        List<AGym> gymArrayList = new ArrayList<>();

        for (int i : gymNameID) {
            gymArrayList.add(getGymName(i));
        }

        return gymArrayList;
    }

    @Override
    public AGym getGymName(int id) {

        for (AGym c: gymMassive.GymList){

            if (c.getId() == id) {

                return c;
            }
        }

        return null;
    }





}