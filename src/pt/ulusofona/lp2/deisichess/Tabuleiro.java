package pt.ulusofona.lp2.deisichess;
import java.util.HashMap;
import java.util.Map;

public class Tabuleiro {

    Peca[][] campoJogo;
    int dimensao;
    HashMap<Integer, Peca> pecas = new HashMap<>();
    int turno = 0;

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

    public Tabuleiro copy() {
        Tabuleiro copiedTabuleiro = new Tabuleiro(this.dimensao);

        // Copiar o estado do tabuleiro
        copiedTabuleiro.setPecas(new HashMap<>());

        for (Map.Entry<Integer, Peca> entry : this.pecas.entrySet()) {
            int id = entry.getKey();
            Peca originalPeca = entry.getValue();
            Peca copiedPeca = originalPeca.copy();
            copiedTabuleiro.getPecas().put(id, copiedPeca);
        }

        // Copiar outros campos relevantes
        copiedTabuleiro.setTurno(this.turno);

        return copiedTabuleiro;
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

    public int getTurno() {return turno;}

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
    public void setTurno(int turno) {this.turno = turno;}

}