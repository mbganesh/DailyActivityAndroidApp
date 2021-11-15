package mb.ganesh.dailystaff;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MainDao {

//    Insert Query
    @Insert(onConflict = REPLACE)
    void insert(MainData mainData);


//    Delete Query
    @Delete
    void delete(MainData mainData);


//    Get All data query
    @Query("SELECT * FROM  myTable")
    List<MainData> getAll();
}
