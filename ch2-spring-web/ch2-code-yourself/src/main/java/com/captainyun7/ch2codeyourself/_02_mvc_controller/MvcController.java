package com.captainyun7.ch2codeyourself._02_mvc_controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mvc")
public class MvcController {

    // /mvc/basic/view
    @GetMapping("/basic/view")
    public String basicView() {
        return "basic-view"; // view resolver
    }
    
    // mvc 패턴 : Model View Controller
    // 유지보수, 역할 분리
    @GetMapping("/model")
    public String model(Model model) {
        model.addAttribute("msg", "Hello World");
        model.addAttribute("currentTime", java.time.LocalDateTime.now());
        return "model-view";
    }
}
