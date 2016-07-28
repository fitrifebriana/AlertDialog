package id.or.codelabs.alertdialog;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class Page1Activity extends AppCompatActivity {

    private Button btnOne;
    private Button btnTwo;
    private Button btnThree;
    private Button btnTimePicker;
    private EditText txtTime;
    private Button btnDatePicker;
    private EditText txtDate;
    private Button btnListView;
    private Button btnScroll;
    private Button btnEdit;
    private Button btnWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_1);

        btnOne = (Button)findViewById(R.id.btn_one);
        btnTwo = (Button)findViewById(R.id.btn_two);
        btnThree = (Button)findViewById(R.id.btn_three);
        btnTimePicker = (Button)findViewById(R.id.btn_time_picker);
        btnDatePicker = (Button)findViewById(R.id.btn_date_picker);
        btnListView = (Button)findViewById(R.id.btn_list_view);
        btnScroll = (Button)findViewById(R.id.btn_scroll);
        btnEdit = (Button)findViewById(R.id.btn_edit);
        btnWebView = (Button)findViewById(R.id.btn_web_view);

        btnOne.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                alertOneButton();
            }
        });

        btnTwo.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                alertTwoButtons();
            }
        });

        btnThree.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                alertThreeButtons();
            }
        });

        btnTimePicker.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                alertTimePicker();
            }
        });

        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDatePicker();
            }
        });

        btnListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertSimpleListView();
            }
        });

        btnScroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertScrollView();
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertFormElements();
            }
        });

        btnWebView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertWebView();
            }
        });
    }

    private void alertWebView() {
        WebView webView = new WebView(Page1Activity.this);
        webView.loadUrl("http://google.com/");

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        new AlertDialog.Builder(Page1Activity.this).setView(webView)
                .setTitle("Web View")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).show();
    }

    private void alertFormElements() {
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElements = inflater.inflate(R.layout.form_elements, null, false);

        final CheckBox checkBox = (CheckBox)formElements.findViewById(R.id.check_box);
        final RadioGroup radioGender = (RadioGroup)formElements.findViewById(R.id.rg_gender);
        final EditText editName = (EditText)formElements.findViewById(R.id.edit_name);

        new AlertDialog.Builder(Page1Activity.this).setView(formElements)
                .setTitle("Form Elements")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String toastString = "";

                        if(checkBox.isChecked()){
                            toastString += "Happy is checked!\n";
                        }else{
                            toastString += "Happy is NOT checked!\n";
                        }

                        int selectedId = radioGender.getCheckedRadioButtonId();

                        RadioButton selectedRadioButton = (RadioButton)formElements.findViewById(selectedId);

                        toastString += "Selected radio button is : " + selectedRadioButton.getText() + "\n";
                        toastString += "Name : " + editName.getText() + "\n";

                        Toast.makeText(getApplicationContext(), toastString, Toast.LENGTH_LONG).show();

                        dialog.cancel();
                    }
                }).show();
    }

    private void alertScrollView() {
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View scrollView = inflater.inflate(R.layout.scroll_text, null, false);

        TextView tv = (TextView)scrollView.findViewById(R.id.txt_with_scroll);
        tv.setText("");

        for (int i = 1; i < 50; i++){
            tv.append("Hello ,there!\n");
            tv.append("Are you happy now?\n\n");
        }

        new AlertDialog.Builder(Page1Activity.this).setView(scrollView)
                .setTitle("Scroll View")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).show();
    }

    private void alertSimpleListView() {
        final CharSequence[] items = {"Fitri", "Febri", "Ana"};

        new AlertDialog.Builder(Page1Activity.this)
            .setTitle("Make your selection")
            .setItems(items, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int item) {
                Toast.makeText(getApplicationContext(), "You're choosing " + items[item], Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        }).show();
    }

    private void alertDatePicker() {
        txtDate = (EditText)findViewById(R.id.txt_date);

        //get current date
        final Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);

        //launch date picker dialog
        DatePickerDialog dpd = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener(){
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        txtDate.setText(dayOfMonth + "-" + (monthOfYear+1) + "-" + year);
                    }
                }, day, month, year);
        dpd.show();
    }

    private void alertTimePicker() {

        txtTime = (EditText) findViewById(R.id.txt_time);

        //get current time
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        //Launch time picker dialog
        TimePickerDialog tpd = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener(){
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        //Display selected time
                        txtTime.setText(hourOfDay + ":" + minute);
                    }
                }, hour, minute, false);
        tpd.show();
    }

    private void alertThreeButtons() {
        new AlertDialog.Builder(Page1Activity.this)
                .setTitle("Three Buttons")
                .setMessage("Are you hungry?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Me too!", Toast.LENGTH_LONG).show();
                        dialog.cancel();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Okaaaay", Toast.LENGTH_LONG).show();
                        dialog.cancel();
                    }
                })
                .setNeutralButton("A little", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Get some snack!", Toast.LENGTH_LONG).show();
                        dialog.cancel();
                    }
                }).show();
    }

    private void alertTwoButtons() {
        new AlertDialog.Builder(Page1Activity.this)
                .setTitle("Two Buttons")
                .setMessage("Are you happy now ? :)")
                .setNegativeButton("No", new DialogInterface.OnClickListener(){
                    @TargetApi(11)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Sorry to hear that :(", Toast.LENGTH_LONG).show();
                        dialog.cancel();
                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                    @TargetApi(11)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "You're Awesome !", Toast.LENGTH_LONG).show();
                        dialog.cancel();
                    }
                }).show();
    }

    private void alertOneButton() {
        new AlertDialog.Builder(Page1Activity.this)
                .setTitle("One Button")
                .setMessage("Hello! Semangat belajarnya ya :)")
                .setPositiveButton("Okay", new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }

                }).show();
    }
}
