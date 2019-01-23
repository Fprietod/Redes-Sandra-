package edu.escom.cliente.gui;

import edu.escom.cliente.backend.ButtonColumn;
import edu.escom.cliente.backend.PDFCreator;
import edu.escom.cliente.backend.TableCellListener;
import edu.escom.cliente.backend.TableModelProducto;
import edu.escom.cliente.sockets.ClienteConexion;
import edu.escom.data.Producto;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class Carrito extends javax.swing.JFrame {

    private ClienteConexion conexion;
    private List<Producto> carrito;
    JLabel jLabel3; //label con el numero de archivos
    JPanel panelProductos;
    double total = 0.0;
    
    private String html;
    private File path;

    public Carrito(List<Producto> carrito, JLabel jLabel3, JPanel panelProductos,
                   File path, ClienteConexion conexion) {
        this.carrito = carrito;
        this.jLabel3 = jLabel3;
        this.panelProductos = panelProductos;
        this.conexion = conexion;
        this.path = path;
        initComponents();
        ButtonColumn borrar = new ButtonColumn(jTable1, delete, 4);
        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        ajustarTabla();
        setLocationRelativeTo(null);

        TableCellListener tcl = new TableCellListener(jTable1, action);

    }

    Action delete = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {

            JTable table = (JTable) e.getSource();
            int modelRow = Integer.valueOf(e.getActionCommand());

            int showConfirmDialog = JOptionPane.showConfirmDialog(null, "Estas seguro que deseas eliminar '"
                    + carrito.get(modelRow).getNombre() + "'.", "Eliminando producto", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (JOptionPane.OK_OPTION == showConfirmDialog) {

                Component[] components = panelProductos.getComponents();
                for (Component component : components) {
                    ProductoPanel tmp = (ProductoPanel) component;

                    if (tmp.nombre.getText().contains(carrito.get(modelRow).getNombre())) {
                        tmp.boton.setEnabled(true);
                        break;
                    }

                }
                carrito.remove(modelRow);
                ((TableModelProducto) jTable1.getModel()).removeRow(modelRow);
                jLabel3.setText( String.valueOf(carrito.size()) );
            }
        }
    };

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Carrito de compras");

        jTable1.setModel(new TableModelProducto(carrito));
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Comprar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Total:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("0.0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 568, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(26, 26, 26))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jButton1))
                .addGap(10, 10, 10))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        createHTML();
        if (carrito.size() < 1) {
            JOptionPane.showMessageDialog(this, "Carrito vacio!", "No se puede realizar la compra. ", JOptionPane.WARNING_MESSAGE);
        } else if (total <= 0) {
            JOptionPane.showMessageDialog(this, "Carrito vacio! Asegurate de introducir cuantas piezas por producto quieres.", "No se puede realizar la compra. ", JOptionPane.WARNING_MESSAGE);
        } else {

            for (Producto prod : carrito) {
                if (prod.getCantidad() == 0) {
                    JOptionPane.showMessageDialog(this, "Carrito parcialmente vacio! Asegurate de introducir cuantas piezas por producto quieres.", "No se puede realizar la compra. ", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }

            boolean succes = enviarCambiosServidor();
            if (succes) {
                try {
                    PDFCreator.create(html, path);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Un error ocurrio creado el ticket de compra! " + ex,
                            "No se puede imprimir el ticket. ", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            } else {
                System.exit(0);
            }

        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    private void ajustarTabla() {
        for (int column = 0; column < jTable1.getColumnCount(); column++) {
            TableColumn tableColumn = jTable1.getColumnModel().getColumn(column);
            int preferredWidth = tableColumn.getMinWidth();
            int maxWidth = tableColumn.getMaxWidth();

            for (int row = 0; row < jTable1.getRowCount(); row++) {
                TableCellRenderer cellRenderer = jTable1.getCellRenderer(row, column);
                Component c = jTable1.prepareRenderer(cellRenderer, row, column);
                int width = c.getPreferredSize().width + jTable1.getIntercellSpacing().width;
                preferredWidth = Math.max(preferredWidth, width);

                //  We've exceeded the maximum width, no need to check other rows
                if (preferredWidth >= maxWidth) {
                    preferredWidth = maxWidth;
                    break;
                }
            }

            jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

            jTable1.getColumnModel().getColumn(0).setPreferredWidth(195);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(90);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(70);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(75);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(120);

        }
    }

    Action action = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
           
            TableCellListener tcl = (TableCellListener) e.getSource();
            
            
            if (Integer.parseInt(String.valueOf(tcl.getNewValue())) > carrito.get(tcl.getRow()).getDisponibles()) {
                JOptionPane.showMessageDialog(null, "El número solicitado es mayor que los disponibles.", "Error en la compra",
                        JOptionPane.WARNING_MESSAGE);
                tcl.getTable().getModel().setValueAt(tcl.getOldValue(), tcl.getRow(), tcl.getColumn());
            }

            total = 0;
            for (Producto prod : carrito) {
                total += prod.getCantidad() * prod.getPrecio();
            }
            jLabel4.setText(String.valueOf(total));
        }
    };

    private void createHTML() {

        Date fechaActual = new Date();
        DateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

        Locale locale = new Locale("es", "MX");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);

        double tmp = 0.0;
        int cont = 0;
        html = "<html><head><style>table td{padding:6px;} .odd{background-color:#8DA1C4;}</style></head><body>";
        html += "<h2 align='right'; style='color:#00000;'>TICKET DE COMPRA</h2>";
        html += "<h1 style='color:#7D9FEF;'>Tiendita RUPE y MONJA :v</h1>";
        html += "<h3 align='left'; style='color:#0E0E10;font-size:12px;'>RFC: GGB060809JD5 </h3>";
        html += "<h3 align='left'; style='color:#0E0E10;font-size:12px;'>Juan de Dios Bátiz, Nueva Industrial Vallejo, Ciudad de México, CDMX</h3>";
        html += "<h3 align='left'; style='color:#0E0E10;font-size:12px;'>Ciudad de México C.P:07710 </h3>";
        html += "<h3 align='left'; style='color:#0E0E10;font-size:12px;'>Tel. 55-12345678</h3>";
        html += "<br/><hr style='color:#5C8AE6;'/>";
        html += "<table style='width:100%; border:1px solid #7384F3;'><tr style='background-color:#7384F3;color:white;'>"
                + "<td style='width:45%;'>Artículo</td>"
                + "<td style='width:15%;'><p align='center'>Cantidad</p></td>"
                + "<td style='width:20%;'><p align='center'>Precio Unitario</p></td>"
                + "<td style='width:20%;'><p align='center'>Importe</p></td></tr>";        
        
        for (Producto prod : carrito) {
            tmp = prod.getCantidad() * prod.getPrecio();
            String tmpS = cont++ % 2 > 0 ? "" : "odd";

            html += "<tr class='" + tmpS + "'><td>" + prod.getNombre() + "</td>"
                    + "<td><p align='center'>" + prod.getCantidad() + "</p></td>"
                    + "<td><p align='right'>" + currencyFormatter.format(prod.getPrecio()) + "</p></td>"
                    + "<td><p align='right'>" + currencyFormatter.format(tmp) + "</p></td></tr>";

        }
        html += "<tr><td></td><td colspan='2'><p align='right'><b>Total a pagar</b></p></td>"
                + "<td><p align='right'><b>" + currencyFormatter.format(total) + "</b></p></td></tr>";
        html += "</table>";
        html += "<h3 style='color:#4F249A;font-size:12px;'> Hora: " + formatoHora.format(fechaActual) + "</h3>";
        html += "<h3 style='color:#4F249A;font-size:12px;'> Fecha de operación: " + formatoFecha.format(fechaActual) + "</h3>";
        html += "<br/><h2 align='center'; style='color:#00000;'>¡ GRACIAS POR SU COMPRA !</h2></body></html>";
    }

    private boolean enviarCambiosServidor() {
        for (Producto prod : carrito) {
            prod.setDisponibles(prod.getDisponibles() - prod.getCantidad());
            prod.setCantidad(0);
            total = 0;
        }

        try {
            conexion.enviarCambios(carrito);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Un error ocurrio actualizando información en el servidor! " + ex,
                    "No se pudo concretar la compra. ", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        Component[] components = panelProductos.getComponents();
        for (Component component : components) {
            ProductoPanel tmp = (ProductoPanel) component;
            tmp.boton.setEnabled(true);
        }

        //Actualizar disponibilidad en panels
        for (Producto prod : carrito) {
            for (Component component : components) {
                ProductoPanel tmp = (ProductoPanel) component;

                if (tmp.nombre.getText().contains(prod.getNombre())) {
                    tmp.precioDisponibles.setText("Precio: " + prod.getPrecio() + " | Disponibles: " + prod.getDisponibles());
                    if (prod.getDisponibles() <= 0) {
                        tmp.boton.setEnabled(false);
                        tmp.boton.setText("Agotados");
                        tmp.boton.setBackground(Color.red);
                    } else {
                        tmp.boton.setText("Agregar al carrito");
                    }
                }
            }
        }

        jLabel3.setText("0");
        carrito.clear();
        this.dispose();
        return true;
    }
}
