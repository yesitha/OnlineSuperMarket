package com.architects.notificationService.services;



import com.infobip.ApiClient;
import com.infobip.ApiException;
import com.infobip.ApiKey;
import com.infobip.BaseUrl;
import com.infobip.api.EmailApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class EmailService implements notificationServiceInterface {

    @Value("${infobipemail.api.key}")  // Update the property key for email API key
    private String apiKey;

    @Value("${infobipemail.base.url}")  //  base URL
    private String baseUrl;

    @Value("${infobip.email.sender.address}")
    private String emailSenderAddress;

    public void sendEmail( String body,String to, String subject) {
        // Create the API client and the Email API instances.
        var apiClient = ApiClient.forApiKey(ApiKey.from(apiKey))
                .withBaseUrl(BaseUrl.from(baseUrl))
                .build();
        var emailApi = new EmailApi(apiClient);

        try {
            // Create the email and send it.
            var recipients = new ArrayList<>(List.of(to));
            var emailResponse = emailApi
                    .sendEmail(recipients)
                    .from(emailSenderAddress)
                    .subject(subject)
                    .text(body)
                    .execute();

            System.out.println("Email Response body: " + emailResponse);

            // Get delivery reports. It may take a few seconds to show the above-sent email.
            var reportsResponse = emailApi.getEmailDeliveryReports().execute();
            System.out.println(reportsResponse.getResults());
        } catch (ApiException e) {
            System.out.println("Email HTTP status code: " + e.responseStatusCode());
            System.out.println("Email Response body: " + e.rawResponseBody());
            // Handle the exception or log the error.
        }
    }

    @Override
    public void sendNotification(String message, String recipient, String subject) {
        sendEmail(message,recipient, subject);
    }
}
