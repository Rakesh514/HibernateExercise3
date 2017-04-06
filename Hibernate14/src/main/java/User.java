import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tblUser")
/*Notice the table name and column name in the query. 
 * It is not the entity name and field name as we are using native query
 * and not HQL.
 * Also, we need set 'resultClass' to make hibernate understand the type of the return type.*/ 
@NamedNativeQuery(name="User.byFirstName", query="select * from tblUser where first_name = ?", resultClass=User.class)
public class User {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;
	
	public User() {
	}

	public User(String fname, String lname) {
		this.firstName = fname;
		this.lastName = lname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String first_name) {
		this.firstName = first_name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String last_name) {
		this.lastName = last_name;
	}

}