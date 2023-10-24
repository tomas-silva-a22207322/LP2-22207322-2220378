package pt.ulusofona.lp2.deisichess;

public class Peca {
    private int id;
    private int tipo;
    private int cor;
    private String nome;


    public Peca(int id, int tipo, int cor, String nome) {
        this.id = id;
        this.tipo = tipo;
        this.cor = cor;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }
}