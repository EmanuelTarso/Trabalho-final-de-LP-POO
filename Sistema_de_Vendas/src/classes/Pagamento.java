package classes;
public class Pagamento extends FormaDePagamento {
    private String numeroCartao;
    private String validade;
    private String cvv;

    public Pagamento(String numeroCartao, String validade, String cvv) {
        this.numeroCartao = numeroCartao;
        this.validade = validade;
        this.cvv = cvv;
    }

    @Override
    public String realizarPagamento(double valor) {
        //efetuação do pagamento
        return "Pagamento de R$" + valor + " efetuado!";
    }
}