package EigeneKlassen;

import java.util.Iterator;
import java.util.HashSet;

import net.sf.tweety.lp.asp.syntax.DLPLiteral;

public class PessimisticLabel implements Iterable<HashSet<DLPLiteral>>{
	private HashSet<HashSet<DLPLiteral>> pessLabel;
	
	/**
	 * constructor of empty instance of pessimistic label
	 */
	public PessimisticLabel() {
		this.pessLabel = new HashSet<HashSet<DLPLiteral>>();
	}
	
	/**
	 * constructor of non-empty instance of pessimistic label
	 * @param pessLabel HashSet
	 */
	
	public PessimisticLabel(HashSet<HashSet<DLPLiteral>> pessLabel) {
		this.pessLabel = pessLabel;
	}
	
	public HashSet<HashSet<DLPLiteral>> getPessLabel() {
		return pessLabel;
	}

	public void setPessLabel(HashSet<HashSet<DLPLiteral>> pessLabel) {
		this.pessLabel = pessLabel;
	}

	/**
	 * iterator for pessimistic label
	 */
	@Override
	public Iterator<HashSet<DLPLiteral>> iterator() {
		return pessLabel.iterator();
	}
	
	/**
	 * adds a set of decisions to pessimistic label
	 * 
	 * @param decisions HashSet
	 */
	public void addLiterals(HashSet<DLPLiteral> decisions) {
		this.pessLabel.add(decisions);
	}
	
	/**
	 * converts pessimistic label to string
	 * @return pessLabel String
	 */
	
	public String toString() {
		String pessLabel = "{";
		for (HashSet<DLPLiteral> decision : this.pessLabel) {
			pessLabel = pessLabel.concat("{");
			for(DLPLiteral literal : decision) {
				pessLabel = pessLabel.concat(literal + ", ");
			}
			pessLabel = pessLabel.concat("}");
		}
		pessLabel = pessLabel.concat("}");
		return pessLabel;
	}
	
	/**
	 * returns the size of a pessimistic label
	 * 
	 * @return this.pessLabel.size() int
	 */
	
	public int size () {
		return this.pessLabel.size();
	}
}
