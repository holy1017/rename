package ������;

import java.awt.event.ActionListener;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class NumberSettingClass {

    DefaultTableModel tmVideo;
    DefaultTableModel tmSmi;
    JTable �������̺�;
    JTable �ڸ����̺�;

    public NumberSettingClass(JTable jtVideo, JTable jtSmi) {
        // TODO Auto-generated constructor stub
        �������̺� = jtVideo;
        �ڸ����̺� = jtSmi;
        tmVideo = (DefaultTableModel) jtVideo.getModel();
        tmSmi = (DefaultTableModel) jtSmi.getModel();
    }

    public NumberSettingClass(DefaultTableModel jmVideo, DefaultTableModel jmSmi) {
        // TODO Auto-generated constructor stub
        tmVideo = jmVideo;
        tmSmi = jmSmi;
    }

    public NumberSettingClass(TableModel jmVideo, TableModel jmSmi) {
        // TODO Auto-generated constructor stub
        tmVideo = (DefaultTableModel) jmVideo;
        tmSmi = (DefaultTableModel) jmSmi;
    }

    public void NumberSetting() {
        System.out.println(�������̺�.getRowSelectionAllowed()+":");
        System.out.println(�ڸ����̺�.getRowSelectionAllowed()+":");
        for (int i = 0; i < tmVideo.getRowCount(); i++) {
            tmVideo.setValueAt(i + 1, i, 0);
        }
        for (int i = 0; i < tmSmi.getRowCount(); i++) {
            tmSmi.setValueAt(i + 1, i, 0);
        }
//        �������̺�.setRowSelectionAllowed(true);
//        �ڸ����̺�.setRowSelectionAllowed(true);
    }
}
