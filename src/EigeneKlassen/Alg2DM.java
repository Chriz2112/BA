package EigeneKlassen;

import java.util.HashSet;

import net.sf.tweety.lp.asp.solver.DLV;
import net.sf.tweety.lp.asp.solver.SolverException;
import net.sf.tweety.lp.asp.syntax.DLPLiteral;
import net.sf.tweety.lp.asp.syntax.Program;
import net.sf.tweety.lp.asp.util.AnswerSet;
import net.sf.tweety.lp.asp.util.AnswerSetList;

public class Alg2DM {
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
	    optimisticLabelAndUtility.setOptimisticLabel(optimisticLabel);
	    optimisticLabelAndUtility.setOptimisticUtility(optimisticUtility);
	    return optimisticLabelAndUtility;
	}
	
	public static HashSet<DLPLiteral> getDecisionLiterals(HashSet<DLPLiteral> treeSet, AnswerSet answerset){
		HashSet<DLPLiteral> decisionLiterals = new HashSet<DLPLiteral>();
		for (DLPLiteral decisionLiteral : treeSet) {
				if (answerset.toString().contains("ass(" + decisionLiteral.toString() + ")")){
					decisionLiterals.add(decisionLiteral);
				}
		}
		return decisionLiterals;
	}
}
