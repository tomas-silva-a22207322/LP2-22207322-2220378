package pt.ulusofona.lp2.deisichess;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class GameManager {
    Tabuleiro tabuleiro;
    GameInfo gameInfo;
    String resultado = "";

    Stack<Tabuleiro> estadosAnteriores = new Stack<>();

    public GameManager() {
    }

    public void loadGame(File file)throws InvalidGameInputException, IOException  {
        //throws InvalidGameInputException, IOException
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            int dimensaoTabuleiro = Integer.parseInt(br.readLine());
            int numeroPecas = Integer.parseInt(br.readLine());

            setTabuleiro(new Tabuleiro(dimensaoTabuleiro));

            getTabuleiro().setPecas(new HashMap<>());

            for (int i = 0; i < numeroPecas; i++) {
                String linha = br.readLine();
                String[] partes = linha.split(":");
                if(partes.length == 4){
                    int id = Integer.parseInt(partes[0]);
                    int tipo = Integer.parseInt(partes[1]);
                    int equipa = Integer.parseInt(partes[2]);
                    String nome = partes[3];

                    Peca peca;
                    switch (tipo) {
                        case 0 -> peca = new Rei(id, tipo, equipa, nome);
                        case 1 -> peca = new Rainha(id, tipo, equipa, nome);
                        case 2 -> peca = new PoneiMagico(id, tipo, equipa, nome);
                        case 3 -> peca = new PadreDaVila(id, tipo, equipa, nome);
                        case 4 -> peca = new TorreHorizontal(id, tipo, equipa, nome);
                        case 5 -> peca = new TorreVertical(id, tipo, equipa, nome);
                        case 6 -> peca = new HomerSimpson(id, tipo, equipa, nome);
                        case 7 -> peca = new Joker(id, tipo, equipa, nome);
                        default -> throw new InvalidGameInputException(i + 1, "Tipo de peca invalido");
                    }

                    HashMap<Integer, Peca> pecasHM = getTabuleiro().getPecas();
                    pecasHM.put(id, peca);
                    getTabuleiro().setPecas(pecasHM);
                }else{
                    throw new InvalidGameInputException(i + 1, "DADOS" + (partes.length > 4 ? "A MAIS" : "A MENOS") + "(Esperava: 4 ; Obtive: " + partes.length + ")");
                }
            }

            int[][] posicaoPecas = new int[dimensaoTabuleiro][dimensaoTabuleiro];

            for (int x = 0; x < dimensaoTabuleiro; x++){
                String linha = br.readLine();
                String[] partes = linha.split(":");
                for (int y = 0; y < dimensaoTabuleiro; y++){
                    posicaoPecas[x][y] = Integer.parseInt(partes[y]);
                }
            }


            Peca[][] pecaTabuleiro = new Peca[dimensaoTabuleiro][dimensaoTabuleiro];

            for (int x = 0; x < dimensaoTabuleiro; x++) {
                for (int y = 0; y < dimensaoTabuleiro; y++) {
                    int id = posicaoPecas[x][y];
                    if (id != 0) {
                        Peca peca = getTabuleiro().getPecaById(id);
                        if (peca != null) {
                            pecaTabuleiro[x][y] = peca;
                            peca.setX(y);
                            peca.setY(x);
                            peca.setCapturado(false);
                        }
                    }
                }
            }
            getTabuleiro().setCampoJogo(pecaTabuleiro);

            gameInfo = new GameInfo(getTabuleiro());

            String movimento = br.readLine();
            while(movimento!=null){

                String[] Coordenadas = movimento.split(";");
                move(Integer.parseInt(Coordenadas[0]),Integer.parseInt(Coordenadas[1]),
                        Integer.parseInt(Coordenadas[2]),Integer.parseInt(Coordenadas[3]));

                movimento = br.readLine();
            }
            br.close();

        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
    public int getBoardSize() {return getTabuleiro().getDimensao();}

    public boolean move(int x0, int y0, int x1, int y1) {
        String movimento = x0+";"+y0+";"+x1+";"+y1;


        Peca peca0 = getTabuleiro().getPecabyPosicao(x0,y0);
        Peca peca1 = getTabuleiro().getPecabyPosicao(x1,y1);

        if (peca0 == null) {
            if(getTabuleiro().getEquipaAtual() == 10) {
                getTabuleiro().setJogadasInvalidasPretas(getTabuleiro().getJogadasInvalidasPretas() + 1);
            } else {
                getTabuleiro().setJogadasInvalidasBrancas(getTabuleiro().getJogadasInvalidasBrancas() + 1);
            }
            getGameInfo().addMove(movimento,getTabuleiro());
            return false;
        }

        if(peca0.getEquipa() != getTabuleiro().getEquipaAtual()) {

            if(getTabuleiro().getEquipaAtual() == 10) {
                getTabuleiro().setJogadasInvalidasPretas(getTabuleiro().getJogadasInvalidasPretas() + 1);
            } else {
                getTabuleiro().setJogadasInvalidasBrancas(getTabuleiro().getJogadasInvalidasBrancas() + 1);
            }
            getGameInfo().addMove(movimento,getTabuleiro());
            return false;
        }

        if (getTabuleiro().getPecabyPosicao(x0, y0).verificaPosicoes(x0, y0, x1, y1, getTabuleiro().getTurno(), getTabuleiro())) {

            if (peca1 != null) {
                peca0.setPontuacaoCapturas(peca0.getPontuacaoCapturas() + peca1.getPontuacao());
                peca0.setCapturas(peca0.getCapturas() + 1);

                peca1.setCapturado(true);
                peca1.setX(-1);
                peca1.setY(-1);

                if(getTabuleiro().getEquipaAtual() == 10) {
                    getTabuleiro().setCapturasPretas(getTabuleiro().getCapturasPretas() + 1);
                } else {
                    getTabuleiro().setCapturasBrancas(getTabuleiro().getCapturasBrancas() + 1);
                }

                getTabuleiro().setJogadasAposCaptura(0);
            } else {

                if (getTabuleiro().getCapturasBrancas() >= 1 || getTabuleiro().getCapturasPretas() >= 1) {
                    if(getTabuleiro().getJogadasAposCaptura() >= 0) {
                        getTabuleiro().setJogadasAposCaptura(getTabuleiro().getJogadasAposCaptura() + 1);
                    }
                }
            }

            getTabuleiro().getCampoJogo()[y1][x1] = peca0;
            peca0.setX(x1);
            peca0.setY(y1);
            getTabuleiro().setPecabyPosicao(x0, y0, null);

            peca0.setJogadasValidas(peca0.getJogadasValidas() + 1);

            if(getTabuleiro().getEquipaAtual() == 10) {
                getTabuleiro().setJogadasValidasPretas(getTabuleiro().getJogadasValidasPretas() + 1);
            } else {
                getTabuleiro().setJogadasValidasBrancas(getTabuleiro().getJogadasValidasBrancas() + 1);
            }

            getTabuleiro().setEquipaAtual((getTabuleiro().getEquipaAtual() == 10) ? 20 : 10);
            getTabuleiro().setTurno(getTabuleiro().getTurno() + 1);

            getGameInfo().addMove(movimento, getTabuleiro().copy());
            return true;
        }

        peca0.setJogadasInvalidas(peca0.getJogadasInvalidas() + 1);

        if(getTabuleiro().getEquipaAtual() == 10) {
            getTabuleiro().setJogadasInvalidasPretas(getTabuleiro().getJogadasInvalidasPretas() + 1);
        } else {
            getTabuleiro().setJogadasInvalidasBrancas(getTabuleiro().getJogadasInvalidasBrancas() + 1);
        }
        getGameInfo().addMove(movimento,getTabuleiro());
        return false;
    }
    public String[] getSquareInfo(int x, int y) {
        //id | tipo | equipa | alcunha | png(null)
        if (x < 0 || x > getTabuleiro().getDimensao() || y < 0 || y > getTabuleiro().getDimensao()) {
            return null;
        }

        Peca peca = getTabuleiro().getPecabyPosicao(x, y);

        if (peca == null) {
            return new String[]{};
        }
        String foto = (peca.getEquipa() == 10) ? "crazy_emoji_black.png" : "crazy_emoji_white.png";

        return new String[]{String.valueOf(peca.getId()), String.valueOf(peca.getTipo()),
                String.valueOf(peca.getEquipa()), peca.getNome(), foto};
    }

    public String[] getPieceInfo(int ID) {
        //id | tipo | equipa | alcunha | mostrar se tá em jogo ou capturado

        Peca peca = getTabuleiro().getPecaById(ID);

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
        infoArray = new String[]{id, tipo, equipa, nome,"em jogo", String.valueOf(peca.getX()), String.valueOf(peca.getY())};

        return infoArray;
    }

    public String getPieceInfoAsString(int ID) {
        //id | tipo | pontos | equipa | nome @ (x,y);
        Peca peca = getTabuleiro().getPecaById(ID);
        String info = "";
        int turno = getTabuleiro().getTurno();

        if (peca == null) {
            return info;
        }

        String id = String.valueOf(peca.getId());
        String tipo = peca.getTipoNome();
        if (tipo.equals("Joker")) {
            int moveJoker = turno % 6;

            switch (moveJoker) {
                case 0 -> tipo += "/Rainha";
                case 1 -> tipo += "/Ponei Mágico";
                case 2 -> tipo += "/Padre da Vila";
                case 3 -> tipo += "/Torre Horizontal";
                case 4 -> tipo += "/Torre Vertical";
                case 5 -> tipo += "/Homer Simpson";
            }

        }
        String equipa = String.valueOf(peca.getEquipa());
        String nome = peca.getNome();
        int pontosInt= peca.getPontuacao();
        String pontos = pontosInt == 1000? "(infinito)": pontosInt+"";
        String coordenadas = "";

        if (peca.isCapturado()) {
            coordenadas = "(n/a)";
            info = id + " | " + tipo + " | " + pontos + " | " + equipa + " | " + nome + " @ " + coordenadas;
            return info;
        }
        if (tipo.equals("Homer Simpson")) {
            if (turno % 3 == 0) {
                return info + "Doh! zzzzzzz";
            }
        }
        coordenadas = "(" + peca.getX() + ", " + peca.getY() + ")";

        info = id + " | " + tipo + " | " + pontos + " | " + equipa + " | " + nome + " @ " + coordenadas;

        return info;
    }
    public int getCurrentTeamID() {return getTabuleiro().getEquipaAtual();}

    public boolean gameOver() {
        /*
        chamado no final de cada jogada
        acaba se só existir reis de 1 equipa (vitoria mostrar a equipa),
        existe 1 rei em cada equipa (empate),
        após 1 captura caso não haja outra captura após 10 jogadas
        */
        int pecasEquipa0 = 0;
        int pecasEquipa1 = 0;
        boolean temrei0 = false;
        boolean temrei1 = false;

        for (Peca peca : getTabuleiro().getPecas().values()) {
            if (peca.getEquipa() == 10 && !peca.isCapturado()) {
                pecasEquipa0++;
                if (peca.getTipo() == 0) { // Verifica se a peça é um rei
                    temrei0 = true;
                }
            } else if (peca.getEquipa() == 20 && !peca.isCapturado()) {
                pecasEquipa1++;
                if (peca.getTipo() == 0) { // Verifica se a peça é um rei
                    temrei1 = true;
                }
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

        if (pecasEquipa0 == 1 && temrei0 && pecasEquipa1 == 1 && temrei1) {
            setResultado("EMPATE");
            return true;
        }

        if (getTabuleiro().getJogadasAposCaptura() >= 10) {
            // Após 1 captura, se não houve outra captura após 10 jogadas, o jogo acaba, o resultado é empate
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
        results.add(String.valueOf(getTabuleiro().getCapturasPretas()));
        results.add(String.valueOf(getTabuleiro().getJogadasValidasPretas()));
        results.add(String.valueOf(getTabuleiro().getJogadasInvalidasPretas()));
        results.add("Equipa das Brancas");
        results.add(String.valueOf(getTabuleiro().getCapturasBrancas()));
        results.add(String.valueOf(getTabuleiro().getJogadasValidasBrancas()));
        results.add(String.valueOf(getTabuleiro().getJogadasInvalidasBrancas()));

        return results;
    }

    public void saveGame(File file) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            // Escrever a dimensão do tabuleiro
            Tabuleiro tabuleiroinical = getGameInfo().getTabuleiroInicial();
            bw.write(tabuleiroinical.getDimensao() + "\n");

            // Escrever o número de peças
            int numeroPecas = tabuleiroinical.getPecas().size();
            bw.write(numeroPecas + "\n");

            // Escrever as informações de cada peça no tabuleiro
            for (Peca peca : tabuleiroinical.getPecas().values()) {
                bw.write(peca.getId() + ":" + peca.getTipo() + ":" + peca.getEquipa() + ":" + peca.getNome() + "\n");
            }

            // Escrever a posição das peças no tabuleiro
            Peca[][] tabuleiro = tabuleiroinical.getCampoJogo();
            for (Peca[] pecas : tabuleiro) {
                for (int y = 0; y < pecas.length; y++) {
                    if (pecas[y] != null) {
                        bw.write(pecas[y].getId() + ":");
                    } else {
                        bw.write("0:");
                    }
                }
                bw.write("\n");
                for(int i = 0;i < gameInfo.getMovimentos().size();i++){
                    bw.write(gameInfo.getMovimentos().get(i)+"\n");
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void undo() {
        setTabuleiro(getGameInfo().undo());
    }


    public List<Comparable> getHints(int x, int y){
        List<Comparable> hints = new ArrayList<>();

        Peca pecaSelecionada = getTabuleiro().getPecabyPosicao(x, y);

        if (pecaSelecionada == null || pecaSelecionada.isCapturado() || pecaSelecionada.getEquipa() != getTabuleiro().getEquipaAtual()) {
            return hints;
        }

        if (pecaSelecionada != null && !pecaSelecionada.isCapturado()) {
            ComparableClass novaJogada;
            // Obtém todas as possíveis jogadas para a peça selecionada
            for (int i = 0; i < getTabuleiro().getDimensao(); i++) {
                for (int j = 0; j < getTabuleiro().getDimensao(); j++) {

                    if (i != y && j != x) {

                        if (pecaSelecionada.verificaPosicoes(x, y, j, i, getTabuleiro().getTurno(), getTabuleiro())) {
                            Peca pecaji = getTabuleiro().getPecabyPosicao(j, i);
                            if (pecaji != null && pecaji.getEquipa() != getTabuleiro().getPecabyPosicao(x, y).getEquipa()) {
                                novaJogada = new ComparableClass(j, i, pecaji.getPontuacao());

                            } else {
                                novaJogada = new ComparableClass(j, i, 0);
                            }
                            hints.add(novaJogada);
                        }
                    }
                }
            }

            Collections.sort(hints);
        }

        return hints;
    }


    public JPanel getAuthorsPanel() {
        return null;
    }
    public Map<String,String> customizeBoard(){return new HashMap<>();}

    //GETTERS
    public Tabuleiro getTabuleiro() {return tabuleiro;}
    public java.lang.String getResultado() {return resultado;}
    public GameInfo getGameInfo() {return gameInfo;}

    //SETTERS
    public void setTabuleiro(Tabuleiro tabuleiro) {this.tabuleiro = tabuleiro;}
    public void setResultado(java.lang.String resultado) {this.resultado = resultado;}
}
