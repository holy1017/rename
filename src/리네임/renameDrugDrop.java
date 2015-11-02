package ������;

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
                String fileRoot = file.getAbsolutePath();//����� ���ϰ��
                String fileName = file.getName(); //����� ���ϸ�
                System.out.print(fileRoot);
                int pos = fileName.lastIndexOf("."); //Ȯ���� ���п�
                int cut = fileRoot.lastIndexOf("\\");
                String t = "avi,wmv,mp4,mkv,mpg,mpeg,asf,tp,ts,mov,skm,k3g,flv";//����Ȯ����
                String lowerM = fileName.substring(pos + 1).toLowerCase();//Ȯ���� �ҹ��ں�ȭ
                String lowerV = fileName.substring(pos + 1).toLowerCase();//Ȯ���� �ҹ��ں�ȭ

                if (lowerV.equals("smi")) {//�ڸ����̺��߰�
                    int j = mdSmi.findColumn("��ü���"); //��ü��� ��ġã��
                    boolean find = false;
                    int count = mdSmi.getRowCount(); //���ȣ�� �����ֱ�
                    for (int i = 0; i < count; i++) {
                        String root = (String) mdSmi.getValueAt(i, j); //
                        if (root.equals(fileRoot)) { //�ߺ����ϰ˻�
                            JOptionPane.showMessageDialog(null, "���������� ���Խ��ϴ�", "�ߺ� Error", JOptionPane.WARNING_MESSAGE);
                            find = true;
                            break;
                        }

                    }
                    if (!find) {
                        String[] st = {(count + 1) + "", fileRoot.substring(0, cut), fileName.substring(0, pos), lowerV, fileRoot};
                        mdSmi.addRow(st);
                    }
                } else if (t.contains(lowerM)) {
                    //else if(fileName.substring(pos+1).equals("avi")){//�������̺��߰�
                    int j = mdVideo.findColumn("��ü���"); //��ü��� ��ġã��
                    boolean find = false;
                    int count = mdVideo.getRowCount();
                    for (int i = 0; i < count; i++) {
                        String root = (String) mdVideo.getValueAt(i, j); //
                        if (root.equals(fileRoot)) { //�ߺ����ϰ˻�
                            JOptionPane.showMessageDialog(null, "���������� ���Խ��ϴ�", "�ߺ� Error", JOptionPane.WARNING_MESSAGE);
                            find = true;
                            break;
                        }

                    }
                    if (!find) {
                        //{"��ȣ", "���", "���ϸ�", "����", "Ȯ����", "��ü���"}; // ���,Ȯ���ڴ� �Ⱥ��̰�

                        String[] st = {(count + 1) + "", fileRoot.substring(0, cut), fileName.substring(0, pos), fileName.substring(0, pos)/*����*/, lowerM, fileRoot};
                        for (String st1 : st) {
                            System.out.print(st1 + ",");
                        }
                        System.out.println(",");
                        mdVideo.addRow(st);
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "����� �ڸ����ϸ� �־��ּ���", "Ȯ���� Error", JOptionPane.WARNING_MESSAGE);
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
