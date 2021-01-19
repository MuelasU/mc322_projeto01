package src.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import src.model.Disciplina;
import src.model.Estudante;
import src.model.Instrutor;
import src.model.Moderador;
import src.model.Perfil;
import src.model.Usuario;
import src.model.agenda.Evento;
import src.model.agenda.Monitoria;

public class Controller {
    private static ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
    private static ArrayList<Evento> eventos = new ArrayList<Evento>();
    private static Usuario userSession = null;
    private static String mensagem = "Nenhuma mensagem foi disparada";
    
    /**
     * Realiza o cadastro de um {@link Usuario} no aplicativo
     * 
     * @param email
     * @param senha
     * @param perfil
     * @param tipoUsuario : 1 para Estudante; 2 para Instrutor; 3 para Moderador.
     * @return {@code true} caso cadastro foi realizado. {@code false} caso <b>email</b> já tenha sido cadastrado ou faltam informações
     */
    public static boolean cadastrar(String email, String senha, Perfil perfil, int tipoUsuario) {
        if (email.isEmpty() || senha.isEmpty()) {
            mensagem = "Estao faltando informacoes de cadastro";
            return false;
        }
        for(Usuario u : usuarios) {
            if (u.getEmail().equals(email)) {
                mensagem = "Email ja cadastrado";
                return false;
            }
        }
        Usuario usuario = (tipoUsuario == 1) ? new Estudante(email, senha, perfil)
                        : (tipoUsuario == 2) ? new Instrutor(email, senha, perfil, new ArrayList<Disciplina>())
                        : new Moderador(email, senha, perfil);
        usuarios.add(usuario);
        mensagem = "Usuario cadastrado com sucesso";
        return true;
    }

    /**
     * Inicia a sessão do {@link Usuario} no aplicativo
     * 
     * @param email
     * @param senha
     * @return {@code true} caso exista um cadastro com as informações passadas. {@code false} caso contrário
     */
    public static boolean login(String email, String senha) {
        for(Usuario u : usuarios) {
            if (u.getEmail().equals(email) && u.getSenha().equals(senha)) {
                userSession = u;
                mensagem = "Usuario logado. Bem vindo, " + userSession.getPerfil().getNome();
                return true;
            }
        }
        if (email.isEmpty() || senha.isEmpty()) {
            mensagem = "Eh preciso informar Email e Senha";
            return false;
        }
        mensagem = "Email ou Senha incorreto";
        return false;
    }

    /**
     * Encerra a sessão do {@link Usuario} no aplicativo
     * 
     * @return {@code true}
     */
    public static boolean logout() {
        mensagem = "Usuario deslogado com sucesso. Ate breve, " + userSession.getPerfil().getNome();
        System.out.println(mensagem);
        userSession = null;
        return true;
    }
    
    public static boolean solicitarMonitoria(String nome, String descricao, String dataString, Disciplina disciplina) {
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
            Calendar data = Calendar.getInstance();
            data.setTime(fmt.parse(dataString));
            if (nome.equals("")) {
                mensagem = "Faltam informacoes";
                System.out.println(mensagem);
                return false;
            }
            Monitoria pedido = ((Estudante) userSession).solicitarMonitoria(data, nome, descricao, disciplina);
            if (pedido == null) {
                mensagem = "Data invalida";
                System.out.println(mensagem);
                return false;
            }
            eventos.add(pedido);
            mensagem = "Pedido de monitoria realizado com sucesso. Agora eh soh esperar algum instrutor aceitar";
            System.out.println(mensagem);
            return true;
        } catch (ParseException e) {
            mensagem = "Data invalida";
            System.out.println(mensagem);
            return false;
        }
    }

    //#region Getters e Setters
    public static ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    protected static void setUsuarios(ArrayList<Usuario> usuarios) {
        Controller.usuarios = usuarios;
    }

    public static ArrayList<Evento> getEventos() {
        return eventos;
    }

    protected static void setEventos(ArrayList<Evento> eventos) {
        Controller.eventos = eventos;
    }

    public static Usuario getUserSession() {
        return userSession;
    }

    public static String getMensagem() {
        return mensagem;
    }

    public static void setMensagem(String mensagem) {
        Controller.mensagem = mensagem;
    }
    //#endregion
}
