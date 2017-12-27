package EigeneKlassen;

import java.util.Iterator;
import java.util.HashSet;

import net.sf.tweety.lp.asp.syntax.DLPLiteral;

public class PessimisticLabel implements Iterable<HashSet<DLPLiteral>>{
	private HashSet<HashSet<DLPLiteral>> pessLabel;
	
	public HashSet<HashSet<DLPLiteral>> getPessLabel() {
		return pessLabel;
	}

	public void setPessLabel(HashSet<HashSet<DLPLiteral>> pessLabel) {
		this.pessLabel = pessLabel;
	}

	public PessimisticLabel() {
		this.pessLabel = new HashSet<HashSet<DLPLiteral>>();
	}
	
	public PessimisticLabel(HashSet<HashSet<DLPLiteral>> pessLabel) {
		this.pessLabel = pessLabel;
	}
	
	@Override
	public Iterator<HashSet<DLPLiteral>> iterator() {
		return pessLabel.iterator();
	}
	
	public void addLiterals(HashSet<DLPLiteral> treeSet) {
		this.pessLabel.add(treeSet);
	}
	
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
	
	public int size () {
		return this.pessLabel.size();
	}
}
