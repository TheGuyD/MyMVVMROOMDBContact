package il.theguyd.mymvvmroomdbcontact.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import il.theguyd.mymvvmroomdbcontact.models.db.entities.Contact;
import il.theguyd.mymvvmroomdbcontact.models.Repository;

/**
 * The purpose of the ViewModel is to acquire and keep the information that is necessary for an Activity or a Fragment.
 * It acts as a link between the Model and the View.
 * It's responsible for transforming the data from the Model. It provides data streams to the View.
 * The Activity or the Fragment should be able to observe changes in the ViewModel.
 * ViewModels usually expose this information via LiveData or Android Data Binding.
 */

/*
    If you need to use context inside your Viewmodel
    you should use AndroidViewModel (AVM),
    because it contains the application context.

    AndroidViewModel class is a subclass of ViewModel
    and similar to them, they are designed to store and
    manage UI-related data are responsible to
    prepare & provide data for UI and automatically
    allow data to survive configuration change.
*/

public class ContactsFragmentViewModel extends AndroidViewModel {

    private final Repository repository;
    private LiveData<List<Contact>> allContacts;

    public ContactsFragmentViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository(application);
    }

    public LiveData<List<Contact>> getAllContacts() {
        allContacts = repository.getAllContacts();
        return allContacts;
    }

    public void addContact(Contact contact) {
        repository.addContact(contact);
    }

    public void deleteContact(Contact contact) {
        repository.deleteContact(contact);
    }
}
