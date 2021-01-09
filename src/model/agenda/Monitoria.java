package src.model.agenda;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;

import src.model.Disciplina;
import src.model.Estudante;
import src.model.Instrutor;
import src.model.Nota;

public class Monitoria extends Evento {
	private static ArrayList<Monitoria> solicitacoes = new ArrayList<Monitoria>();
	private String exerciciosSolicitados;
	private String exerciciosPlanejados;
	
	public Monitoria(String nome, String descricao, Disciplina disciplina, Calendar data, Duration duracao, int capacidade, String salaDeVideo, Instrutor instrutor) {
		super(nome, descricao, disciplina, data, duracao, capacidade, salaDeVideo, instrutor);
	}
	
	//Usado para Estudante solicitar Monitoria
	public Monitoria(String nome, String descricao, Disciplina disciplina, Calendar data) {
		super(nome, descricao, disciplina, data);
		solicitacoes.add(this);
	}

	//#region Getters and Setters
    public static ArrayList<Monitoria> getSolicitacoes() {
        return solicitacoes;
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

	@Override
	public float avaliarInstrutor(Nota nota, Estudante avaliador) {
		boolean eventoAcontece = getStatus() != StatusEvento.ACONTECERA;
		boolean avaliadorParticipouDoEvento = getParticipantes().contains(avaliador);
		if (eventoAcontece && avaliadorParticipouDoEvento) {
			return getInstrutor().getAvaliacao().avaliarMonitoria(nota);
		}
		return -1f;	
	}

	@Override
	public float avaliarInstrutor(Nota nota, String comentario, Estudante avaliador) {
		boolean eventoAcontece = getStatus() != StatusEvento.ACONTECERA;
		boolean avaliadorParticipouDoEvento = getParticipantes().contains(avaliador);
		if (eventoAcontece && avaliadorParticipouDoEvento) {
			return getInstrutor().getAvaliacao().avaliarMonitoria(nota, comentario);
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
