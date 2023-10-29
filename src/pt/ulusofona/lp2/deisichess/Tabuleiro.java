package pt.ulusofona.lp2.deisichess;

public class Tabuleiro {
    int[][] campoJogo;
    int dimensao;

    public Tabuleiro(int dimensao) {
        this.dimensao = dimensao;
        this.campoJogo = new int[dimensao][dimensao];
        //inicializar o tabuleiro
        for (int i = 0; i < dimensao; i++) {
            for (int j = 0; j < dimensao; j++) {
                campoJogo[i][j] = 0;
            }
        }
    }
    //colocarPeca como boolean?
    public void colocarPeca(int x, int y, Peca peca) {

        if (x < 0 || x > dimensao || y < 0 || y > dimensao) {
            System.out.println("Posição inválida.");
        }

        if (campoJogo[x][y] == 0) {
            campoJogo[x][y] = peca.getId();
        }

    }

    public int getDimensao() {
        return dimensao;
    }
}
