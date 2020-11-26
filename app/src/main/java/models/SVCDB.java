package models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.HashMap;

public class SVCDB extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "SVCDB.db";
    //Constants for the User table
    public static final String USER_TABLE_NAME = "user";
    public static final String USER_COLUMN_EMAIL = "email";
    public static final String USER_COLUMN_FULL_NAME = "full_name";
    public static final String USER_COLUMN_PASSWORD = "password";

    //Constants for the VisitCard table
    public static final String VC_TABLE_NAME = "visit_card";
    public static final String VC_COLUMN_ID = "id";
    public static final String VC_COLUMN_OWNER = "owner";
    public static final String VC_COLUMN_EMAIL = "email";
    public static final String VC_COLUMN_FULL_NAME = "full_name";
    public static final String VC_COLUMN_POSITION_TITLE = "position_title";
    public static final String VC_COLUMN_COMPANY = "company";
    public static final String VC_COLUMN_ADDRESS = "address";
    public static final String VC_COLUMN_TELEPHONE = "telephone";
    public static final String VC_COLUMN_FAX = "fax";
    public static final String VC_COLUMN_MOBILE = "mobile";
    public static final String VC_COLUMN_WEBSITE = "website";


    private HashMap hp;
    //only need one instance of the database, make the class a singleton class.


    public SVCDB(Context context){

        super(context, DATABASE_NAME , null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "CREATE TABLE `user` (`email` VARCHAR(255) NOT NULL, `password` VARCHAR(255) NOT NULL, `full_name` VARCHAR(255),  PRIMARY KEY (`email`));"
        );
        db.execSQL(
                "CREATE TABLE `visit_card` (" +
                        "  `id` INT NOT NULL, " +
                        "  `owner` VARCHAR(255), " +
                        "  `email` VARCHAR(255), " +
                        "  `full_name` VARCHAR(255), " +
                        "  `position_title` VARCHAR(255), " +
                        "  `company` VARCHAR(255), " +
                        "  `address` VARCHAR(255), " +
                        "  `telephone` VARCHAR(15), " +
                        "  `fax` VARCHAR(15), " +
                        "  `mobile` VARCHAR(15), " +
                        "  `website` VARCHAR(255), " +
                        "  PRIMARY KEY (`id`), " +
                        "  CONSTRAINT `owner` " +
                        "    FOREIGN KEY (`owner`) " +
                        "    REFERENCES `user` (`email`) " +
                        "    ON DELETE CASCADE " +
                        "    ON UPDATE CASCADE);"
        );
        //for testing only: add dummy user to database
        db.execSQL("INSERT INTO `user` (`email`, `full_name`, `password`) VALUES ('safadimiras@gmail.com', 'Miras Safadi', '123456');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS user");
        db.execSQL("DROP TABLE IF EXISTS visit_card");
        onCreate(db);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public UserDTO getUser(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM user WHERE email = ?";
        Cursor cursor = db.rawQuery(sql, new String[] {email});
        if(cursor.moveToFirst()){
            String userEmail = cursor.getString(cursor.getColumnIndex(USER_COLUMN_EMAIL));
            String password = cursor.getString(cursor.getColumnIndex(USER_COLUMN_PASSWORD));
            String full_name = cursor.getString(cursor.getColumnIndex(USER_COLUMN_FULL_NAME));
            return new UserDTO(userEmail,password,full_name,false);
        }
        return null;
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public VisitCardDTO getVC(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM visit_card WHERE id = ?";
        Cursor cursor = db.rawQuery(sql, new String[] {Integer.toString(id)});
        if(cursor.moveToFirst()){
            String VCid = cursor.getString(cursor.getColumnIndex(VC_COLUMN_ID));
            String owner = cursor.getString(cursor.getColumnIndex(VC_COLUMN_OWNER));
            String full_name = cursor.getString(cursor.getColumnIndex(VC_COLUMN_FULL_NAME));
            return new VisitCardDTO(Integer.parseInt(VCid),owner,full_name,null,  null,  null, null,null);
        }
        return null;
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public boolean addUser(UserDTO user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_COLUMN_EMAIL, user.getEmail());
        contentValues.put(USER_COLUMN_PASSWORD, user.getPassword());
        contentValues.put(USER_COLUMN_FULL_NAME, user.getFull_name());

        long insert_result = db.insert(USER_TABLE_NAME, null, contentValues);
        return insert_result != -1;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public boolean addVC(VisitCardDTO vc){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(VC_COLUMN_ID, vc.getId());
        contentValues.put(VC_COLUMN_OWNER, vc.getOwner());
        contentValues.put(VC_COLUMN_EMAIL, vc.getEmail());
        contentValues.put(VC_COLUMN_FULL_NAME, vc.getFull_name());
        contentValues.put(VC_COLUMN_POSITION_TITLE, vc.getJob());
        contentValues.put(VC_COLUMN_COMPANY, vc.getCompany());
        contentValues.put(VC_COLUMN_ADDRESS, vc.getAddress());
        contentValues.put(VC_COLUMN_MOBILE, vc.getPhone_number());

        long insert_result = db.insert(VC_TABLE_NAME, null, contentValues);
        return insert_result != -1;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public boolean removeUser(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(USER_TABLE_NAME, "email = ? ", new String[] { email}) == 1;
    }










    public boolean insertContact (String name, String phone, String email, String street,String place) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("name", name);
//        contentValues.put("phone", phone);
//        contentValues.put("email", email);
//        contentValues.put("street", street);
//        contentValues.put("place", place);
//        db.insert("contacts", null, contentValues);
        return true;
    }

    public Cursor getData(int id) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor res =  db.rawQuery( "select * from contacts where id="+id+"", null );
//        return res;
        return null;
    }

    public int numberOfRows(){
//        SQLiteDatabase db = this.getReadableDatabase();
//        int numRows = (int) DatabaseUtils.queryNumEntries(db, CONTACTS_TABLE_NAME);
//        return numRows;
        return 0;
    }

    public boolean updateContact (Integer id, String name, String phone, String email, String street,String place) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("name", name);
//        contentValues.put("phone", phone);
//        contentValues.put("email", email);
//        contentValues.put("street", street);
//        contentValues.put("place", place);
//        db.update("contacts", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteContact (Integer id) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        return db.delete("contacts",
//                "id = ? ",
//                new String[] { Integer.toString(id) });
        return null;
    }

    public ArrayList<String> getAllCotacts() {
//        ArrayList<String> array_list = new ArrayList<String>();
//
//        //hp = new HashMap();
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor res =  db.rawQuery( "select * from contacts", null );
//        res.moveToFirst();
//
//        while(res.isAfterLast() == false){
//            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME)));
//            res.moveToNext();
//        }
//        return array_list;
        return null;
    }
}
