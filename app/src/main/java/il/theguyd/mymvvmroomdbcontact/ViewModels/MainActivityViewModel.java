package il.theguyd.mymvvmroomdbcontact.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import il.theguyd.mymvvmroomdbcontact.Model.DB.Instances.ContactsDB;
import il.theguyd.mymvvmroomdbcontact.Model.Repository;

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

public class MainActivityViewModel extends AndroidViewModel {

    private Repository repository;
    private ContactsDB contactsDB;
    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        contactsDB = ContactsDB.getInstance(application);
        this.repository = new Repository(contactsDB.getContactDAO(),application);
    }
}
