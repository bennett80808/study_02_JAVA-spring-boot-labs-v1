package com.example.ch2labs.labs05;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/labs5")
public class DigitSumController {

    @GetMapping("/sum-digits")
    public ResponseEntity<?> sumDigits(@RequestParam String number) {

        if(number==null || number.length()==0){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "number 파라미터는 필수입니다.",
                            "status", 400));
        }

        if (number.startsWith("-")) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "음수는 지원하지 않습니다. 양의 정수를 입력해주세요.",
                            "status", 400));
        }

        if (!number.matches("\\d+")) {
            return ResponseEntity
                    .status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(Map.of("error", "정수만 입력 가능합니다. 예: /sum-digits?number=1234",
                            "status", 422));
        }

        int sum = 0;
        for (char ch : number.toCharArray()) {
            sum += Character.getNumericValue(ch);
        }
        return ResponseEntity
                .ok(Map.of("message", "각 자리수 합: " + sum));
    }
}
