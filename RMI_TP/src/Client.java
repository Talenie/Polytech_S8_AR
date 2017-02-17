import java.rmi.RMISecurityManager;

/**
 * @author morat 
 */
public class Client {
	/**
	 * @param args
	 */
	public static void main(String args[]) {
		String nom="", host="localhost";
		int num=1, nombre=1;
		// récupération des arguments
		if (args.length!=4){
			System.out.println("Client <hostname> <nom générique des objets distants> <nombre d'objets distants> <nombre de clients>");
			System.exit(1);
		}
		try  {
			host = args[0];
			nom = args[1];
			num = Integer.parseInt(args[2]);
			nombre = Integer.parseInt(args[3]);
		}catch(Exception e) {
			System.out.println("Client <hostname> <nom générique des objets distants> <nombre d'objets distants> <nombre de clients>");
			System.exit(1);
		}	
		// installation d'un securityManager 
		// A COMPLETER : INSTALLATION DU SECURITYMANAGER
		// Démarrage des consommateurs
		for(int i=0;i<nombre;i++)  {
			new Customer(host,nom,((int)(Math.random()*num))+1,i).start();
		}
	}
}
