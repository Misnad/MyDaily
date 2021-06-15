package com.misnadqasim.mydaily.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.misnadqasim.mydaily.component.Task;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MyDaily.db";
    private SQLiteDatabase database;

    private static final String COL1 = "Id";
    private static final String COL2 = "Name";
    private static final String COL3 = "ColorCode";
    private static final String COL4 = "Parent";
    private static final String COL5 = "StartDate";
    private static final String COL6 = "EndDate";
    private static final String COL7 = "RepeatVal";


    public Database(Context context) {
        super(context, DATABASE_NAME, null, 1);
        database = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String tableCols =
                COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COL2 + " VARCHAR, " +
                        COL3 + " VARCHAR, " +
                        COL4 + " INTEGER , " +
                        COL5 + " VARCHAR, " +
                        COL6 + " VARCHAR, " +
                        COL7 + " INTEGER NOT NULL";


        db.execSQL("CREATE TABLE IF NOT EXISTS TASKS(" + tableCols + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addTask(
            String name,
            String colorCode,
            Integer parent,
            String startDate,
            String endDate,
            int repeatVal
    ) {
        String values =
                        "'" + name + "', " +
                        "'" + colorCode + "', " +
                        "'" + parent + "', " +
                        "'" + startDate + "', " +
                        "'" + endDate + "', " +
                        "'" + repeatVal + "'";

        String IDs =
                COL2 + ", " +
                COL3 + ", " +
                COL4 + ", " +
                COL5 + ", " +
                COL6 + ", " +
                COL7;

        database.execSQL("INSERT INTO TASKS(" + IDs + ") VALUES(" + values + ");");
    }

    public void removeTask(int taskID) {
        database.execSQL("DELETE FROM TASKS WHERE Id = " + taskID);
        // TODO also remove all task t, where t.parent == taskID
    }

    public void editTask(int taskID,
                         String name,
                         String color,
                         Integer parent,
                         String sDate,
                         String eDate,
                         int repeat) {
        database.execSQL("UPDATE TASKS SET " + COL2 + " = '" + name + "' WHERE Id = " + taskID);
        database.execSQL("UPDATE TASKS SET " + COL3 + " = '" + color + "' WHERE Id = " + taskID);
        database.execSQL("UPDATE TASKS SET " + COL4 + " = '" + parent + "' WHERE Id = " + taskID);
        database.execSQL("UPDATE TASKS SET " + COL5 + " = '" + sDate + "' WHERE Id = " + taskID);
        database.execSQL("UPDATE TASKS SET " + COL6 + " = '" + eDate + "' WHERE Id = " + taskID);
        database.execSQL("UPDATE TASKS SET " + COL7 + " = '" + repeat + "' WHERE Id = " + taskID);
    }

    public Task getTask(int taskID) {
        Cursor resultSet = database.rawQuery("SELECT * FROM 'TASKS' WHERE Id = " + taskID, null);
        resultSet.moveToFirst();
        int id = resultSet.getInt(0);
        String name = resultSet.getString(1);
        String colorCode = resultSet.getString(2);
        Integer parent = resultSet.getInt(3);
        String startDate = resultSet.getString(4);
        String endDate = resultSet.getString(5);
        int repeatVal = resultSet.getInt(6);
        return new Task(id, name, colorCode, parent, startDate, endDate, repeatVal);
    }

    public ArrayList<Integer> getAllTasks() {
        ArrayList<Integer> taskIDs = new ArrayList<>();
        Cursor cur = database.rawQuery("Select * from TASKS",null);
        cur.moveToFirst();
        while (!cur.isLast()) {
            taskIDs.add(cur.getInt(0));
            cur.moveToNext();
        }
        return taskIDs;
    }

    public void exec(String command) {
        database.execSQL(command);
    }

}
