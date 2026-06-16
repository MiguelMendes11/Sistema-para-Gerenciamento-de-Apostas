import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static Connection conexao;

    private static final String URL = "jdbc:h2:./bancoApostas";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    private Conexao() {
    }

    public static Connection conectar() {

        try {

            if (conexao == null || conexao.isClosed()) {

                conexao = DriverManager.getConnection(
                        URL,
                        USER,
                        PASSWORD
                );

                System.out.println("Conexão criada com sucesso!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conexao;
    }
}