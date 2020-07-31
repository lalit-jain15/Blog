package com.assignment.blog;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

import java.util.Date;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(BlogService.class)
class BlogTest {

	
	private final BlogService service;
    private final BlogRepository repository;

    public BlogTest(@Autowired BlogService service,    
    		@Autowired BlogRepository repository) {
        this.service = service;
        this.repository = repository;
    }
    
    
	@Autowired
    private WebTestClient webClient;
	
		
	@Test
	void testCreateBlog()
	{
		
		Blog blog = new Blog("AuthorHandle", new Date(), "Title of Blog", "here is content of title", new ObjectId("5f1c9d15aee54538372e6dcd"));
		Blog returnBlog = new Blog("AuthorHandle", new Date(), "Title of Blog", "here is content of title", new ObjectId("5f1c9d15aee54538372e6dcd"));
		//Mockito.when(service.create("AuthorHandle", "Title of Blog", "here is content of title", "5f1c9d15aee54538372e6dcd")).thenReturn(Mono.just(returnBlog));
		//Mockito.when(repository.save(blog)).thenReturn(Mono.just(returnBlog));
				
		webClient
		.post().uri("/create")
		.accept(MediaType.APPLICATION_JSON)
		.body(BodyInserters.fromObject(blog))
		.exchange()
		.expectStatus().isCreated();
		
		//Mockito.verify(repository, times(1)).save(returnBlog);
		
	}

	@Test
	void testFindByAuthor() {

		Blog returnBlog = new Blog("AuthorHandle", new Date(), "Title of Blog", "here is content of title", new ObjectId("5f1c9d15aee54538372e6dcd"));

		//final String author, Sort sort
		//Mockito.when(repository.findByAuthor("Test", Sort.by(Sort.Direction.DESC,"_id"))).thenReturn(Flux.just(returnBlog));
		
		webClient.get().uri("/getByAuthor/{author}", "Test")
		.header(HttpHeaders.ACCEPT, "application/json")
		.exchange()
		.expectStatus().isOk()
		.expectBodyList(Blog.class);
		
		//Mockito.verify(repository, times(1)).findByAuthor("Test", Sort.by(Sort.Direction.DESC,"_id"));
	}
	
	@Test
	void testUpdateBlog() {
		
		Blog blog = new Blog("AuthorHandle", new Date(), "Title of Blog Changed", "here is content of title", new ObjectId("5f1c9d15aee54538372e6dcd"));
		Blog returnBlog = new Blog("AuthorHandle", new Date(),"Title of Blog Changed ", "here is content of title", new ObjectId("5f1c9d15aee54538372e6dcd"));
		//Mockito.when(service.update("AuthorHandle", "Title of Blog", "here is content of title", "5f1c9d15aee54538372e6dcd")).thenReturn(Mono.just(returnBlog));
		//Mockito.when(repository.update(blog)).thenReturn(Mono.just(returnBlog));
				
		webClient
		.post().uri("/create")
		.accept(MediaType.APPLICATION_JSON)
		.body(BodyInserters.fromObject(blog))
		.exchange()
		.expectStatus().isCreated();
		
		//Mockito.verify(repository, times(1)).save(returnBlog);
	}

}
