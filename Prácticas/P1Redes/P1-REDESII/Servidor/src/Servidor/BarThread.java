package Servidor;

import javax.swing.JProgressBar;

public class BarThread extends Thread{
    private JProgressBar bar;
    private int x=0;
    public BarThread(JProgressBar bar) {
        this.bar=bar;
    }

    public void setValue(int x){
        this.x=x;
    }
    public int getValue(){
        return this.x;
    }
    @Override
    public void run() {
        while ( bar.getValue()!=100 ) {
            System.out.println("Barra:"+getValue());
            bar.setValue(getValue());            
            checkBar();
            waitFor(500);
        }
        System.out.println("FIN Barra Servidor");
    }
    
    public void checkBar(){
        bar.setStringPainted(true);
        bar.setValue(getValue());
        bar.setStringPainted(true);
    }
    public void waitFor(int ms){
        try {
            sleep(ms);
        }
        catch (InterruptedException ex) {
            System.out.println(ex);
        }
    }
    
}
