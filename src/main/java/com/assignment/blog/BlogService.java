package com.assignment.blog;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Sort;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class BlogService {
	
    private final ApplicationEventPublisher publisher;
	private final BlogRepository blogRepository;
	private final BlogCommentRepository blogCommentRepository;

	
	BlogService(ApplicationEventPublisher publisher, BlogRepository blogRepository, BlogCommentRepository blogCommentRepository) {
        this.publisher = publisher;
        this.blogRepository = blogRepository;
        this.blogCommentRepository = blogCommentRepository;
    }

	public Mono<Blog> update(String author, Date publishedDate, String title, String content, String uniqueid) {
     
		
		return this.blogRepository
            .findById(uniqueid)
            .map(p -> new Blog(author,publishedDate , title, content, new ObjectId(uniqueid)))
            .flatMap(this.blogRepository::save);
    }
	
	 public Mono<Blog> create(String author, Date publishedDate, String title, String content, String uniqueid) { 
		 
		  
		   return this.blogRepository
	            .save(new Blog( author, publishedDate, title,  content, null))
	            .doOnSuccess(blog -> this.publisher.publishEvent(new BlogCreatedEvent(blog)));
	    }
	 
	 public Mono<Blog> get(String id) { // <4>
	        return this.blogRepository.findById(id);
	        }
	 
	 public Flux<Blog> findByAuthor(String author) {
			
		 	
		 	return this.blogRepository.findByAuthor(author, Sort.by(Sort.Direction.DESC,"_id"));
	    }
	 
	 
	 public Mono<BlogComment> addNewComment(String _id, String _blog_id, List<Comment> comments) {
		 
		return this.blogCommentRepository.addNewComment(new ObjectId(_id), new ObjectId(_blog_id), comments) ;   
	      
	 }

	 public Mono<BlogComment> addFirstComment(String _id, String _blog_id, List<Comment> comments) {
		 
	
		 return this.blogCommentRepository
		            .save(new BlogComment(null, new ObjectId(_blog_id), comments));   
	      
	 }
	 
	 
}
