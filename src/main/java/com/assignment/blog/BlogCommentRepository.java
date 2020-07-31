package com.assignment.blog;
import java.util.List;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;


@Repository
public interface BlogCommentRepository extends ReactiveMongoRepository<BlogComment, String>, CustomBlogCommentRepository{

	
	    Mono<BlogComment> addNewComment (ObjectId _id, ObjectId _blog_id, List<Comment> comments);


		 	
}



