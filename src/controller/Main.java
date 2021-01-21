package src.controller;

import java.io.File;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import src.model.Disciplina;
import src.model.Escolaridade;
import src.model.Estado;
import src.model.Estudante;
import src.model.Instrutor;
import src.model.Moderador;
import src.model.Nota;
import src.model.Perfil;
import src.model.Usuario;
import src.model.agenda.Evento;
import src.model.agenda.Monitoria;
import src.model.agenda.StatusEvento;
import src.model.forum.Comentario;
import src.model.forum.Discussao;
import src.model.repositorio.Diretorio;
import src.model.repositorio.Material;
import src.view.Login;

public class Main {
    public static void main(String[] args) {
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

		// #region Instanciacao de Usuarios
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
		usuarios.add(estudante1);
    	Estudante estudante2 = new Estudante("luiz@gmail.com", "123", perfil_estudante2);
		usuarios.add(estudante2);

    	Instrutor instrutor1 = new Instrutor("muelas@gmail.com", "123", perfil_instrutor1, disciplinas_instrutor1);
		usuarios.add(instrutor1);
		
    	Moderador moderador1 = new Moderador("joao@gmail.com", "123", perfil_moderador1);
		usuarios.add(moderador1);
        /*System.out.println(estudante1);
    	System.out.println(estudante2);
    	System.out.println(instrutor1);
		System.out.println(moderador1);*/ //teste dos objetos instanciados
		//#endregion
 
		// #region Teste de Eventos
		//As tres linhas abaixo correspondem a criacao de um tempo "Duration" padrao
    	Instant start = Instant.parse("2020-01-20T10:00:00.00Z");
    	Instant end = Instant.parse("2020-01-20T12:00:00.00Z");
		Duration duracao_padrao = Duration.between(start, end);
		
    	//Eventos, aulas e monitorias serao criadas atraves dos metodos dos usuarios
		Evento monitoria1 = estudante1.solicitarMonitoria(new GregorianCalendar(2021, 2, 14),"Monitoria de ingles", "Gostaria de revisar verbo to be", Disciplina.INGLES); //teste do metodo solicitarMonitoria de Estudante
    	monitoria1 = instrutor1.aceitarMonitoria((Monitoria) monitoria1, duracao_padrao, 20, "meet.com/..."); //teste do metodo aceitarMonitoria de Instrutor
        //System.out.println(monitoria1);
		
		Evento aula1 = instrutor1.criarEvento("Aula de matematica", "Aula de trigonometria focada no enem", Disciplina.MATEMATICA, new GregorianCalendar(2021, 01, 15), duracao_padrao, 50, "meet.com/...", null, true);
		// System.out.println(aula1);
    	estudante2.inscreverEvento(aula1);
    	//System.out.println(aula1);
    	estudante2.desinscreverEvento(aula1);
    	//System.out.println(aula1);
        
    	//As 6 linhas abaixo servem tanto para testar o metodo criarAula de moderador quanto para ver se a capacidade nao e excedida
		Evento aula2 = moderador1.criarEvento("Aula plataforma", "Ensinamento de conceitos basicos sobre a plataforma", Disciplina.ARTES, new GregorianCalendar(2021,01,4), duracao_padrao, 1, "meet.com/...", instrutor1, true);
    	// System.out.println(aula2);
    	estudante1.inscreverEvento(aula2);
    	//System.out.println(aula2);
    	estudante2.inscreverEvento(aula2);
    	//System.out.println(aula2);
    	moderador1.cancelarEvento(aula1);
        //System.out.println(aula1); //teste do metodo cancelarEvento de moderador
		
    	//As 5 linhas abaixo testam o metodo remover evento, inclusive com um estudante inscrito
		Evento monitoria2 = moderador1.criarEvento("Aula revisao Geo", "Revisao de tipos de rochas", Disciplina.GEOGRAFIA, new GregorianCalendar(2021, 2, 16), duracao_padrao, 10, "meet.com/...", instrutor1, false);
		//System.out.println(monitoria2); //teste de criarMonitoria do moderador
    	estudante1.inscreverEvento(monitoria2);
    	//System.out.println(monitoria2);
    	monitoria2.removeEvento(instrutor1);
        //System.out.println(estudante1);
        //System.out.println(monitoria2);
		
		//As linhas abaixo testam os metodos de avaliar instrutor da classe aula. Como e necessario que o evento tenha ocorrido para avaliar, setamos seu status para "aconteceu"
        // System.out.println(aula2);
		aula2.setStatus(StatusEvento.ACONTECEU);
		// System.out.println(aula2);
		// System.out.println(instrutor1.getAvaliacao());
		aula2.avaliarInstrutor(Nota.BOM, estudante1);
		// System.out.println(instrutor1.getAvaliacao());
		aula2.avaliarInstrutor(Nota.EXCELENTE, "instrutor atencioso", estudante1);
		// System.out.println(instrutor1.getAvaliacao());
    	aula2.avaliarInstrutor(Nota.MUITO_RUIM, "nunca vi um cara tao ruim assim", estudante1);
		// System.out.println(instrutor1.getAvaliacao());

    	//As 4 linhas abaixo testam o metodo solicitar exercicios da classe monitoria
		Evento monitoria3 = instrutor1.criarEvento("Monitoria pra enem quimica", "Monitoria de duvidas enem", Disciplina.QUIMICA, new GregorianCalendar(2021,01,30), duracao_padrao, 10, "meet.com/...", null, false);
		estudante2.inscreverEvento(monitoria3);
    	((Monitoria) monitoria3).solicitarExercicios("1.1,1.2,1.3", estudante2);
		//System.out.println(monitoria3.getExerciciosSolicitados());
		
		//As 3 linhas abaixo testam um metodo de filtro da classe Agenda
		// System.out.println(Agenda.getAgendaGeral().getEventos());
		// System.out.println("Filtrar eventos de Historia");
		// System.out.println(Agenda.getAgendaGeral().filtraPorDisciplina(Disciplina.HISTORIA).getEventos());
		//#endregion
	 
		// #region Teste de Repositorio
		// As 4 linhas abaixo criam um diretorio base e testam o metodo de criar diretorio da classe moderador
		Diretorio root = new Diretorio();
		// System.out.println(root);
		Diretorio diretorio1 = moderador1.criarDiretorio("matematica", "contem todo conteudo de matematica", root);
		// System.out.println(diretorio1);

		//testa o metodo solicitar diretorio da classe instrutor
		Diretorio diretorio2 = instrutor1.solicitarDiretorio("funcoes_de_segundo_grau", "dedicado a funcoes quadraticas polinomiais", diretorio1);
		// System.out.println(diretorio2);
		
		//testa o metodo aceitar diretorio da classe moderador
    	moderador1.aceitarDiretorio(diretorio2);
		// System.out.println(diretorio2);
		
		//testa o metodo adicionar material da classe instrutor
		Material material1 = instrutor1.adicionarMaterial(new File("Instrutor.java"), "Solucao por bhaskara", "raizes de funcoes quadraticas", Disciplina.MATEMATICA, diretorio2);
    	//System.out.println(material1);
		
		//testa o metodo adicionar material da classe moderador
		Material material2 = moderador1.adicionarMaterial(new File("Instrutor.java"), "operacoes basicas", "divisao, subtracao, etc", Disciplina.MATEMATICA, diretorio1);
    	//System.out.println(material2);
		
		//testa o metodo comentar da classe material
    	Comentario comentario1 = material2.comentar("material top, parabens", estudante2);
		//System.out.println(material2);
		
		Material material3 = moderador1.adicionarMaterial(new File("Instrutor.java"), "Soma e Produto", "raizes por soma e produto", Disciplina.MATEMATICA, diretorio1);
		//System.out.println(material3);
		
		//testa o metodo mover da classe material
		material3.mover(diretorio2, root, moderador1);
		//System.out.println(material3);
		
		//testa os metodos remover diretorio e remover material
		moderador1.removerDiretorio(diretorio2, root);
		// System.out.println(diretorio1);
		moderador1.removerMaterial(material2, root);
		// System.out.println(diretorio1);
		//#endregion

		//#region Teste de Discussoes
		//As 9 linhas abaixo testam as classes discussao e comentario, bem como seus metodos "comentar". Tambem testa o metodo iniciar discussao da classe estudante
		Discussao discussao1 = estudante1.iniciarDiscussao("Alguem me ajuda nesse exercecio de fisica? ...", Disciplina.FISICA, material2);
    	//System.out.println(discussao1);
    	Comentario comentario2 = discussao1.comentar("faz desse jeito aqui...", instrutor1);
    	Comentario comentario3 = comentario2.comentar("nao man, faz assim...", moderador1);
    	Comentario comentario4 = comentario3.comentar("tem razao meu rei", instrutor1);
    	// System.out.println(discussao1);
    	// System.out.println(comentario2);
    	// System.out.println(comentario3);
    	// System.out.println(comentario4);
		
    	//As 3 linhas acima testam os metodos de upvote e downvote de comentario
		comentario2.upvote(estudante1);
    	comentario2.downvote(estudante2);
    	//System.out.println(comentario2);
    	
    	discussao1.resolver(estudante1, comentario2);
    	//System.out.println(discussao1); //teste do metodo resolver da classe discussao
		//#endregion
	
		//#region UI
		System.out.println(estudante1);
		Controller.setUsuarios(usuarios);

		Login login = new Login();
		login.setVisible(true);
	}
}
