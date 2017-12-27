package EigeneKlassen;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import net.sf.tweety.lp.asp.syntax.DLPLiteral;
import net.sf.tweety.lp.asp.syntax.Program;
import net.sf.tweety.lp.asp.syntax.Rule;

public class DecisionMakingUncertainty {
	private PossibilisticProgram knowledge;
	private ArrayList<DLPLiteral> decisions;
	private PossibilisticLiteralSet preferences;
	private HashSet<Double> scale;
	
	public DecisionMakingUncertainty() {
		this.knowledge = new PossibilisticProgram();
		this.decisions = new ArrayList<DLPLiteral>();
		this.preferences = new PossibilisticLiteralSet();
		this.scale = new HashSet<Double>();
	}
	
	public PossibilisticProgram getKnowledge() {
		return knowledge;
	}
	public void setKnowledge(PossibilisticProgram knowledge) {
		this.knowledge = knowledge;
	}
	public ArrayList<DLPLiteral> getDecisions() {
		return decisions;
	}
	public void setDecisions(ArrayList<DLPLiteral> decision) {
		this.decisions = decision;
	}
	public PossibilisticLiteralSet getPreferences() {
		return preferences;
	}
	public void setPreferences(PossibilisticLiteralSet preferences) {
		this.preferences = preferences;
	}
	
	public void addScaleElement(Double necessity) {
		this.scale.add(necessity);
	}
	
	public void addRule (PossibilisticRule rule) {
		this.knowledge.add(rule);
		this.scale.add(rule.getNecessity());
	}
	
	public void addKnowledge(PossibilisticProgram knowledge) {
		for(PossibilisticRule rule : knowledge) {
			this.knowledge.add(rule);
			this.scale.add(rule.getNecessity());
		}
	}
	
	public void addDecision(DLPLiteral decision) {
		this.decisions.add(decision);
	}
	
	public void addDecisions(ArrayList<DLPLiteral> decisions) {
		for(DLPLiteral literal : decisions) {
			this.decisions.add(literal);	
		}
	}
	
	public void addPreference(PossibilisticLiteral literal) {
		this.preferences.add(literal);
		this.scale.add(literal.getNecessity());
	}
	
	public void addPreferences(ArrayList<PossibilisticLiteral> preferences) {
		for(PossibilisticLiteral literal : preferences) {
			this.preferences.add(literal);
			this.scale.add(literal.getNecessity());
		}
	}
	
	public String scaleToString() {
		String scale = "scale: ";
		for (Double necessity : this.scale) {
			scale = scale.concat(necessity + ", ");
		}
		return scale;
	}
	
	public String knowledgeToString() {
		String knowledge = new String();
		int i = 1;
		for (PossibilisticRule rule : this.knowledge) {
			knowledge = knowledge.concat("r" + i + ": (" + rule.getNecessity() + ", " + rule.toString() + "\n");
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
		for (PossibilisticLiteral literal : this.preferences) {
			preferences = preferences.concat("p" + i + ": (" + literal.getNecessity() + ", "+ literal.getLiteral().toString() + ")\n");
			i++;
		}
		return preferences;
	}
	
	public String toString() {
		String dm = this.knowledgeToString() + this.decisionsToString() + this.preferencesToString();
		return dm;
	}
}
