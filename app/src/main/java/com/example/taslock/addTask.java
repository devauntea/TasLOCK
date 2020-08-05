package com.example.taslock;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class addTask extends AppCompatActivity {
//    private Spinner spinner;
//    LinearLayout BoarderView;
    EditText TitleView;
    EditText TimeView;
    EditText TeacherView;
    TextView SubjectView;
    DatabaseReference databasePosts;
    boolean clicked = false;
    FirebaseAuth fAuth;
    String userid;
    FirebaseUser user;
    TextView Timer;
    int t1hour, t1minute;
    FirebaseDatabase database;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        Timer = findViewById(R.id.Timer);
        TitleView = findViewById(R.id.eventTitle);
        TeacherView = findViewById(R.id.teacher);
        SubjectView = findViewById(R.id.subView);
//        BoarderView = findViewById(R.id.boarder);
//        spinner = findViewById(R.id.spinner);
        fAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        userid = user.getUid();

        databasePosts = database.getReference().child(userid);
        createNotificationChannel();




//        spinner.setOnItemSelectedListener(this);
//
//        String[] color = getResources().getStringArray(R.array.colors);
//        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, color);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);

        Timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        addTask.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                t1hour = hourOfDay;
                                t1minute = minute;

                                String time = t1hour + ":" + t1minute;
                                SimpleDateFormat f24Hours = new SimpleDateFormat(
                                        "HH:mm"
                                );
                                try {
                                    Date date = f24Hours.parse(time);
                                    SimpleDateFormat f12Hours = new SimpleDateFormat(
                                            "hh:mm"
                                    );
                                    Timer.setText(f12Hours.format(date));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        },12,0,true
                );
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.updateTime(t1hour,t1minute);
                timePickerDialog.show();
            }
        });



//        final LottieAnimationView lottieClickFloat = findViewById(R.id.lottieClickSend);
//        lottieClickFloat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (clicked = true){         String title = TitleView.getText().toString();
//                    TitleView.setText("");
//                    String post = PostView.getText().toString();
//                    PostView.setText("");
//                    Intent intent = new Intent( addTask.this, Tasks.class);
//                    startActivity(intent);
//
//                    taskedPosts postMessage  = new taskedPosts(title,post);
//                    databasePosts.push().setValue(postMessage);
//                    closeKeyboard();
//                    clicked = false;
//                }
//            }
//        });

    }
    /*@Override
    public void onItemSelected(AdapterView<?> parent,View view, int position, long id ){
        if (parent.getId() == R.id.spinner){
            String valueFromString = parent.getItemAtPosition(position).toString();

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent ){

    }*/

    public void task(View view){

        databasePosts.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String title = TitleView.getText().toString();
                String time = Timer.getText().toString();
                String teacher = TeacherView.getText().toString();
                String subject = SubjectView.getText().toString();
                SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
                Date date = new Date();
                String timenow = formatter.format(date);
                int mintimenow = toMins(timenow);
                int timeTilTask;
                //
                if (Timer.getText().toString() != "" ) {
                    final int timeInt = toMins(time);
                    Surveyposts sTime = dataSnapshot.getValue(Surveyposts.class);
                    int totalInt = (sTime.startTime + timeInt);
                    String gTime = reverseFormat(toStringTime(totalInt));
                    Intent intent1a = new Intent("my.action.string1");
                    intent1a.putExtra("extra", title);
                    sendBroadcast(intent1a);
                    Intent intent1b = new Intent("my.action.string2");
                    intent1b.putExtra("extra", gTime);
                    sendBroadcast(intent1b);
                    Intent intent2 = new Intent(addTask.this, ReminderBroadcast.class);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(addTask.this, 0, intent2, 0);
                    AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

                    long timeAtButtonClick = System.currentTimeMillis();

                    long tenSecsInMilis = 1000 * 10;

                    alarmManager.set(AlarmManager.RTC_WAKEUP,
                        timeAtButtonClick + tenSecsInMilis,
                            pendingIntent);
                    TitleView.setText("");
                    TeacherView.setText("");
                    Timer.setText("");
                    SubjectView.setText("");
                    taskedPosts postMessage = new taskedPosts(title, gTime, teacher, subject);
                    databasePosts.push().setValue(postMessage);
                }

//                NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
//                        .setContentTitle(title +" Due at " + gTime)
//                        .setContentText("Your scheduled " + title + " task is due at " + gTime)
//                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);


            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }
            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
            }
            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }

        });


        Intent intent = new Intent( addTask.this, Tasks.class);
        startActivity(intent);


        closeKeyboard();
        clicked = false;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "TaskitReminderChannel";
            String description = "Channel for Taskit Reminder";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("notifyTaskit", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void closeKeyboard(){
        View view = this.getCurrentFocus();
        if (view != null){
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }
    public static int toMins(String s) {
        String[] hourMin = s.split(":");
        int hour = Integer.parseInt(hourMin[0].replaceAll("/s+"," "));
        int mins = Integer.parseInt(hourMin[1].replaceAll("/s+"," "));
        int hoursInMins = hour * 60;
        return hoursInMins + mins;
    }
    public static String toStringTime(int t) {
        int hour = t / 60;
        int min = t % 60;

        return hour + ":" + min;
    }
    public String reverseFormat(String time){
        DateFormat df = new SimpleDateFormat("HH:mm");
        //Date/time pattern of desired output date
        DateFormat outputformat = new SimpleDateFormat("hh:mm aa");
        Date date = null;
        String output = null;
        try{
            //Conversion of input String to date
            date= df.parse(time);
            //old date format to new date format
            output = outputformat.format(date);
        }catch(ParseException pe){
            pe.printStackTrace();
        }

        return output;
    }
}

