package com.example.ch2labs.labs02;

import org.springframework.stereotype.Service;
import java.util.Random;

@Service
public class DiceService {
    private final Random random = new Random();

    public int rollDice() {
        return random.nextInt(6) + 1;  // 1~6
    }
}

