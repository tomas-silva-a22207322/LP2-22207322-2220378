package pt.ulusofona.lp2.deisichess;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class Tabuleiro {
    int[][] campoJogo;
    int dimensao;
    HashMap<Integer,Peca> pecas = new HashMap<>();


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
    public void colocarPeca(Peca peca) {

        for (int x = 0; x < dimensao; x++) {
            for (int y = 0; y < dimensao; y++) {
                if (campoJogo[x][y] == 0) {
                    campoJogo[x][y] = peca.getId();
                    peca.x = x;
                    peca.y = y;
                    break;
                } else {
                    System.out.println("Posição ocupada");
                }
            }
        }

    }

    public int getDimensao() {
        return dimensao;
    }
}
