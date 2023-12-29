package pt.ulusofona.lp2.deisichess;

public class HomerSimpson extends Peca{
    int pontuacao = 2;

    public HomerSimpson(int id, int tipo, int equipa, String nome) {
        super(id, tipo, equipa, nome);
    }

    @Override
    public boolean isValidMove(int x0, int y0, int x1, int y1, int turno, Tabuleiro tabuleiro) {
        if (turno % 3 == 0) {
            return false; // Homer Simpson está dormindo neste turno
        }

        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);

        return (dx == 1 && dy == 1); // Movimento em diagonal de uma casa
    }

    public String getTipoNome() {return "Homer Simpson";}
}