package com.assignment.blog;

import org.springframework.data.annotation.Id;

import java.util.Date;

import org.bson.types.ObjectId;

public class Blog {
	
		
		@Id
    	private ObjectId _id;
		
		private final String author;
		private final String title;
		private final String content;
		private final Date publishedDate;
			      		
		

		public Blog(String author, Date publishedDate, String title, String content, ObjectId _id) {
			this.author = author;
			this.publishedDate = publishedDate;
			this.title = title;
			this.content = content;
			this._id = _id;
			
		}


		public String getAuthor() {
			return author;
		}
	
				
		public String getTitle() {
			return title;
		}

		public String getContent() {
			return content;
		}

		public Date getPublishedDate() {
			return publishedDate;
		}

		public String get_id() { return _id.toHexString(); }
		
		public void set_id(ObjectId _id) { this._id = _id; }

}
