package 리네임;

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
    	FileDialog fd = new FileDialog(this, "파일 추가", FileDialog.LOAD);//윈도우창 열기
    	fd.setMultipleMode(true);
    	fd.setVisible(true); 
    	
    	File[] F = fd.getFiles(); 
    	
    	for (int i = 0; i < F.length; i++) {
			File file = F[i];
			String fileRoot = file.getCanonicalPath();//열어온 파일경로
	    	String fileName = file.getName(); //열어온 파일명
	    	int pos = fileName.lastIndexOf("."); //확장자 구분용
	    	int cut = fileRoot.lastIndexOf("\\");
	    	String t = "avi,wmv,mp4,mkv,mpg,mpeg,asf,tp,ts,mov,skm,k3g,flv";//영상확장자
	    	String lowerM = fileName.substring(pos+1).toLowerCase();//확장자 소문자변화
	    	String lowerV = fileName.substring(pos+1).toLowerCase();//확장자 소문자변화
	    	
	    	if (lowerV.equals("smi")){//자막테이블추가
	    		int j = mdSmi.findColumn("전체경로"); //전체경로 위치찾기
	    		boolean find = false ;
	    		int count = mdSmi.getRowCount(); //행번호로 순서넣기
	    		for (int z = 0; z < count; z++) {
	    			String root = (String) mdSmi.getValueAt(z, j); //
	    			if(root.equals(fileRoot)){ //중복파일검사
	    				JOptionPane.showMessageDialog(null, "같은파일이 들어왔습니다", "중복 Error", JOptionPane.WARNING_MESSAGE);
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
	    	//else if(fileName.substring(pos+1).equals("avi")){//영상테이블추가
	    		int j = mdVideo.findColumn("전체경로"); //전체경로 위치찾기
	    		boolean find = false ;
	    		int count = mdVideo.getRowCount();
	    		for (int z = 0; z < count; z++) {
	    			String root = (String) mdVideo.getValueAt(z, j); //
	    			if(root.equals(fileRoot)){ //중복파일검사
	    				JOptionPane.showMessageDialog(null, "같은파일이 들어왔습니다", "중복 Error", JOptionPane.WARNING_MESSAGE);
	    				find = true;
	    				break;
	    			}
	 
	    		}
	    		if(!find){
	    		String[] st = { (count+1)+"" ,fileRoot.substring(0, cut) , fileName.substring(0,pos), fileName.substring(0,pos)/*수정*/ , lowerM, fileRoot };
	    		String[] ed = { "","","",""};
	    		mdEdit.addRow(ed);
	    		mdVideo.addRow(st);
	    		
	    		}
	    		
	    		
	    	}
	    	else{
	    		JOptionPane.showMessageDialog(null, "영상과 자막파일만 넣어주세요", "확장자 Error", JOptionPane.WARNING_MESSAGE);
	    	}
		}
    	
    	
    	
    	
    	
    	
    }
} //end of FileDialogFrame class 