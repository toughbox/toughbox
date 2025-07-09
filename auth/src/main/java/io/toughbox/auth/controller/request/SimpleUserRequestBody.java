package io.toughbox.auth.controller.request;

import lombok.Getter;

import java.beans.ConstructorProperties;

@Getter
public class SimpleUserRequestBody {

    private final String userId;
    private final String password;

    @ConstructorProperties({"userId", "password"})
    public SimpleUserRequestBody(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
}
