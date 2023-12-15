package com.hotel.pethotel.model;

public enum AnimalSize {
    SMALL("Mały (Pies <= 10kg | Kot <= 3kg)"),
    MEDIUM("Średni (Pies 11 - 25kg | Kot 4 - 6kg)"),
    LARGE ("Duży (Pies > 25kg | Kot > 6 kg)");

    public final String label;
    private AnimalSize(String label){
        this.label = label;
    }
}
