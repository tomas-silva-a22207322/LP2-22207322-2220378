package pt.ulusofona.lp2.deisichess;

public class TorreVertical extends Peca{
    int pontuacao = 3;

    public TorreVertical(int id, int tipo, int equipa, String nome) {
        super(id, tipo, equipa, nome);
    }

    @Override
    public boolean isValidMove(int x0, int y0, int x1, int y1, int turno, Tabuleiro tabuleiro) {
        if (x0 == x1){
            int minY = Math.min(y0, y1);
            int maxY = Math.max(y0, y1);

            for (int y = minY + 1; y < maxY; y++) {
                if (tabuleiro.getPecabyPosicao(x0, y) != null) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public String getTipoNome() {return "TorreVer";}

}