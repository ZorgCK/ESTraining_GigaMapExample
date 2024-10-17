package one.microstream.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import io.micronaut.serde.annotation.Serdeable;
import one.microstream.dto.DTOBook;


@Serdeable
public class Book
{
	private final String	ISBN;
	private final String	title;
	private final LocalDate	publicationDate;
	private final int		edition;
	
	private int				availableQuantity;
	private BigDecimal		price;
	
	public Book(DTOBook dto)
	{
		super();
		
		this.ISBN = dto.ISBN();
		this.title = dto.title();
		this.publicationDate = dto.publicationDate();
		this.edition = dto.edition();
		this.availableQuantity = dto.availableQuantity();
		
		this.price = new BigDecimal(dto.price());
	}
	
	public int getAvailableQuantity()
	{
		return availableQuantity;
	}
	
	public void setAvailableQuantity(int availableQuantity)
	{
		this.availableQuantity = availableQuantity;
	}
	
	public BigDecimal getPrice()
	{
		return price;
	}
	
	public void setPrice(BigDecimal price)
	{
		this.price = price;
	}
	
	public String getISBN()
	{
		return ISBN;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public LocalDate getPublicationDate()
	{
		return publicationDate;
	}
	
	public int getEdition()
	{
		return edition;
	}
	
}
