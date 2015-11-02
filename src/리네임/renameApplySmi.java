package 리네임;

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
		int jun = mdSmi.findColumn("전체경로");
		int su	= mdVideo.findColumn("수정");
		int root = mdSmi.findColumn("경로");
		int afterName = mdSmi.findColumn("자막명");
		int ex = mdSmi.findColumn("확장자");
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
						JOptionPane.showMessageDialog(null, "이름이 중복됩니다", "이름중복Error" , JOptionPane.WARNING_MESSAGE);
						break;
					}
					
					mdSmi.setValueAt(newName, i, afterName);	
					mdSmi.setValueAt(fileroot+"\\"+newName+"."+hwak, i, jun);	
				}else{
					JOptionPane.showMessageDialog(null, "지정위치에 파일이 없습니다.", "존재여부Error" , JOptionPane.WARNING_MESSAGE);
					break;
				}
			}
		}else{
			JOptionPane.showMessageDialog(null, "자막파일과 영상파일의 수가 다릅니다", "행갯수Error" , JOptionPane.WARNING_MESSAGE);
			
		}
	}
}

