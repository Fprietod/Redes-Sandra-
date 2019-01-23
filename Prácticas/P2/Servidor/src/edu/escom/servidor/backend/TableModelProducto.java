
package edu.escom.servidor.backend;

import edu.escom.data.Producto;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TableModelProducto extends AbstractTableModel {

    private String[] columnNames = {"Nombre", "Disponibles", "Precio", "", ""};
    private List<Producto> productos;

    public TableModelProducto(List<Producto> productos) {
        this.productos = productos;
    }

    @Override
    public int getRowCount() {
        return productos.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return productos.get(rowIndex).getServer(columnIndex);
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        if (col < 3) {
            return false;
        } else {
            return true;
        }
    }

    public void removeRow(int row) {
        fireTableRowsDeleted(row, row);
    }
    
    public void insertedRow(int row){
        fireTableRowsInserted(row, row);
    }

}
