package sp_basket;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Test extends JFrame {
	String header[] = { "주문번호", "회원ID", "회원이름", "상품이름", "상품수량", "상품단가", "합계" };
	String contents[][];
	JTable table = null;
	DefaultTableModel dfTable = new DefaultTableModel(contents, header);
	JScrollPane tableScroll = new JScrollPane(table);
	JLabel ob = null;
	JTextField sum = null;	
	JPanel sp = null;
	
	Test() {
		table = new JTable(dfTable);
		tableScroll = new JScrollPane(table);
		this.setBounds(320, 400, 400, 320);
		
		sum = new JTextField(10);
		sp = new JPanel();
		ob =new JLabel("총 계산 금액 : ");
		sp.add(ob);
		sp.add(sum);
		this.add(tableScroll,"Center");
		this.add(sp,"South");
		this.setVisible(true);
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
	}

	public static void main(String[] args) {
		new Test();
	}
}