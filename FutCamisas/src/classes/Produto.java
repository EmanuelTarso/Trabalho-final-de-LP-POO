package classes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Produto {
    private int id;
    private String nome;
    private double preco;
    private String caminhoImagem;  // Novo atributo
    private int estoque;

    public Produto(int id, String nome, double preco, String caminhoImagem) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.caminhoImagem = caminhoImagem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getEstoque(){
        return estoque;
    }

    public void setEstoque(int estoque){
        this.estoque = estoque;
    }

    public String getCaminhoImagem() {
        return caminhoImagem;
    }

    public void setCaminhoImagem(String caminhoImagem) {
        this.caminhoImagem = caminhoImagem;
    }
    /**
     * Método para imprimir todos os PRODUTOS do Banco de Dados 
     * @return
     */
    public static List<Produto> imprimeProdutos() {
        List<Produto> produtos = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\BancodeDados/banco_sistema_de_vendas.bd");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM [PRODUTOS]")) {
            while (rs.next()) {
                produtos.add(new Produto(
                        rs.getInt("Produto_ID"),
                        rs.getString("Nome"),
                        rs.getDouble("Preco"),
                        rs.getString("CaminhoImagem")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produtos;
    }
     /**
     * Método para adicionar PRODUTOS ao Banco de Dados
     * @return
     */
    public static void adicionarProduto(Produto produto) {
        String sql = "INSERT INTO [PRODUTOS](Nome, Preco, CaminhoImagem) VALUES(?, ?, ?)";
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\BancodeDados/banco_sistema_de_vendas.bd");
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, produto.getNome());
            pstmt.setDouble(2, produto.getPreco());
            pstmt.setString(3, produto.getCaminhoImagem());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}