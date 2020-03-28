package sp_basket;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Order_btn extends JFrame {
	String header[] = { "회원ID", "상품이름", "합계" };
	String contents[][];
	DAO DAOins = DAO.getInstance();
	Main mainAdr = null;
	int row = 0;
	String selID;
	DefaultTableModel dfTable = null;
	JTable table = null;
	String[] returnData = new String[3];
	ArrayList<String> TMdata = new ArrayList<>();

	Order_btn(Main ADR, int selRow) {
		super("주문목록");
		mainAdr = ADR;
		row = selRow;
		getArray();
	}

	private void getArray() {
		selID = (String) mainAdr.table.getValueAt(row, 0);
		DAOins.getOrder(selID);
	}
}