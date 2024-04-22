package il.theguyd.mymvvmroomdbcontact.models.db.instances;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import il.theguyd.mymvvmroomdbcontact.models.db.daos.ContactDAO;
import il.theguyd.mymvvmroomdbcontact.models.db.entities.Contact;

/**
 * Serves as the database holder class, utilizing the Singleton pattern to prevent
 * the creation of multiple instances and to optimize resource usage across the app's lifecycle.
 * The Database Annotation associates this database holder class with its corresponding
 * entities, ensuring a coherent data model within the application.
 **/

@Database(entities = {Contact.class}, version = 1)
public abstract class ContactsDB extends RoomDatabase {

    //link the ContactDAO
    public abstract ContactDAO getContactDAO();

    //Singleton Pattern
    private static ContactsDB dbInstance;

    //use synchronized for thread safety
    public static synchronized ContactsDB getInstance(Context context) {
        if (dbInstance == null) {
            dbInstance = Room.databaseBuilder(context.getApplicationContext(), ContactsDB.class, "contacts_db")

                    //useful during development environment, on production develop more sophisticated fallback strategy.
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return dbInstance;
    }


}
