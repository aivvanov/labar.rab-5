package com.example.JavaGymExercises.repository;

import com.example.JavaGymExercises.model.AGym;

import java.util.List;

public interface RepositoryInterface {

    List<AGym> read();

    void write(List<AGym> accounts);
}
