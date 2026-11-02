package org.example;

public class PagamentoDinheiro implements FormaPagamento {

    public float calcular(float valor) {
        return valor;
    }

    public String getNome() {
        return "Dinheiro";
    }
}
