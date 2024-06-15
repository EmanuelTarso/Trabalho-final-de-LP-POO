package teste;

import classes.Cliente;
import classes.Produto;
import classes.Vendedor;

public class TesteBancoDeDados {
    public static void main(String[] args) {
        // Adicionando dados ao banco
        Cliente cliente1 = new Cliente("Maria Silva", "123.456.789-00", "maria@gmail.com", "Rua das Flores, 123");
        Cliente.adicionarCliente(cliente1);

        Cliente cliente2 = new Cliente("Usuario", "senha", "email", "qualquerlugar");
        Cliente.adicionarCliente(cliente2);

        Vendedor vendedor1 = new Vendedor("João Pereira", "987.654.321-00", "joao@gmail.com", 3500.0);
        Vendedor.adicionarVendedor(vendedor1);

        Produto produto1 = new Produto(1, "Camisa Brasil", 79.90, "imagens/camisas_brasil.jpeg");
        Produto.adicionarProduto(produto1);

        // Imprimindo os dados do banco
        System.out.println("Clientes:");
        for (Cliente c : Cliente.imprimeClientes()) {
            System.out.println(c.getNome() + " - " + c.getCpf() + " - " + c.getEmail() + " - " + c.getEndereco());
        }

        System.out.println("\nVendedores:");
        for (Vendedor v : Vendedor.imprimeVendedores()) {
            System.out.println(v.getNome() + " - " + v.getCpf() + " - " + v.getEmail() + " - ");
        }

        System.out.println("\nProdutos:");
        for (Produto p : Produto.imprimeProdutos()) {
            System.out.println(p.getNome() + " - " + p.getPreco() + " - " + p.getCaminhoImagem());
        }
    }
}