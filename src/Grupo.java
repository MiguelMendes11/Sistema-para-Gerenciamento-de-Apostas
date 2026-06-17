import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Grupo {
    private static final int LIMITE_PARTICIPANTES = 5;

    private String nome;
    private List<Participante> participantes;

    public Grupo(String nome) {
        this.nome = nome;
        this.participantes = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public List<Participante> getParticipantes() {
        return participantes;
    }

    public void adicionarParticipante(Participante p) {
        if (participantes.size() < LIMITE_PARTICIPANTES) {
            participantes.add(p);
        }
    }

    public String getClassificacao() {
        if (participantes.isEmpty()) {
            return "Nenhum participante no grupo.";
        }

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
}