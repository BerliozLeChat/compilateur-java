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
												new Atom(1, 0, AtomType
														.NONTERMINAL, "N"),
												new Atom(null, 0, AtomType
														.TERMINAL, "->")),
										new Atom(2, 0, AtomType
												.NONTERMINAL, "E")),
								new Atom(null, 1, AtomType.TERMINAL, ","))),
			new Atom(null,  0,  AtomType.TERMINAL, ";"))
		);
		this.add(new Atom(null,2,AtomType.TERMINAL, "IDNTER"));
		
		this.add(new Conc(
				new Star(
						new Conc(
								new Atom(null,0,AtomType.TERMINAL, "+"),
								new Atom(3,3,AtomType.NONTERMINAL, "T"))),
				new Atom(3,0,AtomType.NONTERMINAL, "T")));
		
		this.add(new Conc(
				new Star(
						new Conc(
								new Atom(null, 0, AtomType.TERMINAL, "."),
								new Atom(4, 4, AtomType.NONTERMINAL, "F"))),
				new Atom(4, 0, AtomType.NONTERMINAL,"F")));
		
		this.add(
				new Union(
						new Union(
								new Union(
										new Union(
												new Conc(
														new Atom(null, 0,
																AtomType
																		.TERMINAL, "/"),
														new Conc(
																new Atom(2,
																		0,
																		AtomType.NONTERMINAL,"E"),
																new Atom(null,
																		7,
																		AtomType.TERMINAL, "/)")
														)
												),
												new Conc(
													new Atom(null, 0, AtomType
															.TERMINAL, "["),
													new Conc(
															new Atom(2, 0,
																	AtomType
																			.NONTERMINAL, "E"),
															new Atom(null, 6,
																	AtomType
																			.TERMINAL, "]")
													)
												)
										),
										new Conc(
												new Atom(null, 0, AtomType
														.TERMINAL, "("),
												new Conc(
														new Atom(2, 0,
																AtomType
																		.NONTERMINAL, "E"),
														new Atom(null, 0,
																AtomType
																		.TERMINAL, ")")
												)
										)
								),
								new Atom(null, 5, AtomType.TERMINAL, "ELTER")
						),
					new Atom(null, 5, AtomType.TERMINAL, "IDNTER")
				)	
		);
		
		for(int i = 0; i < this.rules.size(); ++i)
			System.out.println("A[" + i + "] \n" + this.imprimerArbre(i));
	}

}
