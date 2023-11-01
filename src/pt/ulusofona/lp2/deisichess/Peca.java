package pt.ulusofona.lp2.deisichess;

public class Peca {
    int id;
    int tipo;
    int equipa;
    String nome;
    int x;
    int y;

    boolean pecaEmJogo;
    boolean capturado;

    public Peca(int id, int tipo, int equipa, String nome) {
        this.id = id;
        this.tipo = tipo;
        this.equipa = equipa;
        this.nome = nome;
        this.pecaEmJogo = false;
    }

    //GETTERS
    public int getId() {return id;}
    public int getTipo() {return tipo;}
    public int getEquipa() {return equipa;}
    public String getNome() {return nome;}
    public int getX() {return x;}
    public int getY() {return y;}
    public boolean isPecaEmJogo() {return pecaEmJogo;}
    public boolean isCapturado() {return capturado;}

    //SETTERS
    public void setId(int id) {this.id = id;}
    public void setTipo(int tipo) {this.tipo = tipo;}
    public void setEquipa(int equipa) {this.equipa = equipa;}
    public void setNome(String nome) {this.nome = nome;}
    public void setX(int x) {this.x = x;}
    public void setY(int y) {this.y = y;}
    public void setPecaEmJogo(boolean pecaEmJogo) {this.pecaEmJogo = pecaEmJogo;}
    public void setCapturado(boolean capturado) {this.capturado = capturado;}


}