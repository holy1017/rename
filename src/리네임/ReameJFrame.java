/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package 리네임;

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
    String[] jTitleVideo = {"번호", "경로", "파일명", "수정", "확장자", "전체경로"}; // 경로,확장자는 안보이게
    String[] jTitleSmi = {"번호", "경로", "자막명", "확장자", "전체경로"}; // 경로,확장자는 안보이게
    String[] jTitleEdit = {"수정1", "수정2", "수정3", "수정4"};
    String[] jComboBox = {"선택", "이름", "넘버", "부제"};
    renameAdd rad;
    NumberSettingClass nsc;

    //Actionperformed af;//파일명 수정
    /**
     * Creates new form testJFrame
     */
    public ReameJFrame() {//실제 사용할 생성자
        ReameJFrameMake();
    }

    public ReameJFrame(String t) {//테스트를 위한 생성자
        ReameJFrameMake();

        if ("test".equals(t)) {
            //testData();//테스트용 데이터 삽입
            nsc.NumberSetting();
        }
    }

    private void ReameJFrameMake() {

        //모델 배치
        mdVideo = new DefaultTableModel(jTitleVideo, 0);
        mdEdit = new DefaultTableModel(jTitleEdit, 0);
        mdSmi = new DefaultTableModel(jTitleSmi, 0);

        initComponents();//컴포넌트들 생성 및 레이아웃 구현부분        
        setLocationRelativeTo(null);

        열수정();//테이블 열을 꾸미기 위한 설정

        //Actionperformed으로 넘기기위해 배열에 담음
        JComboBox j콤보[] = new JComboBox[4];
        j콤보[0] = jComboBox1;
        j콤보[1] = jComboBox2;
        j콤보[2] = jComboBox3;
        j콤보[3] = jComboBox4;
        JTextField j텍스트[] = new JTextField[4];
        j텍스트[0] = jTextField1;
        j텍스트[1] = jTextField2;
        j텍스트[2] = jTextField3;
        j텍스트[3] = jTextField4;

        //하단 기능버튼 패널에 관한 기능 클래스
        new Actionperformed(mdVideo, 자막테이블, mdEdit, mdSmi, 영상테이블, 번호라벨, j콤보, j텍스트);

        //드래그앤드랍 구현
        new renameDrugDrop(영상테이블, 자막테이블, this);

        //이름바꾸기
        new reWordWindows(mdVideo, mdEdit, 이름바꾸기);

        //이름추가
        //rad=new renameAdd(mdVideo, mdSmi, mdEdit);
        nsc = new NumberSettingClass(영상테이블, 자막테이블);
        //nsc.NumberSetting();

        new multi(mdVideo, mdSmi, 영상테이블, 자막테이블, 맨위로, 위로, 아래로, 맨아래로, 선택삭제);
        
        //디자인 구현 부분. 
        uiset = new UISetting(this);
        //uiset1 = new UISetting(this);
        
    }

    private void 열수정() {
        영상테이블.getTableHeader().setReorderingAllowed(false);
        영상테이블.getColumn("번호").setPreferredWidth(50);// 열 넓이 설정
        영상테이블.getColumn("번호").setMinWidth(10);// 열 넓이 설정
        영상테이블.getColumn("번호").setMaxWidth(50);// 열 넓이 설정
        // jt영상.getColumn("경로").setPreferredWidth(0);// 열 넓이 설정
        테이블열숨기기(영상테이블, "경로");
        // jt영상.getColumn("파일명").setPreferredWidth(150);// 열 넓이 설정
        영상테이블.getColumn("파일명").setPreferredWidth(50);// 열 넓이 설정
        영상테이블.getColumn("수정").setPreferredWidth(150);// 열 넓이 설정
        // jt영상.getColumn("경로").setWidth(10);
        영상테이블.getColumn("확장자").setPreferredWidth(50);// 열 넓이 설정
        영상테이블.getColumn("확장자").setMinWidth(50);
        영상테이블.getColumn("확장자").setMaxWidth(50);
        // jt영상.getColumn("확장자").setWidth(10);
        테이블열숨기기(영상테이블, "전체경로");
        

        자막테이블.getTableHeader().setReorderingAllowed(false);
        자막테이블.getColumn("번호").setPreferredWidth(50);// 열 넓이 설정
        자막테이블.getColumn("번호").setMinWidth(10);// 열 넓이 설정
        자막테이블.getColumn("번호").setMaxWidth(50);// 열 넓이 설정
        // jt자막.getColumn("경로").setPreferredWidth(0);// 열 넓이 설정
        테이블열숨기기(자막테이블, "경로");
        // jt자막.getColumn("경로").setWidth(10);
        자막테이블.getColumn("자막명").setPreferredWidth(50);// 열 넓이 설정
        자막테이블.getColumn("확장자").setPreferredWidth(50);// 열 넓이 설정
        자막테이블.getColumn("확장자").setMinWidth(50);
        자막테이블.getColumn("확장자").setMaxWidth(50);
        // System.out.println(jTableSmi.getColumn("확장자"));
        // jt자막.getColumn("확장자").setWidth(10);
        테이블열숨기기(자막테이블, "전체경로");
        
    }

    private void 테이블열숨기기(JTable tb, String 테이블열이름) {
        tb.getColumn(테이블열이름).setWidth(0);
        tb.getColumn(테이블열이름).setMinWidth(0);
        tb.getColumn(테이블열이름).setMaxWidth(0);
    }

    public void 번호붙이기() {
        for (int i = 0; i < mdVideo.getRowCount() - 1; i++) {
            mdVideo.setValueAt(i + 1, i, 0);
        }
        for (int i = 0; i < mdVideo.getRowCount() - 1; i++) {
            mdSmi.setValueAt(i + 1, i, 0);
        }
    }

    private void testData() {// 테스트용 데이터
        int max = 30;
        for (int i = 0; i < max; i++) {
            String[] st = {i + "", "경로" + i, "파일명" + i, "수정1" + i, "확장자" + i, "확장자" + i};
            mdVideo.addRow(st);
        }
        for (int i = 0; i < max; i++) {
            String[] st = {i + "", "경로" + i, "파일명" + i, "확장자" + i, "확장자" + i};
            mdSmi.addRow(st);
        }
        for (int i = 0; i < max; i++) {
            String[] st = {i + "", "경1로" + i, "파1일명" + i, "확1장자" + i};
            mdEdit.addRow(st);
        }
    }

    private DefaultComboBoxModel dcb() {
        return new javax.swing.DefaultComboBoxModel(new String[]{"선택", "이름", "넘버", "부제"});
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

        수정테이블 = new JScrollPane();
        jTable1 = new JTable();
        buttonGroup1 = new ButtonGroup();
        메인패널 = new JPanel();
        jScrollPane2 = new JScrollPane();
        우측패널 = new JPanel();
        기능패널 = new JPanel();
        영상적용 = new JButton();
        자막적용 = new JButton();
        jSeparator4 = new JSeparator();
        파일추가 = new JButton();
        jSeparator1 = new JSeparator();
        선택삭제 = new JButton();
        영상전체삭제 = new JButton();
        자막전체삭제 = new JButton();
        jSeparator2 = new JSeparator();
        맨위로 = new JButton();
        위로 = new JButton();
        아래로 = new JButton();
        맨아래로 = new JButton();
        jSeparator3 = new JSeparator();
        이름바꾸기 = new JButton();
        jSeparator5 = new JSeparator();
        샌터패널 = new JPanel();
        테이블패널 = new JPanel();
        jSplitPane1 = new JSplitPane();
        jScrollPane3 = new JScrollPane();
        영상테이블 = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                //System.out.print(영상테이블.getRowSelectionAllowed());
                //this.setRowSelectionAllowed();
                return column==3 ? true : false;
            }
        };
        jScrollPane1 = new JScrollPane();
        자막테이블 = new JTable(){
            @Override
            public boolean isCellEditable(int row, int column) {
                //return column==3 ? true : false;
                //System.out.print(this.getRowSelectionAllowed());
                //this.setRowSelectionAllowed();
                return false;
            }
        };
        하단버튼패널 = new JPanel();
        번호패널 = new JPanel();
        jLabel2 = new JLabel();
        번호라벨 = new JLabel();
        수정패널 = new JPanel();
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
        김지영 = new JMenuItem();
        홍진표 = new JMenuItem();
        김동식 = new JMenuItem();
        박동희 = new JMenuItem();

        jTable1.setModel(mdEdit);
        수정테이블.setViewportView(jTable1);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        메인패널.setLayout(new BorderLayout());

        jScrollPane2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        우측패널.setLayout(new BorderLayout());

        기능패널.setLayout(new GridBagLayout());

        영상적용.setBackground(new Color(255, 102, 102));
        ResourceBundle bundle = ResourceBundle.getBundle("리네임/Bundle"); // NOI18N
        영상적용.setText(bundle.getString("ReameJFrame.영상적용.text")); // NOI18N
        영상적용.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                영상적용(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        기능패널.add(영상적용, gridBagConstraints);

        자막적용.setBackground(new Color(255, 102, 102));
        자막적용.setText(bundle.getString("ReameJFrame.자막적용.text")); // NOI18N
        자막적용.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                자막적용(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        기능패널.add(자막적용, gridBagConstraints);

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
        기능패널.add(jSeparator4, gridBagConstraints);

        파일추가.setBackground(new Color(102, 102, 255));
        파일추가.setText(bundle.getString("ReameJFrame.파일추가.text")); // NOI18N
        파일추가.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                파일추가(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        기능패널.add(파일추가, gridBagConstraints);

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
        기능패널.add(jSeparator1, gridBagConstraints);

        선택삭제.setBackground(new Color(255, 153, 255));
        선택삭제.setText(bundle.getString("ReameJFrame.선택삭제.text")); // NOI18N
        선택삭제.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent evt) {
                번호부여(evt);
            }
        });
        선택삭제.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                번호입력(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        기능패널.add(선택삭제, gridBagConstraints);

        영상전체삭제.setBackground(new Color(255, 153, 255));
        영상전체삭제.setText(bundle.getString("ReameJFrame.영상전체삭제.text")); // NOI18N
        영상전체삭제.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                영상전체삭제(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        기능패널.add(영상전체삭제, gridBagConstraints);

        자막전체삭제.setBackground(new Color(255, 153, 255));
        자막전체삭제.setText(bundle.getString("ReameJFrame.자막전체삭제.text")); // NOI18N
        자막전체삭제.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                자막전체삭제(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        기능패널.add(자막전체삭제, gridBagConstraints);

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
        기능패널.add(jSeparator2, gridBagConstraints);

        맨위로.setBackground(new Color(255, 255, 102));
        맨위로.setText(bundle.getString("ReameJFrame.맨위로.text")); // NOI18N
        맨위로.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent evt) {
                번호부여(evt);
            }
        });
        맨위로.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                번호입력(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        기능패널.add(맨위로, gridBagConstraints);

        위로.setBackground(new Color(255, 255, 102));
        위로.setText(bundle.getString("ReameJFrame.위로.text")); // NOI18N
        위로.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent evt) {
                번호부여(evt);
            }
        });
        위로.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                번호입력(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        기능패널.add(위로, gridBagConstraints);

        아래로.setBackground(new Color(255, 255, 102));
        아래로.setText(bundle.getString("ReameJFrame.아래로.text")); // NOI18N
        아래로.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent evt) {
                번호부여(evt);
            }
        });
        아래로.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                번호입력(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        기능패널.add(아래로, gridBagConstraints);

        맨아래로.setBackground(new Color(255, 255, 102));
        맨아래로.setText(bundle.getString("ReameJFrame.맨아래로.text")); // NOI18N
        맨아래로.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent evt) {
                번호부여(evt);
            }
        });
        맨아래로.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                번호입력(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        기능패널.add(맨아래로, gridBagConstraints);

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
        기능패널.add(jSeparator3, gridBagConstraints);

        이름바꾸기.setBackground(new Color(51, 255, 51));
        이름바꾸기.setText(bundle.getString("ReameJFrame.이름바꾸기.text")); // NOI18N
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        기능패널.add(이름바꾸기, gridBagConstraints);

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
        기능패널.add(jSeparator5, gridBagConstraints);

        우측패널.add(기능패널, BorderLayout.NORTH);

        jScrollPane2.setViewportView(우측패널);

        메인패널.add(jScrollPane2, BorderLayout.EAST);

        샌터패널.setLayout(new BorderLayout());

        GridBagLayout 테이블패널Layout = new GridBagLayout();
        테이블패널Layout.columnWidths = new int[] {0};
        테이블패널Layout.rowHeights = new int[] {0};
        테이블패널Layout.columnWeights = new double[] {0.0};
        테이블패널Layout.rowWeights = new double[] {0.0};
        테이블패널.setLayout(테이블패널Layout);

        jSplitPane1.setResizeWeight(0.7);
        jSplitPane1.setToolTipText(bundle.getString("ReameJFrame.jSplitPane1.toolTipText")); // NOI18N

        jScrollPane3.setPreferredSize(new Dimension(600, 402));

        영상테이블.setModel(mdVideo);
        영상테이블.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        영상테이블.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                영상테이블KeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(영상테이블);

        jSplitPane1.setLeftComponent(jScrollPane3);

        jScrollPane1.setPreferredSize(new Dimension(300, 402));

        자막테이블.setModel(mdSmi);
        자막테이블.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                자막테이블KeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(자막테이블);

        jSplitPane1.setRightComponent(jScrollPane1);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        테이블패널.add(jSplitPane1, gridBagConstraints);

        샌터패널.add(테이블패널, BorderLayout.CENTER);

        하단버튼패널.setLayout(new GridBagLayout());

        번호패널.setPreferredSize(new Dimension(50, 63));
        번호패널.setLayout(new GridBagLayout());

        jLabel2.setText(bundle.getString("ReameJFrame.jLabel2.text")); // NOI18N
        jLabel2.setToolTipText(bundle.getString("ReameJFrame.jLabel2.toolTipText")); // NOI18N
        jLabel2.setHorizontalTextPosition(SwingConstants.CENTER);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.PAGE_END;
        번호패널.add(jLabel2, gridBagConstraints);

        번호라벨.setText(bundle.getString("ReameJFrame.번호라벨.text")); // NOI18N
        번호라벨.setHorizontalTextPosition(SwingConstants.CENTER);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.PAGE_END;
        번호패널.add(번호라벨, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 10;
        하단버튼패널.add(번호패널, gridBagConstraints);
        번호패널.getAccessibleContext().setAccessibleDescription(bundle.getString("ReameJFrame.번호패널.AccessibleContext.accessibleDescription")); // NOI18N

        수정패널.setLayout(new GridLayout(3, 4));

        jLabel4.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel4.setText(bundle.getString("ReameJFrame.jLabel4.text")); // NOI18N
        수정패널.add(jLabel4);

        jLabel5.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel5.setText(bundle.getString("ReameJFrame.jLabel5.text")); // NOI18N
        수정패널.add(jLabel5);

        jLabel6.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel6.setText(bundle.getString("ReameJFrame.jLabel6.text")); // NOI18N
        수정패널.add(jLabel6);

        jLabel7.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel7.setText(bundle.getString("ReameJFrame.jLabel7.text")); // NOI18N
        수정패널.add(jLabel7);

        jComboBox1.setModel(dcb());
        수정패널.add(jComboBox1);

        jComboBox2.setModel(dcb());
        수정패널.add(jComboBox2);

        jComboBox3.setModel(dcb());
        수정패널.add(jComboBox3);

        jComboBox4.setModel(dcb());
        수정패널.add(jComboBox4);
        수정패널.add(jTextField1);
        수정패널.add(jTextField2);
        수정패널.add(jTextField3);
        수정패널.add(jTextField4);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        하단버튼패널.add(수정패널, gridBagConstraints);

        샌터패널.add(하단버튼패널, BorderLayout.PAGE_END);

        메인패널.add(샌터패널, BorderLayout.CENTER);

        getContentPane().add(메인패널, BorderLayout.CENTER);

        jMenu1.setText(bundle.getString("ReameJFrame.jMenu1.text")); // NOI18N

        buttonGroup1.add(jRadioButtonMenuItem1);
        jRadioButtonMenuItem1.setText(bundle.getString("ReameJFrame.jRadioButtonMenuItem1.text")); // NOI18N
        jRadioButtonMenuItem1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                UI세팅(evt);
            }
        });
        jMenu1.add(jRadioButtonMenuItem1);

        buttonGroup1.add(jRadioButtonMenuItem2);
        jRadioButtonMenuItem2.setText(bundle.getString("ReameJFrame.jRadioButtonMenuItem2.text")); // NOI18N
        jRadioButtonMenuItem2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                UI세팅(evt);
            }
        });
        jMenu1.add(jRadioButtonMenuItem2);

        buttonGroup1.add(jRadioButtonMenuItem3);
        jRadioButtonMenuItem3.setText(bundle.getString("ReameJFrame.jRadioButtonMenuItem3.text")); // NOI18N
        jRadioButtonMenuItem3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                UI세팅(evt);
            }
        });
        jMenu1.add(jRadioButtonMenuItem3);

        buttonGroup1.add(jRadioButtonMenuItem4);
        jRadioButtonMenuItem4.setText(bundle.getString("ReameJFrame.jRadioButtonMenuItem4.text")); // NOI18N
        jRadioButtonMenuItem4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                UI세팅(evt);
            }
        });
        jMenu1.add(jRadioButtonMenuItem4);

        buttonGroup1.add(jRadioButtonMenuItem5);
        jRadioButtonMenuItem5.setText(bundle.getString("ReameJFrame.jRadioButtonMenuItem5.text")); // NOI18N
        jRadioButtonMenuItem5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                UI세팅(evt);
            }
        });
        jMenu1.add(jRadioButtonMenuItem5);

        buttonGroup1.add(jRadioButtonMenuItem6);
        jRadioButtonMenuItem6.setSelected(true);
        jRadioButtonMenuItem6.setText(bundle.getString("ReameJFrame.jRadioButtonMenuItem6.text")); // NOI18N
        jRadioButtonMenuItem6.setToolTipText(bundle.getString("ReameJFrame.jRadioButtonMenuItem6.toolTipText")); // NOI18N
        jRadioButtonMenuItem6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                UI세팅(evt);
            }
        });
        jMenu1.add(jRadioButtonMenuItem6);

        buttonGroup1.add(jRadioButtonMenuItem7);
        jRadioButtonMenuItem7.setText(bundle.getString("ReameJFrame.jRadioButtonMenuItem7.text")); // NOI18N
        jRadioButtonMenuItem7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                UI세팅(evt);
            }
        });
        jMenu1.add(jRadioButtonMenuItem7);

        buttonGroup1.add(jRadioButtonMenuItem8);
        jRadioButtonMenuItem8.setText(bundle.getString("ReameJFrame.jRadioButtonMenuItem8.text")); // NOI18N
        jRadioButtonMenuItem8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                UI세팅(evt);
            }
        });
        jMenu1.add(jRadioButtonMenuItem8);

        buttonGroup1.add(jRadioButtonMenuItem9);
        jRadioButtonMenuItem9.setText(bundle.getString("ReameJFrame.jRadioButtonMenuItem9.text")); // NOI18N
        jRadioButtonMenuItem9.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                UI세팅(evt);
            }
        });
        jMenu1.add(jRadioButtonMenuItem9);

        buttonGroup1.add(jRadioButtonMenuItem10);
        jRadioButtonMenuItem10.setText(bundle.getString("ReameJFrame.jRadioButtonMenuItem10.text")); // NOI18N
        jRadioButtonMenuItem10.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                UI세팅(evt);
            }
        });
        jMenu1.add(jRadioButtonMenuItem10);

        buttonGroup1.add(jRadioButtonMenuItem11);
        jRadioButtonMenuItem11.setText(bundle.getString("ReameJFrame.jRadioButtonMenuItem11.text")); // NOI18N
        jRadioButtonMenuItem11.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                UI세팅(evt);
            }
        });
        jMenu1.add(jRadioButtonMenuItem11);

        buttonGroup1.add(jRadioButtonMenuItem12);
        jRadioButtonMenuItem12.setText(bundle.getString("ReameJFrame.jRadioButtonMenuItem12.text")); // NOI18N
        jRadioButtonMenuItem12.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                UI세팅(evt);
            }
        });
        jMenu1.add(jRadioButtonMenuItem12);

        jMenuBar1.add(jMenu1);

        jMenu3.setText(bundle.getString("ReameJFrame.jMenu3.text")); // NOI18N

        김지영.setText(bundle.getString("ReameJFrame.김지영.text")); // NOI18N
        김지영.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                감상(evt);
            }
        });
        jMenu3.add(김지영);

        홍진표.setText(bundle.getString("ReameJFrame.홍진표.text")); // NOI18N
        홍진표.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                감상(evt);
            }
        });
        jMenu3.add(홍진표);

        김동식.setText(bundle.getString("ReameJFrame.김동식.text")); // NOI18N
        김동식.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                감상(evt);
            }
        });
        jMenu3.add(김동식);

        박동희.setText(bundle.getString("ReameJFrame.박동희.text")); // NOI18N
        박동희.setToolTipText(bundle.getString("ReameJFrame.박동희.toolTipText")); // NOI18N
        박동희.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                감상(evt);
            }
        });
        jMenu3.add(박동희);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void 영상전체삭제(ActionEvent evt) {//GEN-FIRST:event_영상전체삭제
        mdVideo.setNumRows(0);
        mdEdit.setNumRows(0);

    }//GEN-LAST:event_영상전체삭제

    private void 파일추가(ActionEvent evt) {//GEN-FIRST:event_파일추가

        try {
            new renameAdd(파일추가, mdVideo, mdSmi, mdEdit);
            //rad.Add(jTitleSmi);
        } catch (IOException ex) {
            Logger.getLogger(ReameJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_파일추가

    private void 이름바구기ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_이름바구기ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_이름바구기ActionPerformed

    private void 자막적용(ActionEvent evt) {//GEN-FIRST:event_자막적용
        // TODO add your handling code here:
        try {
            new renameApplySmi(mdVideo, mdSmi);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }//GEN-LAST:event_자막적용

    private void 영상적용(ActionEvent evt) {//GEN-FIRST:event_영상적용
        // TODO add your handling code here:
        try {
            new renameApplyVideo(mdVideo, mdSmi);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }//GEN-LAST:event_영상적용

    private void 자막전체삭제(ActionEvent evt) {//GEN-FIRST:event_자막전체삭제
        mdSmi.setNumRows(0);
    }//GEN-LAST:event_자막전체삭제

    private void UI세팅(ActionEvent evt) {//GEN-FIRST:event_UI세팅
        // TODO add your handling code here:
        //System.out.println("bt:" + ((JRadioButtonMenuItem) evt.getSource()).getText());
        uiset.UISelet(((JRadioButtonMenuItem) evt.getSource()).getText());
        //uiset1.UISelet(((JRadioButtonMenuItem) evt.getSource()).getText());
    }//GEN-LAST:event_UI세팅

    private void 영상테이블KeyReleased(KeyEvent evt) {//GEN-FIRST:event_영상테이블KeyReleased
        // TODO add your handling code here:
        nsc.NumberSetting();
    }//GEN-LAST:event_영상테이블KeyReleased

    private void 자막테이블KeyReleased(KeyEvent evt) {//GEN-FIRST:event_자막테이블KeyReleased
        // TODO add your handling code here:
        nsc.NumberSetting();
    }//GEN-LAST:event_자막테이블KeyReleased

    private void 번호부여(MouseEvent evt) {//GEN-FIRST:event_번호부여
        // TODO add your handling code here:
        nsc.NumberSetting();
    }//GEN-LAST:event_번호부여

    private void 번호입력(ActionEvent evt) {//GEN-FIRST:event_번호입력
        // TODO add your handling code here:
        nsc.NumberSetting();
    }//GEN-LAST:event_번호입력

    private void 감상(ActionEvent evt) {//GEN-FIRST:event_감상
        // TODO add your handling code here:
        if (evt.getSource()==김지영) {			
		JOptionPane.showMessageDialog(null, "한줄짜리 글이 이렇게 긴 메소드가 될줄 몰랐다.", "김지영", JOptionPane.ERROR_MESSAGE);
		}        
        if (evt.getSource()==홍진표) {			
        	JOptionPane.showMessageDialog(null, "이번 프로젝트를 하면서 이해 못 했던 개념들을 이해할 수 있는 좋은 기회가 되었습니다."
                        + "\n또한 나만 알아볼 수 있는 프로그램이 아닌 다른 사람과 협동하면서 각자의 부분을 이해할 수 있도록 하며,"
                        + "\n하나의 프로그램을 어떻게 여러 사람들이 파트를 나눠서 만들 수 있는지를 이해하게 되었습니다. "
                        + "\n그리고 리더의 중요성과 현명한 역할 분담이 가져오는 효과 또한 체험할 수 있었던 유익한 시간이었습니다.", "홍진표", JOptionPane.INFORMATION_MESSAGE);
		}       
        if (evt.getSource()==김동식) {			
        	JOptionPane.showMessageDialog(null, "아직도 많이 부족하지만 그래도 뭔가를 해냈다는 생각에 자신감이 조금은 생겼습니다\n" +
"모자란 저에게 도움을준 팀원들에게 감사드려요", "김동식", JOptionPane.WARNING_MESSAGE);
		}       
        if (evt.getSource()==박동희) {			
        	JOptionPane.showMessageDialog(null, "아...내가 뭘했지..? \n채팅이나 만들자;;", "박동희", JOptionPane.QUESTION_MESSAGE);
		}
    }//GEN-LAST:event_감상

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
    JPanel 기능패널;
    JMenuItem 김동식;
    JMenuItem 김지영;
    JButton 맨아래로;
    JButton 맨위로;
    JPanel 메인패널;
    JMenuItem 박동희;
    JLabel 번호라벨;
    JPanel 번호패널;
    JPanel 샌터패널;
    JButton 선택삭제;
    JScrollPane 수정테이블;
    JPanel 수정패널;
    JButton 아래로;
    JButton 영상적용;
    JButton 영상전체삭제;
    JTable 영상테이블;
    JPanel 우측패널;
    JButton 위로;
    JButton 이름바꾸기;
    JButton 자막적용;
    JButton 자막전체삭제;
    JTable 자막테이블;
    JPanel 테이블패널;
    JButton 파일추가;
    JPanel 하단버튼패널;
    JMenuItem 홍진표;
    // End of variables declaration//GEN-END:variables

}
