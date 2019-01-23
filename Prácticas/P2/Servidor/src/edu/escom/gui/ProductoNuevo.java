package edu.escom.gui;

import edu.escom.data.Producto;
import edu.escom.servidor.backend.Catalogo;
import edu.escom.servidor.backend.ChangesListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class ProductoNuevo extends javax.swing.JFrame {

    List<ChangesListener> listeners = new ArrayList<ChangesListener>();

    private Catalogo catalogo;
    private boolean updating = false;
    private int index = 0;
    private File imagenFile;
    JFileChooser fileChooser = new JFileChooser();

    public ProductoNuevo(Catalogo catalogo) {
        initComponents();
        this.catalogo = catalogo;
        setLocationRelativeTo(null);
        updating = false;
    }

    public ProductoNuevo(Catalogo catalogo, int modelRow) {
        initComponents();
        this.catalogo = catalogo;
        setLocationRelativeTo(null);
        setTitle("Editar producto");
        Producto prod = catalogo.getProductos().get(modelRow);
        nombre.setText(prod.getNombre());
        colores.setText(prod.getColores());
        tiempo.setText(prod.getTiempo());
        precio.setText(String.valueOf(prod.getPrecio()));
        descripcion.setText(prod.getDescripcion());
        updating = true;
        jButton1.setText("Editar");
        index = modelRow;
        disponibles.setText(String.valueOf(prod.getDisponibles()));
        imagen.setText(prod.getImagen().getName());
        imagenFile = prod.getImagen();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        tiempo = new javax.swing.JTextField();
        colores = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        nombre = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        precio = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descripcion = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        disponibles = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        imagen = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("TENIS YEU-OM");

        jLabel13.setText("Tiempo en Llegar:");

        tiempo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tiempoActionPerformed(evt);
            }
        });

        jLabel12.setText("Color:");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Producto Nuevo");

        jLabel2.setText("Nombre:");

        jLabel5.setText("Precio:");

        jLabel6.setText("Descripción:");

        descripcion.setColumns(20);
        descripcion.setRows(5);
        jScrollPane1.setViewportView(descripcion);

        jButton1.setText("Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel7.setText("Disponibles:");

        jLabel8.setText("Imagen");

        imagen.setEnabled(false);

        jButton2.setText("Buscar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setText("Agregar Nuevo Producto");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(22, 22, 22))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nombre)
                            .addComponent(precio)
                            .addComponent(jScrollPane1)
                            .addComponent(disponibles)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel2))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(imagen, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton2))
                            .addComponent(jLabel8))
                        .addContainerGap(48, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel10)
                .addGap(34, 34, 34)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(precio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(disponibles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(imagen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        double precioD = 0.0;
        int dis = 0;

        try {
            precioD = Double.parseDouble(precio.getText());
            dis = Integer.parseInt(disponibles.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Debes introducir un precio/cantidad valido.", "Error creando producto", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean succes = false;

        if (updating) {
            succes = catalogo.updateProducto(index, new Producto(nombre.getText(), colores.getText(), tiempo.getText(), descripcion.getText(),
                    imagenFile, dis, precioD));
        } else {
            succes = catalogo.addProducto(new Producto(nombre.getText(), colores.getText(), tiempo.getText(), descripcion.getText(),
                    imagenFile, dis, precioD));
        }

        if (succes) {
            JOptionPane.showMessageDialog(null, "Producto actualizado.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            updateFired();
        }
        this.dispose();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int resultado = fileChooser.showOpenDialog(null);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            imagenFile = fileChooser.getSelectedFile();
            imagen.setText(imagenFile.getName());
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tiempoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tiempoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tiempoActionPerformed

    private void updateFired() {
        for (ChangesListener hl : listeners) {
            hl.updateOnCatalog();
        }
    }

    public void addListener(ChangesListener toAdd) {
        listeners.add(toAdd);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField colores;
    private javax.swing.JTextArea descripcion;
    private javax.swing.JTextField disponibles;
    private javax.swing.JTextField imagen;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nombre;
    private javax.swing.JTextField precio;
    private javax.swing.JTextField tiempo;
    // End of variables declaration//GEN-END:variables
}
