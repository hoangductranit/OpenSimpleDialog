package cordova.plugin.simpledialog;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.apache.cordova.CordovaInterface;

import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.DatePicker;

import androidx.appcompat.app.AlertDialog;

import com.example.hello.R;

import java.util.Calendar;

/**
 * This class echoes a string called from JavaScript.
 */
public class OpenSimpleDialog extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("alert")) {
            String contact = args.getString(0);
            final CordovaInterface cordova = this.cordova;
            Runnable runnable = new Runnable() {
                public void run() {

                    AlertDialog.Builder builder = new AlertDialog.Builder(cordova.getActivity());
                    builder.setTitle("Add contact");
                    builder.setMessage("Are you sure to add this phone number " + contact + " to your contact ?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            callbackContext.success("Added successfully");

                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            callbackContext.success("Added unsuccessfully");
                        }
                    });
                    builder.create();
                    builder.show();
                }
            };
            cordova.getActivity().runOnUiThread(runnable);
            return true;
        } else if (action.equals("openDateTimePicker")) {
            cordova.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Calendar calendar = Calendar.getInstance();
                    final int year = calendar.get(Calendar.YEAR);
                    final int month = calendar.get(Calendar.MONTH);
                    final int day = calendar.get(Calendar.DAY_OF_MONTH);
                    DatePickerDialog datePickerDialog = new DatePickerDialog(cordova.getActivity(),
                            android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                            dateSetListener, year, month, day);
                    datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    datePickerDialog.show();

                    dateSetListener = new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                            month = month + 1;
                            String dateStr = day + "/" + month + "/" + year;
                            callbackContext.success(dateStr);
                        }
                    };
                }
            });
            return true;
        }
        return false;
    }

    private DatePickerDialog.OnDateSetListener dateSetListener;

}
