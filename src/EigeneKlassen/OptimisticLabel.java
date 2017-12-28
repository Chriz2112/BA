package EigeneKlassen;

import java.util.HashSet;
import java.util.Iterator;

import net.sf.tweety.lp.asp.syntax.DLPLiteral;

public class OptimisticLabel implements Iterable<HashSet<DLPLiteral>>{
private HashSet<HashSet<DLPLiteral>> optLabel;

	/**
	 * constructor of an empty instance of optimistic label
	 */

	public OptimisticLabel() {
		this.optLabel = new HashSet<HashSet<DLPLiteral>>();
	}
	
	/**
	 * constructor of a non-empty instance of optimistic label
	 * 
	 * @param optLabel HashSet
	 */
	
	public OptimisticLabel(HashSet<HashSet<DLPLiteral>> optLabel) {
		this.optLabel = optLabel;
	}
	
	public HashSet<HashSet<DLPLiteral>> getoptLabel() {
		return optLabel;
	}

	public void setoptLabel(HashSet<HashSet<DLPLiteral>> optLabel) {
		this.optLabel = optLabel;
	}
	
	/**
	 * creates Iterator for optimistic label
	 */
	
	@Override
	public Iterator<HashSet<DLPLiteral>> iterator() {
		return optLabel.iterator();
	}
	
	/**
	 * adds a set of decisions to optimisic label
	 * 
	 * @param decisions HashSet
	 */
	
	public void add(HashSet<DLPLiteral> decisions) {
		this.optLabel.add(decisions);
	}
	
	/**
	 * converts optimistic label to string
	 * 
	 * @return optLabel String
	 */
	public String toString() {
		String optLabel = "{";
		for (HashSet<DLPLiteral> decision : this.optLabel) {
			optLabel = optLabel.concat("{");
			for(DLPLiteral literal : decision) {
				optLabel = optLabel.concat(literal + ", ");
			}
			optLabel = optLabel.concat("}");
		}
		optLabel = optLabel.concat("}");
		return optLabel;
	}
	
	/**
	 * computes size of an optimistic label
	 * 
	 * @return this.optLabel.size()
	 */
	
	public int size () {
		return this.optLabel.size();
	}
}
