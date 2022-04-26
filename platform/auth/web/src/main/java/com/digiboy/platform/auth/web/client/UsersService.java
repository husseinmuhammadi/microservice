package com.digiboy.platform.auth.web.client;

import com.digiboy.platform.auth.generated.v1.api.DefaultApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "users", path = "/api/v1")
public interface UsersService extends DefaultApi {

}
