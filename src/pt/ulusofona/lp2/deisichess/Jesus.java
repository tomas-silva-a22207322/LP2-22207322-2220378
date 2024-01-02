package pt.ulusofona.lp2.deisichess;

public class Jesus extends Peca{

    public Jesus(int id, int tipo, int equipa, String nome) {
        super(id, tipo, equipa, nome);
        pontuacao = 100;
    }

    @Override
    public boolean isValidMove(int x0, int y0, int x1, int y1, int turno, Tabuleiro tabuleiro) {
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        int y = y1 - y0;

        if (tabuleiro.getPecabyPosicao(x1,y1) != null && tabuleiro.getPecabyPosicao(x1, y1).getTipo() == 3) {
            return false;
        }

        return (dx == 1 && dy == 0) || (dx == 0 && dy == 1) || (dx == 0 && y == 2);
    }

    public String getTipoNome() {return "Jesus";}

    @Override
    public Peca copy() {
        Peca peca =  new Jesus(getId(),getTipo(),getEquipa(),getNome());
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