import java.util.ArrayList;

public class Table {
	public static final int MAXPLAYER = 4;
	private Deck deck;
	private Player[] player;
	private Dealer dealer;
	private int[] pos_betArray;
	public Table(int ndeck){
		deck = new Deck(ndeck);
		player = new Player[MAXPLAYER];
		pos_betArray = new int[MAXPLAYER];
	}
	public void set_player(int pos, Player p){
		player[pos] = p;
	}
	public Player[] get_player(){
		return player;
	}
	public void set_dealer(Dealer d){
		dealer = d;
	}
	public Card get_face_up_card_of_dealer(){
		ArrayList<Card> dThisRoundCard = dealer.getOneRoundCard();
		Card card;
		card = dThisRoundCard.get(1);
		return null;
	}
	private void ask_each_player_about_bets(){
		int pos;
		for(pos = 0 ; pos < MAXPLAYER ; pos ++){
			Player contestant = player[pos];
			contestant.sayHello();
			pos_betArray[pos] = contestant.makeBet();
		}
	}
	private void distribute_cards_to_dealer_and_players(){
		int count = 0 , pos = 0;
		for(count = 0 ; count < 2 ; count ++){
			for(pos = 0 ; pos < MAXPLAYER ; pos ++){
				Player contestant = player[pos];
				ArrayList<Card> FirstRound = contestant.getOneRoundCard();
				Card card = deck.getOneCard(true);				
				FirstRound.add(card);
				contestant.setOneRoundCard(FirstRound);
			}
		}
		ArrayList<Card> dFirstRound = dealer.getOneRoundCard();
		Card dcard1 = deck.getOneCard(false);
		dFirstRound.add(dcard1);
		Card dcard2 = deck.getOneCard(true);
		dFirstRound.add(dcard2);
		dealer.setOneRoundCard(dFirstRound);
		System.out.print("Dealer's face up card is ");
		dcard2.printCard();
	}
	private void ask_each_player_about_hits(){
		int pos = 0;
		for(pos = 0 ; pos < MAXPLAYER ; pos ++){
			Player contestant = player[pos];
			boolean hit=false;
			do{
				if(contestant.getTotalValue()>21)break;
				hit=contestant.hit_me(this);
				if(hit){
					contestant.getOneRoundCard().add(deck.getOneCard(true));
					contestant.setOneRoundCard(contestant.getOneRoundCard());
					System.out.print("Hit! ");
					System.out.println(contestant.getName()+"'s Cards now:");
					for(Card c : contestant.getOneRoundCard()){
						c.printCard();
					}
				}
				else{
					System.out.println(contestant.getName()+", Pass hit!");
					System.out.println(contestant.getName()+", Final Card:");
					for(Card c : contestant.getOneRoundCard()){
						c.printCard();
					}
				}
			}while(hit);
		}
	}
	private void ask_dealer_about_hits(){
		boolean hit=false;
		do{
			if(dealer.getTotalValue()>21)break;
			hit=dealer.hit_me(this); //this
			if(hit){
				dealer.getOneRoundCard().add(deck.getOneCard(true));
				dealer.setOneRoundCard(dealer.getOneRoundCard());
				System.out.print("Hit! ");
				System.out.println("Dealer's Cards now:");
				for(Card c : dealer.getOneRoundCard()){
					c.printCard();
				}
			}
			else{
				System.out.println("Dealer's hit is over!");
	        }
		}while(hit);
	}
	private void calculate_chips(){
		ArrayList<Card> dThisRound  = new ArrayList<>();
		dThisRound = dealer.getOneRoundCard();
		int count = 0 , pos = 0;
		System.out.println("Dealer's card value is "+dealer.getTotalValue()+" ,Cards:");
		for(count = 0 ; count < dThisRound.size() ; count++){
			Card card = dThisRound.get(count);
			card.printCard();
		}
		for(pos = 0 ; pos < MAXPLAYER ; pos++){
			Player contestant = player[pos];
			if(dealer.getTotalValue()>21 && contestant.getTotalValue()>21){
				System.out.println("Need another game");
			}else if(dealer.getTotalValue()<=21&&contestant.getTotalValue()>21){
				System.out.println("Dealer wins the game");
				contestant.increaseChips(-pos_betArray[pos]);
			}else if(dealer.getTotalValue()>21&&contestant.getTotalValue()<=21){
				System.out.println(contestant.getName()+" wins the game");
				contestant.increaseChips(pos_betArray[pos]);
			}else if(dealer.getTotalValue()>contestant.getTotalValue()&&dealer.getTotalValue()<=21){
				System.out.println("Dealer wins the game");
				contestant.increaseChips(-pos_betArray[pos]);
			}else if(dealer.getTotalValue()<contestant.getTotalValue()&&contestant.getTotalValue()<=21){
				System.out.println(contestant.getName()+" wins the game");
				contestant.increaseChips(pos_betArray[pos]);
			}else{
				System.out.println("Need another game");
			}
		}
	}
	public int[] get_palyers_bet(){
		return pos_betArray;
	}
	public void play(){
		ask_each_player_about_bets();
		distribute_cards_to_dealer_and_players();
		ask_each_player_about_hits();
		ask_dealer_about_hits();
		calculate_chips();
	}
}
