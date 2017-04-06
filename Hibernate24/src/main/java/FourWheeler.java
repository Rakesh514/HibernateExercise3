import javax.persistence.Entity;

@Entity
public class FourWheeler extends Vehicle{

	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
