package ������;

import java.awt.FileDialog;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

class renameAdd extends JFrame { 
	public renameAdd(JButton fileAdd, DefaultTableModel mdVideo ,DefaultTableModel mdSmi, DefaultTableModel mdEdit) throws IOException 
    {  
    	FileDialog fd = new FileDialog(this, "���� �߰�", FileDialog.LOAD);//������â ����
    	fd.setMultipleMode(true);
    	fd.setVisible(true); 
    	
    	File[] F = fd.getFiles(); 
    	
    	for (int i = 0; i < F.length; i++) {
			File file = F[i];
			String fileRoot = file.getCanonicalPath();//����� ���ϰ��
	    	String fileName = file.getName(); //����� ���ϸ�
	    	int pos = fileName.lastIndexOf("."); //Ȯ���� ���п�
	    	int cut = fileRoot.lastIndexOf("\\");
	    	String t = "avi,wmv,mp4,mkv,mpg,mpeg,asf,tp,ts,mov,skm,k3g,flv";//����Ȯ����
	    	String lowerM = fileName.substring(pos+1).toLowerCase();//Ȯ���� �ҹ��ں�ȭ
	    	String lowerV = fileName.substring(pos+1).toLowerCase();//Ȯ���� �ҹ��ں�ȭ
	    	
	    	if (lowerV.equals("smi")){//�ڸ����̺��߰�
	    		int j = mdSmi.findColumn("��ü���"); //��ü��� ��ġã��
	    		boolean find = false ;
	    		int count = mdSmi.getRowCount(); //���ȣ�� �����ֱ�
	    		for (int z = 0; z < count; z++) {
	    			String root = (String) mdSmi.getValueAt(z, j); //
	    			if(root.equals(fileRoot)){ //�ߺ����ϰ˻�
	    				JOptionPane.showMessageDialog(null, "���������� ���Խ��ϴ�", "�ߺ� Error", JOptionPane.WARNING_MESSAGE);
	    				find = true;
	    				break;
	    			}
	 
	    		}
	    		if(!find){
	    		String[] st = { (count+1)+"" ,fileRoot.substring(0, cut) , fileName.substring(0,pos) , lowerV, fileRoot };
	    		mdSmi.addRow(st);
	    		}
	    	}
	    	
	    	
	    	else if(t.contains(lowerM)){
	    	//else if(fileName.substring(pos+1).equals("avi")){//�������̺��߰�
	    		int j = mdVideo.findColumn("��ü���"); //��ü��� ��ġã��
	    		boolean find = false ;
	    		int count = mdVideo.getRowCount();
	    		for (int z = 0; z < count; z++) {
	    			String root = (String) mdVideo.getValueAt(z, j); //
	    			if(root.equals(fileRoot)){ //�ߺ����ϰ˻�
	    				JOptionPane.showMessageDialog(null, "���������� ���Խ��ϴ�", "�ߺ� Error", JOptionPane.WARNING_MESSAGE);
	    				find = true;
	    				break;
	    			}
	 
	    		}
	    		if(!find){
	    		String[] st = { (count+1)+"" ,fileRoot.substring(0, cut) , fileName.substring(0,pos), fileName.substring(0,pos)/*����*/ , lowerM, fileRoot };
	    		String[] ed = { "","","",""};
	    		mdEdit.addRow(ed);
	    		mdVideo.addRow(st);
	    		
	    		}
	    		
	    		
	    	}
	    	else{
	    		JOptionPane.showMessageDialog(null, "����� �ڸ����ϸ� �־��ּ���", "Ȯ���� Error", JOptionPane.WARNING_MESSAGE);
	    	}
		}
    	
    	
    	
    	
    	
    	
    }
} //end of FileDialogFrame class 