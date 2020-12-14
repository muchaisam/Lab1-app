package com.example.storage;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import android.database.Cursor;
public class mydbhandler extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "MY_DATABASE";
    // User table name
    private static final String TABLE_NAME = "MY_TABLE";
    // User Table Columns names
    private static final String EMP_ID = "user_id";
    private static final String EMP_NAME = "user_name";
    private static final String EMP_EMAIL = "user_email";
    private static final String EMP_PASSWORD = "user_pass";
    private final Context context;
    private String CREATE_TABLE= "CREATE TABLE " + TABLE_NAME + "(" + EMP_ID + "TEXT," + EMP_NAME + " TEXT," + EMP_EMAIL + " TEXT," + EMP_PASSWORD + " TEXT " + ")";
    private String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    public mydbhandler(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        this.context=context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }
    public void addemp(String empid, String name, String email, String pass)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(mydbhandler.EMP_ID,empid);
        values.put(mydbhandler.EMP_NAME,name);
        values.put(mydbhandler.EMP_EMAIL,email);
        values.put(mydbhandler.EMP_PASSWORD,pass);
        long status=db.insert(TABLE_NAME,null,values);
        if(status<=0){
            Toast.makeText(context, "Insertion Unsuccessful",
                    Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Insertion Successful",
                    Toast.LENGTH_SHORT).show();
        }
        db.close();
    }
    public void deleteUser(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
// delete user record by id
        long s=db.delete(TABLE_NAME, EMP_ID + " = ?",
                new String[]{String.valueOf(id)});
        if(s<=0){
            Toast.makeText(context, "Deletion Unsuccessful",
                    Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Deletion Successful", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }
    public String load()
    {
        String result = "";
        String query = "Select*FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            int result_0 = cursor.getInt(0);
            String result_1 = cursor.getString(1);
            String result_2 = cursor.getString(2);
            String result_3 = cursor.getString(3);
            result += String.valueOf(result_0) + " " + result_1 + " " + result_2 + " "
                    + result_3 + "\n";
            System.getProperty("line.separator");
        }
        cursor.close();
        db.close();
        return result;
    }
}