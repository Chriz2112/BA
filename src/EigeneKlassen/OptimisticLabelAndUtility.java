package EigeneKlassen;

import java.util.ArrayList;

import net.sf.tweety.lp.asp.syntax.DLPLiteral;

public class OptimisticLabelAndUtility {
	private OptimisticLabel optimisticLabel;
	private Double optimisticUtility;
	
	
	
	public OptimisticLabel getOptimisticLabel() {
		return optimisticLabel;
	}

	public void setOptimisticLabel(OptimisticLabel optimisticLabel) {
		this.optimisticLabel = optimisticLabel;
	}

	public Double getPessimisticUtility() {
		return optimisticUtility;
	}

	public void setPessimisticUtility(Double optimisticUtility) {
		this.optimisticUtility = optimisticUtility;
	}

	public OptimisticLabelAndUtility() {
		this.optimisticLabel = new OptimisticLabel();
		this.optimisticUtility = 0.0;
	}
	
	public OptimisticLabelAndUtility(ArrayList<DLPLiteral> literals, Double utility) {
		this.optimisticLabel.add(literals);
		this.optimisticUtility = utility;
	}
	
	public void addLiterals (ArrayList<DLPLiteral> literals) {
		this.optimisticLabel.add(literals);
	}
	
	public String toString() {
		String labelAndUtility = new String();
		labelAndUtility = labelAndUtility.concat(optimisticLabel.toString() + "pessimistic Utility: " + this.optimisticUtility);
		return labelAndUtility;
	}
}
