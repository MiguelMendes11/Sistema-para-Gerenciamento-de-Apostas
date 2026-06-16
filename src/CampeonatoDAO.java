import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class CampeonatoDAO {

    public void criarTabela() {

        String sql = """
                CREATE TABLE IF NOT EXISTS CAMPEONATO (
                    ID INT AUTO_INCREMENT PRIMARY KEY,
                    NOME VARCHAR(100) NOT NULL
                )
                """;

        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql);
            System.out.println("Tabela CAMPEONATO criada com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void salvar(Campeonato campeonato) {

        String sql = "INSERT INTO CAMPEONATO (NOME) VALUES (?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, campeonato.getNome());

            stmt.executeUpdate();

            System.out.println("Campeonato salvo no banco!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String listarCampeonatos() {

        String sql = "SELECT * FROM CAMPEONATO";
        String texto = "--- CAMPEONATOS SALVOS NO BANCO ---\n\n";

        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            boolean encontrou = false;

            while (rs.next()) {
                encontrou = true;

                texto += rs.getInt("ID") + " - "
                        + rs.getString("NOME") + "\n";
            }

            if (!encontrou) {
                texto += "Nenhum campeonato salvo.";
            }

        } catch (Exception e) {
            e.printStackTrace();
            texto += "Erro ao listar campeonatos.";
        }

        return texto;
    }
}