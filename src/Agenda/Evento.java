package src.agenda;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;

import src.Disciplina;
import src.Estudante;
import src.Instrutor;
import src.Moderador;
import src.Usuario;

/**
 * Representa um evento do tipo {@link Aula} ou {@link Monitoria}.
 * <p>
 * Possui um {@link Instrutor} responsável, {@link Estudante} participantes, data, duração e outras características comuns a um evento.
 */
public class Evento {
    private static int numeroEventos = 0;
    private int id;
    private String nome;
    private String descricao;
    private Disciplina disciplina;
    private Calendar data;
    private int capacidade;
    private Duration duracao;
    private boolean confirmado;
    private String salaDeVideo;
    private StatusEvento status;
    private Instrutor instrutor;
    private ArrayList<Estudante> participantes;
	
	//#region Construtuores
    public Evento(String nome, String descricao, Disciplina disciplina, Calendar data, Duration duracao, int capacidade, String salaDeVideo, Instrutor instrutor) {
		id = numeroEventos;
        numeroEventos++;
        this.nome = nome;
    	this.descricao = descricao;
    	this.disciplina = disciplina;
    	this.data = data;
    	this.duracao = duracao;
    	this.capacidade = capacidade > 100 ? 100 : capacidade;
        this.salaDeVideo = salaDeVideo;
        this.instrutor = instrutor;
        instrutor.getAgenda().getEventos().add(this);
        confirmado = true;
        status = StatusEvento.ACONTECERA;
        participantes = new ArrayList<Estudante>();
		Agenda.getTodosEventos().add(this);
	}

	//Usado para Estudante solicitar Monitoria
	public Evento(String nome, String descricao, Disciplina disciplina, Calendar data) {
		id = numeroEventos;
        numeroEventos++;
		this.nome = nome;
    	this.descricao = descricao;
    	this.disciplina = disciplina;
		this.data = data;
		confirmado = false;
		status = StatusEvento.ACONTECERA;
		participantes = new ArrayList<Estudante>();
		Agenda.getTodosEventos().add(this);
	}
	//#endregion
    
	//#region Getters and Setters
	public static int getNumeroEventos() {
		return numeroEventos;
	}

	public static void setNumeroEventos(int numeroEventos) {
		Evento.numeroEventos = numeroEventos;
	}

	public int getId() {
		return id;
    }
    
	public void setId(int id) {
		this.id = id;
    }
    
	public int getCapacidade() {
		return capacidade;
    }
    
	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade > 100 ? 100 : capacidade;
    }
    
	public ArrayList<Estudante> getParticipantes() {
		return participantes;
    }
    
	public boolean isConfirmado() {
		return confirmado;
    }
    
	public void setConfirmado(boolean confirmado) {
		this.confirmado = confirmado;
    }
    
	public Calendar getData() {
		return data;
    }
    
	public void setData(Calendar data) {
		this.data = data;
    }
    
	public String getNome() {
		return nome;
    }
    
	public void setNome(String nome) {
		this.nome = nome;
    }
    
	public Duration getDuracao() {
		return duracao;
    }
    
	public void setDuracao(Duration duracao) {
		this.duracao = duracao;
    }
    
	public String getDescricao() {
		return descricao;
    }
    
	public void setDescricao(String descricao) {
		this.descricao = descricao;
    }
    
	public String getSalaDeVideo() {
		return salaDeVideo;
    }
    
	public void setSalaDeVideo(String salaDeVideo) {
		this.salaDeVideo = salaDeVideo;
    }
    
	public StatusEvento getStatus() {
		return status;
    }
    
	public void setStatus(StatusEvento status) {
		this.status = status;
    }
    
	public Instrutor getInstrutor() {
		return instrutor;
	}
	
	public void setInstrutor(Instrutor instrutor) {
		instrutor.getAgenda().getEventos().add(this);
		this.instrutor = instrutor;
	}
    
	public Disciplina getDisciplina() {
		return disciplina;
    }
    
	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
    //#endregion
	
	/**
     * Remove um {@link Evento} do aplicativo.
	 * <p>
	 * Este método faz com que nenhuma {@link Agenda} faça referência ao {@code Evento}.
	 * 
	 * @param solicitante
	 * @return {@code true} se removeu. {@code false} caso contrário, quando <b>solicitante</b> não tem permissão para tal
	 */
    public boolean removeEvento(Usuario solicitante) {
		boolean eInstrutorResponsavel = (solicitante instanceof Instrutor && solicitante == getInstrutor());
		boolean eModerador = solicitante instanceof Moderador;
		if (eInstrutorResponsavel || eModerador) {	
			for (Estudante participante : getParticipantes()) {
				participante.desinscreverEvento(this);
			}
			Agenda.getTodosEventos().remove(this);
			getInstrutor().getAgenda().getEventos().remove(this);
			return true;
		}
		return false;
    }
}

