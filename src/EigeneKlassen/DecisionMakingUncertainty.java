package EigeneKlassen;

import java.util.Set;

import net.sf.tweety.lp.asp.syntax.DLPLiteral;

public class DecisionMakingUncertainty {
	private PossibilisticProgram knowledge;
	private Set<DLPLiteral> decision;
	private PossibilisticLiteralSet preferences;
	public PossibilisticProgram getKnowledge() {
		return knowledge;
	}
	public void setKnowledge(PossibilisticProgram knowledge) {
		this.knowledge = knowledge;
	}
	public Set<DLPLiteral> getDecision() {
		return decision;
	}
	public void setDecision(Set<DLPLiteral> decision) {
		this.decision = decision;
	}
	public PossibilisticLiteralSet getPreferences() {
		return preferences;
	}
	public void setPreferences(PossibilisticLiteralSet preferences) {
		this.preferences = preferences;
	}
	
}
