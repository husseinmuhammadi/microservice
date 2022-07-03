package com.digiboy.platform.email.resources;

import com.digiboy.platform.email.generated.v1.api.EmailsApi;
import com.digiboy.platform.email.generated.v1.model.Email;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailsResource implements EmailsApi {



    @Override
    public ResponseEntity<Void> sendEmail(String xAuthUser, Email email) {
        return EmailsApi.super.sendEmail(xAuthUser, email);
    }
}
