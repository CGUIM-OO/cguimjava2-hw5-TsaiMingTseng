
public class Card {

public enum Suit { Club, Diamond, Heart, Spade};
	private int rank;
	private Suit suit;
	public Card(int s,int r){
		rank=r;
		if(s == 1)
		{
	        suit = Suit.Club;
		}
		if(s == 2)
		{
			suit = Suit.Diamond;
		}
		if(s == 3)
		{
			suit = Suit.Heart;
		}
		if(s == 4)
		{
			suit = Suit.Spade;
		}
	}	
	public static char[] card(Card number) {
		return null;
	}
	public void printCard(){
		String number[] = {"Ace" , "2" , "3" , "4" , "5" , "6" ,
				"7" , "8" , "9" , "10" , "J" , "Q" , "K"};
		System.out.println(suit + "\t" +number[rank-1]);
	}
	public Card.Suit getSuit(){
		return suit;
	}
	public int getRank(){
		return rank;
	}
}

