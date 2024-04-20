package il.theguyd.mymvvmroomdbcontact.Model;


import android.app.Application;
import android.os.Looper;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import android.os.Handler;

import androidx.lifecycle.LiveData;

import il.theguyd.mymvvmroomdbcontact.Model.DB.Daos.ContactDAO;
import il.theguyd.mymvvmroomdbcontact.Model.DB.Entities.Contact;
import il.theguyd.mymvvmroomdbcontact.Model.DB.Instances.ContactsDB;


/**
 * Repository is a class that is mainly used to manage multiple sources of data.
 * The repository class isolates the data sources from the rest of the app and
 * provides a clean API for data access to the rest of the app.
 * different REST APIs,cache, local database storage) and it provides this data to the rest of the app.
 **/
public class Repository {
    private ContactsDB contactsDB;
    private final ContactDAO contactDAO;
    private ExecutorService executorService;
    private Handler handler;


    public Repository(ContactDAO contactDAO, Application application) {
        contactsDB = ContactsDB.getInstance(application);
        this.contactDAO = contactsDB.getContactDAO();

        /**
         * thread separation - background database operations are separated from UI preventing from UI Blocking and ANR (Application Not Responding).
         * synchronization - concurrent access to the database is managed by executorService assuring database operations occurring sequentially and avoiding race condition
         * responsive UI  - updates occur on the main thread
         * **/

        //execute the db operation in the background and sequentially using newSingleThreadExecutor
        executorService = Executors.newSingleThreadExecutor();

        //update the UI
        handler = new Handler(Looper.getMainLooper());
    }

    //Methods in DAO being executed from Repository
    public void addContact(Contact contact) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                contactDAO.insert(contact);
            }
        });

    }

    public void deleteContact(Contact contact) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                contactDAO.delete(contact);
            }
        });
    }


    //problem: Runnable return Void hence we use LiveData
    public LiveData<List<Contact>> getAllContacts() {
        return contactDAO.getAllContacts();
    }


}

