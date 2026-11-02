package org.example;

public class FormaPagamentoFactory {

    public static FormaPagamento obterForma(String tipo) {
        Class<?> classe = null;
        Object objeto = null;
        try {
            classe = Class.forName("org.example.Pagamento" + tipo);
            objeto = classe.getDeclaredConstructor().newInstance();
        } catch (Exception ex) {
            throw new IllegalArgumentException("Forma de pagamento inexistente");
        }
        if (!(objeto instanceof FormaPagamento)) {
            throw new IllegalArgumentException("Forma de pagamento inválida");
        }
        return (FormaPagamento) objeto;
    }
}
