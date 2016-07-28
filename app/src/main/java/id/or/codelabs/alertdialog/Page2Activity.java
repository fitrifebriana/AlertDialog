package id.or.codelabs.alertdialog;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by FitriFebriana on 6/20/2016.
 */
public class Page2Activity extends Activity {

    private Button btnSingleChoice;
    private Button btnMultipleChoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_2);

        btnSingleChoice = (Button) findViewById(R.id.btn_single_choice);
        btnMultipleChoice = (Button) findViewById(R.id.btn_multiple_choice);

        btnSingleChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertSingleChoice();
            }
        });

        btnMultipleChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertMultipleChoices();
            }
        });
    }

    private void alertMultipleChoices() {
        final ArrayList<Integer> selectedItems = new ArrayList<Integer>();
        AlertDialog.Builder builder = new AlertDialog.Builder(Page2Activity.this);
        builder.setTitle("Choose One or More")
                .setMultiChoiceItems(R.array.choices, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (isChecked) {
                            selectedItems.add(which);
                        } else if (selectedItems.contains(which)) {
                            selectedItems.remove(Integer.valueOf(which));
                        }
                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String selectedIndex = "";
                        for (Integer i : selectedItems) {
                            selectedIndex += i + ",";
                        }

                        Toast.makeText(getApplicationContext(), "Selected index : " + selectedIndex, Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).show();
    }

    private void alertSingleChoice() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Page2Activity.this);
        builder.setTitle("Choose One")
                .setSingleChoiceItems(R.array.choices, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Selected index : " + which, Toast.LENGTH_SHORT).show();
                    }
                })
                .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int selectedPosition = ((AlertDialog) dialog).getListView().getCheckedItemPosition();
                        Toast.makeText(getApplicationContext(), "Selected Position : " + selectedPosition, Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).show();

    }

}
