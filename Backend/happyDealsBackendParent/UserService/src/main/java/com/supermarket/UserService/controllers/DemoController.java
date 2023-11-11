package com.supermarket.UserService.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test/demo")
public class DemoController {

    @GetMapping("/admin")
    @PreAuthorize("hasRole('admin')")
    public String test1() {
        return "Test to User role Management-Admin";
    }

    @GetMapping("/customer")
    @PreAuthorize("hasRole('customer')")
    public String test2() {
        return "Test to User role Management- Customer";
    }

    @GetMapping("/delivery")
    @PreAuthorize("hasRole('delivery_person')")
    public String test3() {
        return "Test to User role Management-Delivery Person";
    }

    @GetMapping("/inventory")
    @PreAuthorize("hasRole('inventory_keeper')")
    public String test4() {
        return "Test to User role Management- Inventory Keeper";
    }



}

