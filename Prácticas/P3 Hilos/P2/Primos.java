public class Primos extends Thread{
	private int numero; //para saber en qué num empieza la cuenta 
	 boolean bPrimo=false;

	private boolean interrupcion=false;

	private char letra; //para saber en que letra empezar
	private boolean bLetra=false;

	private boolean bFin=false;

	Primos(int numero, char letra){
		this.numero=numero;
		this.letra=letra;
	}

	public int getNum(){
		return this.numero;
	}
	public boolean getInt(){
		return this.interrupcion;
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
	public void setInt(boolean interrupcion){
		this.interrupcion=interrupcion;
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
		int x=getNum();
		
		for(int i=x;i<=100;i++){
			if(x==100)
				x=1;
			if( getInt() ){//hay una interrupción
				setInt(false);
				break;
			}
			else 
				printPrimos(i);
		}
	}
	public void printPrimos(int n){
		if(esPrimo(n)){
			setNum(n);
			System.out.println("Primo: " + n);
			esperarXsegundos(1);
		}
	}

	public void pABC(){
		char l=getLetra();
		for(int i=l; i<=90; i++){
			if(l=='Z')
				l='A';
			if( getInt() ){//hay una interrupción
				setInt(false);
				break;
			}
			else{
				setLetra((char)i);
				System.out.println("Letra: " + (char)i);
				esperarXsegundos(1);	
			}
		}
	}


	@Override
	public void run() {
		while(getBFin()==false){
			pPrimos();
			pABC();
			esperarX(100);
		}
		System.out.println("Salio del While");

	}

	private void esperarXsegundos(int segundos) {
		try {
			Thread.sleep(segundos * 1000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	private void esperarX(int milis) {
		try {
			Thread.sleep(milis);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}
}