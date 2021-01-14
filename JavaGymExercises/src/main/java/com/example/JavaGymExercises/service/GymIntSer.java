package com.example.JavaGymExercises.service;

import com.example.JavaGymExercises.model.AGym;

import java.util.List;

public interface GymIntSer {

    List<AGym> getAllGymName();

    List<AGym> getGymName(List<Integer> GymNameID);

    AGym getGymName(int id);
}
