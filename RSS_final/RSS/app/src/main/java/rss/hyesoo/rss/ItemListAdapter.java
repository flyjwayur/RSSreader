package rss.hyesoo.rss;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;



public class ItemListAdapter extends ArrayAdapter<Item> {
    private ArrayList<Item> items;
    public ItemListAdapter(Context context, int resource, ArrayList<Item> items) {
        super(context, resource, items);
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Make sure we have a view to work with(may have been given null)
        View itemView = convertView;
        if(itemView == null){
            LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            itemView = li.inflate(R.layout.item_row, parent,false);
        }
        //find the item of feed
        Item thisItem = items.get(position);
        //fill the view
        if(thisItem !=null){
            TextView itemTitle = (TextView) itemView.findViewById(R.id.itemTitle);
            itemTitle.setText(thisItem.getTitle());
            TextView itemDesc = (TextView) itemView.findViewById(R.id.itemDesc);
            itemDesc.setText(thisItem.getDescription());
            TextView itemLink = (TextView) itemView.findViewById(R.id.itemLink);
            itemLink.setText(thisItem.getLink());
        }
        return itemView;
    }
}
