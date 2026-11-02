package org.example;

public class AtendimentoDelivery extends Atendimento {

    protected float calcularTaxa() {
        return 8.0f;
    }

    @Override
    protected String getTipo() {
        return "AtendimentoDelivery";
    }
}
