package EigeneKlassen;

import java.util.HashSet;
import java.util.Iterator;
import net.sf.tweety.lp.asp.solver.SolverException;
import net.sf.tweety.lp.asp.syntax.DLPLiteral;
import net.sf.tweety.lp.asp.syntax.Program;

public class Alg3DM {
	
	public PessimisticLabelAndUtility calculatePessimisticDecisionsDMU (DecisionMakingUncertainty dmu) throws SolverException {
		PessimisticLabelAndUtility pessimisticLabelAndUtility = new PessimisticLabelAndUtility();
		PessimisticLabelAndUtility pessimisticLabelAndUtility_subprograms = new PessimisticLabelAndUtility();
		Double pessimisticUtility = 0.0;
		boolean finish = false;
		Alg1DM alg1 = new Alg1DM();
		Double [] scaleValues = dmu.getScale().toArray();
		int i = scaleValues.length;
		Double upperbound = scaleValues[i-1];
		
		/* Erzeuge Programm ohne Sicherheitsgrade */
		
		DecisionMaking dm = new DecisionMaking();
		dm.addKnowledge(dmu.getKnowledge().projection());
		dm.addDecisions(dmu.getDecisions());
		dm.addPreferences(dmu.getPreferences().literalsNecessity(scaleValues[i-2]).projection());
		pessimisticLabelAndUtility_subprograms = alg1.calculatePessimisticDecisionsDM(dm);
		if (pessimisticLabelAndUtility_subprograms.getPessimisticLabel().size() > 0) {
			Iterator<Double> iterator = dmu.getScale().iterator();
			while(dmu.getScale().hasNext() && pessimisticUtility < 1.0 && !finish) {
				i--;
				pessimisticUtility = iterator.next();
				upperbound = scaleValues[i];
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
				pessimisticLabelAndUtility_subprograms = alg1.calculatePessimisticDecisionsDM(newDM);
				System.out.println("Pess Label Size: " + pessimisticLabelAndUtility.getPessimisticLabel().size() + " Label: " + pessimisticLabelAndUtility.getPessimisticLabel());
				if(pessimisticLabelAndUtility_subprograms.getPessimisticLabel().size() != 0) {
					pessimisticLabelAndUtility = pessimisticLabelAndUtility_subprograms;
					pessimisticLabelAndUtility.setPessimisticUtility(pessimisticUtility);
				}
				else{
					finish = true;
				}
			}
		}
		return pessimisticLabelAndUtility;
	}
}
