package com.digiboy.platform.auth.client;

import com.digiboy.platform.auth.generated.v1.api.DefaultApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "users", path = "/api/v1")
public interface UsersApiClient extends DefaultApi {

}
