package util;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;


public class ButtonColumnCellRendere extends DefaultTableCellRenderer{
	
	private String buttonType;

	public String getButtonType() {
		return buttonType;
	}

	public void setButtonType(String buttonType) {
		this.buttonType = buttonType;
	}
	
	public ButtonColumnCellRendere(String buttonType) {
		this.buttonType = buttonType;
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
		
		//instaciar a label que será redenderizada
		JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		
		//alterando as propriedades dessa label
		label.setHorizontalAlignment(CENTER);
		
		//colocando a imagem no celula
		label.setIcon(new ImageIcon("D:\\Projeto Desenvolvedor\\capgemini\\logica-III\\introducao2\\TodoApp\\src\\main\\resources\\" + buttonType +".png"));
		
		
		return label;
	}
		
	
}
