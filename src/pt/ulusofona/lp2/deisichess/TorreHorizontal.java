package pt.ulusofona.lp2.deisichess;

public class TorreHorizontal extends Peca{

    public TorreHorizontal(int id, int tipo, int equipa, String nome) {
        super(id, tipo, equipa, nome);
        pontuacao = 3;

    }

    @Override
    public boolean isValidMove(int x0, int y0, int x1, int y1, int turno, Tabuleiro tabuleiro) {
        if (y0 == y1){
            int minX = Math.min(x0, x1);
            int maxX = Math.max(x0, x1);

            for (int x = minX + 1; x < maxX; x++) {
                if (tabuleiro.getPecabyPosicao(x, y0) != null) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public String getTipoNome() {return "TorreHor";}

    @Override
    public Peca copy() {
        Peca peca =  new TorreHorizontal(getId(),getTipo(),getEquipa(),getNome());
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