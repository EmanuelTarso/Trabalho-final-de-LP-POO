package classes;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {

    private List<ItensPedido> itens;

    // Construtor
    public Carrinho() {
        itens = new ArrayList<>();
    }

    public void adicionarItem(Produto produto, int quantidade, String tamanho) {
        boolean itemExiste = false;
        for (ItensPedido item : itens) {
            if (item.getProduto().equals(produto) && item.getTamanho().equals(tamanho)) {
                item.setQuantidade(item.getQuantidade() + quantidade);
                itemExiste = true;
                break;
            }
        }
        if (!itemExiste) {
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

    public void removerUmaUnidadeItem(ItensPedido item) {
        int novaQuantidade = item.getQuantidade() - 1;
        item.setQuantidade(novaQuantidade); // Define a nova quantidade do item
        if (novaQuantidade <= 0) {
            itens.remove(item); // Remove o item do carrinho se a quantidade ficar menor ou igual a zero
        }
    }

    public void atualizarTelaCarrinho() {
    }
}
