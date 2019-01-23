public class Cliente extends Conexion{
    public Cliente() throws IOException{super("cliente");} 
    /* Metodo de inizializacion del cliente */
    public void startClient() {
        try{            
            /*Flujo de datos hacia el servidor*/
            salidaServidor = new DataOutputStream(cs.getOutputStream());
            /*Se escribe en el servidor usando su flujo de datos*/
            salidaServidor.writeUTF(" Hola ");   
            cs.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}