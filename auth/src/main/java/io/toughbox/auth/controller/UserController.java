package io.toughbox.auth.controller;

import io.toughbox.auth.controller.request.EncryptedUserRequestBody;
import io.toughbox.auth.domain.User;
import io.toughbox.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/api/v1/users")
    public User createuser(@RequestBody EncryptedUserRequestBody requestBody) {
        return userService.createUser(requestBody.getUserId(), requestBody.getPassword());
    }
}
