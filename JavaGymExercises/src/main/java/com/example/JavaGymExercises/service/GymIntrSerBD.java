package com.example.JavaGymExercises.service;

import com.example.JavaGymExercises.model.AGym;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.List;

public class GymIntrSerBD implements GymIntSer {
    @Autowired
    MongoTemplate mT;
    @Override
    public List<AGym> getAllGymName() {
        return mT.findAll(AGym.class);
    }

    @Override
    public List<AGym> getGymName(List<Integer> GymNameID) {
        List<AGym> gymArrayList = new ArrayList<>();

        for (Integer i: GymNameID){
            gymArrayList.add(getGymName(i));
        }

        return gymArrayList;
    }

    @Override
    public AGym getGymName(int id) {
        AGym gymMs = mT.query(AGym.class).matching(Query.query(Criteria.where("id").is(id))).oneValue();

        return gymMs;
    }
}
