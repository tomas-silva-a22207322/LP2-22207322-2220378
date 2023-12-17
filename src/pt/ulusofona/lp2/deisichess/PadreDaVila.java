package pt.ulusofona.lp2.deisichess;

public class PadreDaVila extends Peca{
    int pontuacao = 3;

    public PadreDaVila(int id, int tipo, int equipa, String nome) {
        super(id, tipo, equipa, nome);
    }

    @Override
    public boolean isValidMove(int x0, int y0, int x1, int y1, int turno, Tabuleiro tabuleiro) {
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);

        if (dx == dy && dx <= 3) {
            int xCompare = Integer.compare(x1, x0);
            int yCompare = Integer.compare(y1, y0);

            x0 += xCompare;
            y0 += yCompare;

            for (int i = 1; i < dx; i++) {
                int x = x0 + i * xCompare;
                int y = y0 + i * yCompare;

                if (tabuleiro.getPecabyPosicao(x, y) != null) {
                    return false;
                }
            }
        }

        return true;
    }
}