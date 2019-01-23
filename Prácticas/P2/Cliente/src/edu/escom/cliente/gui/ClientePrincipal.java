package edu.escom.cliente.gui;

import edu.escom.cliente.sockets.ClienteConexion;
import edu.escom.data.Producto;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ClientePrincipal extends javax.swing.JFrame {

    private File path;  //ruta de donde se guardarán las imágenes y el ticket
    private String ip;  //ip del servidor
    private ClienteConexion conexion;
    private List<Producto> productos;   //Lista de Productos
    private List<Producto> carrito;     //Carrito con lista de productos
    private GridLayout gd;

    public ClientePrincipal(File path, String ip) {
        this.path = path;
        this.ip = ip;
        carrito = new ArrayList();
        try {
            conexion = new ClienteConexion(ip, path); //clase que hace la comunicación entre cliente y servidor
            productos = conexion.conectar(); //se obtiene la lista de productos
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex.getStackTrace());
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "No se pudo conectar al servidor de la tienda, intentelo más tarde. " + ex, "Error de conexión", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        gd = new GridLayout(0, 3, 10, 10);
        initComponents();

        panelProductos.setLayout(gd);
        panelProductos.setBackground(Color.WHITE);

        agregarProductos();
        jLabel5.setText(String.valueOf(productos.size()));
        setLocationRelativeTo(null);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                try {
                    conexion.cerrar();
                } catch (IOException ex) {
                    Logger.getLogger(ClientePrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.exit(0);
            }
        });
        this.setTitle("CLIENTE");

    }

    private void agregarProductos() {
        for (Producto producto : productos) {
            Component add = panelProductos.add(new ProductoPanel(producto, path, carrito, jLabel3));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        panelProductos = new javax.swing.JPanel();
        webStatusBar1 = new com.alee.extended.statusbar.WebStatusBar();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new com.alee.extended.label.WebHotkeyLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("ESCOMarket");
        setResizable(false);

        javax.swing.GroupLayout panelProductosLayout = new javax.swing.GroupLayout(panelProductos);
        panelProductos.setLayout(panelProductosLayout);
        panelProductosLayout.setHorizontalGroup(
            panelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 739, Short.MAX_VALUE)
        );
        panelProductosLayout.setVerticalGroup(
            panelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 401, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(panelProductos);

        jLabel4.setText("Total de productos en la tienda:");
        webStatusBar1.add(jLabel4);

        jLabel5.setText("0");
        jLabel5.setMaximumSize(new java.awt.Dimension(100, 14));
        jLabel5.setMinimumSize(new java.awt.Dimension(100, 14));
        webStatusBar1.add(jLabel5);

        jButton1.setText("Ver Carrito");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setText("0");
        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel1.setText("Número de artículos seleccionados: ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(webStatusBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(webStatusBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new Carrito(carrito, jLabel3, panelProductos, path, conexion).setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private com.alee.extended.label.WebHotkeyLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelProductos;
    private com.alee.extended.statusbar.WebStatusBar webStatusBar1;
    // End of variables declaration//GEN-END:variables

}
