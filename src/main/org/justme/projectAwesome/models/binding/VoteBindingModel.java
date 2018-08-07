package justme.projectAwesome.models.binding;

import justme.projectAwesome.entities.Comment;
import justme.projectAwesome.entities.User;
import justme.projectAwesome.entities.enums.VoteType;

public class VoteBindingModel {

    private VoteType voteType;

    private Comment voteContent;

    private User voteOwner;

    public VoteBindingModel() {
    }

    public VoteType getVoteType() {
        return this.voteType;
    }

    public void setVoteType(VoteType voteType) {
        this.voteType = voteType;
    }

    public Comment getVoteContent() {
        return this.voteContent;
    }

    public void setVoteContent(Comment comment) {
        this.voteContent = comment;
    }

    public User getVoteOwner() {
        return this.voteOwner;
    }

    public void setVoteOwner(User voteOwner) {
        this.voteOwner = voteOwner;
    }
}
