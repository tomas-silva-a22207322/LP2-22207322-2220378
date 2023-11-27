package pt.ulusofona.lp2.deisichess;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class Tabuleiro {

    Peca[][] campoJogo;
    int dimensao;
    HashMap<Integer, Peca> pecas = new HashMap<>();

    public Tabuleiro(int dimensao) {
        this.dimensao = dimensao;
        this.campoJogo = new Peca[dimensao][dimensao];
        //inicializar o tabuleiro vazio
        for (int i = 0; i < dimensao; i++) {
            for (int j = 0; j < dimensao; j++) {
                campoJogo[i][j] = null;
            }
        }
    }

    public boolean isValidMove(int x0, int y0, int x1, int y1) {
        Peca pecaOrigem = getPecabyPosicao(x0, y0);
        Peca pecaDestino = getPecabyPosicao(x1, y1);

        if (pecaOrigem != null) {
            if (pecaDestino != null && pecaOrigem.getEquipa() == pecaDestino.getEquipa()) {
                return false; // Mesma equipa, movimento inválido
            }

            return pecaOrigem.isValidMove(x0, y0, x1, y1); // Validação de movimento da peça
        }

        return false; // Não há peça na posição de origem
    }


    //GETTERS
    public int getDimensao() {return dimensao;}

    public Peca[][] getCampoJogo() {return campoJogo;}

    public HashMap<Integer, Peca> getPecas() {return pecas;}
    public Peca getPecaById(int id){
        return pecas.get(id);
    }
    public Peca getPecabyPosicao(int x,int y){
        return campoJogo[y][x];
    }



    //SETTERS
    public void setTabuleiro(Peca[][] board){
        this.campoJogo = board;
    }
    public void setPecabyPosicao(int x,int y,Peca peca){
        this.campoJogo[y][x] = peca;
    }

    public void setDimensao(int dimensao) {this.dimensao = dimensao;}

    public void setCampoJogo(Peca[][] campoJogo) {this.campoJogo = campoJogo;}

    public void setPecas(HashMap<Integer, Peca> pecas) {this.pecas = pecas;}
}