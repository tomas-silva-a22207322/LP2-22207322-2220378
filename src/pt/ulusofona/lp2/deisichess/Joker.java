package pt.ulusofona.lp2.deisichess;

public class Joker extends Peca{


    public Joker(int id, int tipo, int equipa, String nome) {
        super(id, tipo, equipa, nome);
    }

    @Override
    public boolean isValidMove(int x0, int y0, int x1, int y1, int turno) {
        int movePeca = turno % 7;

        Peca peca;

        switch (movePeca) {
            case 1: // Rainha
                peca = new Rainha(getId(),  getTipo(),  getEquipa(),  getNome());
                return peca.isValidMove(x0, y0, x1, y1, turno);
            case 2: // Ponei Mágico
                peca = new PoneiMagico(getId(),  getTipo(),  getEquipa(),  getNome());
                return peca.isValidMove(x0, y0, x1, y1, turno);
            case 3: // Padre da Vila
                peca = new PadreDaVila(getId(),  getTipo(),  getEquipa(),  getNome());
                return peca.isValidMove(x0, y0, x1, y1, turno);
            case 4: // Torre Horizontal
                peca = new TorreHorizontal(getId(),  getTipo(),  getEquipa(),  getNome());
                return peca.isValidMove(x0, y0, x1, y1, turno);
            case 5: // Torre Vertical
                peca = new TorreVertical(getId(),  getTipo(),  getEquipa(),  getNome());
                return peca.isValidMove(x0, y0, x1, y1, turno);
            case 6: // Homer Simpson
                peca = new HomerSimpson(getId(),  getTipo(),  getEquipa(),  getNome());
                return peca.isValidMove(x0, y0, x1, y1, turno);
            case 0: // Rei
                peca = new Rei(getId(),  getTipo(),  getEquipa(),  getNome());
                return peca.isValidMove(x0, y0, x1, y1, turno);
            default:
                return false;
        }
    }

}
