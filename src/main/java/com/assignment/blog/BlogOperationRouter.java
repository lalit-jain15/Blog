package com.assignment.blog;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class BlogOperationRouter {

	
	  @Bean
	  public RouterFunction<ServerResponse> route(BlogOperationHandler blogOpsHandler) {
		  			  	
		  	return RouterFunctions
		    .route(RequestPredicates.GET("/getBlogs/{id}").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), blogOpsHandler::getBlog)
		  	.andRoute(RequestPredicates.GET("/getByAuthor/{author}").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), blogOpsHandler::findByAuthor)
		  	.andRoute(RequestPredicates.POST("/create").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), blogOpsHandler::createBlog)
		  	.andRoute(RequestPredicates.POST("/update").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), blogOpsHandler::updateBlog)
		  	.andRoute(RequestPredicates.POST("/addComment").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), blogOpsHandler::addNewComment)
		  	.andRoute(RequestPredicates.POST("/addFirstComment").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), blogOpsHandler::addFirstComment);

	  }
	
	
	
}
