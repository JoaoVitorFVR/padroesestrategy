package org.example;

public class Caixa {

    private Caixa() {};
    private static Caixa instance = new Caixa();
    public static Caixa getInstance() {
        return instance;
    }

    private float total;
    private int quantidade;

    public void registrar(float valor) {
        this.total += valor;
        this.quantidade++;
    }

    public float getTotal() {
        return total;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void limpar() {
        this.total = 0.0f;
        this.quantidade = 0;
    }
}
