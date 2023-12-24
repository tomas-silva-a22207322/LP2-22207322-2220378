package pt.ulusofona.lp2.deisichess;

public abstract class Peca {
    int id;
    int tipo;
    int equipa;
    String nome;
    int x;
    int y;
    boolean capturado;
    int capturas = 0;
    int pontuacao;
    int pontuacaoCapturas = 0;
    int jogadasInvalidas = 0;
    int jogadasValidas = 0;

    public Peca(int id, int tipo, int equipa, String nome) {
        this.id = id;
        this.tipo = tipo;
        this.equipa = equipa;
        this.nome = nome;
        this.capturado = true;
    }

    public Peca copy() {
        try {
            return (Peca) super.clone();
        } catch (CloneNotSupportedException e) {
            // Tratar a exceção ou relançá-la como uma exceção de tempo de execução
            throw new RuntimeException("Clone not supported for class Peca", e);
        }
    }


    public boolean verificaPosicoes(int x0, int y0, int x1, int y1, int turno, Tabuleiro tabuleiro) {
        Peca pecaOrigem = tabuleiro.getPecabyPosicao(x0, y0);
        Peca pecaDestino = tabuleiro.getPecabyPosicao(x1, y1);

        if (pecaOrigem != null) {
            if (pecaDestino != null && pecaOrigem.getEquipa() == pecaDestino.getEquipa()) {
                return false; // Mesma equipa, movimento inválido
            }
            pecaOrigem.isValidMove(x0, y0, x1, y1, turno, tabuleiro);
        }
        return false; // Não há peça na posição de origem
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
    public int getCapturas() {return capturas;}
    public int getPontuacao() {return pontuacao;}
    public int getPontuacaoCapturas() {return pontuacaoCapturas;}
    public int getJogadasInvalidas() {return jogadasInvalidas;}
    public int getJogadasValidas() {return jogadasValidas;}

    //SETTERS
    public void setId(int id) {this.id = id;}
    public void setTipo(int tipo) {this.tipo = tipo;}
    public void setEquipa(int equipa) {this.equipa = equipa;}
    public void setNome(String nome) {this.nome = nome;}
    public void setX(int x) {this.x = x;}
    public void setY(int y) {this.y = y;}
    public void setCapturado(boolean capturado) {this.capturado = capturado;}
    public void setCapturas(int capturas) {this.capturas = capturas;}
    public void setPontuacaoCapturas(int pontuacaoCapturas) {this.pontuacaoCapturas = pontuacaoCapturas;}
    public void setJogadasInvalidas(int jogadasInvalidas) {this.jogadasInvalidas = jogadasInvalidas;}
    public void setJogadasValidas(int jogadasValidas) {this.jogadasValidas = jogadasValidas;}

}
