package prepaidsystem;

import java.util.ArrayList;


public class Lists {

	private static CardList cardList = new CardList();
	private static CardMovements movementsList = new CardMovements();
	
	private Lists () {
		cardList.setCardList(new ArrayList<Card>());
		movementsList.setCardMovements(new ArrayList<Movement>());
	}
	
	public static Lists getListsConstructor () {
		return new Lists ();
	}

	public static CardList getCardList() {
		return cardList;
	}

	public static void setCardList(CardList cardList) {
		Lists.cardList = cardList;
	}

	public static CardMovements getMovementsList() {
		return movementsList;
	}

	public static void setMovementsList(CardMovements movementsList) {
		Lists.movementsList = movementsList;
	}
	
}
