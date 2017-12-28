package EigeneKlassen;
import java.util.HashSet;
import java.util.Iterator;

import net.sf.tweety.lp.asp.syntax.DLPLiteral;

/**
 * this class is for modeling possibilistic literal sets
 * 
 * @author Christoph Meyer
 *
 */

public class PossibilisticLiteralSet implements Iterable<PossibilisticLiteral>{
	
	private HashSet<PossibilisticLiteral> possibilisticLiterals;
	
	/**
	 * constructor of empty possibilistic literal set
	 */
	
	public PossibilisticLiteralSet() {
		this.possibilisticLiterals = new HashSet<PossibilisticLiteral>();
	}
	
	/**
	 * constructor of non-empty possibilistic literal set
	 * @param possibilisticLiterals HashSet
	 */
	
	public PossibilisticLiteralSet(HashSet<PossibilisticLiteral> possibilisticLiterals) {
		this.possibilisticLiterals = possibilisticLiterals;
	}

	public HashSet<PossibilisticLiteral> getPossibilisticLiterals() {
		return possibilisticLiterals;
	}

	public void setPossibilisticLiterals(HashSet<PossibilisticLiteral> possibilisticLiterals) {
		this.possibilisticLiterals = possibilisticLiterals;
	}
	
	/**
	 * creates iterator for possibilistic literal set
	 */
	
	@Override
	public Iterator<PossibilisticLiteral> iterator() {
		return possibilisticLiterals.iterator();
	}
	
	/**
	 * projection of possibilistic literal set
	 * 
	 * @return literals HashSet
	 */
	
	public HashSet<DLPLiteral> projection (){
		HashSet<DLPLiteral> literals = new HashSet<DLPLiteral>();
		for (PossibilisticLiteral literal : this.possibilisticLiterals) {
			literals.add(literal.getLiteral());
		}
		return literals;
	}
	
	/**
	 * return literals with necessity greater or equal a lower bound
	 * @param necessity Double
	 * @return literals PossibilisticLiteralSet
	 */
	
	public PossibilisticLiteralSet literalsNecessity(Double necessity){
		PossibilisticLiteralSet literals = new PossibilisticLiteralSet();
		for ( PossibilisticLiteral literal : possibilisticLiterals) {
		    if(literal.getNecessity() > necessity) {
		    	    literals.add(literal);
		    }
		}
		return literals;
	}
	
	/**
	 * adds a possibilistic literal to possibilistic literal set
	 * 
	 * @param literal PossibilisticLiteral
	 */
	
	public void add (PossibilisticLiteral literal) {
		this.possibilisticLiterals.add(literal);
	}
	
	/**
	 * adds possibilistic literal set to possibilistic literals
	 * 
	 * @param literals PossibilisticLiteralSet
	 */
	
	public void add(PossibilisticLiteralSet literals) {
		for (PossibilisticLiteral literal : literals) {
			this.possibilisticLiterals.add(literal);
		}
	}
	
	
	/**
	 * converts possibilistic literal set to string
	 * @return literals String
	 */
	
	public String toString() {
		String literals = new String();
		for (PossibilisticLiteral literal : this.possibilisticLiterals) {
			literals = literals.concat("(" + literal.getNecessity() + ", " + literal.getLiteral() + ") \n");
		}
		return literals;
	}
}
