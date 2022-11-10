package TodoApp;

import java.util.ArrayList;
import java.util.List;

import controller.ProjectController;
import model.Project;

public class TesteProjectController {

	public static void main(String[] args) {

		
		//Teste Salvar
		ProjectController controllerTeste = new ProjectController();
//		
		Project projectTest = new Project();
		projectTest.setName("projeto teste2");
		projectTest.setDescription("Testando a criacao de projeto2");
		controllerTeste.save(projectTest);
		
		

		//Teste atualizar
		 
//		ProjectController controllerTeste = new ProjectController();
//		Project projectTest = new Project();
//		projectTest.setId(1);
//		projectTest.setName("Novoo nome projeto");
//		projectTest.setDescription("Atualizando o projeto");
//		controllerTeste.update(projectTest);

		
//		ProjectController controllerTeste = new ProjectController();
		List<Project> listProjetos = controllerTeste.getAll();
		
		System.out.println("Total projetos: " + listProjetos.size());
		
		//controllerTeste.removeByID(2);
		
		
	}

}
