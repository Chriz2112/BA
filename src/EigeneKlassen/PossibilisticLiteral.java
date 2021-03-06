package EigeneKlassen;
import net.sf.tweety.lp.asp.syntax.DLPLiteral;

/**
 * this class is for modeling possibilistic literals
 * 
 * @author Christoph Meyer
 *
 */

public class PossibilisticLiteral {
	private double necessity;
	private DLPLiteral literal;
	
	/**
	 * constructor of a possibilistic literal
	 * @param necessity Double
	 * @param literal DLPLiteral
	 */
	
	public PossibilisticLiteral(Double necessity, DLPLiteral literal) {
		this.necessity = necessity;
		this.literal = literal;
	}
	
	public double getNecessity() {
		return necessity;
	}
	public void setNecessity(double necessity) {
		this.necessity = necessity;
	}
	public DLPLiteral getLiteral() {
		return literal;
	}
	public void setLiteral(DLPLiteral literal) {
		this.literal = literal;
	}
}
