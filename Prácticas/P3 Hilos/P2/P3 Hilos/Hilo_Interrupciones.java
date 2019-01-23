public class Hilo_Interrupciones extends Thread{
	private Hilo_Secuencia1 hilo1;
	private Hilo_Secuencia2 hilo2;
	Hilo_Interrupciones(Hilo_Secuencia1 hilo1,Hilo_Secuencia2 hilo2){
		this.hilo1=hilo1;
		this.hilo2=hilo2;
	}
	public Hilo_Secuencia1 getH1(){
		return this.hilo1;
	}
	public Hilo_Secuencia2 getH2(){
		return this.hilo2;
	}
	@Override
    public void run(){
    	int j=1;
        int k=1;
		try {
			getH1().start();
			getH2().start();
			 
			while (j!=11 && k!=11) {
				esperarX(2);
				j=metodo1a(j);		//h1
				esperarX(1);
				k=metodo1b(k); 		//h2
				esperarX(1);
				j=metodo1a(j);		//h1
				esperarX(2);
				j=metodo1a(j);		//h1
				k=metodo1b(k); 		//h2
            }
            System.out.println("FIN DE INTERRUPCIONES");
            getH1().setBFin(true);
            //esperarX(3); //<--IMPORTANTE           
            getH2().setBFin(true);
        } 
        catch (Exception e) {
            System.err.println("Error en metodo1a " + e);
        }
	}

	private int metodo1a(int aa) {
		//System.out.println("1A="+aa);
		int xx=aa;
        try {
        	if(xx<10){
        		System.out.println("Interrupcion "+ (xx) +" H1");
            	getH1().setInt(true);
        	}
        	else if(xx==10){
        		System.out.println("Interrupcion "+ (xx) +" H1");
        		getH1().setBFin(true);
        	}
            else{

            }
        	xx++;
            //Thread.sleep(2000);
        } catch (Exception e) {
            System.err.println("Error en metodo1a " + e);
            //log.error("Error en el metodo 1a");
        }
        return xx;
    }
    private int metodo1b(int a) {
    	//System.out.println("1B="+a);
    	int x=a;
        try {
        	if(x<11){
        		System.out.println("Interrupcion "+ (x) +" H2");
            	getH2().setInt(true);
				x++;
        	}
        	else{
        		getH2().setBFin(true);
        	}
            //Thread.sleep(3000);
        } catch (Exception e) {
            System.err.println("Error en metodo1b " + e);
            //log.error("Error en el metodo 1b");
        }
        return x;
    }

    private void esperarX(int s) {
		try {
			Thread.sleep(s*1000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}
}
