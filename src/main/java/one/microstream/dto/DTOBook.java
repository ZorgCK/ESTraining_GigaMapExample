package one.microstream.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import io.micronaut.serde.annotation.Serdeable;
import one.microstream.domain.Author;

@Serdeable
public record DTOBook(String isbn, String title, LocalDate release, BigDecimal price, Author author)
{
	
}
