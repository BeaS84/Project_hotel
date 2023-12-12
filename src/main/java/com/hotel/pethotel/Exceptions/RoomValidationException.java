package com.hotel.pethotel.Exceptions;

public class RoomValidationException extends RuntimeException {
    public RoomValidationException(String message) {
        super(message);
    }
}
