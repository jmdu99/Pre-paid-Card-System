package prepaidsystem;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "cardList")
@XmlAccessorType(XmlAccessType.FIELD)
public class CardList {
	
	@XmlElement(name = "card")
	private List<Card> cardsList = null;
	
	public List<Card> getCardList() {
		return cardsList;
	}

	public void setCardList(List<Card> cardList) {
		this.cardsList = cardList;
	}

	// Returns the card which has the input cardNumber
	public static Card cardNumberInSystem(String cardNumber) {
		Card card = null;
		boolean encontrado = false;
		int i  = 0;
		while (!encontrado && i < Lists.getCardList().getCardList().size()) {
			if (cardNumber.equals(Lists.getCardList().getCardList().get(i).getCardNumber())) {
				card = Lists.getCardList().getCardList().get(i);
				encontrado = true;
			}
			i++;
		}
		return card;
	}
	
	public static Card lastCard() throws JAXBException {
		Lists.getCardList().setCardList(new ArrayList<Card>());
		PrepaidSystem.unMarshalingDataBase();
		if (Lists.getCardList().getCardList().isEmpty()) {
			return null;
		} else {
			return Lists.getCardList().getCardList().get(Lists.getCardList().getCardList().size() - 1);
		}
	}

}
