import java.util.ArrayList;

class Campeonato {
    private String nome;
    private ArrayList<Clube> clubes;

    public Campeonato(String nome) {
        this.nome = nome;
        this.clubes = new ArrayList<>();
    }

    public void adicionarClube(Clube clube) {
        if (clubes.size() < 8) {
            clubes.add(clube);
            System.out.println("Clube adicionado: " + clube.getNome());
        } else {
            System.out.println("Limite de 8 clubes atingido!");
        }
    }

    public void listarClubes() {
        System.out.println("Clubes do campeonato:");
        for (Clube c : clubes) {
            System.out.println("- " + c.getNome());
        }
    }
}