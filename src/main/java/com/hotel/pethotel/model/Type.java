package com.hotel.pethotel.model;

public enum Type {
    DOG("Pies"), CAT("Kot");

    public final String label;
    private Type(String label) {
        this.label = label;
    }
}

