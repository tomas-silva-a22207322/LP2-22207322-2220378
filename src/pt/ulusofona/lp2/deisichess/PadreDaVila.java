package pt.ulusofona.lp2.deisichess;

public class PadreDaVila extends Peca{
    int pontuacao = 3;

    public PadreDaVila(int id, int tipo, int equipa, String nome) {
        super(id, tipo, equipa, nome);
    }

    @Override
    public boolean isValidMove(int x0, int y0, int x1, int y1, int turno) {
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);

        return (dx == dy && dx <= 3);
    }
}
