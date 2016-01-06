import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;


public class Main {

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		int n=5;
		double k=0.3;
		//get random characters
		ArrayList<String> randomCharacters =  getRandomArray(n);
		ArrayList<String> randomCharactersFinal =  new ArrayList<String>();
		
		System.out.println("randomCharacters::"+randomCharacters.toString());
		
		//calculate the number of differrent couples
		int numberOfCouples = (int) (((n*n)/2)*k);
		System.out.println("numberOfCouples::"+numberOfCouples);
		//get differrent couples
		ArrayList<String> differrentCouples = getCouples(numberOfCouples, randomCharacters);
		System.out.println("differrentCouples::"+differrentCouples.toString());
		
		//remove character that are not included in couples' list
		for(String s:randomCharacters){
			//System.out.println("checking char::"+s);
			//System.out.println("LLL:::"+Arrays.stream(differrentCouples.toArray()).anyMatch(i -> i == s));
			if(Arrays.asList(differrentCouples).toString().contains(s)){
				//System.out.println("removing char::"+s);
				randomCharactersFinal.add(s);
			}
		}
		System.out.println("New randomCharacters::"+randomCharactersFinal.toString());
		
		//find max possible matches
		ArrayList<ArrayList<String>> maximumPossibleMatches = new ArrayList<ArrayList<String>>();
		//maximumPossibleMatches = getMaxMatches(randomCharacters, differrentCouples);
		//randomCharacters::[M, V, K, L, I]
		//differrentCouples::[VM, MI, VI]
		getMaxMatchesCombinations(randomCharacters, differrentCouples);
		for (int i=0;i<maximumPossibleMatches.size();i++){
			
			System.out.println("Number of match::"+(i+1)+" ::"+maximumPossibleMatches.get(i).toString());
		}
		

		long endTime = System.currentTimeMillis();

		System.out.println("Finished in: " + (endTime - startTime) + " milliseconds");
		
	}
	
	public static ArrayList<String> getRandomArray (int n) {
		String[] alb = {"A","B","C","D","E","F","G","H","I","J","K",
				"L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
		List<String> alphabet = new ArrayList<>();
		for(int i =0; i< alb.length; i++){
			alphabet.add(alb[i]);
		}
	//List<String> alfabet =  Arrays.asList(alb);
	/*
	alfabet.add("A");
	alfabet.add("B");
	alfabet.add("C");
	alfabet.add("D");
	alfabet.add("E");
	alfabet.add("F");
	*/
			//(ArrayList<String>) Arrays.asList(");
	ArrayList<String> randomArray = new ArrayList<String>();
	Random rand = new Random();
	
	for (int i=0;i<n;i++){
		//get random from list
		int indx = rand.nextInt(alphabet.size());
		
		String r = alphabet.get(indx);
		//add it to randomArray
		randomArray.add(r);
		//remove letter from alfabet list
		alphabet.remove(indx);
	}
		
		return randomArray;
	}
	
	public static ArrayList<String> getCouples (int noC, ArrayList<String> list) {
		ArrayList<String> randomcouplesArray = new ArrayList<String>();
		//ArrayList<String> random = new HashSet();
		Random rand = new Random();
		while(randomcouplesArray.size()<noC){
			
				String first = list.get(rand.nextInt(list.size()));
				ArrayList<String> random = new ArrayList<String>();
				random.addAll(list);
				random.remove(first);
				String newCouple = first + random.get(rand.nextInt(random.size()));
				
				//check if already exists the same or inverted
				if(!checkIfExists(randomcouplesArray, newCouple)){
					randomcouplesArray.add(newCouple);					
				}
		}
		
		return randomcouplesArray;
	}
	
	public static boolean checkIfExists(ArrayList<String> strCouples, String strToCheck){
		if (strCouples.contains(strToCheck) || strCouples.contains(new StringBuilder(strToCheck).reverse().toString())){
			return true;
		} 
		return false;
	}
	
	public static ArrayList<String> getMaxMatchesCombinations (ArrayList<String> randChars, ArrayList<String> couples){
		 List<List<String>> randomLettersSet = new LinkedList<List<String>>();
		 List<List<String>> couplesSet = new LinkedList<List<String>>();
		 
		 List<String> randomLettersJoin = new ArrayList<String>();
		 List<String> couplesJoin = new ArrayList<String>();
		 
		 List<String> finalCombinations = new ArrayList<String>();


		    for (int i = 2; i <= randChars.size(); i++)
		    	randomLettersSet.addAll(combination(randChars, i));
		    
		    for(List<String> rand: randomLettersSet)
		    		randomLettersJoin.add(String.join("", rand));
		    
		   
		    
		    for (int i = 1; i <= couples.size(); i++)
		    	couplesSet.addAll(combination(couples, i));

		    for(List<String> coup: couplesSet)
		    	couplesJoin.add(String.join("", coup));
	    
	    System.out.println("couplesJoin:"+couplesJoin);
	    
	    //combinations
	    List<String> combinations = new ArrayList<String>();
	    Collections.sort(randomLettersJoin, new MyComparator());
	    System.out.println("randomLettersJoin:"+randomLettersJoin);
	    for(String r: randomLettersJoin){
	    	for(String couple:couplesJoin){
	    		if(couple.length()==r.length()*(r.length()-1)){
	    			//String regex = "["+r+"]\\2+";
	    			//if (c.matches(regex));
	    			boolean containsCouple = true;
	    			for(char c: couple.toCharArray()) {
	    				if(r.contains(String.valueOf(c))){
	    					
	    				} else {
	    					containsCouple = false;
	    				}
	    			}
	    			
	    			if(containsCouple){
	    			
	    				if(!finalCombinations.toString().contains(r)){
	    					finalCombinations.add(r);
	    				}
	    			}
	    		
	    			
	    		}
	    	}
	    	 
	    }
		    
		System.out.println("finalCombinations::"+finalCombinations);
		return null;
	}
	
	public static class MyComparator implements Comparator<String>{
	    @Override
	    public int compare(String o1, String o2) {  
	      if (o1.length() < o2.length()) {
	         return 1;
	      } else if (o1.length() > o2.length()) {
	         return -1;
	      }
	      return o1.compareTo(o2);
	    }
	}
	

public static <T> List<List<T>> combination(List<T> values, int size) {

    if (0 == size) {
        return Collections.singletonList(Collections.<T> emptyList());
    }

    if (values.isEmpty()) {
        return Collections.emptyList();
    }

    List<List<T>> combination = new LinkedList<List<T>>();

    T actual = values.iterator().next();

    List<T> subSet = new LinkedList<T>(values);
    subSet.remove(actual);

    List<List<T>> subSetCombination = combination(subSet, size - 1);

    for (List<T> set : subSetCombination) {
        List<T> newSet = new LinkedList<T>(set);
        newSet.add(0, actual);
        combination.add(newSet);
    }

    combination.addAll(combination(subSet, size));

    return combination;
}
	
	public static ArrayList<ArrayList<String>> getMaxMatches (ArrayList<String> randChars, ArrayList<String> couples){
		
		ArrayList<ArrayList<String>> maxLetterCombinations = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> tempMaxLetterCombinations = new ArrayList<ArrayList<String>>();
		for (String let : randChars){				
			//check if exists in couples
			for(String str : couples){
				if(maxLetterCombinations.size() == 0 && str.contains(let)){
					System.out.println("Adding first letter:: " + let);
					ArrayList<String> addNew = new ArrayList<String>();
					addNew.add(let);
					maxLetterCombinations.add(addNew);
				}
				//System.out.println(str);
				if(str.contains(let)) {
					System.out.println("The letter " + let + "  exists in " + str);
					for(ArrayList<String> listStr : maxLetterCombinations){
						System.out.println("::::::::::maxLetterCombinations:::: " + maxLetterCombinations.toString());
						if(existsInList(listStr, couples, let)){
							System.out.println("letter " + let + "  exists in couples" + couples);
							if(!listStr.contains(let)){
								listStr.add(let);
							}
						} else {
							ArrayList<String> addNew = new ArrayList<String>();
							addNew.add(let);
							for(ArrayList<String> temp:tempMaxLetterCombinations) {
							if(!temp.contains(addNew)){
								System.out.println("letter " + addNew + "  does not exist in tempMaxLetterCombinations" + temp);
								tempMaxLetterCombinations.add(addNew);
							}
							}
							
						}
					}
					if(tempMaxLetterCombinations.size()>0 && !maxLetterCombinations.contains(tempMaxLetterCombinations)){
						System.out.println("letter " + tempMaxLetterCombinations + "  does not exist in tempMaxLetterCombinations" + maxLetterCombinations);
						maxLetterCombinations.addAll(tempMaxLetterCombinations);
					}
					
					tempMaxLetterCombinations.clear();
				} else {
					System.out.println("letter " + let + " doesn't exist in " + str);
				}
			}

			
		}
		
		return maxLetterCombinations;
	}
	
	public static boolean existsInList(ArrayList<String> maxMatchesRow, ArrayList<String> couples, String letter) {
		boolean exist = false;
		
		for (int i=0;i<maxMatchesRow.size();i++){
			String newCouple = maxMatchesRow.get(i) + letter;
			if(checkIfExists(couples,newCouple)) {
				exist = true;
			} 
		}
		
		return exist;
	}
}
