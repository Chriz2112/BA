package EigeneKlassen;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;

import net.sf.tweety.logics.commons.syntax.Predicate;
import net.sf.tweety.lp.asp.solver.DLV;
import net.sf.tweety.lp.asp.solver.SolverException;
import net.sf.tweety.lp.asp.syntax.DLPAtom;
import net.sf.tweety.lp.asp.syntax.DLPElement;
import net.sf.tweety.lp.asp.syntax.DLPLiteral;
import net.sf.tweety.lp.asp.syntax.DLPNeg;
import net.sf.tweety.lp.asp.syntax.DLPNot;
import net.sf.tweety.lp.asp.syntax.DLPPredicate;
import net.sf.tweety.lp.asp.syntax.Program;
import net.sf.tweety.lp.asp.syntax.Rule;
import net.sf.tweety.lp.asp.util.AnswerSet;
import net.sf.tweety.lp.asp.util.AnswerSetList;
import scala.collection.SortedSet;

public class AtomTest {
    public DLPAtom atom;
    
    public AtomTest()
    {
    	    atom = new DLPAtom();
    }
    
    public DLPAtom getAtom() {
		return atom;
	}

	public void setAtom(DLPAtom atom) {
		this.atom = atom;
	}

	public static void main (String args []) throws SolverException {
		testProgramm();
    }
    
    public static void printeAtom (DLPAtom atom) {
    	    DLPPredicate predicate = new DLPPredicate ("b");
    	     String pred = atom.toString();
    	     System.out.println(pred);
    	     atom.setPredicate(predicate);
    	     pred = atom.toString();
    	     System.out.println(pred);    	     
    }
    
    public void testLiteral () {
    	    DLPAtom atom = new DLPAtom("a");
    	    DLPNeg negLiteral = new DLPNeg (atom);
    }
    
    public static void testRegel () {
    	    List<DLPLiteral> dlpHeads = new ArrayList();
    	    DLPLiteral head1 = new DLPAtom("a");
    	    DLPLiteral head2 = new DLPAtom("b");
    	    dlpHeads.add(head1);
    	    dlpHeads.add(head2);
    	    DLPLiteral body1 = new DLPNeg("c");
    	    DLPLiteral body2 = new DLPNeg("d");
    	    DLPNot body3 = new DLPNot(body1);
    	    List<DLPElement> body = new ArrayList();
    	    body.add(body1);
    	    body.add(body2);
    	    body.add(body3);
    	    Rule r = new Rule (dlpHeads, body);
    	    java.util.SortedSet<DLPLiteral> set = r.getLiterals();
    	    System.out.println("Alle Literale aus r:");
    	    Object[] arr = set.toArray();
    	    for (int i = 0; i < arr.length; i++) {
    	    	    System.out.println("Literal:" + (i+1) + " " + arr[i]);
    	    }
    	    System.out.println("Kopf von r: " + r.getConclusion());
    	    Program p = new Program();
    	    p.add(r);
    }
    
    public static void testProgramm () throws SolverException {
    	    /*ArrayList<DLPLiteral> dlpHeadsR1 = new ArrayList<DLPLiteral>();
    	    ArrayList<DLPLiteral> dlpHeadsR2 = new ArrayList<DLPLiteral>();
    	    ArrayList<DLPLiteral> dlpHeadsR3 = new ArrayList<DLPLiteral>();
    	    DLPLiteral head1 = new DLPAtom("a");
    	    DLPLiteral head2 = new DLPAtom("b");
    	    DLPLiteral head3 = new DLPAtom("c");
    	    	DLPLiteral head4 = new DLPAtom("d");
    	    //System.out.println(head3.toString());
    	    dlpHeadsR1.add(head1);
    	    dlpHeadsR1.add(head4);
    	    DLPLiteral body1 = new DLPAtom("b");
    	    DLPNot body2 = new DLPNot(head2);
    	    DLPNot body3 = new DLPNot(head3);
    	    DLPNot body4 = new DLPNot (head1);
    	    DLPNot body5 = new DLPNot(head4);
    	    ArrayList<DLPElement> bodyR1 = new ArrayList<DLPElement>();
    	    bodyR1.add(head2);
    	    bodyR1.add(body3);
    	    Rule r = new Rule (dlpHeadsR1, bodyR1);
    	    ArrayList<DLPElement> bodyR2 = new ArrayList<DLPElement>();
    	    bodyR2.add(head2);
    	    bodyR2.add(body4);
    	    bodyR2.add(body5);
    	    Rule r2 = new Rule (head3, bodyR2);
    	    Rule r3 = new Rule (head2);
    	    
    	    Program p = new Program();
    	    
    	    p.add(r);
    	    p.add(r2);
    	    p.add(r3);
    	    
    	    System.out.println(p.toString());
    	    
    	    ArrayList<PossibilisticRule> rules = new ArrayList<PossibilisticRule> ();
    	    PossibilisticRule possRule = new PossibilisticRule(0.5, r);
    	    String test = possRule.getRule().toString();
    	    //System.out.println(test);
    	    PossibilisticRule possRule2 = new PossibilisticRule (1, r);
    	    PossibilisticRule possRule3 = new PossibilisticRule (0.2, r);
    	    rules.add(possRule);
    	    rules.add(possRule2);
       	PossibilisticProgram possProgram = new PossibilisticProgram(rules);
    	    //possProgram.add(possRule);
    	    possProgram.add(possRule3);
    	    //String test2 = possProgram.toString();
    	    //System.out.println(test2);
    	    //possProgram.add(possRule2);
    	    //String prog = possProgram.toString();
    	    //System.out.println(possProgram.toString());
    	    PossibilisticProgram progNec = possProgram.programNecessity(0.5);
    	    //System.out.println(progNec.toString());
    	    
    	    PossibilisticLiteralSet possibilisticLiterals = new PossibilisticLiteralSet();
    	    
    	    PossibilisticLiteral literal1 = new PossibilisticLiteral(0.5, head1);
    	    PossibilisticLiteral literal2 = new PossibilisticLiteral(0.8, head2);
    	    possibilisticLiterals.add(literal1);
    	    possibilisticLiterals.add(literal2);
    	    ArrayList<PossibilisticLiteral> possLiterals = new ArrayList<PossibilisticLiteral>();
    	    possLiterals.add(literal1);
    	    possLiterals.add(literal2);
    	    //System.out.println(possibilisticLiterals.toString());
    	    //System.out.println(possibilisticLiterals.literalsNecessity(0.6));
    	    DecisionMakingUncertainty dmu = new DecisionMakingUncertainty();
    	    dmu.addPreferences(possLiterals);
    	    //System.out.println(dmu.toString());
    	    //System.out.println(dmu.scaleToString());
    	    
    	    
    	    DecisionMaking dm = new DecisionMaking();
    	    //dm.addKnowledge(p);
    	    //dm.addDecisions(dlpHeads);
    	    //dm.addPreferences(dlpHeads);
    	    //dm.addRule(r);
    	    //System.out.println("subProgram: ");
    	    //System.out.println(dm.subProgram().toString());
    	    DLV dlv = new DLV("/Users/christophmeyer/Desktop/dlv.bin");
    	    AnswerSetList answersets = dlv.computeModels(p, 5);
    	    System.out.println(answersets.toString());
    	    /*
    	    PessimisticLabel pessLabel = new PessimisticLabel();
    	    OptimisticLabel optLabel = new OptimisticLabel();
    	    optLabel.add(dlpHeads);
    	    System.out.println("optLabel = " + optLabel.toString());
    	    pessLabel.add(dlpHeads);
    	    pessLabel.add(dlpHeads);
    	    System.out.println(pessLabel.toString());
    	    
    	    AnswerSet answerset = new AnswerSet();
    	    DLPLiteral ass_a = new DLPAtom("ass(a)");
    	    DLPLiteral ass_e = new DLPAtom("ass(e)");
    	    DLPLiteral c = new DLPAtom("c");
    	    answerset.add(ass_e);
    	    answerset.add(ass_a);
    	    answerset.add(c);
    	    
    	    dm.addDecision(new DLPAtom("a"));
    	    dm.addDecision(new DLPAtom("e"));
    	    Alg1DM alg1 = new Alg1DM();
    	    ArrayList<DLPLiteral> list = Alg1DM.getDecisionLiterals(dm.getDecisions(), answerset);
    	    
    	    for(DLPLiteral l : answerset) {
    	    	System.out.println("In AWM sind folgende Literale: "+ l);
    	    }
    	    
    	    for (DLPLiteral l : list) {
    	    	System.out.println("Literal: " +  l.toString());
    	    }*/
    		// DMU
    		DecisionMaking dm = new DecisionMaking();
    		//Wissen
    		Program knowledge = new Program();
    		DLPLiteral a = new DLPAtom("a");
    		DLPLiteral b = new DLPAtom("b");
    		DLPLiteral c = new DLPAtom("c");
    		DLPLiteral d = new DLPAtom("d");
    		DLPLiteral e = new DLPAtom("e");
    		Rule r1 = new Rule (a,b);
    		Rule r2 = new Rule (b,c);
    		Rule r3 = new Rule (e,d);
    		knowledge.add(r1);
    		knowledge.add(r2);
    		knowledge.add(r3);
    		//Entscheidungen
    		HashSet<DLPLiteral> decisions = new HashSet<DLPLiteral>();
    		decisions.add(c);
    		decisions.add(d);
    		dm.setDecisions(decisions);
    		//Präferenzen
    		HashSet<DLPLiteral> preferences = new HashSet<DLPLiteral>();
    		preferences.add(a);
    		preferences.add(b);
    		preferences.add(e);
    		dm.addKnowledge(knowledge);
    		//System.out.println("Wissen");
    		dm.addDecisions(decisions);
    		dm.addPreferences(preferences);
    		//System.out.println("DM ist: " + dm.toString());
    		Alg1DM alg1 = new Alg1DM();
    		Alg2DM alg2 = new Alg2DM();
    		PessimisticLabelAndUtility pess = alg1.calculatePessimisticDecisionsDM(dm);
    		//OptimisticLabelAndUtility opt = alg2.calculatePessimisticDecisionsDM(dm);
    		//System.out.println(pess.toString());
    		
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
    	    
    	    System.out.println(dmu.toString());
    	    
    	   
    		
    }
}
