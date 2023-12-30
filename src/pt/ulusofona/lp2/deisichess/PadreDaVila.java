package pt.ulusofona.lp2.deisichess;

public class PadreDaVila extends Peca{
    public PadreDaVila(int id, int tipo, int equipa, String nome) {
        super(id, tipo, equipa, nome);
        pontuacao = 3;
    }

    @Override
    public boolean isValidMove(int x0, int y0, int x1, int y1, int turno, Tabuleiro tabuleiro) {
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);

        if (dx == dy && dx <= 3) {
            int xCompare = Integer.compare(x1, x0);
            int yCompare = Integer.compare(y1, y0);

            int x = x0 + xCompare;
            int y = y0 + yCompare;

            while (x != x1 || y != y1) {
                if (tabuleiro.getPecabyPosicao(x, y) != null) {
                    return false; // Verifica se há peças no caminho
                }
                x += xCompare;
                y += yCompare;
            }

            return true; // Retorna verdadeiro se o movimento for válido
        }

        return false;
    }

    public String getTipoNome() {return "Padre da Vila";}

    @Override
    public Peca copy() {
        Peca peca =  new PadreDaVila(getId(),getTipo(),getEquipa(),getNome());
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