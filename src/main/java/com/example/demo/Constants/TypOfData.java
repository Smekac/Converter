package com.example.demo.Constants;

public enum TypOfData {
    NUMERIC(0),
    BOOLEAN(1),
    STRING(2),
    FORMULA(3);

    private int value;

    TypOfData(int value) {
        this.value = value;
    }

    public void exit ( )
    {
        System . exit ( value ) ;
    }

}
