import java.rmi.registry.*;
import java.rmi.RemoteException;
import java.rmi.RMISecurityManager;

/**
 * @author morat 
 */
public class Server {
  /**
   * @param args
   */
  public static void main(final String args[]) {
    String nom="";
    int nombre=1; int port = 1099;
    Registry registry=null;
    // récupération des arguments
    if (args.length!=3){
      System.out.println("Server <nom générique des objets distants> <nombre de noms> <port du registry>");
      System.exit(1);
    }
    try  {
      nom = args[0];
      port = Integer.parseInt(args[1]);
      nombre = Integer.parseInt(args[2]);
    }catch(Exception e) {
      System.out.println("Server <nom générique des objets distants> <nombre de noms> <port du registry>");
      System.exit(1);
    }
    // installation d'un securityManager
    // A COMPLETER : INSTALLATIOND'UN SECURITYMANAGER
    // A COMPLETER : MISE EN PLACE DU REGISTRY
    try {
      for(int i=1;i<=nombre;i++){
      	// A COMPLETER : CONSTRUCTION ET EXPORTATION DES OBJETS DISTANTS
      }
      System.out.println("Tous les objets sont enregistrés dans le serveur d'objets distants");
    } catch (Exception e) {
      System.out.println("Server err: " + e);
    }
  }
}
