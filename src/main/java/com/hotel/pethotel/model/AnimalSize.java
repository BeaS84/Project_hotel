package com.hotel.pethotel.model;

public enum AnimalSize {
    SMALL("MAŁY (Pies <= 10kg | Kot <= 3kg)"),
    MEDIUM("ŚREDNI(Pies 11 - 25kg | Kot 4 - 6kg)"),
    LARGE ("DUŻY(Pies > 25kg | Kot > 6 kg)");

    public final String label;
    private AnimalSize(String label){
        this.label = label;
    }
}
