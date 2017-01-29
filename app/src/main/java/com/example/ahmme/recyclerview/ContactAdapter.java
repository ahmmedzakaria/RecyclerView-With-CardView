package com.example.ahmme.recyclerview;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ahmme on 1/28/2017.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder>{
    ArrayList<Contact> adapter_list=new ArrayList<>();
    MainActivity mainActivity;
    Context context;

    public ContactAdapter(ArrayList<Contact> adapter_list, Context context) {
        this.adapter_list = adapter_list;
        this.context = context;
        mainActivity=(MainActivity) context;

    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_layout,parent,false);
        ContactViewHolder contactViewHolder=new ContactViewHolder(view,mainActivity);

        return contactViewHolder;
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        holder.img.setImageResource(adapter_list.get(position).getImg_id());
        holder.name.setText(adapter_list.get(position).getName());
        holder.email.setText(adapter_list.get(position).getEmai());
        if(!mainActivity.is_in_action){
            holder.checkBox.setVisibility(View.GONE);
        }else {
            holder.checkBox.setVisibility(View.VISIBLE);
            holder.checkBox.setChecked(false);
        }
    }

    @Override
    public int getItemCount() {
        return adapter_list.size();
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView img;
        TextView name;
        TextView email;
        CheckBox checkBox;
        MainActivity mainActivity;
        CardView cardView;

        public ContactViewHolder(View itemView,MainActivity mainActivity) {
            super(itemView);
            img=(ImageView)itemView.findViewById(R.id.img_id);
            name=(TextView)itemView.findViewById(R.id.name);
            email=(TextView)itemView.findViewById(R.id.email);
            checkBox=(CheckBox) itemView.findViewById(R.id.check_list_item);
            this.mainActivity=mainActivity;
            cardView=(CardView)itemView.findViewById(R.id.cardView);
            cardView.setOnLongClickListener(mainActivity);

            checkBox.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            mainActivity.prepearSelection(v,getAdapterPosition());

        }
    }
    public void updateAdapter(ArrayList<Contact> contactArrayList){
        for(Contact contact: contactArrayList){
            adapter_list.remove(contact);
        }
        notifyDataSetChanged();
    }
}
