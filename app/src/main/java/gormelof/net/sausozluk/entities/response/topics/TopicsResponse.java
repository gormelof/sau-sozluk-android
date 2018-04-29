package gormelof.net.sausozluk.entities.response.topics;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import gormelof.net.sausozluk.entities.Topic;

public class TopicsResponse {

    // @SerializedName("entries_count")
    // @Expose
    // private Integer entriesCount;

    @SerializedName("topics")
    @Expose
    private List<Topic> topics = null;

    // @SerializedName("topics_count")
    // @Expose
    // private String topicsCount;

    /*
    public Integer getEntriesCount() {
        return entriesCount;
    }

    public void setEntriesCount(Integer entriesCount) {
        this.entriesCount = entriesCount;
    }
    */

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    /*
    public String getTopicsCount() {
        return topicsCount;
    }

    public void setTopicsCount(String topicsCount) {
        this.topicsCount = topicsCount;
    }
    */

}

