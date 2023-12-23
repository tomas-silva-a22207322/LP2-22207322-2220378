package pt.ulusofona.lp2.deisichess;

public class ComparableClass<T extends Comparable<T>> implements Comparable<ComparableClass<T>> {
    int x;
    int y;
    int pontuacao;

    public ComparableClass(int x, int y, int pontuacao) {
        this.x = x;
        this.y = y;
        this.pontuacao = pontuacao;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    @Override
    public int compareTo(ComparableClass move){
        return Integer.compare(move.pontuacao, getPontuacao());
    }

    @Override
    public String toString() {
        return "ComparableClass{" +
                "x=" + x +
                ", y=" + y +
                ", pontuacao=" + pontuacao +
                '}';
    }
}
