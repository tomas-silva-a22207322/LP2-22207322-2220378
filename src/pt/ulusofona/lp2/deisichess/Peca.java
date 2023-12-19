package pt.ulusofona.lp2.deisichess;

public abstract class Peca {
    int id;
    int tipo;
    int equipa;
    String nome;
    int x;
    int y;
    boolean capturado;
    int pontuacao;

    public Peca(int id, int tipo, int equipa, String nome) {
        this.id = id;
        this.tipo = tipo;
        this.equipa = equipa;
        this.nome = nome;
        this.capturado = true;
    }

    public abstract boolean isValidMove(int x0, int y0, int x1, int y1, int turno, Tabuleiro tabuleiro);

    //GETTERS
    public int getId() {return id;}
    public int getTipo() {return tipo;}
    public int getEquipa() {return equipa;}
    public String getNome() {return nome;}
    public int getX() {return x;}
    public int getY() {return y;}
    public boolean isCapturado() {return capturado;}

    //SETTERS
    public void setId(int id) {this.id = id;}
    public void setTipo(int tipo) {this.tipo = tipo;}
    public void setEquipa(int equipa) {this.equipa = equipa;}
    public void setNome(String nome) {this.nome = nome;}
    public void setX(int x) {this.x = x;}
    public void setY(int y) {this.y = y;}
    public void setCapturado(boolean capturado) {this.capturado = capturado;}


}