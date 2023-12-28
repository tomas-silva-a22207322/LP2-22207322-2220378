package pt.ulusofona.lp2.deisichess;

import java.util.ArrayList;

public class GameInfo {
    private ArrayList<Tabuleiro> estadosAnteriores;
    private Tabuleiro estadoAtual;

    public GameInfo() {
        estadosAnteriores = new ArrayList<>();
        estadoAtual = null;
    }
//
    public ArrayList<Tabuleiro> getEstadosAnteriores() {
        return estadosAnteriores;
    }

    public void setEstadosAnteriores(ArrayList<Tabuleiro> estadosAnteriores) {
        this.estadosAnteriores = estadosAnteriores;
    }

    public Tabuleiro getEstadoAtual() {
        return estadoAtual;
    }

    public void setEstadoAtual(Tabuleiro estadoAtual) {
        this.estadoAtual = estadoAtual;
    }
}
