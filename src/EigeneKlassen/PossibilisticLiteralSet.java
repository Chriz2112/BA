package EigeneKlassen;
import java.util.HashSet;
import java.util.Iterator;

import net.sf.tweety.lp.asp.syntax.DLPLiteral;

public class PossibilisticLiteralSet implements Iterable<PossibilisticLiteral>{
	
	private HashSet<PossibilisticLiteral> possibilisticLiterals;
	
	public PossibilisticLiteralSet() {
		this.possibilisticLiterals = new HashSet<PossibilisticLiteral>();
	}
	
	public PossibilisticLiteralSet(HashSet<PossibilisticLiteral> possibilisticLiterals) {
		this.possibilisticLiterals = possibilisticLiterals;
	}

	public HashSet<PossibilisticLiteral> getPossibilisticLiterals() {
		return possibilisticLiterals;
	}

	public void setPossibilisticLiterals(HashSet<PossibilisticLiteral> possibilisticLiterals) {
		this.possibilisticLiterals = possibilisticLiterals;
	}
	
	@Override
	public Iterator<PossibilisticLiteral> iterator() {
		return possibilisticLiterals.iterator();
	}
	
	public HashSet<DLPLiteral> projection (){
		HashSet<DLPLiteral> literals = new HashSet<DLPLiteral>();
		for (PossibilisticLiteral literal : this.possibilisticLiterals) {
			literals.add(literal.getLiteral());
		}
		return literals;
	}
	
	public PossibilisticLiteralSet literalsNecessity(Double necessity){
		PossibilisticLiteralSet literals = new PossibilisticLiteralSet();
		for ( PossibilisticLiteral literal : possibilisticLiterals) {
		    if(literal.getNecessity() > necessity) {
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
	
	public void add(PossibilisticLiteralSet literals) {
		for (PossibilisticLiteral literal : literals) {
			this.possibilisticLiterals.add(literal);
		}
	}
}
