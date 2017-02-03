/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package jus.aor.printing;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import static jus.aor.printing.Notification.REPLY_PRINT_OK;

/**
 *
 * @author alicia
 */
public class Esclave extends Thread{
    Socket soc;
    
    public Esclave(Socket soc){
        this.soc = soc;
    }
    
    public void run(){
        String file_content = null;
        try {
            file_content = TCP.readData(soc);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, "Erreur lecture de data dans l''esclave : {0}", ex);
        }
        
        System.out.println("jus.aor.printing.Server.runTCP() : " + file_content);
        
    }
}
