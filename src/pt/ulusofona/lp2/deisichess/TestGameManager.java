package pt.ulusofona.lp2.deisichess;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestGameManager {
    @Test
    public void testJogoCompleto() {
        GameManager gameManager = new GameManager();
        String filePath = "test-files/8x8.txt";
        assertDoesNotThrow(() -> gameManager.loadGame(new File(filePath)));

        assertFalse(gameManager.move(0, 0, 0, 2));

        gameManager.undo();

        assertTrue(gameManager.move(5, 0, 5, 7));
        assertTrue(gameManager.move(4, 7, 5, 7));
        assertTrue(gameManager.move(6, 0, 5, 1));

        assertFalse(gameManager.move(6, 7, 5, 1));

        assertTrue(gameManager.move(2, 7, 4, 5));
        assertTrue(gameManager.move(1, 0, 1, 3));

        assertFalse(gameManager.move(1, 7, 1, 3));

        assertTrue(gameManager.move(3, 7, 0, 4));
        assertTrue(gameManager.move(1, 3, 0, 4));
        assertTrue(gameManager.move(7, 7, 5, 5));
        assertTrue(gameManager.move(7, 0, 4, 3));
        assertTrue(gameManager.move(5, 5, 6, 5));
        assertTrue(gameManager.move(4, 3, 4, 5));
        assertTrue(gameManager.move(6, 5, 5, 4));

        assertFalse(gameManager.move(0, 5, 5, 4));

        assertEquals("[]", gameManager.getHints(5, 1).toString());

        assertTrue(gameManager.move(3, 0, 6, 3));
        assertTrue(gameManager.move(0, 7, 0, 6));

        assertEquals("[(5,4) -> 4, (3,0) -> 0, (4,1) -> 0, (5,2) -> 0, (7,2) -> 0, (7,4) -> 0]", gameManager.getHints(6, 3).toString());

        assertFalse(gameManager.move(0, 3, 1, 3));
        assertFalse(gameManager.move(0, 4, 0, 0));

        assertDoesNotThrow(() -> gameManager.saveGame(new File("test-files/8x8save.txt")));
        assertDoesNotThrow(() -> gameManager.loadGame(new File("test-files/8x8save.txt")));

        assertTrue(gameManager.move(0, 4, 0, 6));

        assertTrue(gameManager.gameOver());
        ArrayList<String> results = gameManager.getGameResults();
        StringBuilder resultado = new StringBuilder();
        for (String result : results) {
            resultado.append(result).append("\n");
        }
        assertEquals("""
                JOGO DE CRAZY CHESS
                Resultado: VENCERAM AS PRETAS
                ---
                Equipa das Pretas
                4
                8
                3
                Equipa das Brancas
                1
                7
                2
                """, String.valueOf(resultado));
    }

    @Test
    public void testJesus() {
        GameManager gameManager = new GameManager();
        String filePath = "test-files/testJesus.txt";
        assertDoesNotThrow(() -> gameManager.loadGame(new File(filePath)));

        assertTrue(gameManager.move(2, 1, 2, 3));

        gameManager.undo();

        assertFalse(gameManager.move(2, 1, 2, 2));
        assertTrue(gameManager.move(2, 1, 3, 1));

        assertTrue(gameManager.move(1, 0, 0, 1));

        assertTrue(gameManager.move(3, 1, 2, 1));

        assertTrue(gameManager.move(0, 1, 1, 0));

        assertTrue(gameManager.move(2, 1, 1, 1));

        assertTrue(gameManager.move(2, 0, 2, 1));

        assertTrue(gameManager.move(1, 1, 1, 0));

        assertTrue(gameManager.gameOver());
        ArrayList<String> results = gameManager.getGameResults();
        StringBuilder resultado = new StringBuilder();
        for (String result : results) {
            resultado.append(result).append("\n");
        }
        assertEquals("""
                JOGO DE CRAZY CHESS
                Resultado: VENCERAM AS PRETAS
                ---
                Equipa das Pretas
                3
                4
                1
                Equipa das Brancas
                0
                3
                0
                """, String.valueOf(resultado));
    }

    @Test
    public void testGetPieceInfo() {
        GameManager gameManager = new GameManager();

        Tabuleiro tabuleiro = new Tabuleiro(8);
        gameManager.setTabuleiro(tabuleiro);

        Peca peca = new Rei(1, 0, 20, "Rei");
        tabuleiro.getPecas().put(1, peca);
        tabuleiro.setPecabyPosicao(0, 0, peca);


        String[] resultadoAtual = gameManager.getPieceInfo(1);
        String[] resultadoEsperado = {"1", "0", "20", "Rei", "capturado", "", ""};

        assertArrayEquals(resultadoEsperado, resultadoAtual);
    }

    @Test
    public void testGetPieceInfoAsString() {
        GameManager gameManager = new GameManager();

        Tabuleiro tabuleiro = new Tabuleiro(4);
        gameManager.setTabuleiro(tabuleiro);

        Peca peca = new Rei(1, 0, 20, "Rei");
        peca.setCapturado(false);
        tabuleiro.getPecas().put(1, peca);
        tabuleiro.setPecabyPosicao(0, 0, peca);


        String resultadoAtual = gameManager.getPieceInfoAsString(1);
        String resultadoReal = "1 | Rei | (infinito) | 20 | Rei @ (0, 0)";

        assertEquals(resultadoReal, resultadoAtual);
    }

    @Test
    public void testGetSquareInfo() {
        GameManager gameManager = new GameManager();

        Tabuleiro tabuleiro = new Tabuleiro(6);
        gameManager.setTabuleiro(tabuleiro);

        Peca peca = new Rei(1, 0, 10, "Rei");
        peca.setCapturado(false);
        tabuleiro.getPecas().put(1, peca);
        tabuleiro.setPecabyPosicao(0, 0, peca);

        String[] result = gameManager.getSquareInfo(0, 0);
        String[] expected = {"1", "0", "10", "Rei", "crazy_emoji_black.png"};

        assertArrayEquals(expected, result);
    }

    @Test
    public void testGameOverPecasEquipa0Is0() {
        GameManager gameManager = new GameManager();

        Tabuleiro tabuleiro = new Tabuleiro(2);
        gameManager.setTabuleiro(tabuleiro);

        Peca peca = new Rei(1, 0, 20, "Rei");
        peca.setCapturado(false);
        tabuleiro.getPecas().put(1, peca);
        tabuleiro.setPecabyPosicao(0, 0, peca);

        boolean resultado = gameManager.gameOver();

        assertTrue(resultado);
        assertEquals("VENCERAM AS BRANCAS", gameManager.getResultado());
    }

}