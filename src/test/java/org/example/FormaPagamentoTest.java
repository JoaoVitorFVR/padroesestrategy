package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FormaPagamentoTest {

    @Test
    void deveCalcularPixComDescontoDeCincoPorCento() {
        FormaPagamento forma = FormaPagamentoFactory.obterForma("Pix");
        assertEquals(95.0f, forma.calcular(100.0f));
        assertEquals("Pix", forma.getNome());
    }

    @Test
    void deveCalcularDinheiroSemAlteracao() {
        FormaPagamento forma = FormaPagamentoFactory.obterForma("Dinheiro");
        assertEquals(100.0f, forma.calcular(100.0f));
        assertEquals("Dinheiro", forma.getNome());
    }

    @Test
    void deveCalcularCartaoComTaxaDeTresPorCento() {
        FormaPagamento forma = FormaPagamentoFactory.obterForma("Cartao");
        assertEquals(103.0f, forma.calcular(100.0f));
        assertEquals("Cartão", forma.getNome());
    }
}
