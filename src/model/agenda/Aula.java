package src.model.agenda;

import java.time.Duration;
import java.util.Calendar;

import src.model.Disciplina;
import src.model.Estudante;
import src.model.Instrutor;
import src.model.Nota;

/**
 * É um {@link Evento} no qual um conteúdo é ministrado por algum {@link Instrutor}.
 * <p>
 * Possui, adicionalmente, atributos "exercícios recomendados" e "conteúdo".
 */
public class Aula extends Evento{
	private String exerciciosRecomendados;
	
	public Aula(String nome, String descricao, Disciplina disciplina, Calendar data, Duration duracao, int capacidade, String salaDeVideo, Instrutor instrutor) {
		super(nome, descricao, disciplina, data, duracao, capacidade, salaDeVideo, instrutor);
	}

	//#region Getters and Setters
	public String getExerciciosRecomendados() {
		return exerciciosRecomendados;
	}

	public void setExerciciosRecomendados(String exerciciosRecomendados) {
		this.exerciciosRecomendados = exerciciosRecomendados;
	}
	//#endregion
	
	@Override
	public float avaliarInstrutor(Nota nota, Estudante avaliador) {
		boolean eventoAcontece = getStatus() != StatusEvento.ACONTECERA;
		boolean avaliadorParticipouDoEvento = getParticipantes().contains(avaliador);
		if (eventoAcontece && avaliadorParticipouDoEvento) {
			return getInstrutor().getAvaliacao().avaliarAula(nota);
		}
		return -1f;	
	}
	
	@Override
	public float avaliarInstrutor(Nota nota, String comentario, Estudante avaliador) {
		boolean eventoAcontece = getStatus() != StatusEvento.ACONTECERA;
		boolean avaliadorParticipouDoEvento = getParticipantes().contains(avaliador);
		if (eventoAcontece && avaliadorParticipouDoEvento) {
			return getInstrutor().getAvaliacao().avaliarAula(nota, comentario);
		}
		return -1f;
	}

	@Override
	public String toString() {
		String retorno = "Aula";
		retorno += "\nConteudo: "+this.getConteudo()+"\n";
		retorno += super.toString();
		return retorno;
	}
}
