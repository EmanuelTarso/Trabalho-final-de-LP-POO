package conexoes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ConexaoSQLite {

    private Connection conexao;
    

    /**
     * Conecta a um banco de dados (cria o banco, caso ele não exista)
     * @return
     */
    public boolean conectar(){
        try {
           String url = "jdbc:sqlite:C:\\BancodeDados/banco_sistema_de_vendas.bd";

           this.conexao = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
        System.out.println("conectou");
        return true;
    };

    public boolean desconectar(){
        try{
            if(this.conexao.isClosed()== false){
                this.conexao.close();
            }

        }catch (SQLException e){
            System.err.println(e.getMessage());
            return false;
        }
        System.out.println("desconectou");
        return true;
    };  
}
