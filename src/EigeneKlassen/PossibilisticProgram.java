package EigeneKlassen;
import java.util.ArrayList;
import java.util.Iterator;

import net.sf.tweety.lp.asp.syntax.Program;

public class PossibilisticProgram implements Iterable<PossibilisticRule>{
	
	private ArrayList<PossibilisticRule> possibilisticProgram;
	
	public PossibilisticProgram() {
		this.possibilisticProgram =  new ArrayList<PossibilisticRule>();
	}
	
	public PossibilisticProgram(ArrayList<PossibilisticRule> rules) {
		this.possibilisticProgram = rules;
	}

	public ArrayList<PossibilisticRule> getPossibilisticProgram() {
		return possibilisticProgram;
	}

	public void setPossibilisticProgram(ArrayList<PossibilisticRule> possibilisticProgram) {
		this.possibilisticProgram = possibilisticProgram;
	}
	
	@Override
	public Iterator<PossibilisticRule> iterator(){
		return possibilisticProgram.iterator();
	}
	
	/*Füge neue Regel zu possibilistischem Programm hinzu */
	
	public void add (PossibilisticRule rule) {
		this.possibilisticProgram.add(rule);
	}
	/*Projektion eines possibilistischen Programms */
	
	public static Program projection (PossibilisticProgram possibilisticProgram){
		Program program = new Program();
		for(PossibilisticRule rule : possibilisticProgram) {
			program.add(rule.getRule());
		}
		return program;
	}
	
	/* Possibilistisches Programm bezüglich eines bestimmten Niveaus */
	
	public PossibilisticProgram programNecessity (Double necessity) {
		PossibilisticProgram program = new PossibilisticProgram();
		for(PossibilisticRule rule : this.possibilisticProgram) {
			if(rule.getNecessity() >= necessity) {
				program.add(rule);
			}
		}
		return program;
	}
	
	public String toString() {
		String program = new String();
		int i = 1;
		for (PossibilisticRule rule : this.possibilisticProgram) {
			program = program.concat("r" + i + ": (" + rule.getNecessity() + ", " + rule.getRule().toString() + ") \n");
			i++;
		}
		return program;
	}
	
	/* Test */
	
	
}
