package EigeneKlassen;

import java.util.HashSet;

import net.sf.tweety.lp.asp.syntax.DLPLiteral;

public class OptimisticLabelAndUtility {
	private OptimisticLabel optimisticLabel;
	private Double optimisticUtility;
	
	/**
	 * constructor of an empty instance of optimistic label and utility
	 */
	
	public OptimisticLabelAndUtility() {
		this.optimisticLabel = new OptimisticLabel();
		this.optimisticUtility = 0.0;
	}
	
	/**
	 * constructor of a non-empty instancen of optimistic label and utility
	 * @param literals HashSet
	 * @param utility Double
	 */
	
	public OptimisticLabelAndUtility(HashSet<DLPLiteral> literals, Double utility) {
		this.optimisticLabel.add(literals);
		this.optimisticUtility = utility;
	}
	
	public OptimisticLabel getOptimisticLabel() {
		return optimisticLabel;
	}

	public void setOptimisticLabel(OptimisticLabel optimisticLabel) {
		this.optimisticLabel = optimisticLabel;
	}

	public Double getOptimisticUtility() {
		return optimisticUtility;
	}

	public void setOptimisticUtility(Double optimisticUtility) {
		this.optimisticUtility = optimisticUtility;
	}
	
	/**
	 * add set of literals to optimistic label
	 * 
	 * @param literals HashSet
	 */
	
	public void addLiterals (HashSet<DLPLiteral> literals) {
		this.optimisticLabel.add(literals);
	}
	
	/**
	 * converts label and utility to string
	 * 
	 * @return labelAndUtiliy String
	 */
	
	public String toString() {
		String labelAndUtility = new String();
		labelAndUtility = labelAndUtility.concat(optimisticLabel.toString() + "optimistic Utility: " + this.optimisticUtility);
		return labelAndUtility;
	}
}
