package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.TaskController;
import model.Project;
import model.Task;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFormattedTextField;

public class TaskDialogScreen extends JDialog {
	
	TaskController taskController;
	Project project;
	JLabel lblNameError = new JLabel("Campo nome está vazio!");
	JLabel lblDeadLineError = new JLabel("Campo prazo está vazio!");
	
	
	private JTextField textFieldName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			TaskDialogScreen dialog = new TaskDialogScreen();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public TaskDialogScreen() {
		
		hideErrorFields();
		
		taskController = new TaskController();
		
		setBounds(100, 100, 450, 650);
		getContentPane().setLayout(null);
		
		JPanel panelToolBar = new JPanel();
		panelToolBar.setLayout(null);
		panelToolBar.setBackground(new Color(74, 137, 143));
		panelToolBar.setBounds(0, 0, 434, 72);
		getContentPane().add(panelToolBar);
		
		JLabel lblToolBarAdd = new JLabel("");

		lblToolBarAdd.setIcon(new ImageIcon("D:\\Projeto Desenvolvedor\\capgemini\\logica-III\\introducao2\\TodoApp\\src\\main\\resources\\check.png"));
		lblToolBarAdd.setHorizontalAlignment(SwingConstants.RIGHT);
		lblToolBarAdd.setBounds(346, 11, 78, 49);
		panelToolBar.add(lblToolBarAdd);
		
		JLabel lblToolBarTitle = new JLabel("Tarefa");
		lblToolBarTitle.setHorizontalAlignment(SwingConstants.LEFT);
		lblToolBarTitle.setForeground(new Color(201, 251, 255));
		lblToolBarTitle.setFont(new Font("Verdana", Font.BOLD, 26));
		lblToolBarTitle.setBounds(10, 11, 208, 49);
		panelToolBar.add(lblToolBarTitle);
		
		JPanel panelTaskNameDescription = new JPanel();
		panelTaskNameDescription.setBounds(10, 83, 414, 131);
		getContentPane().add(panelTaskNameDescription);
		panelTaskNameDescription.setLayout(null);
		
		JLabel lblName = new JLabel("Nome");
		lblName.setBounds(10, 11, 394, 14);
		panelTaskNameDescription.add(lblName);
		lblName.setFont(new Font("Verdana", Font.BOLD, 12));
		
		textFieldName = new JTextField();
		textFieldName.setBounds(10, 36, 394, 31);
		panelTaskNameDescription.add(textFieldName);
		textFieldName.setHorizontalAlignment(SwingConstants.LEFT);
		textFieldName.setFont(new Font("Verdana", Font.PLAIN, 12));
		textFieldName.setColumns(10);
		
		JLabel lblDescription = new JLabel("Descrição");
		lblDescription.setBounds(10, 106, 394, 14);
		panelTaskNameDescription.add(lblDescription);
		lblDescription.setFont(new Font("Verdana", Font.BOLD, 12));
		
		
		lblNameError.setForeground(new Color(255, 0, 0));
		lblNameError.setFont(new Font("Verdana", Font.BOLD, 12));
		lblNameError.setBounds(10, 81, 394, 14);
		panelTaskNameDescription.add(lblNameError);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 225, 393, 72);
		getContentPane().add(scrollPane);
		
		JTextArea textAreaDescription = new JTextArea();
		scrollPane.setViewportView(textAreaDescription);
		textAreaDescription.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		JPanel panelDeadLineNotes = new JPanel();
		panelDeadLineNotes.setLayout(null);
		panelDeadLineNotes.setBounds(10, 308, 414, 131);
		getContentPane().add(panelDeadLineNotes);
		
		JLabel lblDeadLine = new JLabel("Prazo");
		lblDeadLine.setFont(new Font("Verdana", Font.BOLD, 12));
		lblDeadLine.setBounds(10, 11, 394, 14);
		panelDeadLineNotes.add(lblDeadLine);
		
		JLabel lblNotes = new JLabel("Notas");
		lblNotes.setFont(new Font("Verdana", Font.BOLD, 12));
		lblNotes.setBounds(10, 106, 394, 14);
		panelDeadLineNotes.add(lblNotes);
		
		JFormattedTextField formattedTextFieldDeadLine = new JFormattedTextField();
		formattedTextFieldDeadLine.setBounds(10, 36, 394, 29);
		panelDeadLineNotes.add(formattedTextFieldDeadLine);
		
		
		lblDeadLineError.setForeground(Color.RED);
		lblDeadLineError.setFont(new Font("Verdana", Font.BOLD, 12));
		lblDeadLineError.setBounds(10, 76, 394, 14);
		panelDeadLineNotes.add(lblDeadLineError);
		
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(20, 450, 393, 101);
		getContentPane().add(scrollPane_1);
		
		JTextArea textAreaNotes = new JTextArea();
		scrollPane_1.setViewportView(textAreaNotes);
		textAreaNotes.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		lblToolBarAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					
					if(textFieldName.getText().isEmpty() || formattedTextFieldDeadLine.getText().isEmpty()) {
						hideErrorFields();				
						if(textFieldName.getText().isEmpty()) {
							lblNameError.setVisible(true);
						}
						if(formattedTextFieldDeadLine.getText().isEmpty()) {
							lblDeadLineError.setVisible(true);
						}
						JOptionPane.showMessageDialog(rootPane, "Nome e/ou prazo vazio!");
						
					}else {
						
						Task task = new Task();
						
						task.setIdProject(project.getId());
						
						task.setName(textFieldName.getText());
						task.setDescription(textAreaDescription.getText());
						task.setNotes(textAreaNotes.getText());
						
						SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
						Date deadLine = null;	
						deadLine = dateFormat.parse(formattedTextFieldDeadLine.getText());
						task.setDeadline(deadLine);
						
						taskController.save(task);
						JOptionPane.showMessageDialog(rootPane, "Tarefa cadastrada com sucesso");				
						
						dispose();
					}
										
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(rootPane, e2);
				}
			}
		});
	}
	
	public void hideErrorFields() {
		lblNameError.setVisible(false);
		lblDeadLineError.setVisible(false);
	}

	public void setProject(Project project) {
		this.project = project;
	}
}
