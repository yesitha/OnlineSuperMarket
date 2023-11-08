package com.architects.deliveryService.controllers;

import com.architects.deliveryService.dto.request.requestDeliveryPersonDto;
import com.architects.deliveryService.dto.response.responseDeliveryPersonDto;
import com.architects.deliveryService.services.DeliveryPersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/deliveryPersons")
public class DeliveryPersonController {


    private final DeliveryPersonServiceImpl deliveryPersonService;


    @Autowired
    public DeliveryPersonController(DeliveryPersonServiceImpl deliveryPersonService) {
        this.deliveryPersonService = deliveryPersonService;
    }

    @GetMapping("/get-available-delivery-person-list")
    public List<responseDeliveryPersonDto> getAvailableDeliveryPersonList() {
        return deliveryPersonService.getAvailableDeliveryPersons();
    }

    @PutMapping("/update-delivery-person-status/{id}/{status}")
    public void updateDeliveryPersonStatus(@PathVariable Long id, @PathVariable Boolean status) {
        deliveryPersonService.updateDeliveryPersonStatus(id, status);

    }

    @GetMapping("/all")
    public List<responseDeliveryPersonDto> getAllDeliveryPersons() {
        return deliveryPersonService.getAllDeliveryPersons();
    }

    @GetMapping("/{id}")
    public responseDeliveryPersonDto getDeliveryPersonById(@PathVariable Long id) {
        return deliveryPersonService.getDeliveryPersonById(id);
    }

    @PostMapping("/create")
    public void createDeliveryPerson(@RequestBody requestDeliveryPersonDto deliveryPerson) {
        deliveryPersonService.createDeliveryPerson(deliveryPerson);
    }

    @PutMapping("/update/{id}")
    public void updateDeliveryPerson(@PathVariable Long id, @RequestBody requestDeliveryPersonDto updatedDeliveryPerson) {
        deliveryPersonService.updateDeliveryPerson(id, updatedDeliveryPerson);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteDeliveryPerson(@PathVariable Long id) {
        deliveryPersonService.deleteDeliveryPerson(id);
    }
}
