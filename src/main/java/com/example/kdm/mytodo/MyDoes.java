package com.example.kdm.mytodo;

public class MyDoes {

    String titleDoes;
    String dateDoes;
    String descDoes;
    String keyDoes;

    public MyDoes() {
    }

    public MyDoes(String titleDoes, String dateDoes, String descDoes, String keyDoes) {
        this.titleDoes = titleDoes;
        this.dateDoes = dateDoes;
        this.descDoes = descDoes;
        this.keyDoes = keyDoes;
    }

    public String getKeyDoes() {
        return keyDoes;
    }

    public void setKeyDoes(String keyDoes) {
        this.keyDoes = keyDoes;
    }

    public String getTitleDoes() {
        return titleDoes;
    }

    public void setTitleDoes(String titleDoes) {
        this.titleDoes = titleDoes;
    }

    public String getDateDoes() {
        return dateDoes;
    }

    public void setDateDoes(String dateDoes) {
        this.dateDoes = dateDoes;
    }

    public String getDescDoes() {
        return descDoes;
    }

    public void setDescDoes(String descDoes) {
        this.descDoes = descDoes;
    }
}
