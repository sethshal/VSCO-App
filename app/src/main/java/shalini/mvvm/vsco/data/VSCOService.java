package shalini.mvvm.vsco.data;

import retrofit2.http.GET;
import io.reactivex.Observable;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface VSCOService {

  /**
   * The is for the rest endpoint call that is made using retrofit.
   * @return an observable response
   */

  @Headers("Content-Type: application/json")
  @GET("/api/")
  Observable<VSCOResponse> fetchHits(
          @Query("safesearch") boolean safesearch,
          @Query(value="q", encoded=true) String q );
}
