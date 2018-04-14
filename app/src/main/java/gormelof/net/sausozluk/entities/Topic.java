package gormelof.net.sausozluk.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Topic {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("slug")
    @Expose
    private String slug;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("count")
    @Expose
    private Integer count;

    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    @SerializedName("created_at")
    @Expose
    private String createdAt;

    @SerializedName("page")
    @Expose
    private Integer page;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

}
