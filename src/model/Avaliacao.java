package src.model;

import java.util.ArrayList;

import src.model.agenda.Evento;

/**
 * Representa uma ficha avaliativa de um {@link Instrutor} em específico.
 * <p>
 * Ela é composta por uma nota média e um conjunto de comentários de feedback feitos pelos {@link Estudante} que participaram de algum {@link Evento} dado pelo {@code Instrutor}.
 * 
 * @see Nota
 */
public class Avaliacao {
    private int numeroAvaliacoesAula;
    private int numeroAvaliacoesMonitoria;
    private float mediaAula;
    private float mediaMonitoria;
    private ArrayList<String> comentarios;

    public Avaliacao() {
        numeroAvaliacoesAula = 0;
        numeroAvaliacoesMonitoria = 0;
        mediaAula = 0f;
        mediaMonitoria = 0f;
        comentarios = new ArrayList<String>();
    }

    //#region Getters e Setters
    public int getNumeroAvaliacoes() {
        return numeroAvaliacoesAula + numeroAvaliacoesMonitoria;
    }

    public float getMedia() {
        return (mediaAula + mediaMonitoria) / 2f;
    }

    public int getNumeroAvaliacoesAula() {
        return numeroAvaliacoesAula;
    }

    public void setNumeroAvaliacoesAula(int numeroAvaliacoesAula) {
        this.numeroAvaliacoesAula = numeroAvaliacoesAula;
    }

    public int getNumeroAvaliacoesMonitoria() {
        return numeroAvaliacoesMonitoria;
    }

    public void setNumeroAvaliacoesMonitoria(int numeroAvaliacoesMonitoria) {
        this.numeroAvaliacoesMonitoria = numeroAvaliacoesMonitoria;
    }

    public float getMediaAula() {
        return mediaAula;
    }

    public void setMediaAula(float mediaAula) {
        this.mediaAula = mediaAula;
    }

    public float getMediaMonitoria() {
        return mediaMonitoria;
    }

    public void setMediaMonitoria(float mediaMonitoria) {
        this.mediaMonitoria = mediaMonitoria;
    }

    public ArrayList<String> getComentarios() {
        return comentarios;
    }

    public void setComentarios(ArrayList<String> comentarios) {
        this.comentarios = comentarios;
    }
    //#endregion

    /**
     * Avalia o {@link Instrutor} com uma {@link Nota}.
     * 
     * @param nota
     * @return nova nota média do {@code Instrutor}
     */
    public float avaliarAula(Nota nota) {
        boolean jaAvaliado = numeroAvaliacoesAula > 0;
        float denominador = jaAvaliado ? 2f : 1f;
        numeroAvaliacoesAula++;
        setMediaAula( (getMediaAula() + (float) nota.getValor()) / denominador);
        return getMediaAula();
    }

    /**
     * Avalia o {@link Instrutor} com uma {@link Nota}.
     * 
     * @param nota
     * @return nova nota média do {@code Instrutor}
     */
    public float avaliarMonitoria(Nota nota) {
        boolean jaAvaliado = numeroAvaliacoesMonitoria > 0;
        float denominador = jaAvaliado ? 2f : 1f;
        numeroAvaliacoesMonitoria++;
        setMediaMonitoria( (getMediaMonitoria() + (float) nota.getValor()) / denominador);
        return getMediaMonitoria();
    }
    
    /**
     * Avalia o {@link Instrutor} com uma {@link Nota} e acrescenta um comentário de feedback.
     * 
     * @param nota
     * @param comentario
     * @return nova nota média do {@code Instrutor}
     */
    public float avaliarAula(Nota nota, String comentario) {
        avaliarAula(nota);
        getComentarios().add(comentario);
        return getMediaAula();
    }

    /**
     * Avalia o {@link Instrutor} com uma {@link Nota} e acrescenta um comentário de feedback.
     * 
     * @param nota
     * @param comentario
     * @return nova nota média do {@code Instrutor}
     */
    public float avaliarMonitoria(Nota nota, String comentario) {
        avaliarMonitoria(nota);
        getComentarios().add(comentario);
        return getMediaMonitoria();
    }

    @Override
    public String toString() {
    	String retorno = "Avaliacoes";
    	retorno += "\nMedia: " + this.getMediaAula() + "de Aula e " + this.getMediaMonitoria() + "de Monitoria\n";
    	retorno += "\nComentarios: "+this.getComentarios()+"\n";
    	return retorno;
    }
}
