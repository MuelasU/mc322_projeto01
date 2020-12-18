package src;

import src.agenda.*;
import src.repositorio.*;
import src.forum.*;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.ArrayList;
import java.time.Duration;
import java.time.Instant;

public class Main {
    public static void main(String[] args) {
		//#region Instanciacao de Usuarios
		//Criacao de disciplinas de interesse p/ o instrutor
    	ArrayList<Disciplina> disciplinas_instrutor1 = new ArrayList<Disciplina>();
    	disciplinas_instrutor1.add(Disciplina.MATEMATICA);
        disciplinas_instrutor1.add(Disciplina.FISICA);
        
        //Instanciacao dos objetos perfis para passar para usuario
    	Perfil perfil_estudante1 = new Perfil("Luigi", Estado.SAO_PAULO, "Jundiai", Escolaridade.FUNDAMENTAL2, new GregorianCalendar(2001, 8, 25));
    	Perfil perfil_estudante2 = new Perfil("Luiz", Estado.SAO_PAULO, "Sao Paulo", Escolaridade.MEDIO, new GregorianCalendar(1999, 10, 4));
    	Perfil perfil_instrutor1 = new Perfil("Gabriel", Estado.MATO_GROSSO_DO_SUL, "Campo Grande", Escolaridade.SUPERIOR, new GregorianCalendar(2001, 9, 4));
    	Perfil perfil_moderador1 = new Perfil("Joao", Estado.ACRE, "Rio Branco", Escolaridade.POS_DOUTORADO, new GregorianCalendar(2000, 4, 26));
        
        //Instanciacao dos usuarios na forma de suas subclasses
    	Estudante estudante1 = new Estudante("luigi@gmail.com", "123", perfil_estudante1);
    	Estudante estudante2 = new Estudante("luiz@gmail.com", "123", perfil_estudante2);
    	Instrutor instrutor1 = new Instrutor("muelas@gmail.com", "123", perfil_instrutor1, disciplinas_instrutor1);
    	Moderador moderador1 = new Moderador("joao@gmail.com", "123", perfil_moderador1);
        /*System.out.println(estudante1);
    	System.out.println(estudante2);
    	System.out.println(instrutor1);
		System.out.println(moderador1);*/ //teste dos objetos instanciados
		//#endregion

		//#region Teste de Eventos
		//As tres linhas abaixo correspondem a criacao de um tempo "Duration" padrao
    	Instant start = Instant.parse("2020-01-01T10:00:00.00Z");
    	Instant end = Instant.parse("2020-01-02T10:00:00.00Z");
		Duration duracao_padrao = Duration.between(start, end);
		
    	//Eventos, aulas e monitorias serao criadas atraves dos metodos dos usuarios
    	Monitoria monitoria1 = estudante1.solicitarMonitoria(new GregorianCalendar(2020, 12, 14),"Monitoria de ingles", "Gostaria de...", Disciplina.INGLES); //teste do metodo solicitarMonitoria de Estudante
    	monitoria1 = instrutor1.aceitarMonitoria(monitoria1, duracao_padrao, 20, "meet"); //teste do metodo aceitarMonitoria de Instrutor
        //System.out.println(monitoria1);
		
    	Aula aula1 = instrutor1.criarAula("Aula de mateca", "aula ordinaria", Disciplina.MATEMATICA, new GregorianCalendar(2020, 12, 15), duracao_padrao, 50, "meet.com/...");
    	//System.out.println(aula1);
    	estudante2.inscreverEvento(aula1);
    	//System.out.println(aula1);
    	estudante2.desinscreverEvento(aula1);
    	//System.out.println(aula1);
        
    	//As 6 linhas abaixo servem tanto para testar o metodo criarAula de monitor quanto para ver se a capacidade nao e excedida
        Aula aula2 = moderador1.criarAula("aula teste", "", Disciplina.BIOLOGIA, new GregorianCalendar(2020,11,18), duracao_padrao, 1, "", instrutor1);
    	//System.out.println(aula2);
    	estudante1.inscreverEvento(aula2);
    	//System.out.println(aula2);
    	estudante2.inscreverEvento(aula2);
    	//System.out.println(aula2);
    	moderador1.cancelarEvento(aula1);
        //System.out.println(aula1); //teste do metodo cancelarEvento de moderador
        
    	//As 5 linhas abaixo testam o metodo remover evento, inclusive com um estudante inscrito
    	Monitoria monitoria2 = moderador1.criarMonitoria("a", "", Disciplina.GEOGRAFIA, new GregorianCalendar(2020,12,16), duracao_padrao, 10, "meet...", instrutor1);
    	//System.out.println(monitoria2); //teste de criarMonitoria do moderador
    	estudante1.inscreverEvento(monitoria2);
    	//System.out.println(monitoria2);
    	monitoria2.removeEvento(instrutor1);
        //System.out.println(estudante1);
        //System.out.println(monitoria2);
        
        System.out.println(aula2);
    	System.out.println(aula2.avaliarInstrutor(Nota.BOM, estudante1));
		System.out.println(instrutor1.getAvaliacao());
		//#endregion
	
		//#region Teste de Repositorio
		Diretorio root = new Diretorio();
		moderador1.criarDiretorio("matematica", "contem todo conteudo de matematica", root);
		//#endregion
	}
}
