package src.agenda;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import src.Disciplina;

public class Agenda {
    private static ArrayList<Evento> todosEventos = new ArrayList<Evento>();
    private ArrayList<Evento> eventos;

    public Agenda() {
        eventos = new ArrayList<Evento>();
    }

    public Agenda(ArrayList<Evento> eventos) {
        this.eventos = eventos;
    }

    //#region Getters
    public static ArrayList<Evento> getTodosEventos() {
        return todosEventos;
    }

    public ArrayList<Evento> getEventos() {
        return eventos;
    }
    //#endregion

    public static Agenda getAgendaGeral() {
        return new Agenda(getTodosEventos());
    }

    //#region Metodos de Filtro
    // public Agenda filtraPorDisciplina(Disciplina disciplina) {
    //     List<Evento> novo = eventos.stream().filter(e -> (e.getDisciplina() == disciplina)).collect(Collectors.toList());
    //     return new Agenda(new ArrayList<Evento>(novo));
    // }
    //#endregion
}
