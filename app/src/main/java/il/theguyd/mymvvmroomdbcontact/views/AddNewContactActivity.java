package il.theguyd.mymvvmroomdbcontact.views;

import android.os.Bundle;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import il.theguyd.mymvvmroomdbcontact.R;
import il.theguyd.mymvvmroomdbcontact.viewmodels.SavedStateViewModel;

public class AddNewContactActivity extends AppCompatActivity {
    private SavedStateViewModel savedStateViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_new_contact);

        // Obtain the ViewModel
        savedStateViewModel = new ViewModelProvider(this).get(SavedStateViewModel.class);

        // Show the ViewModel property's value in a TextView
        savedStateViewModel.getName().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String savedString) {
                ((EditText) findViewById(R.id.edtxtName))
                        .setText(getString(R.string.saved_in_vm, savedString));

            }
        });

        savedStateViewModel.getEmail().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String savedString) {
                ((EditText) findViewById(R.id.edtxtEmail))
                        .setText(getString(R.string.saved_in_vm, savedString));

            }
        });


    }
}