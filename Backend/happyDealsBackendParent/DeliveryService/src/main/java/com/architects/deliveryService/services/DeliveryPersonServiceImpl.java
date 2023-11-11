package com.architects.deliveryService.services;

import com.architects.deliveryService.Repositories.DeliveryPersonRepository;
import com.architects.deliveryService.dto.request.requestDeliveryPersonDto;
import com.architects.deliveryService.dto.response.responseDeliveryPersonDto;
import com.architects.deliveryService.entity.DeliveryPerson;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DeliveryPersonServiceImpl implements DeliveryPersonService {
    private final DeliveryPersonRepository deliveryPersonRepository;
    private final WebClient.Builder webClientBuilder;

    @Autowired
    public DeliveryPersonServiceImpl(DeliveryPersonRepository deliveryPersonRepository, WebClient.Builder webClientBuilder) {
        this.deliveryPersonRepository = deliveryPersonRepository;

        this.webClientBuilder = webClientBuilder;
    }


    public List<responseDeliveryPersonDto> getAllDeliveryPersons() {
        return deliveryPersonRepository.findAll().stream()
                .map(dp -> new responseDeliveryPersonDto(
                        dp.getDeliveryPersonId(),
                        dp.getDeliveryPersonName(),
                        dp.getDeliveryPersonPhoneNumber(),
                        dp.getDeliveryPersonEmail()
                ))
                .collect(Collectors.toList());
    }

    public List<responseDeliveryPersonDto> getAvailableDeliveryPersons() {
        List<DeliveryPerson> dps = deliveryPersonRepository.findAll().stream()
                .filter(DeliveryPerson::isAvailable)
                .toList();


        return dps.stream()
                .map(dp -> new responseDeliveryPersonDto(
                        dp.getDeliveryPersonId(),
                        dp.getDeliveryPersonName(),
                        dp.getDeliveryPersonPhoneNumber(),
                        dp.getDeliveryPersonEmail()
                ))
                .collect(Collectors.toList());
    }


    public responseDeliveryPersonDto getDeliveryPersonById(Long id) {
        Optional<DeliveryPerson> deliveryPersonOptional = deliveryPersonRepository.findById(id);
        if (deliveryPersonOptional.isPresent()) {
            return new responseDeliveryPersonDto(
                    deliveryPersonOptional.get().getDeliveryPersonId(),
                    deliveryPersonOptional.get().getDeliveryPersonName(),
                    deliveryPersonOptional.get().getDeliveryPersonPhoneNumber(),
                    deliveryPersonOptional.get().getDeliveryPersonEmail()

            );
        } else {
            throw new EntityNotFoundException("DeliveryPerson not found with ID: " + id);
        }
    }

    public void updateDeliveryPerson(Long id, requestDeliveryPersonDto updatedDeliveryPerson) {
        // Add validation or specific update logic if needed
        DeliveryPerson deliveryPerson = deliveryPersonRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("DeliveryPerson not found with ID: " + id));
        if (deliveryPerson != null) {
            deliveryPerson.setDeliveryPersonName(updatedDeliveryPerson.getDeliveryPersonName());
            deliveryPerson.setDeliveryPersonPhoneNumber(updatedDeliveryPerson.getDeliveryPersonPhoneNumber());
            deliveryPerson.setDeliveryPersonEmail(updatedDeliveryPerson.getDeliveryPersonEmail());

            deliveryPersonRepository.save(deliveryPerson);
        }

    }

    public void deleteDeliveryPerson(Long id) {
        deliveryPersonRepository.deleteById(id);
    }

    public void updateDeliveryPersonStatus(Long id, Boolean status) {
        DeliveryPerson deliveryPerson = deliveryPersonRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("DeliveryPerson not found with ID: " + id));
        if (deliveryPerson != null) {

            deliveryPerson.setAvailable(status);
            deliveryPersonRepository.save(deliveryPerson);
            if (!status) {

                //notify delivery person code need to be added
            }
        }
    }

    public void createDeliveryPerson(requestDeliveryPersonDto deliveryPerson) {

        UUID uuid = UUID.randomUUID();
        Long deliveryPersonId = uuid.getMostSignificantBits() & Long.MAX_VALUE;


        DeliveryPerson dp = new DeliveryPerson(

                deliveryPersonId,
                deliveryPerson.getDeliveryPersonName(),
                deliveryPerson.getDeliveryPersonPhoneNumber(),
                deliveryPerson.getDeliveryPersonEmail(), null
                , true

        );
        deliveryPersonRepository.save(dp);
    }

}
