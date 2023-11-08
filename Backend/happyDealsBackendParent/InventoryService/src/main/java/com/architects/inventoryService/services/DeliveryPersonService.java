package com.architects.inventoryService.services;


import com.architects.inventoryService.dto.request.requestDeliveryPersonDto;
import com.architects.inventoryService.dto.response.responseDeliveryPersonDto;

import java.util.List;


public interface DeliveryPersonService {


    public List<responseDeliveryPersonDto> getAllDeliveryPersons();

    public List<responseDeliveryPersonDto> getAvailableDeliveryPersons();

    public responseDeliveryPersonDto getDeliveryPersonById(Long id);

    public void updateDeliveryPerson(Long id, requestDeliveryPersonDto updatedDeliveryPerson);

    public void deleteDeliveryPerson(Long id);

    public void createDeliveryPerson(requestDeliveryPersonDto deliveryPerson);
}
