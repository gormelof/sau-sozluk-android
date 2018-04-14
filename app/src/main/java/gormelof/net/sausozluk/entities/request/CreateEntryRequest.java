package gormelof.net.sausozluk.entities.request;

public class CreateEntryRequest {

    private int topic_id;
    private String text;

    public CreateEntryRequest(int topic_id, String text) {
        this.topic_id = topic_id;
        this.text = text;
    }

}
