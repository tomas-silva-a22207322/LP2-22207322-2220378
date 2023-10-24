package pt.ulusofona.lp2.deisichess;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;

public class GameManager {





    boolean loadGame(File file) {
        return true;
    }
    int getBoardSize() {
        return 0;
    }
    boolean move(int x0, int y0, int x1, int y1) {
        return true;
    }
    String[] getSquareInfo(int x, int y) {
        return new String[1];
    }
    String[] getPieceInfo(int ID) {
        return new String[1];
    }

    String getPieceInfoAsString(int ID) {
        return  "";
    }
    int getCurrentTeamID() {
        return 0;
    }
    boolean gameOver() {
        return true;
    }
    ArrayList<String> getGameResults() {
        return new ArrayList<String>();
    }
    JPanel getAuthorsPanel() {
        return null;
    }

}