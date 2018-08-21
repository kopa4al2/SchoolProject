package justme.projectAwesome.entities.interfaces;

import justme.projectAwesome.entities.Comment;

import java.util.List;

public interface Commentable {
    List<Comment> getComments();

    void addComment(Comment comment);
}
