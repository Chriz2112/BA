package EigeneKlassen;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import net.sf.tweety.lp.asp.syntax.DLPLiteral;

public class PossibilisticLiteralSet implements Iterable<PossibilisticLiteral>{
	
	private ArrayList<PossibilisticLiteral> possibilisticLiterals;
	
	public PossibilisticLiteralSet() {
		this.possibilisticLiterals = new ArrayList<PossibilisticLiteral>();
	}
	
	public PossibilisticLiteralSet(ArrayList<PossibilisticLiteral> possibilisticLiterals) {
		this.possibilisticLiterals = possibilisticLiterals;
	}

	public ArrayList<PossibilisticLiteral> getPossibilisticLiterals() {
		return possibilisticLiterals;
	}

	public void setPossibilisticLiterals(ArrayList<PossibilisticLiteral> possibilisticLiterals) {
		this.possibilisticLiterals = possibilisticLiterals;
	}
	
	@Override
	public Iterator<PossibilisticLiteral> iterator() {
		return possibilisticLiterals.iterator();
	}
	
	public Set<DLPLiteral> projection (PossibilisticLiteralSet possibilisticLiterals){
		Set<DLPLiteral> literals = new HashSet<DLPLiteral>();
		for (PossibilisticLiteral literal : possibilisticLiterals) {
			literals.add(literal.getLiteral());
		}
		return literals;
	}
	
	public PossibilisticLiteralSet literalsNecessity (Double necessity){
		PossibilisticLiteralSet literals = new PossibilisticLiteralSet();
		for ( PossibilisticLiteral literal : possibilisticLiterals) {
		    if(literal.getNecessity() >= necessity) {
		    	    literals.add(literal);
		    }
		}
		return literals;
	}
	
	public void add (PossibilisticLiteral literal) {
		this.possibilisticLiterals.add(literal);
	}
	
	public String toString() {
		String literals = new String();
		for (PossibilisticLiteral literal : this.possibilisticLiterals) {
			literals = literals.concat("(" + literal.getNecessity() + ", " + literal.getLiteral() + ") \n");
		}
		return literals;
	}
}
