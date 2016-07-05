package neusoft.duanxudong.com.classdemo.Service;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import neusoft.duanxudong.com.classdemo.model.ExamQuestion;

/**
 * Created by on 2015/5/27
 */
public class DDDBService {
    private SQLiteDatabase db;

    public DDDBService() {
        db = SQLiteDatabase.openDatabase("/data/data/neusoft.duanxudong.com.classdemo/databases/question.db", null, SQLiteDatabase.OPEN_READWRITE);
// TODO: 16/4/7  


    }


    public void finalize() throws Throwable {

        db.close();
        super.finalize();
    }

    public List<ExamQuestion> getQuestions() {
        List<ExamQuestion> list = new ArrayList<ExamQuestion>();
        Cursor cursor = db.rawQuery("select * from question", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int count = cursor.getCount();
            for (int i = 0; i < count; i++) {
                cursor.moveToPosition(i);
                ExamQuestion question = new ExamQuestion();
                question.question = cursor.getString(cursor.getColumnIndex("question"));
                question.answerA = cursor.getString(cursor.getColumnIndex("answerA"));
                question.answerB = cursor.getString(cursor.getColumnIndex("answerB"));
                question.answerC = cursor.getString(cursor.getColumnIndex("answerC"));
                question.answerD = cursor.getString(cursor.getColumnIndex("answerD"));
                question.answer = cursor.getInt(cursor.getColumnIndex("answer"));
                question.ID = cursor.getInt(cursor.getColumnIndex("ID"));
                question.explaination = cursor.getString(cursor.getColumnIndex("explaination"));
                question.selectedAnswer = -1;
                list.add(question);
            }
        }
        return list;
    }

}
