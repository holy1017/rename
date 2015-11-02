package 리네임;

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
		int jun = mdVideo.findColumn("전체경로");
		int su	= mdVideo.findColumn("수정");
		int root = mdVideo.findColumn("경로");
		int afterName = mdVideo.findColumn("파일명");
		int ex = mdVideo.findColumn("확장자");
		
		
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
						JOptionPane.showMessageDialog(null, "이름이 중복됩니다", "이름중복Error" , JOptionPane.WARNING_MESSAGE);
						break;
					}
					mdVideo.setValueAt(newName, i, afterName);	
					mdVideo.setValueAt(fileroot+"\\"+newName+"."+hwak, i, jun);
				}else{
					JOptionPane.showMessageDialog(null, "지정위치에 파일이 없습니다.", "존재여부Error" , JOptionPane.WARNING_MESSAGE);
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
						JOptionPane.showMessageDialog(null, "이름이 중복됩니다", "이름중복Error" , JOptionPane.WARNING_MESSAGE);
						break;
					}
					
					mdVideo.setValueAt(newName, i, afterName);	
					mdVideo.setValueAt(fileroot+"\\"+newName+"."+hwak, i, jun);
				}else{
					JOptionPane.showMessageDialog(null, "지정위치에 파일이 없습니다.", "존재여부Error" , JOptionPane.WARNING_MESSAGE);
					break;
				}
			}
		}
		
		else{
			JOptionPane.showMessageDialog(null, "자막파일과 영상파일의 수가 다릅니다", "행갯수Error" , JOptionPane.WARNING_MESSAGE);
		}
		
	}
}

