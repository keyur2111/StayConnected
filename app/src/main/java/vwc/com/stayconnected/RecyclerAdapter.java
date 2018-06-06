package vwc.com.stayconnected;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {

    private ArrayList<String> names = new ArrayList<>(), Numbers = new ArrayList<>();

    RecyclerAdapter(ArrayList<String> Names, ArrayList<String> Contact_Nos) {

        names = Names;
        Numbers = Contact_Nos;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_item_layout, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

        String name = names.get(position);

        if(name.length() > 22)
        {
            name = name.substring(0, 20);
            name = name + "...";
            holder.textView.setText(name);
        }
        else
        {
            holder.textView.setText(names.get(position));
        }

        holder.textView2.setText(Numbers.get(position));

    }

    public interface BubbleTextGetter
    {
        String getTextToShowInBubble(int pos);
    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder{

        private TextView textView, textView2;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.contact_name);
            textView2 = (TextView) itemView.findViewById(R.id.contact_number);
            itemView.findViewById(R.id.card_view);
            itemView.findViewById(R.id.call_contact);
        }
    }
}
