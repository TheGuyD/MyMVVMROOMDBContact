package il.theguyd.mymvvmroomdbcontact.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import il.theguyd.mymvvmroomdbcontact.R;
import il.theguyd.mymvvmroomdbcontact.databinding.FragmentContactsBinding;
import il.theguyd.mymvvmroomdbcontact.models.db.entities.Contact;
import il.theguyd.mymvvmroomdbcontact.viewmodels.ContactsFragmentViewModel;
import il.theguyd.mymvvmroomdbcontact.views.adapters.ContactAdapter;

public class ContactsFragment extends Fragment {

    // Data Source
    private ArrayList<Contact> contactArrayList;

    // Adapter
    private ContactAdapter contactAdapter;
    private RecyclerView rcContacts;

    //Data Binding
    private FragmentContactsBinding fragmentContactsBinding;

    // ViewModel
    private ContactsFragmentViewModel contactsFragmentViewModel;

    public ContactsFragment() {
    }

    public static ContactsFragment newInstance() {
        ContactsFragment fragment = new ContactsFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentContactsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_contacts, container, false);

        // Important for LiveData to update the UI automatically
        fragmentContactsBinding.setLifecycleOwner(this);

        // RecyclerView obtain using data binding
        rcContacts = fragmentContactsBinding.rcContacts;
        rcContacts.setLayoutManager(new LinearLayoutManager(this.getContext()));
        rcContacts.setHasFixedSize(true);

        // Adapter
        contactAdapter = new ContactAdapter(contactArrayList);

        // Linking the RecyclerView with the Adapter
        rcContacts.setAdapter(contactAdapter);

        // Activity-scoped ViewModel
        contactsFragmentViewModel = new ViewModelProvider(requireActivity()).get(ContactsFragmentViewModel.class);

        // Loading the data in room DB
        contactsFragmentViewModel.getAllContacts().observe(this.getViewLifecycleOwner(), new Observer<List<Contact>>() {
            @Override
            public void onChanged(List<Contact> contacts) {

                contactAdapter.setContactArrayList((ArrayList<Contact>) contacts);
            }

        });

        return fragmentContactsBinding.getRoot();
    }


}
