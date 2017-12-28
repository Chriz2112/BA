package EigeneKlassen;

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
    
    /**
     * constructor for empty DM
     */
    
    public DecisionMaking() {
    	/*
    	 * creates new empty Instance of a Decision Making Problem
    	 */
    	    this.knowledge = new Program();
    	    this.decisions = new HashSet<DLPLiteral>();
    	    this.preferences = new HashSet<DLPLiteral>();
    }
	    
	/**
	 * constructor for non-empty Instance of a Decision Making Problem
	 * 
	 * @param knowledge Program
	 * @param decisions HashSet
	 * @param preferences HashSet
	 */
    
    public DecisionMaking (Program knowledge, HashSet<DLPLiteral> decisions, HashSet <DLPLiteral> preferences) {
    	
    	/*
    	 * creates new non-empty Instance of a Decision Making Problem
    	 */
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
		return this.preferences;
	}
	public void setPreferences(HashSet<DLPLiteral> preferences) {
		this.preferences = preferences;
	}
	
	/** 
	 * Adds a Rule to knowledge
	 * 
	 * @param rule Rule
	 */
	public void addRule (Rule rule) {
		this.knowledge.add(rule);
	}
	
	/**
	 * Adds a Program to the knowledge
	 * 
	 * @param knowledge Program
	 */
	public void addKnowledge(Program knowledge) {
		for(Rule rule : knowledge) {
			this.knowledge.add(rule);			
		}
	}
	
	/**
	 * Adds a decision to Set of decisions
	 * 
	 * @param decision DLPLiteral
	 */
	public void addDecision (DLPLiteral decision) {
		this.decisions.add(decision);
	}
	
	/**
	 * adds Set of decisions to decisions
	 * 
	 * @param decisions2 HashSet
	 */
	public void addDecisions(HashSet<DLPLiteral> decisions2) {
		for(DLPLiteral literal : decisions2) {
			this.decisions.add(literal);	
		}
	}
	
	/**
	 * adds a preference to the Set of preferences
	 * 
	 * @param preference DLPLiteral
	 */
	public void addPreference (DLPLiteral preference) {
		this.preferences.add(preference);
	}
	
	/**
	 * adds Set of preferences to preferences
	 * 
	 * @param preferences HashSet
	 */
	
	public void addPreferences(HashSet<DLPLiteral> preferences) {
		for(DLPLiteral literal : preferences) {
			this.preferences.add(literal);
		}
	}
	
	/**
	 * the String of the knowledge of a Decision Making Problem
	 * @return knowledge String
	 */
	
	public String knowledgeToString() {
		String knowledge = new String();
		int i = 1;
		for (Rule rule : this.knowledge) {
			knowledge = knowledge.concat("r" + i + ": " + rule.toString() + "\n");
			i++;
		}
		return knowledge;
	}
	
	/**
	 * the String of the decisions of a Decision Making Problem
	 * @return decisions String
	 */
	
	public String decisionsToString() {
		String decisions = new String();
		int i = 1;
		for (DLPLiteral literal : this.decisions) {
			decisions = decisions.concat("d" + i + ": " + literal.toString() + "\n");
			i++;
		}
		return decisions;
	}
	
	/**
	 * the String of the preferences of a Decision Making Problem
	 * @return preferences String
	 */
	
	public String preferencesToString() {
		String preferences = new String();
		int i = 1;
		for (DLPLiteral literal : this.preferences) {
			preferences = preferences.concat("p" + i + ": " + literal.toString() + "\n");
			i++;
		}
		return preferences;
	}
	
	/**
	 * the String of a Decision Making Problem
	 * @return dm String
	 */
	public String toString() {
		String dm = this.knowledgeToString() + this.decisionsToString() + this.preferencesToString();
		return dm;
	}

	/**
	 * creates a subprogram for computing pessimistic decisions
	 * 
	 * @param dm DecisionMaking
	 * @return subProgram Program
	 */
	 public static Program subProgramPessimistic(DecisionMaking dm) {
		Program subProgram = dm.getKnowledge();
		subProgram.add(constraintsPreferences(dm));	
		subProgram.add(assumptions1(dm));
		subProgram.add(assumptions2(dm));
		subProgram.add(weakConstraints(dm));
		return subProgram;
	 }
	 
	 /**
	  * creates a subprogram for computing optimistic decisions
	  * 
	  * @param dm DecisionMaking
	  * @return subProgram Program
	  */
	 
	 public static Program subProgramOptimistic(DecisionMaking dm) {
		Program subProgram = dm.getKnowledge();
		subProgram.add(preferencesFacts(dm));	
		subProgram.add(assumptions1(dm));
		subProgram.add(assumptions2(dm));
		subProgram.add(weakConstraints(dm));
		return subProgram;
	 }
	 
	 /**
	  * creates a program that contains preferences as facts
	  * 
	  * @param dm DecisionMaking
	  * @return factProgram Program
	  */
	 
	public static Program preferencesFacts(DecisionMaking dm) {
		Program factProgram = new Program();
		for (DLPLiteral preference : dm.preferences) {
			Rule constraint = new Rule(preference);
			factProgram.add(constraint);
		}
		return factProgram;
	}
	
	/**
	 * creates a program that contains constraints for preferences
	 * 
	 * @param dm DecisionMaking
	 * @return constraintProgram Program
	 */
	
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
	
	/**
	 * creates program for ordered disjunction of assumptions
	 * (see paper for details)
	 * 
	 * @param dm DecisionMaking
	 * @return assumptionsProgram Program
	 */
	
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
	
	/**
	 * creates program for ordered disjunction of assumptions
	 * (see paper for details)
	 * 
	 * @param dm DecisionMaking
	 * @return assumptionsProgram Program
	 */
	
	public static Program assumptions2(DecisionMaking dm) {
		Program assumptionsProgram = new Program();
		for (DLPLiteral decision : dm.decisions) {
			DLPAtom ass_d = new DLPAtom("ass(" + decision.toString() + ")");
			Rule assumption1 = new Rule(decision, ass_d);
			assumptionsProgram.add(assumption1);		
		}
		return assumptionsProgram;
	}
	
	/**
	 * creates program with weak constraints for minimal sets of decisions
	 * 
	 * @param dm DecisionMaking
	 * @return weakConstraintsProgram Program
	 */
	
	public static Program weakConstraints(DecisionMaking dm) {
		Program weakConstraintsProgram = new Program();
		for(DLPLiteral decision : dm.decisions) {
			Rule rule = new Rule(decision);
			weakConstraintsProgram.add(rule);
		}
		return weakConstraintsProgram;
	}
}
