import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Grammar{
	private List<Node> rules;

	public Grammar(){
		rules = new ArrayList<Node>();
	}

	public void add(Node rule){
		rules.add(rule);
	}

	public String imprimerArbre(int idrule){
		StringBuilder buildTree = new StringBuilder();
		imprim_rec(0,buildTree,rules.get(idrule));
		return buildTree.toString();
	}



	public void imprim_rec(int level, StringBuilder builder, Node node){
		for(int i=0; i<level; ++i){
			builder.append("---");
		}
		builder.append(node.toString()+"\n");
		if(node.getLeft() != null){
			imprim_rec(level+1, builder,node.getLeft());
			if(node.getRight() != null)
				imprim_rec(level+1, builder,node.getRight());
		}
	}

	@Override
	public String toString(){
		return null;
	}

	public static void main(String[] args) {
		/*
		Grammar test = new Grammar();

		test.add(new Atom('A',0,AtomType.TERMINAL));

		test.add(new Conc(new Union(new Atom('B',0,AtomType.TERMINAL),new
				Atom('B',0,AtomType.TERMINAL)),new Atom('C',0,AtomType.TERMINAL)));


		System.out.println(test.imprimerArbre(0));
		System.out.println(test.imprimerArbre(1));

		StringBuilder buildstr = new StringBuilder();
		test.imprim_rec(0, buildstr,new Conc(new Union(new Atom('B',0,AtomType.TERMINAL),new
				Atom('B',0,AtomType.TERMINAL)),new Atom('C',0,AtomType.TERMINAL)));
		System.out.println(buildstr.toString());
		*/
		
		Grammar G = new Grammar();
		G.add(new Conc(
				new Star(
						new Conc(
								new Conc(
										new Conc(
												new Atom('N', 0, AtomType.NONTERMINAL), 
												new Atom('>', 0, AtomType.TERMINAL)), 
										new Atom('E', 0, AtomType.NONTERMINAL)), 
								new Atom(',', 1, AtomType.TERMINAL))), 
			new Atom(';',  0,  AtomType.TERMINAL))
		);
		G.add(new Atom('I',0,AtomType.TERMINAL));
		
		G.add(new Conc(
				new Star(
						new Conc(
								new Atom('+',0,AtomType.TERMINAL),
								new Atom('T',0,AtomType.NONTERMINAL))),
				new Atom('T',0,AtomType.NONTERMINAL)));
		
		G.add(new Conc(
				new Star(
						new Conc(
								new Atom('.', 0, AtomType.TERMINAL),
								new Atom('I', 0, AtomType.NONTERMINAL))), 
				new Atom('I', 0, AtomType.NONTERMINAL)));
		
		G.add(
				new Union(
						new Union(
								new Union(
										new Union(
												new Conc(
														new Atom('|', 0, AtomType.TERMINAL),
														new Conc(
																new Atom('E', 0, AtomType.NONTERMINAL),
																new Atom('|', 0, AtomType.TERMINAL)
														)
												),
												new Conc(
													new Atom('[', 0, AtomType.TERMINAL),
													new Conc(
															new Atom('E', 0, AtomType.NONTERMINAL),
															new Atom(']', 0, AtomType.TERMINAL)
													)
												)
										),
										new Conc(
												new Atom('(', 0, AtomType.TERMINAL),
												new Conc(
														new Atom('E', 0, AtomType.NONTERMINAL),
														new Atom(')', 0, AtomType.TERMINAL)
												)
										)
								),
								new Atom('R', 0, AtomType.TERMINAL)
						),
					new Atom('I', 0, AtomType.TERMINAL)
				)
		);
		
		for(int i = 0; i < G.rules.size(); ++i)
			System.out.println("A[" + i + "] \n" + G.imprimerArbre(i));
	}

} 
