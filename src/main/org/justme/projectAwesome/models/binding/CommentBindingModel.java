package justme.projectAwesome.models.binding;

import justme.projectAwesome.entities.User;

import java.util.Date;

public class CommentBindingModel {

    private String content;

    private User writer;
    private Date createdOn;

    public CommentBindingModel() {
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getWriter() {
        return this.writer;
    }

    public void setWriter(User writer) {
        this.writer = writer;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getCreatedOn() {
        return createdOn;
    }
}
