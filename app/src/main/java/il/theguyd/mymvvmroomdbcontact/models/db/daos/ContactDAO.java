package il.theguyd.mymvvmroomdbcontact.models.db.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import il.theguyd.mymvvmroomdbcontact.models.db.entities.Contact;

/**
 * DAO - Data Access Object.
 * use for abtraction of SQLite Database Operations.
 * basic Operations- can be annotate as follow : Insert, Delete, Update.
 * query - Query("SELECT ...").
 **/
@Dao
public interface ContactDAO {

    @Insert
    void insert(Contact contact);

    @Delete
    void delete(Contact contact);

    // when using select it is recommend using LiveData to Observe database changes, it allows real time updates when the underline data changes
    @Query("SELECT * FROM contacts")
    LiveData<List<Contact>> getAllContacts();


}
