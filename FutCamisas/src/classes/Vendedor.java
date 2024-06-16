package classes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Vendedor extends Pessoa {
    private double salario;

    // Construtor
    public Vendedor(String nome, String cpf, String email, double salario) {
        super(nome, cpf, email);
        this.salario = salario;
    }

    // Getters e Setters
    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
     /**
     * Método para ler todos os VENDEDORES do Banco de Dados.
     * @return
     */
    public static List<Vendedor> lerVendedores() {
        List<Vendedor> vendedores = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\BancodeDados\\banco_sistema_de_vendas.bd");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM [VENDEDORES]")) {
            while (rs.next()) {
                vendedores.add(new Vendedor(
                        rs.getString("Nome"),
                        rs.getString("CPF"),
                        rs.getString("Email"),
                        rs.getDouble("Salario")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vendedores;
    }
     /**
     * Método para adicionar VENDEDORES ao Banco de Dados
     * @return
     */
    public static void adicionarVendedor(Vendedor vendedor) {
        String sql = "INSERT INTO [VENDEDORES](Nome, CPF, Email, Salario) VALUES(?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\BancodeDados\\banco_sistema_de_vendas.bd");
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, vendedor.getNome());
            pstmt.setString(2, vendedor.getCpf());
            pstmt.setString(3, vendedor.getEmail());
            pstmt.setDouble(4, vendedor.getSalario());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}