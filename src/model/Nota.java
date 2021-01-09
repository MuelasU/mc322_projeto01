package src.model;

/**
 * Corresponde a uma nota avaliativa dada pelos {@link Estudante} ao {@link Instrutor}.
 * <p>
 * Cada opção de nota possui um valor inteiro associado que varia de 0 a 5 e é usado para computar a avaliação média do {@code Instrutor}.
 */
public enum Nota {
    /** Nota 0/5 */
    MUITO_RUIM(0),
    /** Nota 1/5 */
    RUIM(1),
    /** Nota 2/5 */
    MEDIO(2),
    /** Nota 3/5 */
    BOM(3),
    /** Nota 4/5 */
    MUITO_BOM(4),
    /** Nota 5/5 */
    EXCELENTE(5);
    
    private int valor;

    Nota(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}