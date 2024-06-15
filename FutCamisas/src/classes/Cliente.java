package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Cliente extends Pessoa {
    private String endereco;

    // Construtor
    public Cliente(String nome, String cpf, String email, String endereco) {
        super(nome, cpf, email);
        this.endereco = endereco;
    }

    // Getters e Setters
    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    /**
     * Método para imprimir todos os CLIENTES do Banco de Dados
     * @return
     */
    public static List<Cliente> imprimeClientes() {
        List<Cliente> clientes = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\BancodeDados/banco_sistema_de_vendas.bd");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM [CLIENTES]")) {
            while (rs.next()) {
                clientes.add(new Cliente(
                        rs.getString("Nome"),
                        rs.getString("CPF"),
                        rs.getString("Email"),
                        rs.getString("Endereco")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }
     /**
     * Método para adicionar CLIENTES ao Banco de Dados
     * @return
     */
    public static void adicionarCliente(Cliente cliente) {
        String sql = "INSERT INTO [CLIENTES](Nome, CPF, Email, Endereco) VALUES(?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\BancodeDados/banco_sistema_de_vendas.bd");
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cliente.getNome());
            pstmt.setString(2, cliente.getCpf());
            pstmt.setString(3, cliente.getEmail());
            pstmt.setString(4, cliente.getEndereco());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}