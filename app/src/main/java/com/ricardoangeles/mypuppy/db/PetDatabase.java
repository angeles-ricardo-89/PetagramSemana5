package com.ricardoangeles.mypuppy.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ricardoangeles.mypuppy.model.Pet;

import java.util.ArrayList;

public class PetDatabase extends SQLiteOpenHelper {

    private Context context;

    public PetDatabase(Context context){
        super(context, DBBaseConstants.DATABASE_NAME, null, DBBaseConstants.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createPetTableQuery = "CREATE TABLE " + DBBaseConstants.TABLE_PET + "(" +
                DBBaseConstants.TABLE_PET_ID + " INTEGER PRIMARY KEY, " +
                DBBaseConstants.TABLE_PET_NAME + " TEXT, "+
                DBBaseConstants.TABLE_PET_PICTURE + " INTEGER" +
                ")";
        String createPetBoniesTableQuery = "CREATE TABLE " + DBBaseConstants.TABLE_PET_BONIES + "(" +
                DBBaseConstants.TABLE_PET_BONIES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                DBBaseConstants.TABLE_PET_BONIES_ID_PET + " INTEGER, " +
                DBBaseConstants.TABLE_PET_BONIES_BONIES_NUMBER + " INTEGER," +
                "FOREIGN KEY (" + DBBaseConstants.TABLE_PET_BONIES_ID_PET + ") "+
                "REFERENCES " + DBBaseConstants.TABLE_PET + "(" + DBBaseConstants.TABLE_PET_ID + ")" +
                ")";

        sqLiteDatabase.execSQL(createPetTableQuery);
        sqLiteDatabase.execSQL(createPetBoniesTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DBBaseConstants.TABLE_PET_BONIES);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DBBaseConstants.TABLE_PET);
        onCreate(sqLiteDatabase);
    }

    public Pet petFromRecord(Cursor record){
        Pet pet = new Pet(record.getInt(0),
                          record.getString(1),
                          record.getInt(3),
                          record.getInt(2));

        return pet;
    }



    public ArrayList<Pet> getPetsWithRank(){
        return getPetsWithRank(false, 0);
    }

    public ArrayList<Pet> getPetsWithRank(boolean last_ranked, int limit){
        ArrayList<Pet> pets = new ArrayList<Pet>();

        String query = "SELECT %s,%s,%s,total_rank,rank_id\n"+
                        "FROM %s, (SELECT max(%s) as rank_id,%s,SUM(%s) as total_rank " +
                                    "FROM %s GROUP BY %s)\n" +
                        "WHERE id = id_pet%s%s%s;";

        String finalQuery = String.format(query, DBBaseConstants.TABLE_PET_ID,
                             DBBaseConstants.TABLE_PET_NAME,
                             DBBaseConstants.TABLE_PET_PICTURE,
                             DBBaseConstants.TABLE_PET,
                             DBBaseConstants.TABLE_PET_BONIES_ID,
                             DBBaseConstants.TABLE_PET_BONIES_ID_PET,
                             DBBaseConstants.TABLE_PET_BONIES_BONIES_NUMBER,
                             DBBaseConstants.TABLE_PET_BONIES,
                             DBBaseConstants.TABLE_PET_BONIES_ID_PET,
                             last_ranked ? " AND total_rank > 0":"",
                             last_ranked ? " ORDER BY rank_id DESC":"",
                             limit == 0 ? "" : (" LIMIT "+Integer.toString(limit))
                             );

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor record = db.rawQuery(finalQuery, null);

        while(record.moveToNext()){
            Pet pet = petFromRecord(record);
            pets.add(pet);
        }

        db.close();
        return pets;
    }

    public void insertPet(Pet pet) throws SQLiteConstraintException {

        ContentValues values = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();
        values.put(DBBaseConstants.TABLE_PET_ID, pet.getId());
        values.put(DBBaseConstants.TABLE_PET_NAME, pet.getName());
        values.put(DBBaseConstants.TABLE_PET_PICTURE, pet.getPicture());
        db.insertOrThrow(DBBaseConstants.TABLE_PET, null, values);
        db.close();

        registerEmptyPetBonie(pet);
    }

    public void registerEmptyPetBonie(Pet pet){//workaround
        ContentValues values = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();
        values.put(DBBaseConstants.TABLE_PET_BONIES_ID_PET, pet.getId());
        values.put(DBBaseConstants.TABLE_PET_BONIES_BONIES_NUMBER, 0);//Un solo huesito (like)
        db.insert(DBBaseConstants.TABLE_PET_BONIES,null, values);
        db.close();
    }

    public void registerPetBonie(Pet pet){
        ContentValues values = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();
        values.put(DBBaseConstants.TABLE_PET_BONIES_ID_PET, pet.getId());
        values.put(DBBaseConstants.TABLE_PET_BONIES_BONIES_NUMBER, 1);//Un solo huesito (like)
        db.insert(DBBaseConstants.TABLE_PET_BONIES,null, values);
        db.close();
    }
}
