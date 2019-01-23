package Servidor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.Timer;

public class UIServidor extends javax.swing.JFrame {
    private int puerto = 5000;
    private Socket sc;
    private ServerSocket serversc;
    private DataOutputStream salida;
    private BufferedReader entrada;

    public void setPuerto(int puerto){
        this.puerto=puerto;
    }
    public UIServidor() {
        initComponents(); 
        ocultarHerramientas();
    }
    
    public class progreso implements ActionListener{
        public void actionPerformed(ActionEvent evt ){
            int n=barraProgresoServidor.getValue();
            try{
                if(n<100){
                   n++;
                   barraProgresoServidor.setValue(n);
               }
               else{
                   timer.stop();
                   //barraProgresoServidor.setValue(0);
                   JOptionPane.showMessageDialog(null,"Acabó el progreso");
               }   
            }
            catch (Exception e){
                e.printStackTrace();
            }
 
        }
    }
    /*Método para ocultar la parte de recibir archivos en el servidor */
    public void ocultarHerramientas(){
        recibirArchivos.setVisible(false);
        recibirMas.setVisible(false);
    }
     /*Método para correr el cliente */
    public void initServer(){    
        textAreaServer.setText("");
        try{
            setPuerto( Integer.parseInt(jPuerto.getText()) );
            serversc = new ServerSocket(puerto);/* crea socket servidor que escuchara en puerto 5000*/
            sc=new Socket();
            textAreaServer.append("Servidor: Iniciando socket... \n");
            
            sc = serversc.accept();
            salida = new DataOutputStream(sc.getOutputStream());
            
            //Inicia el socket, ahora esta esperando una conexión por parte del cliente
            salida.writeUTF("Servidor: Petición recibida y aceptada. \n");
            textAreaServer.append("Servidor: Se ha conectado un cliente. \n");
            entrada = new BufferedReader(new InputStreamReader(sc.getInputStream()));           
            textAreaServer.append("Servidor: Enviando confirmación de conexion al cliente.... \n");
            salida.writeUTF("Servidor: Conexión exitosa. \n");
            salida.writeUTF("Servidor: Envio de archivos habilitado\n");
            textAreaServer.append(entrada.readLine() + "\n");
            textAreaServer.append("Servidor: Esperando archivos del cliente... \n"); 
            sc.close();
            serversc.close();
        }
        //Mensaje de error
        catch(Exception e ){
            JOptionPane.showMessageDialog( null, "Error: " + e.getMessage() );
            System.out.println(e.getMessage());
        }
    }

    public void recibirNombres(int pto){
        DataInputStream in  = null; //variable de entrada de datos
        OutputStream    out = null; //variable de salida de datos
        
        int bytesRcvd =0;              //variable para numero de bytes recibidos
        byte[] byteBuffer = null;   
        String fileName   = null;   //variable para almacenar nombre del archivo
        long fileSize     = 0;      //variable para tamanio del archivo
        long count        = 0;      //variable para el tamanio de bytes recibidos
        int numFiles      = 0;      //variable de numero de archivos
        float porcentaje    = 0;
        int progreso=0;
        try{
            byteBuffer = new byte[4096];        //tamaño maximo del buffer (4 Gb)
            serversc = new ServerSocket(pto);//crea el socket del servidor en el puerto 5000
            sc=new Socket();                    
            /* Recibe el numero de archivos que el cliente quiere enviar */
            sc = serversc.accept();
            in = new DataInputStream(sc.getInputStream()); // lectura del flujo de datos
            numFiles = in.readInt();                       // guarda el numero de archivos que se estan enviando
            in.close();                                    //se cierran el inputstream
            
            //model.addRow(new Object[]{}(" * El servidor esta listo para recibir " + numFiles + " archivo(s)"), BorderLayout.NORTH);
            textAreaServer.append("Numero de archivos: " + numFiles + ". \n");
            textAreaServer.append("*******************************************************************************************\n");
            /* Espera a que el cliente envie un archivo, lo recibe y lo guarda dentro de la carpeta del proyecto */
            porcentaje=(100/(numFiles) );

            for (int j = 0; j < numFiles; j++){
                sc = serversc.accept();
                in = new DataInputStream(sc.getInputStream());
                /* Recibe el nombre del archivo y su tamaño */
                fileName = in.readUTF();
                fileSize = in.readLong();
                textAreaServer.append("Archivos a Recibir:\n\tNombre: " +fileName+ "\n\tTamaño: " +fileSize+ " Bytes.\n");
                out = new FileOutputStream(fileName);
                textAreaServer.append("Archivo recibido:\n\t" + fileName + " de " + fileSize + " Bytes.\n\n");
                /* Recibe los bytes del cliente y los escribe en un nuevo archivo */
                progreso+=porcentaje;
                //Thread.sleep(1500);
                //textAreaServer.append("Progreso:\n");
                //recibirDatos(progreso, bytesRcvd, count, in, out,byteBuffer);
                out.close();
                in.close();
            }
            
            //System.out.println("porcentaje:"+progreso);
            textAreaServer.append("------------------------------------------------------------------------------------------\n");
            //textAreaServer.append("Se recibieron: " + count + " bytes.\n");
            textAreaServer.append("*******************************************************************************************\n\n");
            sc.close();
            serversc.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }    
    public void recibirArchivos(int pto){
        DataInputStream in  = null; //variable de entrada de datos
        OutputStream    out = null; //variable de salida de datos
        
        int bytesRcvd =0;              //variable para numero de bytes recibidos
        byte[] byteBuffer = null;   
        String fileName   = null;   //variable para almacenar nombre del archivo
        long fileSize     = 0;      //variable para tamanio del archivo
        long count        = 0;      //variable para el tamanio de bytes recibidos
        int numFiles      = 0;      //variable de numero de archivos
        float porcentaje    = 0;
        int progreso=0;
        try{
            byteBuffer = new byte[4096];        //tamaño maximo del buffer (4 Gb)
            serversc = new ServerSocket(pto);//crea el socket del servidor en el puerto 5000
            sc=new Socket();                    
            /* Recibe el numero de archivos que el cliente quiere enviar */
            sc = serversc.accept();
            in = new DataInputStream(sc.getInputStream()); // lectura del flujo de datos
            numFiles = in.readInt();                       // guarda el numero de archivos que se estan enviando
            in.close();                                    //se cierran el inputstream
            
            //model.addRow(new Object[]{}(" * El servidor esta listo para recibir " + numFiles + " archivo(s)"), BorderLayout.NORTH);
            //textAreaServer.append("Numero de archivos: " + numFiles + ". \n");
            textAreaServer.append("*******************************************************************************************\n");
            /* Espera a que el cliente envie un archivo, lo recibe y lo guarda dentro de la carpeta del proyecto */
            porcentaje=(100/(numFiles) );

            for (int j = 0; j < numFiles; j++){
                sc = serversc.accept();
                in = new DataInputStream(sc.getInputStream());
                /* Recibe el nombre del archivo y su tamaño */
                fileName = in.readUTF();
                fileSize = in.readLong();
                textAreaServer.append("Recibiendo archivo:\n\tNombre: " +fileName+ "\n\tTamaño: " +fileSize+ " Bytes.\n");
                out = new FileOutputStream(fileName);
                textAreaServer.append("Archivo recibido:\n\t" + fileName + " de " + fileSize + " Bytes.\n\n");
                /* Recibe los bytes del cliente y los escribe en un nuevo archivo */
                progreso+=porcentaje;
                //Thread.sleep(1500);
                textAreaServer.append("Progreso:\n");
                recibirDatos(progreso, bytesRcvd, count, in, out,byteBuffer);
                
                out.close();
                in.close();
            }
            
            System.out.println("porcentaje:"+progreso);
            textAreaServer.append("------------------------------------------------------------------------------------------\n");
            textAreaServer.append("Se recibieron: " + count + " bytes.\n");
            textAreaServer.append("*******************************************************************************************\n\n");
            sc.close();
            serversc.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void recibirDatos(int progreso,int bytesRcvd,long count,DataInputStream in,OutputStream out,byte[] byteBuffer){
        try{
            while ( ( bytesRcvd = in.read(byteBuffer) ) != -1) {
                        out.write(byteBuffer, 0, bytesRcvd);
                        count += bytesRcvd;
            }
            Thread.sleep(1000);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        //cambiaBarra(progreso);
        textAreaServer.append("\t"+progreso+"%\n");
        System.out.println("porcentaje:"+progreso);
    }
    public void cambiaBarra(int progreso){
        barraProgresoServidor.setStringPainted(true);
        barraProgresoServidor.setValue(progreso);
        barraProgresoServidor.setStringPainted(true);
    }
    /*NetBeans*/
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textAreaServer = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        iniciarSocketServer = new javax.swing.JButton();
        barraProgresoServidor = new javax.swing.JProgressBar();
        cerrarSocket = new javax.swing.JButton();
        recibirArchivos = new javax.swing.JButton();
        recibirMas = new javax.swing.JButton();
        jPuerto = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Interfáz Servidor");

        jLabel1.setBackground(new java.awt.Color(153, 153, 153));
        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 51));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Servidor");
        jLabel1.setOpaque(true);

        textAreaServer.setColumns(20);
        textAreaServer.setRows(5);
        jScrollPane1.setViewportView(textAreaServer);
        textAreaServer.getAccessibleContext().setAccessibleName("");
        textAreaServer.getAccessibleContext().setAccessibleDescription("");

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel2.setText("Servidor se inicia y espera conexión de un cliente.");

        iniciarSocketServer.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        iniciarSocketServer.setText("Iniciar Socket de servidor");
        iniciarSocketServer.setToolTipText("");
        iniciarSocketServer.setEnabled(false);
        iniciarSocketServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iniciarSocketServerActionPerformed(evt);
            }
        });

        barraProgresoServidor.setBackground(new java.awt.Color(255, 255, 255));
        barraProgresoServidor.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        barraProgresoServidor.setForeground(new java.awt.Color(0, 0, 0));
        barraProgresoServidor.setOpaque(true);
        barraProgresoServidor.setStringPainted(true);

        cerrarSocket.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        cerrarSocket.setText("Cerrar Conexion");
        cerrarSocket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerrarSocketActionPerformed(evt);
            }
        });

        recibirArchivos.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        recibirArchivos.setText("Recibir Archivos");
        recibirArchivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recibirArchivosActionPerformed(evt);
            }
        });

        recibirMas.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        recibirMas.setText("Volver a Recibir");
        recibirMas.setEnabled(false);
        recibirMas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recibirMasActionPerformed(evt);
            }
        });

        jButton1.setText("Inciar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Puerto");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(cerrarSocket, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(208, 208, 208))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                            .addComponent(jPuerto, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 601, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(recibirArchivos)
                                .addGap(131, 131, 131)
                                .addComponent(recibirMas, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                                .addGap(35, 35, 35)))
                        .addContainerGap(23, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 601, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(barraProgresoServidor, javax.swing.GroupLayout.PREFERRED_SIZE, 601, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(iniciarSocketServer)
                        .addGap(165, 165, 165))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(97, 97, 97))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(iniciarSocketServer, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(recibirMas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(recibirArchivos))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPuerto, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(barraProgresoServidor, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cerrarSocket)
                .addGap(16, 16, 16))
        );

        barraProgresoServidor.setMaximum(100);
        barraProgresoServidor.setMinimum(0);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /*Muestra la información de recibir archivos*/
    private void iniciarSocketServerActionPerformed(java.awt.event.ActionEvent evt){//GEN-FIRST:event_iniciarSocketServerActionPerformed
        textAreaServer.append("Esperando una conexión: \n");
        initServer();
        recibirArchivos.setVisible(true);
        recibirMas.setVisible(true);
    }//GEN-LAST:event_iniciarSocketServerActionPerformed

    /*Metodo para cerrar la conexión entre el servidor y el cliente*/
    private void cerrarSocketActionPerformed(java.awt.event.ActionEvent evt){//GEN-FIRST:event_cerrarSocketActionPerformed
        textAreaServer.setText("");
        try{
            sc.close();
            textAreaServer.append("Cerrando conexión... \n");
            serversc.close();
            textAreaServer.append("Conexión cerrada. \n");
            ocultarHerramientas();
        }
        /*Mensaje de Error*/
        catch (IOException e){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        barraProgresoServidor.setValue(barraProgresoServidor.getMinimum());
    }//GEN-LAST:event_cerrarSocketActionPerformed

    /*Metodo para recepción de archivos*/
    private void recibirArchivosActionPerformed(java.awt.event.ActionEvent evt){//GEN-FIRST:event_recibirArchivosActionPerformed
        timer=new Timer(10,new progreso());
        timer.start();
        recibirNombres(Integer.parseInt(jPuerto.getText()));
        recibirArchivos(Integer.parseInt(jPuerto.getText()));
        recibirArchivos.setEnabled(false);
        recibirMas.setEnabled(true);
    }//GEN-LAST:event_recibirArchivosActionPerformed

    private void recibirMasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recibirMasActionPerformed
        barraProgresoServidor.setValue(barraProgresoServidor.getMinimum());
        recibirArchivos.setEnabled(true);
        recibirMas.setEnabled(false);
    }//GEN-LAST:event_recibirMasActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        iniciarSocketServer.setEnabled(true);
        jButton1.setEnabled(false);
        jPuerto.setEnabled(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    /*Netbeans*/
    public static void main(String args[]){
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try{
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()){
                if ("Nimbus".equals(info.getName())){
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }catch (ClassNotFoundException ex){
            java.util.logging.Logger.getLogger(UIServidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex){
            java.util.logging.Logger.getLogger(UIServidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex){
            java.util.logging.Logger.getLogger(UIServidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex){
            java.util.logging.Logger.getLogger(UIServidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Crea y muesra el formulario NB */
        java.awt.EventQueue.invokeLater(new Runnable(){
            public void run(){
                new UIServidor().setVisible(true);
            }
        });
    }
    
    private Timer timer;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar barraProgresoServidor;
    private javax.swing.JButton cerrarSocket;
    private javax.swing.JButton iniciarSocketServer;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField jPuerto;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton recibirArchivos;
    private javax.swing.JButton recibirMas;
    private javax.swing.JTextArea textAreaServer;
    // End of variables declaration//GEN-END:variables
}

