import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
//The below annotation is not compulsory
@DiscriminatorValue("2Wheeler")
public class TwoWheeler extends Vehicle {

	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
