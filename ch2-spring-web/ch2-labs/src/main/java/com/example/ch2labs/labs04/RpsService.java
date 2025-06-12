package com.example.ch2labs.labs04;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RpsService {
    private static final String[] CHOICES = {"rock", "paper", "scissors"};
    private final Random random = new Random();

    public String getServerChoice(){
        return CHOICES[random.nextInt(3)];
    }

    public String determineResult(String user, String server){
        if(user.equals(server)){ return "Draw!";
        }else if (user.equals("rock")) {
            return server.equals("scissors") ? "You Win!" : "You Lose!";
        } else if (user.equals("scissors")) {
            return server.equals("paper") ? "You Win!" : "You Lose!";
        } else if (user.equals("paper")) {
            return server.equals("rock") ? "You Win!" : "You Lose!";
        }
        return "Error";
    }
}
