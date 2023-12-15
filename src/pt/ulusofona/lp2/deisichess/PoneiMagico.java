package pt.ulusofona.lp2.deisichess;

public class PoneiMagico extends Peca{


    public PoneiMagico(int id, int tipo, int equipa, String nome) {
        super(id, tipo, equipa, nome);
    }

    @Override
    public boolean isValidMove(int x0, int y0, int x1, int y1, int turno, Tabuleiro tabuleiro) {
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);

        // Verificação do caminho no eixo x primeiro e depois no eixo y
        boolean path1Valid = isPathValid(x0, y0, x1, y0, x1, y1, tabuleiro);

        // Verificação do caminho no eixo y primeiro e depois no eixo x
        boolean path2Valid = isPathValid(x0, y0, x0, y1, x1, y1, tabuleiro);

        return path1Valid || path2Valid;
    }

    // Método para verificar se o caminho entre duas posições é válido
    private boolean isPathValid(int x0, int y0, int x1, int y1, int xFinal, int yFinal, Tabuleiro tabuleiro) {
        int compareX = Integer.compare(x1, x0);
        int compareY = Integer.compare(y1, y0);

        int x = x0 + compareX;
        int y = y0 + compareY;

        while (x != xFinal || y != yFinal) {
            if (tabuleiro.getPecabyPosicao(x, y) != null) {
                return false;
            }

            if (x != xFinal) {
                x += compareX;
            }
            if (y != yFinal) {
                y += compareY;
            }
        }

        return true;
    }
}
