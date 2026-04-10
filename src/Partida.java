import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Partida {
    private Clube casa;
    private Clube visitante;
    private int golsCasa;
    private int golsVisitante;
    private boolean finalizada;
    private boolean resultadoRegistrado; // 🔥 evita duplicar resultado

    private LocalDateTime dataHora;

    public Partida(Clube casa, Clube visitante, String data, String hora) {
        this.casa = casa;
        this.visitante = visitante;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        this.dataHora = LocalDateTime.parse(data + " " + hora, formatter);

        this.finalizada = false;
        this.resultadoRegistrado = false;
    }

    public boolean podeApostar() {
        LocalDateTime agora = LocalDateTime.now();
        return agora.isBefore(dataHora.minusMinutes(20));
    }

    public boolean podeRegistrarResultado() {
        return finalizada && !resultadoRegistrado;
    }


    public void finalizarPartida() {
        if (this.finalizada) {
            System.out.println("Partida já foi finalizada!");
            return;
        }
        this.finalizada = true;
    }

    public void setResultado(int golsCasa, int golsVisitante) {

        if (!this.finalizada) {
            System.out.println("Finalize a partida antes de registrar o resultado!");
            return;
        }

        if (resultadoRegistrado) {
            System.out.println("Resultado já foi registrado!");
            return;
        }

        this.golsCasa = golsCasa;
        this.golsVisitante = golsVisitante;
        this.resultadoRegistrado = true;
    }

    public int getResultado() {
        if (!resultadoRegistrado) {
            System.out.println("Resultado ainda não registrado!");
            return -1;
        }

        if (golsCasa > golsVisitante) return 1;
        if (golsCasa < golsVisitante) return 2;
        return 0;
    }

    public int getGolsCasa() {
        return golsCasa;
    }

    public int getGolsVisitante() {
        return golsVisitante;
    }

    public boolean isFinalizada() {
        return finalizada;
    }

    public boolean isResultadoRegistrado() {
        return resultadoRegistrado;
    }

    public String getPlacar() {
        if (!resultadoRegistrado) {
            return casa.getNome() + " x " + visitante.getNome() + " (sem resultado)";
        }

        return casa.getNome() + " " + golsCasa + " x " + golsVisitante + " " + visitante.getNome();
    }

    public String getInfo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        String status;
        if (!finalizada) {
            status = "AGENDADA";
        } else if (!resultadoRegistrado) {
            status = "FINALIZADA (sem resultado)";
        } else {
            status = "FINALIZADA";
        }

        return casa.getNome() + " x " + visitante.getNome() +
                " | " + dataHora.format(formatter) +
                " | " + status;
    }
}