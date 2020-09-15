package com.example.trabalho;

import java.util.ArrayList;
import java.util.List;

public class Bolsafamilia {
    private List<String> bolsa;


    public Bolsafamilia (){
        this.bolsa = new ArrayList<>();
    }

    public List<String> getBolsa() {
        return bolsa;
    }

    public void setBolsa(List<String> bolsa) {
        this.bolsa = bolsa;
    }

    public Bolsafamilia(List<String> bolsa){
        this.bolsa = bolsa;
    }
    public void addBolsa(String bolsa){
        this.bolsa.add(bolsa);
    }
    @Override
    public String toString() {
            return "Resultado:\n" + bolsa + "\n---------------\n";
    }
    public String string(){
        return "\n=====================\n";
    }
}
