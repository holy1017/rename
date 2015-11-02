package 리네임;

import java.awt.GraphicsConfiguration;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

public class reWordWindows extends JFrame implements ActionListener {

    DefaultTableModel mdVideo;
    DefaultTableModel mdVideo2;
    JButton rename;
    String word1 = "";
    String word2 = "";
    JTextField jTextField1;
    JTextField jTextField2;
    JButton jButton1;

    /**
     * @param mdVideo 테이블
     * @param mdVideo2 임시 테이블
     * @param rename 버튼
     */
    public reWordWindows(DefaultTableModel mdVideo, DefaultTableModel mdVideo2, JButton rename) {
        this.mdVideo = mdVideo;
        this.mdVideo2 = mdVideo2;
        this.rename = rename;
        rename.addActionListener(this);
        initComponents();
    }

    void reWord() { //(찾을, 바꿀) 같은 단어를 치환해준다.
        word1 = jTextField1.getText();
        word2 = jTextField2.getText();

        for (int i = 0; i < mdVideo2.getRowCount(); i++) {
            for (int j = 0; j < mdVideo2.getColumnCount(); j++) {
                String str = (String) mdVideo2.getValueAt(i, j);
                str = str.replaceAll(word1, word2);
                mdVideo2.setValueAt(str, i, j);
            }
        }
        strSum();
    }

    void strSum() { //임시폴더의 데이터를 합하여 수정후로 보냄
        int k = mdVideo.findColumn("수정");
        System.out.println(k + "z케이");
        for (int i = 0; i < mdVideo2.getRowCount(); i++) {
            String strss = "";
            for (int j = 0; j < 4; j++) {
                strss += mdVideo2.getValueAt(i, j);
            }
            mdVideo.setValueAt(strss, i, k);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {
        GridBagConstraints gridBagConstraints;

        JLabel jLabel1 = new JLabel();
        JLabel jLabel2 = new JLabel();
        jTextField1 = new JTextField();
        jTextField2 = new JTextField();
        jButton1 = new JButton();
        JLabel jLabel3 = new JLabel();

        //setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridBagLayout());

        jLabel1.setText("   변경전");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        getContentPane().add(jLabel1, gridBagConstraints);

        jLabel2.setText("   변경후");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        getContentPane().add(jLabel2, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 271;
        gridBagConstraints.ipady = 14;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(5, 0, 5, 5);
        getContentPane().add(jTextField1, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 271;
        gridBagConstraints.ipady = 13;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(0, 0, 0, 5);
        getContentPane().add(jTextField2, gridBagConstraints);

        jButton1.setText("변경");
        jButton1.addActionListener(this);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 17;
        gridBagConstraints.ipady = 16;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        getContentPane().add(jButton1, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        getContentPane().add(jLabel3, gridBagConstraints);

        pack();

    }

	//public static void main(String args[]) {
    //reWordWindows rw=new reWordWindows();
    //rw.setVisible(true);
//	}
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == rename) {
            setVisible(true);
        }

        if (e.getSource() == jButton1) {
            reWord();
        }

    }

}
