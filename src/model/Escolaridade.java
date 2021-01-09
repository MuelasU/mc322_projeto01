package src.model;

/**
 * Estes níveis escolares representam os graus de escolaridade.
 * <p> Eles correspondem ao grau de instrução que um {@link Usuario} possui, mediante as etapas de educação que foram concluídas.
 */
public enum Escolaridade {
    /** Completou o Ensino Fundamental I */
    FUNDAMENTAL1(0,"Completou o Ensino Fundamental I"),
    /** Completou o Ensino Fundamental II */
    FUNDAMENTAL2(1,"Completou o Ensino Fundamental II"),
    /** Completou o Ensino Médio */
    MEDIO(2,"Completou o Ensino Medio"),
    /** Completou o Ensino Superior */
    SUPERIOR(3,"Completou o Ensino Superior"),
    /** Completou o Mestrado */
    MESTRADO(4,"Completou o Mestrado"),
    /** Completou o Doutorado */
    DOUTORADO(5,"Completou o Doutorado"),
    /** Completou o Pós-Doutorado */
    POS_DOUTORADO(6,"Completou o Pos-Doutorado");

    private int id;
    private String descricao;

    Escolaridade(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }
    
    public String getDescricao() {
        return descricao;
    }
}
