package pt.ulusofona.lp2.deisichess;

public class Rainha extends Peca{


    public Rainha(int id, int tipo, int equipa, String nome) {
        super(id, tipo, equipa, nome);
    }

    @Override
    public boolean isValidMove(int x0, int y0, int x1, int y1, int turno, Tabuleiro tabuleiro) {
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);

        return (dx <= 5 && dy <= 5 && (dx == 0 || dy == 0 || dx == dy));
    }
}
