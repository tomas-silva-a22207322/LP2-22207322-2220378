package pt.ulusofona.lp2.deisichess;

public class Rei extends Peca{
    public Rei(int id, int tipo, int equipa, String nome) {
        super(id, tipo, equipa, nome);
        pontuacao = 1000;
    }

    @Override
    public boolean isValidMove(int x0, int y0, int x1, int y1, int turno, Tabuleiro tabuleiro) {
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);

        return (dx <= 1 || dy <= 1);
    }

    public String getTipoNome() {return "Rei";}

    @Override
    public Peca copy() {
        Peca peca =  new Rei(getId(),getTipo(),getEquipa(),getNome());
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