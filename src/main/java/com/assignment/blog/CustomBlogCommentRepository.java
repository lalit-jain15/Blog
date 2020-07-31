package com.assignment.blog;

import java.util.List;

import org.bson.types.ObjectId;

import reactor.core.publisher.Mono;

public interface CustomBlogCommentRepository {

	public Mono<BlogComment> addNewComment(ObjectId _id, ObjectId _blog_id,  List<Comment> comments);
}


