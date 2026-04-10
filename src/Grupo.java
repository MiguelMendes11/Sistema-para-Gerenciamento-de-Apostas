import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Grupo {
    private String nome;
    private ArrayList<Participante> participantes;

    public Grupo(String nome) {
        this.nome = nome;
        this.participantes = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public ArrayList<Participante> getParticipantes() {
        return participantes;
    }

    public void adicionarParticipante(Participante p) {
        if (participantes.size() < 5) {
            participantes.add(p);
            System.out.println("Participante adicionado!");
        } else {
            System.out.println("Grupo cheio (máx 5 participantes)!");
        }
    }

    public String getClassificacao() {
        if (participantes.isEmpty()) {
            return "Nenhum participante no grupo.";
        }

        // ordenar o ranking
        Collections.sort(participantes, new Comparator<Participante>() {
            @Override
            public int compare(Participante p1, Participante p2) {
                return Integer.compare(p2.getPontos(), p1.getPontos());
            }
        });

        String texto = "--- CLASSIFICAÇÃO DO GRUPO: " + nome + " ---\n";

        for (int i = 0; i < participantes.size(); i++) {
            Participante p = participantes.get(i);
            texto += (i + 1) + "º - " + p.getNome() + " - " + p.getPontos() + " pontos\n";
        }

        return texto;
    }

    public void mostrarClassificacao() {
        System.out.println(getClassificacao());
    }
}