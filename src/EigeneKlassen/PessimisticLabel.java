package EigeneKlassen;

import java.util.ArrayList;
import java.util.Iterator;

import net.sf.tweety.lp.asp.syntax.DLPLiteral;

public class PessimisticLabel implements Iterable<ArrayList<DLPLiteral>>{
	private ArrayList<ArrayList<DLPLiteral>> pessLabel;
	
	public ArrayList<ArrayList<DLPLiteral>> getPessLabel() {
		return pessLabel;
	}

	public void setPessLabel(ArrayList<ArrayList<DLPLiteral>> pessLabel) {
		this.pessLabel = pessLabel;
	}

	public PessimisticLabel() {
		this.pessLabel = new ArrayList<ArrayList<DLPLiteral>>();
	}
	
	public PessimisticLabel(ArrayList<ArrayList<DLPLiteral>> pessLabel) {
		this.pessLabel = pessLabel;
	}
	
	@Override
	public Iterator<ArrayList<DLPLiteral>> iterator() {
		return pessLabel.iterator();
	}
	
	public void addLiterals(ArrayList<DLPLiteral> decision) {
		this.pessLabel.add(decision);
	}
	
	public String toString() {
		String pessLabel = "{";
		for (ArrayList<DLPLiteral> decision : this.pessLabel) {
			pessLabel = pessLabel.concat("{");
			for(DLPLiteral literal : decision) {
				pessLabel = pessLabel.concat(literal + ", ");
			}
			pessLabel = pessLabel.concat("}");
		}
		pessLabel = pessLabel.concat("}");
		return pessLabel;
	}
}
