package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Task;
import util.ConnectionFactory;

public class TaskController {

	public void save(Task task) {
		String sql = "INSERT INTO tasks (idProject," + "name," + "description," + "completed," + "notes," + "deadline,"
				+ "createdAt," + "updateAt) VALUES (?,?,?,?,?,?,?,?)";

		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(sql);

			statement.setInt(1, task.getIdProject());
			statement.setString(2, task.getName());
			statement.setString(3, task.getDescription());
			statement.setBoolean(4, task.getIsCompleted());
			statement.setString(5, task.getNotes());
			statement.setDate(6, new Date(task.getDeadline().getTime()));
			statement.setDate(7, new Date(task.getCreateAt().getTime()));
			statement.setDate(8, new Date(task.getUpdateAt().getTime()));
			
			statement.execute();

		} catch (Exception e) {
			throw new RuntimeException("Erro ao salvar a tarefa", e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement);
		}
	}

	public void update(Task task) {

		String sql = "UPDATE tasks SET " + "idProject = ?, " + "name = ?, " + "description  = ?, " + "completed = ?, "
				+ "notes = ?, " + "deadline = ?, " + "createdAt = ?, " + "updateAt = ? " + "WHERE id = ?";

		Connection connection = null;
		PreparedStatement statement = null;

		try {
			//Estabelecendo conexão com o DB
			connection = ConnectionFactory.getConnection();
			
			//Preparando a query
			statement = connection.prepareStatement(sql);
			
			//Setando os valores do statement
			statement.setInt(1, task.getIdProject());
			statement.setString(2, task.getName());
			statement.setString(3, task.getDescription());
			statement.setBoolean(4, task.getIsCompleted());
			statement.setString(5, task.getNotes());
			statement.setDate(6, new Date(task.getDeadline().getTime()));
			statement.setDate(7, new Date(task.getCreateAt().getTime()));
			statement.setDate(8, new Date(task.getUpdateAt().getTime()));
			statement.setInt(9, task.getId());
			
			//executando a query
			statement.execute();

		} catch (Exception e) {
			throw new RuntimeException("Erro ao atualizar a tarefa", e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement);
		}
	}

	public void removeById(int taskID){
		// comando que será enviado ao BD
		String sql = "DELETE FROM tasks WHERE id = ?";

		// criando conexão
		Connection connection = null;
		// tratando o comando antes de executar o camando no BD
		PreparedStatement statement = null;

		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(sql);
			
			statement.setInt(1, taskID);
			statement.execute();

		} catch (Exception e) {
			throw new RuntimeException("Erro ao deletar tarefa");
		} finally {
			ConnectionFactory.closeConnection(connection, statement);
		}
	}
	
	public List<Task> getAll(int idProject){
		
		String sql = "SELECT * FROM tasks WHERE idProject = ?";
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null; // retorno DB
		
		//criando a lista de tarefas
		List<Task> tasks = new ArrayList<>();
		
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(sql);
			
			//setando o valor do filto a query
			statement.setInt(1, idProject);
			
			//aramazenando o resultado da query
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				Task task = new Task();
				
				task.setId(resultSet.getInt("id"));
				task.setIdProject(resultSet.getInt("idProject"));
				task.setName(resultSet.getString("name"));
				task.setDescription(resultSet.getString("description"));
				task.setIsCompleted(resultSet.getBoolean("completed"));
				task.setNotes(resultSet.getString("notes"));
				task.setDeadline(resultSet.getDate("deadline"));
				task.setCreateAt(resultSet.getDate("createdAt"));
				task.setUpdateAt(resultSet.getDate("updateAt"));
				
				tasks.add(task);							
			}		
		} catch (Exception e) {
			throw new RuntimeException("Erro ao buscar tarefa", e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement, resultSet);
		}	
		
		return tasks;
	}
	
	
}
