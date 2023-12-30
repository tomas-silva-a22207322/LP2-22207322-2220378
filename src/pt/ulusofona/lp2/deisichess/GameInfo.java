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
        if(tabuleiros.size() > 1) {
            this.tabuleiros.remove(tabuleiros.size() - 1);
            this.movimentos.remove(movimentos.size() - 1);
            return tabuleiros.get(tabuleiros.size() - 1).copy();
        }else{
            return tabuleiroInicial.copy();
        }
    }
    public void addMove(String move, Tabuleiro tabuleiro) {
        this.tabuleiros.add(tabuleiro);
        this.movimentos.add(move);
    }

    public ArrayList<Tabuleiro> getTabuleiros() {
        return tabuleiros;
    }
    public Tabuleiro getTabuleiroInicial() {return tabuleiroInicial.copy();}
    public ArrayList<String> getMovimentos() {return movimentos;}
    public void setTabuleiros(ArrayList<Tabuleiro> Tabuleiros) {this.tabuleiros = Tabuleiros;}
    public void setTabuleiroInicial(Tabuleiro TabuleiroInicial) {
        this.tabuleiroInicial = TabuleiroInicial;
    }
    public void setMovimentos(ArrayList<String> movimentos) {this.movimentos = movimentos;}
}
