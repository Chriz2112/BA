package EigeneKlassen;

import java.util.HashSet;

import net.sf.tweety.lp.asp.syntax.DLPLiteral;

public class PessimisticLabelAndUtility {
	private PessimisticLabel pessimisticLabel;
	private Double pessimisticUtility;
	
	/**
	 * constructor for empty instance of pessimistic label and utility
	 */
	
	public PessimisticLabelAndUtility() {
		this.pessimisticLabel = new PessimisticLabel();
		this.pessimisticUtility = 0.0;
	}
	
	/**
	 * constructor for non-empty instance of pessimistic label and utility
	 * @param literals HashSet
	 * @param utility Double
	 */	
	
	public PessimisticLabelAndUtility(HashSet<DLPLiteral> literals, Double utility) {
		this.pessimisticLabel.addLiterals(literals);
		this.pessimisticUtility = utility;
	}
	
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
	
	/**
	 * adds set of decisions to pessimistic label
	 * 
	 * @param literals HashSet
	 */
	
	public void addLiterals (HashSet<DLPLiteral> literals) {
		this.pessimisticLabel.addLiterals(literals);
	}
	
	/**
	 * converts pessimistic label and utility to string
	 * 
	 * @return labelAndUtility String
	 */
	
	public String toString() {
		String labelAndUtility = new String();
		labelAndUtility = labelAndUtility.concat(pessimisticLabel.toString() + "pessimistic Utility: " + this.pessimisticUtility);
		return labelAndUtility;
	}
}
