package src.agenda;

/**
 * Representa a situação em que um {@link Evento} se encontra na linha do tempo.
 */
public enum StatusEvento {
	/** Evento já ocorrido */
	ACONTECEU(1, "Evento ja ocorrido"),
	/** Evento acontecendo */
	ACONTECENDO(2, "Evento acontecendo"),
	/** Evento ainda nao ocorrido */
    ACONTECERA(3, "Evento ainda nao ocorrido");
	
	private int id;
	private String descricao;
	
	private StatusEvento(int id, String descricao) {
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
