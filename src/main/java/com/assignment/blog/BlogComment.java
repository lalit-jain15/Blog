package com.assignment.blog;

import org.springframework.data.annotation.Id;

import org.bson.types.ObjectId;
import java.util.List;

public class BlogComment {
	
		
		
		@Id
    	private ObjectId _id;
		
		private final ObjectId _blog_id;
		List<Comment> comments;
	
		
		//DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	    
		
		public BlogComment( ObjectId _id, ObjectId _blog_id, List<Comment> comments) {
			this.comments =comments;
			this._blog_id = _blog_id;
			this._id = _id;
			
		}


		public String get_id() { return _id.toHexString(); }
		
		public void set_id(ObjectId _id) { this._id = _id; }

		
		public String get_blog_id() { return _blog_id.toHexString(); }
		
		public List<Comment> getComments() {
			return comments;
		}


			public void setComments(List<Comment> comments) {
				this.comments = comments;
			}
		

}