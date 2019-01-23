package edu.escom.cliente.backend;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import javax.swing.JOptionPane;

public class PDFCreator {

    public static void create(String html, File path) throws DocumentException, FileNotFoundException, IOException {
        // step 1
        Document document = new Document();
        // step 2
        PdfWriter instance = PdfWriter.getInstance(document, new FileOutputStream(path + "/Factura.pdf"));
        // step 3
        document.open();
        document.addAuthor("Tiendita RUPE y MONJA :v");
        document.addSubject("Ticket de compra");
        document.addCreationDate();

        XMLWorkerHelper worker = XMLWorkerHelper.getInstance();
        worker.parseXHtml(instance, document, new StringReader(html));
        document.close();
        

        File pdfFile = new File(path + "/Factura.pdf");
        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().open(pdfFile);
        }else{
            JOptionPane.showMessageDialog(null, "Tu ticket fue generado correctamente. Puedes encontrarlo aquí: "
                + path + "/Factura.pdf", "Ticket generado", JOptionPane.INFORMATION_MESSAGE);
        }
    }

}
