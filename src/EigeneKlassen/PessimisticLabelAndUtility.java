package EigeneKlassen;

import java.util.ArrayList;

import net.sf.tweety.lp.asp.syntax.DLPLiteral;

public class PessimisticLabelAndUtility {
	private PessimisticLabel pessimisticLabel;
	private Double pessimisticUtility;
	
	
	
	public PessimisticLabel getPessimisticLabel() {
		return pessimisticLabel;
	}

	public void setPessimisticLabel(PessimisticLabel pessimisticLabel) {
		this.pessimisticLabel = pessimisticLabel;
	}

	public Double getPessimisticUtility() {
		return pessimisticUtility;
	}

	public void setPessimisticUtility(Double pessimisticUtility) {
		this.pessimisticUtility = pessimisticUtility;
	}

	public PessimisticLabelAndUtility() {
		this.pessimisticLabel = new PessimisticLabel();
		this.pessimisticUtility = 0.0;
	}
	
	public PessimisticLabelAndUtility(ArrayList<DLPLiteral> literals, Double utility) {
		this.pessimisticLabel.addLiterals(literals);
		this.pessimisticUtility = utility;
	}
	
	public void addLiterals (ArrayList<DLPLiteral> literals) {
		this.pessimisticLabel.addLiterals(literals);
	}
	
	public String toString() {
		String labelAndUtility = new String();
		labelAndUtility = labelAndUtility.concat(pessimisticLabel.toString() + "pessimistic Utility: " + this.pessimisticUtility);
		return labelAndUtility;
	}
}
