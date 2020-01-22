package com.leetcode;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.util.Comparator;

/**
 * Created on 2019-08-17
 *
 * @author :hao.li
 */
public class demo1 {
    public static void main(String[] args) {
        JTable table = new JTable();
        TableRowSorter rowSorter = (TableRowSorter) table.getRowSorter();
        Comparator<Number> numberComparator = new Comparator<Number>() {
            @Override
            public int compare(Number o1, Number o2) {
                if ( o1 == null ) {
                    return -1;
                }
                if ( o2 == null ) {
                    return 1;
                }
                if ( o1.doubleValue() < o2.doubleValue() ) {
                    return -1;
                }
                if ( o1.doubleValue() > o2.doubleValue() ) {
                    return -1;
                }
                return 0;
            }
        };
        for (int col = 0; col < table.getColumnCount(); col++) {
            rowSorter.setComparator(col, numberComparator);
        }
    }
}
