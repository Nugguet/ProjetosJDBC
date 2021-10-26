package br.com.estacio.model;

import javax.swing.event.TableModelListener;

public interface TableModel {
	int getRowCount();

	int getColumnCount();

	String getColumnName(int columnIndex);

	Class<?> getColumnClass(int columnIndex);

	boolean isCellEditable(int rowIndex, int columnIndex);

	Object getValueAt(int rowIndex, int columnIndex);

	void setValueAt(Object aValue, int rowIndex, int columnIndex);

	void addTableModelListener(TableModelListener listener);

	void removeTableModelListener(TableModelListener listener);
}