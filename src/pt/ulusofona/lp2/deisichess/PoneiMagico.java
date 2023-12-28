package pt.ulusofona.lp2.deisichess;

import java.util.Objects;

public class PoneiMagico extends Peca {


    public PoneiMagico(int id, int tipo, int equipa, String nome) {
        super(id, tipo, equipa, nome);
    }

    @Override
    public boolean isValidMove(int x0, int y0, int x1, int y1, int turno, Tabuleiro tabuleiro) {
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);

        if (dx == dy && dx == 2) {

            int midX = x0 + dx / 2;
            int midY = y0 + dy / 2;

            boolean validPathXY = tabuleiro.getPecabyPosicao(midX, y0) == null &&
                    tabuleiro.getPecabyPosicao(x1, y0) == null &&
                    tabuleiro.getPecabyPosicao(x1, midY) == null;
            boolean validPathYX = tabuleiro.getPecabyPosicao(x0, midY) == null &&
                    tabuleiro.getPecabyPosicao(x0, y1) == null &&
                    tabuleiro.getPecabyPosicao(midX, y1) == null;

            return validPathXY || validPathYX;
        }
        return false;
    }
}