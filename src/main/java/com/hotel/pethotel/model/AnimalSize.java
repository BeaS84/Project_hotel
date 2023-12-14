package com.hotel.pethotel.model;

public enum AnimalSize {
    SMALL("MAŁE: pies do 10 kg / kot do 3 kg"),
    MEDIUM("ŚREDNIE: pies od 11 do 25 kg / kot od 4 do 6 kg"),
    LARGE ("DUŻE: pies powyżej 25 kg / kot powyżej 6 kg");

    public final String label;
    private AnimalSize(String label){
        this.label = label;
    }
}
