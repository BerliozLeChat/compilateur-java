import java.util.ArrayList;
import java.util.List;

public class Grammar{
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
	
	

	public boolean Analyse(Node node){
		if(node instanceof Conc){
			if(Analyse(node.getLeft())){
				return Analyse(node.getRight());
			}
			else {
				return false;
			}
		}
		else if(node instanceof Union){
			if(node.getLeft()){
				return true;
			}
			else {
				return Analyse(node.getRight());
			}
		}
		else if(node instanceof Star){
			return Analyse(node.getLeft());
		}
		else if(node instanceof Un){
			return Analyse(node.getLeft());
		}
		else if(node instanceof Atome){
			if((Atome)node.getAtype() == AtomType.TERMINAL){
				if((Atome)node.getCode() == code){
					if((Atome)node.getAction() != null)
						(Atome)node.getAction().exec();
					Scan();
					return true;
				}
				else
					return false;
			}
			else if((Atome)node.getAtype() == AtomType.TERMINAL){
				if(Analyse(rules.get(node.getCode()))){
					if((Atome)node.getAction() != null)
						(Atome)node.getAction().exec();
				} else
					return false;
			}
	}
	

	public static void main(String[] args) {
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
	}

} 
