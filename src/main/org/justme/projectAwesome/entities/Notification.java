package justme.projectAwesome.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "notifications")
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "notification_type")
public abstract class Notification {

     @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private String id;

    @Column(nullable = false)
    private String message;

    @Column
    private String urlToEvent;

    @Column
    private Boolean isSeen;

    public Notification() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrlToEvent() {
        return this.urlToEvent;
    }

    public void setUrlToEvent(String urlToEvent) {
        this.urlToEvent = urlToEvent;
    }

    public Boolean getSeen() {
        return this.isSeen;
    }

    public void setSeen(Boolean seen) {
        isSeen = seen;
    }
}
