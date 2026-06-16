import java.sql.Connection;

public class Main {

    public static void main(String[] args) {

        Connection conexao = Conexao.conectar();

        if (conexao != null) {
            System.out.println("Conectado ao banco H2 com sucesso!");
        } else {
            System.out.println("Falha ao conectar ao banco H2.");
        }

        ParticipanteDAO participanteDAO = new ParticipanteDAO();
        participanteDAO.criarTabela();

        ApostaDAO apostaDAO = new ApostaDAO();
        apostaDAO.criarTabela();

        GrupoDAO grupoDAO = new GrupoDAO();
        grupoDAO.criarTabela();

        new TelaPrincipal().setVisible(true);
    }
}