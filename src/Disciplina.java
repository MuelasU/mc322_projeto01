package src;

/**
 * Representa a lista de todas disciplinas escolares que o aplicativo contempla.
 * <p>
 * Todo {@code Evento}, {@code Discussao}, {@code Diretorio}, {@code Material} está associado a uma das disciplinas listadas.
 */
public enum Disciplina {
    ARTES(0, "Artes"),
    BIOLOGIA(1, "Biologia"),
    ESPANHOL(2, "Espanhol"),
    FILOSOFIA(3, "Filosofia"),
    FISICA(4, "Fisica"),
    GEOGRAFIA(5, "Geografia"),
    HISTORIA(6, "Historia"),
    INGLES(7, "Ingles"),
    MATEMATICA(8, "Matematica"),
    PORTUGUES(9, "Portugues"),
    QUIMICA(10, "Quimica"),
    SOCIOLOGIA(11, "Sociologia");

    private int id;
    private String nome;

    Disciplina(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
