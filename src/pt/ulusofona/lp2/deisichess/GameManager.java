package pt.ulusofona.lp2.deisichess;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class GameManager {

    Tabuleiro tabuleiro;
    int numeroPecas;
    int jogadasAposCaptura = 0;
    int jogadasValidasPretas = 0;
    int jogadasValidasBrancas = 0;
    int jogadasInvalidasPretas = 0;
    int jogadasInvalidasBrancas = 0;
    int capturasPretas = 0;
    int capturasBrancas = 0;
    int equipaAtual = 0;


    boolean loadGame(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            int dimensaoTabuleiro = Integer.parseInt(br.readLine());
            int numeroPecas = Integer.parseInt(br.readLine());

            tabuleiro = new Tabuleiro(dimensaoTabuleiro);

            tabuleiro.pecas=new HashMap<>();

            for (int i = 0; i < numeroPecas; i++) {
                String linha = br.readLine();
                String[] partes = linha.split(":");
                if(partes.length==4){
                    int id = Integer.parseInt(partes[0]);
                    int tipo = Integer.parseInt(partes[1]);
                    int equipa = Integer.parseInt(partes[2]);
                    String nome = partes[3];

                    Peca peca = new Peca(id, tipo, equipa, nome);
                    tabuleiro.pecas.put(id, peca);
                }
            }

            int[][] posicaoPecas = new int[dimensaoTabuleiro][dimensaoTabuleiro] ;

            for (int x = 0; x< dimensaoTabuleiro; x++){
                String linha = br.readLine();
                String[] partes = linha.split(":");
                for (int y = 0; y<dimensaoTabuleiro; y++){
                    posicaoPecas[x][y] = Integer.parseInt(partes[y]);
                }
            }
            br.close();

            Peca[][] pecaTabuleiro = new Peca[dimensaoTabuleiro][dimensaoTabuleiro];

            for (int x = 0; x < dimensaoTabuleiro; x++) {
                for (int y = 0; y < dimensaoTabuleiro; y++) {
                    int id = posicaoPecas[x][y];
                    if (id != 0) {
                        Peca peca = tabuleiro.getPecaById(id);
                        if (peca != null) {
                            pecaTabuleiro[x][y] = peca;
                            peca.setX(x);
                            peca.setY(y);
                            peca.setPecaEmJogo(true);
                        }
                    }
                }
            }


            return true;
        } catch (FileNotFoundException fileNotFoundException) {
            return false;
        }catch (IOException e){
            throw new RuntimeException(e);
        }

    }
    int getBoardSize() {return tabuleiro.getDimensao();}
    boolean move(int x0, int y0, int x1, int y1) {

        if (tabuleiro.getPecabyPosicao(x0,y0) == null) {
            return false;
        }

        if(tabuleiro.getPecabyPosicao(x0,y0).getEquipa() != equipaAtual) {
            return false;
        }

        if (tabuleiro.isValidMove(x0, y0, x1, y1)) {
            Peca peca = tabuleiro.campoJogo[x0][y0];


            if (tabuleiro.getPecabyPosicao(x1,y1) != null) {

                tabuleiro.getPecabyPosicao(x1,y1).setPecaEmJogo(false);
                tabuleiro.getPecabyPosicao(x1,y1).setX(-1);
                tabuleiro.getPecabyPosicao(x1,y1).setY(-1);

                if(equipaAtual == 0) {
                    capturasPretas++;
                } else {
                    capturasBrancas++;
                }

                tabuleiro.setPecabyPosicao(x1,y1, tabuleiro.getPecabyPosicao(x0,y0));
                tabuleiro.getPecabyPosicao(x1,y1).setX(x1);
                tabuleiro.getPecabyPosicao(x1,y1).setY(y1);

                tabuleiro.campoJogo[x0][y0] = null;
                jogadasAposCaptura = 0;
            } else {
                tabuleiro.campoJogo[x1][y1] = peca;
                peca.x = x1;
                peca.y = y1;

                if (capturasBrancas >= 1 || capturasPretas >= 1) {
                    jogadasAposCaptura++;
                }
            }

            if(equipaAtual == 0) {
                jogadasValidasPretas++;
            } else {
                jogadasValidasBrancas++;
            }

            equipaAtual = (equipaAtual == 0) ?  1 : 0;

            return true;
        }

        if(equipaAtual == 0) {
            jogadasInvalidasPretas++;
        } else {
            jogadasInvalidasBrancas++;
        }

        return false;
    }
    String[] getSquareInfo(int x, int y) {//String.valueOf
        //id | tipo | equipa | alcunha |    png(null)
        if (x < 0 || x > tabuleiro.dimensao || y < 0 || y > tabuleiro.dimensao) {
            return null;
        }
        return new String[] {};
    }
    String[] getPieceInfo(int ID) {
        //id | tipo | equipa | alcunha | mostrar se tá em jogo ou capturado

        return new String[1];
    }

    String getPieceInfoAsString(int ID) {
        //tooltip retorna a peca com a string id | tipo | equipa | alcunha @ (x, y)

        return  "";
    }
    int getCurrentTeamID() {return equipaAtual;}

    boolean gameOver() {
        //chamado no final de cada jogada
        //acaba se só existir reis de 1 equipa (vitoria mostrar a equipa),
        // existe 1 rei em cada equipa (empate),
        // após 1 captura caso não haja outra captura após 10 jogadas
        //TODO criar variavel captura e o count
        return true;
    }
    ArrayList<String> getGameResults() {
        /*
        JOGO DE CRAZY CHESS
        Resultado: <(vitoria de que equipa ou empate)>
        ---
        Equipa das Pretas
        <capturasPretas>
        <jogadasValidasPretas>
        <jogadasInvalidasPretas>
        Equipa das Brancas
        <capturasBrancas>
        <jogadasValidasBrancas>
        <jogadasInvalidasBrancas>
         */
        return new ArrayList<String>();
    }
    JPanel getAuthorsPanel() {
        return null;
    }


    //GETTERS
    public Tabuleiro getTabuleiro() {return tabuleiro;}
    public int getNumeroPecas() {return numeroPecas;}
    public int getJogadasAposCaptura() {return jogadasAposCaptura;}
    public int getJogadasValidasPretas() {return jogadasValidasPretas;}
    public int getJogadasValidasBrancas() {return jogadasValidasBrancas;}
    public int getJogadasInvalidasPretas() {return jogadasInvalidasPretas;}
    public int getJogadasInvalidasBrancas() {return jogadasInvalidasBrancas;}
    public int getCapturasPretas() {return capturasPretas;}
    public int getCapturasBrancas() {return capturasBrancas;}
    public int getEquipaAtual() {return equipaAtual;}


    //SETTERS
    public void setTabuleiro(Tabuleiro tabuleiro) {this.tabuleiro = tabuleiro;}
    public void setNumeroPecas(int numeroPecas) {this.numeroPecas = numeroPecas;}
    public void setJogadasAposCaptura(int jogadasAposCaptura) {this.jogadasAposCaptura = jogadasAposCaptura;}
    public void setJogadasValidasPretas(int jogadasValidasPretas) {this.jogadasValidasPretas = jogadasValidasPretas;}
    public void setJogadasValidasBrancas(int jogadasValidasBrancas) {this.jogadasValidasBrancas = jogadasValidasBrancas;}
    public void setJogadasInvalidasPretas(int jogadasInvalidasPretas) {this.jogadasInvalidasPretas = jogadasInvalidasPretas;}
    public void setJogadasInvalidasBrancas(int jogadasInvalidasBrancas) {this.jogadasInvalidasBrancas = jogadasInvalidasBrancas;}
    public void setCapturasPretas(int capturasPretas) {this.capturasPretas = capturasPretas;}
    public void setCapturasBrancas(int capturasBrancas) {this.capturasBrancas = capturasBrancas;}
    public void setEquipaAtual(int equipaAtual) {this.equipaAtual = equipaAtual;}
}