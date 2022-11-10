package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ProjectController;
import model.Project;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;

public class ProjectDialogScreen extends JDialog {
	
	ProjectController projectController;
	private JTextField textFieldName;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ProjectDialogScreen dialog = new ProjectDialogScreen();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ProjectDialogScreen() {
		
		projectController = new ProjectController();
		
		setResizable(false);
		setBounds(100, 100, 450, 430);
		getContentPane().setLayout(null);
		
		JPanel panelToolBar = new JPanel();
		panelToolBar.setLayout(null);
		panelToolBar.setBackground(new Color(74, 137, 143));
		panelToolBar.setBounds(0, 0, 434, 72);
		getContentPane().add(panelToolBar);
		
		JLabel lblToolBarTitle = new JLabel("Projeto");
		lblToolBarTitle.setHorizontalAlignment(SwingConstants.LEFT);
		lblToolBarTitle.setForeground(new Color(201, 251, 255));
		lblToolBarTitle.setFont(new Font("Verdana", Font.BOLD, 26));
		lblToolBarTitle.setBounds(10, 11, 208, 49);
		panelToolBar.add(lblToolBarTitle);
		
		JLabel lblToolBarSave = new JLabel("");
		lblToolBarSave.setHorizontalAlignment(SwingConstants.RIGHT);
		lblToolBarSave.setIcon(new ImageIcon("D:\\Projeto Desenvolvedor\\capgemini\\logica-III\\introducao2\\TodoApp\\src\\main\\resources\\check.png"));
		lblToolBarSave.setBounds(346, 11, 78, 49);
		panelToolBar.add(lblToolBarSave);
		
		JPanel panelProject = new JPanel();
		panelProject.setBounds(10, 83, 414, 115);
		getContentPane().add(panelProject);
		panelProject.setLayout(null);
		
		JLabel lblName = new JLabel("Nome");
		lblName.setFont(new Font("Verdana", Font.BOLD, 12));
		lblName.setBounds(0, 11, 414, 14);
		panelProject.add(lblName);
		
		textFieldName = new JTextField();
		textFieldName.setHorizontalAlignment(SwingConstants.LEFT);
		textFieldName.setFont(new Font("Verdana", Font.PLAIN, 12));
		textFieldName.setColumns(10);
		textFieldName.setBounds(0, 36, 414, 31);
		panelProject.add(textFieldName);
		
		JLabel lblDescription = new JLabel("Descricao");
		lblDescription.setFont(new Font("Verdana", Font.BOLD, 12));
		lblDescription.setBounds(0, 90, 414, 14);
		panelProject.add(lblDescription);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 209, 414, 171);
		getContentPane().add(scrollPane);
		
		JTextArea textAreaDescription = new JTextArea();
		scrollPane.setViewportView(textAreaDescription);
		
		lblToolBarSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					if(!textFieldName.getText().equals("")) {
						Project project = new Project();
						project.setName(textFieldName.getText());
						project.setDescription(textAreaDescription.getText());
						
						projectController.save(project);
						JOptionPane.showMessageDialog(rootPane, "Projeto cadastrado com sucesso");
					
						dispose();
					}else {
						JOptionPane.showMessageDialog(rootPane, "Nome não preenchido!");
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(rootPane, e);
				}
				
			}
		});
	}
}
