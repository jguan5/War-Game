import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
public class WarRunner{
    
   /**
	 * The ranks of the cards for this game to be sent to the deck.
	 */
	private static final String[] RANKS =
		{"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

	/**
	 * The suits of the cards for this game to be sent to the deck.
	 */
	private static final String[] SUITS =
		{"♠", "♥", "♦", "♣"};

	/**
	 * The values of the cards for this game to be sent to the deck.
	 */
	private static final int[] POINT_VALUES =
		{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 0, 0, 0};
      
      
   public static void main(String[] args)
   {
     //beginningDeck is the Deck we start with.  When we deal, it gets split into two 
     //Decks for each player 
	 Scanner scanner = new Scanner(System.in);

     Deck beginningDeck = new Deck(RANKS,SUITS,POINT_VALUES);
     beginningDeck.shuffle();
     System.out.println(beginningDeck);

	 Deck playerDeck = new Deck();
	 Deck computerDeck = new Deck();

	 //Divides the beginning deck into two and stores them into the player and computer deck
	 beginningDeck.split();
	 System.out.println("Cards initialized");

	while (!playerDeck.pIsEmpty() || !computerDeck.cIsEmpty()){
		System.out.print("Press 'ENTER' to go to war or 'S' to shuffle your deck: ");
		String userInput = scanner.nextLine();
		beginningDeck.battle(userInput);
	}
	if (playerDeck.pIsEmpty()){
		System.out.println("Player Deck is empty. \n LOL, YOU LOST 🤣😍❤😂");
	} else {
		System.out.println("Computer Deck is empty. \n You won... I guess..");
	}

   }     
}