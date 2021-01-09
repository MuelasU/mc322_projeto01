package src.model.forum;

import java.util.ArrayList;

import src.model.Comentavel;
import src.model.Usuario;
import src.model.repositorio.Material;

/**
 * É um tipo de {@link Mensagem} útil para responder ou ressaltar alguma informação em alguma {@link Discussao} ou algum {@link Material}.
 * <p>
 * Um {@code Comentario} pode ainda ser avaliado positivamente ou negativamente a partir de {@code upvotes} e {@code downvotes}.
 */
public class Comentario extends Mensagem implements Comentavel, Comparable<Comentario> {
    private static int numeroComentarios = 0;
	private int id;
	private int upvotes;
	private int downvotes;
	private ArrayList<Usuario> votantes;
	private ArrayList<Comentario> comentarios;
	
	public Comentario(String texto, Usuario dono){
		super(texto,dono);
		id = numeroComentarios;
        numeroComentarios++;
        upvotes = 0;
		downvotes = 0;
		votantes = new ArrayList<Usuario>();
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

	public ArrayList<Usuario> getVotantes() {
		return votantes;
	}

	@Override
	public ArrayList<Comentario> getComentarios() {
		return comentarios;
	}

	@Override
	public void setComentarios(ArrayList<Comentario> comentarios) {
		this.comentarios = comentarios;
	}
	//#endregion
	
	/**
	 * Este método avalia positivamente algum {@link Comentario}. Um {@link Usuario} só pode votar uma vez
	 * 
	 * @param votante
	 */
	public void upvote(Usuario votante) {
		if (!this.getVotantes().contains(votante)) {
			upvotes++;
			this.getVotantes().add(votante);
		}
	}
	
	/**
	 * Este método avalia negativamente algum {@link Comentario}. Um {@link Usuario} só pode votar uma vez
	 *  
	 * @param votante
	 */
	public void downvote(Usuario votante) {
		if (!this.getVotantes().contains(votante)) {
			downvotes++;
			this.getVotantes().add(votante);
		}
	}
	
	@Override
	public Comentario comentar(String texto, Usuario usuario) {
		Comentario comentario = new Comentario(texto, usuario);
        this.comentarios.add(comentario);
        return comentario;
	}

	/**
	 * Compara dois comentarios a partir do saldo de <em>upvotes</em> menos <em>downvotes</em>
	 */
	@Override
	public int compareTo(Comentario o) {
		int esteSaldoDeVotos = this.getUpvotes() - this.getDownvotes();
		int outroSaldoDeVotos = o.getUpvotes() - o.getDownvotes();
		return Integer.compare(esteSaldoDeVotos, outroSaldoDeVotos);
	}
	
	@Override
	public String toString() {
		String out = "";
		out += this.getTexto() + "\n";
		out += "Id do Comentario: " + getId() + "\n";
		out += getUpvotes() +" Curtiram :)\n";
		out += getDownvotes() + " Nao Curtiram :(\n";
		out += "Respostas ao comentario: ";
        for (int i=0; i<this.getComentarios().size(); i++) {
            out += "\n"+this.getComentarios().get(i).getTexto()+"; Gostei: "+this.getComentarios().get(i).getUpvotes()+"; N�o gostei: "+this.getComentarios().get(i).getDownvotes();
        }
        out += "\n";
		return out;
	}
}
