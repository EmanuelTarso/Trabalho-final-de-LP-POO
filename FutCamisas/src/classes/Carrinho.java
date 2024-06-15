package classes;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {

    private List<ItensPedido> itens;

    public Carrinho() {
        itens = new ArrayList<>();
    }

    public void adicionarItem(Produto produto, int quantidade, String tamanho) {
        boolean itemExistente = false;
        for (ItensPedido item : itens) {
            if (item.getProduto().equals(produto) && item.getTamanho().equals(tamanho)) {
                item.setQuantidade(item.getQuantidade() + quantidade);
                itemExistente = true;
                break;
            }
        }
        if (!itemExistente) {
            itens.add(new ItensPedido(produto, quantidade, tamanho));
        }
    }

    public void removerItem(ItensPedido itemPedido) {
        itens.remove(itemPedido);
    }

    public List<ItensPedido> getItens() {
        return itens;
    }

    public double calcularValorTotal() {
        double total = 0.0;
        for (ItensPedido item : itens) {
            total += item.getValorTotal();
        }
        return total;
    }

    public void atualizarTelaCarrinho() {
    }
}
