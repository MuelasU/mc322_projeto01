package src.agenda;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;

import src.Disciplina;
import src.Estudante;
import src.Instrutor;
import src.Nota;
import src.repositorio.Material;

public class Monitoria extends Evento{
	private static ArrayList<Monitoria> solicitacoes = new ArrayList<Monitoria>();
	private ArrayList<Material> conteudo;
	private String exerciciosSolicitados;
	private String exerciciosPlanejados;
	
	public Monitoria(String nome, String descricao, Disciplina disciplina, Calendar data, Duration duracao, int capacidade, String salaDeVideo, Instrutor instrutor) {
		super(nome, descricao, disciplina, data, duracao, capacidade, salaDeVideo, instrutor);
		conteudo = new ArrayList<Material>();
	}
	
	//Usado para Estudante solicitar Monitoria
	public Monitoria(String nome, String descricao, Disciplina disciplina, Calendar data) {
		super(nome, descricao, disciplina, data);
		conteudo = new ArrayList<Material>();
		solicitacoes.add(this);
	}

	//#region Getters and Setters
    public static ArrayList<Monitoria> getSolicitacoes() {
        return solicitacoes;
    }

	public ArrayList<Material> getConteudo() {
		return conteudo;
    }
    
	public void setConteudo(ArrayList<Material> conteudo) {
		this.conteudo = conteudo;
    }

	public String getExerciciosSolicitados() {
		return exerciciosSolicitados;
    }
    
	public void setExerciciosSolicitados(String exerciciosSolicitados) {
		this.exerciciosSolicitados = exerciciosSolicitados;
    }
    
	public String getExerciciosPlanejados() {
		return exerciciosPlanejados;
    }
    
	public void setExerciciosPlanejados(String exerciciosPlanejados) {
		this.exerciciosPlanejados = exerciciosPlanejados;
    }
	//#endregion
	
	/**
	 * Este método permite a algum {@link Estudante} participante submeter exercícios para resolução.
	 * 
	 * @param exercicios : separado por vírgulas apenas
	 * @return {@code true} se submeteu. {@code false} caso contrário, quando o <b>solicitante</b> não tenha participado da monitoria
	 */
	public boolean solicitarExercicios(String exercicios, Estudante solicitante) {
		boolean eventoAindaNaoFoi = getStatus() != StatusEvento.ACONTECEU;
		boolean solicitanteParticipaDoEvento = getParticipantes().contains(solicitante);
		if (eventoAindaNaoFoi && solicitanteParticipaDoEvento) {
			this.exerciciosSolicitados += exercicios + ",";
			return true;
		}
		return false;
	}

	/**
     * Avalia o {@link Instrutor} com uma {@link Nota}.
     * 
     * @param nota
	 * @param avaliador
     * @return nova nota média do {@code Instrutor}. {@code -1} caso <b>avaliador</b> não tenha participado da monitoria.
     */
	public float avaliarInstrutor(Nota nota, Estudante avaliador) {
		boolean eventoAcontece = getStatus() != StatusEvento.ACONTECERA;
		boolean avaliadorParticipouDoEvento = getParticipantes().contains(avaliador);
		if (eventoAcontece && avaliadorParticipouDoEvento) {
			return getInstrutor().getAvaliacao().avaliar(nota);
		}
		return -1f;	
	}

	/**
     * Avalia o {@link Instrutor} com uma {@link Nota} e acrescenta um comentário de feedback.
     * 
     * @param nota
     * @param comentario
     * @return nova nota média do {@code Instrutor}. {@code -1} caso <b>avaliador</b> não tenha participado da monitoria.
     */
	public float avaliarInstrutor(Nota nota, String comentario, Estudante avaliador) {
		boolean eventoAcontece = getStatus() != StatusEvento.ACONTECERA;
		boolean avaliadorParticipouDoEvento = getParticipantes().contains(avaliador);
		if (eventoAcontece && avaliadorParticipouDoEvento) {
			return getInstrutor().getAvaliacao().avaliar(nota, comentario);
		}
		return -1f;
	}

	@Override
	public String toString() {
		String retorno = "Monitoria";
		retorno += "\nConteudo: "+this.getConteudo();
		retorno += "\nExercicios solicitados: "+this.getExerciciosSolicitados();
		retorno += "\nExercicios planejados: "+this.getExerciciosPlanejados()+"\n";
		retorno += super.toString();
		return retorno;
	}
}
