package src.model.agenda;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;

import src.model.Disciplina;
import src.model.Estudante;
import src.model.Instrutor;
import src.model.Moderador;
import src.model.Nota;
import src.model.Usuario;
import src.model.repositorio.Material;

/**
 * Representa um evento do tipo {@link Aula} ou {@link Monitoria}.
 * <p>
 * Possui um {@link Instrutor} responsável, {@link Estudante} participantes, data, duração e outras características comuns a um evento.
 */
public abstract class Evento {
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
	private ArrayList<Material> conteudo;
	
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
		Agenda.getTodosEventos().add(this);
        confirmado = true;
        status = StatusEvento.ACONTECERA;
        participantes = new ArrayList<Estudante>();
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

	public ArrayList<Material> getConteudo() {
		return conteudo;
	}

	public void setConteudo(ArrayList<Material> conteudo) {
		this.conteudo = conteudo;
	}
    //#endregion
	
	public abstract float avaliarInstrutor(Nota nota, Estudante avaliador);

	public abstract float avaliarInstrutor(Nota nota, String comentario, Estudante avaliador);

	/**
     * Remove um {@link Evento} do aplicativo.
	 * <p>
	 * Este método faz com que nenhuma {@link Agenda} faça referência ao {@code Evento}.
	 * 
	 * @param solicitante
	 * @return {@code true} se removeu. {@code false} caso contrário, quando <b>solicitante</b> não tem permissão para tal
	 */
    public boolean removeEvento(Usuario solicitante) {
		boolean ehInstrutorResponsavel = (solicitante instanceof Instrutor && solicitante == getInstrutor());
		boolean ehModerador = solicitante instanceof Moderador;
		if (ehInstrutorResponsavel || ehModerador) {	
			for (Estudante participante : getParticipantes()) {
				participante.getAgenda().getEventos().remove(this);
			}
			this.getParticipantes().clear();
			Agenda.getTodosEventos().remove(this);
			getInstrutor().getAgenda().getEventos().remove(this);
			return true;
		}
		return false;
	}
	
	@Override
    public String toString() {
        String retorno = "Evento";
        retorno += "\nId: "+this.getId();
        retorno += "\nNome: "+this.getNome();
        retorno += "\nDescricao: "+this.getDescricao();
        retorno += "\nDisciplina: "+this.getDisciplina();
        retorno += "\nData: "+data.get(Calendar.DATE)+"/"+data.get(Calendar.MONTH)+"/"+data.get(Calendar.YEAR);
        retorno = retorno + "\nParticipantes = {";
        for (int i=0; i<this.getParticipantes().size(); i++) {
            retorno = retorno + this.getParticipantes().get(i).getPerfil().getNome() + " ";
        }
        retorno = retorno + "}";
        retorno += "\nConfirmado? "+this.isConfirmado()+"\n";
        return retorno;
    }
}

