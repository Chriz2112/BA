package EigeneKlassen;
import net.sf.tweety.lp.asp.syntax.DLPLiteral;

public class PossibilisticLiteral {
	private double necessity;
	private DLPLiteral literal;
	
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
