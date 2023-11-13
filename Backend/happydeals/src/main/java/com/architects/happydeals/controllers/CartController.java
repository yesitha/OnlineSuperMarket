package com.architects.happydeals.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/MainCart")
@CrossOrigin

public class CartController {
    @GetMapping("/getMainCart")
    public String getMainCart(){
        return "MainCart";
    }
    @PostMapping("/saveMainCart")
    public String saveMainCart(){
        return "MainCart Saved";
    }
    @PutMapping("/updateMainCart")
    public String updateMainCart(){
        return "MainCart Updated";
    }
    @DeleteMapping("/deleteMainCart")
    public String deleteMainCart(){
        return "MainCart Deleted";
    }
}
