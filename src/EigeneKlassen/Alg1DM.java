package EigeneKlassen;

import java.util.HashSet;

import net.sf.tweety.lp.asp.solver.DLV;
import net.sf.tweety.lp.asp.solver.SolverException;
import net.sf.tweety.lp.asp.syntax.DLPLiteral;
import net.sf.tweety.lp.asp.syntax.Program;
import net.sf.tweety.lp.asp.util.AnswerSet;
import net.sf.tweety.lp.asp.util.AnswerSetList;

public class Alg1DM {
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
	    pessimisticLabelAndUtility.setPessimisticLabel(pessimisticLabel);
	    pessimisticLabelAndUtility.setPessimisticUtility(pessimisticUtility);
	    return pessimisticLabelAndUtility;
	}
	
	public static HashSet<DLPLiteral> getDecisionLiterals(HashSet<DLPLiteral> treeSet, AnswerSet answerset){
		HashSet<DLPLiteral> decisionLiterals = new HashSet<DLPLiteral>();
		for (DLPLiteral decisionLiteral : treeSet) {
				if (answerset.toString().contains("ass(" + decisionLiteral.toString() + ")")){
					decisionLiterals.add(decisionLiteral);
				}
			//}
		}
		return decisionLiterals;
	}
}
