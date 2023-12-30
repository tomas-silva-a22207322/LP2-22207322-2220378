package pt.ulusofona.lp2.deisichess;

public class Joker extends Peca{

    public Joker(int id, int tipo, int equipa, String nome) {
        super(id, tipo, equipa, nome);
        pontuacao = 4;
    }

    @Override
    public boolean isValidMove(int x0, int y0, int x1, int y1, int turno, Tabuleiro tabuleiro) {
        int movePeca = turno % 6;

        Peca peca;

        switch (movePeca) {
            case 0: // Rainha
                peca = new Rainha(getId(),  getTipo(),  getEquipa(),  getNome());
                return peca.isValidMove(x0, y0, x1, y1, turno, tabuleiro);
            case 1: // Ponei Mágico
                peca = new PoneiMagico(getId(),  getTipo(),  getEquipa(),  getNome());
                return peca.isValidMove(x0, y0, x1, y1, turno, tabuleiro);
            case 2: // Padre da Vila
                peca = new PadreDaVila(getId(),  getTipo(),  getEquipa(),  getNome());
                return peca.isValidMove(x0, y0, x1, y1, turno, tabuleiro);
            case 3: // Torre Horizontal
                peca = new TorreHorizontal(getId(),  getTipo(),  getEquipa(),  getNome());
                return peca.isValidMove(x0, y0, x1, y1, turno, tabuleiro);
            case 4: // Torre Vertical
                peca = new TorreVertical(getId(),  getTipo(),  getEquipa(),  getNome());
                return peca.isValidMove(x0, y0, x1, y1, turno, tabuleiro);
            case 5: // Homer Simpson
                peca = new HomerSimpson(getId(),  getTipo(),  getEquipa(),  getNome());
                return peca.isValidMove(x0, y0, x1, y1, turno, tabuleiro);
            default:
                return false;
        }
    }

    @Override
    public Peca copy() {
        Peca peca =  new Joker(getId(),getTipo(),getEquipa(),getNome());
        peca.setX(getX());
        peca.setY(getY());
        peca.setCapturado(isCapturado());
        peca.setCapturas(getCapturas());
        peca.setPontuacaoCapturas(getPontuacaoCapturas());
        peca.setJogadasValidas(getJogadasValidas());
        peca.setJogadasInvalidas(getJogadasInvalidas());
        return peca;
    }
    public String getTipoNome() {return "Joker";}
}