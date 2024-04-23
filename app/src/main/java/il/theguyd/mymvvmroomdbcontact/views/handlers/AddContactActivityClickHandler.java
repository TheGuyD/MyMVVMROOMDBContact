package il.theguyd.mymvvmroomdbcontact.views.handlers;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

public class AddContactActivityClickHandler {
    private Context context;

    public AddContactActivityClickHandler(Context context) {
        this.context = context;
    }

    public void onSaveContactClickListener(View view) {
        Toast.makeText(view.getContext(), "save contact button clicked", Toast.LENGTH_SHORT).show();
    }
}
