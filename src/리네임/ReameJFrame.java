/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ������;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.*;

/**
 *
 * @author Administrator
 */
public class ReameJFrame extends javax.swing.JFrame implements ActionListener {

    DefaultTableModel mdVideo;
    DefaultTableModel mdEdit;
    DefaultTableModel mdSmi;
    UISetting uiset;
    //UISetting uiset1;
    String[] jTitleVideo = {"��ȣ", "���", "���ϸ�", "����", "Ȯ����", "��ü���"}; // ���,Ȯ���ڴ� �Ⱥ��̰�
    String[] jTitleSmi = {"��ȣ", "���", "�ڸ���", "Ȯ����", "��ü���"}; // ���,Ȯ���ڴ� �Ⱥ��̰�
    String[] jTitleEdit = {"����1", "����2", "����3", "����4"};
    String[] jComboBox = {"����", "�̸�", "�ѹ�", "����"};
    renameAdd rad;
    NumberSettingClass nsc;

    //Actionperformed af;//���ϸ� ����
    /**
     * Creates new form testJFrame
     */
    public ReameJFrame() {//���� ����� ������
        ReameJFrameMake();
    }

    public ReameJFrame(String t) {//�׽�Ʈ�� ���� ������
        ReameJFrameMake();

        if ("test".equals(t)) {
            //testData();//�׽�Ʈ�� ������ ����
            nsc.NumberSetting();
        }
    }

    private void ReameJFrameMake() {

        //�� ��ġ
        mdVideo = new DefaultTableModel(jTitleVideo, 0);
        mdEdit = new DefaultTableModel(jTitleEdit, 0);
        mdSmi = new DefaultTableModel(jTitleSmi, 0);

        initComponents();//������Ʈ�� ���� �� ���̾ƿ� �����κ�        
        setLocationRelativeTo(null);

        ������();//���̺� ���� �ٹ̱� ���� ����

        //Actionperformed���� �ѱ������ �迭�� ����
        JComboBox j�޺�[] = new JComboBox[4];
        j�޺�[0] = jComboBox1;
        j�޺�[1] = jComboBox2;
        j�޺�[2] = jComboBox3;
        j�޺�[3] = jComboBox4;
        JTextField j�ؽ�Ʈ[] = new JTextField[4];
        j�ؽ�Ʈ[0] = jTextField1;
        j�ؽ�Ʈ[1] = jTextField2;
        j�ؽ�Ʈ[2] = jTextField3;
        j�ؽ�Ʈ[3] = jTextField4;

        //�ϴ� ��ɹ�ư �гο� ���� ��� Ŭ����
        new Actionperformed(mdVideo, �ڸ����̺�, mdEdit, mdSmi, �������̺�, ��ȣ��, j�޺�, j�ؽ�Ʈ);

        //�巡�׾ص�� ����
        new renameDrugDrop(�������̺�, �ڸ����̺�, this);

        //�̸��ٲٱ�
        new reWordWindows(mdVideo, mdEdit, �̸��ٲٱ�);

        //�̸��߰�
        //rad=new renameAdd(mdVideo, mdSmi, mdEdit);
        nsc = new NumberSettingClass(�������̺�, �ڸ����̺�);
        //nsc.NumberSetting();

        new multi(mdVideo, mdSmi, �������̺�, �ڸ����̺�, ������, ����, �Ʒ���, �ǾƷ���, ���û���);
        
        //������ ���� �κ�. 
        uiset = new UISetting(this);
        //uiset1 = new UISetting(this);
        
    }

    private void ������() {
        �������̺�.getTableHeader().setReorderingAllowed(false);
        �������̺�.getColumn("��ȣ").setPreferredWidth(50);// �� ���� ����
        �������̺�.getColumn("��ȣ").setMinWidth(10);// �� ���� ����
        �������̺�.getColumn("��ȣ").setMaxWidth(50);// �� ���� ����
        // jt����.getColumn("���").setPreferredWidth(0);// �� ���� ����
        ���̺������(�������̺�, "���");
        // jt����.getColumn("���ϸ�").setPreferredWidth(150);// �� ���� ����
        �������̺�.getColumn("���ϸ�").setPreferredWidth(50);// �� ���� ����
        �������̺�.getColumn("����").setPreferredWidth(150);// �� ���� ����
        // jt����.getColumn("���").setWidth(10);
        �������̺�.getColumn("Ȯ����").setPreferredWidth(50);// �� ���� ����
        �������̺�.getColumn("Ȯ����").setMinWidth(50);
        �������̺�.getColumn("Ȯ����").setMaxWidth(50);
        // jt����.getColumn("Ȯ����").setWidth(10);
        ���̺������(�������̺�, "��ü���");
        

        �ڸ����̺�.getTableHeader().setReorderingAllowed(false);
        �ڸ����̺�.getColumn("��ȣ").setPreferredWidth(50);// �� ���� ����
        �ڸ����̺�.getColumn("��ȣ").setMinWidth(10);// �� ���� ����
        �ڸ����̺�.getColumn("��ȣ").setMaxWidth(50);// �� ���� ����
        // jt�ڸ�.getColumn("���").setPreferredWidth(0);// �� ���� ����
        ���̺������(�ڸ����̺�, "���");
        // jt�ڸ�.getColumn("���").setWidth(10);
        �ڸ����̺�.getColumn("�ڸ���").setPreferredWidth(50);// �� ���� ����
        �ڸ����̺�.getColumn("Ȯ����").setPreferredWidth(50);// �� ���� ����
        �ڸ����̺�.getColumn("Ȯ����").setMinWidth(50);
        �ڸ����̺�.getColumn("Ȯ����").setMaxWidth(50);
        // System.out.println(jTableSmi.getColumn("Ȯ����"));
        // jt�ڸ�.getColumn("Ȯ����").setWidth(10);
        ���̺������(�ڸ����̺�, "��ü���");
        
    }

    private void ���̺������(JTable tb, String ���̺��̸�) {
        tb.getColumn(���̺��̸�).setWidth(0);
        tb.getColumn(���̺��̸�).setMinWidth(0);
        tb.getColumn(���̺��̸�).setMaxWidth(0);
    }

    public void ��ȣ���̱�() {
        for (int i = 0; i < mdVideo.getRowCount() - 1; i++) {
            mdVideo.setValueAt(i + 1, i, 0);
        }
        for (int i = 0; i < mdVideo.getRowCount() - 1; i++) {
            mdSmi.setValueAt(i + 1, i, 0);
        }
    }

    private void testData() {// �׽�Ʈ�� ������
        int max = 30;
        for (int i = 0; i < max; i++) {
            String[] st = {i + "", "���" + i, "���ϸ�" + i, "����1" + i, "Ȯ����" + i, "Ȯ����" + i};
            mdVideo.addRow(st);
        }
        for (int i = 0; i < max; i++) {
            String[] st = {i + "", "���" + i, "���ϸ�" + i, "Ȯ����" + i, "Ȯ����" + i};
            mdSmi.addRow(st);
        }
        for (int i = 0; i < max; i++) {
            String[] st = {i + "", "��1��" + i, "��1�ϸ�" + i, "Ȯ1����" + i};
            mdEdit.addRow(st);
        }
    }

    private DefaultComboBoxModel dcb() {
        return new javax.swing.DefaultComboBoxModel(new String[]{"����", "�̸�", "�ѹ�", "����"});
    }

    public void actionPerformed(ActionEvent e) {
        nsc.NumberSetting();
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        GridBagConstraints gridBagConstraints;

        �������̺� = new JScrollPane();
        jTable1 = new JTable();
        buttonGroup1 = new ButtonGroup();
        �����г� = new JPanel();
        jScrollPane2 = new JScrollPane();
        �����г� = new JPanel();
        ����г� = new JPanel();
        �������� = new JButton();
        �ڸ����� = new JButton();
        jSeparator4 = new JSeparator();
        �����߰� = new JButton();
        jSeparator1 = new JSeparator();
        ���û��� = new JButton();
        ������ü���� = new JButton();
        �ڸ���ü���� = new JButton();
        jSeparator2 = new JSeparator();
        ������ = new JButton();
        ���� = new JButton();
        �Ʒ��� = new JButton();
        �ǾƷ��� = new JButton();
        jSeparator3 = new JSeparator();
        �̸��ٲٱ� = new JButton();
        jSeparator5 = new JSeparator();
        �����г� = new JPanel();
        ���̺��г� = new JPanel();
        jSplitPane1 = new JSplitPane();
        jScrollPane3 = new JScrollPane();
        �������̺� = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                //System.out.print(�������̺�.getRowSelectionAllowed());
                //this.setRowSelectionAllowed();
                return column==3 ? true : false;
            }
        };
        jScrollPane1 = new JScrollPane();
        �ڸ����̺� = new JTable(){
            @Override
            public boolean isCellEditable(int row, int column) {
                //return column==3 ? true : false;
                //System.out.print(this.getRowSelectionAllowed());
                //this.setRowSelectionAllowed();
                return false;
            }
        };
        �ϴܹ�ư�г� = new JPanel();
        ��ȣ�г� = new JPanel();
        jLabel2 = new JLabel();
        ��ȣ�� = new JLabel();
        �����г� = new JPanel();
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();
        jLabel6 = new JLabel();
        jLabel7 = new JLabel();
        jComboBox1 = new JComboBox();
        jComboBox2 = new JComboBox();
        jComboBox3 = new JComboBox();
        jComboBox4 = new JComboBox();
        jTextField1 = new JTextField();
        jTextField2 = new JTextField();
        jTextField3 = new JTextField();
        jTextField4 = new JTextField();
        jMenuBar1 = new JMenuBar();
        jMenu1 = new JMenu();
        jRadioButtonMenuItem1 = new JRadioButtonMenuItem();
        jRadioButtonMenuItem2 = new JRadioButtonMenuItem();
        jRadioButtonMenuItem3 = new JRadioButtonMenuItem();
        jRadioButtonMenuItem4 = new JRadioButtonMenuItem();
        jRadioButtonMenuItem5 = new JRadioButtonMenuItem();
        jRadioButtonMenuItem6 = new JRadioButtonMenuItem();
        jRadioButtonMenuItem7 = new JRadioButtonMenuItem();
        jRadioButtonMenuItem8 = new JRadioButtonMenuItem();
        jRadioButtonMenuItem9 = new JRadioButtonMenuItem();
        jRadioButtonMenuItem10 = new JRadioButtonMenuItem();
        jRadioButtonMenuItem11 = new JRadioButtonMenuItem();
        jRadioButtonMenuItem12 = new JRadioButtonMenuItem();
        jMenu3 = new JMenu();
        ������ = new JMenuItem();
        ȫ��ǥ = new JMenuItem();
        �赿�� = new JMenuItem();
        �ڵ��� = new JMenuItem();

        jTable1.setModel(mdEdit);
        �������̺�.setViewportView(jTable1);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        �����г�.setLayout(new BorderLayout());

        jScrollPane2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        �����г�.setLayout(new BorderLayout());

        ����г�.setLayout(new GridBagLayout());

        ��������.setBackground(new Color(255, 102, 102));
        ResourceBundle bundle = ResourceBundle.getBundle("������/Bundle"); // NOI18N
        ��������.setText(bundle.getString("ReameJFrame.��������.text")); // NOI18N
        ��������.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ��������(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        ����г�.add(��������, gridBagConstraints);

        �ڸ�����.setBackground(new Color(255, 102, 102));
        �ڸ�����.setText(bundle.getString("ReameJFrame.�ڸ�����.text")); // NOI18N
        �ڸ�����.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                �ڸ�����(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        ����г�.add(�ڸ�����, gridBagConstraints);

        jSeparator4.setToolTipText(bundle.getString("ReameJFrame.jSeparator4.toolTipText")); // NOI18N
        jSeparator4.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        jSeparator4.setMinimumSize(new Dimension(100, 100));
        jSeparator4.setName("test"); // NOI18N
        jSeparator4.setPreferredSize(new Dimension(10, 2));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(5, 0, 5, 0);
        ����г�.add(jSeparator4, gridBagConstraints);

        �����߰�.setBackground(new Color(102, 102, 255));
        �����߰�.setText(bundle.getString("ReameJFrame.�����߰�.text")); // NOI18N
        �����߰�.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                �����߰�(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        ����г�.add(�����߰�, gridBagConstraints);

        jSeparator1.setToolTipText(bundle.getString("ReameJFrame.test.toolTipText")); // NOI18N
        jSeparator1.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        jSeparator1.setMinimumSize(new Dimension(100, 100));
        jSeparator1.setName("test"); // NOI18N
        jSeparator1.setPreferredSize(new Dimension(10, 2));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(5, 0, 5, 0);
        ����г�.add(jSeparator1, gridBagConstraints);

        ���û���.setBackground(new Color(255, 153, 255));
        ���û���.setText(bundle.getString("ReameJFrame.���û���.text")); // NOI18N
        ���û���.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent evt) {
                ��ȣ�ο�(evt);
            }
        });
        ���û���.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ��ȣ�Է�(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        ����г�.add(���û���, gridBagConstraints);

        ������ü����.setBackground(new Color(255, 153, 255));
        ������ü����.setText(bundle.getString("ReameJFrame.������ü����.text")); // NOI18N
        ������ü����.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ������ü����(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        ����г�.add(������ü����, gridBagConstraints);

        �ڸ���ü����.setBackground(new Color(255, 153, 255));
        �ڸ���ü����.setText(bundle.getString("ReameJFrame.�ڸ���ü����.text")); // NOI18N
        �ڸ���ü����.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                �ڸ���ü����(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        ����г�.add(�ڸ���ü����, gridBagConstraints);

        jSeparator2.setToolTipText(bundle.getString("ReameJFrame.jSeparator2.toolTipText")); // NOI18N
        jSeparator2.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        jSeparator2.setMinimumSize(new Dimension(100, 100));
        jSeparator2.setName("test"); // NOI18N
        jSeparator2.setPreferredSize(new Dimension(10, 2));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(5, 0, 5, 0);
        ����г�.add(jSeparator2, gridBagConstraints);

        ������.setBackground(new Color(255, 255, 102));
        ������.setText(bundle.getString("ReameJFrame.������.text")); // NOI18N
        ������.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent evt) {
                ��ȣ�ο�(evt);
            }
        });
        ������.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ��ȣ�Է�(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        ����г�.add(������, gridBagConstraints);

        ����.setBackground(new Color(255, 255, 102));
        ����.setText(bundle.getString("ReameJFrame.����.text")); // NOI18N
        ����.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent evt) {
                ��ȣ�ο�(evt);
            }
        });
        ����.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ��ȣ�Է�(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        ����г�.add(����, gridBagConstraints);

        �Ʒ���.setBackground(new Color(255, 255, 102));
        �Ʒ���.setText(bundle.getString("ReameJFrame.�Ʒ���.text")); // NOI18N
        �Ʒ���.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent evt) {
                ��ȣ�ο�(evt);
            }
        });
        �Ʒ���.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ��ȣ�Է�(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        ����г�.add(�Ʒ���, gridBagConstraints);

        �ǾƷ���.setBackground(new Color(255, 255, 102));
        �ǾƷ���.setText(bundle.getString("ReameJFrame.�ǾƷ���.text")); // NOI18N
        �ǾƷ���.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent evt) {
                ��ȣ�ο�(evt);
            }
        });
        �ǾƷ���.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ��ȣ�Է�(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        ����г�.add(�ǾƷ���, gridBagConstraints);

        jSeparator3.setToolTipText(bundle.getString("ReameJFrame.jSeparator3.toolTipText")); // NOI18N
        jSeparator3.setMinimumSize(new Dimension(100, 100));
        jSeparator3.setName("test"); // NOI18N
        jSeparator3.setPreferredSize(new Dimension(10, 2));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(5, 0, 5, 0);
        ����г�.add(jSeparator3, gridBagConstraints);

        �̸��ٲٱ�.setBackground(new Color(51, 255, 51));
        �̸��ٲٱ�.setText(bundle.getString("ReameJFrame.�̸��ٲٱ�.text")); // NOI18N
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        ����г�.add(�̸��ٲٱ�, gridBagConstraints);

        jSeparator5.setToolTipText(bundle.getString("ReameJFrame.jSeparator5.toolTipText")); // NOI18N
        jSeparator5.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        jSeparator5.setMinimumSize(new Dimension(100, 100));
        jSeparator5.setName("test"); // NOI18N
        jSeparator5.setPreferredSize(new Dimension(10, 2));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(5, 0, 5, 0);
        ����г�.add(jSeparator5, gridBagConstraints);

        �����г�.add(����г�, BorderLayout.NORTH);

        jScrollPane2.setViewportView(�����г�);

        �����г�.add(jScrollPane2, BorderLayout.EAST);

        �����г�.setLayout(new BorderLayout());

        GridBagLayout ���̺��г�Layout = new GridBagLayout();
        ���̺��г�Layout.columnWidths = new int[] {0};
        ���̺��г�Layout.rowHeights = new int[] {0};
        ���̺��г�Layout.columnWeights = new double[] {0.0};
        ���̺��г�Layout.rowWeights = new double[] {0.0};
        ���̺��г�.setLayout(���̺��г�Layout);

        jSplitPane1.setResizeWeight(0.7);
        jSplitPane1.setToolTipText(bundle.getString("ReameJFrame.jSplitPane1.toolTipText")); // NOI18N

        jScrollPane3.setPreferredSize(new Dimension(600, 402));

        �������̺�.setModel(mdVideo);
        �������̺�.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        �������̺�.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                �������̺�KeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(�������̺�);

        jSplitPane1.setLeftComponent(jScrollPane3);

        jScrollPane1.setPreferredSize(new Dimension(300, 402));

        �ڸ����̺�.setModel(mdSmi);
        �ڸ����̺�.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                �ڸ����̺�KeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(�ڸ����̺�);

        jSplitPane1.setRightComponent(jScrollPane1);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        ���̺��г�.add(jSplitPane1, gridBagConstraints);

        �����г�.add(���̺��г�, BorderLayout.CENTER);

        �ϴܹ�ư�г�.setLayout(new GridBagLayout());

        ��ȣ�г�.setPreferredSize(new Dimension(50, 63));
        ��ȣ�г�.setLayout(new GridBagLayout());

        jLabel2.setText(bundle.getString("ReameJFrame.jLabel2.text")); // NOI18N
        jLabel2.setToolTipText(bundle.getString("ReameJFrame.jLabel2.toolTipText")); // NOI18N
        jLabel2.setHorizontalTextPosition(SwingConstants.CENTER);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.PAGE_END;
        ��ȣ�г�.add(jLabel2, gridBagConstraints);

        ��ȣ��.setText(bundle.getString("ReameJFrame.��ȣ��.text")); // NOI18N
        ��ȣ��.setHorizontalTextPosition(SwingConstants.CENTER);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.PAGE_END;
        ��ȣ�г�.add(��ȣ��, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 10;
        �ϴܹ�ư�г�.add(��ȣ�г�, gridBagConstraints);
        ��ȣ�г�.getAccessibleContext().setAccessibleDescription(bundle.getString("ReameJFrame.��ȣ�г�.AccessibleContext.accessibleDescription")); // NOI18N

        �����г�.setLayout(new GridLayout(3, 4));

        jLabel4.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel4.setText(bundle.getString("ReameJFrame.jLabel4.text")); // NOI18N
        �����г�.add(jLabel4);

        jLabel5.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel5.setText(bundle.getString("ReameJFrame.jLabel5.text")); // NOI18N
        �����г�.add(jLabel5);

        jLabel6.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel6.setText(bundle.getString("ReameJFrame.jLabel6.text")); // NOI18N
        �����г�.add(jLabel6);

        jLabel7.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel7.setText(bundle.getString("ReameJFrame.jLabel7.text")); // NOI18N
        �����г�.add(jLabel7);

        jComboBox1.setModel(dcb());
        �����г�.add(jComboBox1);

        jComboBox2.setModel(dcb());
        �����г�.add(jComboBox2);

        jComboBox3.setModel(dcb());
        �����г�.add(jComboBox3);

        jComboBox4.setModel(dcb());
        �����г�.add(jComboBox4);
        �����г�.add(jTextField1);
        �����г�.add(jTextField2);
        �����г�.add(jTextField3);
        �����г�.add(jTextField4);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        �ϴܹ�ư�г�.add(�����г�, gridBagConstraints);

        �����г�.add(�ϴܹ�ư�г�, BorderLayout.PAGE_END);

        �����г�.add(�����г�, BorderLayout.CENTER);

        getContentPane().add(�����г�, BorderLayout.CENTER);

        jMenu1.setText(bundle.getString("ReameJFrame.jMenu1.text")); // NOI18N

        buttonGroup1.add(jRadioButtonMenuItem1);
        jRadioButtonMenuItem1.setText(bundle.getString("ReameJFrame.jRadioButtonMenuItem1.text")); // NOI18N
        jRadioButtonMenuItem1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                UI����(evt);
            }
        });
        jMenu1.add(jRadioButtonMenuItem1);

        buttonGroup1.add(jRadioButtonMenuItem2);
        jRadioButtonMenuItem2.setText(bundle.getString("ReameJFrame.jRadioButtonMenuItem2.text")); // NOI18N
        jRadioButtonMenuItem2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                UI����(evt);
            }
        });
        jMenu1.add(jRadioButtonMenuItem2);

        buttonGroup1.add(jRadioButtonMenuItem3);
        jRadioButtonMenuItem3.setText(bundle.getString("ReameJFrame.jRadioButtonMenuItem3.text")); // NOI18N
        jRadioButtonMenuItem3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                UI����(evt);
            }
        });
        jMenu1.add(jRadioButtonMenuItem3);

        buttonGroup1.add(jRadioButtonMenuItem4);
        jRadioButtonMenuItem4.setText(bundle.getString("ReameJFrame.jRadioButtonMenuItem4.text")); // NOI18N
        jRadioButtonMenuItem4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                UI����(evt);
            }
        });
        jMenu1.add(jRadioButtonMenuItem4);

        buttonGroup1.add(jRadioButtonMenuItem5);
        jRadioButtonMenuItem5.setText(bundle.getString("ReameJFrame.jRadioButtonMenuItem5.text")); // NOI18N
        jRadioButtonMenuItem5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                UI����(evt);
            }
        });
        jMenu1.add(jRadioButtonMenuItem5);

        buttonGroup1.add(jRadioButtonMenuItem6);
        jRadioButtonMenuItem6.setSelected(true);
        jRadioButtonMenuItem6.setText(bundle.getString("ReameJFrame.jRadioButtonMenuItem6.text")); // NOI18N
        jRadioButtonMenuItem6.setToolTipText(bundle.getString("ReameJFrame.jRadioButtonMenuItem6.toolTipText")); // NOI18N
        jRadioButtonMenuItem6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                UI����(evt);
            }
        });
        jMenu1.add(jRadioButtonMenuItem6);

        buttonGroup1.add(jRadioButtonMenuItem7);
        jRadioButtonMenuItem7.setText(bundle.getString("ReameJFrame.jRadioButtonMenuItem7.text")); // NOI18N
        jRadioButtonMenuItem7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                UI����(evt);
            }
        });
        jMenu1.add(jRadioButtonMenuItem7);

        buttonGroup1.add(jRadioButtonMenuItem8);
        jRadioButtonMenuItem8.setText(bundle.getString("ReameJFrame.jRadioButtonMenuItem8.text")); // NOI18N
        jRadioButtonMenuItem8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                UI����(evt);
            }
        });
        jMenu1.add(jRadioButtonMenuItem8);

        buttonGroup1.add(jRadioButtonMenuItem9);
        jRadioButtonMenuItem9.setText(bundle.getString("ReameJFrame.jRadioButtonMenuItem9.text")); // NOI18N
        jRadioButtonMenuItem9.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                UI����(evt);
            }
        });
        jMenu1.add(jRadioButtonMenuItem9);

        buttonGroup1.add(jRadioButtonMenuItem10);
        jRadioButtonMenuItem10.setText(bundle.getString("ReameJFrame.jRadioButtonMenuItem10.text")); // NOI18N
        jRadioButtonMenuItem10.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                UI����(evt);
            }
        });
        jMenu1.add(jRadioButtonMenuItem10);

        buttonGroup1.add(jRadioButtonMenuItem11);
        jRadioButtonMenuItem11.setText(bundle.getString("ReameJFrame.jRadioButtonMenuItem11.text")); // NOI18N
        jRadioButtonMenuItem11.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                UI����(evt);
            }
        });
        jMenu1.add(jRadioButtonMenuItem11);

        buttonGroup1.add(jRadioButtonMenuItem12);
        jRadioButtonMenuItem12.setText(bundle.getString("ReameJFrame.jRadioButtonMenuItem12.text")); // NOI18N
        jRadioButtonMenuItem12.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                UI����(evt);
            }
        });
        jMenu1.add(jRadioButtonMenuItem12);

        jMenuBar1.add(jMenu1);

        jMenu3.setText(bundle.getString("ReameJFrame.jMenu3.text")); // NOI18N

        ������.setText(bundle.getString("ReameJFrame.������.text")); // NOI18N
        ������.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ����(evt);
            }
        });
        jMenu3.add(������);

        ȫ��ǥ.setText(bundle.getString("ReameJFrame.ȫ��ǥ.text")); // NOI18N
        ȫ��ǥ.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ����(evt);
            }
        });
        jMenu3.add(ȫ��ǥ);

        �赿��.setText(bundle.getString("ReameJFrame.�赿��.text")); // NOI18N
        �赿��.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ����(evt);
            }
        });
        jMenu3.add(�赿��);

        �ڵ���.setText(bundle.getString("ReameJFrame.�ڵ���.text")); // NOI18N
        �ڵ���.setToolTipText(bundle.getString("ReameJFrame.�ڵ���.toolTipText")); // NOI18N
        �ڵ���.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ����(evt);
            }
        });
        jMenu3.add(�ڵ���);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ������ü����(ActionEvent evt) {//GEN-FIRST:event_������ü����
        mdVideo.setNumRows(0);
        mdEdit.setNumRows(0);

    }//GEN-LAST:event_������ü����

    private void �����߰�(ActionEvent evt) {//GEN-FIRST:event_�����߰�

        try {
            new renameAdd(�����߰�, mdVideo, mdSmi, mdEdit);
            //rad.Add(jTitleSmi);
        } catch (IOException ex) {
            Logger.getLogger(ReameJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_�����߰�

    private void �̸��ٱ���ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_�̸��ٱ���ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_�̸��ٱ���ActionPerformed

    private void �ڸ�����(ActionEvent evt) {//GEN-FIRST:event_�ڸ�����
        // TODO add your handling code here:
        try {
            new renameApplySmi(mdVideo, mdSmi);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }//GEN-LAST:event_�ڸ�����

    private void ��������(ActionEvent evt) {//GEN-FIRST:event_��������
        // TODO add your handling code here:
        try {
            new renameApplyVideo(mdVideo, mdSmi);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }//GEN-LAST:event_��������

    private void �ڸ���ü����(ActionEvent evt) {//GEN-FIRST:event_�ڸ���ü����
        mdSmi.setNumRows(0);
    }//GEN-LAST:event_�ڸ���ü����

    private void UI����(ActionEvent evt) {//GEN-FIRST:event_UI����
        // TODO add your handling code here:
        //System.out.println("bt:" + ((JRadioButtonMenuItem) evt.getSource()).getText());
        uiset.UISelet(((JRadioButtonMenuItem) evt.getSource()).getText());
        //uiset1.UISelet(((JRadioButtonMenuItem) evt.getSource()).getText());
    }//GEN-LAST:event_UI����

    private void �������̺�KeyReleased(KeyEvent evt) {//GEN-FIRST:event_�������̺�KeyReleased
        // TODO add your handling code here:
        nsc.NumberSetting();
    }//GEN-LAST:event_�������̺�KeyReleased

    private void �ڸ����̺�KeyReleased(KeyEvent evt) {//GEN-FIRST:event_�ڸ����̺�KeyReleased
        // TODO add your handling code here:
        nsc.NumberSetting();
    }//GEN-LAST:event_�ڸ����̺�KeyReleased

    private void ��ȣ�ο�(MouseEvent evt) {//GEN-FIRST:event_��ȣ�ο�
        // TODO add your handling code here:
        nsc.NumberSetting();
    }//GEN-LAST:event_��ȣ�ο�

    private void ��ȣ�Է�(ActionEvent evt) {//GEN-FIRST:event_��ȣ�Է�
        // TODO add your handling code here:
        nsc.NumberSetting();
    }//GEN-LAST:event_��ȣ�Է�

    private void ����(ActionEvent evt) {//GEN-FIRST:event_����
        // TODO add your handling code here:
        if (evt.getSource()==������) {			
		JOptionPane.showMessageDialog(null, "����¥�� ���� �̷��� �� �޼ҵ尡 ���� ������.", "������", JOptionPane.ERROR_MESSAGE);
		}        
        if (evt.getSource()==ȫ��ǥ) {			
        	JOptionPane.showMessageDialog(null, "�̹� ������Ʈ�� �ϸ鼭 ���� �� �ߴ� ������� ������ �� �ִ� ���� ��ȸ�� �Ǿ����ϴ�."
                        + "\n���� ���� �˾ƺ� �� �ִ� ���α׷��� �ƴ� �ٸ� ����� �����ϸ鼭 ������ �κ��� ������ �� �ֵ��� �ϸ�,"
                        + "\n�ϳ��� ���α׷��� ��� ���� ������� ��Ʈ�� ������ ���� �� �ִ����� �����ϰ� �Ǿ����ϴ�. "
                        + "\n�׸��� ������ �߿伺�� ������ ���� �д��� �������� ȿ�� ���� ü���� �� �־��� ������ �ð��̾����ϴ�.", "ȫ��ǥ", JOptionPane.INFORMATION_MESSAGE);
		}       
        if (evt.getSource()==�赿��) {			
        	JOptionPane.showMessageDialog(null, "������ ���� ���������� �׷��� ������ �س´ٴ� ������ �ڽŰ��� ������ ������ϴ�\n" +
"���ڶ� ������ �������� �����鿡�� ��������", "�赿��", JOptionPane.WARNING_MESSAGE);
		}       
        if (evt.getSource()==�ڵ���) {			
        	JOptionPane.showMessageDialog(null, "��...���� ������..? \nä���̳� ������;;", "�ڵ���", JOptionPane.QUESTION_MESSAGE);
		}
    }//GEN-LAST:event_����

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        //new ReameJFrame("test").setVisible(true);

        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //new UISetting(2);
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ReameJFrame("test").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    ButtonGroup buttonGroup1;
    public JComboBox jComboBox1;
    public JComboBox jComboBox2;
    public JComboBox jComboBox3;
    public JComboBox jComboBox4;
    JLabel jLabel2;
    JLabel jLabel4;
    JLabel jLabel5;
    JLabel jLabel6;
    JLabel jLabel7;
    JMenu jMenu1;
    JMenu jMenu3;
    JMenuBar jMenuBar1;
    JRadioButtonMenuItem jRadioButtonMenuItem1;
    JRadioButtonMenuItem jRadioButtonMenuItem10;
    JRadioButtonMenuItem jRadioButtonMenuItem11;
    JRadioButtonMenuItem jRadioButtonMenuItem12;
    JRadioButtonMenuItem jRadioButtonMenuItem2;
    JRadioButtonMenuItem jRadioButtonMenuItem3;
    JRadioButtonMenuItem jRadioButtonMenuItem4;
    JRadioButtonMenuItem jRadioButtonMenuItem5;
    JRadioButtonMenuItem jRadioButtonMenuItem6;
    JRadioButtonMenuItem jRadioButtonMenuItem7;
    JRadioButtonMenuItem jRadioButtonMenuItem8;
    JRadioButtonMenuItem jRadioButtonMenuItem9;
    JScrollPane jScrollPane1;
    JScrollPane jScrollPane2;
    JScrollPane jScrollPane3;
    JSeparator jSeparator1;
    JSeparator jSeparator2;
    JSeparator jSeparator3;
    JSeparator jSeparator4;
    JSeparator jSeparator5;
    JSplitPane jSplitPane1;
    JTable jTable1;
    public JTextField jTextField1;
    public JTextField jTextField2;
    public JTextField jTextField3;
    public JTextField jTextField4;
    JPanel ����г�;
    JMenuItem �赿��;
    JMenuItem ������;
    JButton �ǾƷ���;
    JButton ������;
    JPanel �����г�;
    JMenuItem �ڵ���;
    JLabel ��ȣ��;
    JPanel ��ȣ�г�;
    JPanel �����г�;
    JButton ���û���;
    JScrollPane �������̺�;
    JPanel �����г�;
    JButton �Ʒ���;
    JButton ��������;
    JButton ������ü����;
    JTable �������̺�;
    JPanel �����г�;
    JButton ����;
    JButton �̸��ٲٱ�;
    JButton �ڸ�����;
    JButton �ڸ���ü����;
    JTable �ڸ����̺�;
    JPanel ���̺��г�;
    JButton �����߰�;
    JPanel �ϴܹ�ư�г�;
    JMenuItem ȫ��ǥ;
    // End of variables declaration//GEN-END:variables

}
