package EigeneKlassen;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

import net.sf.tweety.lp.asp.solver.DLV;
import net.sf.tweety.lp.asp.solver.SolverException;
import net.sf.tweety.lp.asp.syntax.DLPLiteral;
import net.sf.tweety.lp.asp.syntax.Program;
import net.sf.tweety.lp.asp.util.AnswerSet;
import net.sf.tweety.lp.asp.util.AnswerSetList;

public class Alg3DM {
	
	public PessimisticLabelAndUtility calculatePessimisticDecisionsDMU (DecisionMakingUncertainty dmu) throws SolverException {
		PessimisticLabelAndUtility pessimisticLabelAndUtility = new PessimisticLabelAndUtility();
		PessimisticLabel pessimisticLabel = new PessimisticLabel();
		Double pessimisticUtility = 0.0;
		Double upperbound = 1.0;
		boolean finish = false;
		Alg1DM alg1 = new Alg1DM();
		Double [] scaleValues = dmu.getScale().toArray();
		
		/* Erzeuge Programm ohne Sicherheitsgrade */
		
		DecisionMaking dm = new DecisionMaking();
		dm.addKnowledge(dmu.getKnowledge().projection());
		dm.addDecisions(dmu.getDecisions());
		dm.addPreferences(dmu.getPreferences().projection());
		int i = scaleValues.length;
		pessimisticLabelAndUtility = alg1.calculatePessimisticDecisionsDM(dm);
		if (pessimisticLabelAndUtility.getPessimisticLabel().size() > 0) {
			Iterator<Double> iterator = dmu.getScale().iterator();
			i--;
			upperbound = scaleValues[i-1];
			while(dmu.getScale().hasNext() && pessimisticUtility < 1.0 && !finish) {
				pessimisticUtility = iterator.next();
				
				DecisionMaking newDM = new DecisionMaking();
				Program knowledge = new Program();
				HashSet<DLPLiteral> preferences = new HashSet<DLPLiteral>();
				knowledge.add(dmu.getKnowledge().programNecessity(pessimisticUtility).projection());
				PossibilisticLiteralSet possPreferences = new PossibilisticLiteralSet();
				possPreferences.add(dmu.getPreferences().literalsNecessity(upperbound));
				preferences.addAll(possPreferences.projection());
				newDM.addKnowledge(knowledge);
				newDM.addDecisions(dmu.getDecisions());
				newDM.addPreferences(preferences);
				pessimisticLabelAndUtility = alg1.calculatePessimisticDecisionsDM(newDM);
				if(pessimisticLabelAndUtility.getPessimisticLabel().size() == 0) {
					finish = true;
				}
			}
		}
		return pessimisticLabelAndUtility;
	}
}
