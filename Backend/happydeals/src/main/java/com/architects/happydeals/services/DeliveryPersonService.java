package com.architects.happydeals.services;

import com.architects.happydeals.Repositories.DeliveryPersonRepository;
import com.architects.happydeals.entity.DeliveryPerson;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DeliveryPersonService {

    private final DeliveryPersonRepository deliveryPersonRepository;

    @Autowired
    public DeliveryPersonService(DeliveryPersonRepository deliveryPersonRepository) {
        this.deliveryPersonRepository = deliveryPersonRepository;
    }

    public List<DeliveryPerson> getAllDeliveryPersons() {
        return deliveryPersonRepository.findAll();
    }

    public List<DeliveryPerson> getAvailableDeliveryPersons() {
        return getAllDeliveryPersons().stream()
                .filter(DeliveryPerson::isAvailable)
                .collect(Collectors.toList());
    }

    public DeliveryPerson getDeliveryPersonById(Long id) {
        Optional<DeliveryPerson> deliveryPersonOptional = deliveryPersonRepository.findById(id);
        if (deliveryPersonOptional.isPresent()) {
            return deliveryPersonOptional.get();
        } else {
            throw new EntityNotFoundException("DeliveryPerson not found with ID: " + id);
        }
    }

    public void updateDeliveryPerson(Long id,DeliveryPerson updatedDeliveryPerson) {
        // Add validation or specific update logic if needed
        DeliveryPerson deliveryPerson = getDeliveryPersonById(id);
        deliveryPerson.setAvailable(updatedDeliveryPerson.isAvailable());
        deliveryPerson.setDeliveryPersonName(updatedDeliveryPerson.getDeliveryPersonName());

        deliveryPersonRepository.save(deliveryPerson);

    }

    public void deleteDeliveryPerson(Long id) {
        deliveryPersonRepository.deleteById(id);
    }

    public void createDeliveryPerson(DeliveryPerson deliveryPerson) {
        deliveryPersonRepository.save(deliveryPerson);
    }

}
