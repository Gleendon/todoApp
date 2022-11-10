package util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import model.Task;

public class TaskTableModel extends AbstractTableModel {

	String[] columns = { "Nome", "Descriçao", "Prazo", "Tarefa Concluída", "Editar", "Excluir" };
	List<Task> tasks = new ArrayList<>();

	@Override
	public int getRowCount() {
		// retorno do número de linhas
		return tasks.size();
	}

	@Override
	public int getColumnCount() {
		// retorno do número de colunas
		return columns.length;
	}
	
	@Override
	public String getColumnName(int columnIndex) {
		return columns[columnIndex];
	}
	
	@Override
	//Metodo para retornoar o tipo do objeto nas células da table(boolean check)
	public Class<?> getColumnClass(int columnIndex){
		if(tasks.isEmpty()) {
			return Object.class; 
		}
		return this.getValueAt(0, columnIndex).getClass();
		
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		switch (columnIndex) {
			case 0:
				return tasks.get(rowIndex).getName();
			case 1:
				return tasks.get(rowIndex).getDescription();
			case 2:
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				return dateFormat.format(tasks.get(rowIndex).getDeadline());
			case 3:
				return tasks.get(rowIndex).getIsCompleted();
			case 4:
				return "";
			case 5:
				return "";
			default:
				return "Dados não encontrado";
		}
	}
	
	//permitir que a celula seja modificada
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return columnIndex == 3;
	}
	
	@Override
	//setar o valor de uma celula
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		System.out.println(columnIndex);
		tasks.get(rowIndex).setIsCompleted((boolean) aValue);
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public String[] getColumns() {
		return columns;
	}
		
}
