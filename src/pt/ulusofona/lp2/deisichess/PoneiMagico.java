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
        boolean pathyxValid = isPathValid(x0, y0, x1, y0, tabuleiro, "eixoY eixoX");

        // Verificação do caminho no eixo y primeiro e depois no eixo x
        boolean pathxyValid = isPathValid(x0, y0, x0, y1, tabuleiro, "eixoX eixoY");

        return pathyxValid || pathxyValid;
    }

    private boolean isPathValid(int x0, int y0, int x1, int y1, Tabuleiro tabuleiro, String direcao) {
        
        int compareX = Integer.compare(x1, x0);
        int compareY = Integer.compare(y1, y0);
        
        int x;
        int y;
        
        if(Objects.equals(direcao, "eixoX eixoY")){
            x = x0 + compareX;
            y = y0;
            
            while(x != x0){
                if (tabuleiro.getPecabyPosicao(x, y) != null) {
                    return false;
                }
                x += compareX;
            }

            return tabuleiro.getPecabyPosicao(x, y+compareY) != null;
        }
        if(Objects.equals(direcao, "eixoY eixoX")){
            x = x0;
            y = y0 + compareY;

            while(y != y0){
                if (tabuleiro.getPecabyPosicao(x, y) != null) {
                    return false;
                }
                y += compareY;
            }

            return tabuleiro.getPecabyPosicao(x + compareX, y) != null;
        }
        return false;
    }
}
