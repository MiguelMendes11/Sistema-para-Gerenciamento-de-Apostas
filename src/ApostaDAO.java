import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ApostaDAO {

    public void criarTabela() {

        String sql = """
                CREATE TABLE IF NOT EXISTS APOSTA (
                    ID INT AUTO_INCREMENT PRIMARY KEY,
                    PARTICIPANTE VARCHAR(100) NOT NULL,
                    PARTIDA VARCHAR(200) NOT NULL,
                    PALPITE_CASA INT NOT NULL,
                    PALPITE_VISITANTE INT NOT NULL
                )
                """;

        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql);
            System.out.println("Tabela APOSTA criada com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void salvar(Aposta aposta) {

        String sql = "INSERT INTO APOSTA (PARTICIPANTE, PARTIDA, PALPITE_CASA, PALPITE_VISITANTE) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, aposta.getParticipante().getNome());
            stmt.setString(2, aposta.getPartida().getInfo());
            stmt.setInt(3, aposta.getPalpiteCasa());
            stmt.setInt(4, aposta.getPalpiteVisitante());

            stmt.executeUpdate();

            System.out.println("Aposta salva no banco!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String listarApostas() {

        String sql = "SELECT * FROM APOSTA";
        String texto = "--- HISTÓRICO DE APOSTAS ---\n\n";

        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            boolean encontrou = false;

            while (rs.next()) {
                encontrou = true;

                texto += rs.getInt("ID") + " - "
                        + rs.getString("PARTICIPANTE") + "\n"
                        + rs.getString("PARTIDA") + "\n"
                        + "Palpite: "
                        + rs.getInt("PALPITE_CASA") + " x "
                        + rs.getInt("PALPITE_VISITANTE") + "\n\n";
            }

            if (!encontrou) {
                texto += "Nenhuma aposta salva.";
            }

        } catch (Exception e) {
            e.printStackTrace();
            texto += "Erro ao listar apostas.";
        }

        return texto;
    }
}