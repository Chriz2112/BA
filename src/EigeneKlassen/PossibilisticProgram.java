package EigeneKlassen;
import java.util.ArrayList;
import java.util.Iterator;

import net.sf.tweety.lp.asp.syntax.Program;

/**
 * this class is for modeling possibilistic programs
 * 
 * @author Christoph Meyer
 *
 */

public class PossibilisticProgram implements Iterable<PossibilisticRule>{
	
	private ArrayList<PossibilisticRule> possibilisticProgram;
	
	/**
	 * constructor of an empty possibilistic program
	 */
	
	public PossibilisticProgram() {
		this.possibilisticProgram =  new ArrayList<PossibilisticRule>();
	}
	
	
	/**
	 * constructor of a non-empty possibilistic program
	 * @param rules ArrayList
	 */
	public PossibilisticProgram(ArrayList<PossibilisticRule> rules) {
		this.possibilisticProgram = rules;
	}

	public ArrayList<PossibilisticRule> getPossibilisticProgram() {
		return possibilisticProgram;
	}

	public void setPossibilisticProgram(ArrayList<PossibilisticRule> possibilisticProgram) {
		this.possibilisticProgram = possibilisticProgram;
	}
	
	/**
	 * creates iterator for a possibilistic program
	 */
	
	@Override
	public Iterator<PossibilisticRule> iterator(){
		return possibilisticProgram.iterator();
	}
	
	/**
	 * adds a rule to a possibilistic program
	 * 
	 * @param rule PossibilisticRule
	 */
	
	public void addRule(PossibilisticRule rule) {
		this.possibilisticProgram.add(rule);
	}

	/**
	 * returns the projection of a possibilistic program
	 * 
	 * @return program Program
	 */
	
	public Program projection (){
		Program program = new Program();
		for(PossibilisticRule rule : this.possibilisticProgram) {
			program.add(rule.getRule());
		}
		return program;
	}
	
	/**
	 * returns all rules with necessity greater or equal a lower bound
	 * 
	 * @param necessity Double
	 * @return program PossibilisticProgram
	 */
	
	public PossibilisticProgram programNecessity (Double necessity) {
		PossibilisticProgram program = new PossibilisticProgram();
		for(PossibilisticRule rule : this.possibilisticProgram) {
			if(rule.getNecessity() >= necessity) {
				program.addRule(rule);
			}
		}
		return program;
	}
	
	/**
	 * converts possibilistic program to string
	 * 
	 * @return program String
	 */
	public String toString() {
		String program = new String();
		int i = 1;
		for (PossibilisticRule rule : this.possibilisticProgram) {
			program = program.concat("r" + i + ": (" + rule.getNecessity() + ", " + rule.getRule().toString() + ") \n");
			i++;
		}
		return program;
	}
}
