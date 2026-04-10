class Aposta {
    private Participante participante;
    private Partida partida;
    private int palpiteCasa;
    private int palpiteVisitante;
    private boolean pontuada;

    public Aposta(Participante participante, Partida partida, int palpiteCasa, int palpiteVisitante) {
        this.participante = participante;
        this.partida = partida;
        this.palpiteCasa = palpiteCasa;
        this.palpiteVisitante = palpiteVisitante;
        this.pontuada = false;
    }

    public Participante getParticipante() {
        return participante;
    }

    public Partida getPartida() {
        return partida;
    }

    public int getPalpiteCasa() {
        return palpiteCasa;
    }

    public int getPalpiteVisitante() {
        return palpiteVisitante;
    }

    public void calcularPontos() {
        if (pontuada) return;

        int resultadoReal = partida.getResultado();

        if (resultadoReal == -1) return;

        int resultadoPalpite;

        if (palpiteCasa > palpiteVisitante) {
            resultadoPalpite = 1;
        } else if (palpiteCasa < palpiteVisitante) {
            resultadoPalpite = 2;
        } else {
            resultadoPalpite = 0;
        }

        if (resultadoPalpite == resultadoReal) {
            if (palpiteCasa == partida.getGolsCasa() &&
                    palpiteVisitante == partida.getGolsVisitante()) {

                participante.adicionarPontos(10);

            } else {
                participante.adicionarPontos(5);
            }
        }

        pontuada = true;
    }
}