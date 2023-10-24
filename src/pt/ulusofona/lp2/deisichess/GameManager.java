package pt.ulusofona.lp2.deisichess;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GameManager {

    Tabuleiro tabuleiro;
    Peca peca;
    int numeroPecas;




    boolean loadGame(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            int dimensaoTabuleiro = Integer.parseInt(br.readLine());
            int numeroPecas = Integer.parseInt(br.readLine());

            tabuleiro = new Tabuleiro(dimensaoTabuleiro);

            //como pesquisar pelas casas com peças e casas vazias

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }
    int getBoardSize() {
        return tabuleiro.getDimensao();
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