package EigeneKlassen;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

import net.sf.tweety.lp.asp.syntax.DLPAtom;
import net.sf.tweety.lp.asp.syntax.DLPLiteral;
import net.sf.tweety.lp.asp.syntax.DLPNeg;
import net.sf.tweety.lp.asp.syntax.DLPNot;
import net.sf.tweety.lp.asp.syntax.Program;
import net.sf.tweety.lp.asp.syntax.Rule;

public class DecisionMaking {
    private Program knowledge;
    private HashSet<DLPLiteral> decisions;
    private HashSet<DLPLiteral> preferences;
    
    public DecisionMaking() {
    	    this.knowledge = new Program();
    	    this.decisions = new HashSet<DLPLiteral>();
    	    this.preferences = new HashSet<DLPLiteral>();
    }
    
    public DecisionMaking (Program knowledge, HashSet<DLPLiteral> decisions, HashSet <DLPLiteral> preferences) {
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
	public HashSet<DLPLiteral> getDecisions() {
		return decisions;
	}
	public void setDecisions(HashSet<DLPLiteral> decisions) {
		this.decisions = decisions;
	}
	public HashSet<DLPLiteral> getPreferences() {
		return preferences;
	}
	public void setPreferences(HashSet<DLPLiteral> preferences) {
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
	
	public void addDecisions(HashSet<DLPLiteral> decisions2) {
		for(DLPLiteral literal : decisions2) {
			this.decisions.add(literal);	
		}
	}
	
	public void addPreference (DLPLiteral preference) {
		this.preferences.add(preference);
	}
	
	public void addPreferences(HashSet<DLPLiteral> preferences) {
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
	
	 public static Program subProgramPessimistic(DecisionMaking dm) {
		Program subProgram = dm.getKnowledge();
		//subProgram.add(dm.constraintsPreferences(dm));	
		subProgram.add(dm.assumptions1(dm));
		subProgram.add(dm.assumptions2(dm));
		//subProgram.add(dm.weakConstraints(dm));
		return subProgram;
	 }
	 
/*	 public Program addProgram (Program program1, Program additionalProgram) {
			 program1.add(additionalProgram);
		 return program1;
	 }*/
	
	 public static Program subProgramOptimistic(DecisionMaking dm) {
		Program subProgram = dm.getKnowledge();
		subProgram.add(preferencesFacts(dm));	
//		subProgram.add(assumptions1());
//		subProgram.add(assumptions2());
//		subProgram.add(weakConstraints());
		return subProgram;
	 }
	 
	public static Program preferencesFacts(DecisionMaking dm) {
		Program factProgram = new Program();
		for (DLPLiteral preference : dm.preferences) {
			Rule constraint = new Rule(preference);
			factProgram.add(constraint);
		}
		return factProgram;
	}
	
	public static Program constraintsPreferences (DecisionMaking dm) {
		Program constraintProgram = new Program();
		for (DLPLiteral preference : dm.preferences) {
			Rule constraint = new Rule();
			DLPNot notPreference = new DLPNot(preference);
			constraint.addPremise(notPreference);
			constraintProgram.add(constraint);
		}
		return constraintProgram;
	}
	
	public static Program assumptions1(DecisionMaking dm) {
		Program assumptionsProgram = new Program();
		for (DLPLiteral decision : dm.decisions) {
			DLPNeg negAss_d = new DLPNeg("ass(" + decision.toString() + ")");
			DLPAtom ass_d = new DLPAtom("ass(" + decision.toString() + ")");
			DLPNot notNegAss_d = new DLPNot(negAss_d);
			DLPNot notAss_d = new DLPNot (ass_d);
			Rule assumption1 = new Rule(negAss_d, notAss_d);
			Rule assumption2 = new Rule(ass_d, notNegAss_d);
			assumptionsProgram.add(assumption1);
			assumptionsProgram.add(assumption2);			
		}
		return assumptionsProgram;
	}
	public static Program assumptions2(DecisionMaking dm) {
		Program assumptionsProgram = new Program();
		for (DLPLiteral decision : dm.decisions) {
			DLPAtom ass_d = new DLPAtom("ass(" + decision.toString() + ")");
			Rule assumption1 = new Rule(decision, ass_d);
			assumptionsProgram.add(assumption1);		
		}
		return assumptionsProgram;
	}
	
	public static Program weakConstraints(DecisionMaking dm) {
		Program weakConstraintsProgram = new Program();
		for(DLPLiteral decision : dm.decisions) {
			Rule rule = new Rule();
			rule.addPremise(decision);
			weakConstraintsProgram.add(rule);
		}
		return weakConstraintsProgram;
	}
}
