
package gormelof.net.sausozluk.models.topics;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TopicResponse {

    @SerializedName("entries_count")
    @Expose
    private Integer entriesCount;
    @SerializedName("topics")
    @Expose
    private List<Topic> topics = null;
    @SerializedName("topics_count")
    @Expose
    private Integer topicsCount;

    public Integer getEntriesCount() {
        return entriesCount;
    }

    public void setEntriesCount(Integer entriesCount) {
        this.entriesCount = entriesCount;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public Integer getTopicsCount() {
        return topicsCount;
    }

    public void setTopicsCount(Integer topicsCount) {
        this.topicsCount = topicsCount;
    }

}
