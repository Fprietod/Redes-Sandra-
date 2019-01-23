package Servidor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.*;
import java.io.*;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class UIServidor extends javax.swing.JFrame {
    private int puerto = 9000;
    private Socket sc;
    private ServerSocket serversc;
    private DataOutputStream salida;
    private BufferedReader entrada;

    private BarThread barT;
    
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
        recibirNombres.setVisible(false);
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
        DataInputStream in= null;   //variable de entrada de datos
        
        int bytesRcvd     = 0;      //variable para numero de bytes recibidos
        byte[] byteBuffer = null;   
        String fileName   = null;   //variable para almacenar nombre del archivo
        long fileSize     = 0;      //variable para tamanio del archivo
        long count        = 0;      //variable para el tamanio de bytes recibidos
        int numFiles      = 0;      //variable de numero de archivos
        float porcentaje  = 0;
        try{
            byteBuffer = new byte[4096];        //tamaño maximo del buffer (4 Gb)
            serversc   = new ServerSocket(pto); //crea el socket del servidor en el puerto 5000
            sc = new Socket();                    
            sc = serversc.accept();
            in = new DataInputStream(sc.getInputStream()); // lectura del flujo de datos
            numFiles = in.readInt();                       // guarda el numero de archivos que se estan enviando
            in.close();                                    //se cierran el inputstream
            sc.close();
            textAreaServer.append("Numero de archivos: " + numFiles + ". \n");
            textAreaServer.append("*******************************************************************************************\n");
            porcentaje=(100/(numFiles) );

            for (int j = 0; j < numFiles; j++){
                sc = serversc.accept();
                in = new DataInputStream(sc.getInputStream());
                fileName = in.readUTF();
                fileSize = in.readLong();
                System.out.println("Extension:"+getExt(fileName));
                textAreaServer.append("Archivo a Recibir:\n\tNombre: " 
                                    +fileName + "\n\tExtension: " + getExt(fileName)+ "\n\tTamaño: " +fileSize+ " Bytes.\n");
                in.close();
            }
            textAreaServer.append("*******************************************************************************************\n");
            sc.close();
            serversc.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }    
    public String getExt(String n){
        String s="";
        for(int i=n.length()-1;i>=0;i--){
            if(n.charAt(i)=='.'){
                s=n.substring(i,n.length());
                break;
            }
        }
        return s;
    }
    public void recibirArchivos(int pto){
        DataInputStream in= null; //variable de entrada de datos
        OutputStream  out = null; //variable de salida de datos
        int bytesRcvd     = 0;      //variable para numero de bytes recibidos
        byte[] byteBuffer = null;   
        String fileName   = null;   //variable para almacenar nombre del archivo
        long fileSize     = 0;      //variable para tamanio del archivo
        long count        = 0;      //variable para el tamanio de bytes recibidos
        int numFiles      = 0;      //variable de numero de archivos
        float porcentaje  = 0;
        int progreso=0;
        
        try{
            long cuenta=0;
            byteBuffer = new byte[4096];                  //tamaño maximo del buffer (4 Gb)
            serversc   = new ServerSocket(pto);           //crea el socket del servidor en el puerto 5000
            sc = new Socket();                    
            sc = serversc.accept();
            in = new DataInputStream(sc.getInputStream());// lectura del flujo de datos
            numFiles = in.readInt();                      // guarda el numero de archivos que se estan enviando
            in.close();                                   //se cierran el inputstream
            sc.close();
            textAreaServer.append("*******************************************************************************************\n");            
            porcentaje=(100/(numFiles) );
            for (int j = 0; j < numFiles; j++){
                sc = serversc.accept();
                in = new DataInputStream(sc.getInputStream());
                fileName = in.readUTF();
                fileSize = in.readLong();
                textAreaServer.append("Recibiendo archivo:\n\tNombre: " +fileName+ "\n\tTamaño: " +fileSize+ " Bytes.\n");
                System.out.println("Recibiendo archivo:\n\tNombre: " +fileName+ "\n\tTamaño: " +fileSize+ " Bytes.\n");
                out = new FileOutputStream(fileName);
                textAreaServer.append("Archivo recibido:\n\t" + fileName + " de " + fileSize + " Bytes.\n\n");
                /* Recibe los bytes del cliente y los escribe en un nuevo archivo */
                progreso+=porcentaje;
                textAreaServer.append("Progreso:\n");
                barT.setValue(progreso);
                recibirDatos(progreso, bytesRcvd, count, in, out,byteBuffer);
                
                out.close();
                in.close();
                cuenta+=cuenta+fileSize;
            }
            barT.setValue(100);
            barT.setValue(100);
            System.out.println("BytesF"+cuenta);
            System.out.println("porcentaje:"+progreso);
            textAreaServer.append("------------------------------------------------------------------------------------------\n");
            textAreaServer.append("Se recibieron: " + cuenta + " bytes.\n");
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
            //Thread.sleep(1000);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        textAreaServer.append("\t"+progreso+"%\n");
        System.out.println("porcentaje:"+progreso);
        System.out.println("Bytes:"+count);
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
        recibirNombres = new javax.swing.JButton();
        recibirMas = new javax.swing.JButton();
        jPuerto = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        recibirArchivos = new javax.swing.JButton();

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

        recibirNombres.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        recibirNombres.setText("Recibir Nombres");
        recibirNombres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recibirNombresActionPerformed(evt);
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

        jPuerto.setText("5000");

        jButton1.setText("Inciar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Puerto");

        recibirArchivos.setText("Recibir Archivos");
        recibirArchivos.setEnabled(false);
        recibirArchivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recibirArchivosActionPerformed(evt);
            }
        });

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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(recibirNombres)
                                .addGap(164, 164, 164)
                                .addComponent(recibirMas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 601, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(20, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 601, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(barraProgresoServidor, javax.swing.GroupLayout.PREFERRED_SIZE, 601, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(recibirArchivos, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(recibirNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPuerto, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)
                        .addGap(158, 158, 158))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(recibirMas, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                        .addComponent(recibirArchivos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
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
        recibirNombres.setVisible(true);
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
    private void recibirNombresActionPerformed(java.awt.event.ActionEvent evt){//GEN-FIRST:event_recibirNombresActionPerformed
        barraProgresoServidor.setValue(0);
        recibirNombres(Integer.parseInt(jPuerto.getText()));
        recibirNombres.setEnabled(false);
        recibirArchivos.setEnabled(true);
        recibirMas.setEnabled(false);
    }//GEN-LAST:event_recibirNombresActionPerformed

    private void recibirMasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recibirMasActionPerformed
        barraProgresoServidor.setValue(barraProgresoServidor.getMinimum());
        recibirNombres.setEnabled(true);
        recibirMas.setEnabled(false);
    }//GEN-LAST:event_recibirMasActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        iniciarSocketServer.setEnabled(true);
        jButton1.setEnabled(false);
        jPuerto.setEnabled(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void recibirArchivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recibirArchivosActionPerformed
        barT=new BarThread(barraProgresoServidor);
        barT.start();
        recibirArchivos(Integer.parseInt(jPuerto.getText()));
        recibirArchivos.setEnabled(false);
        recibirMas.setEnabled(true);
    }//GEN-LAST:event_recibirArchivosActionPerformed

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
    private javax.swing.JButton recibirNombres;
    private javax.swing.JTextArea textAreaServer;
    // End of variables declaration//GEN-END:variables
}

