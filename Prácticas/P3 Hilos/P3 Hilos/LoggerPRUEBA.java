import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerPRUEBA{
   // El log para ESTA clase en particular
    private final static Logger LOG_RAIZ = Logger.getLogger("");
    private final static Logger LOGGER = Logger.getLogger("LoggerS.class");
    
    public static void main(String[] args) {
        try {
            Handler consoleHandler = new ConsoleHandler();
            Handler fileHandler = new FileHandler("bitacorita.log", false);

            MiFormato simpleFormatter = new MiFormato();
            fileHandler.setFormatter(simpleFormatter);

            //LOGGER.addHandler(consoleHandler);
            LOGGER.addHandler(fileHandler);

            consoleHandler.setLevel(Level.ALL);
            fileHandler.setLevel(Level.ALL);

            

            LOGGER.info("Holis");
            LOGGER.log(Level.INFO, "Bitacora inicializada");

		}
        catch (Exception e) {
            LOGGER.log(Level.INFO, "Error en metodo1a " + e);
        }
	}

}