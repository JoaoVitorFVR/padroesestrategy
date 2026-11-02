package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AtendimentoTest {

    @BeforeEach
    void setUp() {
        Caixa.getInstance().limpar();
    }

    private AtendimentoBalcao balcao() {
        AtendimentoBalcao atendimento = new AtendimentoBalcao();
        atendimento.setNumero(1);
        atendimento.setCliente("Ana");
        return atendimento;
    }

    private AtendimentoDelivery delivery() {
        AtendimentoDelivery atendimento = new AtendimentoDelivery();
        atendimento.setNumero(2);
        atendimento.setCliente("Bruno");
        return atendimento;
    }


    @Test
    void devePagarNoBalcaoComPix() {
        String recibo = balcao().pagar(100.0f, "Pix");
        assertEquals("Atendimento{numero=1, cliente='Ana', pagamento='Pix', total=95.0}", recibo);
    }

    @Test
    void devePagarNoBalcaoComDinheiro() {
        String recibo = balcao().pagar(100.0f, "Dinheiro");
        assertEquals("Atendimento{numero=1, cliente='Ana', pagamento='Dinheiro', total=100.0}", recibo);
    }

    @Test
    void devePagarNoBalcaoComCartao() {
        String recibo = balcao().pagar(100.0f, "Cartao");
        assertEquals("Atendimento{numero=1, cliente='Ana', pagamento='Cartão', total=103.0}", recibo);
    }

    @Test
    void devePagarNoDeliveryComPixIncluindoTaxaDeEntrega() {
        // 92 + 8 (taxa) = 100 -> Pix = 95
        String recibo = delivery().pagar(92.0f, "Pix");
        assertEquals("AtendimentoDelivery{numero=2, cliente='Bruno', pagamento='Pix', total=95.0}", recibo);
    }

    @Test
    void devePagarNoDeliveryComCartaoIncluindoTaxaDeEntrega() {
        String recibo = delivery().pagar(92.0f, "Cartao");
        assertEquals("AtendimentoDelivery{numero=2, cliente='Bruno', pagamento='Cartão', total=103.0}", recibo);
    }

    @Test
    void devePagarNoDeliveryComDinheiroIncluindoTaxaDeEntrega() {
        String recibo = delivery().pagar(92.0f, "Dinheiro");
        assertEquals("AtendimentoDelivery{numero=2, cliente='Bruno', pagamento='Dinheiro', total=100.0}", recibo);
    }

    @Test
    void devePagarComEstrategiaInformada() {
        String recibo = balcao().pagar(100.0f, new PagamentoPix());
        assertEquals("Atendimento{numero=1, cliente='Ana', pagamento='Pix', total=95.0}", recibo);
    }

    @Test
    void devePagarComEstrategiaPersonalizada() {
        FormaPagamento vale = new FormaPagamento() {
            public float calcular(float valor) {
                return valor - 20.0f;
            }

            public String getNome() {
                return "Vale";
            }
        };
        String recibo = balcao().pagar(100.0f, vale);
        assertEquals("Atendimento{numero=1, cliente='Ana', pagamento='Vale', total=80.0}", recibo);
    }

    @Test
    void deveRegistrarPagamentoNoCaixa() {
        balcao().pagar(100.0f, "Pix");
        assertEquals(95.0f, Caixa.getInstance().getTotal());
        assertEquals(1, Caixa.getInstance().getQuantidade());
    }

    @Test
    void deveAcumularNoMesmoCaixaPagamentosDeAtendimentosDiferentes() {
        balcao().pagar(100.0f, "Pix");        // 95
        delivery().pagar(92.0f, "Cartao");    // 103
        assertEquals(198.0f, Caixa.getInstance().getTotal());
        assertEquals(2, Caixa.getInstance().getQuantidade());
    }

    @Test
    void naoDevePagarValorZero() {
        try {
            balcao().pagar(0.0f, "Pix");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Valor inválido", e.getMessage());
            assertEquals(0, Caixa.getInstance().getQuantidade());
        }
    }

    @Test
    void naoDevePagarValorNegativo() {
        try {
            balcao().pagar(-10.0f, "Pix");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Valor inválido", e.getMessage());
            assertEquals(0, Caixa.getInstance().getQuantidade());
        }
    }

    @Test
    void naoDevePagarComFormaInexistente() {
        try {
            balcao().pagar(100.0f, "Bitcoin");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Forma de pagamento inexistente", e.getMessage());
            assertEquals(0, Caixa.getInstance().getQuantidade());
        }
    }

    @Test
    void naoDevePagarComFormaInvalida() {
        try {
            balcao().pagar(100.0f, "Cheque");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Forma de pagamento inválida", e.getMessage());
            assertEquals(0, Caixa.getInstance().getQuantidade());
        }
    }

}
