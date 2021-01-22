package src.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;

import src.model.Disciplina;
import src.model.Estudante;
import src.model.Instrutor;
import src.model.Moderador;
import src.model.Perfil;
import src.model.Usuario;
import src.model.agenda.Agenda;
import src.model.agenda.Evento;
import src.model.agenda.Monitoria;

/**
 * Esta classe realiza todo o controle do aplicativo. Ela é responsável por fazer a comunicação entre a interface gráfica do aplicativo e o modelo criado para o sistema.
 */
public class Controller {
    private static ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
    private static Usuario userSession = null;
    private static String mensagem = "Nenhuma mensagem foi disparada";
    private static final SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");

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
    
    /**
     * 
     * @param nome
     * @param descricao
     * @param dataString
     * @param disciplina
     * @return {@code true} se a solicitação foi bem sucedida. {@code false} caso contrário
     * @see Estudante#solicitarMonitoria(Calendar, String, String, Disciplina)
     */
    public static boolean solicitarMonitoria(String nome, String descricao, String dataString, Disciplina disciplina) {
        try {
            Calendar data = Calendar.getInstance();
            data.setTime(fmt.parse(dataString));
            if (nome.isEmpty()) {
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
            mensagem = "Pedido de monitoria realizado com sucesso. Agora eh soh esperar algum instrutor aceitar";
            System.out.println(mensagem);
            System.out.println(Agenda.getTodosEventos().size());
            return true;
        } catch (ParseException e) {
            mensagem = "Data invalida";
            System.out.println(mensagem);
            return false;
        }
    }

    /**
     * 
     * @param nome
     * @param descricao
     * @param disciplina
     * @param dataString
     * @param duracaoString
     * @param capacidade
     * @param salaDeVideo
     * @param instrutor
     * @param ehAula
     * @return {@code true} se criou o {@link Evento}. {@code false} caso contrário
     * @see Usuario#criarEvento(String, String, Disciplina, Calendar, Duration, int, String, Instrutor, boolean)
     */
    public static boolean criarEvento(String nome, String descricao, Disciplina disciplina, String dataString, String duracaoString, int capacidade, String salaDeVideo, Instrutor instrutor, boolean ehAula) {
        try {
            if (nome.isEmpty()) {
                mensagem = "Faltam informacoes";
                System.out.println(mensagem);
                return false;
            }
            Calendar data = Calendar.getInstance();
            data.setTime(fmt.parse(dataString));
            int horas = Integer.parseInt(duracaoString);
            if (horas < 1 || horas > 4) {
                mensagem = "Duracao muito curta ou muito longa para um evento";
                System.out.println(mensagem);
                return false;
            }
            Duration duracao = Duration.ofHours(horas);
            Evento evento = userSession.criarEvento(nome, descricao, disciplina, data, duracao, capacidade, salaDeVideo, instrutor, ehAula);
            if (evento == null) {
                mensagem = "Data invalida";
                System.out.println(mensagem);
                return false;
            }
            mensagem = "Evento criado com sucesso";
            System.out.println(mensagem);
            System.out.println(Agenda.getTodosEventos().size());
            return true;
        } catch (ParseException e) {
            mensagem = "Data invalida";
            System.out.println(mensagem);
            return false;
        } catch (NumberFormatException e) {
            mensagem = "Duracao invalida";
            System.out.println(mensagem);
            return false;
        }
    }

    /**
     * 
     * @param evento
     * @return {@code true} se desinscreveu do {@link Evento}. {@code false} caso contrário
     * @see Estudante#desinscreverEvento(Evento)
     */
    public static boolean desinscreverEvento(Evento evento) {
        ((Estudante) userSession).desinscreverEvento(evento);
        mensagem = "Desinscricao realizada com sucesso";
        System.out.println(mensagem);
        return true;
    }

    /**
     * 
     * @param evento
     * @return {@code true} se inscreveu no {@link Evento}. {@code false} caso contrário
     * @see Estudante#desinscreverEvento(Evento)
     */
    public static boolean inscreverEvento(Evento evento) {
        boolean inscreveu = ((Estudante) userSession).inscreverEvento(evento);
        mensagem = inscreveu ? "Inscricao realizada com sucesso" : "Nao foi possivel realizar a inscricao";
        System.out.println(mensagem);
        return inscreveu;
    }
    
    /**
     * Confirma um {@link Evento} caso este não esteja confirmado e vice-versa.
     * 
     * @param evento
     * @return {@code true} se modificou a confirmacao do {@code Evento}. {@code false} caso contrário
     * @see Usuario#cancelarEvento(Evento)
     * @see Usuario#confirmarEvento(Evento)
     */
    public static boolean mudaConfirmacaoEvento(Evento evento) {
        boolean mudou = evento.isConfirmado() ? userSession.cancelarEvento(evento) : userSession.confirmarEvento(evento);
        mensagem = mudou ? "Evento " + (evento.isConfirmado() ? "confirmado" : "cancelado") + " com sucesso" : "Voce nao tem permissao para realizar esta operacao";
        System.out.println(mensagem);
        return mudou;
    }
    
    /**
     * 
     * @param monitoria
     * @param capacidadeString
     * @param salaDeVideo
     * @param duracaoString
     * @return {@code true} se aceitou a {@link Monitoria}. {@code false} caso contrário
     * @see Instrutor#aceitarMonitoria(Monitoria, Duration, int, String)
     */
    public static boolean aceitarMonitoria(Monitoria monitoria, String capacidadeString, String salaDeVideo, String duracaoString) {
        try {
            int horas = Integer.parseInt(duracaoString);
            if (horas < 1 || horas > 4) {
                mensagem = "Duracao muito curta ou muito longa para um evento";
                System.out.println(mensagem);
                return false;
            }
            Duration duracao = Duration.ofHours(horas);
            int capacidade = Integer.parseInt(capacidadeString);
            if (capacidade > Evento.CAPACIDADE_MAXIMA || capacidade < Evento.CAPACIDADE_MINIMA) {
                mensagem = "Capacidade deve estar entre " + Evento.CAPACIDADE_MINIMA + " e " + Evento.CAPACIDADE_MAXIMA;
                System.out.println(mensagem);
                return false;
            }
            ((Instrutor) userSession).aceitarMonitoria(monitoria, duracao, capacidade, salaDeVideo);
            mensagem = "Monitoria aceita. Agora eh sua responsabilidade ministra-la";
            System.out.println(mensagem);
            return true;
        } catch (NumberFormatException e) {
            mensagem = "Entrada numerica nao reconhecida";
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
