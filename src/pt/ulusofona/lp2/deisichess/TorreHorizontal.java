package pt.ulusofona.lp2.deisichess;

public class TorreHorizontal extends Peca{

    int pontuacao = 3;
    public TorreHorizontal(int id, int tipo, int equipa, String nome) {
        super(id, tipo, equipa, nome);
    }

    @Override
    public boolean isValidMove(int x0, int y0, int x1, int y1, int turno, Tabuleiro tabuleiro) {
        return (y0 == y1);
    }
}
