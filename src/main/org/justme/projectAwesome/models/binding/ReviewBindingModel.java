package justme.projectAwesome.models.binding;

import justme.projectAwesome.entities.User;

public class ReviewBindingModel {

    private String title;

    private String content;

    private User writer;

    public ReviewBindingModel() {
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
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
}
