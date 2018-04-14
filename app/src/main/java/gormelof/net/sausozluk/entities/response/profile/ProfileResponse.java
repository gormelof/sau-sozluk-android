package gormelof.net.sausozluk.entities.response.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProfileResponse {

    @SerializedName("entry_count")
    @Expose
    private Integer entryCount;

    @SerializedName("last_entries")
    @Expose
    private List<LastEntry> lastEntries = null;

    @SerializedName("most_liked")
    @Expose
    private List<MostLiked> mostLiked = null;

    @SerializedName("most_hated")
    @Expose
    private List<MostHated> mostHated = null;

    @SerializedName("liked")
    @Expose
    private List<Liked> liked = null;

    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("last_activities")
    @Expose
    private List<LastActivity> lastActivities = null;

    @SerializedName("generation")
    @Expose
    private String generation;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("online")
    @Expose
    private Boolean online;

    public Integer getEntryCount() {
        return entryCount;
    }

    public void setEntryCount(Integer entryCount) {
        this.entryCount = entryCount;
    }

    public List<LastEntry> getLastEntries() {
        return lastEntries;
    }

    public void setLastEntries(List<LastEntry> lastEntries) {
        this.lastEntries = lastEntries;
    }

    public List<MostLiked> getMostLiked() {
        return mostLiked;
    }

    public void setMostLiked(List<MostLiked> mostLiked) {
        this.mostLiked = mostLiked;
    }

    public List<MostHated> getMostHated() {
        return mostHated;
    }

    public void setMostHated(List<MostHated> mostHated) {
        this.mostHated = mostHated;
    }

    public List<Liked> getLiked() {
        return liked;
    }

    public void setLiked(List<Liked> liked) {
        this.liked = liked;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<LastActivity> getLastActivities() {
        return lastActivities;
    }

    public void setLastActivities(List<LastActivity> lastActivities) {
        this.lastActivities = lastActivities;
    }

    public String getGeneration() {
        return generation;
    }

    public void setGeneration(String generation) {
        this.generation = generation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

}
