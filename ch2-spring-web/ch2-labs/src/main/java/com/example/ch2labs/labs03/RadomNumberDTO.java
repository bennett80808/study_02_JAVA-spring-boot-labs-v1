package com.example.ch2labs.labs03;

public class RadomNumberDTO {
    private int num;

    public RadomNumberDTO(int num) {
        this.num = num;
    }

    public int getRandomNumber() {
        return num;
    }

    public void setRandomNumber(int num) {
        this.num = num;
    }
}
