package pt.ulusofona.lp2.deisichess;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestGameManager {
    //A função de teste não pode estar vazia;
    //A função de teste tem de ter pelo menos um assert;
    //A função de teste tem de chamar código do projecto;
    //O assert tem de comparar o resultado de uma função do projecto com um resultado
    //esperado.

    @Test
    public void testGetPieceInfo() {
        /*GameManager gameManager = new GameManager();

        Tabuleiro tabuleiro = new Tabuleiro(8);
        gameManager.setTabuleiro(tabuleiro);

        Peca peca = new Peca(1, 0, 0, "Rei");
        tabuleiro.getPecas().put(1, peca);
        tabuleiro.setPecabyPosicao(0, 0, peca);


        String[] resultadoAtual = gameManager.getPieceInfo(1);
        String[] resultadoEsperado = {"1", "0", "0", "Rei", "capturado", "", ""};

        assertArrayEquals(resultadoEsperado, resultadoAtual);*/
    }

    @Test
    public void testGetPieceInfoAsString() {
        /*GameManager gameManager = new GameManager();

        Tabuleiro tabuleiro = new Tabuleiro(4);
        gameManager.setTabuleiro(tabuleiro);

        Peca peca = new Peca(1, 0, 0, "Rei");
        peca.setCapturado(false);
        tabuleiro.getPecas().put(1, peca);
        tabuleiro.setPecabyPosicao(0, 0, peca);


        String resultadoAtual = gameManager.getPieceInfoAsString(1);
        String resultadoReal = "1 | 0 | 0 | Rei @ (0, 0)";

        assertEquals(resultadoReal, resultadoAtual);*/
    }

    @Test
    public void testGetSquareInfo() {
        /*GameManager gameManager = new GameManager();

        Tabuleiro tabuleiro = new Tabuleiro(6);
        gameManager.setTabuleiro(tabuleiro);

        Peca peca = new Peca(1, 0, 0, "Rei");
        peca.setCapturado(false);
        tabuleiro.getPecas().put(1, peca);
        tabuleiro.setPecabyPosicao(0, 0, peca);

        String[] result = gameManager.getSquareInfo(0, 0);
        String[] expected = {"1", "0", "0", "Rei", "crazy_emoji_black.png"};

        assertArrayEquals(expected, result);*/
    }

    @Test
    public void testGameOverPecasEquipa0Is0() {
        /*GameManager gameManager = new GameManager();

        Tabuleiro tabuleiro = new Tabuleiro(2);
        gameManager.setTabuleiro(tabuleiro);

        Peca peca = new Peca(1, 0, 1, "Rei");
        peca.setCapturado(false);
        tabuleiro.getPecas().put(1, peca);
        tabuleiro.setPecabyPosicao(0, 0, peca);

        boolean resultado = gameManager.gameOver();

        assertTrue(resultado);
        assertEquals("VENCERAM AS BRANCAS", gameManager.getResultado());*/
    }

}