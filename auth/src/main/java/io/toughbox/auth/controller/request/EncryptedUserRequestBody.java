package io.toughbox.auth.controller.request;

import io.toughbox.auth.annotation.PasswordEncryption;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.beans.ConstructorProperties;

@Getter
public class EncryptedUserRequestBody {

    private final String userId;

    @PasswordEncryption
    private String password;

    /*
    @ConstructorProperties({"userId", "password"})는
    생성자의 파라미터가 각각 어떤 프로퍼티에 매핑되는지 명확히 지정해주는 어노테이션으로,
    직렬화/역직렬화, 불변 객체 설계, 프레임워크 바인딩 등에서
    객체 생성의 안정성과 명확성을 높여줍니다.
     */
    @ConstructorProperties({"userId", "password"})
    public EncryptedUserRequestBody(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
}
