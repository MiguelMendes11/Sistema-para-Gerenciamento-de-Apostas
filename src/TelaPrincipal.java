import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TelaPrincipal extends JFrame {

    private List<Grupo> grupos = new ArrayList<>();
    private List<Partida> partidas = new ArrayList<>();
    private List<Aposta> apostas = new ArrayList<>();
    private Campeonato camp = null;

    private Clube c1 = new Clube("Palmeiras");
    private Clube c2 = new Clube("Corinthians");
    private Clube c3 = new Clube("São Paulo");
    private Clube c4 = new Clube("Santos");
    private Clube c5 = new Clube("Flamengo");
    private Clube c6 = new Clube("Vasco");
    private Clube c7 = new Clube("Grêmio");
    private Clube c8 = new Clube("Internacional");

    public TelaPrincipal() {
        setTitle("Sistema de Gerenciamento de Apostas");
        setSize(640, 560);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setMinimumSize(new Dimension(600, 500));
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                int resp = JOptionPane.showConfirmDialog(
                        null, "Deseja realmente sair?", "Sair",
                        JOptionPane.YES_NO_OPTION);
                if (resp == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        UIManager.put("Button.font", new Font("Segoe UI", Font.BOLD, 13));
        UIManager.put("OptionPane.yesButtonText", "Sim");
        UIManager.put("OptionPane.noButtonText", "Não");

        partidas.add(new Partida(c1, c2, "17/07/2026", "20:00"));
        partidas.add(new Partida(c3, c4, "18/07/2026", "18:00"));
        partidas.add(new Partida(c5, c6, "19/07/2026", "16:00"));
        partidas.add(new Partida(c7, c8, "20/07/2026", "21:00"));

        JButton b1 = new JButton("Criar Campeonato");
        JButton b2 = new JButton("Criar Grupo");
        JButton b3 = new JButton("Cadastrar Participantes");
        JButton b4 = new JButton("Fazer Aposta");
        JButton b5 = new JButton("Registrar Resultado");
        JButton b6 = new JButton("Ver Classificação");
        JButton b7 = new JButton("Ver Partidas");
        JButton b8 = new JButton("Finalizar Partida");
        JButton b9 = new JButton("Ver Participantes");
        JButton b10 = new JButton("Ranking Geral");
        JButton b11 = new JButton("Histórico de Apostas");
        JButton b12 = new JButton("Ver Grupos");
        JButton b13 = new JButton("Ver Campeonatos");
        JButton b14 = new JButton("Excluir Participante");
        JButton b15 = new JButton("Excluir Grupo");

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(new Color(245, 245, 245));

        JPanel operacoesPanel = new JPanel(new GridLayout(0, 2, 8, 8));
        operacoesPanel.setBorder(BorderFactory.createTitledBorder("Operações"));
        operacoesPanel.setBackground(new Color(255, 255, 255));
        operacoesPanel.add(b1);
        operacoesPanel.add(b2);
        operacoesPanel.add(b3);
        operacoesPanel.add(b14);
        operacoesPanel.add(b15);
        mainPanel.add(operacoesPanel);

        JPanel apostasPanel = new JPanel(new GridLayout(0, 2, 8, 8));
        apostasPanel.setBorder(BorderFactory.createTitledBorder("Apostas"));
        apostasPanel.setBackground(new Color(255, 255, 255));
        apostasPanel.add(b4);
        apostasPanel.add(b8);
        apostasPanel.add(b5);
        mainPanel.add(apostasPanel);

        JPanel consultasPanel = new JPanel(new GridLayout(0, 2, 8, 8));
        consultasPanel.setBorder(BorderFactory.createTitledBorder("Consultas"));
        consultasPanel.setBackground(new Color(255, 255, 255));
        consultasPanel.add(b7);
        consultasPanel.add(b6);
        consultasPanel.add(b10);
        consultasPanel.add(b11);
        consultasPanel.add(b9);
        consultasPanel.add(b12);
        consultasPanel.add(b13);
        mainPanel.add(consultasPanel);

        add(mainPanel, BorderLayout.CENTER);

        // botão 1
        b1.addActionListener(e -> {
            String nome = JOptionPane.showInputDialog("Nome do campeonato:");
            if (nome == null || nome.trim().isEmpty()) return;

            camp = new Campeonato(nome);
            grupos.clear();
            apostas.clear();

            camp.adicionarClube(c1);
            camp.adicionarClube(c2);
            camp.adicionarClube(c3);
            camp.adicionarClube(c4);
            camp.adicionarClube(c5);
            camp.adicionarClube(c6);
            camp.adicionarClube(c7);
            camp.adicionarClube(c8);

            CampeonatoDAO dao = new CampeonatoDAO();
            dao.salvar(camp);

            String msg = "Campeonato criado e salvo no banco: " + nome + "\n\nClubes:\n"
                    + c1.getNome() + "\n"
                    + c2.getNome() + "\n"
                    + c3.getNome() + "\n"
                    + c4.getNome() + "\n"
                    + c5.getNome() + "\n"
                    + c6.getNome() + "\n"
                    + c7.getNome() + "\n"
                    + c8.getNome();

            JOptionPane.showMessageDialog(null, msg);
        });

        // botão 2
        b2.addActionListener(e -> {
            if (camp == null) {
                JOptionPane.showMessageDialog(null, "Crie o campeonato primeiro!");
                return;
            }

            if (grupos.size() >= 5) {
                JOptionPane.showMessageDialog(null, "Limite de grupos atingido!");
                return;
            }

            String nome = JOptionPane.showInputDialog("Nome do grupo:");
            if (nome == null || nome.trim().isEmpty()) return;

            for (Grupo g : grupos) {
                if (g.getNome().equalsIgnoreCase(nome.trim())) {
                    JOptionPane.showMessageDialog(null, "Já existe um grupo com esse nome!");
                    return;
                }
            }

            Grupo grupo = new Grupo(nome.trim());
            grupos.add(grupo);

            GrupoDAO dao = new GrupoDAO();
            dao.salvar(grupo);

            JOptionPane.showMessageDialog(null, "Grupo criado e salvo no banco!");
        });

        // botão 3
        b3.addActionListener(e -> {
            if (grupos.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Crie um grupo primeiro!");
                return;
            }

            String[] nomesGrupos = new String[grupos.size()];
            for (int i = 0; i < grupos.size(); i++) {
                nomesGrupos[i] = grupos.get(i).getNome();
            }

            int gIndex = JOptionPane.showOptionDialog(
                    null, "Escolha o grupo:", "Grupos",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, nomesGrupos, nomesGrupos[0]);

            if (gIndex == -1) return;

            Grupo g = grupos.get(gIndex);

            String lista = "Participantes:\n";
            for (Participante p : g.getParticipantes()) {
                lista += "- " + p.getNome() + "\n";
            }

            if (g.getParticipantes().size() >= 5) {
                JOptionPane.showMessageDialog(null, lista + "\nLimite atingido!");
                return;
            }

            String nome = JOptionPane.showInputDialog(lista + "\nNome:");
            if (nome == null || nome.trim().isEmpty()) return;

            for (Participante p : g.getParticipantes()) {
                if (p.getNome().equalsIgnoreCase(nome.trim())) {
                    JOptionPane.showMessageDialog(null, "Esse participante já existe no grupo!");
                    return;
                }
            }

            Participante participante = new Participante(nome.trim());

            g.adicionarParticipante(participante);

            ParticipanteDAO dao = new ParticipanteDAO();
            dao.salvar(participante);

            JOptionPane.showMessageDialog(null, "Participante adicionado e salvo no banco!");
        });

        // botão 4
        b4.addActionListener(e -> {
            if (grupos.isEmpty()) return;

            String[] nomesGrupos = new String[grupos.size()];
            for (int i = 0; i < grupos.size(); i++) {
                nomesGrupos[i] = grupos.get(i).getNome();
            }

            int gIndex = JOptionPane.showOptionDialog(
                    null, "Escolha o grupo:", "Grupo",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, nomesGrupos, nomesGrupos[0]);

            if (gIndex == -1) return;

            Grupo g = grupos.get(gIndex);

            if (g.getParticipantes().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Sem participantes!");
                return;
            }

            String[] nomesParticipantes = new String[g.getParticipantes().size()];
            for (int i = 0; i < g.getParticipantes().size(); i++) {
                nomesParticipantes[i] = g.getParticipantes().get(i).getNome();
            }

            int pIndex = JOptionPane.showOptionDialog(
                    null, "Escolha o participante:", "Participante",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, nomesParticipantes, nomesParticipantes[0]);

            if (pIndex == -1) return;

            java.util.List<Integer> indicesDisponiveis = new ArrayList<>();
            for (int i = 0; i < partidas.size(); i++) {
                if (partidas.get(i).podeApostar()) {
                    indicesDisponiveis.add(i);
                }
            }

            String[] opcoesPartidas = indicesDisponiveis.stream()
                    .map(i -> partidas.get(i).getInfo())
                    .toArray(String[]::new);

            if (opcoesPartidas.length == 0) {
                JOptionPane.showMessageDialog(null, "Nenhuma partida disponível para aposta.");
                return;
            }

            int partIndex = JOptionPane.showOptionDialog(
                    null, "Escolha a partida:", "Partidas",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, opcoesPartidas, opcoesPartidas[0]);

            if (partIndex == -1) return;

            Partida partidaEscolhida = partidas.get(indicesDisponiveis.get(partIndex));

            for (Aposta a : apostas) {
                if (a.getParticipante() == g.getParticipantes().get(pIndex)
                        && a.getPartida() == partidaEscolhida) {
                    JOptionPane.showMessageDialog(null, "Você já apostou nessa partida!");
                    return;
                }
            }

            try {
                String pcStr = JOptionPane.showInputDialog("Gols casa:");
                String pvStr = JOptionPane.showInputDialog("Gols visitante:");

                if (pcStr == null || pvStr == null) return;

                int pc = Integer.parseInt(pcStr);
                int pv = Integer.parseInt(pvStr);

                if (pc < 0 || pv < 0) {
                    JOptionPane.showMessageDialog(null, "Placar inválido! Digite valores positivos.");
                    return;
                }

                Aposta aposta = new Aposta(
                        g.getParticipantes().get(pIndex),
                        partidaEscolhida,
                        pc,
                        pv
                );

                apostas.add(aposta);

                ApostaDAO dao = new ApostaDAO();
                dao.salvar(aposta);

                JOptionPane.showMessageDialog(null, "Aposta registrada e salva no banco!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro!");
            }
        });

        // botão 5
        b5.addActionListener(e -> {
            java.util.List<Integer> indicesSemResultado = new ArrayList<>();
            for (int i = 0; i < partidas.size(); i++) {
                Partida p = partidas.get(i);
                if (p.isFinalizada() && p.podeRegistrarResultado()) {
                    indicesSemResultado.add(i);
                }
            }

            if (indicesSemResultado.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nenhuma partida finalizada aguardando resultado.");
                return;
            }

            String[] opcoes = indicesSemResultado.stream()
                    .map(i -> partidas.get(i).getInfo())
                    .toArray(String[]::new);

            int index = JOptionPane.showOptionDialog(
                    null, "Escolha a partida:", "Resultado",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, opcoes, opcoes[0]);

            if (index == -1) return;

            Partida p = partidas.get(indicesSemResultado.get(index));

            try {
                String gcStr = JOptionPane.showInputDialog("Gols casa:");
                String gvStr = JOptionPane.showInputDialog("Gols visitante:");

                if (gcStr == null || gvStr == null) return;

                int gc = Integer.parseInt(gcStr);
                int gv = Integer.parseInt(gvStr);

                if (gc < 0 || gv < 0) {
                    JOptionPane.showMessageDialog(null, "Placar inválido! Digite valores positivos.");
                    return;
                }

                p.setResultado(gc, gv);

                ParticipanteDAO dao = new ParticipanteDAO();
                String detalhes = "";

                for (Aposta a : apostas) {
                    if (a.getPartida() == p) {
                        int pontos = a.calcularPontos();
                        dao.atualizarPontos(a.getParticipante());
                        if (pontos > 0) {
                            String tipo = (pontos == 10) ? "placar exato!" : "vencedor";
                            detalhes += a.getParticipante().getNome() + " ganhou " + pontos + " pontos (" + tipo + ")\n";
                        }
                    }
                }

                String mensagem = "Resultado registrado!\n" + p.getPlacar() + "\n\n" + detalhes;
                JOptionPane.showMessageDialog(null, mensagem);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro!");
            }
        });

        // botão 6
        b6.addActionListener(e -> {
            if (grupos.isEmpty()) return;

            String[] nomes = new String[grupos.size()];
            for (int i = 0; i < grupos.size(); i++) {
                nomes[i] = grupos.get(i).getNome();
            }

            int index = JOptionPane.showOptionDialog(
                    null, "Escolha o grupo:", "Classificação",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, nomes, nomes[0]);

            if (index == -1) return;

            JOptionPane.showMessageDialog(null, grupos.get(index).getClassificacao());
        });

        // botão 7
        b7.addActionListener(e -> {
            String texto = "";
            for (Partida p : partidas) {
                texto += p.getInfo() + "\n";
            }
            JOptionPane.showMessageDialog(null, texto);
        });

        // botão 8
        b8.addActionListener(e -> {
            java.util.List<Integer> indicesNaoFinalizadas = new ArrayList<>();
            for (int i = 0; i < partidas.size(); i++) {
                if (!partidas.get(i).isFinalizada()) {
                    indicesNaoFinalizadas.add(i);
                }
            }

            if (indicesNaoFinalizadas.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Todas as partidas já foram finalizadas.");
                return;
            }

            String[] opcoes = indicesNaoFinalizadas.stream()
                    .map(i -> partidas.get(i).getInfo())
                    .toArray(String[]::new);

            int index = JOptionPane.showOptionDialog(
                    null, "Escolha a partida que deseja finalizar:",
                    "Finalizar Partida",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null, opcoes, opcoes[0]);

            if (index == -1) return;

            Partida p = partidas.get(indicesNaoFinalizadas.get(index));
            p.finalizarPartida();
            JOptionPane.showMessageDialog(null, "Partida finalizada!");
        });

        // botão 9
        b9.addActionListener(e -> {
            ParticipanteDAO dao = new ParticipanteDAO();
            String participantesBanco = dao.listarParticipantes();

            JOptionPane.showMessageDialog(null, participantesBanco);
        });

        // botão 10
        b10.addActionListener(e -> {
            ParticipanteDAO dao = new ParticipanteDAO();
            String ranking = dao.rankingGeral();

            JOptionPane.showMessageDialog(null, ranking);
        });

        // botão 11
        b11.addActionListener(e -> {
            ApostaDAO dao = new ApostaDAO();
            String historico = dao.listarApostas();

            JOptionPane.showMessageDialog(null, historico);
        });

        // botão 12
        b12.addActionListener(e -> {
            GrupoDAO dao = new GrupoDAO();
            String gruposBanco = dao.listarGrupos();

            JOptionPane.showMessageDialog(null, gruposBanco);
        });

        // botão 13
        b13.addActionListener(e -> {
            CampeonatoDAO dao = new CampeonatoDAO();
            String campeonatosBanco = dao.listarCampeonatos();

            JOptionPane.showMessageDialog(null, campeonatosBanco);
        });

        // botão 14
        b14.addActionListener(e -> {
            java.util.List<String> todosParticipantes = new ArrayList<>();

            for (Grupo g : grupos) {
                for (Participante p : g.getParticipantes()) {
                    todosParticipantes.add(p.getNome());
                }
            }

            if (todosParticipantes.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nenhum participante cadastrado.");
                return;
            }

            String[] opcoes = todosParticipantes.toArray(new String[0]);
            int escolha = JOptionPane.showOptionDialog(
                    null, "Selecione o participante para excluir:", "Excluir Participante",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, opcoes, opcoes[0]);

            if (escolha == -1) return;

            String nomeExcluir = todosParticipantes.get(escolha);
            int confirmacao = JOptionPane.showConfirmDialog(
                    null, "Excluir \"" + nomeExcluir + "\" permanentemente?",
                    "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);

            if (confirmacao != JOptionPane.YES_OPTION) return;

            ParticipanteDAO dao = new ParticipanteDAO();
            dao.deletar(nomeExcluir);

            for (Grupo g : grupos) {
                g.getParticipantes().removeIf(p -> p.getNome().equals(nomeExcluir));
            }

            JOptionPane.showMessageDialog(null, "Participante \"" + nomeExcluir + "\" excluído!");
        });

        // botão 15
        b15.addActionListener(e -> {
            if (grupos.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nenhum grupo cadastrado.");
                return;
            }

            String[] opcoes = new String[grupos.size()];
            for (int i = 0; i < grupos.size(); i++) {
                opcoes[i] = grupos.get(i).getNome();
            }

            int escolha = JOptionPane.showOptionDialog(
                    null, "Selecione o grupo para excluir:", "Excluir Grupo",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, opcoes, opcoes[0]);

            if (escolha == -1) return;

            String nomeExcluir = grupos.get(escolha).getNome();

            if (!grupos.get(escolha).getParticipantes().isEmpty()) {
                int confirmacao = JOptionPane.showConfirmDialog(
                        null, "O grupo \"" + nomeExcluir + "\" tem participantes. Excluir mesmo assim?",
                        "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
                if (confirmacao != JOptionPane.YES_OPTION) return;
            }

            Grupo grupoExcluir = grupos.get(escolha);
            ParticipanteDAO participanteDAO = new ParticipanteDAO();
            for (Participante p : grupoExcluir.getParticipantes()) {
                participanteDAO.deletar(p.getNome());
            }

            GrupoDAO dao = new GrupoDAO();
            dao.deletar(nomeExcluir);
            grupos.remove(escolha);

            JOptionPane.showMessageDialog(null, "Grupo \"" + nomeExcluir + "\" excluído!");
        });
    }

    public static void main(String[] args) {
        new ParticipanteDAO().criarTabela();
        new ApostaDAO().criarTabela();
        new GrupoDAO().criarTabela();
        new CampeonatoDAO().criarTabela();

        new TelaPrincipal().setVisible(true);
    }
}