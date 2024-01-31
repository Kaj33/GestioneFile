package gestionefile;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author KT
 * @version 31/01/2024
 */

public class Scrittore implements Runnable, AutoCloseable {

    String nomeFile;

    public Scrittore(String nomeFile) {
        this.nomeFile = nomeFile;
    }

    @Override
    public void run() {
        scrivi();
    }

    //Scrive un file di testo usando la classe BufferedWriter
    public void scrivi() {
        try (BufferedWriter br = new BufferedWriter(new FileWriter(nomeFile))) {
            // 2) scrivo nel buffer
            br.write("File in output");
            br.write("\n\r");
            // 3) svuoto il buffer e salvo nel file i dati
            br.flush();
        } catch (IOException ex) {
            Logger.getLogger(Scrittore.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void close() throws IOException {
        // Qui non serve più la chiusura perchè viene tutto gestito dal try-with-resources
    }
}

/*
In questo casp, ho rimosso la sezione "finally" che gestiva la chiusura
del BufferedWriter e ho fatto in modo che la classe implementi 
l'interfaccia "AutoCloseable".
Grazie a questa modifica, in teoria la chiusura del "BufferedWriter"
sarà automaticamente gestita dal
blocco "try-with-resources" alla fine del blocco "try".
*/