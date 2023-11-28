package pt.ulusofona.lp2.deisichess;

public class PoneiMagico extends Peca{

    int pontuacao = 5;

    public PoneiMagico(int id, int tipo, int equipa, String nome) {
        super(id, tipo, equipa, nome);
    }

    @Override
    public boolean isValidMove(int x0, int y0, int x1, int y1, int turno) {
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);

        return (dx == 2 && dy == 2);
    }
}
