package sp_basket;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Order_btn extends JFrame {
	String header[] = { "주문번호", "회원ID", "회원이름", "상품이름", "상품수량", "상품단가", "합계" };
	String contents[][];
	DAO DAOins = DAO.getInstance();
	Main mainAdr = null;
	int row = 0;
	String selID;
	DefaultTableModel dfTable = new DefaultTableModel(contents, header);
	JTable table = new JTable(dfTable);
	JScrollPane tableScroll = new JScrollPane(table);
	JLabel ob = null;
	JTextField odsum = null;
	JPanel sp = null;
	String[] returnData = new String[3];
	ArrayList<String[]> TMdata = new ArrayList<>();

	Order_btn(Main ADR, String ID) {
		super(ID + " 님의 주문목록");
		selID = ID;
		this.setBounds(320, 400, 400, 320);
		mainAdr = ADR;
		getArray();
		spSetting();

		this.add(tableScroll);
		this.setVisible(true);
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
	}

	private void spSetting() {
		odsum = new JTextField(10);
		sp = new JPanel();
		ob = new JLabel("총 계산 금액 : ");
		textSum();
		sp.add(ob);
		sp.add(odsum);
		this.add(sp, "South");
	}

	private void textSum() {
		int sum = 0;
		int limit = table.getRowCount();
		for (int i = 0; i < limit; i++) {
			sum = sum + Integer.parseInt(table.getValueAt(i, 6).toString()) ;
		}
		String resultSum =  Integer.toString(sum);
		odsum.setText(resultSum);
	}

	private void getArray() {
		TMdata = DAOins.getOrder(selID);
		for (int i = 0; i < TMdata.size(); i++) {
			dfTable.addRow(TMdata.get(i));
		}
	}
}