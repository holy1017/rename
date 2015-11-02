package 리네임;

import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.*;
import java.io.File;
import java.util.List;

import javax.swing.*;
import javax.swing.table.*;

public class renameDrugDrop extends JFrame implements DropTargetListener {

    private DefaultTableModel mdSmi;
    private DefaultTableModel mdVideo;

    renameDrugDrop(JTable jTableVideo, JTable jTableSmi, JFrame JF) {
        new DropTarget(jTableVideo, this);
        new DropTarget(jTableSmi, this);
        this.mdSmi = (DefaultTableModel) jTableSmi.getModel();
        this.mdVideo = (DefaultTableModel) jTableVideo.getModel();
        JF.setDropTarget(new DropTarget(JF, this));
    }

    @Override
    public void dragEnter(DropTargetDragEvent dtde) {
		// TODO Auto-generated method stub

    }

    @Override
    public void dragOver(DropTargetDragEvent dtde) {
		// TODO Auto-generated method stub

    }

    @Override
    public void dropActionChanged(DropTargetDragEvent dtde) {
		// TODO Auto-generated method stub

    }

    @Override
    public void dragExit(DropTargetEvent dte) {
		// TODO Auto-generated method stub

    }

    @Override
    public void drop(DropTargetDropEvent e) {
        try {
            e.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
            List<File> droppedFiles = (List<File>) e.getTransferable()
                    .getTransferData(DataFlavor.javaFileListFlavor);

            for (File file : droppedFiles) {
                String fileRoot = file.getAbsolutePath();//열어온 파일경로
                String fileName = file.getName(); //열어온 파일명
                System.out.print(fileRoot);
                int pos = fileName.lastIndexOf("."); //확장자 구분용
                int cut = fileRoot.lastIndexOf("\\");
                String t = "avi,wmv,mp4,mkv,mpg,mpeg,asf,tp,ts,mov,skm,k3g,flv";//영상확장자
                String lowerM = fileName.substring(pos + 1).toLowerCase();//확장자 소문자변화
                String lowerV = fileName.substring(pos + 1).toLowerCase();//확장자 소문자변화

                if (lowerV.equals("smi")) {//자막테이블추가
                    int j = mdSmi.findColumn("전체경로"); //전체경로 위치찾기
                    boolean find = false;
                    int count = mdSmi.getRowCount(); //행번호로 순서넣기
                    for (int i = 0; i < count; i++) {
                        String root = (String) mdSmi.getValueAt(i, j); //
                        if (root.equals(fileRoot)) { //중복파일검사
                            JOptionPane.showMessageDialog(null, "같은파일이 들어왔습니다", "중복 Error", JOptionPane.WARNING_MESSAGE);
                            find = true;
                            break;
                        }

                    }
                    if (!find) {
                        String[] st = {(count + 1) + "", fileRoot.substring(0, cut), fileName.substring(0, pos), lowerV, fileRoot};
                        mdSmi.addRow(st);
                    }
                } else if (t.contains(lowerM)) {
                    //else if(fileName.substring(pos+1).equals("avi")){//영상테이블추가
                    int j = mdVideo.findColumn("전체경로"); //전체경로 위치찾기
                    boolean find = false;
                    int count = mdVideo.getRowCount();
                    for (int i = 0; i < count; i++) {
                        String root = (String) mdVideo.getValueAt(i, j); //
                        if (root.equals(fileRoot)) { //중복파일검사
                            JOptionPane.showMessageDialog(null, "같은파일이 들어왔습니다", "중복 Error", JOptionPane.WARNING_MESSAGE);
                            find = true;
                            break;
                        }

                    }
                    if (!find) {
                        //{"번호", "경로", "파일명", "수정", "확장자", "전체경로"}; // 경로,확장자는 안보이게

                        String[] st = {(count + 1) + "", fileRoot.substring(0, cut), fileName.substring(0, pos), fileName.substring(0, pos)/*수정*/, lowerM, fileRoot};
                        for (String st1 : st) {
                            System.out.print(st1 + ",");
                        }
                        System.out.println(",");
                        mdVideo.addRow(st);
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "영상과 자막파일만 넣어주세요", "확장자 Error", JOptionPane.WARNING_MESSAGE);
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
