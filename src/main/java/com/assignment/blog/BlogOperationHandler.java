package com.assignment.blog;

import org.reactivestreams.Publisher;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.net.URI;

import reactor.core.publisher.Mono;

@Component
class BlogOperationHandler {
	
	private final BlogService blogService;
	
	BlogOperationHandler(BlogService blogService){
   
		this.blogService = blogService;
    }
		
	public Mono<ServerResponse> createBlog(ServerRequest request) {	
		Mono<Blog> blog = request.bodyToMono(Blog.class).flatMap(toWrite -> this.blogService.create(toWrite.getAuthor(), toWrite.getPublishedDate(), toWrite.getTitle(), toWrite.getContent(), null));
		return defaultWriteResponse(blog);
		
	}
	
	public Mono<ServerResponse> updateBlog(ServerRequest request) {	
		Mono<Blog> blog = request.bodyToMono(Blog.class).flatMap(toWrite -> this.blogService.update(toWrite.getAuthor(), toWrite.getPublishedDate(), toWrite.getTitle(), toWrite.getContent(), toWrite.get_id()));
		return defaultWriteResponse(blog);
		
	}
	
	Mono<ServerResponse> getBlog(ServerRequest request) {
        return defaultReadResponse(this.blogService.get(id(request)));
    }
	
	Mono<ServerResponse> findByAuthor(ServerRequest request) {
		return defaultReadResponse(this.blogService.findByAuthor(author(request)));
    }
	
	
	 private static Mono<ServerResponse> defaultWriteResponse(Publisher<Blog> blog) {
	        return Mono
	            .from(blog)
	            .flatMap(p -> ServerResponse
	                .created(URI.create("/blog/" + p.get_id()))
	                .contentType(MediaType.APPLICATION_JSON)
	                .build()
	            );
	    }
	 
	 private static Mono<ServerResponse> defaultReadResponse(Publisher<Blog> blogs) {
	        return ServerResponse
	            .ok()
	            .contentType(MediaType.APPLICATION_JSON)
	            .body(blogs, Blog.class);
	    }
	 
		 
	 private static String id(ServerRequest r) {
	        return r.pathVariable("id");
	    }
	
	 private static String author(ServerRequest r) {
	        return r.pathVariable("author");
	    }
	
	 public Mono<ServerResponse> addNewComment (ServerRequest request){
	     
	        Mono<BlogComment> blogComment=  request.bodyToMono(BlogComment.class).flatMap(toWrite -> this.blogService.addNewComment(toWrite.get_id(), toWrite.get_blog_id(),toWrite.getComments()));
	        return defaultCommentResponse(blogComment);
	    }
	 
	 public Mono<ServerResponse> addFirstComment(ServerRequest request) {	
			Mono<BlogComment> blogComment = request.bodyToMono(BlogComment.class).flatMap(toWrite -> this.blogService.addFirstComment(null, toWrite.get_blog_id(), toWrite.getComments()));
			return defaultCommentResponse(blogComment);
			
		}
	 
	 private static Mono<ServerResponse> defaultCommentResponse(Publisher<BlogComment> blogComment) {
		      return Mono
	            .from(blogComment)
	            .flatMap(p -> ServerResponse
	                .created(URI.create("/blogComment/" + p.get_id()))
	                .contentType(MediaType.APPLICATION_JSON)
	                .build()
	            );
	    }
}
