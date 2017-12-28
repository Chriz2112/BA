package EigeneKlassen;

import java.util.HashSet;
import java.util.Iterator;

import net.sf.tweety.lp.asp.syntax.DLPLiteral;

public class DecisionMakingUncertainty {
	private PossibilisticProgram knowledge;
	private HashSet<DLPLiteral> decisions;
	private PossibilisticLiteralSet preferences;
	private Scale scale;
	
	/**
	 * creates empty Instance of DMU
	 */
	
	public DecisionMakingUncertainty() {
		this.knowledge = new PossibilisticProgram();
		this.decisions = new HashSet<DLPLiteral>();
		this.preferences = new PossibilisticLiteralSet();
		this.scale = new Scale();
	}
	
	/**
	 * creates non-empty Instance of DMU
	 *
	 * @param knowledge PossibilisticProgram
	 * @param decisions HashSet
	 * @param preferences PossibilisticLiteralSet
	 * @param scale Scale
	 */
	
	public DecisionMakingUncertainty(PossibilisticProgram knowledge, HashSet<DLPLiteral> decisions, PossibilisticLiteralSet preferences, Scale scale) {
		this.knowledge = knowledge;
		this.decisions = decisions;
		this.preferences = preferences;
		this.scale= scale;
	}
	
	public Scale getScale() {
		return scale;
	}

	public void setScale(Scale scale) {
		this.scale = scale;
	}

	public PossibilisticProgram getKnowledge() {
		return knowledge;
	}
	public void setKnowledge(PossibilisticProgram knowledge) {
		this.knowledge = knowledge;
	}
	public HashSet<DLPLiteral> getDecisions() {
		return decisions;
	}
	public void setDecisions(HashSet<DLPLiteral> decision) {
		this.decisions = decision;
	}
	public PossibilisticLiteralSet getPreferences() {
		return preferences;
	}
	public void setPreferences(PossibilisticLiteralSet preferences) {
		this.preferences = preferences;
	}
	
	
	/**
	 * Adds Element to Scale
	 * 
	 * @param necessity Double
	 */
	public void addScaleElement(Double necessity) {
		this.scale.add(necessity);
	}
	
	/**
	 * adds a possibilistic Rule to knowledge
	 * 
	 * @param rule PossibilisticRule
	 */
	
	public void addRule (PossibilisticRule rule) {
		this.knowledge.addRule(rule);
		this.scale.add(rule.getNecessity());
	}
	
	/**
	 * adds Possibilistic Program to knowledge
	 * 
	 * @param knowledge PossibilisticProgram
	 */
	public void addKnowledge(PossibilisticProgram knowledge) {
		for(PossibilisticRule rule : knowledge) {
			this.knowledge.addRule(rule);
			this.scale.add(rule.getNecessity());
		}
	}
	
	
	/**
	 * adds a decision to set of decisions
	 * 
	 * @param decision DLPLiteral
	 */
	public void addDecision(DLPLiteral decision) {
		this.decisions.add(decision);
	}
	
	/**
	 * adds a set of decisions to decisions
	 * 
	 * @param decisions HashSet
	 */
	
	public void addDecisions(HashSet<DLPLiteral> decisions) {
		for(DLPLiteral literal : decisions) {
			this.decisions.add(literal);	
		}
	}
	
	/**
	 * adds a Possibilistic preference to preferences
	 * 
	 * @param literal PossibilisticLiteral
	 */
	
	public void addPreference(PossibilisticLiteral literal) {
		this.preferences.add(literal);
		this.scale.add(literal.getNecessity());
	}
	
	/**
	 * adds a set of possibilistic preferences to preferences
	 * 
	 * @param preferences HashSet
	 */
	
	public void addPreferences(HashSet<PossibilisticLiteral> preferences) {
		for(PossibilisticLiteral literal : preferences) {
			this.preferences.add(literal);
			this.scale.add(literal.getNecessity());
		}
	}
	
	/**
	 * converts scale to string
	 * 
	 * @return scale String
	 */
	
	public String scaleToString() {
		String scale = "scale: ";
		Iterator<Double> it = this.scale.iterator();
		while(it.hasNext()) {
			scale = scale.concat(it.next() + ", ");
		}
		return scale;
	}
	
	
	/**
	 * converts knowledge to string
	 * 
	 * @return knowledge String
	 */
	public String knowledgeToString() {
		String knowledge = new String();
		int i = 1;
		for (PossibilisticRule rule : this.knowledge) {
			knowledge = knowledge.concat("r" + i + ": (" + rule.getNecessity() + ", " + rule.getRule().toString() + ") \n");
			i++;
		}
		return knowledge;
	}
	
	/**
	 * converts decisions to string
	 * 
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
	 * converts preferences to string
	 * 
	 * @return preferences String
	 */
	
	public String preferencesToString() {
		String preferences = new String();
		int i = 1;
		for (PossibilisticLiteral literal : this.preferences) {
			preferences = preferences.concat("p" + i + ": (" + literal.getNecessity() + ", "+ literal.getLiteral().toString() + ")\n");
			i++;
		}
		return preferences;
	}
	
	/**
	 * converts DMU to string
	 * 
	 * @return dm String
	 */
	
	public String toString() {
		String dm = this.knowledgeToString() + this.decisionsToString() + this.preferencesToString();
		return dm;
	}
}
