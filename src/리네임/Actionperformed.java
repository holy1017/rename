

package 리네임;

/*
 * 이름,번호,부제 
 *  
 *  키보드 딜키 삭제, 방향키로 이동
 *  
 *  
 * 
 * 
 * 
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

class Actionperformed implements ActionListener,MouseListener,KeyListener{


	DefaultTableModel mdVideo;
	DefaultTableModel mdVideo2;
	DefaultTableModel mdSmi;
	JTable jTableVideo;
	JTable jTableSmi;
	JLabel jLabelNum;
	JComboBox<?> j콤보[];
	JTextField j텍스트[];
	boolean Ctrl = false;
	boolean table = true;


	/**
	 * @param mdVideo  영상테이블
	 * @param mdVideo2 영상2 테이불 임시수정용
	 * @param jTableVideo 자막 테이블
	 * @param jLabelNum 수정창 앞의 라벨번호
	 * @param jcombo 콤보박스 숫자
	 * @param jtext 텍스트에리아 숫자
	 */
	//new Actionperformed(mdVideo, 자막테이블, mdEdit, mdSmi, 영상테이블, 번호라벨, j콤보, j텍스트);
	Actionperformed(DefaultTableModel mdVideo,JTable jTableSmi, DefaultTableModel mdVideo2,DefaultTableModel mdSmi,JTable jTableVideo,
			JLabel jLabelNum,	JComboBox<?> j콤보[],JTextField j텍스트[] ){
		this.mdVideo = mdVideo;
		this.mdVideo2 = mdVideo2;
		this.mdSmi= mdSmi;
		this.jTableVideo = jTableVideo;
		this.jTableSmi = jTableSmi;
		this.jLabelNum = jLabelNum;
		this.j콤보 = j콤보;
		this.j텍스트 =  j텍스트;

		for (int i = 0; i < j텍스트.length; i++) {
			j텍스트[i].addActionListener(this);// 텍스트 필드 액션 부여
		}
		jTableVideo.addMouseListener(this);
		jTableVideo.addKeyListener(this);
		jTableSmi.addMouseListener(this);
		jTableSmi.addKeyListener(this);
		//botten.addActionListener(this); // reWord용 앤션리슨어
	}
	public void actionPerformed(ActionEvent e) {

		//		 System.out.println(e);
		// System.out.println(e);
		// System.out.println(e.getSource()== jtext[0]);

		if (e.getSource() == j텍스트[0]) {
			// System.out.println(jcombo[0].getSelectedIndex());
			runtext(j콤보[0].getSelectedIndex(), 0);
		}
		if (e.getSource() == j텍스트[1]) {
			// System.out.println(jcombo[0].getSelectedIndex());
			runtext(j콤보[1].getSelectedIndex(), 1);
		}
		if (e.getSource() == j텍스트[2]) {
			// System.out.println(jcombo[0].getSelectedIndex());
			runtext(j콤보[2].getSelectedIndex(), 2);
		}
		if (e.getSource() == j텍스트[3]) {
			// System.out.println(jcombo[0].getSelectedIndex());
			runtext(j콤보[3].getSelectedIndex(), 3);
		}


	}

	void runtext(int jcomNum,int textnum){ // 앤션리슨어 실행 	jcomNum 콤보박스넘버 ,textnum 텍스트필드 박스 

		switch(jcomNum){
		case 0 :
			break;
		case 1 :
			str1(textnum);
			break;
		case 2:
			numbering(textnum);
			break;
		case 3:
			str2(textnum);
			break;
			//		case 4:
			//			str2(textnum);
			//			break;
		}
	}

	void setTextField(){  //마우스 리슨어로 찍은 번호로 텍스트 필드 다시 세팅
		int x=jTableVideo.getSelectedRow(); // 마우스리슨어로 행값 불러오기.
		//System.out.println(" x텍스트 "+x);
		for (int i = 0; i < 4; i++) {
			Object y = mdVideo2.getValueAt(x, i);

			j텍스트[i].setText((String) y);

		}

	}
	void strSum(int textnum){ //임시폴더의 데이터를 합하여 수정후로 보냄
		int k = mdVideo.findColumn("수정");
		for (int i = 0; i < mdVideo2.getRowCount(); i++) {
			String strss = "";
			for (int j = 0; j < 4; j++) {
				strss += mdVideo2.getValueAt(i, j);
			}
			mdVideo.setValueAt(strss, i, k);
		}
	}




	int s2n = 0; // str2Num순서 변수
	void moveNum (){ // 키보드로 표시 영역 이동시  번호 맞추기
		//System.out.println(jTableVideo.getSelectedRow());
		s2n = jTableVideo.getSelectedRow();
	}
	public int str2Num(){ // str2 순서넘기기
		s2n = jTableVideo.getSelectedRow();
		return s2n;
	}

	void str1(int textnum){ // 이름1 실행
		String strs = j텍스트[textnum].getText();

		for (int i = 0; i < mdVideo2.getRowCount(); i++) {
			mdVideo2.setValueAt(strs, i, textnum);
		}
		strSum(textnum);
	}
	void str2(int textnum){//이름2실행
		System.out.println(s2n+ "      "  +  mdVideo2.getRowCount());
		if(s2n < mdVideo2.getRowCount()){
			String strs = j텍스트[textnum].getText();
			mdVideo2.setValueAt(strs, s2n, textnum);
			if(s2n < mdVideo2.getRowCount()-1){
				s2n++;
				jLabelNum.setText(s2n + 1 + "");
			}
			j텍스트[textnum].setText(" ");

			strSum(textnum);
		}else{
			Actionperformed.showInfoBox("마지막줄 입니다.", ""); // 얼럿 내용
		}
	}
	public static void showInfoBox(String msg, String title) { // 얼럿 박스 생성

		JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);

	}

	boolean numCheckb =true; // 넘버링 숫자체크 블린

	void numCheck (int textnum){
		numCheckb = true;
		String regex = "[0-9]*";
		if(j텍스트[textnum].getText().matches(regex) == true){

		}else{
			Actionperformed.showInfoBox("자리수를 포함한 숫자만 입력하세요\n ex) 01 , 001 ,  중간숫자 05 005", ""); // 얼럿 내용
			numCheckb = false;
		}
	}

	void numbering(int textnum){ //n1 = 자리수 n2 = 시작값

		numCheck(textnum);
		if(numCheckb == true){
			int n1,n2;  //n1 = 자리수 n2 = 시작값, dn = 텍스트필드 
			n2 =Integer.parseInt(j텍스트[textnum].getText());

			for (int i = 0; i < mdVideo2.getRowCount() ; i++) { // 배열 길이 넣기
				String n3 =""+n2;
				n1 = j텍스트[textnum].getText().length();
				int n4 = n3.length();
				for (int j = 0; j < n1-n4; j++) {
					n3 = "0" +n3;
				}
				mdVideo2.setValueAt(n3,i, textnum);
				n2++;
				//System.out.println(n3);
			}
			strSum(textnum);
		}
	}
	//	void moveKeyUp(){ //키보드로 위로이동
	//		System.out.println("up 실행전"+ table);
	//		if(table == true){
	//			System.out.println(table);
	//			//		mdVideo.moveRow(jTableVideo.getSelectedRow() +1, jTableVideo.getSelectedRow() +1, jTableVideo.getSelectedRow() );
	//			int Y = jTableVideo.getSelectedRow();
	//			mdVideo.moveRow(Y , Y , Y -1);
	//			jTableVideo.setRowSelectionInterval(Y-1 , Y-1);
	//		}else if(table == false){
	//			System.out.println(table);
	//			int Y = jTableSmi.getSelectedRow();
	//			mdSmi.moveRow( Y , Y , Y -1);
	//			jTableSmi.setRowSelectionInterval(Y-1 , Y-1);
	//
	//		}
	//	}
	//	void moveKeyDown(){ //키보드로 아래로이동
	//		System.out.println("down 실행전"+ table);
	//		if(table == true){
	//			System.out.println(table);
	//			//		mdVideo.moveRow(jTableVideo.getSelectedRow() -1, jTableVideo.getSelectedRow() -1, jTableVideo.getSelectedRow() );
	//			int x = jTableVideo.getSelectedRow();
	//			mdVideo.moveRow(x , x , x +1);
	//			jTableVideo.setRowSelectionInterval(x+1 , x+1);
	//		}else if(table == false){
	//			System.out.println(table);
	//			int Y = jTableSmi.getSelectedRow();
	//			mdSmi.moveRow(Y , Y , Y +1);
	//			jTableSmi.setRowSelectionInterval(Y+1 , Y+1);
	//		}
	//	}


	void moveKeyUp(){ //키보드로 위로이동
		//System.out.println("up 실행전"+ table);
		if(table == true){
			int [] x = jTableVideo.getSelectedRows();
			for (int i = 0; i < x.length; i++) {
				mdVideo.moveRow(x[i] , x[i] , x[0]+i-1);
			}
			jTableVideo.setRowSelectionInterval(x[0]-1 , x[0]+x.length-2);
		}else if(table == false){
			int [] x = jTableSmi.getSelectedRows();
			for (int i = 0; i < x.length; i++) {
				mdSmi.moveRow(x[i] , x[i] , x[0]+i-1);
			}
			jTableSmi.setRowSelectionInterval(x[0]-1 , x[0]+x.length-2);

			//			System.out.println(table);
			//			int y = jTableSmi.getSelectedRow();
			//			mdSmi.moveRow( y , y , y -1);
			//			jTableSmi.setRowSelectionInterval(y-1 , y-1);

		}
	}
	void moveKeyDown(){ //키보드로 아래로이동
		//System.out.println("down 실행전"+ table);
		if(table == true){
			int [] x = jTableVideo.getSelectedRows();
			//System.out.println("mdVideo.getRowCount() :" +mdVideo.getRowCount() + "x[x.length-1 :"+ x[x.length-1]);
			if(mdVideo.getRowCount()-1 == x[x.length-1]){

			}else if (mdVideo.getRowCount() > x[x.length-1]){
				for (int i = 0; i < x.length; i++) {
					mdVideo.moveRow(x[x.length-1-i] , x[x.length-1-i] , x[x.length-1]+1-i);
					//mdVideo.moveRow(start, end, to);
				}
				jTableVideo.setRowSelectionInterval(x[x.length-1]-x.length+2 , x[x.length-1]+1);
			}

		}else if(table == false){
			int [] x = jTableSmi.getSelectedRows();
			//System.out.println("mdVideo.getRowCount() :" +			mdSmi.getRowCount() + "x[x.length-1 :"+ x[x.length-1]);
			if(mdSmi.getRowCount()-1 == x[x.length-1]){

			}else if (mdSmi.getRowCount() > x[x.length-1]){
				for (int i = 0; i < x.length; i++) {
					mdSmi.moveRow(x[x.length-1-i] , x[x.length-1-i] , x[x.length-1]+1-i);
					//mdVideo.moveRow(start, end, to);
				}
				jTableSmi.setRowSelectionInterval(x[x.length-1]-x.length+2 , x[x.length-1]+1);
			}
			//System.out.println(table);
			//			int Y = jTableSmi.getSelectedRow();
			//			mdSmi.moveRow(Y , Y , Y +1);
			//			jTableSmi.setRowSelectionInterval(Y+1 , Y+1);
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
	}
	@Override
	public void mousePressed(MouseEvent e) {

	}
	@Override
	public void mouseReleased(MouseEvent e) {

		if(e.getButton() == 1){
			if(e.getSource() == jTableVideo){

				setTextField();// 마우스로 찍었을때 행번호 돌려줌
				str2Num();

				jTableVideo.setCellSelectionEnabled(true);
				jTableSmi.setCellSelectionEnabled(false);
				jTableSmi.clearSelection();
				table = true;
			}
			if(e.getSource() == jTableSmi){
				jTableSmi.setCellSelectionEnabled(true);
				jTableVideo.setCellSelectionEnabled(false);
				jTableVideo.clearSelection();
				table = false;
			}
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {

	}
	@Override
	public void mouseExited(MouseEvent e) {

	}
	@Override
	public void keyTyped(KeyEvent e) {

	}
	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == 17) {
			Ctrl = true;
		}
		//System.out.println("keyCode : " + e.getKeyCode()+Ctrl);

		if (e.getKeyCode() == 38 && Ctrl == true) { // 위쪽 방향키
			if (jTableVideo.getSelectedRow() > 0 && table == true) {
				//System.out.println("위로");
				moveKeyUp();
			}
			if (jTableSmi.getSelectedRow() > 0 && table == false) {
				//System.out.println("위로");
				moveKeyUp();
			}
		}
		if (e.getKeyCode() == 40 && Ctrl == true) { // 아래쪽 방향키
			if (jTableVideo.getSelectedRow() < mdVideo.getRowCount()-1 && table == true) {
				moveKeyDown();
			}
			if (jTableSmi.getSelectedRow() < mdSmi.getRowCount()-1 && table == false) {
				moveKeyDown();
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_DELETE){

			del();
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		//		System.out.println("keyCode : " + e.getKeyCode());

		if (e.getKeyCode() == 17) {
			Ctrl = false;
		}

		if(e.getKeyCode() == 38 || e.getKeyCode() == 40 ) 
			moveNum();

	}
	void del (){  // 딜키로 목록 삭제
		//System.out.println("제이테이블  :"+jTableVideo.getSelectedRow());
		//System.out.println("제이테이블 화면 :"+jTableVideo.getRowCount());
		//System.out.println("영상테이블  :"+mdVideo.getRowCount());

		//System.out.println("실행전" + table);

		if(table == true){
			int x= jTableVideo.getSelectedRow();

			if(jTableVideo.getRowCount() > 0){
				int [] temp = jTableVideo.getSelectedRows();
				for (int i = 0; i < temp.length; i++) {
					mdVideo.removeRow(x); 
				}

				if(jTableVideo.getRowCount() == x )
					if(jTableVideo.getRowCount() == 0){
					}else{
						//System.out.println("실행");
						jTableVideo.setRowSelectionInterval(x-1, x-1);
					}
				else if(jTableVideo.getRowCount() > x)
					jTableVideo.setRowSelectionInterval(x, x);
			}
		}else if(table == false){
			//System.out.println("false 자막테이블 카운트"+jTableSmi.getRowCount());

			int x= jTableSmi.getSelectedRow();

			if(jTableSmi.getRowCount() > 0){
				int [] temp = jTableSmi.getSelectedRows();
				for (int i = 0; i < temp.length; i++) {
					mdSmi.removeRow(x); 
				}

				if(jTableSmi.getRowCount() == x )
					if(jTableSmi.getRowCount() == 0){
					}else{
						//System.out.println("실행");
						jTableSmi.setRowSelectionInterval(x-1, x-1);
					}
				else if(jTableSmi.getRowCount() > x)
					jTableSmi.setRowSelectionInterval(x, x);
			}
		}
		//System.out.println(jTableVideo.getSelectedRows());
	}

}
