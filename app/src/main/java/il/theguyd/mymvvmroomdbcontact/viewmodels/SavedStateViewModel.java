package il.theguyd.mymvvmroomdbcontact.viewmodels;

import static il.theguyd.mymvvmroomdbcontact.utils.Constants.EMAIL_KEY;
import static il.theguyd.mymvvmroomdbcontact.utils.Constants.NAME_KEY;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;

import il.theguyd.mymvvmroomdbcontact.models.Repository;
import il.theguyd.mymvvmroomdbcontact.models.db.entities.Contact;

/**
 * <p><b>ViewModel</b> is designed to store and manage UI-related data in a lifecycle-conscious way. It survives configuration changes such as screen rotations,
 * meaning that data you hold inside the ViewModel will not be destroyed and re-created during these events.</p>
 *
 * <p><b>Limitation: The main limitation of a regular ViewModel</b> is that it does not survive the process death.
 * If the Android system needs to reclaim resources and terminates the app process, when the app is brought back to the foreground (incoming phone call),
 * the ViewModel will be re-initialized, losing all its previous state.
 * This scenario typically occurs when the user navigates away from the app and returns to it after some time, especially if the device is under memory pressure.</p>
 *
 * <p><b>Surviving Process Death:</b> Unlike a regular ViewModel, when using SavedStateHandle,
 * the data persists not only through configuration changes but also through process death.
 * The data within SavedStateHandle is saved to a Bundle that is managed by the Android framework,
 * making it more robust against temporary process termination.</p>
 * {@link}  <a href="https://medium.com/androiddevelopers/viewmodels-persistence-onsaveinstancestate-restoring-ui-state-and-loaders-fc7cc4a6c090">explanation</a>
 * {@link } <a href="https://github.com/android/codelab-android-lifecycles/blob/master/app/src/main/java/com/example/android/lifecycles/step6_solution/SavedStateViewModel.java">example 1</a>
 * {@link } <a href="https://developer.android.com/codelabs/android-lifecycles#6">example 2</a>
 ***/

public class SavedStateViewModel extends AndroidViewModel {

    private final Repository repository;
    private final SavedStateHandle mState;

    public SavedStateViewModel(@NonNull Application application, SavedStateHandle mState) {
        super(application);
        this.mState = mState;
        this.repository = new Repository(application);
    }


    public MutableLiveData<String> getName() {
        return mState.getLiveData(NAME_KEY);
    }


    public MutableLiveData<String> getEmail() {
        return mState.getLiveData(EMAIL_KEY);
    }

    public void insertSavedStateDataToDB() {
        Contact contact = new Contact();
        contact.setName(this.mState.get(NAME_KEY));
        contact.setEmail(this.mState.get(EMAIL_KEY));
        repository.addContact(contact);
    }


}
