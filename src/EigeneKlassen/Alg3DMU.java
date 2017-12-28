package EigeneKlassen;

import java.util.HashSet;
import java.util.Iterator;
import net.sf.tweety.lp.asp.solver.SolverException;
import net.sf.tweety.lp.asp.syntax.DLPLiteral;
import net.sf.tweety.lp.asp.syntax.Program;

public class Alg3DMU {
	
	/**
	 * calculates pessimistic decisions for DMU
	 * 
	 * @param dmu DecisionMakingUncertainty
	 * @return pessimisticLabelAndUtility PessimisticLabelAndUtility
	 * @throws SolverException if answer set can't be computed
	 */
	
	public PessimisticLabelAndUtility calculatePessimisticDecisionsDMU (DecisionMakingUncertainty dmu) throws SolverException {
		PessimisticLabelAndUtility pessimisticLabelAndUtility = new PessimisticLabelAndUtility();
		PessimisticLabelAndUtility pessimisticLabelAndUtility_subprograms = new PessimisticLabelAndUtility();
		/*
		 * initialize utility and upper bound
		 */
		Double pessimisticUtility = 0.0;
		boolean finish = false;
		Alg1DM alg1 = new Alg1DM();
		Double [] scaleValues = dmu.getScale().toArray();
		int i = scaleValues.length;
		Double upperbound = scaleValues[i-1];
		
		/*
		 * create subProgram and check, if most important preference can be satisfied
		 */
		
		DecisionMaking dm = new DecisionMaking();
		dm.addKnowledge(dmu.getKnowledge().projection());
		dm.addDecisions(dmu.getDecisions());
		dm.addPreferences(dmu.getPreferences().literalsNecessity(scaleValues[i-2]).projection());
		pessimisticLabelAndUtility_subprograms = alg1.calculatePessimisticDecisionsDM(dm);
		if (pessimisticLabelAndUtility_subprograms.getPessimisticLabel().size() > 0) {
			Iterator<Double> iterator = dmu.getScale().iterator();
			while(dmu.getScale().hasNext() && pessimisticUtility < 1.0 && !finish) {
				/*
				 * increase utility, lower upper bound
				 */
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
				/*
				 * create new subProgram
				 */
				newDM.addKnowledge(knowledge);
				newDM.addDecisions(dmu.getDecisions());
				newDM.addPreferences(preferences);
				/*
				 * calculate pessimistic label and utility for new sub program
				 */
				pessimisticLabelAndUtility_subprograms = alg1.calculatePessimisticDecisionsDM(newDM);
				System.out.println("Pess Label Size: " + pessimisticLabelAndUtility.getPessimisticLabel().size() + " Label: " + pessimisticLabelAndUtility.getPessimisticLabel());
				/*
				 * label for new subProgram was not empty
				 */
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
