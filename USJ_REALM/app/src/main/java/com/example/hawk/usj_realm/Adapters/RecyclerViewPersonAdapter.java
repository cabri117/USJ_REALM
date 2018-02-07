
package com.example.hawk.usj_realm.Adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hawk.usj_realm.Models.Person;
import com.example.hawk.usj_realm.Models.Pet;
import com.example.hawk.usj_realm.R;

import java.util.ArrayList;
import io.realm.RealmList;

public class RecyclerViewPersonAdapter extends RecyclerView.Adapter<RecyclerViewPersonAdapter.PersonHolder> {

    static String LOG_TAG = "RecyclerViewPersonAdapter";
    ArrayList<Person> mDataset;
    static MyClickListener myClickListener;

    public static class PersonHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        TextView age;
        TextView pets;

        public PersonHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tvname);
            age = (TextView) itemView.findViewById(R.id.tvage);
            pets = (TextView) itemView.findViewById(R.id.tvpets);

            Log.i(LOG_TAG, "Adding Listener");
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public RecyclerViewPersonAdapter(ArrayList<Person> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public PersonHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_row, parent, false);

        PersonHolder personHolder = new PersonHolder(view);
        return personHolder;
    }

    @Override
    public void onBindViewHolder(PersonHolder holder, int position) {
        holder.name.setText(mDataset.get(position).getName());
        holder.age.setText("Edad: " + mDataset.get(position).getAge());

        String petsName= "Mascotas:";
        RealmList<Pet> pets= mDataset.get(position).getPets();
        for(int i=0; i<pets.size(); i++){
            petsName= petsName + " - " + pets.get(i).getName();

        }

        holder.pets.setText(petsName);
    }

    public void addItem(Person dataObj, int index) {
        mDataset.add(index, dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
}
