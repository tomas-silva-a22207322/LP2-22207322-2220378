package pt.ulusofona.lp2.deisichess;

public class InvalidGameInputException extends Exception {
    private final int linhaComErro;
    private final String descricaoProblema;

    public InvalidGameInputException(int linhaComErro, String descricaoProblema) {
        this.linhaComErro = linhaComErro;
        this.descricaoProblema = descricaoProblema;
    }

    public int getLineWithError() {
        return linhaComErro;
    }

    public String getProblemDescription() {
        return "Ocorreu um erro ao ler o ficheiro, na linha " + getLineWithError() + " com o seguinte problema: " + descricaoProblema;
    }
}