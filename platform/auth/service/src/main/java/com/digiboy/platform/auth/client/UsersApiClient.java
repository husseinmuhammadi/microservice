package com.digiboy.platform.auth.client;

import com.digiboy.platform.auth.generated.v1.api.DefaultApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Profile;

// todo: move this class to packaage com.digiboy.platform.user.client
@FeignClient(name = "${client.services.users-api.name}", path = "/api/v1")
public interface UsersApiClient extends DefaultApi {
}
