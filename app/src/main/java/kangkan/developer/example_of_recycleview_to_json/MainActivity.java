package kangkan.developer.example_of_recycleview_to_json;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity implements Contact_Adapter.OnContactClickListener{

    Contact_Adapter adapter;
    ArrayList<Contact> contacts=new ArrayList<>();

    RecyclerView recyclerView;

    public static final String BASE_URL = "https://untearable-trays.000webhostapp.com";
    get_data service;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialized();

        getData();

    }

    private void getData() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(get_data.class);

        Call<List<Contact>> call =service.getAllData();
        call.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                if (response.code() == 200){

                    contacts= (ArrayList<Contact>) response.body();
                    adapter=new Contact_Adapter(contacts, MainActivity.this);
                    recyclerView.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "No internet connection", Toast.LENGTH_LONG).show();
            }
        });

    }


    private void initialized() {
        recyclerView=findViewById(R.id.recy);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new Contact_Adapter(contacts,this);
        recyclerView.setAdapter(adapter);

    }


    @Override
    public void onContactClick(int position) {


        Contact temp=contacts.get(position);
        position++;
        Toast.makeText(this, "Name"+"\n"+temp.getName()+"\n"+position, Toast.LENGTH_SHORT).show();

    }
}
