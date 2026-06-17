public class Aposta {
    private static final int PONTOS_ACERTO_EXATO = 10;
    private static final int PONTOS_ACERTO_VENCEDOR = 5;

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

    public int calcularPontos() {
        if (pontuada) return 0;

        int resultadoReal = partida.getResultado();

        if (resultadoReal == -1) return 0;

        int resultadoPalpite;

        if (palpiteCasa > palpiteVisitante) {
            resultadoPalpite = 1;
        } else if (palpiteCasa < palpiteVisitante) {
            resultadoPalpite = 2;
        } else {
            resultadoPalpite = 0;
        }

        if (resultadoPalpite == resultadoReal) {
            if (palpiteCasa == partida.getGolsCasa()
                    && palpiteVisitante == partida.getGolsVisitante()) {
                participante.adicionarPontos(PONTOS_ACERTO_EXATO);
                pontuada = true;
                return PONTOS_ACERTO_EXATO;
            } else {
                participante.adicionarPontos(PONTOS_ACERTO_VENCEDOR);
                pontuada = true;
                return PONTOS_ACERTO_VENCEDOR;
            }
        }

        pontuada = true;
        return 0;
    }
}