package EigeneKlassen;
import java.util.ArrayList;
import java.util.List;

import net.sf.tweety.logics.commons.syntax.Predicate;
import net.sf.tweety.lp.asp.syntax.DLPAtom;
import net.sf.tweety.lp.asp.syntax.DLPElement;
import net.sf.tweety.lp.asp.syntax.DLPLiteral;
import net.sf.tweety.lp.asp.syntax.DLPNeg;
import net.sf.tweety.lp.asp.syntax.DLPNot;
import net.sf.tweety.lp.asp.syntax.DLPPredicate;
import net.sf.tweety.lp.asp.syntax.Program;
import net.sf.tweety.lp.asp.syntax.Rule;
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

	public static void main (String args []) {
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
    
    public static void testProgramm () {
    	    ArrayList<DLPLiteral> dlpHeads = new ArrayList<DLPLiteral>();
    	    DLPLiteral head1 = new DLPAtom("a");
    	    DLPLiteral head2 = new DLPAtom("b");
    	    dlpHeads.add(head1);
    	    dlpHeads.add(head2);
    	    DLPLiteral body1 = new DLPNeg("c");
    	    DLPLiteral body2 = new DLPNeg("d");
    	    DLPNot body3 = new DLPNot(body1);
    	    ArrayList<DLPElement> body = new ArrayList<DLPElement>();
    	    body.add(body1);
    	    body.add(body2);
    	    body.add(body3);
    	    Rule r = new Rule (dlpHeads, body);
    	    Program p = new Program();
    	    p.add(r);
    	    
    	    ArrayList<PossibilisticRule> rules = new ArrayList<PossibilisticRule> ();
    	    PossibilisticRule possRule = new PossibilisticRule(0.5, r);
    	    String test = possRule.getRule().toString();
    	    System.out.println(test);
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
    	    System.out.println(possProgram.toString());
    	    PossibilisticProgram progNec = possProgram.programNecessity(0.5);
    	    System.out.println(progNec.toString());
    	    
    	    PossibilisticLiteralSet possibilisticLiterals = new PossibilisticLiteralSet();
    	    
    	    PossibilisticLiteral literal1 = new PossibilisticLiteral(0.5, head1);
    	    PossibilisticLiteral literal2 = new PossibilisticLiteral(0.8, head2);
    	    possibilisticLiterals.add(literal1);
    	    possibilisticLiterals.add(literal2);
    	    System.out.println(possibilisticLiterals.toString());
    	    System.out.println(possibilisticLiterals.literalsNecessity(0.6));
    	    
    	    DecisionMaking dm = new DecisionMaking();
    	    dm.addKnowledge(p);
    	    dm.addDecisions(dlpHeads);
    	    dm.addPreferences(dlpHeads);
    	    System.out.println(dm.toString());
    	    
    }
}
