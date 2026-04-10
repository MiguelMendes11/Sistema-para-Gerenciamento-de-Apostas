import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // CLUBES
        Clube c1 = new Clube("Palmeiras");
        Clube c2 = new Clube("Corinthians");
        Clube c3 = new Clube("São Paulo");
        Clube c4 = new Clube("Santos");
        Clube c5 = new Clube("Flamengo");
        Clube c6 = new Clube("Vasco");
        Clube c7 = new Clube("Grêmio");
        Clube c8 = new Clube("Internacional");

        // CAMPEONATO
        Campeonato camp = null;

        // PARTIDAS
        ArrayList<Partida> partidas = new ArrayList<>();

        partidas.add(new Partida(c1, c2, "10/04/2026", "20:00"));
        partidas.add(new Partida(c3, c4, "11/04/2026", "18:00"));
        partidas.add(new Partida(c5, c6, "12/04/2026", "16:00"));
        partidas.add(new Partida(c7, c8, "13/04/2026", "21:00"));

        ArrayList<Grupo> grupos = new ArrayList<>();

        Aposta aposta = null;

        int opcao;

        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1 - Criar campeonato");
            System.out.println("2 - Criar grupo");
            System.out.println("3 - Cadastrar participante em grupo");
            System.out.println("4 - Fazer aposta");
            System.out.println("5 - Registrar resultado");
            System.out.println("6 - Ver classificação do grupo");
            System.out.println("7 - Ver partidas");
            System.out.println("8 - Finalizar partida");
            System.out.println("0 - Sair");

            opcao = sc.nextInt();

            switch (opcao) {

                case 1:
                    System.out.print("Nome do campeonato: ");
                    sc.nextLine();
                    String nomeCamp = sc.nextLine();

                    camp = new Campeonato(nomeCamp);

                    camp.adicionarClube(c1);
                    camp.adicionarClube(c2);
                    camp.adicionarClube(c3);
                    camp.adicionarClube(c4);
                    camp.adicionarClube(c5);
                    camp.adicionarClube(c6);
                    camp.adicionarClube(c7);
                    camp.adicionarClube(c8);

                    System.out.println("Campeonato criado!");
                    break;

                case 2:
                    if (camp == null) {
                        System.out.println("Crie o campeonato primeiro!");
                        break;
                    }

                    if (grupos.size() < 5) {
                        System.out.print("Nome do grupo: ");
                        sc.nextLine();
                        String nomeGrupo = sc.nextLine();

                        Grupo novoGrupo = new Grupo(nomeGrupo);
                        grupos.add(novoGrupo);

                        System.out.println("Grupo criado!");
                    } else {
                        System.out.println("Limite de 5 grupos atingido!");
                    }
                    break;

                case 3:
                    if (camp == null) {
                        System.out.println("Crie o campeonato primeiro!");
                        break;
                    }

                    if (grupos.isEmpty()) {
                        System.out.println("Crie um grupo primeiro!");
                        break;
                    }

                    System.out.println("Escolha o grupo:");
                    for (int i = 0; i < grupos.size(); i++) {
                        System.out.println(i + " - " + grupos.get(i).getNome());
                    }

                    int gIndex = sc.nextInt();
                    Grupo grupoEscolhido = grupos.get(gIndex);

                    System.out.print("Nome do participante: ");
                    sc.nextLine();
                    String nome = sc.nextLine();

                    Participante participante = new Participante(nome);
                    grupoEscolhido.adicionarParticipante(participante);
                    break;

                case 4:
                    if (camp == null) {
                        System.out.println("Crie o campeonato primeiro!");
                        break;
                    }

                    if (grupos.isEmpty()) {
                        System.out.println("Crie um grupo primeiro!");
                        break;
                    }

                    // escolher grupo
                    System.out.println("Escolha o grupo:");
                    for (int i = 0; i < grupos.size(); i++) {
                        System.out.println(i + " - " + grupos.get(i).getNome());
                    }

                    int gAposta = sc.nextInt();
                    Grupo grupoAposta = grupos.get(gAposta);

                    if (grupoAposta.getParticipantes().isEmpty()) {
                        System.out.println("Grupo sem participantes!");
                        break;
                    }

                    // escolher participante
                    System.out.println("Escolha o participante:");
                    for (int i = 0; i < grupoAposta.getParticipantes().size(); i++) {
                        System.out.println(i + " - " + grupoAposta.getParticipantes().get(i).getNome());
                    }

                    int pEscolhido = sc.nextInt();
                    Participante participanteEscolhido = grupoAposta.getParticipantes().get(pEscolhido);

                    // escolher partida
                    System.out.println("Escolha a partida:");
                    for (int i = 0; i < partidas.size(); i++) {
                        System.out.println(i + " - Partida " + (i + 1));
                    }

                    int pIndex = sc.nextInt();
                    Partida partidaEscolhida = partidas.get(pIndex);

                    if (!partidaEscolhida.podeApostar()) {
                        System.out.println("Aposta não permitida!");
                        break;
                    }

                    System.out.print("Palpite gols casa: ");
                    int pc = sc.nextInt();

                    System.out.print("Palpite gols visitante: ");
                    int pv = sc.nextInt();

                    aposta = new Aposta(participanteEscolhido, partidaEscolhida, pc, pv);
                    System.out.println("Aposta registrada!");
                    break;

                case 5:
                    if (camp == null) {
                        System.out.println("Crie o campeonato primeiro!");
                        break;
                    }

                    System.out.println("Escolha a partida:");
                    for (int i = 0; i < partidas.size(); i++) {
                        System.out.println(i + " - Partida " + (i + 1));
                    }

                    int rIndex = sc.nextInt();
                    Partida partidaRes = partidas.get(rIndex);

                    if (!partidaRes.isFinalizada()) {
                        System.out.println("Finalize a partida antes!");
                        break;
                    }

                    System.out.print("Gols casa: ");
                    int rc = sc.nextInt();

                    System.out.print("Gols visitante: ");
                    int rv = sc.nextInt();

                    partidaRes.setResultado(rc, rv);

                    if (aposta != null) {
                        aposta.calcularPontos();
                    }

                    System.out.println("Resultado registrado!");
                    break;

                case 6:
                    if (grupos.isEmpty()) {
                        System.out.println("Nenhum grupo criado!");
                        break;
                    }

                    System.out.println("Escolha o grupo:");
                    for (int i = 0; i < grupos.size(); i++) {
                        System.out.println(i + " - " + grupos.get(i).getNome());
                    }

                    int verIndex = sc.nextInt();
                    grupos.get(verIndex).mostrarClassificacao();
                    break;

                case 7:
                    System.out.println("\n--- PARTIDAS ---");
                    for (int i = 0; i < partidas.size(); i++) {
                        System.out.println((i + 1) + " - " + partidas.get(i).getInfo());
                    }
                    break;

                case 8:
                    System.out.println("Escolha a partida para finalizar:");
                    for (int i = 0; i < partidas.size(); i++) {
                        System.out.println(i + " - Partida " + (i + 1));
                    }

                    int fIndex = sc.nextInt();
                    partidas.get(fIndex).finalizarPartida();

                    System.out.println("Partida finalizada!");
                    break;

                case 0:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);

        sc.close();
    }
}