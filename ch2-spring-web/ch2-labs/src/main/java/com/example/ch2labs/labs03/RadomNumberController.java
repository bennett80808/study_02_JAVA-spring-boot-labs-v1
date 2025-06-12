package com.example.ch2labs.labs03;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RadomNumberController {

    private final RadomNumberService radomNumberService;

    public RadomNumberController(RadomNumberService radomNumberService) {
        this.radomNumberService = radomNumberService;
    }

    @GetMapping("/random")
    public RadomNumberDTO rollNum(@RequestParam int min, @RequestParam int max) {
        int value = radomNumberService.generateRandomNumber(min, max);
        return new RadomNumberDTO(value);
    }
}

