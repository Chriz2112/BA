package EigeneKlassen;

import java.util.HashSet;

import net.sf.tweety.lp.asp.solver.DLV;
import net.sf.tweety.lp.asp.solver.SolverException;
import net.sf.tweety.lp.asp.syntax.DLPLiteral;
import net.sf.tweety.lp.asp.syntax.Program;
import net.sf.tweety.lp.asp.util.AnswerSet;
import net.sf.tweety.lp.asp.util.AnswerSetList;

/**
 * this class contains algorithm 2 for
 * calculating pessimistic decisions for a DM
 * 
 * @author Christoph Meyer
 *
 */

public class Alg2DM {
	
	/**
	 * calculates optimistic decisions of a decision making problem
	 * @param dm DecisionMaking
	 * @return optimisticLabelAndUtility OptimisticLabelAndUtility
	 * @throws SolverException if answer sets can't be computed
	 */
	public OptimisticLabelAndUtility calculateOptimisticDecisionsDM (DecisionMaking dm) throws SolverException {
		OptimisticLabelAndUtility optimisticLabelAndUtility = new OptimisticLabelAndUtility();
		OptimisticLabel optimisticLabel = new OptimisticLabel();
		Double optimisticUtility = 0.0;
		Program subprogram = dm.subProgramOptimistic(dm);
	    DLV dlv = new DLV("/Users/christophmeyer/Desktop/dlv.bin");
	    AnswerSetList answerSets = dlv.computeModels(subprogram, 9999);
	    if (answerSets.size() > 0) {
	    		optimisticUtility = 1.0;
	    		for (AnswerSet answerSet : answerSets) {
	    			optimisticLabel.add(getDecisionLiterals(dm.getDecisions(), answerSet));
	    		}
	    }
	    optimisticLabelAndUtility.setOptimisticLabel(optimisticLabel.getSmallestSets());
	    optimisticLabelAndUtility.setOptimisticUtility(optimisticUtility);
	    return optimisticLabelAndUtility;
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
				if (!answerset.toString().contains("-ass(" + decisionLiteral.toString() + ")") && answerset.toString().contains("ass(" + decisionLiteral.toString() + ")")){
					decisionLiterals.add(decisionLiteral);
				}
		}
		return decisionLiterals;
	}
}
