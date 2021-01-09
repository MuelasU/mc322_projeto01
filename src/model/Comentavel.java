package src.model;

import java.util.ArrayList;

import src.model.forum.Comentario;

public interface Comentavel {
    public ArrayList<Comentario> getComentarios();
    public void setComentarios(ArrayList<Comentario> comentario);
    /**
     * Este método adiciona um novo {@link Comentario} no {@code Comentario}.
     * 
     * @param texto
     * @param usuario
     * @return referência para o {@code Comentario} adicionado
     */
    public Comentario comentar(String texto, Usuario usuario);
}
