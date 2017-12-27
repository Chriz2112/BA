package EigeneKlassen;

import java.util.HashSet;

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

	public Double setOptimisticUtility() {
		return optimisticUtility;
	}

	public void setOptimisticUtility(Double optimisticUtility) {
		this.optimisticUtility = optimisticUtility;
	}

	public OptimisticLabelAndUtility() {
		this.optimisticLabel = new OptimisticLabel();
		this.optimisticUtility = 0.0;
	}
	
	public OptimisticLabelAndUtility(HashSet<DLPLiteral> literals, Double utility) {
		this.optimisticLabel.add(literals);
		this.optimisticUtility = utility;
	}
	
	public void addLiterals (HashSet<DLPLiteral> literals) {
		this.optimisticLabel.add(literals);
	}
	
	public String toString() {
		String labelAndUtility = new String();
		labelAndUtility = labelAndUtility.concat(optimisticLabel.toString() + "optimistic Utility: " + this.optimisticUtility);
		return labelAndUtility;
	}
}
