package gormelof.net.sausozluk.entities.response.entries;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateEntryResponse {

    @SerializedName("id")
    @Expose
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
