package EigeneKlassen;
import net.sf.tweety.lp.asp.syntax.Rule;

/**
 * this class is for modeling possibilistic rules
 * 
 * @author Christoph Meyer
 *
 */

public class PossibilisticRule {
	private double necessity;
	private Rule rule;
	
	/**
	 * creates new possibilistic rule 
	 * @param necessity Double
	 * @param rule Rule
	 */
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
