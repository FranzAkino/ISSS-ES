package com.springapp.mvc;

/**
 * Created by akino on 06-14-15.
 */
public enum TipoGraficos {
    MAYOR_ELECTIVA(1),
    MAYOR_EMERGENCIA(9),
    MENOR_ELECTIVA(2),
    MENOR_EMERGENCIA(3),
    SUSPENDIDAS(4),
    ANUALES(6),
    QUIROFANO(7),
    ANUALES_ESPECIALIDAD(8);

    private final int num;
    TipoGraficos(int num) { this.num = num; }
    public int getValue() { return num; }

    public static TipoGraficos getValue(int i){
        for(TipoGraficos t: values()){
            if(t.getValue()==i)
                return t;
        }
        return ANUALES;
    }
}
