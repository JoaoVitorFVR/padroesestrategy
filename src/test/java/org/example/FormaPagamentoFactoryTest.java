package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FormaPagamentoFactoryTest {

    @Test
    void deveObterPagamentoPix() {
        FormaPagamento forma = FormaPagamentoFactory.obterForma("Pix");
        assertTrue(forma instanceof PagamentoPix);
    }

    @Test
    void deveObterPagamentoDinheiro() {
        FormaPagamento forma = FormaPagamentoFactory.obterForma("Dinheiro");
        assertTrue(forma instanceof PagamentoDinheiro);
    }

    @Test
    void deveObterPagamentoCartao() {
        FormaPagamento forma = FormaPagamentoFactory.obterForma("Cartao");
        assertTrue(forma instanceof PagamentoCartao);
    }

    @Test
    void deveRetornarExcecaoParaFormaInexistente() {
        try {
            FormaPagamento forma = FormaPagamentoFactory.obterForma("Bitcoin");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Forma de pagamento inexistente", e.getMessage());
        }
    }

    @Test
    void deveRetornarExcecaoParaFormaInvalida() {
        try {
            FormaPagamento forma = FormaPagamentoFactory.obterForma("Cheque");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Forma de pagamento inválida", e.getMessage());
        }
    }
}
