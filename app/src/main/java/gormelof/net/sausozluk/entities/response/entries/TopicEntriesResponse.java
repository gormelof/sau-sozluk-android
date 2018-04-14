package gormelof.net.sausozluk.entities.response.entries;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import gormelof.net.sausozluk.entities.Entry;

public class TopicEntriesResponse {

    @SerializedName("entries")
    @Expose
    private List<Entry> entries = null;

    @SerializedName("total_page")
    @Expose
    private Integer totalPage;

    @SerializedName("slug")
    @Expose
    private String slug;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("locked")
    @Expose
    private Boolean locked;

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

}
