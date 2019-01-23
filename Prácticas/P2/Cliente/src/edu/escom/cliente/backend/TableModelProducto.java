package edu.escom.cliente.backend;

import edu.escom.data.Producto;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TableModelProducto extends AbstractTableModel {

    private String[] columnNames = {"Producto", "Precio", "Cantidad", "Disponibles", ""};
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
        return productos.get(rowIndex).getClient(columnIndex);
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        
        Producto prod = productos.get(rowIndex);
        switch (columnIndex) {
            case 0:
                prod.setNombre(String.valueOf(aValue));
            case 1:
               prod.setPrecio(Double.parseDouble(aValue.toString()));
            case 2:
                prod.setCantidad(Integer.parseInt(aValue.toString()));
        }
    }
    
    

    @Override
    public boolean isCellEditable(int row, int col) {
        if(col == 2 || col == 4){
            return true;
        }else{
            return false;
        }
    }

    public void removeRow(int row) {
        fireTableRowsDeleted(row, row);
    }

    public void insertedRow(int row) {
        fireTableRowsInserted(row, row);
    }

}
