package classes;

public class Pagamento extends FormaDePagamento {
    private String numeroCartao;
    private String validade;
    private String cvv;

    // Construtor
    public Pagamento(String numeroCartao, String validade, String cvv) {
        this.numeroCartao = numeroCartao;
        this.validade = validade;
        this.cvv = cvv;
    }

    // Getters e Setters
    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    @Override
    public String realizarPagamento(double valor) {
        // Efetuação do pagamento
        return "Pagamento de R$" + valor + " efetuado!";
    }
}
