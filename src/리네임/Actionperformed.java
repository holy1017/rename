

package ������;

/*
 * �̸�,��ȣ,���� 
 *  
 *  Ű���� ��Ű ����, ����Ű�� �̵�
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
	JComboBox<?> j�޺�[];
	JTextField j�ؽ�Ʈ[];
	boolean Ctrl = false;
	boolean table = true;


	/**
	 * @param mdVideo  �������̺�
	 * @param mdVideo2 ����2 ���̺� �ӽü�����
	 * @param jTableVideo �ڸ� ���̺�
	 * @param jLabelNum ����â ���� �󺧹�ȣ
	 * @param jcombo �޺��ڽ� ����
	 * @param jtext �ؽ�Ʈ������ ����
	 */
	//new Actionperformed(mdVideo, �ڸ����̺�, mdEdit, mdSmi, �������̺�, ��ȣ��, j�޺�, j�ؽ�Ʈ);
	Actionperformed(DefaultTableModel mdVideo,JTable jTableSmi, DefaultTableModel mdVideo2,DefaultTableModel mdSmi,JTable jTableVideo,
			JLabel jLabelNum,	JComboBox<?> j�޺�[],JTextField j�ؽ�Ʈ[] ){
		this.mdVideo = mdVideo;
		this.mdVideo2 = mdVideo2;
		this.mdSmi= mdSmi;
		this.jTableVideo = jTableVideo;
		this.jTableSmi = jTableSmi;
		this.jLabelNum = jLabelNum;
		this.j�޺� = j�޺�;
		this.j�ؽ�Ʈ =  j�ؽ�Ʈ;

		for (int i = 0; i < j�ؽ�Ʈ.length; i++) {
			j�ؽ�Ʈ[i].addActionListener(this);// �ؽ�Ʈ �ʵ� �׼� �ο�
		}
		jTableVideo.addMouseListener(this);
		jTableVideo.addKeyListener(this);
		jTableSmi.addMouseListener(this);
		jTableSmi.addKeyListener(this);
		//botten.addActionListener(this); // reWord�� �ؼǸ�����
	}
	public void actionPerformed(ActionEvent e) {

		//		 System.out.println(e);
		// System.out.println(e);
		// System.out.println(e.getSource()== jtext[0]);

		if (e.getSource() == j�ؽ�Ʈ[0]) {
			// System.out.println(jcombo[0].getSelectedIndex());
			runtext(j�޺�[0].getSelectedIndex(), 0);
		}
		if (e.getSource() == j�ؽ�Ʈ[1]) {
			// System.out.println(jcombo[0].getSelectedIndex());
			runtext(j�޺�[1].getSelectedIndex(), 1);
		}
		if (e.getSource() == j�ؽ�Ʈ[2]) {
			// System.out.println(jcombo[0].getSelectedIndex());
			runtext(j�޺�[2].getSelectedIndex(), 2);
		}
		if (e.getSource() == j�ؽ�Ʈ[3]) {
			// System.out.println(jcombo[0].getSelectedIndex());
			runtext(j�޺�[3].getSelectedIndex(), 3);
		}


	}

	void runtext(int jcomNum,int textnum){ // �ؼǸ����� ���� 	jcomNum �޺��ڽ��ѹ� ,textnum �ؽ�Ʈ�ʵ� �ڽ� 

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

	void setTextField(){  //���콺 ������� ���� ��ȣ�� �ؽ�Ʈ �ʵ� �ٽ� ����
		int x=jTableVideo.getSelectedRow(); // ���콺������� �ప �ҷ�����.
		//System.out.println(" x�ؽ�Ʈ "+x);
		for (int i = 0; i < 4; i++) {
			Object y = mdVideo2.getValueAt(x, i);

			j�ؽ�Ʈ[i].setText((String) y);

		}

	}
	void strSum(int textnum){ //�ӽ������� �����͸� ���Ͽ� �����ķ� ����
		int k = mdVideo.findColumn("����");
		for (int i = 0; i < mdVideo2.getRowCount(); i++) {
			String strss = "";
			for (int j = 0; j < 4; j++) {
				strss += mdVideo2.getValueAt(i, j);
			}
			mdVideo.setValueAt(strss, i, k);
		}
	}




	int s2n = 0; // str2Num���� ����
	void moveNum (){ // Ű����� ǥ�� ���� �̵���  ��ȣ ���߱�
		//System.out.println(jTableVideo.getSelectedRow());
		s2n = jTableVideo.getSelectedRow();
	}
	public int str2Num(){ // str2 �����ѱ��
		s2n = jTableVideo.getSelectedRow();
		return s2n;
	}

	void str1(int textnum){ // �̸�1 ����
		String strs = j�ؽ�Ʈ[textnum].getText();

		for (int i = 0; i < mdVideo2.getRowCount(); i++) {
			mdVideo2.setValueAt(strs, i, textnum);
		}
		strSum(textnum);
	}
	void str2(int textnum){//�̸�2����
		System.out.println(s2n+ "      "  +  mdVideo2.getRowCount());
		if(s2n < mdVideo2.getRowCount()){
			String strs = j�ؽ�Ʈ[textnum].getText();
			mdVideo2.setValueAt(strs, s2n, textnum);
			if(s2n < mdVideo2.getRowCount()-1){
				s2n++;
				jLabelNum.setText(s2n + 1 + "");
			}
			j�ؽ�Ʈ[textnum].setText(" ");

			strSum(textnum);
		}else{
			Actionperformed.showInfoBox("�������� �Դϴ�.", ""); // �� ����
		}
	}
	public static void showInfoBox(String msg, String title) { // �� �ڽ� ����

		JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);

	}

	boolean numCheckb =true; // �ѹ��� ����üũ ��

	void numCheck (int textnum){
		numCheckb = true;
		String regex = "[0-9]*";
		if(j�ؽ�Ʈ[textnum].getText().matches(regex) == true){

		}else{
			Actionperformed.showInfoBox("�ڸ����� ������ ���ڸ� �Է��ϼ���\n ex) 01 , 001 ,  �߰����� 05 005", ""); // �� ����
			numCheckb = false;
		}
	}

	void numbering(int textnum){ //n1 = �ڸ��� n2 = ���۰�

		numCheck(textnum);
		if(numCheckb == true){
			int n1,n2;  //n1 = �ڸ��� n2 = ���۰�, dn = �ؽ�Ʈ�ʵ� 
			n2 =Integer.parseInt(j�ؽ�Ʈ[textnum].getText());

			for (int i = 0; i < mdVideo2.getRowCount() ; i++) { // �迭 ���� �ֱ�
				String n3 =""+n2;
				n1 = j�ؽ�Ʈ[textnum].getText().length();
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
	//	void moveKeyUp(){ //Ű����� �����̵�
	//		System.out.println("up ������"+ table);
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
	//	void moveKeyDown(){ //Ű����� �Ʒ����̵�
	//		System.out.println("down ������"+ table);
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


	void moveKeyUp(){ //Ű����� �����̵�
		//System.out.println("up ������"+ table);
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
	void moveKeyDown(){ //Ű����� �Ʒ����̵�
		//System.out.println("down ������"+ table);
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

				setTextField();// ���콺�� ������� ���ȣ ������
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

		if (e.getKeyCode() == 38 && Ctrl == true) { // ���� ����Ű
			if (jTableVideo.getSelectedRow() > 0 && table == true) {
				//System.out.println("����");
				moveKeyUp();
			}
			if (jTableSmi.getSelectedRow() > 0 && table == false) {
				//System.out.println("����");
				moveKeyUp();
			}
		}
		if (e.getKeyCode() == 40 && Ctrl == true) { // �Ʒ��� ����Ű
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
	void del (){  // ��Ű�� ��� ����
		//System.out.println("�������̺�  :"+jTableVideo.getSelectedRow());
		//System.out.println("�������̺� ȭ�� :"+jTableVideo.getRowCount());
		//System.out.println("�������̺�  :"+mdVideo.getRowCount());

		//System.out.println("������" + table);

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
						//System.out.println("����");
						jTableVideo.setRowSelectionInterval(x-1, x-1);
					}
				else if(jTableVideo.getRowCount() > x)
					jTableVideo.setRowSelectionInterval(x, x);
			}
		}else if(table == false){
			//System.out.println("false �ڸ����̺� ī��Ʈ"+jTableSmi.getRowCount());

			int x= jTableSmi.getSelectedRow();

			if(jTableSmi.getRowCount() > 0){
				int [] temp = jTableSmi.getSelectedRows();
				for (int i = 0; i < temp.length; i++) {
					mdSmi.removeRow(x); 
				}

				if(jTableSmi.getRowCount() == x )
					if(jTableSmi.getRowCount() == 0){
					}else{
						//System.out.println("����");
						jTableSmi.setRowSelectionInterval(x-1, x-1);
					}
				else if(jTableSmi.getRowCount() > x)
					jTableSmi.setRowSelectionInterval(x, x);
			}
		}
		//System.out.println(jTableVideo.getSelectedRows());
	}

}
