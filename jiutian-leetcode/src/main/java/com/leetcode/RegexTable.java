package com.leetcode;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created on 2019-08-17
 *
 * @author :hao.li
 */
public class RegexTable {
    public static void main(String args[]) {
        JFrame frame = new JFrame("Regexing JTable");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Object rows[][] = { { "A", "About", 44.36 }, { "B", "Boy", 44.84 }, { "C", "Cat", 463.63 },
                { "D", "Day", 27.14 }, { "E", "Eat", 44.57 }, { "F", "Fail", 23.15 },
                { "G", "Good", 4.40 }, { "H", "Hot", 24.96 }, { "I", "Ivey", 5.45 },
                { "J", "Jack", 49.54 }, { "K", "Kids", 280.00 } };
        String columns[] = { "Symbol", "Name", "Price" };
        TableModel model = new DefaultTableModel(rows, columns) {
            public Class getColumnClass(int column) {
                Class returnValue;
                if ((column >= 0) && (column < getColumnCount())) {
                    returnValue = getValueAt(0, column).getClass();
                } else {
                    returnValue = Object.class;
                }
                return returnValue;
            }
        };

        final JTable table = new JTable(model);

        //创建可排序表对象
        final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);

        //将可排序表对象设置到表中
        table.setRowSorter(sorter);
        JScrollPane pane = new JScrollPane(table);
        frame.add(pane, BorderLayout.CENTER);

        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Filter");
        panel.add(label, BorderLayout.WEST);
        final JTextField filterText = new JTextField("A");
        panel.add(filterText, BorderLayout.CENTER);
        frame.add(panel, BorderLayout.NORTH);
        JButton button = new JButton("Filter");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = filterText.getText();
                if (text.length() == 0) {
                    sorter.setRowFilter(null);
                } else {

                    //调用方法实现过滤内容
                    sorter.setRowFilter(RowFilter.regexFilter(text));
                }
            }
        });
        frame.add(button, BorderLayout.SOUTH);
        frame.setSize(300, 250);
        frame.setVisible(true);
    }
}
