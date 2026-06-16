import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ParticipanteDAO {

    public void criarTabela() {

        String sql = """
                CREATE TABLE IF NOT EXISTS PARTICIPANTE (
                    ID INT AUTO_INCREMENT PRIMARY KEY,
                    NOME VARCHAR(100) NOT NULL,
                    PONTOS INT NOT NULL
                )
                """;

        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql);

            System.out.println("Tabela PARTICIPANTE criada com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void salvar(Participante participante) {

        String sql = "INSERT INTO PARTICIPANTE (NOME, PONTOS) VALUES (?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, participante.getNome());
            stmt.setInt(2, participante.getPontos());

            stmt.executeUpdate();

            System.out.println("Participante salvo no banco!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void atualizarPontos(Participante participante) {

        String sql = "UPDATE PARTICIPANTE SET PONTOS = ? WHERE NOME = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, participante.getPontos());
            stmt.setString(2, participante.getNome());

            stmt.executeUpdate();

            System.out.println("Pontuação atualizada no banco!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String listarParticipantes() {

        String sql = "SELECT * FROM PARTICIPANTE";
        String texto = "--- PARTICIPANTES SALVOS NO BANCO ---\n\n";

        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            boolean encontrou = false;

            while (rs.next()) {
                encontrou = true;

                texto += rs.getInt("ID") + " - "
                        + rs.getString("NOME") + " - "
                        + rs.getInt("PONTOS") + " pontos\n";
            }

            if (!encontrou) {
                texto += "Nenhum participante salvo.";
            }

        } catch (Exception e) {
            e.printStackTrace();
            texto += "Erro ao listar participantes.";
        }

        return texto;
    }

    public String rankingGeral() {

        String sql = "SELECT * FROM PARTICIPANTE ORDER BY PONTOS DESC";
        String texto = "--- RANKING GERAL DO BANCO ---\n\n";

        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            boolean encontrou = false;
            int posicao = 1;

            while (rs.next()) {
                encontrou = true;

                texto += posicao + "º - "
                        + rs.getString("NOME") + " - "
                        + rs.getInt("PONTOS") + " pontos\n";

                posicao++;
            }

            if (!encontrou) {
                texto += "Nenhum participante salvo.";
            }

        } catch (Exception e) {
            e.printStackTrace();
            texto += "Erro ao carregar ranking.";
        }

        return texto;
    }
}