package models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;

public class SVCDB extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 2;
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
    public static final String VC_COLUMN_PREFIX = "prefix";
    public static final String VC_COLUMN_FIRST_NAME = "first_name";
    public static final String VC_COLUMN_MIDDLE_NAME = "middle_name";
    public static final String VC_COLUMN_LAST_NAME = "last_name";
    public static final String VC_COLUMN_POSITION_TITLE = "position_title";
    public static final String VC_COLUMN_COMPANY = "company";
    public static final String VC_COLUMN_ADDRESS = "address";
    public static final String VC_COLUMN_TELEPHONE = "telephone";
    public static final String VC_COLUMN_FAX = "fax";
    public static final String VC_COLUMN_MOBILE = "mobile";
    public static final String VC_COLUMN_WEBSITE = "website";





    public SVCDB(Context context){
        super(context, DATABASE_NAME , null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "CREATE TABLE `user` (`email` VARCHAR(255) PRIMARY KEY, `password` VARCHAR(255) NOT NULL, `full_name` VARCHAR(255));"
        );
        db.execSQL(
                "CREATE TABLE `visit_card` (" +
                        "  `id` INTEGER  PRIMARY KEY AUTOINCREMENT, " +
                        "  `owner` VARCHAR(255), " +
                        "  `email` VARCHAR(255) UNIQUE, " +
                        "  `prefix` VARCHAR(255), " +
                        "  `first_name` VARCHAR(255) UNIQUE, " +
                        "  `middle_name` VARCHAR(255), " +
                        "  `last_name` VARCHAR(255) UNIQUE, " +
                        "  `position_title` VARCHAR(255) , " +
                        "  `company` VARCHAR(255), " +
                        "  `address` VARCHAR(255), " +
                        "  `telephone` VARCHAR(15), " +
                        "  `fax` VARCHAR(15), " +
                        "  `mobile` VARCHAR(15), " +
                        "  `website` VARCHAR(255), " +
                        "  CONSTRAINT `owner` " +
                        "    FOREIGN KEY (`owner`) " +
                        "    REFERENCES `user` (`email`) " +
                        "    ON DELETE CASCADE " +
                        "    ON UPDATE CASCADE);"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS user");
        db.execSQL("DROP TABLE IF EXISTS visit_card");
        onCreate(db);
    }

    //user related methods
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public UserDTO getUser(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM user WHERE email = ?";
        Cursor cursor = db.rawQuery(sql, new String[] {email});
        if(cursor.moveToFirst()){
            String userEmail = cursor.getString(cursor.getColumnIndex(USER_COLUMN_EMAIL));
            String password = cursor.getString(cursor.getColumnIndex(USER_COLUMN_PASSWORD));
            String full_name = cursor.getString(cursor.getColumnIndex(USER_COLUMN_FULL_NAME));
            return new UserDTO.Builder()
                    .setEmail(userEmail)
                    .setPassword(password,false)
                    .setFull_name(full_name)
                    .build();
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
    public boolean removeUser(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(USER_TABLE_NAME, "email = ? ", new String[] { email}) == 1;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public boolean editPassword(UserDTO user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_COLUMN_PASSWORD, user.getPassword());


        long update_result= db.update(USER_TABLE_NAME, contentValues,"email = ? ", new String[] {user.getEmail()});
        return update_result != -1;
    }

    //Visit card related methods
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public VisitCardDTO getVC(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM visit_card WHERE id = ?";
        Cursor cursor = db.rawQuery(sql, new String[] { Integer.toString(id) });
        if(cursor.moveToFirst()){
            int vc_id = cursor.getInt(cursor.getColumnIndex(VC_COLUMN_ID));
            String owner = cursor.getString(cursor.getColumnIndex(VC_COLUMN_OWNER));
            String email = cursor.getString(cursor.getColumnIndex(VC_COLUMN_EMAIL));
            String prefix = cursor.getString(cursor.getColumnIndex(VC_COLUMN_PREFIX));
            String first_name = cursor.getString(cursor.getColumnIndex(VC_COLUMN_FIRST_NAME));
            String middle_name = cursor.getString(cursor.getColumnIndex(VC_COLUMN_MIDDLE_NAME));
            String last_name = cursor.getString(cursor.getColumnIndex(VC_COLUMN_LAST_NAME));
            String position_title = cursor.getString(cursor.getColumnIndex(VC_COLUMN_POSITION_TITLE));
            String company = cursor.getString(cursor.getColumnIndex(VC_COLUMN_COMPANY));
            String address = cursor.getString(cursor.getColumnIndex(VC_COLUMN_ADDRESS));
            String telephone = cursor.getString(cursor.getColumnIndex(VC_COLUMN_TELEPHONE));
            String fax = cursor.getString(cursor.getColumnIndex(VC_COLUMN_FAX));
            String mobile = cursor.getString(cursor.getColumnIndex(VC_COLUMN_MOBILE));
            String website = cursor.getString(cursor.getColumnIndex(VC_COLUMN_WEBSITE));

            return new VisitCardDTO.Builder().
                    setId(vc_id).
                    setOwner(owner).
                    setEmail(email).
                    setPrefix(prefix).
                    setFirst_name(first_name).
                    setMiddle_name(middle_name).
                    setLast_name(last_name).
                    setPosition_title(position_title).
                    setCompany(company).
                    setAddress(address).
                    setTelephone(telephone).
                    setFax(fax).
                    setMobile(mobile).
                    setWebsite(website).
                    build();
        }
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public boolean addVC(VisitCardDTO vc){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(VC_COLUMN_OWNER, vc.getOwner());
        contentValues.put(VC_COLUMN_EMAIL, vc.getEmail());
        contentValues.put(VC_COLUMN_PREFIX, vc.getPrefix());
        contentValues.put(VC_COLUMN_FIRST_NAME, vc.getFirst_name());
        contentValues.put(VC_COLUMN_MIDDLE_NAME, vc.getMiddle_name());
        contentValues.put(VC_COLUMN_LAST_NAME, vc.getLast_name());
        contentValues.put(VC_COLUMN_POSITION_TITLE, vc.getPosition_title());
        contentValues.put(VC_COLUMN_COMPANY, vc.getCompany());
        contentValues.put(VC_COLUMN_ADDRESS, vc.getAddress());
        contentValues.put(VC_COLUMN_TELEPHONE, vc.getTelephone());
        contentValues.put(VC_COLUMN_FAX, vc.getFax());
        contentValues.put(VC_COLUMN_MOBILE, vc.getMobile());
        contentValues.put(VC_COLUMN_WEBSITE, vc.getWebsite());
        System.out.println(vc.getPosition_title());
        long insert_result = db.insert(VC_TABLE_NAME, null, contentValues);
        System.out.println("result"+ insert_result);
        return insert_result != -1;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public boolean editVC(VisitCardDTO vc){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(VC_COLUMN_OWNER, vc.getOwner());
        contentValues.put(VC_COLUMN_EMAIL, vc.getEmail());
        contentValues.put(VC_COLUMN_PREFIX, vc.getPrefix());
        contentValues.put(VC_COLUMN_FIRST_NAME, vc.getFirst_name());
        contentValues.put(VC_COLUMN_MIDDLE_NAME, vc.getMiddle_name());
        contentValues.put(VC_COLUMN_LAST_NAME, vc.getLast_name());
        contentValues.put(VC_COLUMN_POSITION_TITLE, vc.getPosition_title());
        contentValues.put(VC_COLUMN_COMPANY, vc.getCompany());
        contentValues.put(VC_COLUMN_ADDRESS, vc.getAddress());
        contentValues.put(VC_COLUMN_TELEPHONE, vc.getTelephone());
        contentValues.put(VC_COLUMN_FAX, vc.getFax());
        contentValues.put(VC_COLUMN_MOBILE, vc.getMobile());
        contentValues.put(VC_COLUMN_WEBSITE, vc.getWebsite());

        long update_result= db.update(VC_TABLE_NAME, contentValues,"owner = ? ", new String[] {vc.getOwner()});
        return update_result != -1;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public boolean deleteVC(String email, String first_name, String last_name){
        SQLiteDatabase db = this.getWritableDatabase();
        long delete_result= db.delete(VC_TABLE_NAME,
                VC_COLUMN_EMAIL + " = ? AND " + VC_COLUMN_FIRST_NAME + " = ? AND " + VC_COLUMN_LAST_NAME + " = ?",
                new String[] { email,first_name,last_name });
        return delete_result != -1;
    }



    public ArrayList<VisitCardDTO> getUserVisitCards(String userEmail){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM visit_card WHERE owner = ?";
        Cursor cursor = db.rawQuery(sql, new String[] { userEmail });
        ArrayList<VisitCardDTO> visitCards = new ArrayList<>();
        cursor.moveToFirst();

        while(cursor.isAfterLast() == false){
            int id = cursor.getInt(cursor.getColumnIndex(VC_COLUMN_ID));
            System.out.println(id);
            String email = cursor.getString(cursor.getColumnIndex(VC_COLUMN_EMAIL));
            String prefix = cursor.getString(cursor.getColumnIndex(VC_COLUMN_PREFIX));
            String first_name = cursor.getString(cursor.getColumnIndex(VC_COLUMN_FIRST_NAME));
            String middle_name = cursor.getString(cursor.getColumnIndex(VC_COLUMN_MIDDLE_NAME));
            String last_name = cursor.getString(cursor.getColumnIndex(VC_COLUMN_LAST_NAME));
            String position_title = cursor.getString(cursor.getColumnIndex(VC_COLUMN_POSITION_TITLE));
            String company = cursor.getString(cursor.getColumnIndex(VC_COLUMN_COMPANY));
            String address = cursor.getString(cursor.getColumnIndex(VC_COLUMN_ADDRESS));
            String telephone = cursor.getString(cursor.getColumnIndex(VC_COLUMN_TELEPHONE));
            String fax = cursor.getString(cursor.getColumnIndex(VC_COLUMN_FAX));
            String mobile = cursor.getString(cursor.getColumnIndex(VC_COLUMN_MOBILE));
            String website = cursor.getString(cursor.getColumnIndex(VC_COLUMN_WEBSITE));


            visitCards.add(new VisitCardDTO.Builder().
                                            setId(id).
                                            setOwner(userEmail).
                                            setEmail(email).
                                            setPrefix(prefix).
                                            setFirst_name(first_name).
                                            setMiddle_name(middle_name).
                                            setLast_name(last_name).
                                            setPosition_title(position_title).
                                            setCompany(company).
                                            setAddress(address).
                                            setTelephone(telephone).
                                            setFax(fax).
                                            setMobile(mobile).
                                            setWebsite(website).
                                            build()
            );
            cursor.moveToNext();
        }
        return visitCards;

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
