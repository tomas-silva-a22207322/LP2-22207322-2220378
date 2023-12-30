package pt.ulusofona.lp2.deisichess;

import java.util.ArrayList;

public class GameInfo {
    private ArrayList<Tabuleiro> tabuleiros;
    private Tabuleiro tabuleiroInicial;
    private ArrayList<String> movimentos;

    public GameInfo(Tabuleiro tabuleiroInicial) {
        Tabuleiro novoTabuleiro = tabuleiroInicial.copy();
        this.tabuleiroInicial = novoTabuleiro;
        tabuleiros = new ArrayList<>();
        tabuleiros.add(novoTabuleiro);
        movimentos = new ArrayList<>();
    }
    public Tabuleiro undo(){
        this.tabuleiros.remove(tabuleiros.size() - 1);
        this.movimentos.remove(movimentos.size() - 1);

        return tabuleiros.get(tabuleiros.size() - 1).copy();
    }
    public void addMove(String move, Tabuleiro tabuleiro) {
        this.tabuleiros.add(tabuleiro);
        this.movimentos.add(move);
    }

    public ArrayList<Tabuleiro> getTabuleiros() {
        return tabuleiros;
    }
    public void setTabuleiros(ArrayList<Tabuleiro> Tabuleiros) {this.tabuleiros = Tabuleiros;}
    public Tabuleiro TabuleiroInicial() {
        return tabuleiroInicial;
    }
    public void setTabuleiroInicial(Tabuleiro TabuleiroInicial) {
        this.tabuleiroInicial = TabuleiroInicial;
    }
}
