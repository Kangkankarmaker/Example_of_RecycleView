package kangkan.developer.example_of_recycleview_to_json;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface get_data {

    @GET("/retrofit_class/r.php")
    Call<List<Contact>> getAllData();
}
