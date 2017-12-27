package EigeneKlassen;

import java.util.ArrayList;

import net.sf.tweety.lp.asp.solver.DLV;
import net.sf.tweety.lp.asp.solver.SolverException;
import net.sf.tweety.lp.asp.syntax.DLPLiteral;
import net.sf.tweety.lp.asp.syntax.Program;
import net.sf.tweety.lp.asp.util.AnswerSet;
import net.sf.tweety.lp.asp.util.AnswerSetList;

public class Alg2DM {
	public OptimisticLabelAndUtility calculatePessimisticDecisionsDM (DecisionMaking dm) throws SolverException {
		OptimisticLabelAndUtility optimisticLabelAndUtility = new OptimisticLabelAndUtility();
		OptimisticLabel optimisticLabel = new OptimisticLabel();
		Double optimisticUtility = 0.0;
		Program subprogram = dm.subProgramOptimistic();
	    DLV dlv = new DLV("/Users/christophmeyer/Desktop/dlv.bin");
	    AnswerSetList answerSets = dlv.computeModels(subprogram, 9999);
	    if (answerSets.size() > 0) {
	    	optimisticUtility = 1.0;
	    		for (AnswerSet answerSet : answerSets) {
	    			optimisticLabel.add(getDecisionLiterals(dm.getDecisions(), answerSet));
	    		}
	    }
	    optimisticLabelAndUtility.setOptimisticLabel(optimisticLabel);
	    optimisticLabelAndUtility.setPessimisticUtility(optimisticUtility);
	    return optimisticLabelAndUtility;
	}
	
	public static ArrayList<DLPLiteral> getDecisionLiterals(ArrayList<DLPLiteral> decisions, AnswerSet answerset){
		ArrayList<DLPLiteral> decisionLiterals = new ArrayList<DLPLiteral>();
		for (DLPLiteral decisionLiteral : decisions) {
			//if (! (decisionLiteral.toString().startsWith("ass") || decisionLiteral.toString().startsWith("-ass"))){
				if (answerset.getLiteralsWithName("ass(" + decisionLiteral + ")").size() > 0) {
					decisionLiterals.add(decisionLiteral);
				}
			//}
		}
		return decisionLiterals;
	}
}
