package com.leetcode;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Comparator;

/**
 * Created on 2019-08-17
 *
 * @author :hao.li
 */
public class demo {
    public static void main(String[] args) {
        String[][] data = new String[][]{{"49","a","test1"}, {"24", "b", "test2"},{ "9","c", "test3"},
        { "5", "d", "test4"},{ "210", "a", "test5"}};
        String[] title = new String[]{"a1", "b1", "c1"};

        DefaultTableModel model = new DefaultTableModel(data, title);
        JTable table = new JTable(model);
        String[] rowValues = {"1", "f", "TestAdd"};
        model.addRow(rowValues);

        final TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(
                model);

        final JTextField field = new JTextField(20);
        final JComboBox list = new JComboBox(title);
        JButton button = new JButton("Find");

        table.setRowSorter(sorter);

        sorter.setComparator(0, new Comparator<Object>() {
            public int compare(Object arg0, Object arg1) {
                try {
                    int a = Integer.parseInt(arg0.toString());
                    int b = Integer.parseInt(arg1.toString());
                    return a - b;
                } catch (NumberFormatException e) {
                    return 0;
                }
            }
        });

        button.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                int index = list.getSelectedIndex();
                sorter.setRowFilter(RowFilter.regexFilter(field.getText(),
                        index));
            }
        });

        JFrame frame = new JFrame("TableSort");
        JPanel contentPanel = new JPanel(new BorderLayout());
        JPanel searchPanel = new JPanel();
        searchPanel.add(list);
        searchPanel.add(field);
        searchPanel.add(button);
        contentPanel.add(searchPanel, BorderLayout.NORTH);
        contentPanel.add(new JScrollPane(table), BorderLayout.CENTER);
        frame.getContentPane().add(contentPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}


