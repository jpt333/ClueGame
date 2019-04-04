//Authors: Michael Berg and Jennifer Phan
package clueGame;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import clueGame.BoardCell;

public class Board {
	private int numRows;
	private int numColumns;
	public static final int MAX_BOARD_SIZE = 50;
	
	private Solution answerKey;
	
	private static Board theInstance;
	
	private Solution solution;
	
	private BoardCell board [][];
	
	private Map<Character, String> legend; //stores what is in legend

	private Map<BoardCell, Set<BoardCell>> adjMatrix; //stores what is adjacent to a cell
	
	private Set<BoardCell> visited; //stores which cells were visited
	private Set<BoardCell> targets; //stores which cells are targets
	
	private Set<ComputerPlayer> computerPlayers;
	private Set<Player> players; 
	
	private Set<Card> cards; //deck of cards
	private CardDeck cardDeck; //sorted deck of cards
	
	private String boardConfigFile;
	private String roomConfigFile;
	private String weaponFile;
	private String characterFile;
	
	private Board() {}
	
	public Map<Character, String> getLegend() {
		return legend;
	}
	
	public int getNumRows() {
		return numRows;
	}

	public int getNumColumns() {
		return numColumns;
	}

	public static Board getInstance() {
		if(theInstance == null) {
			theInstance = new Board();
		}
		return theInstance;
	}
	
	public void initialize(){
		//initialize variables
		adjMatrix = new HashMap<BoardCell, Set<BoardCell>>();
		
		answerKey = new Solution();
		visited = new HashSet<>();
		targets = new HashSet<>();
		players = new HashSet<>();
		computerPlayers = new HashSet<>();
		cards = new HashSet<>();
		
		cardDeck = new CardDeck();
		try {
			loadRoomConfig();
			loadBoardConfig();
			loadCards();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			e.printStackTrace();
		} catch (BadConfigFormatException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		calcAdjacencies();
	}
	
	public void loadRoomConfig() throws FileNotFoundException, BadConfigFormatException {
		legend= new HashMap<Character, String>();
		Scanner scanner = new Scanner(new File(roomConfigFile)); 
	    while (scanner.hasNextLine()) {
	    	String[] line = scanner.nextLine().split(", ");
	        if(line.length == 3) {
	        	//make sure rooms are 1 symbol
	        	if(line[0].length() != 1) {
	        		scanner.close();
	        		throw new BadConfigFormatException();
	        		}
	        	Character symbol = line[0].charAt(0);
	        	//make sure there is a string for the room name
	        	if(line[1].length() == 0) {
	        		scanner.close();
	        		throw new BadConfigFormatException();
	        		}
	        	legend.put(symbol,  line[1]);
	        	//make sure that it is a card or other
	        	if(!line[2].equals("Card") && !line[2].equals("Other")) {
	        		scanner.close();
	        		throw new BadConfigFormatException(line[2]);
	        	}
	 
	        	//loads the rooms into the card deck
	        	if(line[2].equals("Card")) {
	        		cardDeck.rooms.add(new Card(line[1], CardType.ROOM, symbol.toString()));
	        		cards.add(new Card(line[1], CardType.ROOM));
	        	}
	        }else {
	        	scanner.close();
	        	throw new BadConfigFormatException();
	        }
	    }	
	    scanner.close();
	}
	
	//from https://stackoverflow.com/questions/2854043/converting-a-string-to-color-in-java user: ZZ Coder and Erick Robertson
	private Color convertColor(String strColor) {
		 Color color;
		 try {
		 // We can use reflection to convert the string to a color
		 Field field = Class.forName("java.awt.Color").getField(strColor.trim());
		 color = (Color)field.get(null);
		 } catch (Exception e) {
		 color = null; // Not defined
		 }
		 return color;
	}
	//-------------------------------------------------------------------------------------

	public void setCardFiles(String weaponFile, String characterFile) {
		this.weaponFile = weaponFile;
		this.characterFile = characterFile;
	}
	
	public void loadCards() throws BadConfigFormatException, FileNotFoundException {
		Scanner scanner = new Scanner(new File(characterFile)); 
	    while (scanner.hasNextLine()) {
	    	String[] line = scanner.nextLine().split(", ");
	        if(line.length == 4) {
	        	//make sure there is no missing data
	        	if(line[0].length() == 0 
	        			|| line[1].length() == 0
	        			|| line[2].length() == 0
	        			|| line[3].length() == 0) {
	        		scanner.close();
	        		throw new BadConfigFormatException();
	        	}
	        	try {
	        		Color locColor = convertColor(line[1].toLowerCase());
	        		if (locColor == null) {
	        			scanner.close();
		        		throw new BadConfigFormatException();
	        		}
	        		// might want to refactor the second argument it is really wordy
	        		players.add(new Player(line[0] , board[(int)Double.parseDouble(line[2])][(int)Double.parseDouble(line[3])], locColor));
	        		cardDeck.people.add(new Card(line[0], CardType.PERSON));
	        		cards.add(new Card(line[0], CardType.PERSON));
	        		//if not a number
	        	}catch(NumberFormatException e){
	        		scanner.close();
	        		throw new BadConfigFormatException();
	        	}
	        }else {
	        	scanner.close();
	        	throw new BadConfigFormatException();
	        }
	    }	
	    scanner.close();
	    //load in the weaponFile
	    scanner = new Scanner(new File(weaponFile)); 
	    while (scanner.hasNextLine()) {
	    	String line = scanner.nextLine();
	    	if(line.length() == 0) {
	    		scanner.close();
        		throw new BadConfigFormatException();
	    	}
	    	cardDeck.weapons.add(new Card(line, CardType.WEAPON));
	    	cards.add(new Card(line, CardType.WEAPON));
	    }
	    scanner.close();
	}
	
	public void loadBoardConfig() throws FileNotFoundException, BadConfigFormatException {
		
		board = new BoardCell[MAX_BOARD_SIZE][MAX_BOARD_SIZE];
		
		numRows = 0;
		numColumns = 0;
		
		Scanner scanner = new Scanner(new File(boardConfigFile));
		while (scanner.hasNextLine()) {
			String[] line = scanner.nextLine().split(",");
			//iterates through the line
			for(int a1 = 0; a1 < line.length; a1++) {
	        	//make sure correct length board cant be over 99 in size
				if(line[a1].length() > 2 || line[a1].length() == 0) {
					scanner.close();
					throw new BadConfigFormatException();
					}
				//check if number
				try {
					 Double.parseDouble(line[a1]); 
					 //counts the number of numbers
					 
					 //if it is a  numbers
					 if(a1 != 0) {
						 numColumns--;
					 }else{
						 numRows--;
					 }
					 break;
				}catch(NumberFormatException e) {
					//not a number
					
					//make sure correct  number of characters
					if(line[a1].length() > 2 || line[a1].length() == 0){
						scanner.close();
						throw new BadConfigFormatException();
						}
					//check that the room exists
					if(!legend.containsKey(line[a1].charAt(0))){
						scanner.close();
						throw new BadConfigFormatException();
						}
					//doorways
		        	if(line[a1].length() == 2) {
		        		if(line[a1].endsWith("U")) {
		        			board[numRows][a1] = new BoardCell(line[a1], DoorDirection.UP);
		        		}
		        		else if(line[a1].endsWith("D")) {
		        			board[numRows][a1] = new BoardCell(line[a1], DoorDirection.DOWN);
		        		}
		        		else if(line[a1].endsWith("L")) {
		        			board[numRows][a1] = new BoardCell(line[a1], DoorDirection.LEFT);
		        		}
		        		else if(line[a1].endsWith("R")) {
		        			board[numRows][a1] = new BoardCell(line[a1], DoorDirection.RIGHT);
		        		}
		        		else if(line[a1].endsWith("N")) {
		        			board[numRows][a1] = new BoardCell(line[a1], DoorDirection.NONE);
		        		}
		        		else {
		        			scanner.close();
		        			throw new BadConfigFormatException();
		        		}
		        	}
		        	else {
		        		//everything else
		        		board[numRows][a1] = new BoardCell(line[a1]);
		        	}
		        	//always take the max length
		        	if(a1 == 0 && line.length > numColumns) {
		        		numColumns = line.length;
					}
		        	//check that all rows are the same length
		        	if(line.length < numColumns) {
		        		scanner.close();
		        		throw new BadConfigFormatException();
		        		}
		        }
			}
			numRows++;
	    }
	    scanner.close();
	}
	
	public void dealCards() {
		Card cardsLoc[] = new Card[cards.size()];
		
		solution = new Solution();
		
		Set<Integer> visitedAddresses = new HashSet<>();
		
		int numberOfCards = Math.floorDiv(cards.size() , players.size());
		
		Random rand = new Random();
		boolean rejected = true;
		int adress = 0;
		
		for(Card cardLoc: cards) {cardsLoc[adress] = cardLoc; adress++; }
		
		//take 3 cards for the solution
		int randomNum = 0;
		for(int a1 = 0; a1 < 3; a1++) {
			while(rejected) {
				randomNum = rand.nextInt(cards.size());
				//find a person card
				if(a1 == 0) {
					if(cardsLoc[randomNum].getCardType() == CardType.PERSON) {
						solution.person = cardsLoc[randomNum];
						break;
					}
				}
				//find a weapon card
				if(a1 == 1) {
					if(cardsLoc[randomNum].getCardType() == CardType.WEAPON) {
						solution.weapon = cardsLoc[randomNum];
						break;
					}
				}
				//find a room card
				if(a1 == 2) {
					if(cardsLoc[randomNum].getCardType() == CardType.ROOM) {
						solution.room = cardsLoc[randomNum];
						break;
					}
				}
			}
			visitedAddresses.add(randomNum);
		}
		
		
		for(Player playerLoc: players) {
			  
			Set<Card> cardSet =  new HashSet<>();
			for(int a1 = 0; a1 < numberOfCards; a1++) {
				//choose random card
				while(rejected) {
					randomNum = rand.nextInt(cards.size());
					rejected = visitedAddresses.contains(randomNum);
				}
				rejected = true;
				visitedAddresses.add(randomNum);
				cardSet.add(cardsLoc[randomNum]);
			}
			
			
			
			playerLoc.setCards(cardSet);
		}
	}
	
	public void calcAdjacencies() {
		//calculates the adjacency list for each grid cell and stores the results as a Map in an inst. var
		// Look for neighbors. Make sure it is valid neighbor. Add neighbor
		
		for(int row = 0; row < numRows; row ++) {
			for(int col = 0; col < numColumns; col++){
				Set<BoardCell> adjTiles = new HashSet<>();
				//if row less than board size do
				
				//all door adj tiles calculations
				//gets the tile in front of the door direction
				if(board[row][col].getDoorDirection() == DoorDirection.DOWN 
						&& row+1 < numRows
						&& board[row + 1][col].getInitial() == 'W'){
					adjTiles.add(board[row+1][col]);
				}
				
				if(board[row][col].getDoorDirection() == DoorDirection.UP 
						&& row > 0
						&& board[row - 1][col].getInitial() == 'W'){
					adjTiles.add(board[row - 1][col]);
				}
				
				if(board[row][col].getDoorDirection() == DoorDirection.LEFT 
						&& col > 0
						&& board[row][col - 1].getInitial() == 'W'){
					adjTiles.add(board[row][col - 1]);
				}
				
				if(board[row][col].getDoorDirection() == DoorDirection.RIGHT 
						&& col+1 < numColumns
						&& board[row][col + 1].getInitial() == 'W'){
					adjTiles.add(board[row][col + 1]);
				}
				
				//bottom adj tile
				if(board[row][col].getInitial() == 'W'){
					if(row > 0){//avoid array out of bounds errors
						if(board[row][col].getInitial() == board[row-1][col].getInitial() //if its the same room or hallway
							||(board[row][col].getInitial() != board[row-1][col].getInitial() //or its not the same room or hallway 
							&& (board[row - 1][col].getDoorDirection() == DoorDirection.DOWN))) {//and is a doorway
		
							adjTiles.add(board[row-1][col]);
							
						}
					}
					//if col less than board size do
					//left adj tile
					if(col > 0){//avoid array out of bounds errors
						if(board[row][col].getInitial() == board[row][col - 1].getInitial() //if its the same room or hallway
							||(board[row][col].getInitial() != board[row][col - 1].getInitial() //or its not the same room or hallway 
							&& (board[row][col - 1].getDoorDirection() == DoorDirection.RIGHT ))) {//and is a doorway
						
							adjTiles.add(board[row][col-1]);
							
						}
					}
					
					//right adj tile
					if(col+1 < numColumns){//avoid array out of bounds errors
						if(board[row][col].getInitial() == board[row][col + 1].getInitial() //if its the same room or hallway
							||(board[row][col].getInitial() != board[row][col + 1].getInitial() //or its not the same room or hallway 
							&& (board[row][col + 1].getDoorDirection() == DoorDirection.LEFT ))) {//and is a doorway
						
							adjTiles.add(board[row][col+1]);
							
						}
					}
					//top adj tile
					if(row+1 < numRows){//avoid array out of bounds errors
						if(board[row][col].getInitial() == board[row + 1][col].getInitial() //if its the same room or hallway
							||(board[row][col].getInitial() != board[row + 1][col].getInitial() //or its not the same room or hallway 
							&& (board[row + 1][col].getDoorDirection() == DoorDirection.UP ))) {//and is a doorway
						
							adjTiles.add(board[row+1][col]);
						}
						
					}
				}
				adjMatrix.put(board[row][col], adjTiles);
			}
		}
	}
	
	public void calcTargetsFun(BoardCell startCell, int pathLength) {
		//Calculates targets that are pathLength distance 
		//from start cell. List of targets stored as a set in inst. var.
		
		visited.add(startCell);
		
		Set<BoardCell> adjTiles = adjMatrix.get(startCell);
		for(BoardCell cell : adjTiles) {
			//if already in visited list, skip the rest
			if(visited.contains(cell)) {
				continue;
			}else {
				visited.add(cell); //adds cell into visited list
			} 
			if(pathLength == 1 || (startCell.getInitial() != cell.getInitial() && startCell.getInitial() == 'W')) {
				targets.add(cell);
			}else {
				calcTargetsFun(cell, pathLength-1); //recursively calls
			}
			visited.remove(cell);
		}
		
	}
	
	
	public Card handleSuggestion(Player player) {
		players.remove(player);
		Card answer = handleSuggestionTech(player.createSuggestion(cardDeck));
		players.add(player);
		return answer;
	}
	
	public Card handleSuggestion(ComputerPlayer player) {
		computerPlayers.remove(player);
		Card answer = handleSuggestionTech(player.createSuggestion(cardDeck));
		computerPlayers.add(player);
		return answer;
	}
	
	public Card handleSuggestionTech(Solution suggestion) {
		Card answer;
		for(Player locPlayers: players) {
			answer = locPlayers.disproveSuggestion(suggestion);
			if(answer != null) {
				return answer;
			}
		}
		for(ComputerPlayer locComputerPlayers: computerPlayers) {
			answer = locComputerPlayers.disproveSuggestion(suggestion);
			if(answer != null) {
				return answer;
			}
		}
		return null;
	}
	
	public void calcTargets(int row, int col, int steps) {
		visited.clear();
		targets.clear();
		calcTargetsFun(board[row][col], steps);
	}

	public void setConfigFiles(String boardFile, String legendFile) {
		boardConfigFile = boardFile;
		roomConfigFile = legendFile;
		//set the defaults
		weaponFile = "weapons.txt";
		characterFile = "Person.txt";
	}

	public BoardCell getCellAt(int row, int col) {
		return board[row][col];
	}

	public Set<Player> getPlayers() {
		return players;
	}

	public Set<Card> getCards() {
		return cards;
	}
	
	public CardDeck getDeck() {
		return cardDeck;
	}
	
	public Solution getSolution() {
		return solution;
	}

	public Set<BoardCell> getAdjList(int row, int col) {
		return adjMatrix.get(board[row][col]);
	}

	public Set<BoardCell> getTargets() {
		return targets; 
	}
	
	public Boolean checkAccusation(Solution accusation) {
		if(!getSolution().getPerson().equals(accusation.getPerson())) {
			return false;
		}
		if(!getSolution().getRoom().equals(accusation.getRoom())) {
			return false;
		}
		if(!getSolution().getWeapon().equals(accusation.getWeapon())) {
			return false;
		}
		
		return true;
	}
	
}
