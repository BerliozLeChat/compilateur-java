public Scan{
	private List<String> terminal = new ArrayList<String>();
	private List<String> nonterminal = new ArrayList<String>();
	

	public static Grammar scan(){
		Scanner in = new Scanner(System.in);
		String rule; 
		while(in.hasNextLine()){
			rule = in.nextLine();
			scan_rec(rule,"");
		}		
	}
	
	private static scan_rec(String rule, String acc){
		char first = rule.charAt(0);
		if(first == '=')
		//fleche
		else if(first == '+')
		
		else if(first == '[')
		
		else if(first == ']')

		else if(first == '.')
		
		else if(first == '(')
		
		else if(first == '/')
		
		else if(first == ')')
		
				
	}
}
