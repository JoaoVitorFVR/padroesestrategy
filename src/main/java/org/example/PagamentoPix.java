package org.example;

public class PagamentoPix implements FormaPagamento {

    public float calcular(float valor) {
        return valor - valor * 5.0f / 100;
    }

    public String getNome() {
        return "Pix";
    }
}
