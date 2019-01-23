/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class UICliente extends javax.swing.JFrame {   
    private String host = "192.168.0.2"; //ip del Servidor 192.168.0.2
    private int puerto = 5000;
    private Socket sock;
    private DataOutputStream salida;
    private BufferedInputStream bis;
    private BufferedReader entrada;
    private BufferedOutputStream bos;
    private String mensajeRecibido;
    private File[] files;
    private int countArchivos;
    
    public void setHost(String host){
        this.host=host;
    }
    public void setPuerto(int puerto){
        this.puerto=puerto;
    }
    public UICliente() {
        initComponents();
        ocultarHerramientas();    
    }
    public void mostrarHerramientas(){
        escogerArchivos.setVisible(true);
        lblArchivosSelecionados.setVisible(true);
        lblElegir.setVisible(true);
        TextAreaCliente.setVisible(true);
        enviarArchivos.setVisible(true);
        archivosSelecionados.setVisible(true);
    }
    public void ocultarHerramientas(){
        escogerArchivos.setVisible(false);
        lblArchivosSelecionados.setVisible(false);
        lblElegir.setVisible(false);
        TextAreaCliente.setVisible(false);
        enviarArchivos.setVisible(false);
        archivosSelecionados.setVisible(false);
    }
     /*ejecuta este metodo para correr el cliente */
    public void initClient(){
            setHost( jHost.getText() ); //ip del Servidor 192.168.0.2
            setPuerto(Integer.parseInt(jPuerto.getText()) );
        try{
            /*conectando a un servidor en localhost con puerto 5000*/
            sock = new Socket(host, puerto); 
            TextAreaCliente.append("Cliente: Iniciando socket... \n");
            mostrarHerramientas();
            salida = new DataOutputStream(sock.getOutputStream());
            entrada = new BufferedReader(new InputStreamReader(sock.getInputStream())); //lee mensaje del servidor
            mensajeRecibido = entrada.readLine();
            TextAreaCliente.append(mensajeRecibido + "\n");
            mensajeRecibido = entrada.readLine();
            TextAreaCliente.append(mensajeRecibido + "\n");
            salida.writeUTF("Cliente: Confirmación exitosa.\n");
            mensajeRecibido = entrada.readLine();
            TextAreaCliente.append(mensajeRecibido + "\n"); 
            sock.close();
        }
        catch(Exception e ){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TextAreaCliente = new javax.swing.JTextArea();
        lblElegir = new javax.swing.JLabel();
        lblArchivosSelecionados = new javax.swing.JLabel();
        escogerArchivos = new javax.swing.JButton();
        barraProgresoCliente = new javax.swing.JProgressBar();
        archivosSelecionados = new javax.swing.JLabel();
        iniciarConexion = new javax.swing.JButton();
        enviarArchivos = new javax.swing.JButton();
        cerrarSocketC = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jBIniciar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jHost = new javax.swing.JTextField();
        jPuerto = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Interfáz de Usuario");
        setBackground(new java.awt.Color(102, 102, 102));
        setResizable(false);

        jLabel1.setBackground(new java.awt.Color(153, 153, 153));
        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 51));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Pantalla Cliente");
        jLabel1.setOpaque(true);

        TextAreaCliente.setColumns(20);
        TextAreaCliente.setRows(5);
        jScrollPane1.setViewportView(TextAreaCliente);

        lblElegir.setBackground(new java.awt.Color(153, 153, 153));
        lblElegir.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lblElegir.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblElegir.setText("Elegir Archivos");
        lblElegir.setOpaque(true);

        lblArchivosSelecionados.setBackground(new java.awt.Color(153, 153, 153));
        lblArchivosSelecionados.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lblArchivosSelecionados.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblArchivosSelecionados.setText("Archivos seleccionados");
        lblArchivosSelecionados.setOpaque(true);

        escogerArchivos.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        escogerArchivos.setText("Seleccionar Archivos");
        escogerArchivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                escogerArchivosActionPerformed(evt);
            }
        });

        barraProgresoCliente.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        barraProgresoCliente.setForeground(new java.awt.Color(0, 0, 0));

        archivosSelecionados.setBackground(new java.awt.Color(255, 255, 255));
        archivosSelecionados.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        archivosSelecionados.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        archivosSelecionados.setText("0");
        archivosSelecionados.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        iniciarConexion.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        iniciarConexion.setText("Iniciar la Conexión");
        iniciarConexion.setEnabled(false);
        iniciarConexion.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        iniciarConexion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iniciarConexionActionPerformed(evt);
            }
        });

        enviarArchivos.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        enviarArchivos.setText("Enviar Archivos Seleccionados");
        enviarArchivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enviarArchivosActionPerformed(evt);
            }
        });

        cerrarSocketC.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        cerrarSocketC.setText("Cerrar Conexión");
        cerrarSocketC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerrarSocketCActionPerformed(evt);
            }
        });

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Host");

        jBIniciar.setText("Iniciar");
        jBIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBIniciarActionPerformed(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Puerto");

        jHost.setText("localhost");
        jHost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jHostActionPerformed(evt);
            }
        });

        jPuerto.setText("5000");
        jPuerto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPuertoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 68, Short.MAX_VALUE)
                .addComponent(barraProgresoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(112, 112, 112))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(254, 254, 254)
                        .addComponent(iniciarConexion, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(256, 256, 256)
                        .addComponent(cerrarSocketC, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPuerto)
                            .addComponent(jHost)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jBIniciar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblElegir, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                    .addComponent(archivosSelecionados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(escogerArchivos, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblArchivosSelecionados, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(enviarArchivos, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(iniciarConexion, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblArchivosSelecionados, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblElegir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(23, 23, 23)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(archivosSelecionados, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(escogerArchivos, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(87, 87, 87)
                                .addComponent(enviarArchivos, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(barraProgresoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cerrarSocketC, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(152, 152, 152)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jHost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(jPuerto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBIniciar)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void iniciarConexionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iniciarConexionActionPerformed
        TextAreaCliente.setText("");
        initClient();
    }//GEN-LAST:event_iniciarConexionActionPerformed

    private void escogerArchivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_escogerArchivosActionPerformed
        barraProgresoCliente.setValue(0);
        try{
            escogerArchivos();
        } 
        catch (IOException e){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_escogerArchivosActionPerformed
    //Abre el explorador de archivos para seleccionar los elementos a enviar
    public void escogerArchivos() throws IOException{
       JFileChooser fc = new JFileChooser(); //Administrador de archivos
       fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
       if (!fc.isMultiSelectionEnabled()){
          fc.setMultiSelectionEnabled(true); //Habilita la seleccion multiple de archivos
       }
       int returnVal = fc.showOpenDialog(this);
       if (returnVal == JFileChooser.APPROVE_OPTION) {        
            fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            if (!fc.isMultiSelectionEnabled()){
                fc.setMultiSelectionEnabled(true);
            }  
            files = fc.getSelectedFiles(); //Regresa el numero de archivos seleccionados
            countArchivos = files.length;
            archivosSelecionados.setText(Integer.toString(countArchivos));//muestra texto de archivos seleccionados
        }
       TextAreaCliente.append("Archivos seleccionados: " + files.length + "\n");
       for(int i=0; i<files.length;i++){
           TextAreaCliente.append("Archivo: " + files[i].getName() + " Tamaño: " + files[i].length() + " Bytes. \n");
       }
       
    }
    private void cerrarSocketCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrarSocketCActionPerformed
        barraProgresoCliente.setValue(0);
        TextAreaCliente.append("Cerrando conexión... \n");
        try 
        {
            sock.close();
            ocultarHerramientas();
        } 
        catch (IOException e) 
        {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_cerrarSocketCActionPerformed

    private void enviarArchivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enviarArchivosActionPerformed
        //int count=0; //contador para almacenar el num de bytes enviados
        try {
            InputStream in = null; //vsriable de lectura de bytes
            DataOutputStream out = null; //vsriable de escritura de bytes
            sendNumFiles(files);
            TextAreaCliente.append("*******************************************************************************************\n");
            for (int i = 0; i < files.length; i++){
                files[i] = new File(files[i].getAbsolutePath()); //arreglo con la ruta de los archivos
                sendNames(files[i], 4096, out, in);
                TextAreaCliente.append("Archivos PARA LECTURA.\n\n");
            }

            sendNumFiles(files);
            for (int i = 0; i < files.length; i++){
                files[i] = new File(files[i].getAbsolutePath()); //arreglo con la ruta de los archivos
                sendNames(files[i], 4096, out, in);
                TextAreaCliente.append("Archivo enviado.\n\n");
            }
            TextAreaCliente.append("------------------------------------------------------------------------------------------\n");
            //TextAreaCliente.append("Se enviaron " + count + " bytes.\n");
            TextAreaCliente.append("*******************************************************************************************\n\n");
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_enviarArchivosActionPerformed

    private void jHostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jHostActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jHostActionPerformed

    private void jPuertoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPuertoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPuertoActionPerformed

    private void jBIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBIniciarActionPerformed
        iniciarConexion.setEnabled(true);
        jHost.setEnabled(false);
        jPuerto.setEnabled(false);
        jBIniciar.setEnabled(false);
    }//GEN-LAST:event_jBIniciarActionPerformed
    
    
    public int sendNames(File file, int bufferSize, DataOutputStream out, InputStream in){
        int count=0;
        int bytesSent = 0;
        long fileSize = 0;      
        byte[] byteBuffer = new byte[bufferSize];  //tamaño del bufer
        Socket socket;
        float porcentaje=0;float progreso=0;
        barraProgresoCliente.setValue(0);
        try{
            socket  = new Socket(host, puerto);
            out     = new DataOutputStream(socket.getOutputStream());
            in      = new FileInputStream(file);
            
            porcentaje=100/countArchivos;
            
            fileSize = file.length();
            TextAreaCliente.append("Archivo:\n");
            TextAreaCliente.append("\tNombre: " +file.getName() +"\n");
            TextAreaCliente.append("\tTamaño: "+fileSize       +"\n");
            TextAreaCliente.append("Enviando archivo...\n");
            out.writeUTF(file.getName());
            out.writeLong(fileSize);
                          
           //barraProgresoCliente.setStringPainted(true);  
           //TextAreaCliente.append("\tSe enviaron " + fileSize + " bytes.\n");
            count+=fileSize;
            socket.close();
            out.close();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
    public int sendfiles(File file, int bufferSize, DataOutputStream out, InputStream in){
        int count=0;
        int bytesSent = 0;
        long fileSize = 0;      
        byte[] byteBuffer = new byte[bufferSize];  //tamaño del bufer
        Socket socket;
        float porcentaje=0;float progreso=0;
        barraProgresoCliente.setValue(0);
        try{
            socket  = new Socket(host, puerto);
            out     = new DataOutputStream(socket.getOutputStream());
            in      = new FileInputStream(file);
            
            porcentaje=100/countArchivos;
            
            fileSize = file.length();
            TextAreaCliente.append("Archivo:\n");
            TextAreaCliente.append("\tNombre: " +file.getName() +"\n");
            TextAreaCliente.append("\tTamaño: "+fileSize       +"\n");
            TextAreaCliente.append("Enviando archivo...\n");
            out.writeUTF(file.getName());
            out.writeLong(fileSize);
                          
           barraProgresoCliente.setStringPainted(true);  
            while ((bytesSent = in.read(byteBuffer)) > 0){
                out.write(byteBuffer, 0, bytesSent);
                progreso+=porcentaje;
                barraProgresoCliente.setValue((int) progreso);
            }
            TextAreaCliente.append("\tSe enviaron " + fileSize + " bytes.\n");
            count+=fileSize;
            socket.close();
            out.close();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
    public void sendNumFiles(File[] files) throws IOException {
            Socket socket = new Socket(host, puerto);
            DataOutputStream out = null;
            out = new DataOutputStream(socket.getOutputStream());
            out.writeInt(files.length);
            out.close();
            socket.close();

    }
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UICliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UICliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UICliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UICliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UICliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea TextAreaCliente;
    private javax.swing.JLabel archivosSelecionados;
    private javax.swing.JProgressBar barraProgresoCliente;
    private javax.swing.JButton cerrarSocketC;
    private javax.swing.JButton enviarArchivos;
    private javax.swing.JButton escogerArchivos;
    private javax.swing.JButton iniciarConexion;
    private javax.swing.JButton jBIniciar;
    private javax.swing.JTextField jHost;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField jPuerto;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblArchivosSelecionados;
    private javax.swing.JLabel lblElegir;
    // End of variables declaration//GEN-END:variables
}
