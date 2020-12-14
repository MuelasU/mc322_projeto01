package src.forum;

import java.util.Calendar;

import src.Usuario;

/**
 * Este elemento representa um modo de interação entre os {@link Usuario} do aplicativo.
 * <p>
 * É possível associar uma mensagem a algum {@link Material} ou a outra {@code Mensagem}.
 */
public class Mensagem {
	private final Usuario dono;
	private Calendar dataHora;
	private String texto;
	
	public Mensagem(String texto, Usuario dono) {
		this.dono = dono;
		this.dataHora = Calendar.getInstance();
		this.texto = texto;
	}
	
	//#region Getters e Setters
	public Usuario getDono() {
		return dono;
	}
	
	public Calendar getDataHora() {
		return dataHora;
    }
    public void setDataHora(Calendar dataHora) {
        this.dataHora = dataHora;
    }
	
	public String getTexto() {
		return texto;
    }
    
	public void setTexto(String txt) {
		texto = txt;
    }
    //#endregion

	@Override
	public String toString() {
		String out = "";
		out += "Usuario: " + getDono().getPerfil().getNome() + "\n";
		out += "Data: " + getDataHora().get(Calendar.DAY_OF_MONTH) +"/"+ getDataHora().get(Calendar.MONTH) +"/"+ getDataHora().get(Calendar.YEAR) + " ";
		out += "Hora: " + getDataHora().get(Calendar.HOUR_OF_DAY) + ":" + getDataHora().get(Calendar.MINUTE) + ":" + getDataHora().get(Calendar.SECOND) + "\n";
		out += "Mensagem:\n" + getTexto();
		return out;
	}
	
	
}

