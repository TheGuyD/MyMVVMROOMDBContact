package il.theguyd.mymvvmroomdbcontact.views;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;

import il.theguyd.mymvvmroomdbcontact.R;
import il.theguyd.mymvvmroomdbcontact.databinding.ActivityMainBinding;
import il.theguyd.mymvvmroomdbcontact.views.handlers.MainActivityClickHandlers;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding activityMainBinding;
    private MainActivityClickHandlers mainActivityClickHandlers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mainActivityClickHandlers = new MainActivityClickHandlers(this);

        activityMainBinding.setMainActivityClickHandlers(mainActivityClickHandlers);

        //inflate Fragment

        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.findFragmentByTag(ContactsFragment.newInstance().getTag()) == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true).addToBackStack(ContactsFragment.newInstance().getTag())
                    .add(R.id.contactsFragment1, new ContactsFragment(), ContactsFragment.newInstance().getTag())
                    .addToBackStack(ContactsFragment.newInstance().getTag())
                    .commit();

        }

    }
}