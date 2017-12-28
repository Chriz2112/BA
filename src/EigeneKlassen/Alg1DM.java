package EigeneKlassen;

import java.util.HashSet;

import net.sf.tweety.lp.asp.solver.DLV;
import net.sf.tweety.lp.asp.solver.SolverException;
import net.sf.tweety.lp.asp.syntax.DLPLiteral;
import net.sf.tweety.lp.asp.syntax.Program;
import net.sf.tweety.lp.asp.util.AnswerSet;
import net.sf.tweety.lp.asp.util.AnswerSetList;

/**
 * this class contains algorithm 1 for
 * calculating pessimistic decisions for a DM
 * 
 * @author Christoph Meyer
 *
 */

public class Alg1DM {
	
	/**
	 * calculates pessimistic decisions of a decision making problem
	 * @param dm DecisionMaking
	 * @return pessimisticLabelAndUtility PessimisticLabelAndUtility
	 * @throws SolverException if answer sets can't be computed
	 */
	public PessimisticLabelAndUtility calculatePessimisticDecisionsDM (DecisionMaking dm) throws SolverException {
		PessimisticLabelAndUtility pessimisticLabelAndUtility = new PessimisticLabelAndUtility();
		PessimisticLabel pessimisticLabel = new PessimisticLabel();
		Double pessimisticUtility = 0.0;
		Program subprogram = dm.subProgramPessimistic(dm);
	    DLV dlv = new DLV("/Users/christophmeyer/Desktop/dlv.bin");
	    AnswerSetList answerSets = dlv.computeModels(subprogram, 9999);
	    if (answerSets.size() > 0) {
	    	pessimisticUtility = 1.0;
	    		for (AnswerSet answerSet : answerSets) {
	    			pessimisticLabel.addLiterals(getDecisionLiterals(dm.getDecisions(), answerSet));
	    	
	    		}
	    }
	    pessimisticLabelAndUtility.setPessimisticLabel(pessimisticLabel.getSmallestSets());
	    pessimisticLabelAndUtility.setPessimisticUtility(pessimisticUtility);
	    return pessimisticLabelAndUtility;
	}
	
	/**
	 * gets decision literals out of answer set 
	 * @param decisions HashSet
	 * @param answerset AnswerSet
	 * @return decisionLiterals HashSet
	 */
	
	public static HashSet<DLPLiteral> getDecisionLiterals(HashSet<DLPLiteral> decisions, AnswerSet answerset){
		HashSet<DLPLiteral> decisionLiterals = new HashSet<DLPLiteral>();
		for (DLPLiteral decisionLiteral : decisions) {
				if (answerset.toString().contains("ass(" + decisionLiteral.toString() + ")")){
					decisionLiterals.add(decisionLiteral);
				}
			//}
		}
		return decisionLiterals;
	}
}
