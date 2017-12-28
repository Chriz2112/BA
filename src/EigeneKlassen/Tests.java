package EigeneKlassen;

import java.util.*;
import org.junit.Test;

import net.sf.tweety.lp.asp.solver.SolverException;
import net.sf.tweety.lp.asp.syntax.DLPAtom;
import net.sf.tweety.lp.asp.syntax.DLPElement;
import net.sf.tweety.lp.asp.syntax.DLPLiteral;
import net.sf.tweety.lp.asp.syntax.DLPNeg;
import net.sf.tweety.lp.asp.syntax.Rule;

import static org.junit.Assert.*;
public class Tests {
	/**
	 * Test for Algorithm 3 with Example 33 and Example 36
	 * Pessimistic Label should be {{c,d}} and pessimistic utility should be 0.5
	 *
	 * @throws SolverException
	 */
	@Test
	public void testAlg3DMU() throws SolverException{
		DecisionMakingUncertainty dmu = new DecisionMakingUncertainty();
		Alg3DMU alg3dmu = new Alg3DMU();
		dmu = newDMU();
		PessimisticLabelAndUtility p = alg3dmu.calculatePessimisticDecisionsDMU(dmu);
		PessimisticLabel labelP = p.getPessimisticLabel();
		Double utilityP = p.getPessimisticUtility();
		PessimisticLabelAndUtility solution = getPLandUtility();
		assertEquals(solution.getPessimisticLabel().toString(), labelP.toString());
		assertEquals(solution.getPessimisticUtility(), utilityP);
	}
	
	/**
	 * Test for Algorithm 4 with Example 33 and Example 36
	 * Pessimistic Label should be {{}} and pessimistic utility should be 1.0
	 *
	 * @throws SolverException
	 */
	@Test
	public void testAlg4DMU() throws SolverException{
		DecisionMakingUncertainty dmu = new DecisionMakingUncertainty();
		Alg4DMU alg4dmu = new Alg4DMU();
		dmu = newDMU();
		OptimisticLabelAndUtility p = alg4dmu.calculateOptimisticDecisionsDMU(dmu);
		OptimisticLabel labelP = p.getOptimisticLabel();
		Double utilityP = p.getOptimisticUtility();
		OptimisticLabelAndUtility solution = getOLandUtility();
		assertEquals(solution.getOptimisticLabel().toString(), labelP.toString());
		assertEquals(solution.getOptimisticUtility(), utilityP);
	}
	
	public static DecisionMakingUncertainty newDMU() {
		DecisionMakingUncertainty dmu = new DecisionMakingUncertainty();
		PossibilisticProgram possKnowledge = new PossibilisticProgram();
		Rule r1_ = new Rule (new DLPAtom("a"), new DLPAtom("b"));
		Rule r2_ = new Rule (new DLPAtom("b"), new DLPAtom("c"));
		Rule r3_ = new Rule (new DLPAtom("e"), new DLPAtom("d"));
		Rule r4_ = new Rule(new DLPNeg(new DLPAtom ("e")));
		PossibilisticRule pr1 = new PossibilisticRule(1.0, r1_);
		PossibilisticRule pr2 = new PossibilisticRule(0.8, r2_);
		PossibilisticRule pr3 = new PossibilisticRule(0.5, r3_);
	    HashSet<DLPElement> bodyR1 = new HashSet<DLPElement>();
	    bodyR1.add(new DLPAtom("c"));
	    bodyR1.add(new DLPAtom("d"));
	    r4_.addPremises(bodyR1);
	    PossibilisticRule pr4 = new PossibilisticRule(0.2, r4_);
	    possKnowledge.addRule(pr1);
	    possKnowledge.addRule(pr2);
	    possKnowledge.addRule(pr3);
	    possKnowledge.addRule(pr4);
	    
	    dmu.addKnowledge(possKnowledge);
	    dmu.addDecision(new DLPAtom("c"));
	    dmu.addDecision(new DLPAtom("d"));
	    
	    PossibilisticLiteral pl1 = new PossibilisticLiteral(1.0, new DLPAtom("a"));
	    PossibilisticLiteral pl2 = new PossibilisticLiteral(0.8, new DLPAtom("b"));
	    PossibilisticLiteral pl3 = new PossibilisticLiteral(0.8, new DLPAtom("e"));
	    
	    dmu.addPreference(pl1);
	    dmu.addPreference(pl2);
	    dmu.addPreference(pl3);
	    
	    return dmu;
	}
	
	public static PessimisticLabelAndUtility getPLandUtility() {
		PessimisticLabelAndUtility plAndUtility = new PessimisticLabelAndUtility();
		PessimisticLabel label = new PessimisticLabel();
		HashSet<DLPLiteral> setCD = new HashSet<DLPLiteral>();
		DLPLiteral c = new DLPAtom ("c");
		DLPLiteral d = new DLPAtom ("d");
		setCD.add(c);
		setCD.add(d);
		label.addLiterals(setCD);
		plAndUtility.setPessimisticLabel(label);
		plAndUtility.setPessimisticUtility(0.5);
		return plAndUtility;
	}
	
	public static OptimisticLabelAndUtility getOLandUtility() {
		OptimisticLabelAndUtility olAndUtility = new OptimisticLabelAndUtility();
		OptimisticLabel label = new OptimisticLabel();
		HashSet<DLPLiteral> emptyset = new HashSet<DLPLiteral>();
		label.add(emptyset);
		olAndUtility.setOptimisticLabel(label);
		olAndUtility.setOptimisticUtility(1.0);
		return olAndUtility;
	}
}
