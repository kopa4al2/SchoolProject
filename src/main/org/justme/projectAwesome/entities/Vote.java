package justme.projectAwesome.entities;

import justme.projectAwesome.entities.enums.VoteType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Vote {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private String id;

    @Column(name="vote_type", nullable = false)
    private VoteType voteType;

    @ManyToOne
    private User voteOwner;

    @ManyToOne(cascade = CascadeType.ALL)
    private Comment voteContent;

    public Vote() {
    }

    public User getVoteOwner() {
        return this.voteOwner;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setVoteOwner(User voteOwner) {
        this.voteOwner = voteOwner;
    }

    public Comment getVoteContent() {
        return this.voteContent;
    }

    public void setVoteContent(Comment voteContent) {
        this.voteContent = voteContent;
    }

    public VoteType getVoteType() {
        return this.voteType;
    }

    public void setVoteType(VoteType voteType) {
        this.voteType = voteType;
    }



}
