public class Hilo_Interrupciones{
	public static void main(String[] args) {
		Hilo_Secuencia1 hilo1=new Hilo_Secuencia1(1,'A');
		int n=0;
		try {
			hilo1.start();
			Thread.sleep(3000);
			for(int i=1; i<=10; i++){
				System.out.println("Interrupcion "+ (i) );
            	hilo1.setInt(true);
            	Thread.sleep(3000);
            	//System.out.println("valor del numero: "+ hilo1.getNum() );
            	//System.out.println("valor de letra: "+ hilo1.getLetra() );
            	//Thread.sleep(3000);
            	//hilo1.setInt(true);
            	//System.out.println("Interrupcion "+ (i+2) );
            	//System.out.println("valor del numero: "+ hilo1.getNum() );
            	//System.out.println("valor de letra: "+ hilo1.getLetra() );
			}
			System.out.println("Fin del Programa");
			hilo1.setBFin(true);
        } catch (InterruptedException e) {
            System.err.println("Error " + e);
        }
	}
}
