package ������;

import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class renameApplySmi extends JFrame {
	
	
	public renameApplySmi( DefaultTableModel mdVideo ,DefaultTableModel mdSmi) throws IOException {
		
		
		int vc = mdVideo.getRowCount();
		int sc =	mdSmi.getRowCount();
		int jun = mdSmi.findColumn("��ü���");
		int su	= mdVideo.findColumn("����");
		int root = mdSmi.findColumn("���");
		int afterName = mdSmi.findColumn("�ڸ���");
		int ex = mdSmi.findColumn("Ȯ����");
		boolean moveOk;
		if(vc==sc){
			for (int i = 0; i < vc; i++) 
			{
				
				String oldAll = (String) mdSmi.getValueAt(i, jun);
				String newName = (String)mdVideo.getValueAt(i, su);
				String hwak = (String)mdSmi.getValueAt(i, ex);
				String fileroot = (String) mdSmi.getValueAt(i, root);
				File fold = new File(oldAll);   
				File fnew = new File(fileroot+"\\"+newName+"."+hwak);
				if(fold.isFile()){
					moveOk = fold.renameTo(fnew);
					if(!moveOk){
						JOptionPane.showMessageDialog(null, "�̸��� �ߺ��˴ϴ�", "�̸��ߺ�Error" , JOptionPane.WARNING_MESSAGE);
						break;
					}
					
					mdSmi.setValueAt(newName, i, afterName);	
					mdSmi.setValueAt(fileroot+"\\"+newName+"."+hwak, i, jun);	
				}else{
					JOptionPane.showMessageDialog(null, "������ġ�� ������ �����ϴ�.", "���翩��Error" , JOptionPane.WARNING_MESSAGE);
					break;
				}
			}
		}else{
			JOptionPane.showMessageDialog(null, "�ڸ����ϰ� ���������� ���� �ٸ��ϴ�", "�హ��Error" , JOptionPane.WARNING_MESSAGE);
			
		}
	}
}

