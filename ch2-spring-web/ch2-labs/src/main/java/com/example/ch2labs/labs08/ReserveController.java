package com.example.ch2labs.labs08;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reserve")
@RequiredArgsConstructor
public class ReserveController {

    private final ReserveService reserveService;;

    @PostMapping
    public ResponseEntity<SuccessResponseDTO> createModel(@RequestBody @Valid RequestDTO requestDTO) {
        return ResponseEntity.ok(reserveService.createModel(requestDTO));
    }
}
