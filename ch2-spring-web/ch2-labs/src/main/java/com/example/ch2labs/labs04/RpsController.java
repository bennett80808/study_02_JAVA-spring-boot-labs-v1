package com.example.ch2labs.labs04;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RpsController {

    private final RpsService rpsService;

    public RpsController(RpsService rpsService) {
        this.rpsService = rpsService;
    }

    @GetMapping("/rps")
    public RpsDTO play(@RequestParam String user){
        String server = rpsService.getServerChoice();
        String result = rpsService.determineResult(user.toLowerCase(), server);
        return new RpsDTO(user.toLowerCase(), server, result);
    }
}
