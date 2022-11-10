package TodoApp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import controller.TaskController;
import model.Task;

public class TesteTaskControler {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		TaskController taskController = new TaskController();			
		Task task = new Task();

		//teste criação task
//		task.setIdProject(1);
//		task.setName("Tarefa teste 2");
//		task.setDescription("Descricao teste 2");
//		task.setIsCompleted(false);
//		task.setDeadline(new Date());
//		
//		taskController.save(task);
		
		//teste atualização task
//		task.setId(1);
//		task.setIdProject(1);
//		task.setName("Novo nome");
//		task.setDeadline(new Date());
//		taskController.update(task);
		
		//deletar task
//		taskController.removeById(2);
		
		//listar todas as tasks
		List<Task> taskList = taskController.getAll(1);
		System.out.println("Total de tasks: " + taskList.size());
		
		
		
	}

}
