package com.assignment.blog;
import org.springframework.context.ApplicationEvent;

@SuppressWarnings("serial")
public class BlogCreatedEvent extends ApplicationEvent {

	public BlogCreatedEvent(Blog source) {
        super(source);	
    }
}
