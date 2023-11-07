package com.architects.happydeals.services;


import com.architects.happydeals.dto.request.requestDeliveryPersonDto;
import com.architects.happydeals.dto.response.responseDeliveryPersonDto;

import java.util.List;


public interface DeliveryPersonService {


    public List<responseDeliveryPersonDto> getAllDeliveryPersons();

    public List<responseDeliveryPersonDto> getAvailableDeliveryPersons();

    public responseDeliveryPersonDto getDeliveryPersonById(Long id);

    public void updateDeliveryPerson(Long id, requestDeliveryPersonDto updatedDeliveryPerson);

    public void deleteDeliveryPerson(Long id);

    public void createDeliveryPerson(requestDeliveryPersonDto deliveryPerson);
}
