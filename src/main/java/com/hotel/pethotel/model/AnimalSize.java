package com.hotel.pethotel.model;

public enum AnimalSize {
    SMALL("mały"),
    MEDIUM("średni"),
    LARGE ("duży");

    public final String label;
    private AnimalSize(String label){
        this.label = label;
    }
}
