package prepaidsystem;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "cardMovements")
@XmlAccessorType(XmlAccessType.FIELD)
public class CardMovements {
	
	@XmlElement(name = "movement")
	private List<Movement> cardsMovements = null;

	public List<Movement> getMovementsList() {
		return cardsMovements;
	}

	public void setCardMovements(List<Movement> cardMovements) {
		this.cardsMovements = cardMovements;
	}
	
	public static  Movement lastMovement() throws JAXBException{
		Lists.getMovementsList().setCardMovements(new ArrayList<Movement>());
		PrepaidSystem.unMarshalingDataBase();
		if(Lists.getMovementsList().getMovementsList().isEmpty()) {
			return null;
		}
		else {
			return  Lists.getMovementsList().getMovementsList().get(Lists.getMovementsList().getMovementsList().size()-1);	
		}
	}

}
