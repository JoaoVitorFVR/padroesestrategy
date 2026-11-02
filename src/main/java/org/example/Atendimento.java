package org.example;

public abstract class Atendimento {

    private int numero;
    protected String cliente;

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }


    public String pagar(float valor, FormaPagamento forma) {
        this.validarValor(valor);
        float valorFinal = forma.calcular(valor + this.calcularTaxa());
        Caixa.getInstance().registrar(valorFinal);
        return this.gerarRecibo(forma, valorFinal);
    }

    public String pagar(float valor, String tipoPagamento) {
        FormaPagamento forma = FormaPagamentoFactory.obterForma(tipoPagamento);
        return this.pagar(valor, forma);
    }

    protected void validarValor(float valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor inválido");
        }
    }

    protected abstract float calcularTaxa();

    protected String getTipo() {
        return "Atendimento";
    }

    protected String gerarRecibo(FormaPagamento forma, float valorFinal) {
        return getTipo() + "{" +
                "numero=" + this.numero +
                ", cliente='" + this.cliente + "'" +
                ", pagamento='" + forma.getNome() + "'" +
                ", total=" + valorFinal +
                "}";
    }
}
