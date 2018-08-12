package com.zshan.koqzs.todoapp.Adapter;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zshan.koqzs.todoapp.MainActivity;
import com.zshan.koqzs.todoapp.Model.ToDo;
import com.zshan.koqzs.todoapp.R;

import org.w3c.dom.Text;

import java.util.List;

class  ListItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener
{

    ItemClickListner itemClickListner;
    TextView item_title,item_description;

    public ListItemViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        itemView.setOnCreateContextMenuListener(this);

        item_title=(TextView)itemView.findViewById(R.id.item_title);
        item_description=(TextView)itemView.findViewById(R.id.item_description);
    }

    public void setItemClickListner(ItemClickListner itemClickListner) {
        this.itemClickListner = itemClickListner;
    }

    @Override
    public void onClick(View v) {
        itemClickListner.onClick(v,getAdapterPosition(),false);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("select the action");
        menu.add(0,0,getAdapterPosition(),"DELETE");
    }
}
public class ListItemAdapter extends RecyclerView.Adapter<ListItemViewHolder>{

    MainActivity mainActivity;
    List<ToDo> toDoList;

    public ListItemAdapter(MainActivity mainActivity, List<ToDo> toDoList) {
        this.mainActivity = mainActivity;
        this.toDoList = toDoList;
    }

    @NonNull
    @Override
    public ListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mainActivity.getBaseContext());
        View view =inflater.inflate(R.layout.list_item,parent,false);
        return new ListItemViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ListItemViewHolder holder, int position) {
        //when th euser selects the item data will be set onto editext view



        holder.item_title.setText(toDoList.get(position).getTitle());
        holder.item_description.setText(toDoList.get(position).getDescription());

        holder.setItemClickListner(new ItemClickListner() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                mainActivity.title.setText(toDoList.get(position).getTitle());
                mainActivity.description.setText(toDoList.get(position).getDescription());

                mainActivity.isUpdate=true;  //set the flag to true

                mainActivity.idUpdate=toDoList.get(position).getId();
            }
        });
    }

    @Override
    public int getItemCount() {
        return toDoList.size();
    }
}
