package gormelof.net.sausozluk.entities.response.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LastActivity {

    @SerializedName("_id")
    @Expose
    private String id;

    @SerializedName("data")
    @Expose
    private Data data;

    @SerializedName("action")
    @Expose
    private String action;

    @SerializedName("date")
    @Expose
    private String date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
