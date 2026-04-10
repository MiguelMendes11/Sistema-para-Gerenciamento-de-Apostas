import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TelaPrincipal extends JFrame {

    private ArrayList<Grupo> grupos = new ArrayList<>();
    private ArrayList<Partida> partidas = new ArrayList<>();
    private ArrayList<Aposta> apostas = new ArrayList<>();
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
        setTitle("Sistema de Apostas");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(9, 1));

        partidas.add(new Partida(c1, c2, "10/04/2026", "20:00"));
        partidas.add(new Partida(c3, c4, "11/04/2026", "18:00"));
        partidas.add(new Partida(c5, c6, "12/04/2026", "16:00"));
        partidas.add(new Partida(c7, c8, "13/04/2026", "21:00"));

        JButton b1 = new JButton("Criar Campeonato");
        JButton b2 = new JButton("Criar Grupo");
        JButton b3 = new JButton("Cadastrar Participante");
        JButton b4 = new JButton("Fazer Aposta");
        JButton b5 = new JButton("Registrar Resultado");
        JButton b6 = new JButton("Ver Classificação");
        JButton b7 = new JButton("Ver Partidas");
        JButton b8 = new JButton("Finalizar Partida");

        add(b1);
        add(b2);
        add(b3);
        add(b4);
        add(b5);
        add(b6);
        add(b7);
        add(b8);

        // botão 1
        b1.addActionListener(e -> {
            String nome = JOptionPane.showInputDialog("Nome do campeonato:");
            if (nome == null || nome.trim().isEmpty()) return;

            camp = new Campeonato(nome);

            camp.adicionarClube(c1);
            camp.adicionarClube(c2);
            camp.adicionarClube(c3);
            camp.adicionarClube(c4);
            camp.adicionarClube(c5);
            camp.adicionarClube(c6);
            camp.adicionarClube(c7);
            camp.adicionarClube(c8);

            String msg = "Campeonato criado: " + nome + "\n\nClubes:\n"
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

            grupos.add(new Grupo(nome));
            JOptionPane.showMessageDialog(null, "Grupo criado!");
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

            g.adicionarParticipante(new Participante(nome));
            JOptionPane.showMessageDialog(null, "Adicionado!");
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

            String[] listaPartidas = new String[partidas.size()];
            for (int i = 0; i < partidas.size(); i++) {
                listaPartidas[i] = partidas.get(i).getInfo();
            }

            int partIndex = JOptionPane.showOptionDialog(
                    null, "Escolha a partida:", "Partidas",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, listaPartidas, listaPartidas[0]);

            if (partIndex == -1) return;

            try {
                String pcStr = JOptionPane.showInputDialog("Gols casa:");
                String pvStr = JOptionPane.showInputDialog("Gols visitante:");

                if (pcStr == null || pvStr == null) return;

                int pc = Integer.parseInt(pcStr);
                int pv = Integer.parseInt(pvStr);

                Aposta aposta = new Aposta(
                        g.getParticipantes().get(pIndex),
                        partidas.get(partIndex),
                        pc,
                        pv
                );

                apostas.add(aposta);

                JOptionPane.showMessageDialog(null, "Aposta registrada!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro!");
            }
        });

        // botão 5
        b5.addActionListener(e -> {
            if (partidas.isEmpty()) return;

            String[] lista = new String[partidas.size()];
            for (int i = 0; i < partidas.size(); i++) {
                lista[i] = partidas.get(i).getInfo();
            }

            int index = JOptionPane.showOptionDialog(
                    null, "Escolha a partida:", "Resultado",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, lista, lista[0]);

            if (index == -1) return;

            Partida p = partidas.get(index);

            if (!p.isFinalizada()) {
                JOptionPane.showMessageDialog(null, "Finalize antes!");
                return;
            }

            try {
                String gcStr = JOptionPane.showInputDialog("Gols casa:");
                String gvStr = JOptionPane.showInputDialog("Gols visitante:");

                if (gcStr == null || gvStr == null) return;

                int gc = Integer.parseInt(gcStr);
                int gv = Integer.parseInt(gvStr);

                p.setResultado(gc, gv);

                for (Aposta a : apostas) {
                    if (a.getPartida() == p) {
                        a.calcularPontos();
                    }
                }

                JOptionPane.showMessageDialog(
                        null,
                        "Resultado registrado!\nPlacar: " + p.getPlacar()
                );
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

            JOptionPane.showMessageDialog(null,
                    grupos.get(index).getClassificacao());
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
            if (partidas.isEmpty()) return;

            String[] lista = new String[partidas.size()];
            for (int i = 0; i < partidas.size(); i++) {
                lista[i] = partidas.get(i).getInfo();
            }

            int index = JOptionPane.showOptionDialog(
                    null,
                    "Escolha a partida que deseja finalizar:",
                    "Finalizar Partida",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    lista,
                    lista[0]);

            if (index == -1) return;

            Partida p = partidas.get(index);

            if (p.isFinalizada()) {
                JOptionPane.showMessageDialog(null, "Essa partida já foi finalizada!");
            } else {
                p.finalizarPartida();
                JOptionPane.showMessageDialog(null, "Partida finalizada!");
            }
        });
    }

    public static void main(String[] args) {
        new TelaPrincipal().setVisible(true);
    }
}