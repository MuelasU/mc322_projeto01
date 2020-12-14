package src.agenda;

import java.util.ArrayList;
import java.util.Calendar;
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

    /**
     * Este método sempre retornará a {@link Agenda} contendo todos {@link Evento} criados no aplicativo. 
     * <p>
     * Por causa disso ele deve preceder qualquer filtragem de escopo global, por exemplo:
     * <pre>
     *  Agenda.getAgendaGeral().filtraPorDisciplina(Disciplina.MATEMATICA).getEventos()
     * </pre>
     * retorna um <code>ArrayList</code> contendo todos eventos de Matemática criados no aplicativo. 
     *  
     * @return {@code Agenda} geral
     */
    public static Agenda getAgendaGeral() {
        return new Agenda(getTodosEventos());
    }

    //#region Metodos de Filtro
    /**
     * @param disciplina
     * @return nova {@code Agenda} contendo apenas {@code Evento} da {@code disciplina} especificada
     */
    public Agenda filtraPorDisciplina(Disciplina disciplina) {
        List<Evento> novo = eventos.stream().filter(e -> (e.getDisciplina() == disciplina)).collect(Collectors.toList());
        return new Agenda(new ArrayList<Evento>(novo));
    }
    
    /**
     * @param dia
     * @return nova {@code Agenda} contendo apenas {@code Evento} do {@code dia} especificado
     */
    public Agenda filtraPorDia(Calendar dia) {
        List<Evento> novo = (eventos.stream()
                                    .filter(e -> (  //checa se o evento ocorre no dia especificado
                                        (e.getData().get(Calendar.DAY_OF_YEAR) == dia.get(Calendar.DAY_OF_YEAR)) &&
                                        (e.getData().get(Calendar.YEAR) == dia.get(Calendar.YEAR))
                                    ))
                                    .collect(Collectors.toList()));
        return new Agenda(new ArrayList<Evento>(novo));
    }

    /**
     * @param status
     * @return nova {@code Agenda} contendo apenas {@code Evento} com o {@code status} especificado
     */
    public Agenda filtraPorStatus(StatusEvento status) {
        List<Evento> novo = eventos.stream().filter(e -> (e.getStatus() == status)).collect(Collectors.toList());
        return new Agenda(new ArrayList<Evento>(novo));
    }

    /**
     * @param estaConfirmado : {@code true} para filtrar eventos confirmados. {@code false} caso contrário
     * @return nova {@code Agenda} contendo apenas {@code Evento} com {@code estaConfirmado} especificado
     */
    public Agenda filtraPorConfirmacao(boolean estaConfirmado) {
        List<Evento> novo = eventos.stream().filter(e -> (e.isConfirmado() == estaConfirmado)).collect(Collectors.toList());
        return new Agenda(new ArrayList<Evento>(novo));
    }

    /**
     * @param tipo : {@code true} para filtrar por {@link Aula}. {@code false} para filtrar por {@link Monitoria}
     * @return nova {@code Agenda} contendo apenas {@code Evento} o {@code tipo} especificado
     */
    public Agenda filtraPorTipo(boolean tipo) {
        List<Evento> novo = eventos.stream().filter(e -> e.getClass().equals(tipo ? Aula.class : Monitoria.class)).collect(Collectors.toList());
        return new Agenda(new ArrayList<Evento>(novo));
    }
    //#endregion
}
