import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
public class Hilo_Interrupciones extends Thread{
	private Hilo_Secuencia1 hilo1;
	private Hilo_Secuencia2 hilo2;

    private final static Logger LOG_RAIZ = Logger.getLogger("");
    private final static Logger LOGGER = Logger.getLogger("LoggerS.class");

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
            Handler consoleHandler = new ConsoleHandler();
            Handler fileHandler = new FileHandler("bitacorita.log", false);

            MiFormato simpleFormatter = new MiFormato();
            fileHandler.setFormatter(simpleFormatter);

            //LOGGER.addHandler(consoleHandler);
            LOGGER.addHandler(fileHandler);

            consoleHandler.setLevel(Level.ALL);
            fileHandler.setLevel(Level.ALL);

            /**************************************************************/
            LOGGER.info("Inicio del PROGRAMA");
            LOGGER.info("ID del Hilo_Interrupciones: " +getId());
            setName("Hilo3");    
            LOGGER.info("NOMBRE del Hilo_Interrupciones: " +getName());

            getH1().setName("Hilo1");
            LOGGER.info("ID del Hilo_Secuencia1: " +getH1().getId());
            LOGGER.info("NOMBRE del Hilo_Secuencia1: " +getH1().getName());
            
            getH2().setName("Hilo2");
            LOGGER.info("ID del Hilo_Secuencia2: " +getH2().getId());
            LOGGER.info("NOMBRE del Hilo_Secuencia2: " +getH2().getName());
            
			getH1().start();
            LOGGER.info("Hilo 1 INICIADO");
            LOGGER.info("Estado del Hilo 1: " + getH1().currentThread() );
			getH2().start();
            LOGGER.info("Hilo 2 INICIADO"); 
            LOGGER.info("Estado del Hilo 2: " + getH2().currentThread() );
            /***************************************************************/
            //enviar Logger LOGGER
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
            LOGGER.info("FIN DEL PROGRAMA");
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
