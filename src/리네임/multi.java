package ������;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class multi implements ActionListener, KeyListener, MouseListener {
	DefaultTableModel mdVideo;
	DefaultTableModel mdSmi;
	JTable jTableVideo;
	JTable jTableSmi;
	JButton listTop;
	JButton listUp;
	JButton listDown;
	JButton listBottom;
	JButton del;
	boolean vs = false;

	public multi(DefaultTableModel mdVideo, DefaultTableModel mdSmi,
			JTable jTableVideo, JTable jTableSmi, JButton listTop,
			JButton listUp, JButton listDown, JButton listBottom, JButton del) {

		this.jTableVideo = jTableVideo;
		this.mdSmi = mdSmi;
		this.mdVideo = mdVideo;
		this.jTableSmi = jTableSmi;
		this.listTop = listTop;
		this.listUp = listUp;
		this.listDown = listDown;
		this.listBottom = listBottom;
		this.del = del;
		listUp.addActionListener(this);
		listDown.addActionListener(this);
		listTop.addActionListener(this);
		listBottom.addActionListener(this);
		del.addActionListener(this);
		jTableVideo.addMouseListener(this);
		jTableSmi.addMouseListener(this);

	}

	void TUp() { // ������

		int vt[] = jTableVideo.getSelectedRows(); // ���� ������
		int st[] = jTableSmi.getSelectedRows(); // �ڸ� ������

		if (vs) { // �����ý� ����

			for (int i = 0; i < vt.length; i++) {
				if (vt[i] > i) {

					mdVideo.moveRow(vt[i], vt[i], i);
					// jTableVideo.removeRowSelectionInterval(vt[i], vt[i]);
					jTableVideo.setRowSelectionInterval(0, vt.length - 1);
				}

			}
		} else { // �ڸ����ý� ����

			for (int i = 0; i < st.length; i++) {
				if (st[i] > i) {
					mdSmi.moveRow(st[i], st[i], i); // ���̵�
					jTableSmi.setRowSelectionInterval(0, st.length - 1);
					// jTableSmi.removeRowSelectionInterval(st[i], st[i]);
				}
			}
		}
	}

	void Up() { // ����

		int vt[] = jTableVideo.getSelectedRows(); // ���� ������
		int st[] = jTableSmi.getSelectedRows(); // �ڸ� ������

		if (vs) { // �����ý� ����
			/*
			 * if (jTableVideo.getSelectedRow() == 0) { } else {
			 */
			for (int i = 0; i < vt.length; i++) {
				if (vt[i] > i) {
					mdVideo.moveRow(vt[i], vt[i], vt[i] - 1); // ���̵�
					jTableVideo.addRowSelectionInterval(vt[i] - 1, vt[i]);// Ŀ��
																			// �̵�
					jTableVideo.removeRowSelectionInterval(vt[i], vt[i]);
				}
				// jTableVideo.changeSelection(vt[i], 1, true,true);
			}

		} else { // �ڸ����ý� ����

			for (int i = 0; i < st.length; i++) {
				if (st[i] > i) {
					mdSmi.moveRow(st[i], st[i], st[i] - 1); // ���̵�
					jTableSmi.addRowSelectionInterval(st[i] - 1, st[i]); // Ŀ���̵�
					jTableSmi.removeRowSelectionInterval(st[i], st[i]);

				}
			}
		}
	}

	void Down() { // �Ʒ���

		int vt[] = jTableVideo.getSelectedRows(); // ���� ������
		int st[] = jTableSmi.getSelectedRows(); // �ڸ� ������

		if (vs) { // �����ý� ����
			/*
			 * if (jTableVideo.getSelectedRow() == jTableVideo.getRowCount() -
			 * 1) { } else {
			 */
			for (int i = vt.length - 1; i >= 0; i--) {
				/*
				 * System.out.println( vt[i]+":"+( jTableSmi.getRowCount()
				 * -(vt.length-i) ));
				 */
				if (vt[i] < jTableSmi.getRowCount() - (vt.length - i)) {
					mdVideo.moveRow(vt[i], vt[i], vt[i] + 1); // ���̵�
					jTableVideo.addRowSelectionInterval(vt[i], vt[i] + 1);
					jTableVideo.removeRowSelectionInterval(vt[i], vt[i]);
				}
			}
		}

		// �ڸ����ý� ����

		for (int i = 0; i < st.length; i++) {
			if (st[i] < jTableSmi.getRowCount() - (st.length - i)) {
				mdSmi.moveRow(st[i], st[i], st[i] + 1); // ���̵�
				jTableSmi.addRowSelectionInterval(st[i] + 1, st[i]); // Ŀ���̵�

				jTableSmi.removeRowSelectionInterval(st[i], st[i]);

			}
		}
	}

	void BDown() { // �ǾƷ���

		int vt[] = jTableVideo.getSelectedRows(); // ���� ������
		int st[] = jTableSmi.getSelectedRows(); // �ڸ� ������

		if (vs) { // �����ý� ����
			if (jTableVideo.getSelectedRow() == jTableVideo.getRowCount() - 1) {
			} else {
				for (int i = 0; i < vt.length; i++) {
					mdVideo.moveRow(vt[i] - i, vt[i] - i,
							mdVideo.getRowCount() - 1); // ���̵�
					//jTableVideo.removeRowSelectionInterval(vt[i], vt[i]);
					//jTableVideo.addRowSelectionInterval(mdVideo.getRowCount()- vt.length, mdVideo.getRowCount() - 1);// ����
					jTableVideo.setRowSelectionInterval(mdVideo.getRowCount() - vt.length , jTableVideo.getRowCount() - 1);
				}
			}
		} else { // �ڸ����ý� ����
			if (jTableSmi.getSelectedRow() == jTableSmi.getRowCount() - 1) {
			} else {
				for (int i = 0; i < st.length; i++) {
					mdSmi.moveRow(st[i] - i, st[i] - i, mdSmi.getRowCount() - 1); // ���̵�
					//jTableSmi.addRowSelectionInterval(mdSmi.getRowCount()- st.length, mdSmi.getRowCount() - 1); // Ŀ���̵�
					//jTableSmi.removeRowSelectionInterval(st[i], st[i]);
					jTableSmi.setRowSelectionInterval(mdSmi.getRowCount() - st.length , mdSmi.getRowCount() - 1);

				}
			}
		}
	}

	void del() {
		int vt[] = jTableVideo.getSelectedRows(); // ���� ������
		int st[] = jTableSmi.getSelectedRows(); // �ڸ� ������
		if (vs) { // �����ý� ����

			for (int i = 0; i < vt.length; i++) {
				System.out.println(vt[i]);
				mdVideo.removeRow(vt[i]-i); // �����
				// jTableVideo.setRowSelectionInterval(vt[i], vt[i]); // Ŀ���̵�
			}

		} else { // �ڸ����ý� ����
			if (jTableSmi.getSelectedRow() == 0) {
			} else {
				for (int i = 0; i < st.length; i++) {
					mdSmi.removeRow(st[i]-i); // �����

				}
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == jTableSmi) {

			jTableVideo.setCellSelectionEnabled(false);
			jTableSmi.setCellSelectionEnabled(true);
			vs = false;
		}
		if (e.getSource() == jTableVideo) {
			jTableVideo.setCellSelectionEnabled(true);
			jTableSmi.setCellSelectionEnabled(false);
			vs = true;

		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == listTop) {
			TUp();
		}

		if (e.getSource() == listUp) {
			Up();
		}
		if (e.getSource() == listDown) {
			Down();
		}
		if (e.getSource() == listBottom) {
			BDown();
		}

		if (e.getSource() == del) {
			del();
		}
		// TODO Auto-generated method stub

	}

}
