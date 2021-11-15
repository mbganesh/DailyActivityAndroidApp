package mb.ganesh.dailystaff;

// define table name

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "myTable")
public class MainData implements Serializable {

    // create id column
    @PrimaryKey(autoGenerate = true)
    private int id;

//    Create custom columns
    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "cigCount")
    private int cigCount;

    @ColumnInfo(name = "cooCount")
    private int cooCount;

//    Add getter , setter


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCigCount() {
        return cigCount;
    }

    public void setCigCount(int cigCount) {
        this.cigCount = cigCount;
    }

    public int getCooCount() {
        return cooCount;
    }

    public void setCooCount(int cooCount) {
        this.cooCount = cooCount;
    }
}
