package pt.ulusofona.lp2.deisichess;

public class TorreHorizontal extends Peca{

    public TorreHorizontal(int id, int tipo, int equipa, String nome) {
        super(id, tipo, equipa, nome);
        pontuacao = 3;

    }

    @Override
    public boolean isValidMove(int x0, int y0, int x1, int y1, int turno, Tabuleiro tabuleiro) {
        int dy = y1 - y0;
        int dx = Math.abs(x1 - x0);
        int x = Integer.compare(x1, x0);
        int xmid = x0;

        if (dy == 0 && dx < tabuleiro.getDimensao() && dx > 0){
            do{
                xmid += x;
                if(tabuleiro.getPecabyPosicao(xmid,y1) != null){
                    return false;
                }
            }while(xmid != x1 - x);

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