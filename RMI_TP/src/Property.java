import java.io.Serializable;

/**
 * J<i>ava</i> U<i>tilities</i> for S<i>tudents</i>
 */

/**
 * @author morat 
 */
public class Property implements IProperty, Serializable{
	private static final long serialVersionUID = 1L;
	private String value;
	/**
	 * @param value la valeur à affecter à la propriété
	 */
	public Property(String value) { this.value=value;}
	public String value(){ return value;}
}
