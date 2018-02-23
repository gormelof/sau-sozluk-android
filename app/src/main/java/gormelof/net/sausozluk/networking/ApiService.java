package gormelof.net.sausozluk.networking;

import java.util.List;

import gormelof.net.sausozluk.models.ApiResponse;
import gormelof.net.sausozluk.models.random.Random;
import gormelof.net.sausozluk.models.topics.TopicResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("topics")
    Call<ApiResponse<TopicResponse>> getTopics(@Query("count") String count);

    @GET("topics/i/random")
    Call<ApiResponse<List<Random>>> getRandoms();

}
