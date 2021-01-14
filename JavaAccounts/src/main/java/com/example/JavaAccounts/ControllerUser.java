package com.example.JavaAccounts;
import com.example.JavaAccounts.model.account;
import com.example.JavaAccounts.service.AccountIntrSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RefreshScope
public class ControllerUser {

    @Autowired
    public AccountIntrSer accountIntrSer;


    @GetMapping("/username/{id}")
    public ResponseEntity<String> getUsername(@PathVariable int id) {
        account username = accountIntrSer.getAccount(id);

        if (username == null) return new ResponseEntity<String>("No user like this", HttpStatus.OK);
        return new ResponseEntity<String>(username.toString(), HttpStatus.OK);
    }


    @PostMapping("/GetMembership")
    public ResponseEntity<String> membership(@RequestParam("usernameId") int userId, @RequestParam("GymId") int gymId ) {
        accountIntrSer.addMembership(userId, gymId);

        return new ResponseEntity<>("UID: "+userId+" GymID: "+gymId, HttpStatus.OK);
    }

    @PostMapping("/GetUnmember")
    public ResponseEntity<String> unmembership(@RequestParam("userId") int userId, @RequestParam("GymId") int gymId ) {
        accountIntrSer.removeMembership(userId, gymId);

        return new ResponseEntity<>("UID: "+userId+" GymID: "+gymId, HttpStatus.OK);
    }




}

