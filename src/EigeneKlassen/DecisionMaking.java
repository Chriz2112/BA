package EigeneKlassen;

import java.util.ArrayList;
import java.util.Set;

import net.sf.tweety.lp.asp.syntax.DLPLiteral;
import net.sf.tweety.lp.asp.syntax.Program;
import net.sf.tweety.lp.asp.syntax.Rule;

public class DecisionMaking {
    private Program knowledge;
    private ArrayList<DLPLiteral> decisions;
    private ArrayList<DLPLiteral> preferences;
    
    public DecisionMaking() {
    	    this.knowledge = new Program();
    	    this.decisions = new ArrayList<DLPLiteral>();
    	    this.preferences = new ArrayList<DLPLiteral>();
    }
    
    public DecisionMaking (Program knowledge, ArrayList<DLPLiteral> decisions, ArrayList <DLPLiteral> preferences) {
    		this.knowledge = knowledge;
    		this.decisions = decisions;
    		this.preferences = preferences;
    }
    
	public Program getKnowledge() {
		return knowledge;
	}
	public void setKnowledge(Program knowledge) {
		this.knowledge = knowledge;
	}
	public ArrayList<DLPLiteral> getDecisions() {
		return decisions;
	}
	public void setDecisions(ArrayList<DLPLiteral> decisions) {
		this.decisions = decisions;
	}
	public ArrayList<DLPLiteral> getPreferences() {
		return preferences;
	}
	public void setPreferences(ArrayList<DLPLiteral> preferences) {
		this.preferences = preferences;
	}
	
	public void addKnowledge(Program knowledge) {
		this.knowledge = knowledge;
	}
	
	public void addDecisions(ArrayList<DLPLiteral> decisions) {
		this.decisions = decisions;
	}
	
	public void addPreferences(ArrayList<DLPLiteral> preferences) {
		this.preferences = preferences;
	}
	
	public String knowledgeToString() {
		String knowledge = new String();
		int i = 1;
		for (Rule rule : this.knowledge) {
			knowledge = knowledge.concat("r" + i + ": " + rule.toString() + "\n");
			i++;
		}
		return knowledge;
	}
	
	public String decisionsToString() {
		String decisions = new String();
		int i = 1;
		for (DLPLiteral literal : this.decisions) {
			decisions = decisions.concat("d" + i + ": " + literal.toString() + "\n");
			i++;
		}
		return decisions;
	}
	
	public String preferencesToString() {
		String preferences = new String();
		int i = 1;
		for (DLPLiteral literal : this.preferences) {
			preferences = preferences.concat("p" + i + ": " + literal.toString() + "\n");
			i++;
		}
		return preferences;
	}
	
	public String toString() {
		String dm = this.knowledgeToString() + this.decisionsToString() + this.preferencesToString();
		return dm;
	}
	
}
