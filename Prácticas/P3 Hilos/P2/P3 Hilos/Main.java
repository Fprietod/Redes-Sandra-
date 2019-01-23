public class Main{
	public static void main(String[] args) {
		Hilo_Secuencia1 hilo1=new Hilo_Secuencia1(1,'A');
		Hilo_Secuencia2 hilo2=new Hilo_Secuencia2(0,0);
		Hilo_Interrupciones h= new Hilo_Interrupciones(hilo1,hilo2);
		h.start();
	}
}