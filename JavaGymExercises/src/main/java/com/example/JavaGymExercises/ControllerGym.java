package com.example.JavaGymExercises;

import com.example.JavaGymExercises.model.AGym;
import com.example.JavaGymExercises.service.GymIntSer;
import com.example.JavaGymExercises.source.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RefreshScope
public class ControllerGym {

    public Integer user_id = null;

    @Autowired
    public GymIntSer courseService;
    @Autowired
    public Sender send;
    @GetMapping("/gym/{id}")
    public ResponseEntity<String> getCourse(@PathVariable int id) {
        return new ResponseEntity<String>(courseService.getGymName(id).toString(),null,HttpStatus.OK);
    }

    @GetMapping("/gym")

    public ResponseEntity<String> getAllGymName() {
        List<AGym> gymName = courseService.getAllGymName();
        StringBuilder stringBd = new StringBuilder();

        return new ResponseEntity<>(stringBd.toString(), HttpStatus.OK);
    }

    @PostMapping("/gym/{id}/membership")
    public ResponseEntity<String> membership(@PathVariable int id) {

        if (user_id == null) return new ResponseEntity<>("You're not a member of a gym", HttpStatus.OK);

        if (courseService.getGymName(id) == null) return new ResponseEntity<>("There are no gyms like this", HttpStatus.OK);
        send.ask_to_sub(user_id, id);

        return new ResponseEntity<>("Wait, please! You will be a member of the gym as soon as possible!", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam("user_id") int user_id, @RequestParam("code") int code){
        String result = "";

        if (send.login(user_id,code)) {
            result = "You are in!";
            this.user_id = user_id;
        }
        else {
            result = "Something goes wrong!:(";
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/memberships")
    public ResponseEntity<String> memberships(){

        if (user_id == null) return new ResponseEntity<>("There is no account like this", HttpStatus.OK);
        List<Integer> subscriptions = send.get_subscriptions(user_id);
        List<AGym> courses =  courseService.getGymName(subscriptions);
        StringBuilder stringBd = new StringBuilder();


        return new ResponseEntity<>(stringBd.toString(), HttpStatus.OK);
    }
}
