package EigeneKlassen;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.sf.tweety.commons.util.rules.RuleSet;
import net.sf.tweety.lp.asp.syntax.Program;
import net.sf.tweety.lp.asp.syntax.Rule;

public class PossibilisticProgram implements Iterable<PossibilisticRule>{
	
	private ArrayList<PossibilisticRule> possibilisticProgram;
	
	public PossibilisticProgram() {
		
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
	
	public static Program projection (PossibilisticProgram possibilisticProgram){
		Program p = new Program();
		for(PossibilisticRule r : possibilisticProgram) {
			p.add(r.getRule());
		}
		return p;
	}
}
