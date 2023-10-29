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

//TODO criar uma classe equipa?


    boolean loadGame(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            int dimensaoTabuleiro = Integer.parseInt(br.readLine());
            int numeroPecas = Integer.parseInt(br.readLine());

            tabuleiro = new Tabuleiro(dimensaoTabuleiro);

            //TODO como pesquisar pelas casas com peças e casas vazias

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
        //TODO mover apenas pecas da equipa
        if (peca.isValidMove(x0, y0, x1, y1)) {
            int pecaID = tabuleiro.campoJogo[x0][y0];

            tabuleiro.campoJogo[x0][y0] = 0;

            tabuleiro.campoJogo[x1][y1] = pecaID;
            peca.x = x1;
            peca.y = y1;

            return true;
        }
        // TODO verificar a equipa da peca e incrementar o count capturas e meter a captura a true
        return false;
    }
    String[] getSquareInfo(int x, int y) {
        //id tipo equipa alcunha png(null)
        if (x < 0 || x > tabuleiro.dimensao || y < 0 || y > tabuleiro.dimensao) {
            return null;
        }
        return new String[1];
    }
    String[] getPieceInfo(int ID) {


        return new String[1];
    }

    String getPieceInfoAsString(int ID) {
        //tooltip retorna a peca com a string id | tipo | equipa | alcunha @ (x, y)

        return  "";
    }
    int getCurrentTeamID() {
        //TODO return 0 se for preta a jogar return 1 se for a branca

        return 0;
    }
    boolean gameOver() {
        //chamado no final de cada jogada
        //acaba se só existir reis de 1 equipa, existe 1 rei em cada equipa,após 1 captura caso não haja outra captura após 10 jogadas
        //TODO criar variavel captura e o count
        return true;
    }
    ArrayList<String> getGameResults() {
        return new ArrayList<String>();
    }
    JPanel getAuthorsPanel() {
        return null;
    }

}