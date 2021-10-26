package br.com.estacio.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public abstract class ViewAbstractTableModel<E> extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected List<E> rows;
	protected String[] columns;

	public ViewAbstractTableModel(List<E> rows) {
		this.rows = rows;
	}

	@Override
	public int getRowCount() {
		return rows.size();
	}

	@Override
	public int getColumnCount() {
		return columns.length;
	}

	@Override
	public String getColumnName(int column) {
		if (column < getColumnCount())
			return columns[column];
		return super.getColumnName(column);
	}

	public E getValueAtRow(int row) {
		return rows.get(row);
	}

	public void setValueAtRow(int row, E object) {
		rows.set(row, object);
	}
}
