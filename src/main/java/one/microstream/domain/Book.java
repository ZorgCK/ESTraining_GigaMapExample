package one.microstream.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.Valid;
import one.microstream.dto.DTOBook;

@Serdeable
public class Book
{
	private final String	isbn;
	private final String	title;
	private final LocalDate	release;
	private BigDecimal		price;
	private final Author	author;
		
	public Book(String isbn, String name, LocalDate release, BigDecimal price, Author author)
	{
		super();
		this.isbn = isbn;
		this.title = name;
		this.price = price;
		this.release = release;
		this.author = author;
	}
	
	public Book(@NonNull @Valid DTOBook dto)
	{
		super();
		this.isbn = dto.isbn();
		this.title = dto.title();
		this.price = dto.price();
		this.release = dto.release();
		this.author = dto.author();
	}

	public String getIsbn()
	{
		return isbn;
	}
	
	
	public String getTitle()
	{
		return title;
	}

	public BigDecimal getPrice()
	{
		return price;
	}
	
	public Author getAuthor()
	{
		return author;
	}
	
	public LocalDate getRelease()
	{
		return release;
	}
	
	public void setPrice(BigDecimal price)
	{
		this.price = price;
	}
	
}
