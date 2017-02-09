import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;


public final class Gzero extends Grammar {
	public Gzero(){
	super();
		this.add(new Conc(
				new Star(
						new Conc(
								new Conc(
										new Conc(
												new Atom("N", 0, AtomType.NONTERMINAL), 
												new Atom("->", 0, AtomType.TERMINAL)), 
										new Atom("E", 0, AtomType.NONTERMINAL)), 
								new Atom(",", 1, AtomType.TERMINAL))), 
			new Atom(";",  0,  AtomType.TERMINAL))
		);
		this.add(new Atom("IDNTER",0,AtomType.TERMINAL));
		
		this.add(new Conc(
				new Star(
						new Conc(
								new Atom("+",0,AtomType.TERMINAL),
								new Atom("T",0,AtomType.NONTERMINAL))),
				new Atom("T",0,AtomType.NONTERMINAL)));
		
		this.add(new Conc(
				new Star(
						new Conc(
								new Atom(".", 0, AtomType.TERMINAL),
								new Atom("F", 0, AtomType.NONTERMINAL))), 
				new Atom("F", 0, AtomType.NONTERMINAL)));
		
		this.add(
				new Union(
						new Union(
								new Union(
										new Union(
												new Conc(
														new Atom("(/", 0, AtomType.TERMINAL),
														new Conc(
																new Atom("E", 0, AtomType.NONTERMINAL),
																new Atom("/)", 0, AtomType.TERMINAL)
														)
												),
												new Conc(
													new Atom("[", 0, AtomType.TERMINAL),
													new Conc(
															new Atom("E", 0, AtomType.NONTERMINAL),
															new Atom("]", 0, AtomType.TERMINAL)
													)
												)
										),
										new Conc(
												new Atom("(", 0, AtomType.TERMINAL),
												new Conc(
														new Atom("E", 0, AtomType.NONTERMINAL),
														new Atom(")", 0, AtomType.TERMINAL)
												)
										)
								),
								new Atom("ELTER", 0, AtomType.TERMINAL)
						),
					new Atom("IDNTER", 0, AtomType.TERMINAL)
				)	
		);
		
		for(int i = 0; i < this.rules.size(); ++i)
			System.out.println("A[" + i + "] \n" + this.imprimerArbre(i));
	}
}
