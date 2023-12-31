package agenda.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import agenda.entities.Comment;
import agenda.entities.Post;
import agenda.service.CommentService;
import agenda.service.PostService;

@Controller
public class ForumController {
  @Autowired
  PostService postService;

  @Autowired
  CommentService commentService;

  // @SchemaMapping(typeName = "Query", value = "postById")
  @QueryMapping
  public Post postById(@Argument String id) {
    System.out.println("postById");
    return postService.postById(id);
  }

  // @SchemaMapping(typeName = "Mutation", value = "createPost")
  @MutationMapping
  public Collection<Post> createPost(@Argument String content) {
    return postService.createPost(content);
  }

  // @SchemaMapping(typeName = "Mutation", value = "createComment")
  @MutationMapping
  public Collection<Comment> createComment(@Argument String content, @Argument String postId) {
    return commentService.createComment(content, postId);
  }

  @SchemaMapping // esse comments é o nome do campo que está no schema
  public Collection<Comment> comments(Post post) {
     System.out.println("comments");
    return commentService.findPostById(post.id());
  }
}
