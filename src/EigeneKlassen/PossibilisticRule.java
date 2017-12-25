package EigeneKlassen;
import net.sf.tweety.lp.asp.syntax.Rule;

public class PossibilisticRule {
	private double necessity;
	private Rule rule;
	
	public PossibilisticRule(double necessity, Rule rule){
		this.necessity = necessity;
		this.rule = rule;
	}

	public double getNecessity() {
		return necessity;
	}

	public void setNecessity(double necessity) {
		this.necessity = necessity;
	}

	public Rule getRule() {
		return rule;
	}

	public void setRule(Rule rule) {
		this.rule = rule;
	}
}
