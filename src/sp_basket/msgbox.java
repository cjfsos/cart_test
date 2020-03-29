package sp_basket;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class msgbox extends JFrame implements ActionListener{
	private static msgbox msgins = null;
	JButton check = null;

	public msgbox() {
	}

	public static msgbox getins() {
		return msgins;
	}
	
	void suessmsg(){
		Dimension size = new Dimension(260, 150);		
		this.setSize(size);
		JLabel msg = new JLabel("정상처리 되었습니다.");
		check = new JButton("확인");
		this.add(msg,"Center");
		this.add(check,"South");
		check.addActionListener(this);
		this.setVisible(true);
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
	}
	
	void fail(){
		Dimension size = new Dimension(260, 150);
		this.setSize(size);
		JLabel msg = new JLabel("명령을 수행하지 못했습니다..");
		check = new JButton("확인");
		this.add(msg,"Center");
		this.add(check,"South");
		check.addActionListener(this);
		this.setVisible(true);
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
	}
	
	void erorrmsg(){
		Dimension size = new Dimension(260, 150);
		this.setSize(size);
		JLabel msg = new JLabel("입력값이 부족합니다. 전부 입력해주세요.");
		check = new JButton("확인");
		this.add(msg,"Center");
		this.add(check,"South");
		check.addActionListener(this);
		this.setVisible(true);
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
	}
	
	void rowerorrmsg(){
		Dimension size = new Dimension(260, 150);
		this.setSize(size);
		JLabel msg = new JLabel("행을 선택한 후 삭제를 눌러주세요");
		check = new JButton("확인");
		this.add(msg,"Center");
		this.add(check,"South");
		check.addActionListener(this);
		this.setVisible(true);
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
	}


	public void Moderorrmsg() {
		Dimension size = new Dimension(260, 150);
		this.setSize(size);
		JLabel msg = new JLabel("행을 선택한 후 수정을 눌러주세요");
		check = new JButton("확인");
		this.add(msg,"Center");
		this.add(check,"South");
		check.addActionListener(this);
		this.setVisible(true);
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
	}	
	
	public void Moderorrmsg2() {
		Dimension size = new Dimension(280, 150);
		this.setSize(size);
		JLabel msg = new JLabel("TextField에 값을 가져온 후 수정을 눌러주세요");
		check = new JButton("확인");
		this.add(msg,"Center");
		this.add(check,"South");
		check.addActionListener(this);
		this.setVisible(true);
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
	}

	public void Moderorrmsg3() {
		Dimension size = new Dimension(280, 150);
		this.setSize(size);
		JLabel msg = new JLabel("행의 값 가져오기를 다시 수행해 주세요.");
		check = new JButton("확인");
		this.add(msg,"Center");
		this.add(check,"South");
		check.addActionListener(this);
		this.setVisible(true);
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
	}
	
	public void Moderorrmsg4() {
		Dimension size = new Dimension(280, 150);
		this.setSize(size);
		JLabel msg = new JLabel("textFild의 값을 수정해주세요.");
		check = new JButton("확인");
		this.add(msg,"Center");
		this.add(check,"South");
		check.addActionListener(this);
		this.setVisible(true);
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
	}
	
	public void Moderorrmsg5() {
		Dimension size = new Dimension(280, 150);
		this.setSize(size);
		JLabel msg = new JLabel("oracle 전송에 실패했습니다.");
		check = new JButton("확인");
		this.add(msg,"Center");
		this.add(check,"South");
		check.addActionListener(this);
		this.setVisible(true);
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
	}
	
	@Override
	public void actionPerformed(ActionEvent s) {
		Object ck = s.getSource();
		if(ck.equals(check)) {
			this.dispose();
		}
	}

	public void modsuccess() {
		Dimension size = new Dimension(280, 150);
		this.setSize(size);
		JLabel msg = new JLabel("수정에 성공했습니다.");
		check = new JButton("확인");
		this.add(msg,"Center");
		this.add(check,"South");
		check.addActionListener(this);
		this.setVisible(true);
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
	}
}
