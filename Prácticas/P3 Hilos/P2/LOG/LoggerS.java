import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerS{
   // El log para ESTA clase en particular
    private final static Logger LOG_RAIZ = Logger.getLogger("");
    private final static Logger LOGGER = Logger.getLogger("LoggerS.class");
    
    public static void main(String[] args) {
        try {
            // Los handler (manejadores) indican a donde mandar la salida ya sea consola o archivo
			// En este caso ConsoleHandler envia los logs a la consola
            Handler consoleHandler = new ConsoleHandler();
            // Con el manejador de archivo, indicamos el archivo donde se mandaran los logs
            // El segundo argumento controla si se sobre escribe el archivo o se agregan los logs al final
            // Para sobre escribir pase un true para agregar al final, false para sobre escribir
            // todo el archivo
            Handler fileHandler = new FileHandler("bitacorita.log", false);

            // El formateador indica como presentar los datos, en este caso usaremos el formaro sencillo
            // el cual es mas facil de leer, si no usamos esto el log estara en formato xml por defecto
            MiFormato simpleFormatter = new MiFormato();

            // Se especifica que formateador usara el manejador (handler) de archivo
            fileHandler.setFormatter(simpleFormatter);

            //LOGGER.addHandler(consoleHandler);
            LOGGER.addHandler(fileHandler);


            // Indicamos a partir de que nivel deseamos mostrar los logs, podemos especificar un nivel en especifico
			// para ignorar informacion que no necesitemos
            consoleHandler.setLevel(Level.ALL);
            fileHandler.setLevel(Level.ALL);

            //LOGGER.log(Level.INFO, "Bitacora inicializada");

            //LOGGER.log(Level.INFO, "Probando manejo de excepciones");
            LOGGER.info("Holis");
		}
        catch (Exception e) {
            LOGGER.log(Level.INFO, "Error en metodo1a " + e);
        }
	}

}