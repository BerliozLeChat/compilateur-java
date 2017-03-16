import java.util.ArrayList;
import java.util.List;

public class Grammar{
	private Scan scan;
	protected List<Node> rules;

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
	
	public void Analyse() {
		scan = new Scan();
		mAnalyse(new Gzero().rules.get(0));
	}

	public boolean mAnalyse(Node node) {
		Atom sc = scan.scan();
		if (node instanceof Conc) {
			if (mAnalyse(node.getLeft())) {
				return mAnalyse(node.getRight());
			} else {
				return false;
			}
		} else if (node instanceof Union) {
			if (node.getLeft() != null) {
				return true;
			} else {
				return mAnalyse(node.getRight());
			}
		} else if (node instanceof Star) {
			return mAnalyse(node.getLeft());
		} else if (node instanceof Un) {
			return mAnalyse(node.getLeft());
		} else if (node instanceof Atom) {
			if (((Atom) node).getAtype() == AtomType.TERMINAL) {
				if (((Atom) node).getChaine() == sc.getChaine()) {
					if (((Atom) node).getAction() != 0)
						((Atom) node).getAction().exec();
					sc = scan.scan();
					return true;
				} else
					return false;
			} else if (((Atom) node).getAtype() == AtomType.NONTERMINAL) {
				if (mAnalyse(rules.get(((Atom) node).getCode()))) {
					if (((Atom) node).getAction() != 0)
						((Atom) node).getAction().exec();
				} else
					return false;

			}
		}
	}
	

	public static void main(String[] args) {
		/*
		Grammar G = new Grammar();
		G.add(new Conc(
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
		G.add(new Atom("IDNTER",0,AtomType.TERMINAL));
		
		G.add(new Conc(
				new Star(
						new Conc(
								new Atom("+",0,AtomType.TERMINAL),
								new Atom("T",0,AtomType.NONTERMINAL))),
				new Atom("T",0,AtomType.NONTERMINAL)));
		
		G.add(new Conc(
				new Star(
						new Conc(
								new Atom(".", 0, AtomType.TERMINAL),
								new Atom("F", 0, AtomType.NONTERMINAL))), 
				new Atom("F", 0, AtomType.NONTERMINAL)));
		
		G.add(
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
		
		for(int i = 0; i < G.rules.size(); ++i)
			System.out.println("A[" + i + "] \n" + G.imprimerArbre(i));
			*/
	}

} 
