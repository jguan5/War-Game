import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Deck class represents a shuffled deck of cards.
 * It provides several operations including
 *      initialize, shuffle, deal, and check if empty.
 */
public class Deck {

	Scanner scan = new Scanner(System.in);

	/**
	 * cards contains all the cards in the deck.
	 */
	private List<Card> cards;

	/**
	 * size is the number of not-yet-dealt cards.
	 * Cards are dealt from the top (highest index) down.
	 * The next card to be dealt is at size - 1.
	 */
	private int size;

	static private ArrayList<Card> playerDeck;
	static private ArrayList<Card> computerDeck;


	/**
	 * Creates a new <code>Deck</code> instance.<BR>
	 * It pairs each element of ranks with each element of suits,
	 * and produces one of the corresponding card.
	 * @param ranks is an array containing all of the card ranks.
	 * @param suits is an array containing all of the card suits.
	 * @param values is an array containing all of the card point values.
	 */
	public Deck(String[] ranks, String[] suits, int[] values) {
		cards = new ArrayList<Card>();
		for (int j = 0; j < ranks.length; j++) {
			for (String suitString : suits) {
				cards.add(new Card(ranks[j], suitString, values[j]));
			}
		}
		size = cards.size();
		shuffle();
	}

	public Deck(){
		playerDeck = new ArrayList<Card>();
		computerDeck = new ArrayList<Card>();
	}


	/**
	 * Determines if this deck is empty (no undealt cards).
	 * @return true if this deck is empty, false otherwise.
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Accesses the number of undealt cards in this deck.
	 * @return the number of undealt cards in this deck.
	 */
	public int size() {
		return size;
	}

	/**
	 * Randomly permute the given collection of cards
	 * and reset the size to represent the entire deck.
	 */
	public void shuffle() {
		for (int k = cards.size() - 1; k > 0; k--) {
			int howMany = k + 1;
			int start = 0;
			int randPos = (int) (Math.random() * howMany) + start;
			Card temp = cards.get(k);
			cards.set(k, cards.get(randPos));
			cards.set(randPos, temp);
		}
		size = cards.size();
	}

	/**
	 * Deals a card from this deck.
	 * @return the card just dealt, or null if all the cards have been
	 *         previously dealt.
	 */
	public Card deal() {
		if (isEmpty()) {
			return null;
		}
		size--;
		Card c = cards.get(size);
		return c;
	}

	/**
	 * Generates and returns a string representation of this deck.
	 * @return a string representation of this deck.
	 */
	@Override
	public String toString() {
		String rtn = "size = " + size + "\nUndealt cards: \n";

		for (int k = size - 1; k >= 0; k--) {
			rtn = rtn + cards.get(k);
			if (k != 0) {
				rtn = rtn + ", ";
			}
			if ((size - k) % 2 == 0) {
				// Insert carriage returns so entire deck is visible on console.
				rtn = rtn + "\n";
			}
		}

		rtn = rtn + "\nDealt cards: \n";
		for (int k = cards.size() - 1; k >= size; k--) {
			rtn = rtn + cards.get(k);
			if (k != size) {
				rtn = rtn + ", ";
			}
			if ((k - cards.size()) % 2 == 0) {
				// Insert carriage returns so entire deck is visible on console.
				rtn = rtn + "\n";
			}
		}

		rtn = rtn + "\n";
		return rtn;
	}

	public String winner(Card comp, Card player){
		if (player.pointValue() > comp.pointValue()){
			return ("win");
		} else if (player.pointValue() < comp.pointValue()){
			return ("lose");
		} else {
			return ("tie");
		}
	}

	public void battle(String inp){
		if (inp.equals("S")){
			shuffle();
		} else if (inp.equals("")){
			int dealt = 2;
			ArrayList<Card> storage = new ArrayList<Card>();
			Card player = playerDeck.get(playerDeck.size()-1);//deal();
			playerDeck.remove(playerDeck.size()-1);
			storage.add(player);
			Card comp = computerDeck.get(computerDeck.size()-1);//deal();
			computerDeck.remove(computerDeck.size()-1);
			storage.add(comp);
			System.out.println("You drew a " + player);
			System.out.println("The computer drew a " + comp);
			String result = winner(comp, player);
			while (result.equals("tie")){
				System.out.println("It's  tie! Battle Again!");
				player = playerDeck.get(playerDeck.size()-1);//deal();
				playerDeck.remove(playerDeck.size()-1);
				storage.add(player);
				Card hidden1 = playerDeck.get(playerDeck.size()-1);//deal();
				playerDeck.remove(playerDeck.size()-1);
				storage.add(hidden1);
				comp = computerDeck.get(computerDeck.size()-1);//deal();
				computerDeck.remove(computerDeck.size()-1);
				storage.add(comp);
				Card hidden2 = computerDeck.get(computerDeck.size()-1);//deal();
				computerDeck.remove(computerDeck.size()-1);
				storage.add(hidden2);
				dealt += 4;
				System.out.println("You drew a " + player);
				System.out.println("The computer drew a " + comp);
				result = winner(comp, player);
			}
			if (result.equals("lose")){
				for (int i = 0; i < storage.size(); i++){
					computerDeck.add(storage.get(i));
				}
				System.out.println("The computer won " + dealt + " cards! Deck sizes: " + playerDeck.size() + "(you)" + computerDeck.size() + "(computer)");
			} else {
				for (int i = 0; i < storage.size(); i++){
					playerDeck.add(storage.get(i));
				}
				System.out.println("You won! " + dealt + " cards! Deck sizes: " + playerDeck.size() + "(you)" + computerDeck.size() + "(computer)");
			}
		}
	}

	public void split(){
		for (int i = 0; i < size / 2; i++){
			playerDeck.add(cards.get(i));
		}
		for (int r = size / 2; r < 52; r++){
			computerDeck.add(cards.get(r));
		}
	}

	public boolean pIsEmpty(){
		if (playerDeck.size() != 0){
			return false;
		}
		return true;
	}

	public boolean cIsEmpty(){
		if (computerDeck.size() != 0){
			return false;
		}
		return true;
	}
}