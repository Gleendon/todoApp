package util;

import java.awt.Color;
import java.awt.Component;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import model.Task;

public class DeadlineColumnCellRender extends DefaultTableCellRenderer{
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
				
		//instaciar a label que será redenderizada
		JLabel label;
		label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		
		//alterando as propriedades dessa label
		label.setHorizontalAlignment(CENTER);
		
		//bucando a tarefas dentro do model (cast = convertendo tipos)
		TaskTableModel taskTableModel = (TaskTableModel) table.getModel();
		Task task = taskTableModel.getTasks().get(row);
		
		//verificando a deadline das tarefas dentro do model
		if(task.getDeadline().after(new Date())) {
			label.setBackground(Color.green);
		}else {
			label.setBackground(Color.red);
		}
		
		return label;
	}
}
