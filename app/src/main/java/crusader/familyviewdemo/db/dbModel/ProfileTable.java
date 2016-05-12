package crusader.familyviewdemo.db.dbModel;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import crusader.familyviewdemo.db.ColumnValuePair;

/**
 * Created by linus on 11/5/16.
 */
public class ProfileTable extends BaseTable<ProfileTable> {

    public static final String PROFILE_TABLE_NAME = "Profile";
    public static final String PROFILE_COLUMN_ID = "profileId";
    public static final String PROFILE_COLUMN_USERNAME = "name";
    public static final String PROFILE_COLUMN_PROFILE_URL = "profileUrl";
    public static final String PROFILE_COLUMN_PROFILE_PARENT_ID = "parentId";
    public static final String PROFILE_COLUMN_PROFILE_DESCRIPTION = "description";
    public static final String PROFILE_COLUMN_PROFILE_HAS_BRO_SIS = "bro_sis";

    
    public ProfileTable() {
    }

    int profileId;
    String name;
    String imgProfileUrl;
    int parentProfileId;
    String description;
    boolean hasBroSis;
    int lvlTier;


    public ProfileTable(int profileId, String name, String imgProfileUrl, int parentProfileId, String description) {
        this.profileId = profileId;
        this.name = name;
        this.imgProfileUrl = imgProfileUrl;
        this.parentProfileId = parentProfileId;
        this.description = description;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgProfileUrl() {
        return imgProfileUrl;
    }

    public void setImgProfileUrl(String imgProfileUrl) {
        this.imgProfileUrl = imgProfileUrl;
    }

   

    public int getParentProfileId() {
        return parentProfileId;
    }

    public void setParentProfileId(int parentProfileId) {
        this.parentProfileId = parentProfileId;
    }

    public boolean isHasBroSis() {
        return hasBroSis;
    }

    public void setHasBroSis(boolean hasBroSis) {
        this.hasBroSis = hasBroSis;
    }
    

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLvlTier() {
        return lvlTier;
    }

    public void setLvlTier(int lvlTier) {
        this.lvlTier = lvlTier;
    }

    @Override
    public void createTable(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE IF NOT EXISTS " + PROFILE_TABLE_NAME +
                        " (" + PROFILE_COLUMN_ID + " integer primary key, " + PROFILE_COLUMN_USERNAME  + " text, " + PROFILE_COLUMN_PROFILE_URL+ " text, " + PROFILE_COLUMN_PROFILE_PARENT_ID+ " text, " + PROFILE_COLUMN_PROFILE_DESCRIPTION + " text, " + PROFILE_COLUMN_PROFILE_HAS_BRO_SIS + " text)"
        );
    }

    @Override
    public String getTableName() {
        return PROFILE_TABLE_NAME;
    }

    @Override
    public String[] getColumnNames() {
        return new String[]{PROFILE_COLUMN_ID, PROFILE_COLUMN_USERNAME, PROFILE_COLUMN_PROFILE_URL, PROFILE_COLUMN_PROFILE_PARENT_ID, PROFILE_COLUMN_PROFILE_DESCRIPTION, PROFILE_COLUMN_PROFILE_HAS_BRO_SIS};
    }

    @Override
    public boolean insertInDb(SQLiteDatabase db, ProfileTable tableModel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(PROFILE_COLUMN_ID, tableModel.getProfileId());
        contentValues.put(PROFILE_COLUMN_USERNAME, tableModel.getName());
        contentValues.put(PROFILE_COLUMN_PROFILE_URL, tableModel.getImgProfileUrl());
        contentValues.put(PROFILE_COLUMN_PROFILE_PARENT_ID, tableModel.getParentProfileId());
        contentValues.put(PROFILE_COLUMN_PROFILE_DESCRIPTION, tableModel.getDescription());
        contentValues.put(PROFILE_COLUMN_PROFILE_HAS_BRO_SIS, tableModel.isHasBroSis() ? "1" : "0");
        long count = db.insert(getTableName(), null, contentValues);
        return count > 0;
    }

    @Override
    public ArrayList<ProfileTable> getAllData(SQLiteDatabase db) {
        ArrayList<ProfileTable> array_list = new ArrayList<>();

        Cursor cursor = db.rawQuery("select * from " + getTableName(), null);
        try {
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                ProfileTable userPROFILE = new ProfileTable();
                userPROFILE.setProfileId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(PROFILE_COLUMN_ID))));
                userPROFILE.setName(cursor.getString(cursor.getColumnIndex(PROFILE_COLUMN_USERNAME)));
                userPROFILE.setImgProfileUrl(cursor.getString(cursor.getColumnIndex(PROFILE_COLUMN_PROFILE_URL)));
                userPROFILE.setParentProfileId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(PROFILE_COLUMN_PROFILE_PARENT_ID))));
                userPROFILE.setDescription(cursor.getString(cursor.getColumnIndex(PROFILE_COLUMN_PROFILE_DESCRIPTION)));
                userPROFILE.setHasBroSis(cursor.getString(cursor.getColumnIndex(PROFILE_COLUMN_PROFILE_HAS_BRO_SIS)).equalsIgnoreCase("1"));
                array_list.add(userPROFILE);
                cursor.moveToNext();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }
        return array_list;
    }

    @Override
    public ArrayList<ProfileTable> getFilteredData(SQLiteDatabase db, ColumnValuePair... pair) {
        ArrayList<ProfileTable> array_list = new ArrayList<>();

        String queryString = "select * from " + getTableName();

        if (pair != null) {
            StringBuilder filteredQuery = new StringBuilder();
            if (pair.length > 0) {
                filteredQuery.append(" WHERE ");
            }
            for (int i = 0; i < pair.length; i++) {
                filteredQuery.append(pair[i].getColumnName()).append(" = ").append("'").append(pair[i].getColumnValue()).append("'");
                if (i != pair.length - 1) {
                    filteredQuery.append(" AND ");
                }
            }
            if (filteredQuery.length() > 0) {
                queryString = queryString + filteredQuery.toString();
            }
        }

        Cursor cursor = db.rawQuery(queryString, null);
        try {
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                ProfileTable userPROFILE = new ProfileTable();
                userPROFILE.setProfileId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(PROFILE_COLUMN_ID))));
                userPROFILE.setName(cursor.getString(cursor.getColumnIndex(PROFILE_COLUMN_USERNAME)));
                userPROFILE.setImgProfileUrl(cursor.getString(cursor.getColumnIndex(PROFILE_COLUMN_PROFILE_URL)));
                userPROFILE.setParentProfileId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(PROFILE_COLUMN_PROFILE_PARENT_ID))));
                userPROFILE.setDescription(cursor.getString(cursor.getColumnIndex(PROFILE_COLUMN_PROFILE_DESCRIPTION)));
                userPROFILE.setHasBroSis(cursor.getString(cursor.getColumnIndex(PROFILE_COLUMN_PROFILE_HAS_BRO_SIS)).equalsIgnoreCase("1"));
                array_list.add(userPROFILE);
                cursor.moveToNext();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }
        return array_list;
    }

    @Override
    public int getRowCount(SQLiteDatabase db, ColumnValuePair... pair) {
        int numRows = 0;
        if(pair != null && pair.length > 0){
            StringBuilder filteredQuery = new StringBuilder();
            String strSelection = "";
            for (int i = 0; i < pair.length; i++) {
                filteredQuery.append(pair[i].getColumnName()).append(" = ").append("'").append(pair[i].getColumnValue()).append("'");
                if (i != pair.length - 1) {
                    filteredQuery.append(" AND ");
                }
            }
            if (filteredQuery.length() > 0) {
                strSelection = strSelection + filteredQuery.toString();
            }
            numRows = (int) DatabaseUtils.queryNumEntries(db, PROFILE_TABLE_NAME , strSelection);
        }else{
            numRows = (int) DatabaseUtils.queryNumEntries(db, PROFILE_TABLE_NAME);
        }
        return  numRows;
    }

    @Override
    public boolean updateData(SQLiteDatabase db, ProfileTable tableModel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(PROFILE_COLUMN_ID, tableModel.getProfileId());
        contentValues.put(PROFILE_COLUMN_USERNAME, tableModel.getName());
        contentValues.put(PROFILE_COLUMN_PROFILE_URL, tableModel.getImgProfileUrl());
        contentValues.put(PROFILE_COLUMN_PROFILE_PARENT_ID, tableModel.getParentProfileId());
        contentValues.put(PROFILE_COLUMN_PROFILE_DESCRIPTION, tableModel.getDescription());
        contentValues.put(PROFILE_COLUMN_PROFILE_HAS_BRO_SIS, tableModel.isHasBroSis() ? "1" : "0");
        db.update(getTableName(), contentValues, PROFILE_COLUMN_ID + " = ? ", new String[] { String.valueOf(tableModel.getProfileId()) } );
        return true;
    }

    @Override
    public boolean insertIfNotExistInDb(SQLiteDatabase db, ProfileTable tableModel) {
        boolean result = false;
        if(getRowCount(db,new ColumnValuePair(PROFILE_COLUMN_ID , String.valueOf(tableModel.getProfileId()))) == 0){
            //Data not exist hence insert
            result = insertInDb(db,tableModel);
        }else{
            //Data exist hence update
            result = updateData(db,tableModel);
        }
        return result;
    }

    @Override
    public Integer deleteData(SQLiteDatabase db, ProfileTable tableModel) {
        return db.delete(getTableName(),
                PROFILE_COLUMN_USERNAME + " = ? ",
                new String[] { tableModel.getName()});
    }


}