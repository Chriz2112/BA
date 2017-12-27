package EigeneKlassen;

import java.util.HashSet;
import java.util.Iterator;

import net.sf.tweety.lp.asp.solver.SolverException;
import net.sf.tweety.lp.asp.syntax.DLPLiteral;
import net.sf.tweety.lp.asp.syntax.Program;

public class Alg4DM {
	public OptimisticLabelAndUtility calculateOptimisticDecisionsDMU (DecisionMakingUncertainty dmu) throws SolverException {
		OptimisticLabelAndUtility optimisticLabelAndUtility = new OptimisticLabelAndUtility();
		Double optimisticUtility = 0.0;
		Alg2DM alg2 = new Alg2DM();
		Double [] scaleValues = dmu.getScale().toArray();
		int i = scaleValues.length;
		Double upperbound = scaleValues[i-1];
		
		/* Erzeuge Programm ohne Sicherheitsgrade */
		
		DecisionMaking dm = new DecisionMaking();
		dm.addKnowledge(dmu.getKnowledge().projection());
		dm.addDecisions(dmu.getDecisions());
		dm.addPreferences(dmu.getPreferences().projection());
		optimisticLabelAndUtility = alg2.calculateOptimisticDecisionsDM(dm);
		if (optimisticLabelAndUtility.getOptimisticLabel().size() > 0) {
			return optimisticLabelAndUtility;
		}
		else {
			Iterator<Double> iterator = dmu.getScale().iterator();
			while(dmu.getScale().hasNext() && optimisticLabelAndUtility.getOptimisticLabel().size() == 0) {
				i--;
				optimisticUtility = iterator.next();
				upperbound = scaleValues[i-1];
				DecisionMaking newDM = new DecisionMaking();
				Program knowledge = new Program();
				HashSet<DLPLiteral> preferences = new HashSet<DLPLiteral>();
				knowledge.add(dmu.getKnowledge().programNecessity(optimisticUtility).projection());
				PossibilisticLiteralSet possPreferences = new PossibilisticLiteralSet();
				possPreferences.add(dmu.getPreferences().literalsNecessity(optimisticUtility));
				preferences.addAll(possPreferences.projection());
				newDM.addKnowledge(knowledge);
				newDM.addDecisions(dmu.getDecisions());
				newDM.addPreferences(preferences);
				
				optimisticLabelAndUtility = alg2.calculateOptimisticDecisionsDM(newDM);
				System.out.println("Pess Label Size: " + optimisticLabelAndUtility.getOptimisticLabel().size() + " Label: " + optimisticLabelAndUtility.getOptimisticLabel());
			}
		}
		optimisticLabelAndUtility.setPessimisticUtility(upperbound);
		return optimisticLabelAndUtility;
	}
}
