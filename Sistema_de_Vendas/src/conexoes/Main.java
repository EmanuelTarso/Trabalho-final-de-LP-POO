package conexoes;

public class Main {
    public static void main(String[] args) {
        
        ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
        conexaoSQLite.conectar();
        conexaoSQLite.desconectar();
    }

}
