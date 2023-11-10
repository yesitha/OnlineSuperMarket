package com.architects.notificationService.services;


import com.infobip.ApiClient;
import com.infobip.ApiException;
import com.infobip.ApiKey;
import com.infobip.BaseUrl;
import com.infobip.api.SmsApi;
import com.infobip.model.SmsAdvancedTextualRequest;
import com.infobip.model.SmsDestination;
import com.infobip.model.SmsTextualMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class SmsService {

    @Value("${infobip.api.key}")  //API key
    private String apiKey;

    @Value("${infobip.base.url}")  //  base URL
    private String baseUrl;

    @Value("${infobip.recipient.number}")  //  recipient phone number
    private String recipientNumber;

    public void sendSms(String recipient, String message) {
        // Creating the API client and the Send SMS API instances.
        var apiClient = ApiClient.forApiKey(ApiKey.from(apiKey))
                .withBaseUrl(BaseUrl.from(baseUrl))
                .build();
        var sendSmsApi = new SmsApi(apiClient);

        // Create a message to send.
        var smsMessage = new SmsTextualMessage()
                .addDestinationsItem(new SmsDestination().to(recipient))
                .text(message);

        // Create a send message request.
        var smsMessageRequest = new SmsAdvancedTextualRequest()
                .messages(Collections.singletonList(smsMessage));

        try {
            // Send the message.
            var smsResponse = sendSmsApi.sendSmsMessage(smsMessageRequest).execute();
            System.out.println("SMS Response body: " + smsResponse);


        } catch (ApiException e) {
            System.out.println("SMS HTTP status code: " + e.responseStatusCode());
            System.out.println("SMS Response body: " + e.rawResponseBody());

        }
    }

}
