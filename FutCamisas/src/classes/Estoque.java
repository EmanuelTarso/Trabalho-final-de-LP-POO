package classes;
import java.util.ArrayList;
import java.util.List;

public class Estoque {
    private List<Produto> produtos;

    public Estoque() {
        produtos = new ArrayList<>();
    }

    // Método para adicionar produtos ao estoque
    public void adicionarProduto(Produto produto, int quantidade) {
        boolean produtoExistente = false;
        for (Produto p : produtos) {
            if (p.getNome().equals(produto.getNome())) {
                p.setEstoque(p.getEstoque() + quantidade);
                produtoExistente = true;
                break;
            }
        }
        if (!produtoExistente) {
            produto.setEstoque(quantidade);
            produtos.add(produto);
        }
    }

    // Método para remover produtos do estoque
    public void removerProduto(Produto produto, int quantidade) {
        for (int i = 0; i < produtos.size(); i++) {
            Produto p = produtos.get(i);
            if (p.getNome().equals(produto.getNome())) {
                int novaQuantidade = p.getEstoque() - quantidade;
                if (novaQuantidade <= 0) {
                    produtos.remove(i);
                } else {
                    p.setEstoque(novaQuantidade);
                }
                break;
            }
        }
    }

    // Método para listar todos os produtos no estoque
    public List<Produto> listarProdutos() {
        return new ArrayList<>(produtos);
    }
}