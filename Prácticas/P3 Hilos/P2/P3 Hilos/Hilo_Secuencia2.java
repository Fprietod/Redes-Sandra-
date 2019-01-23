public class Hilo_Secuencia2 extends Thread{
	private int numero; 	//para saber en qué num empieza la cuenta 
	private int par; 		//para saber en que par empezar
	//banderas
	private boolean interr=false; 	//bandera de interrupción
	private boolean bFin=false;		//bandera de fin

	Hilo_Secuencia2(int numero, int par){
		this.numero=numero;
		this.par=par;
	}
	//getters
	public int getNum(){
		return this.numero;
	}
	public int getPar(){
		return this.par;
	}
	public boolean getInt(){
		return this.interr;
	}
	public boolean getBFin(){
		return this.bFin;
	}
	//setters
	public void setNum(int numero){
		this.numero=numero;
	}
	public void setPar(int par){
		this.par=par;
	}
	public void setInt(boolean interr){
		this.interr=interr;
	}
	public void setBFin(boolean bFin){
		this.bFin=bFin;
	}
	//****************Métodos**********************
	public void pFibo(){
		System.out.println("Sucesion de Fibonacci");
		int x=getNum();
		for(int i=x;i<=1000;i++){	//Los primeros 40 numeros de la sucesión de Fibonacci
			if( getBFin() )		//Bandera de fin del proceso
				break;	
			else if( getInt() )	//hay una interrupción
				break;
			else{
				printFibo(i);
				if(i==1000)
					i=0;
			} 
		} 
	}
	public void printFibo(int n){	//modificar
			setNum(n+1);	//Se guarda el numero siguiente por si hay una interrupción
			System.out.println("Fibo "+n + "\t: "+sFibo(n) );
			esperarX(300);
	}
	public int sFibo(int num){
		int x=0;
        if (num == 0 || num == 1)
            x=num;
        else
        	x = (sFibo(num-2) )+( sFibo(num-1) );
        return x;
	}

	public void pPar(){
		System.out.println("Numeros Pares");
		int p=getPar();
		for(int i=p;i<=1000;i++){	//Primeros 1000 números pares
			if(getBFin()) 			//Bandera de fin del proceso
				break;
			else if( getInt() ){	//hay una interrupción
				setInt(false);
				break;
			}
			else{
				printPares(i);
				if(i==1000)
					i=0;	
			}
		}
	}
	public boolean esPar(int x){
		if(x%2==0)
			//System.out.println("Es Par");
			return true;
		else
			//System.out.println("No es Par");
			return false;
	}
	public void printPares(int n){//modificar
		if(esPar(n)){
			setPar(n+1);
			System.out.println("Par: " + n);
			esperarX(300);
		}
	}

	private void esperarX(int milis) {
		try {
			Thread.sleep(milis);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	@Override
	public void run() {
		while(!getBFin()){
			pFibo();
			if( getInt() ){//hay una interrupción
				setInt(false);
				pPar();
			}
			esperarX(200);
		}
		System.out.println("FIN HILO2");
	}

}
