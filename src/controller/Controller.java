package src.controller;

import java.util.ArrayList;

import src.model.Usuario;
import src.model.agenda.Evento;

public class Controller {
    private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
    private ArrayList<Evento> eventos = new ArrayList<Evento>();
    private String mensagem;
    
    public boolean cadastrar(Usuario usuario) {
        for(Usuario u : usuarios) {
            if (u.getEmail() == usuario.getEmail()) {
                mensagem = "Email ja cadastrado";
                return false;
            }
        }
        usuarios.add(usuario);
        mensagem = "Usuario cadastrado";
        return true;
    }
}
