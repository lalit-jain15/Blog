package com.assignment.blog;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import reactor.core.publisher.Flux;



	interface BlogRepository extends ReactiveMongoRepository<Blog, String> {


		 	@Query("{ 'author': ?0 }")
		    Flux<Blog> findByAuthor(final String author, Sort sort); //sorting based on creation date of object
}
