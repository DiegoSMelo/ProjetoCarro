package com.example.diegomelo.projetocarro.model;

import java.io.Serializable;


public class Carro implements Serializable{

    public String modelo;
    public String fabricante;
    public String ano;
    public String motor;

    public Carro(String modelo, String fabricante, String ano, String motor) {
        this.modelo = modelo;
        this.fabricante = fabricante;
        this.ano = ano;
        this.motor = motor;
    }

    @Override
    public String toString() {
        return this.modelo + " (" + this.fabricante + ") - " + this.motor + " - " + this.ano;
    }
}
