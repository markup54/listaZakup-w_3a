package com.example.listazakupowmodyfikowalna;

public class Produkt {
    private String nazwa;
    private boolean zaznaczony;

    public Produkt(String nazwa) {
        this.nazwa = nazwa;
        this.zaznaczony = false;
    }

    @Override
    public String toString() {
        return nazwa;
    }

    public void setZaznaczony(boolean zaznaczony) {
        this.zaznaczony = zaznaczony;
    }

    public String getNazwa() {
        return nazwa;
    }

    public boolean isZaznaczony() {
        return zaznaczony;
    }
}
