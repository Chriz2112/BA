package EigeneKlassen;

import java.util.ArrayList;
import java.util.Iterator;

import net.sf.tweety.lp.asp.syntax.DLPLiteral;

public class OptimisticLabel implements Iterable<ArrayList<DLPLiteral>>{
private ArrayList<ArrayList<DLPLiteral>> optLabel;
	
	public ArrayList<ArrayList<DLPLiteral>> getoptLabel() {
		return optLabel;
	}

	public void setoptLabel(ArrayList<ArrayList<DLPLiteral>> optLabel) {
		this.optLabel = optLabel;
	}

	public OptimisticLabel() {
		this.optLabel = new ArrayList<ArrayList<DLPLiteral>>();
	}
	
	public OptimisticLabel(ArrayList<ArrayList<DLPLiteral>> optLabel) {
		this.optLabel = optLabel;
	}
	
	@Override
	public Iterator<ArrayList<DLPLiteral>> iterator() {
		return optLabel.iterator();
	}
	
	public void add(ArrayList<DLPLiteral> decision) {
		this.optLabel.add(decision);
	}
	
	public String toString() {
		String optLabel = "{";
		for (ArrayList<DLPLiteral> decision : this.optLabel) {
			optLabel = optLabel.concat("{");
			for(DLPLiteral literal : decision) {
				optLabel = optLabel.concat(literal + ", ");
			}
			optLabel = optLabel.concat("}");
		}
		optLabel = optLabel.concat("}");
		return optLabel;
	}
}
