package pt.ulusofona.lp2.deisichess;

public class Peca {
    int id;
    int tipo;
    int equipa;
    String nome;
    //guardar a posicao na peca?
    int x;
    int y;

    boolean capturado;

    public Peca(int id, int tipo, int equipa, String nome) {
        this.id = id;
        this.tipo = tipo;
        this.equipa = equipa;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public boolean isValidMove(int x0, int y0, int x1, int y1) {
        //validMove do rei
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);

        return (dx == 1 && dy == 0) || (dx == 0 && dy == 1) || (dx == 1 && dy == 1);
    }
}