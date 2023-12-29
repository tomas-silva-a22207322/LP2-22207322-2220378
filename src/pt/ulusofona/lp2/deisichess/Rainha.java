package pt.ulusofona.lp2.deisichess;

public class Rainha extends Peca{

    int pontuacao = 8;
    public Rainha(int id, int tipo, int equipa, String nome) {
        super(id, tipo, equipa, nome);
    }

    @Override
    public boolean isValidMove(int x0, int y0, int x1, int y1, int turno, Tabuleiro tabuleiro) {

        if(tabuleiro.getPecabyPosicao(x1,y1).getTipo() == 1){
            return false;
        }

        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);

        if (dx <= 5 && dy <= 5 && (dx == 0 || dy == 0 || dx == dy)) {
            int xCompare = Integer.compare(x1, x0);
            int yCompare = Integer.compare(y1, y0);

            // Verifica o caminho horizontal
            if (dx > 0 && dy == 0) {
                for (int i = 1; i < dx; i++) {
                    int x = x0 + i * xCompare;
                    if (tabuleiro.getPecabyPosicao(x, y0) != null) {
                        return false;
                    }
                }
            }
            // Verifica o caminho vertical
            else if (dy > 0 && dx == 0) {
                for (int i = 1; i < dy; i++) {
                    int y = y0 + i * yCompare;
                    if (tabuleiro.getPecabyPosicao(x0, y) != null) {
                        return false;
                    }
                }
            }
            // Verifica o caminho diagonal
            else if (dx == dy) {
                int x = x0 + xCompare;
                int y = y0 + yCompare;

                while (x != x1 || y != y1) {
                    if (tabuleiro.getPecabyPosicao(x, y) != null) {
                        return false;
                    }
                    x += xCompare;
                    y += yCompare;
                }
            }

            return true;
        }

        return false;
    }

    public String getTipoNome() {return "Rainha";}

}