package ������;

import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class renameApplyVideo extends JFrame {
	
	
	public renameApplyVideo( DefaultTableModel mdVideo,DefaultTableModel mdSmi ) throws IOException {
		int vc = mdVideo.getRowCount();
		int sc =	mdSmi.getRowCount();
		int jun = mdVideo.findColumn("��ü���");
		int su	= mdVideo.findColumn("����");
		int root = mdVideo.findColumn("���");
		int afterName = mdVideo.findColumn("���ϸ�");
		int ex = mdVideo.findColumn("Ȯ����");
		
		
		boolean moveOk;
		if(vc==sc)
		{	
			
			for (int i = 0; i < vc; i++) 
			{	
				
				String oldAll = (String) mdVideo.getValueAt(i, jun);
				String newName = (String)mdVideo.getValueAt(i, su);
				String hwak = (String)mdVideo.getValueAt(i, ex);
				String fileroot = (String) mdVideo.getValueAt(i, root);
				File fold = new File(oldAll);   
				File fnew = new File(fileroot+"\\"+newName+"."+hwak);
				System.out.println(fold);
				System.out.println(fnew);
				if(fold.isFile()){
					moveOk = fold.renameTo(fnew);
					if(!moveOk){
						JOptionPane.showMessageDialog(null, "�̸��� �ߺ��˴ϴ�", "�̸��ߺ�Error" , JOptionPane.WARNING_MESSAGE);
						break;
					}
					mdVideo.setValueAt(newName, i, afterName);	
					mdVideo.setValueAt(fileroot+"\\"+newName+"."+hwak, i, jun);
				}else{
					JOptionPane.showMessageDialog(null, "������ġ�� ������ �����ϴ�.", "���翩��Error" , JOptionPane.WARNING_MESSAGE);
					break;
				}
			}
		}else if(sc==0){
			
			
			
			for (int i = 0; i < vc; i++) 
			{
				String oldAll = (String) mdVideo.getValueAt(i, jun);
				String newName = (String)mdVideo.getValueAt(i, su);
				String hwak = (String)mdVideo.getValueAt(i, ex);
				String fileroot = (String) mdVideo.getValueAt(i, root);
				File fold = new File(oldAll);   
				File fnew = new File(fileroot+"\\"+newName+"."+hwak);
				
				if(fold.isFile()){
					moveOk = fold.renameTo(fnew);
					if(!moveOk){
						JOptionPane.showMessageDialog(null, "�̸��� �ߺ��˴ϴ�", "�̸��ߺ�Error" , JOptionPane.WARNING_MESSAGE);
						break;
					}
					
					mdVideo.setValueAt(newName, i, afterName);	
					mdVideo.setValueAt(fileroot+"\\"+newName+"."+hwak, i, jun);
				}else{
					JOptionPane.showMessageDialog(null, "������ġ�� ������ �����ϴ�.", "���翩��Error" , JOptionPane.WARNING_MESSAGE);
					break;
				}
			}
		}
		
		else{
			JOptionPane.showMessageDialog(null, "�ڸ����ϰ� ���������� ���� �ٸ��ϴ�", "�హ��Error" , JOptionPane.WARNING_MESSAGE);
		}
		
	}
}

