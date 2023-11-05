package com.architects.happydeals.controllers;

import com.architects.happydeals.entity.DeliveryPerson;
import com.architects.happydeals.services.DeliveryPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/deliveryPersons")
public class DeliveryPersonController {

    private final DeliveryPersonService deliveryPersonService;
    @Autowired
    public DeliveryPersonController(DeliveryPersonService deliveryPersonService) {
        this.deliveryPersonService = deliveryPersonService;
    }
    @GetMapping("/get-available-delivery-person-list")
    public List<DeliveryPerson> getAvailableDeliveryPersonList() {
        return deliveryPersonService.getAvailableDeliveryPersons();
    }
    @PutMapping("/update-delivery-person-status/{id}")
    public void updateDeliveryPersonStatus(@PathVariable Long id, Boolean status) {
        DeliveryPerson deliveryPerson = deliveryPersonService.getDeliveryPersonById(id);
        deliveryPerson.setAvailable(status);
        deliveryPersonService.updateDeliveryPerson(id, deliveryPerson);

    }

    @GetMapping("/all")
    public List<DeliveryPerson> getAllDeliveryPersons() {
        return deliveryPersonService.getAllDeliveryPersons();
    }

    @GetMapping("/{id}")
    public DeliveryPerson getDeliveryPersonById(@PathVariable Long id) {
        return deliveryPersonService.getDeliveryPersonById(id);
    }

    @PostMapping("/create")
    public void createDeliveryPerson(@RequestBody DeliveryPerson deliveryPerson) {
        deliveryPersonService.createDeliveryPerson(deliveryPerson);
    }

    @PutMapping("/update/{id}")
    public void updateDeliveryPerson(@PathVariable Long id, @RequestBody DeliveryPerson updatedDeliveryPerson) {
        deliveryPersonService.updateDeliveryPerson(id, updatedDeliveryPerson);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteDeliveryPerson(@PathVariable Long id) {
        deliveryPersonService.deleteDeliveryPerson(id);
    }
}
