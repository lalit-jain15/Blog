package com.assignment.blog;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.Query;

import reactor.core.publisher.Mono;


public class CustomBlogCommentRepositoryImpl implements CustomBlogCommentRepository {
	
	private final ReactiveMongoTemplate mongoTemplate;

    @Autowired
    public CustomBlogCommentRepositoryImpl(ReactiveMongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    
    @Override
    public Mono<BlogComment> addNewComment(ObjectId _id, ObjectId _blog_id, List<Comment> comment) {
    	   Query query = new Query(Criteria.where("_blog_id").is(_blog_id));
    	   Update update = new Update();
    	   for(Comment tmpCmt:comment) {
    		update.addToSet("comments", tmpCmt);	
    	   }
    	
            
        return mongoTemplate.findAndModify(query, update , BlogComment.class);
    }
    
    }
