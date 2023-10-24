package pt.ulusofona.lp2.deisichess;

public class Tabuleiro {
    int[][] campoJogo;
    int dimensao;

    public Tabuleiro(int dimensao) {
        this.dimensao = dimensao;
        this.campoJogo = new int[dimensao][dimensao];
    }

    /*public void colocarPeca(int x, int y, Peca peca) {
        campoJogo[x][y] = peca.getId();
    }*/

    public int getDimensao() {
        return dimensao;
    }
}
