package il.theguyd.mymvvmroomdbcontact.views;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import il.theguyd.mymvvmroomdbcontact.R;
import il.theguyd.mymvvmroomdbcontact.databinding.ActivityAddNewContactBinding;
import il.theguyd.mymvvmroomdbcontact.viewmodels.SavedStateViewModel;

public class AddNewContactActivity extends AppCompatActivity {
    private SavedStateViewModel savedStateViewModel;
    private ActivityAddNewContactBinding activityAddNewContactBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAddNewContactBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_new_contact);

        // Obtain the ViewModel
        savedStateViewModel = new ViewModelProvider(this).get(SavedStateViewModel.class);
        activityAddNewContactBinding.setSaveStateViewModel(savedStateViewModel);

        activityAddNewContactBinding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savedStateViewModel.insertSavedStateDataToDB();
            }
        });


    }
}