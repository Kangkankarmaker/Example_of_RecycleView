package kangkan.developer.example_of_recycleview_to_json;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static kangkan.developer.example_of_recycleview_to_json.MainActivity.BASE_URL;

public class Contact_Adapter extends RecyclerView.Adapter<Contact_Adapter.viewHolder> {

    ArrayList<Contact> mycontect;
    OnContactClickListener onContactClickListener;
    public static final String BASE_URL = "https://untearable-trays.000webhostapp.com";

    public Contact_Adapter(ArrayList<Contact> mycontect,OnContactClickListener onContactClickListener) {
        this.mycontect = mycontect;
        this.onContactClickListener=onContactClickListener;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.contact_item,viewGroup,false);

        return new viewHolder(view,onContactClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder viewHolder, int position) {

        Contact current=mycontect.get(position);

        viewHolder.nameText.setText(current.getName());
        viewHolder.numberText.setText(current.getPhone());

        String photoString;
        photoString = current.getEmail();
        Uri photoUri = Uri.parse(BASE_URL+"/image/"+photoString);
        Picasso.get().load(photoUri).into(viewHolder.imageView);
    }


    @Override
    public int getItemCount() {
       if (mycontect.isEmpty())
       {
           return 0;
       }
       else {
           return mycontect.size();
       }
    }


    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public  TextView nameText,numberText;
        public ImageView imageView;
        OnContactClickListener onContactClickListener;

        public viewHolder(@NonNull View itemView , OnContactClickListener onContactClickListener) {
            super(itemView);

            nameText=itemView.findViewById(R.id.txt_name);
            numberText=itemView.findViewById(R.id.txt_phone);
            imageView=itemView.findViewById(R.id.img_view);

            this.onContactClickListener=onContactClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onContactClickListener.onContactClick(getAdapterPosition());

        }
    }

    public interface OnContactClickListener{
         void onContactClick(int position);
    }
}
