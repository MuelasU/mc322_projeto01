package src;

import java.util.ArrayList;

import src.agenda.Evento;

/**
 * Representa uma ficha avaliativa de um {@link Instrutor} em específico.
 * <p>
 * Ela é composta por uma nota média e um conjunto de comentários de feedback feitos pelos {@link Estudante} que participaram de algum {@link Evento} dado pelo {@code Instrutor}.
 * 
 * @see Nota
 */
public class Avaliacao {
    public int numeroAvaliacoes;
    public float media;
    public ArrayList<String> comentarios;

    public Avaliacao() {
        numeroAvaliacoes=0;
        media=0f;
        comentarios = new ArrayList<String>();
    }

    //#region Getters e Setters
    public int getNumeroAvaliacoes() {
        return numeroAvaliacoes;
    }

    public void setNumeroAvaliacoes(int numeroAvaliacoes) {
        this.numeroAvaliacoes = numeroAvaliacoes;
    }

    public float getMedia() {
        return media;
    }

    public void setMedia(float media) {
        this.media = media;
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
    public float avaliar(Nota nota) {
        numeroAvaliacoes++;
        setMedia( (getMedia() + (float) nota.getValor()) / 2f);
        return getMedia();
    }
    
    /**
     * Avalia o {@link Instrutor} com uma {@link Nota} e acrescenta um comentário de feedback.
     * 
     * @param nota
     * @param comentario
     * @return nova nota média do {@code Instrutor}
     */
    public float avaliar(Nota nota, String comentario) {
        numeroAvaliacoes++;
        setMedia( (getMedia() + (float) nota.getValor()) / 2f);
        getComentarios().add(comentario);
        return getMedia();
    }

    @Override
    public String toString() {
        return "Avaliacao";
    }
}
