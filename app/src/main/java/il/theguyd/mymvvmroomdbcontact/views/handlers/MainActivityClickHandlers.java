package il.theguyd.mymvvmroomdbcontact.views.handlers;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import il.theguyd.mymvvmroomdbcontact.views.AddNewContactActivity;

public class MainActivityClickHandlers {
    Context context;

    public MainActivityClickHandlers(Context context) {
        this.context = context;
    }

    public void onFABClickHandler(View view) {
        Intent i = new Intent(view.getContext(), AddNewContactActivity.class);
        this.context.startActivity(i);
    }
}
