package pt.ulusofona.lp2.deisichess;

public class TorreVertical extends Peca{
    public TorreVertical(int id, int tipo, int equipa, String nome) {
        super(id, tipo, equipa, nome);
        pontuacao = 3;
    }

    @Override
    public boolean isValidMove(int x0, int y0, int x1, int y1, int turno, Tabuleiro tabuleiro) {
        int dx = x1 - x0;
        int dy = Math.abs(y1 - y0);
        int y = Integer.compare(y1, y0);
        int ymid = y0;

        if (dx == 0 && dy < tabuleiro.getDimensao() && dy > 0){
            do{
                ymid += y;
                if(tabuleiro.getPecabyPosicao(x1,ymid) != null){
                    return false;
                }
            }while(ymid != y1 - y);

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