import java.rmi.Remote; 
import java.rmi.RemoteException; 

/**
 * @author morat 
 */
public interface ISupplier extends Remote {
	/**
	 * renvoie la velur de la propriété os.name
	 * @return la valeur de os.name
	 * @throws RemoteException
	 */
	public String question() throws RemoteException;
	/**
	 * renvoie la valeur de la proptiété de nom s
	 * @param s le nom de la propriété choisie
	 * @return la valeur de cette propriété
	 * @throws RemoteException
	 */
	public String question(String s) throws RemoteException;

	/**
	 * renvoie le nom du Provider : nom générique suivi du rang
	 * @return le nom du Provider
	 * @throws RemoteException
	 */
	public String name() throws RemoteException;
}