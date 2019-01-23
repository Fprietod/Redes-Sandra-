public class Main{
	public static void main(String[] args) {
		Primos primo=new Primos(1,'A');
		int n=0;
		try {
			primo.start();
			for(int i=1; i<=2; i++){
				Thread.sleep(3000);
            	primo.setInt(true);
            	System.out.println("valor del numero: "+ primo.getNum() );
            	System.out.println("valor de letra: "+ primo.getLetra() );
            	Thread.sleep(3000);
            	primo.setInt(true);
            	System.out.println("valor del numero: "+ primo.getNum() );
            	System.out.println("valor de letra: "+ primo.getLetra() );
			}
			System.out.println("Salio del for MAIN");
			primo.setBFin(true);
        } catch (InterruptedException e) {
            System.err.println("Error en metodo1a " + e);
        }
	}
}