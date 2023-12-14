package com.hotel.pethotel.model;

public enum Sex {
    GIRL("Samica"), BOY("samiec");
    public final String label;
    private Sex(String label) {
        this.label = label;
    }
}
