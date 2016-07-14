package com.hfad.joke.quizapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.StrictMode;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by serpentcs on 14/7/16.
 */
public class QuizQuestion extends SQLiteOpenHelper {

    private static final String TAG= "QuizQuestion";

    private static  final String DB_NAME= "questionDb";
    private static final  int DB_VERSION= 1;
    private static final  String TABLE_NAME="question";
    private static  final String ID="qid";
    private static final String  QUESTION="question";
    private static final String ANSWER="ans";
    private static final String OPTION_ONE="op1";
    private  static final String OPTION_TWO="op2";
    private static final String  SCORE="score";

    private SQLiteDatabase sqlDb;

    public QuizQuestion(Context mContxt)
    {
        super(mContxt,DB_NAME,null,DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        sqlDb=db;
        Log.i(TAG, "onCreate: ");
        String query="CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                QUESTION+" TEXT ,"+OPTION_ONE+ " TEXT," +OPTION_TWO+ " TEXT,"+ANSWER+ " TEXT," +SCORE+ " TEXT)";
        db.execSQL(query);
        addQuestion();

    }

    private void addQuestion() {

        addQuestion(new Question("Android is Linux base Operating System","true","false","true"));
        addQuestion(new Question("How to get a response from an activity in Android?"," startActivityToResult()","startActiivtyForResult()","startActiivtyForResult()"));
        addQuestion(new Question("How to move services to foreground in android?"," startFordgroud(int id, Notification notification). ","Using startService(Intent intent)"," startFordgroud(int id, Notification notification). "));
        addQuestion(new Question("How to stop the services in android?","finish()"," stopSelf() and stopService() "," stopSelf() and stopService() "));
        addQuestion(new Question("What is the time limit of broadcast receiver in android?","15 sec","10 sec","10 sec"));
        addQuestion(new Question("What is the purpose of super.onCreate() in android?","To create an activity","To create a graphical window for subclass","To create a graphical window for subclass"));
        addQuestion(new Question("How to fix crash using log cat in android?"," Gmail","log cat contains the exception name along with the line number","log cat contains the exception name along with the line number"));
        addQuestion(new Question("What is an interface in android?"," Interface is a class.","Interface acts as a bridge between class and the outside world.","Interface acts as a bridge between class and the outside world."));
        addQuestion(new Question("How to get current location in android?","SQlite","Using with GPRS","Using with GPRS"));
        addQuestion(new Question("What is the life cycle of services in android?","onCreate()−>onStartCommand()−>onDestory()","onCreate()−>onStartCommand()−>onDestory()->final()","onCreate()−>onStartCommand()−>onDestory()"));
    }
    public void addQuestion(Question q) {

        // SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(QUESTION, q.getQUESTION());
        values.put(OPTION_ONE, q.getOPTION_ONE());
        values.put(OPTION_TWO, q.getOPTION_TWO());
        values.put(ANSWER, q.getANSWER());

        if (sqlDb.insertOrThrow(TABLE_NAME, null, values)==-1)
        {
            Log.i(TAG, "addQuestion: didnt insert ");
        }

    }

    public ArrayList<Question> gatAllQuestion()
    {
        Log.i(TAG, "gatAllQuestion: ");
     ArrayList<Question> queLIst= new ArrayList<Question>();
        String selectQuery= "SELECT * FROM  " +TABLE_NAME;
        sqlDb=getReadableDatabase();
        Cursor cursor= sqlDb.rawQuery(selectQuery,null);

        Log.i(TAG, "gatAllQuestion: cursor size is  " +cursor.getCount());

//        if(cursor.moveToFirst())
//        {
//            Log.i(TAG, "gatAllQuestion: size"+cursor.getString(0));
//        }


        if(cursor.moveToFirst())
        {
            do{
                Question q= new Question();
                q.setID(cursor.getInt(0));
                q.setQUESTION(cursor.getString(1));
                q.setOPTION_ONE(cursor.getString(2));
                q.setOPTION_TWO(cursor.getString(3));
                q.setANSWER(cursor.getString(4));
                queLIst.add(q);

            }while ((cursor.moveToNext()));
        }
        cursor.close();
        return queLIst;

    }
        @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

            Log.i(TAG, "onUpgrade: ");
        db.execSQL("DROP TABLE IF EXIST "+TABLE_NAME);
        onCreate(db);
    }
}
