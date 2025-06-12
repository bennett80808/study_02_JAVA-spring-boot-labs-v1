package com.example.ch2labs.labs01;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@org.springframework.stereotype.Controller
public class Controller {
    @GetMapping("/calc")
    @ResponseBody
    public String calc(@RequestParam int x, @RequestParam int y, @RequestParam String op) {
        if(op.equals("add")){
            return x + " + " + y + " = " + (x+y);
        }
        else if(op.equals("sub")){
            return x + " - " + y + " = " + (x-y);
        }
        else if(op.equals("mul")){
            return x + " * " + y + " = " + (x*y);
        }
        else if(op.equals("div")){
            return x + " / " + y + " = " + (x/y);
        }
        return "Error";
    }
}
