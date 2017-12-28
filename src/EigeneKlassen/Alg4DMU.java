package EigeneKlassen;

import java.util.HashSet;
import java.util.Iterator;

import net.sf.tweety.lp.asp.solver.SolverException;
import net.sf.tweety.lp.asp.syntax.DLPLiteral;
import net.sf.tweety.lp.asp.syntax.Program;

/**
 * this class contains the algorithm 4 for
 * calculating pessimistic decisions for a DMU
 * 
 * @author Christoph Meyer
 *
 */

public class Alg4DMU {
	public OptimisticLabelAndUtility calculateOptimisticDecisionsDMU (DecisionMakingUncertainty dmu) throws SolverException {
		OptimisticLabelAndUtility optimisticLabelAndUtility = new OptimisticLabelAndUtility();
		
		/*
		 * initialize utility and upper bound
		 */
		Double optimisticUtility = 0.0;
		Alg2DM alg2 = new Alg2DM();
		Double [] scaleValues = dmu.getScale().toArray();
		
		int i = scaleValues.length;
		Double upperbound = scaleValues[i-1];
		
		/*
		 * create subProgram and check, if there is at least one answer set
		 */
		
		DecisionMaking dm = new DecisionMaking();
		dm.addKnowledge(dmu.getKnowledge().projection());
		dm.addDecisions(dmu.getDecisions());
		dm.addPreferences(dmu.getPreferences().projection());
		optimisticLabelAndUtility = alg2.calculateOptimisticDecisionsDM(dm);
		if (optimisticLabelAndUtility.getOptimisticLabel().size() > 0) {
			/*
			 * there exists one answer set
			 */
			optimisticLabelAndUtility.setOptimisticUtility(upperbound);
			return optimisticLabelAndUtility;
		}
		else {
			/*
			 * there exists no answer set
			 */
			Iterator<Double> iterator = dmu.getScale().iterator();
			while(dmu.getScale().hasNext() && optimisticLabelAndUtility.getOptimisticLabel().size() == 0) {
				/*
				 * increase utility and lower upper bound
				 */
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
				/*
				 * create new subProgram
				 */
				optimisticLabelAndUtility = alg2.calculateOptimisticDecisionsDM(newDM);
			}
		}
		optimisticLabelAndUtility.setOptimisticUtility(upperbound);
		return optimisticLabelAndUtility;
	}
}
