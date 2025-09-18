package one.microstream.domain;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class Author
{
	private final String	id;
	private final String	firstname;
	private String			lastname;
	private String			email;
	private final String	gender;
	private String			alias;
	
	public Author(String id, String firstname, String email, String gender)
	{
		super();
		this.id = id;
		this.firstname = firstname;
		this.email = email;
		this.gender = gender;
	}
	
	public String getId()
	{
		return id;
	}
	
	public String getFirstname()
	{
		return firstname;
	}
	
	public String getLastname()
	{
		return lastname;
	}
	
	public synchronized void setLastname(String lastname)
	{
		this.lastname = lastname;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public synchronized void setEmail(String email)
	{
		this.email = email;
	}
	
	public String getGender()
	{
		return gender;
	}
	
	public String getAlias()
	{
		return alias;
	}
	
	public synchronized void setAlias(String alias)
	{
		this.alias = alias;
	}
	
}
