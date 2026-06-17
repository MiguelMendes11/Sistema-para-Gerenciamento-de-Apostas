import java.util.ArrayList;
import java.util.List;

public class Campeonato {

    private static final int LIMITE_CLUBES = 8;

    private String nome;
    private List<Clube> clubes;

    public Campeonato(String nome) {
        this.nome = nome;
        this.clubes = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public List<Clube> getClubes() {
        return clubes;
    }

    public void adicionarClube(Clube clube) {

        if (clubes.size() < LIMITE_CLUBES) {
            clubes.add(clube);
        }
    }
}