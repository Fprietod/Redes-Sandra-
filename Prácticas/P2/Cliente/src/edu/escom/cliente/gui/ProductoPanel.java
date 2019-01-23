package edu.escom.cliente.gui;

import com.alee.extended.image.WebImage;
import com.alee.extended.layout.VerticalFlowLayout;
import com.alee.extended.panel.GroupPanel;
import com.alee.extended.panel.GroupingType;
import com.alee.extended.window.WebPopOver;
import com.alee.laf.WebLookAndFeel;
import com.alee.laf.button.WebButton;
import com.alee.laf.label.WebLabel;
import edu.escom.data.Producto;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class ProductoPanel extends JPanel {

    private File path;
    private JLabel label;
    private Producto producto;
    private List<Producto> carrito;
    WebPopOver popOver;

    public ProductoPanel(Producto producto, File path, List<Producto> carrito, JLabel label) {
        this.path = path;
        this.carrito = carrito;
        this.producto = producto;
        this.label = label;
        initComponents();

        setBorder(new LineBorder(Color.GRAY, 1, true));
        setBackground(new Color(238,238,238));
        
        if (producto.getDisponibles() <= 0) {
            boton.setEnabled(false);
            boton.setText("Agotados");
            boton.setBackground(Color.red);
        }
        nombre.setText(producto.getNombre());
        precioDisponibles.setText("PRECIO: " + producto.getPrecio() + " | DISPONIBLES: " + producto.getDisponibles());

        BufferedImage img = null;
        try {
                 img = ImageIO.read(new File(path.getAbsolutePath() + '\\' + producto.getImagen().getName()));
                 Image imagencita = ImageIO.read(producto.getImagen());
                imagen.setIcon((Icon) imagencita);
        } catch (IOException ex) {
            System.out.println("cosa");
            Logger.getLogger(ProductoPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Image scaledInstance = img.getScaledInstance(200, 200, 200);
        //ImageIcon imageIcon = new ImageIcon(scaledInstance);
        

        //imagen.setIcon(new javax.swing.ImageIcon(getClass().getResource(producto.getImagen())));

        //imagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/escom/cliente/gui/resources/Modificar.png")));

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imagen = new javax.swing.JLabel();
        nombre = new javax.swing.JLabel();
        precioDisponibles = new javax.swing.JLabel();
        boton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(230, 420));

        imagen.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        imagen.setMaximumSize(new java.awt.Dimension(200, 200));

        nombre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nombre.setText("Nombre");

        precioDisponibles.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        precioDisponibles.setText("Precio : 23 | Disponibles : 1");

        boton.setText("Agregar al carrito");
        boton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonActionPerformed(evt);
            }
        });

        jButton1.setText("Más información");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(boton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(imagen, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(nombre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(precioDisponibles, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imagen, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nombre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(precioDisponibles)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boton)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void botonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonActionPerformed
        boton.setEnabled(false);
        boton.setText("Agregado");
        carrito.add(producto);
        label.setText(String.valueOf(carrito.size()));
    }//GEN-LAST:event_botonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        popOver = new WebPopOver(this);
        popOver.setCloseOnFocusLoss(true);
        popOver.setMargin(10);
        popOver.setLayout(new VerticalFlowLayout());
        final WebImage icon = new WebImage(WebLookAndFeel.getIcon(16));
        final WebLabel titleLabel = new WebLabel("Carácteristicas", WebLabel.CENTER);
        final WebButton closeButton = new WebButton(new javax.swing.ImageIcon(getClass().getResource("/edu/escom/cliente/gui/resources/cross_small.png")), new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                popOver.dispose();
            }
        }).setUndecorated(true);
        popOver.add(new GroupPanel(GroupingType.fillMiddle, 4, icon, titleLabel, closeButton).setMargin(0, 0, 10, 0));
        popOver.add(new WebLabel("TIEMPO ENTREGA: " + producto.getTiempo()));
        popOver.add(new WebLabel("COLORES: " + producto.getColores()));

        String[] split = producto.getDescripcion().split("\n");
        for (String split1 : split) {
            popOver.add(new WebLabel(split1));
        }
        popOver.show(imagen);

    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton boton;
    private javax.swing.JLabel imagen;
    private javax.swing.JButton jButton1;
    public javax.swing.JLabel nombre;
    public javax.swing.JLabel precioDisponibles;
    // End of variables declaration//GEN-END:variables
}
