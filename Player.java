import java.util.ArrayList;

public class Player extends Person {
	private String name;
	private int chips;
	private int bet;
	public Player(String name, int chips) {
		this.name = name;
		this.chips = chips;
	}
	public String getName() {
		return name;
	}
	public int makeBet() {
		int bet = 1;
		return bet;
	}
	public boolean hit_me(Table table) {
		int total_value = getTotalValue();
		if (total_value < 17)
			return true;
		else if (total_value == 17 && hasAce()) {
			return true;
		}
		else return false;
	}
	public int getCurrentChips() {
		return chips;
	}
	public void increaseChips (int diff) {
		chips += diff;
	}
	public void sayHello() {
		System.out.println("Hello, I am " + name + ".");
	    System.out.println("I have " + chips + " chips.");
	}
	
}