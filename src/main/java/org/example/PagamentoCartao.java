package org.example;

public class PagamentoCartao implements FormaPagamento {

    public float calcular(float valor) {
        return valor + valor * 3.0f / 100;
    }

    public String getNome() {
        return "Cartão";
    }
}
