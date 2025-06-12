package com.example.ch2labs.labs03;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RadomNumberService {
    private final Random random = new Random();

    public int generateRandomNumber(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("min은 max보다 작거나 같아야 합니다.");
        }
        return random.nextInt(max - min + 1) + min;  // 1~6

    }
}

