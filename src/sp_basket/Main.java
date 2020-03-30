package sp_basket;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class Main extends JFrame implements ActionListener {
	String header[] = { "주문번호", "회원ID", "회원이름", "상품이름", "상품수량", "상품단가", "합계" };
	String contents[][];
	JPanel np = new JPanel();// 테이블 패널
	JPanel cp = new JPanel();// 텍스트필드 패널
	JPanel sp = new JPanel();// 버튼 패널
	JPopupMenu Rclick = null;// 우클릭시 메뉴창이 나오게 함
	JMenuItem Rckmenu = null;// 위 우클릭 후 메뉴창에 이름 짓는것
	JButton add = null;
	JButton del = null;
	JButton mod = null;
	JTextField menu[];
	DefaultTableModel tableModel = new DefaultTableModel(contents, header);
	JTable table = new JTable(tableModel);
	JScrollPane tableScroll = new JScrollPane(table);
	DAO daoIns = DAO.getInstance();
	ArrayList<String[]> DTList = new ArrayList<>();// 한행 한행의 Sring[]배열저장
	JLabel sum;
	JButton buy;
	String originData[] = new String[5];

	Main() {
		for (int i = 0; i < originData.length; i++) {// 프로그램 종료시 남을수 있으니 시작시 초기화해줌
			originData[i] = null;
		}
		cpSet();
		spSet();
		popup();

		Dimension size = new Dimension(620, 400);
		this.setSize(size);
		this.add(tableScroll, "Center");
		this.add(sp, "South");
		this.setVisible(true);
		msgbox msgins = new msgbox();
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
	}

	private void popup() {
		Rclick = new JPopupMenu();
		Rckmenu = new JMenuItem("data가져오기");
		Rclick.add(Rckmenu);
		table.add(Rclick);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent k) {
				if (k.getButton() == 3) {// 우클릭 했을시 해당 마우스포인터의 좌표에 JPopupMenu를 보여줌
					int column = table.columnAtPoint(k.getPoint());
					int row = table.rowAtPoint(k.getPoint());
					table.changeSelection(row, column, false, false);
					Rclick.show(table, k.getX(), k.getY());
				}
			}
		});
		Rckmenu.addActionListener(new ActionListener() {// JPopupMenu에 있는 Rckmenu(data가져오기)를 클릭시
			@Override
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() == -1) {
					msgbox msgins = new msgbox();
					msgins.Moderorrmsg();
				} else {
					for (int i = 0; i < menu.length; i++) {//
						menu[i].setText((String) table.getValueAt(table.getSelectedRow(), i + 1));
						// 행의 값을 Textfield로 가져옴
						originData[i] = menu[i].getText();
						// 가져온 행의 값을 originData배열에 넣어놓음 why?수정할 값과 비교해야하니까!
					}
				}
			}
		});
	}

	private void spSet() {
		sp.setLayout(new BorderLayout());
		JPanel spNorth = new JPanel();
		JPanel spSouth = new JPanel();
		add = new JButton("추가");
		del = new JButton("삭제");
		mod = new JButton("수정");
		sum = new JLabel("총 계");
		JTextField tf = new JTextField(10);
		buy = new JButton("주문");
		buy.addActionListener(this);
		tf.setText(takeSum());
		spSouth.add(add);
		spSouth.add(del);
		spSouth.add(mod);
		spSouth.add(sum);
		spSouth.add(tf);
		spSouth.add(buy);
		sp.add(spSouth, "South");
		menu = new JTextField[5];
		for (int i = 0; i < menu.length; i++) {
			spNorth.add(menu[i] = new JTextField(10));
			menu[i].addActionListener(this);
		}
		add.addActionListener(this);
		del.addActionListener(this);
		mod.addActionListener(this);
		sp.add(spNorth);
	}

	private String takeSum() {
		int rowcnt = tableModel.getRowCount();
		int tSum = 0;
		for (int i = 0; i < rowcnt; i++) {
			tSum = tSum + Integer.parseInt(tableModel.getValueAt(i, 6).toString());
			// 게산을 위해 tableModel.getValueAt(i, 5) 에서 나온 object 타입을 인트로 형변환 시켜줘야함
			// 그러나 바로 인트 형변환이 되지 않아 스트링으로 형변환 후 다시 인트로 형변환함
			// 그리고 JTextField에 넣기 위해 다시 스트링으로 변환해줌
		}
		String stSum = Integer.toString(tSum);
		return stSum;
	}

	private void cpSet() {
		DTList = daoIns.getContents();
		for (int i = 0; i < DTList.size(); i++) {// 오라클에서 data를 가져옴
			tableModel.addRow(DTList.get(i));
		}
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		table.setShowVerticalLines(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultTableCellRenderer ts = new DefaultTableCellRenderer();
		ts.setHorizontalAlignment(SwingConstants.RIGHT);
		TableColumnModel tc = table.getColumnModel();
		for (int i = 0; i < tc.getColumnCount(); i++) {// 오른쪽정렬
			tc.getColumn(i).setCellRenderer(ts);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String in[] = new String[7];
		Object act = e.getSource();

		if (act.equals(add)) {// 추가 버튼을 눌렀을시-추가하고싶은건 텍스트 파일이 전부 내용이 있을때만 작동하게 하고싶음
			if (blankCheck()) {
				for (int i = 0; i < menu.length; i++) {
					in[i + 1] = menu[i].getText();
				}
				int a = Integer.parseInt(menu[3].getText());
				int b = Integer.parseInt(menu[4].getText());
				int c = a * b;
				in[6] = Integer.toString(c);
				for (int i = 0; i < menu.length; i++) {
					menu[i].setText("");
				}
				saveToDB(in);
				tableModel.addRow(in);
			} else {
				msgbox msgins = new msgbox();
				msgins.erorrmsg();
			}
		}

		if (act.equals(del)) {
			if (table.getSelectedRow() == -1) {
				msgbox msgins = new msgbox();
				msgins.rowerorrmsg();
			} else {
				String odn = tableModel.getValueAt(table.getSelectedRow(), 0).toString();
				if (daoIns.deltoOracle(odn) == 1) {// Oracle에서 삭제
					msgbox msgins = new msgbox();
					msgins.suessmsg();
				} else {
					msgbox msgins = new msgbox();
					msgins.fail();
				}
				;
				tableModel.removeRow(table.getSelectedRow());// JFrame에서 삭제
			}
		}

		if (act.equals(mod)) {
			if (table.getSelectedRow() == -1) {// 행을 미선택시erorr
				msgbox msgins = new msgbox();
				msgins.Moderorrmsg();
			} else if (table.getSelectedRow() != -1 && blankCheck()) {// 수정조건2 해당 텍스트에 값이 전부 있을것
				int selRow = table.getSelectedRow();
				if (modCheck1()) {// 수정조건3 originData데이터에 값 가져오기로 가져온 기존 값이 있을것
					if (modCheck2()) {// 수정조건4 기존의 있던 기존행의 data와 수정된 textfield값이 서로 다를것!
						String orderN = (String) table.getValueAt(selRow, 0);
						int check = daoIns.update(findAttribute(), findValuse(), orderN);
						// 수정조건 5 수정할 Attribute와 Valuse찾기
						if (check == 1) {
							String insertMod[] = new String[7];
							insertMod[0] = (String) tableModel.getValueAt(selRow, 0);
							for (int i = 1; i < menu.length; i++) {
								insertMod[i] = menu[i - 1].getText();
							}
							insertMod[5] = (String) tableModel.getValueAt(selRow, 5);
							insertMod[6] = (String) tableModel.getValueAt(selRow, 6);
							tableModel.removeRow(selRow);
							tableModel.insertRow(selRow, insertMod);
							msgbox msgins = new msgbox();
							msgins.modsuccess();
						} else if (check == 0) {
							msgbox msgins = new msgbox();
							msgins.Moderorrmsg5();
						}
					} else {
						msgbox msgins = new msgbox();
						msgins.Moderorrmsg4();
					}
				} else {
					msgbox msgins = new msgbox();
					msgins.Moderorrmsg3();
				}
			} else if (table.getSelectedRow() != -1) {// 행은 선택했으나 textfield에 값을 가져오지 않았을시 erorr
				msgbox msgins = new msgbox();
				msgins.Moderorrmsg2();
			}
		}

		if (act.equals(buy)) {
			if (table.getSelectedRow() == -1) {
				msgbox msgins = new msgbox();
				msgins.Moderorrmsg();
			} else if (table.getSelectedRow() != -1) {
				int selRow = table.getSelectedRow();
				String ID = (String) table.getValueAt(selRow, 1);// id
				new Order_btn(this, ID);
			}
		}
	}

	private ArrayList<String> findValuse() {// 수정할 valuse 찾기
		ArrayList<String> findVList = new ArrayList<>();

		boolean mid = false;
		boolean mname = false;
		boolean obname = false;
		boolean mbea = false;
		boolean obprice = false;
		if (!menu[0].getText().equals(originData[0])) {
			mid = true;
			findVList.add(menu[0].getText());
		}
		if (!menu[1].getText().equals(originData[1])) {
			mname = true;
			findVList.add(menu[1].getText());
		}
		if (!menu[2].getText().equals(originData[2])) {
			obname = true;
			findVList.add(menu[2].getText());
		}
		if (!menu[3].getText().equals(originData[3])) {
			mbea = true;
			findVList.add(menu[3].getText());
		}
		if (!menu[4].getText().equals(originData[4])) {
			obprice = true;
			findVList.add(menu[4].getText());
		}
		return findVList;
	}

	private ArrayList<String> findAttribute() {// 수정할 Attribute 찾기
		ArrayList<String> findList = new ArrayList<>();

		boolean mid = false;
		boolean mname = false;
		boolean obname = false;
		boolean mbea = false;
		boolean obprice = false;
		if (!menu[0].getText().equals(originData[0])) {
			mid = true;
			findList.add("mid");
		}
		if (!menu[1].getText().equals(originData[1])) {
			mname = true;
			findList.add("mname");
		}
		if (!menu[2].getText().equals(originData[2])) {
			obname = true;
			findList.add("obname");
		}
		if (!menu[3].getText().equals(originData[3])) {
			mbea = true;
			findList.add("mbea");
		}
		if (!menu[4].getText().equals(originData[4])) {
			obprice = true;
			findList.add("obprice");
		}
		return findList;
	}

	private boolean blankCheck() {
		int blankcnt = 0;
		for (int i = 0; i < menu.length; i++) {
			if (!menu[i].getText().equals("")) {
				blankcnt++;
			}
		}
		if (blankcnt == 5) {
			return true;
		}
		return false;
	}

	private boolean modCheck2() {
		int overcnt = 0;
		for (int i = 0; i < originData.length; i++) {
			if (originData[i].equals(menu[i].getText())) {
				overcnt++;
			}
		}
		if (overcnt == 5) {
			return false;
		}
		return true;
	}

	private boolean modCheck1() {
		if (originData[0] == null && originData[1] == null && originData[2]
				== null && originData[3] == null
				&& originData[4] == null) {
			return false;
		}
		return true;
	}

	private void saveToDB(String[] in) {
		DTO_Cart addDTO = new DTO_Cart();
		addDTO.setmID(in[1]);
		addDTO.setmName(in[2]);
		addDTO.setObName(in[3]);
		addDTO.setEa(in[4]);
		addDTO.setPrice(in[5]);
		boolean swich = daoIns.Insert(addDTO);
		if (swich) {
			msgbox msgins = new msgbox();
			msgins.suessmsg();
		} else {
			// 에러메시지가 나오게
		}
	}

	public static void main(String[] args) {
		new Main();
	}
}