package com.example.diegomelo.projetocarro.model;

import java.io.Serializable;


public class Carro implements Serializable{

    public String modelo;
    public String fabricante;
    public String ano;
    public String motor;
    public String imagem;



    @Override
    public String toString() {
        return this.modelo + " (" + this.fabricante + ") - " + this.motor + " - " + this.ano;
    }
}
