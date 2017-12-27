package EigeneKlassen;

import java.util.HashSet;
import java.util.Iterator;

import net.sf.tweety.lp.asp.syntax.DLPLiteral;

public class OptimisticLabel implements Iterable<HashSet<DLPLiteral>>{
private HashSet<HashSet<DLPLiteral>> optLabel;
	
	public HashSet<HashSet<DLPLiteral>> getoptLabel() {
		return optLabel;
	}

	public void setoptLabel(HashSet<HashSet<DLPLiteral>> optLabel) {
		this.optLabel = optLabel;
	}

	public OptimisticLabel() {
		this.optLabel = new HashSet<HashSet<DLPLiteral>>();
	}
	
	public OptimisticLabel(HashSet<HashSet<DLPLiteral>> optLabel) {
		this.optLabel = optLabel;
	}
	
	@Override
	public Iterator<HashSet<DLPLiteral>> iterator() {
		return optLabel.iterator();
	}
	
	public void add(HashSet<DLPLiteral> hashSet) {
		this.optLabel.add(hashSet);
	}
	
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
	
	public int size () {
		return this.optLabel.size();
	}
}
