package gormelof.net.sausozluk.api;

import java.util.List;

import gormelof.net.sausozluk.entities.request.CreateEntryRequest;
import gormelof.net.sausozluk.entities.request.LoginRequest;
import gormelof.net.sausozluk.entities.request.RegisterRequest;
import gormelof.net.sausozluk.entities.response.api.ApiResponse;
import gormelof.net.sausozluk.entities.response.auth.LoginResponse;
import gormelof.net.sausozluk.entities.response.entries.CreateEntryResponse;
import gormelof.net.sausozluk.entities.response.entries.TopicEntriesResponse;
import gormelof.net.sausozluk.entities.response.topics.TopicsResponse;
import gormelof.net.sausozluk.entities.response.entries.EntryResponse;
import gormelof.net.sausozluk.entities.response.profile.ProfileResponse;
import gormelof.net.sausozluk.entities.response.random.RandomResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @POST("sessions")
    Call<ApiResponse<LoginResponse>> login(@Body LoginRequest loginRequest);

    @GET("sessions/logout")
    Call<ApiResponse> logout(@Header("token") String token);

    @POST("users")
    Call<ApiResponse> register(@Body RegisterRequest registerRequest);

    @GET("users/exit/{usernameSlug}")
    Call<ApiResponse> exit(@Header("token") String token, @Path("usernameSlug") String usernameSlug);

    @GET("users/profile/{usernameSlug}")
    Call<ApiResponse<ProfileResponse>> getProfile(@Path("usernameSlug") String usernameSlug);

    @GET("topics")
    Call<ApiResponse<TopicsResponse>> getTopics(@Query("count") int count);

    @GET("topics")
    Call<ApiResponse<TopicsResponse>> getMoreTopics(@Query("count") int count, @Query("timestamp") String timestamp);

    @GET("topics/i/random")
    Call<ApiResponse<List<RandomResponse>>> getRandom();

    @GET("topics/{topicId}")
    Call<ApiResponse<TopicEntriesResponse>> getTopicEntries(@Path("topicId") int topicId, @Query("page") int page);

    @POST("entries")
    Call<ApiResponse<CreateEntryResponse>> createEntry(@Header("token") String token, CreateEntryRequest createEntryRequest);

    @GET("topics/{entryId}")
    Call<ApiResponse<EntryResponse>> getEntry(@Path("entryId") int entryId);

    @DELETE("entries/{entryId}")
    Call<ApiResponse> deleteEntry(@Header("token") String token, @Path("entryId") int entryId);

}
