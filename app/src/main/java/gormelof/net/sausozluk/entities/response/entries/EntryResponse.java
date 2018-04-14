package gormelof.net.sausozluk.entities.response.entries;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import gormelof.net.sausozluk.entities.Topic;
import gormelof.net.sausozluk.entities.User;

public class EntryResponse {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("text")
    @Expose
    private String text;

    @SerializedName("upvotes_count")
    @Expose
    private Integer upvotesCount;

    @SerializedName("downvotes_count")
    @Expose
    private Integer downvotesCount;

    @SerializedName("created_at")
    @Expose
    private String createdAt;

    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    @SerializedName("user")
    @Expose
    private User user;

    @SerializedName("topic")
    @Expose
    private Topic topic;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getUpvotesCount() {
        return upvotesCount;
    }

    public void setUpvotesCount(Integer upvotesCount) {
        this.upvotesCount = upvotesCount;
    }

    public Integer getDownvotesCount() {
        return downvotesCount;
    }

    public void setDownvotesCount(Integer downvotesCount) {
        this.downvotesCount = downvotesCount;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

}
