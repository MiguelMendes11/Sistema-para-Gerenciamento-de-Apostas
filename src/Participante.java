class Participante extends Pessoa {
    private int pontos;

    public Participante(String nome) {
        super(nome);
        this.pontos = 0;
    }

    public void adicionarPontos(int pontos) {
        this.pontos += pontos;
    }

    public void adicionarPontos() {
        this.pontos += 1;
    }

    public int getPontos() {
        return pontos;
    }
}