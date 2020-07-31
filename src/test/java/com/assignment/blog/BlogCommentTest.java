package com.assignment.blog;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(BlogService.class)
class BlogCommentTest {

	private final BlogService service;
    private final BlogCommentRepository repository;

    public BlogCommentTest(@Autowired BlogService service,    
    		@Autowired BlogCommentRepository repository) {
        this.service = service;
        this.repository = repository;
    }
    
	@Autowired
    private WebTestClient webClient;
	
	@Test
	void testAddComment() {

		Comment comment = new Comment();
		comment.setAuthor("author");
		comment.setComment("Comment added by author on this blog");
		List<Comment> commentList = new ArrayList<Comment>();
		commentList.add(comment);
		
		BlogComment blogComment = new BlogComment(new ObjectId("5f1e403c20dfbc435d9d6e9e"), new ObjectId("5f1e403c20dfbc435d9d6e9e"), commentList);
		//Mockito.when(service.create("AuthorHandle", "Title of Blog", "here is content of title", "5f1c9d15aee54538372e6dcd")).thenReturn(Mono.just(returnBlog));
		//Mockito.when(repository.save(blog)).thenReturn(Mono.just(returnBlog));
				
		webClient
		.post().uri("/addComment")
		.accept(MediaType.APPLICATION_JSON)
		.body(BodyInserters.fromObject(blogComment))
		.exchange()
		.expectStatus().isOk();
		
		//Mockito.verify(repository, times(1)).save(returnBlog);
	}

	@Test
	void testAddFirstComment() {

		Comment comment = new Comment();
		comment.setAuthor("author");
		comment.setComment("Comment added by author on this blog");
		List<Comment> commentList = new ArrayList<Comment>();
		commentList.add(comment);
		
		BlogComment blogComment = new BlogComment(new ObjectId("5f1e403c20dfbc435d9d6e9e"), new ObjectId("5f1e403c20dfbc435d9d6e9e"), commentList);
		//Mockito.when(service.create("AuthorHandle", "Title of Blog", "here is content of title", "5f1c9d15aee54538372e6dcd")).thenReturn(Mono.just(returnBlog));
		//Mockito.when(repository.save(blog)).thenReturn(Mono.just(returnBlog));
				
		webClient
		.post().uri("/addFirstComment")
		.accept(MediaType.APPLICATION_JSON)
		.body(BodyInserters.fromObject(blogComment))
		.exchange()
		.expectStatus().isCreated();
		
		//Mockito.verify(repository, times(1)).save(returnBlog);
	}
	
}
