package edu.escom.gui;


import edu.escom.servidor.backend.ButtonColumn;
import edu.escom.servidor.backend.Catalogo;
import edu.escom.servidor.backend.ChangesListener;
import edu.escom.servidor.backend.TableModelProducto;
import edu.escom.servidor.sockets.ServerConexionII;
import java.awt.Component;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class ServidorVentana extends javax.swing.JFrame implements ChangesListener {

    
    private Catalogo catalogo;
    private ServerConexionII serverConexion;

    public ServidorVentana() {
        catalogo = new Catalogo();
        boolean inicializar = catalogo.inicializar();

        if (!inicializar) {
            System.exit(0);
        }
        initComponents();

        ButtonColumn borrar = new ButtonColumn(jTable1, delete, 4);
        ButtonColumn editar = new ButtonColumn(jTable1, edit, 3);
        updateTotalProducts();

        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

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
            tableColumn.setPreferredWidth(preferredWidth);
        }

        setLocationRelativeTo(null);
        this.setTitle("SERVIDOR");

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        totalProductos = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        addButton = new javax.swing.JButton();
        startButton = new javax.swing.JButton();
        webStatusBar1 = new com.alee.extended.statusbar.WebStatusBar();
        jLabel4 = new javax.swing.JLabel();
        estadoServidor = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ESCOMarket");

        jLabel2.setText("Productos en el sistema:");

        totalProductos.setText("0");

        jTable1.setModel(new TableModelProducto(catalogo.getProductos()));
        jTable1.setPreferredSize(new java.awt.Dimension(654, 230));
        jScrollPane1.setViewportView(jTable1);

        addButton.setText("Agregar producto");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        startButton.setText("Iniciar Servidor");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        jLabel4.setText("Estado del servidor:");
        webStatusBar1.add(jLabel4);

        estadoServidor.setText("detenido");
        webStatusBar1.add(estadoServidor);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(webStatusBar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(startButton)
                                .addGap(29, 29, 29)
                                .addComponent(addButton))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 691, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(totalProductos))))
                        .addGap(0, 17, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(totalProductos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startButton)
                    .addComponent(addButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(webStatusBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        ProductoNuevo pN = new ProductoNuevo(catalogo);
        pN.setVisible(true);
        pN.addListener(this);
    }//GEN-LAST:event_addButtonActionPerformed

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        startButton.setEnabled(false);
        startButton.setText("Corriendo....");
        estadoServidor.setText("Esperando ...");
        serverConexion = new ServerConexionII(catalogo, estadoServidor);
        serverConexion.execute();

    }//GEN-LAST:event_startButtonActionPerformed

    @Override
    public void updateOnCatalog() {
        ((TableModelProducto) jTable1.getModel()).insertedRow(catalogo.getProductos().size() - 1);
        updateTotalProducts();

    }

    Action delete = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {

            JTable table = (JTable) e.getSource();
            int modelRow = Integer.valueOf(e.getActionCommand());

            int showConfirmDialog = JOptionPane.showConfirmDialog(null, "Estas seguro que deseas eliminar '"
                    + catalogo.getProductos().get(modelRow).getNombre() + "'.", "Eliminando producto", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (JOptionPane.OK_OPTION == showConfirmDialog) {
                catalogo.deleteProduct(modelRow);
                ((TableModelProducto) table.getModel()).removeRow(modelRow);
                updateTotalProducts();
            }
        }
    };

    Action edit = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JTable table = (JTable) e.getSource();
            int modelRow = Integer.valueOf(e.getActionCommand());
            new ProductoNuevo(catalogo, modelRow).setVisible(true);
        }
    };

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ServidorVentana().setVisible(true);
                
                
            }
        });
    }

    private void updateTotalProducts() {
        totalProductos.setText(String.valueOf(catalogo.getProductos().size()));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JLabel estadoServidor;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton startButton;
    private javax.swing.JLabel totalProductos;
    private com.alee.extended.statusbar.WebStatusBar webStatusBar1;
    // End of variables declaration//GEN-END:variables

}
