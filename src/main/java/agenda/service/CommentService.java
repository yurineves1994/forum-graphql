package agenda.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import agenda.entities.Comment;

@Service
public class CommentService {
  Map<String, Comment> comments = new HashMap<>();

  public Collection<Comment> createComment(String content, String postId) {
    var newComment = new Comment(UUID.randomUUID().toString(), content, postId);

    comments.put(newComment.id(), newComment);

    return comments.values();
  }

  public Collection<Comment> findPostById(String id) {
    return comments.values().stream().filter(comment -> comment.postId().equals(id)).toList();
  }
}
