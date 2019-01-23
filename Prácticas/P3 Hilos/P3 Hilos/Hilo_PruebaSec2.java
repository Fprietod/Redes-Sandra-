public class Hilo_PruebaSec2{
	public static void main(String[] args) {
		Hilo_Secuencia2 hilo2=new Hilo_Secuencia2(0,0);

		try{
			hilo2.start();
			Thread.sleep(3000);
			for(int i=1; i<=10; i++){
				System.out.println("Interrupcion "+ (i) );
            	hilo2.setInt(true);
            	Thread.sleep(3000);
            	//System.out.println("valor del numero: "+ hilo2.getNum() );
            	//System.out.println("valor de letra: "+ hilo2.getLetra() );
            	//Thread.sleep(3000);
            	//hilo2.setInt(true);
            	//System.out.println("Interrupcion "+ (i+2) );
            	//System.out.println("valor del numero: "+ hilo2.getNum() );
            	//System.out.println("valor de letra: "+ hilo2.getLetra() );
			}
			System.out.println("Fin del Programa");
			hilo2.setBFin(true);
        } 
        catch (InterruptedException e) {
            System.err.println("Error en metodo1a " + e);
        }
	}
}
