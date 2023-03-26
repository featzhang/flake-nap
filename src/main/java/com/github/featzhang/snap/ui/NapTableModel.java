package com.github.featzhang.snap.ui;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class NapTableModel extends AbstractTableModel {

    private final List<NapEntity> data;

    public NapTableModel() {
        data = new ArrayList<>();
    }

    public void addData(NapEntity napEntity) {
        data.add(napEntity);
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        NapEntity row = data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return row.getTitle();
            case 1:
                return row.getStartTime();
            case 2:
                return row.getEndTime();
            default:
                return "";
        }
    }
}
