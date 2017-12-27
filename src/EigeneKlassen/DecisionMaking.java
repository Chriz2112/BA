package EigeneKlassen;

import java.util.ArrayList;
import java.util.Set;

import net.sf.tweety.lp.asp.syntax.DLPAtom;
import net.sf.tweety.lp.asp.syntax.DLPLiteral;
import net.sf.tweety.lp.asp.syntax.DLPNeg;
import net.sf.tweety.lp.asp.syntax.DLPNot;
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
	
	public void addRule (Rule rule) {
		this.knowledge.add(rule);
	}
	
	public void addKnowledge(Program knowledge) {
		for(Rule rule : knowledge) {
			this.knowledge.add(rule);			
		}
	}
	
	public void addDecision (DLPLiteral decision) {
		this.decisions.add(decision);
	}
	
	public void addDecisions(ArrayList<DLPLiteral> decisions) {
		for(DLPLiteral literal : decisions) {
			this.decisions.add(literal);	
		}
	}
	
	public void addPreference (DLPLiteral preference) {
		this.preferences.add(preference);
	}
	
	public void addPreferences(ArrayList<DLPLiteral> preferences) {
		for(DLPLiteral literal : preferences) {
			this.preferences.add(literal);
		}
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
	
	 public Program subProgram() {
		Program subProgram = this.getKnowledge();
		subProgram.add(constraintsPreferences());	
		subProgram.add(assumptions1());
		subProgram.add(assumptions2());
		subProgram.add(weakConstraints());
		return subProgram;
	 }
	 
/*	 public Program addProgram (Program program1, Program additionalProgram) {
			 program1.add(additionalProgram);
		 return program1;
	 }*/
	
	public Program constraintsPreferences () {
		Program constraintProgram = new Program();
		for (DLPLiteral preference : this.preferences) {
			Rule constraint = new Rule();
			DLPNot notPreference = new DLPNot(preference);
			constraint.addPremise(notPreference);
			constraintProgram.add(constraint);
		}
		return constraintProgram;
	}
	
	public Program assumptions1() {
		Program assumptionsProgram = new Program();
		for (DLPLiteral decision : this.decisions) {
			DLPNeg negAss_d = new DLPNeg("ass(" + decision + ")");
			DLPAtom ass_d = new DLPAtom("ass(" + decision + ")");
			DLPNot notNegAss_d = new DLPNot(negAss_d);
			DLPNot notAss_d = new DLPNot (ass_d);
			Rule assumption1 = new Rule(negAss_d, notAss_d);
			Rule assumption2 = new Rule(ass_d, notNegAss_d);
			assumptionsProgram.add(assumption1);
			assumptionsProgram.add(assumption2);			
		}
		return assumptionsProgram;
	}
	public Program assumptions2() {
		Program assumptionsProgram = new Program();
		for (DLPLiteral decision : this.decisions) {
			DLPAtom ass_d = new DLPAtom("ass(" + decision + ")");
			Rule assumption1 = new Rule(decision, ass_d);
			assumptionsProgram.add(assumption1);		
		}
		return assumptionsProgram;
	}
	
	public Program weakConstraints() {
		Program weakConstraintsProgram = new Program();
		for(DLPLiteral decision : this.decisions) {
			Rule rule = new Rule();
			rule.addPremise(decision);
			weakConstraintsProgram.add(rule);
		}
		return weakConstraintsProgram;
	}
	
	/*public PessimisticLabel pessDM () {
		
	}*/
}
