package src.agenda;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;

import src.Disciplina;
import src.Estudante;
import src.Instrutor;
import src.Nota;
import src.repositorio.Material;

/**
 * É um {@link Evento} no qual um conteúdo é ministrado por algum {@link Instrutor}.
 * <p>
 * Possui, adicionalmente, atributos "exercícios recomendados" e "conteúdo".
 */
public class Aula extends Evento{
	private ArrayList<Material> conteudo;
	private String exerciciosRecomendados;
	
	public Aula(String nome, String descricao, Disciplina disciplina, Calendar data, Duration duracao, int capacidade, String salaDeVideo, Instrutor instrutor) {
		super(nome, descricao, disciplina, data, duracao, capacidade, salaDeVideo, instrutor);
		conteudo = new ArrayList<Material>();
	}

	//#region Getters and Setters
	public ArrayList<Material> getConteudo() {
		return conteudo;
	}

	public void setConteudo(ArrayList<Material> conteudo) {
		this.conteudo = conteudo;
	}

	public String getExerciciosRecomendados() {
		return exerciciosRecomendados;
	}

	public void setExerciciosRecomendados(String exerciciosRecomendados) {
		this.exerciciosRecomendados = exerciciosRecomendados;
	}
	//#endregion
	
	/**
     * Avalia o {@link Instrutor} com uma {@link Nota}.
     * 
     * @param nota
	 * @param avaliador
     * @return nova nota média do {@code Instrutor}. {@code -1} caso <b>avaliador</b> não tenha participado da aula.
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
     * @return nova nota média do {@code Instrutor}. {@code -1} caso <b>avaliador</b> não tenha participado da aula.
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
		String retorno = "Aula";
		retorno += "\nConteudo: "+this.getConteudo()+"\n";
		retorno += super.toString();
		return retorno;
	}
}
