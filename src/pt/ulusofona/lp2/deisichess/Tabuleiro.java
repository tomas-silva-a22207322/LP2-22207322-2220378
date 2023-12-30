package pt.ulusofona.lp2.deisichess;
import java.util.HashMap;
import java.util.Map;

public class Tabuleiro {

    Peca[][] campoJogo;
    int dimensao;
    HashMap<Integer, Peca> pecas = new HashMap<>();
    int turno = 0;
    int numeroPecas;
    int jogadasAposCaptura = -1;
    int jogadasValidasPretas = 0;
    int jogadasValidasBrancas = 0;
    int jogadasInvalidasPretas = 0;
    int jogadasInvalidasBrancas = 0;
    int capturasPretas = 0;
    int capturasBrancas = 0;
    int equipaAtual = 10;

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
        Peca[][] campoJogoNovo = new Peca[getDimensao()][getDimensao()];

        for (Map.Entry<Integer, Peca> entry : this.getPecas().entrySet()) {
            int id = entry.getKey();
            Peca originalPeca = entry.getValue();
            Peca copiedPeca = originalPeca.copy();
            copiedTabuleiro.getPecas().put(id, copiedPeca);
        }

        for(int i = 0; i < getDimensao(); i++){
            for(int j = 0; j < getDimensao(); j++){
                if(getPecabyPosicao(i,j) != null){
                    campoJogoNovo[j][i] = copiedTabuleiro.getPecaById(getPecabyPosicao(i,j).getId());
                }
            }
        }

        copiedTabuleiro.setCampoJogo(campoJogoNovo);
        copiedTabuleiro.setTurno(getTurno());
        copiedTabuleiro.setNumeroPecas(getNumeroPecas());
        copiedTabuleiro.setJogadasAposCaptura(getJogadasAposCaptura());
        copiedTabuleiro.setJogadasValidasPretas(getJogadasValidasPretas());
        copiedTabuleiro.setJogadasValidasBrancas(getJogadasValidasBrancas());
        copiedTabuleiro.setJogadasInvalidasPretas(getJogadasInvalidasPretas());
        copiedTabuleiro.setJogadasInvalidasBrancas(getJogadasInvalidasBrancas());
        copiedTabuleiro.setCapturasPretas(getCapturasPretas());
        copiedTabuleiro.setCapturasBrancas(getCapturasBrancas());
        copiedTabuleiro.setEquipaAtual(getEquipaAtual());

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
    public int getNumeroPecas() {return numeroPecas;}
    public int getJogadasAposCaptura() {return jogadasAposCaptura;}
    public int getJogadasValidasPretas() {return jogadasValidasPretas;}
    public int getJogadasValidasBrancas() {return jogadasValidasBrancas;}
    public int getJogadasInvalidasPretas() {return jogadasInvalidasPretas;}
    public int getJogadasInvalidasBrancas() {return jogadasInvalidasBrancas;}
    public int getCapturasPretas() {return capturasPretas;}
    public int getCapturasBrancas() {return capturasBrancas;}
    public int getEquipaAtual() {return equipaAtual;}

    //SETTERS
    public void setCampoJogo(Peca[][] board){
        this.campoJogo = board;
    }
    public void setPecabyPosicao(int x,int y,Peca peca){
        this.campoJogo[y][x] = peca;
    }
    public void setDimensao(int dimensao) {this.dimensao = dimensao;}
    public void setPecas(HashMap<Integer, Peca> pecas) {this.pecas = pecas;}
    public void setTurno(int turno) {this.turno = turno;}
    public void setNumeroPecas(int numeroPecas) {this.numeroPecas = numeroPecas;}
    public void setJogadasAposCaptura(int jogadasAposCaptura) {this.jogadasAposCaptura = jogadasAposCaptura;}
    public void setJogadasValidasPretas(int jogadasValidasPretas) {this.jogadasValidasPretas = jogadasValidasPretas;}
    public void setJogadasValidasBrancas(int jogadasValidasBrancas) {this.jogadasValidasBrancas = jogadasValidasBrancas;}
    public void setJogadasInvalidasPretas(int jogadasInvalidasPretas) {this.jogadasInvalidasPretas = jogadasInvalidasPretas;}
    public void setJogadasInvalidasBrancas(int jogadasInvalidasBrancas) {this.jogadasInvalidasBrancas = jogadasInvalidasBrancas;}
    public void setCapturasPretas(int capturasPretas) {this.capturasPretas = capturasPretas;}
    public void setCapturasBrancas(int capturasBrancas) {this.capturasBrancas = capturasBrancas;}
    public void setEquipaAtual(int equipaAtual) {this.equipaAtual = equipaAtual;}

}