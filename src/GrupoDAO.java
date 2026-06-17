import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class GrupoDAO {

    public void criarTabela() {

        String sql = """
                CREATE TABLE IF NOT EXISTS GRUPO_APOSTA (
                    ID INT AUTO_INCREMENT PRIMARY KEY,
                    NOME VARCHAR(100) NOT NULL
                )
                """;

        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql);
            System.out.println("Tabela GRUPO_APOSTA criada com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void salvar(Grupo grupo) {

        String sql = "INSERT INTO GRUPO_APOSTA (NOME) VALUES (?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, grupo.getNome());

            stmt.executeUpdate();

            System.out.println("Grupo salvo no banco!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deletar(String nome) {
        String sql = "DELETE FROM GRUPO_APOSTA WHERE NOME = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.executeUpdate();
            System.out.println("Grupo \"" + nome + "\" excluído do banco!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String listarGrupos() {

        String sql = "SELECT * FROM GRUPO_APOSTA";
        String texto = "--- GRUPOS SALVOS NO BANCO ---\n\n";

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
                texto += "Nenhum grupo salvo.";
            }

        } catch (Exception e) {
            e.printStackTrace();
            texto += "Erro ao listar grupos.";
        }

        return texto;
    }
}