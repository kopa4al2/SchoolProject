package justme.projectAwesome.services.interfaces;

import justme.projectAwesome.entities.Comment;
import justme.projectAwesome.entities.interfaces.Commentable;
import justme.projectAwesome.models.binding.CommentBindingModel;

public interface CommentService
{

    void save(CommentBindingModel comment);

    void delete(Comment c);

    void delete(String id);

    void comment(String id, CommentBindingModel commentBindingModel, Commentable entityToBeCommented);
}
