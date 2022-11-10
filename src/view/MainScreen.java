package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controller.ProjectController;
import controller.TaskController;
import model.Project;
import model.Task;
import util.ButtonColumnCellRendere;
import util.DeadlineColumnCellRender;
import util.TaskTableModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class MainScreen extends JFrame {

	private JPanel contentPane;
	private JTable tableTasks = new JTable();
	JScrollPane scrollPaneTasks = new JScrollPane();

	// lista declarada fora da main para setar o model
	JList<Project> listProjects = new JList<Project>();

	ProjectController projectController;
	TaskController taskController;

	DefaultListModel<Project> projectsModel;
	TaskTableModel tasksTableModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainScreen frame = new MainScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainScreen() {

		initDateController();
		initComponentsModel();
		deocateTableTask();

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 700);
		contentPane = new JPanel();
		contentPane.setFont(new Font("Verdana", Font.PLAIN, 12));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelToolBar = new JPanel();
		panelToolBar.setBackground(new Color(74, 137, 143));
		panelToolBar.setBounds(0, 0, 734, 100);
		contentPane.add(panelToolBar);
		panelToolBar.setLayout(null);

		JLabel lblToolBarTitle = new JLabel("TaskApp");
		lblToolBarTitle.setIcon(new ImageIcon(
				"D:\\Projeto Desenvolvedor\\capgemini\\logica-III\\introducao2\\TodoApp\\src\\main\\resources\\tick.png"));
		lblToolBarTitle.setForeground(new Color(201, 251, 255));
		lblToolBarTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblToolBarTitle.setFont(new Font("Ink Free", Font.BOLD | Font.ITALIC, 44));
		lblToolBarTitle.setBounds(10, 11, 714, 78);
		panelToolBar.add(lblToolBarTitle);

		JPanel panelProjects = new JPanel();
		panelProjects.setBorder(UIManager.getBorder("CheckBox.border"));
		panelProjects.setBackground(new Color(255, 255, 255));
		panelProjects.setBounds(10, 111, 188, 61);
		contentPane.add(panelProjects);
		panelProjects.setLayout(null);

		JLabel lblProjectsTitle = new JLabel("Projetos");
		lblProjectsTitle.setForeground(new Color(74, 137, 143));
		lblProjectsTitle.setFont(new Font("Verdana", Font.BOLD, 18));
		lblProjectsTitle.setBounds(10, 11, 96, 35);
		panelProjects.add(lblProjectsTitle);

		JLabel lblProjectsAdd = new JLabel("");

		lblProjectsAdd.setIcon(new ImageIcon(
				"D:\\Projeto Desenvolvedor\\capgemini\\logica-III\\introducao2\\TodoApp\\src\\main\\resources\\add.png"));
		lblProjectsAdd.setBounds(146, 11, 32, 35);
		panelProjects.add(lblProjectsAdd);

		JPanel panelTasks = new JPanel();
		panelTasks.setBorder(UIManager.getBorder("CheckBox.border"));
		panelTasks.setBackground(new Color(255, 255, 255));
		panelTasks.setBounds(208, 111, 516, 61);
		contentPane.add(panelTasks);
		panelTasks.setLayout(null);

		JLabel lblTasksTitle = new JLabel("");
		lblTasksTitle.setIcon(new ImageIcon(
				"D:\\Projeto Desenvolvedor\\capgemini\\logica-III\\introducao2\\TodoApp\\src\\main\\resources\\add.png"));
		lblTasksTitle.setBounds(474, 11, 32, 35);
		panelTasks.add(lblTasksTitle);

		JLabel lblTasksAdd = new JLabel("Tarefas");
		lblTasksAdd.setForeground(new Color(74, 137, 143));
		lblTasksAdd.setFont(new Font("Verdana", Font.BOLD, 18));
		lblTasksAdd.setBounds(10, 11, 96, 35);
		panelTasks.add(lblTasksAdd);

		JPanel panelProjectsList = new JPanel();
		panelProjectsList.setBorder(UIManager.getBorder("ComboBox.border"));
		panelProjectsList.setBackground(new Color(255, 255, 255));
		panelProjectsList.setBounds(10, 183, 188, 467);
		contentPane.add(panelProjectsList);
		panelProjectsList.setLayout(null);
		listProjects.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int projectIndex = listProjects.getSelectedIndex();
				Project project = projectsModel.get(projectIndex);
				loadtasks(project.getId());
			}
		});

		listProjects.setFixedCellHeight(50);
		listProjects.setSelectionBackground(new Color(74, 137, 141));
		listProjects.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listProjects.setFont(new Font("Verdana", Font.BOLD, 11));
		listProjects.setBounds(10, 11, 168, 445);
		panelProjectsList.add(listProjects);

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(UIManager.getBorder("PopupMenu.border"));
		panel_4.setBackground(new Color(255, 255, 255));
		panel_4.setBounds(208, 183, 516, 467);
		contentPane.add(panel_4);
		panel_4.setLayout(null);

		scrollPaneTasks.setBounds(0, 0, 516, 467);
		panel_4.add(scrollPaneTasks);

//		tableTasks.setShowVerticalLines(false);
//		tableTasks.setRowHeight(50);
//		tableTasks.setSelectionBackground(new Color(74, 137, 143));
//		tableTasks.setModel(new DefaultTableModel(
//			new Object[][] {
//				{null, null, null, null},
//				{null, null, null, null},
//				{null, null, null, null},
//			},
//			new String[] {
//				"Nome", "Descricao", "Prazo", "Conclu\u00EDda?"
//			}
//		) {
//			Class[] columnTypes = new Class[] {
//				String.class, String.class, String.class, Boolean.class
//			};
//			public Class getColumnClass(int columnIndex) {
//				return columnTypes[columnIndex];
//			}
//		});
//		tableTasks.setFont(new Font("Verdana", Font.PLAIN, 12));
//		scrollPaneTasks.setViewportView(tableTasks);

		lblProjectsAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// abre a caixa de dialogo quando clicado no botao de add
				ProjectDialogScreen projectDialogScreen = new ProjectDialogScreen();
				projectDialogScreen.setVisible(true);

				// identificando que a tela de criação de projeto for fechada atualiza a lista
				projectDialogScreen.addWindowListener(new WindowAdapter() {
					public void windowClosed(WindowEvent e) {
						loadProjects();
					}
				});
			}
		});

		lblTasksTitle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TaskDialogScreen taskDialogScreen = new TaskDialogScreen();
				
				 //pegando o index do projeto a partir da seleção do projeto
				int projectIndex = listProjects.getSelectedIndex();
				Project project = projectsModel.get(projectIndex);
				taskDialogScreen.setProject(project);
				taskDialogScreen.setVisible(true);
				
				taskDialogScreen.addWindowListener(new WindowAdapter() {
					public void windowClosed(WindowEvent e) {
						int projectIndex = listProjects.getSelectedIndex();
						Project project = projectsModel.get(projectIndex);
						loadtasks(project.getId());	
					}
				});
			}
		});
	}

	public void initDateController() {
		projectController = new ProjectController();
		taskController = new TaskController();
	}

	public void initComponentsModel() {
		projectsModel = new DefaultListModel<Project>();
		loadProjects();

		tasksTableModel = new TaskTableModel();
		tableTasks.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowIndex = tableTasks.rowAtPoint(e.getPoint());
				int columnIndex = tableTasks.columnAtPoint(e.getPoint());
				Task task = tasksTableModel.getTasks().get(rowIndex);
				
				switch (columnIndex) {
				case 3:
					// acessar a lista de terafas do model e seleciona a
					// tarefa referente a linha passada como paramentro
					taskController.update(task);
					break;
				case 5:
					//remover tarefa do banco
					taskController.removeById(task.getId());
					//remover tarefa do model
					tasksTableModel.getTasks().remove(task);
					
					//recarregar lista de tarefa do projeto selecionado
					int projectIndex = listProjects.getSelectedIndex();
					Project project = projectsModel.get(projectIndex);
					loadtasks(project.getId());
					
					break;
				}
			}
		});
		tableTasks.setRowHeight(50);
		tableTasks.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableTasks.setModel(tasksTableModel);
		scrollPaneTasks.setViewportView(tableTasks);

		if(!projectsModel.isEmpty()) {
			listProjects.setSelectedIndex(0);
			Project project = projectsModel.get(0);
			System.out.println(project.getId());
			loadtasks(project.getId());
		}

	}

	public void loadtasks(int idProject) {
		System.out.println("carregando tarefas do projeto id " + idProject);
		List<Task> tasks = taskController.getAll(idProject);

		//carregando a table no scrollpanel
		scrollPaneTasks.setViewportView(tableTasks);
		// Passando a lista retornada do DB para o model
		tasksTableModel.setTasks(tasks);
	}

	public void loadProjects() {
		System.out.println("carregando projetos");

		// carregando os projetos do banco de dados
		List<Project> projects = projectController.getAll();

		// limpando as informações presente no model
		projectsModel.clear();

		// interando em cada projeto retornado do BD e inserindo no model
		for (int i = 0; i <= projects.size() - 1; i++) {
			Project project = projects.get(i);
			projectsModel.addElement(project);
		}
		listProjects.setModel(projectsModel);
	}
	

	public void deocateTableTask() {
		System.out.println("setando a cor no deadline");
		tableTasks.getColumnModel().getColumn(2).setCellRenderer(new DeadlineColumnCellRender());
		tableTasks.getColumnModel().getColumn(4).setCellRenderer(new ButtonColumnCellRendere("edit"));
		tableTasks.getColumnModel().getColumn(5).setCellRenderer(new ButtonColumnCellRendere("delete"));
	}
}
