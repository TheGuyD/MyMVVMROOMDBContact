package il.theguyd.mymvvmroomdbcontact.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

public class SavedStateViewModel extends ViewModel {

    private static final String NAME_KEY = "name";
    private static final String EMAIL_KEY = "email";

    private SavedStateHandle mState;

    public SavedStateViewModel(SavedStateHandle savedStateHandle) {
        mState = savedStateHandle;
    }

    // Expose an immutable LiveData
    public LiveData<String> getName() {
        // getLiveData obtains an object that is associated with the key wrapped in a LiveData
        // so it can be observed for changes.
        return mState.getLiveData(NAME_KEY);
    }

    public LiveData<String> getEmail() {
        return mState.getLiveData(EMAIL_KEY);
    }

    void saveNewName(String newName) {
        // Sets a new value for the object associated to the key. There's no need to set it
        // as a LiveData.
        mState.set(NAME_KEY, newName);
    }

    void saveNewEmail(String newEmail) {
        mState.set(EMAIL_KEY, newEmail);
    }
}
