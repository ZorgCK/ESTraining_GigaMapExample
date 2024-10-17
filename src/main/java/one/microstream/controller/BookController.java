package one.microstream.controller;

import java.util.List;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import one.microstream.domain.Book;
import one.microstream.dto.DTOBook;
import one.microstream.repository.DAOBook;


@Controller("/gigaBooks")
public class BookController
{
	private final DAOBook dao;
	
	public BookController(DAOBook dao)
	{
		this.dao = dao;
	}
	
	@Get("/count")
	HttpResponse<Long> countBooks()
	{
		return HttpResponse.ok(dao.countBooks());
	}
	
	@Get("/page/{limit}")
	List<Book> pageAllBooks(@NonNull @NotBlank @PathVariable int limit)
	{
		return dao.pageBooks(limit);
	}
	
	@Get("/search/{searchTerm}")
	List<Book> searchBook(@NonNull @NotBlank @PathVariable String searchTerm)
	{
		return dao.searchBooksTitle(searchTerm);
	}
	
	@Get("/{isbn}")
	Book getBook(@NonNull @NotBlank @PathVariable String isbn)
	{
		return dao.getBookISBN(isbn);
	}
	
	@Post
	HttpResponse<String> create(@NonNull @Valid @Body DTOBook dto)
	{
		dao.insert(new Book(dto));
		return HttpResponse.ok("Successfully created");
	}
}
