package com.example.firebasegetandadddata.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebasegetandadddata.R;
import com.example.firebasegetandadddata.User;

import java.util.ArrayList;

public class MyAdapter extends  RecyclerView.Adapter<MyAdapter.MyViewHolder> {


        Context context;
        ArrayList<User> list;

public MyAdapter(Context context,ArrayList<User> list){
        this.context=context;
        this.list=list;
        }

@NonNull
@Override
public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View v= LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new MyViewHolder(v);
        }

@Override
public void onBindViewHolder(@NonNull MyViewHolder holder,int position){

        User user=list.get(position);
        holder.firstName.setText(user.getFirstName());
        holder.lastName.setText(user.getLastName());

        }

@Override
public int getItemCount(){
        return list.size();
        }

public static class MyViewHolder extends RecyclerView.ViewHolder {

    TextView firstName, lastName;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        firstName = itemView.findViewById(R.id.textViewNameInput);
        lastName = itemView.findViewById(R.id.textViewSurnameInput);
    }
}
}
