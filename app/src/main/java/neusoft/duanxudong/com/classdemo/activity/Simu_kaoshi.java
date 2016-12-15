package neusoft.duanxudong.com.classdemo.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import neusoft.duanxudong.com.classdemo.R;
import neusoft.duanxudong.com.classdemo.Service.DDDBService;
import neusoft.duanxudong.com.classdemo.model.ExamQuestion;

/**
 * Created by duanxudong on 16/1/25 12:29 ${PACKAGENAME}.
 */
public class Simu_kaoshi extends BaseActivity {


    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.timmer)
    TextView timmer;
    long recLen;
    @Bind({R.id.answerA, R.id.answerB, R.id.answerC, R.id.answerD})
    RadioButton[] radioButtons;
    @Bind(R.id.btn_next)
    Button btn_next;
    @Bind(R.id.radioGroup)
    RadioGroup radioGroup;
    @Bind(R.id.question)
    TextView tv_question;
    @Bind(R.id.explaination)
    TextView tv_explaination;
    @Bind(R.id.btn_previous)
    Button btn_previous;
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            recLen++;

            long min = recLen / 60;

            timmer.setText(min + "分" + recLen % 60 + "秒");
            handler.postDelayed(this, 1000);
        }
    };
    private int count;
    private int current;
    private boolean wrongMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_all);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        handler.postDelayed(runnable, 1000);

        String DB_PATH = "/data/data/neusoft.duanxudong.com.classdemo/databases/";
        String DB_NAME = "question.db";

        if ((new File(DB_PATH + DB_NAME).exists())) {
            File dir = new File(DB_PATH);
            if (!dir.exists()) {
                dir.mkdir();
            }

            try {
                InputStream is = getBaseContext().getAssets().open(DB_NAME);
                OutputStream os = new FileOutputStream(DB_PATH + DB_NAME);
                byte[] buffer = new byte[1024];
                int length;

                while ((length = is.read(buffer)) > 0) {
                    os.write(buffer, 0, length);
                }
                os.flush();
                os.close();
                is.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        final DDDBService dbService = new DDDBService();
        final List<ExamQuestion> list = dbService.getQuestions();

        count = list.size();
        current = 0;
        wrongMode = false;


        ExamQuestion q = list.get(0);
        tv_question.setText(q.question);
        tv_explaination.setText(q.explaination);
        radioButtons[0].setText(q.answerA);
        radioButtons[1].setText(q.answerB);
        radioButtons[2].setText(q.answerC);
        radioButtons[3].setText(q.answerD);


        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (current < count - 1) {
                    current++;
                    ExamQuestion q = list.get(current);
                    tv_question.setText(q.question);
                    radioButtons[0].setText(q.answerA);
                    radioButtons[1].setText(q.answerB);
                    radioButtons[2].setText(q.answerC);
                    radioButtons[3].setText(q.answerD);
                    tv_explaination.setText(q.explaination);

                    radioGroup.clearCheck();
                    if (q.selectedAnswer != -1) {
                        radioButtons[q.selectedAnswer].setChecked(true);
                    }
                } else if (current == count - 1 && wrongMode) {
                    new AlertDialog.Builder(Simu_kaoshi.this)
                            .setTitle("提示")
                            .setMessage("已经到达最后一题，是否退出？")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Simu_kaoshi.this.finish();

                                    try {
                                        dbService.finalize();
                                    } catch (Throwable throwable) {
                                        throwable.printStackTrace();
                                    }
                                }
                            })
                            .setNegativeButton("取消", null)
                            .show();
                } else {
                    final List<Integer> wrongList = checkAnswer(list);
                    if (wrongList.size() == 0) {
                        new AlertDialog.Builder(Simu_kaoshi.this)
                                .setTitle("提示")
                                .setMessage("恭喜你全部回答正确！")
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Simu_kaoshi.this.finish();
                                    }
                                })
                                .show();
                    }
                    new AlertDialog.Builder(Simu_kaoshi.this)
                            .setTitle("提示")
                            .setMessage("您答对了" + (list.size() - wrongList.size()) +
                                    "道题目，答错了" + wrongList.size() + "道题目。是否查看错题？")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    wrongMode = true;
                                    List<ExamQuestion> newList = new ArrayList<ExamQuestion>();
                                    for (int i = 0; i < wrongList.size(); i++) {
                                        newList.add(list.get(wrongList.get(i)));
                                    }
                                    list.clear();
                                    for (int i = 0; i < newList.size(); i++) {
                                        list.add(newList.get(i));
                                    }

                                    current = 0;
                                    count = list.size();

                                    ExamQuestion q = list.get(current);
                                    tv_question.setText(q.question);
                                    radioButtons[0].setText(q.answerA);
                                    radioButtons[1].setText(q.answerB);
                                    radioButtons[2].setText(q.answerC);
                                    radioButtons[3].setText(q.answerD);
                                    tv_explaination.setText(q.explaination);
                                    tv_explaination.setVisibility(View.VISIBLE);
                                }
                            })
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Simu_kaoshi.this.finish();
                                }
                            })
                            .show();
                }
            }
        });

        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (current > 0) {
                    current--;
                    ExamQuestion q = list.get(current);
                    tv_question.setText(q.question);
                    radioButtons[0].setText(q.answerA);
                    radioButtons[1].setText(q.answerB);
                    radioButtons[2].setText(q.answerC);
                    radioButtons[3].setText(q.answerD);
                    tv_explaination.setText(q.explaination);

                    radioGroup.clearCheck();
                    if (q.selectedAnswer != -1) {
                        radioButtons[q.selectedAnswer].setChecked(true);
                    }
                }
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int i = 0; i < 4; i++) {
                    if (radioButtons[i].isChecked()) {
                        list.get(current).selectedAnswer = i;
                        break;
                    }
                }
            }
        });

    }

    private List<Integer> checkAnswer(List<ExamQuestion> list) {
        List<Integer> wrongList = new ArrayList<Integer>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).answer != list.get(i).selectedAnswer) {
                wrongList.add(i);
            }
        }
        return wrongList;
    }


}

