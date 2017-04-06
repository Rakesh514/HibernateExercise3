import javax.persistence.Entity;

@Entity
public class TwoWheeler extends Vehicle {

	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
