package com.example.ch2labs.labs02;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DiceController {

    private final DiceService diceService;

    public DiceController(DiceService diceService) {
        this.diceService = diceService;
    }

    @GetMapping("/dice")
    public DiceDTO rollDice() {
        int diceValue = diceService.rollDice();
        return new DiceDTO(diceValue);
    }
}

