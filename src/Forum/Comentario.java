package src.forum;

import java.util.ArrayList;

import src.Usuario;
import src.repositorio.Material;

/**
 * É um tipo de {@link Mensagem} útil para responder ou ressaltar alguma informação em alguma {@link Discussao} ou algum {@link Material}.
 * <p>
 * Um {@code Comentario} pode ainda ser avaliado positivamente ou negativamente a partir de {@code upvotes} e {@code downvotes}.
 */
public class Comentario extends Mensagem{
    private static int numeroComentarios = 0;
	private int id;
	private int upvotes;
	private int downvotes;
	private ArrayList<Comentario> comentarios;
	
	public Comentario(String texto, Usuario dono){
		super(texto,dono);
		id = numeroComentarios;
        numeroComentarios++;
        upvotes = 0;
		downvotes = 0;
		comentarios = new ArrayList<Comentario>();
	}
	
	//#region Getters e Setters
	public static int getNumeroComentarios() {
		return numeroComentarios;
	}

	public static void setNumeroComentarios(int numeroComentarios) {
		Comentario.numeroComentarios = numeroComentarios;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUpvotes() {
		return upvotes;
	}

	public void setUpvotes(int upvotes) {
		this.upvotes = upvotes;
	}

	public int getDownvotes() {
		return downvotes;
	}

	public void setDownvotes(int downvotes) {
		this.downvotes = downvotes;
	}

	public ArrayList<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(ArrayList<Comentario> comentarios) {
		this.comentarios = comentarios;
	}
	//#endregion
	
	/**
	 * Este método avalia positivamente algum {@link Comentario}.
	 */
	public void upvote() {
		upvotes++;
	}
	
	/**
	 * Este método avalia negativamente algum {@link Comentario}.
	 */
	public void downvote() {
		downvotes++;
	}
	
	/**
     * Este método adiciona um novo {@link Comentario} no {@code Comentario}.
     * 
     * @param texto
     * @param usuario
     * @return referência para o {@code Comentario} adicionado
     */
	public Comentario comentar(String texto, Usuario usuario) {
		Comentario comentario = new Comentario(texto, usuario);
        this.comentarios.add(comentario);
        return comentario;
	}
	
	@Override
	public String toString() {
		String out = "";
		out += "Id do Comentario: " + getId() + "\n";
		out += getUpvotes() +" Curtiram :)\n";
		out += getDownvotes() + " Nao Curtiram :(\n";
		out += "Respostas ao comentario:\n" + getComentarios();
		return out;
	}
}
