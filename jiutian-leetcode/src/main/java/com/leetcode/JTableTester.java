package com.leetcode;

/**
 * Created on 2019-08-17
 *
 * @author :hao.li
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JTableTester {
    static String data[][] = {
            {"China","Beijing","Chinese"},
            {"America","Washington","English"},
            {"Korea","Seoul","Korean"},
            {"Japan","Tokyo","Japanese"},
            {"France","Paris","French"},
            {"England","London","English"},
            {"Germany","Berlin","German"},
    };
    static String titles[] = {"Country","Capital","Language"};
    public static void main(String[] args) {
        DefaultTableModel model = new DefaultTableModel(data,titles);
        JTable jTable = new JTable(model);
        final TableRowSorter sorter = new TableRowSorter(model);
        jTable.setRowSorter(sorter); //为JTable设置排序器

        JScrollPane sPane = new JScrollPane();
        sPane.setViewportView(jTable);

//        JPanel panel = new JPanel();
//        panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));
//        JLabel label = new JLabel("Criteria:");
//        final JTextField jTextField = new JTextField();
//        JButton button = new JButton("Do Filter");
//        panel.add(label);
//        panel.add(jTextField);
//        panel.add(button);
//        button.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                if(jTextField.getText().length()==0){
//                    sorter.setRowFilter(null);
//                }else{
//                    sorter.setRowFilter(RowFilter.regexFilter(jTextField.getText()));//为JTable设置基于正则表达式的过滤条件
//                }
//            }
//        });

        JFrame f = new JFrame("JTable Sorting and Filtering");
        f.getContentPane().add(sPane, BorderLayout.CENTER);
//        f.getContentPane().add(panel,BorderLayout.SOUTH);
        f.setSize(400,300);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}
//运行上面程序，单击JTable的某一个title，这个title对应的列就会按照升序/降序重新排列；在下面的Criteria文本框中输入"ese"，点击"Do Filter"按钮，JTable将只显示带有"ese"字符串的行，也就是China和Japan两行，如果文本框里面什么都没有，点击"Do Filter"按钮，这时JTable会显示所有的行。

