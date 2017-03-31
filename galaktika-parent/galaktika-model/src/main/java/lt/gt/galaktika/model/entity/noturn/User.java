package lt.gt.galaktika.model.entity.noturn;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Access(AccessType.PROPERTY)
	private long id;

	@NotNull
	@Size(min = 3, max = 80)
	@Column(unique=true, nullable=false)
	private String email;

	@NotNull
	@Size(min = 2, max = 80)
	private String name;
	
	@Size(min = 4, max = 32)
	@Column (unique=true, nullable=false)
	private String login; 

	@Size(max = 32)
	private String password;

	public User()
	{
	}

	public User(long id)
	{
		this.id = id;
	}

	public User(String email, String name)
	{
		this.email = email;
		this.name = name;
	}

	public long getId ()
	{
		return id;
	}

	public void setId ( long value )
	{
		this.id = value;
	}

	public String getEmail ()
	{
		return email;
	}

	public void setEmail ( String value )
	{
		this.email = value;
	}

	public String getName ()
	{
		return name;
	}

	public void setName ( String value )
	{
		this.name = value;
	}

	public String getPassword ()
	{
		return password;
	}

	public void setPassword ( String password )
	{
		this.password = password;
	}

	public String getLogin ()
	{
		return login;
	}

	public void setLogin ( String login )
	{
		this.login = login;
	}

} // class User
