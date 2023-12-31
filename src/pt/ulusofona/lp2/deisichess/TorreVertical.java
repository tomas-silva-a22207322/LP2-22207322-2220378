package pt.ulusofona.lp2.deisichess;

public class TorreVertical extends Peca{
    public TorreVertical(int id, int tipo, int equipa, String nome) {
        super(id, tipo, equipa, nome);
        pontuacao = 3;
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

    public String getTipoNome() {return "TorreVert";}

    @Override
    public Peca copy() {
        Peca peca =  new TorreVertical(getId(),getTipo(),getEquipa(),getNome());
        peca.setX(getX());
        peca.setY(getY());
        peca.setCapturado(isCapturado());
        peca.setCapturas(getCapturas());
        peca.setPontuacaoCapturas(getPontuacaoCapturas());
        peca.setJogadasValidas(getJogadasValidas());
        peca.setJogadasInvalidas(getJogadasInvalidas());
        return peca;
    }
}