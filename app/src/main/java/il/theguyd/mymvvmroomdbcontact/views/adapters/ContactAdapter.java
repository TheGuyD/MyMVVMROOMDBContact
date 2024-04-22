package il.theguyd.mymvvmroomdbcontact.views.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import il.theguyd.mymvvmroomdbcontact.databinding.ContactListItemBinding;
import il.theguyd.mymvvmroomdbcontact.models.db.entities.Contact;
import il.theguyd.mymvvmroomdbcontact.R;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    private ArrayList<Contact> contactArrayList;

    public ContactAdapter(ArrayList<Contact> contactArrayList) {
        this.contactArrayList = contactArrayList;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //creating new View holders for items in recyclerView
        ContactListItemBinding contactListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.contact_list_item,
                parent,
                false);
        return new ContactViewHolder(contactListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {

        // Called by recyclerView when it needs to display or update an item
        // at a specific position in the list or grid
        Contact contact = contactArrayList.get(position);
        holder.contactListItemBinding.setContact(contact);
    }

    @Override
    public int getItemCount() {
        int size = 0;
        if (contactArrayList != null) {
            size = contactArrayList.size();
        }
        return size;
    }

    public void setContactArrayList(ArrayList<Contact> contactArrayList) {
        this.contactArrayList = contactArrayList;
        notifyDataSetChanged();
    }

    class ContactViewHolder extends RecyclerView.ViewHolder {
        private final ContactListItemBinding contactListItemBinding;

        public ContactViewHolder(@NonNull ContactListItemBinding contactListItemBinding) {
            super(contactListItemBinding.getRoot());
            this.contactListItemBinding = contactListItemBinding;
        }
    }
}
