public class Hilo_Secuencia1 extends Thread{
	private int numero; //para saber en qué num empieza la cuenta 
	private char letra; //para saber en que letra empezar
	//banderas
	private boolean bPrimo=false;
	private boolean bLetra=false;
	private boolean interr=false;
	private boolean bFin=false;

	Hilo_Secuencia1(int numero, char letra){
		this.numero=numero;
		this.letra=letra;
	}

	public int getNum(){
		return this.numero;
	}
	public boolean getInt(){
		return this.interr;
	}
	public char getLetra(){
		return this.letra;
	}
	public boolean getBPrimo(){
		return this.bPrimo;
	}
	public boolean getBLetra(){
		return this.bLetra;
	}
	public boolean getBFin(){
		return this.bFin;
	}

	public void setNum(int numero){
		this.numero=numero;
	}
	public void setInt(boolean interr){
		this.interr=interr;
	}
	public void setLetra(char letra){
		this.letra=letra;
	}
	public void setBPrimo(boolean bPrimo){
		this.bPrimo=bPrimo;
	}
	public void setBLetra(boolean bLetra){
		this.bLetra=bLetra;
	}
	public void setBFin(boolean bFin){
		this.bFin=bFin;
	}

	public boolean esPrimo(int x){
		int a=0;

		for(int i=1; i<=x; i++){
			if(x%i == 0)
				a++;
		}
		//System.out.println("\ta= "+a);
		if(a!=2)//Si hay más de 2 numeros que lo dividan entonces NO ES PRIMO
			//System.out.println(getNum()+" Es primo");
			return false;
		else
			//System.out.println(getNum()+" No es Primo");
			return true;
	}

	public void pPrimos(){
		System.out.println("Numeros PRIMOS");
		int x=getNum();
		for(int i=x;i<=1000;i++){
			if( getBFin() )
				break;	
			else if( getInt() )//hay una interrupción
				break;
			else{
				printPrimos(i);
				if(i==1000)
					i=0;
			} 
		} 
	}

	public void printPrimos(int n){
		if(esPrimo(n)){
			setNum(n+1);
			System.out.println("Primo: " + n);
			esperarX(300);
		}
	}

	public void pABC(){
		System.out.println("ABECEDARIO");
		char l=getLetra();
		for(int i=l; i<=90; i++){
			if(getBFin()) 
				break;
			else if( getInt() ){//hay una interrupción
				setInt(false);
				break;
			}
			else{
				setLetra((char)(i+1));
				System.out.println("Letra: " + (char)i);
				if(i=='Z')
					i=64;
				esperarX(300);	
			}
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
			pPrimos();
			if( getInt() ){//hay una interrupción
				setInt(false);
				pABC();
			}
			esperarX(200);
		}
		System.out.println("FIN HILO1");
	}

}
