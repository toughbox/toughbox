package io.toughbox.auth.exception;

public class InvalidAuthException extends RuntimeException {
    public InvalidAuthException() {
        super("Invalid id or password!");
    }
}
