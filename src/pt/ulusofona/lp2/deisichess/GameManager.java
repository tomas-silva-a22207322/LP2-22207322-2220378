package pt.ulusofona.lp2.deisichess;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class GameManager {

    Tabuleiro tabuleiro;
    int numeroPecas;
    int jogadasAposCaptura = -1;
    int jogadasValidasPretas = 0;
    int jogadasValidasBrancas = 0;
    int jogadasInvalidasPretas = 0;
    int jogadasInvalidasBrancas = 0;
    int capturasPretas = 0;
    int capturasBrancas = 0;
    int equipaAtual = 0;
    String resultado = "";

    public GameManager() {
    }

    public boolean loadGame(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            int dimensaoTabuleiro = Integer.parseInt(br.readLine());
            int numeroPecas = Integer.parseInt(br.readLine());

            tabuleiro = new Tabuleiro(dimensaoTabuleiro);

            tabuleiro.pecas = new HashMap<>();

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

            for (int x = 0; x < dimensaoTabuleiro; x++){
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
                            peca.setX(y);
                            peca.setY(x);
                            peca.setCapturado(false);
                        }
                    }
                }
            }
            tabuleiro.setTabuleiro(pecaTabuleiro);


            return true;
        } catch (FileNotFoundException fileNotFoundException) {
            return false;
        }catch (IOException e){
            throw new RuntimeException(e);
        }

    }
    public int getBoardSize() {return tabuleiro.getDimensao();}

    public boolean move(int x0, int y0, int x1, int y1) {
        Peca peca0 = tabuleiro.getPecabyPosicao(x0,y0);
        Peca peca1 = tabuleiro.getPecabyPosicao(x1,y1);

        if (peca0 == null) {
            if(equipaAtual == 0) {
                jogadasInvalidasPretas++;
            } else {
                jogadasInvalidasBrancas++;
            }
            return false;
        }

        if(peca0.getEquipa() != equipaAtual) {

            if(equipaAtual == 0) {
                jogadasInvalidasPretas++;
            } else {
                jogadasInvalidasBrancas++;
            }
            return false;
        }

        if (tabuleiro.isValidMove(x0, y0, x1, y1)) {

            if (peca1 != null) {

                peca1.setCapturado(true);
                peca1.setX(-1);
                peca1.setY(-1);

                if(equipaAtual == 0) {
                    capturasPretas++;
                } else {
                    capturasBrancas++;
                }

                setJogadasAposCaptura(0);
            } else {

                if (capturasBrancas >= 1 || capturasPretas >= 1) {
                    if(jogadasAposCaptura >= 0) {
                        jogadasAposCaptura++;
                    }
                }
            }

            tabuleiro.campoJogo[y1][x1] = peca0;
            peca0.setX(x1);
            peca0.setY(y1);
            tabuleiro.setPecabyPosicao(x0, y0, null);

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
    public String[] getSquareInfo(int x, int y) {
        //id | tipo | equipa | alcunha | png(null)
        if (x < 0 || x > tabuleiro.dimensao || y < 0 || y > tabuleiro.dimensao) {
            return null;
        }

        Peca peca = tabuleiro.getPecabyPosicao(x, y);

        if (peca == null) {
            return new String[]{};
        }
        String foto = (peca.getEquipa() == 0) ? "crazy_emoji_black.png" : "crazy_emoji_white.png";

        return new String[]{String.valueOf(peca.getId()), String.valueOf(peca.getTipo()),
                String.valueOf(peca.getEquipa()), peca.getNome(), foto};
    }

    public String[] getPieceInfo(int ID) {
        //id | tipo | equipa | alcunha | mostrar se tá em jogo ou capturado

        Peca peca = tabuleiro.getPecaById(ID);

        if (peca == null) {
            return new String[0];
        }

        String id = String.valueOf(peca.getId());
        String tipo = String.valueOf(peca.getTipo());
        String equipa = String.valueOf(peca.getEquipa());
        String nome = peca.getNome();
        String[] infoArray;

        if(peca.isCapturado()){
            infoArray = new String[]{id, tipo, equipa, nome, "capturado","",""};
            return infoArray;
        }
        String coordenadas = "(" + peca.getX() + ", " + peca.getY() + ")";
        infoArray = new String[]{id, tipo, equipa, nome,"em jogo", String.valueOf(peca.getX()), String.valueOf(peca.getY())};

        return infoArray;
    }

    public String getPieceInfoAsString(int ID) {
        Peca peca = tabuleiro.getPecaById(ID);
        String info = "";

        if (peca == null) {
            return info;
        }

        String id = String.valueOf(peca.getId());
        String tipo = String.valueOf(peca.getTipo());
        String equipa = String.valueOf(peca.getEquipa());
        String nome = peca.getNome();

        if (peca.isCapturado()) {
            String coordenadas = "(n/a)";
            info = id + " | " + tipo + " | " + equipa + " | " + nome + " @ " + coordenadas;
            return info;
        }
        String coordenadas = "(" + peca.getX() + ", " + peca.getY() + ")";
        info = id + " | " + tipo + " | " + equipa + " | " + nome + " @ " + coordenadas;

        return info;
    }
    public int getCurrentTeamID() {return equipaAtual;}

    public boolean gameOver() {
        //chamado no final de cada jogada
        //acaba se só existir reis de 1 equipa (vitoria mostrar a equipa),
        // existe 1 rei em cada equipa (empate),
        // após 1 captura caso não haja outra captura após 10 jogadas

        int pecasEquipa0 = 0;
        int pecasEquipa1 = 0;


        for (Peca peca : tabuleiro.getPecas().values()) {
            if (peca.getEquipa() == 0 && !peca.isCapturado()) {
                pecasEquipa0++;
            } else if (peca.getEquipa() == 1 && !peca.isCapturado()) {
                pecasEquipa1++;
            }
        }

        if (pecasEquipa0 == 0) {
            setResultado("VENCERAM AS BRANCAS");
            return true;
        }

        if (pecasEquipa1 == 0) {
            setResultado("VENCERAM AS PRETAS");
            return true;
        }

        if (pecasEquipa0 == 1 && pecasEquipa1 == 1) {
            setResultado("EMPATE");
            return true;
        }

        if (jogadasAposCaptura >= 10) {
            // Após 1 captura, se não houve outra captura após 10 jogadas, o jogo acaba (o resultado é empate?)
            setResultado("EMPATE");
            return true;
        }

        return false;
    }
    public ArrayList<String> getGameResults() {
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
        ArrayList<String> results = new ArrayList<String>();

        results.add("JOGO DE CRAZY CHESS");
        results.add("Resultado: " + getResultado());
        results.add("---");
        results.add("Equipa das Pretas");
        results.add(String.valueOf(getCapturasPretas()));
        results.add(String.valueOf(getJogadasValidasPretas()));
        results.add(String.valueOf(getJogadasInvalidasPretas()));
        results.add("Equipa das Brancas");
        results.add(String.valueOf(getCapturasBrancas()));
        results.add(String.valueOf(getJogadasValidasBrancas()));
        results.add(String.valueOf(getJogadasInvalidasBrancas()));

        return results;
    }
    public JPanel getAuthorsPanel() {
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
    public java.lang.String getResultado() {return resultado;}

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
    public void setResultado(java.lang.String resultado) {this.resultado = resultado;}
}