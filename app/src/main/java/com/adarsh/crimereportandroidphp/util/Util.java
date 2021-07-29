package com.adarsh.crimereportandroidphp.util;



import android.app.Activity;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.ParseException;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore.Images.Media;
import android.provider.MediaStore.MediaColumns;
import android.telephony.TelephonyManager;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.StyleSpan;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.io.OutputStream;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Provides convenience methods and abstractions to some tasks in Android
 * <p/>
 * <br/>
 * <br/>
 *
 * @author Jay
 */
public class Util {

    private static final String TAG = Util.class.getSimpleName();
    int day,month,currentYear;
    static ProgressDialog mProgressDialog;

    /**
     * Shows a long time duration toast message.
     *
     * @param msg Message to be show in the toast.
     * @return Toast object just shown
     **/
    public static Toast showToast(Context ctx, CharSequence msg) {
        return showToast(ctx, msg, Toast.LENGTH_LONG);
    }

    /**
     * Shows the message passed in the parameter in the Toast.
     *
     * @param msg      Message to be show in the toast.
     * @param duration Duration in milliseconds for which the toast should be shown
     * @return Toast object just shown
     **/
    public static Toast showToast(Context ctx, CharSequence msg, int duration) {
        Toast toast = Toast.makeText(ctx, msg, Toast.LENGTH_SHORT);
        toast.setDuration(duration);
        toast.show();
        return toast;
    }

    /**
     * Checks if the Internet connection is available.
     *
     * @return Returns true if the Internet connection is available. False otherwise.
     **/
    public static boolean isInternetAvailable(Context ctx) {
        // using received context (typically activity) to get SystemService causes memory link as this holds strong reference to that activity.
        // use application level context instead, which is available until the app dies.
        ConnectivityManager connectivityManager = (ConnectivityManager) ctx.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        // if network is NOT available networkInfo will be null
        // otherwise check if we are connected
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }

        return false;
    }

    /**
     * Checks if the SD Card is mounted on the device.
     **/
    public static boolean isSdCardMounted() {
        String status = Environment.getExternalStorageState();

        if (status != null && status.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        }

        return false;
    }

//    /**
//     * Shows an alert dialog with the OK button. When the user presses OK button, the dialog
//     * dismisses.
//     **/
//    public static void showAlertDialog(Context context, @StringRes int titleResId, @StringRes int bodyResId) {
//        showAlertDialog(context, context.getString(titleResId),
//                context.getString(bodyResId), null);
//    }

    /**
     * Shows an alert dialog with the OK button. When the user presses OK button, the dialog
     * dismisses.
     **/
    public static void showAlertDialog(Context context, String title, String body) {
        showAlertDialog(context, title, body, null);
    }

    /**
     * Shows an alert dialog with OK button
     **/
    public static void showAlertDialog(Context context, String title, String body, DialogInterface.OnClickListener okListener) {

        if (okListener == null) {
            okListener = new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            };
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setMessage(body).setPositiveButton("OK", okListener);

        if (!TextUtils.isEmpty(title)) {
            builder.setTitle(title);
        }

        builder.show();
    }



    /**
     * Shows a progress dialog with a spinning animation in it. This method must preferably called
     * from a UI thread.
     *
     * @param ctx           Activity context
     * @param title         Title of the progress dialog
     * @param body          Body/Message to be shown in the progress dialog
     * @param isCancellable True if the dialog can be cancelled on back button press, false otherwise
     **/
    public static void showProgressDialog(Context ctx, String title, String body, boolean isCancellable) {
        showProgressDialog(ctx, title, body, null, isCancellable);
    }

    /**
     * Shows a progress dialog with a spinning animation in it. This method must preferably called
     * from a UI thread.
     *
     * @param ctx           Activity context
     * @param title         Title of the progress dialog
     * @param body          Body/Message to be shown in the progress dialog
     * @param icon          Icon to show in the progress dialog. It can be null.
     * @param isCancellable True if the dialog can be cancelled on back button press, false otherwise
     **/
    public static void showProgressDialog(Context ctx, String title, String body, Drawable icon, boolean isCancellable) {

        if (ctx instanceof Activity) {
            if (!((Activity) ctx).isFinishing()) {
                mProgressDialog = ProgressDialog.show(ctx, title, body, true);
                mProgressDialog.setIcon(icon);
                mProgressDialog.setCancelable(isCancellable);
            }
        }
    }

    /**
     * Check if the {@link android.app.ProgressDialog} is visible in the UI.
     **/
    public static boolean isProgressDialogVisible() {
        return (mProgressDialog != null);
    }

    /**
     * Dismiss the progress dialog if it is visible.
     **/
    public static void dismissProgressDialog() {

        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }

        mProgressDialog = null;
    }


    /**
     * Creates a confirmation dialog with Yes-No Button. By default the buttons just dismiss the
     * dialog.
     *
     * @param ctx
     * @param message     Message to be shown in the dialog.
     * @param yesListener Yes click handler
     * @param noListener
     **/
    public static void showConfirmDialog(Context ctx, String message, DialogInterface.OnClickListener yesListener, DialogInterface.OnClickListener noListener) {
        showConfirmDialog(ctx, message, yesListener, noListener, "Yes", "No");
    }

    /**
     * Creates a confirmation dialog with Yes-No Button. By default the buttons just dismiss the
     * dialog.
     *
     * @param ctx
     * @param message     Message to be shown in the dialog.
     * @param yesListener Yes click handler
     * @param noListener
     * @param yesLabel    Label for yes button
     * @param noLabel     Label for no button
     **/
    public static void showConfirmDialog(Context ctx, String message, DialogInterface.OnClickListener yesListener, DialogInterface.OnClickListener noListener, String yesLabel, String noLabel) {

        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);

        if (yesListener == null) {
            yesListener = new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            };
        }

        if (noListener == null) {
            noListener = new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            };
        }

        builder.setMessage(message).setPositiveButton(yesLabel, yesListener).setNegativeButton(noLabel, noListener).show();
    }

    /**
     * Creates a confirmation dialog that show a pop-up with button labeled as parameters labels.
     *
     * @param ctx                 {@link android.app.Activity} {@link android.content.Context}
     * @param message             Message to be shown in the dialog.
     * @param dialogClickListener
     * @param positiveBtnLabel    For e.g. "Yes"
     * @param negativeBtnLabel    For e.g. "No"
     **/
    public static void showDialog(Context ctx, String message, String positiveBtnLabel, String negativeBtnLabel, DialogInterface.OnClickListener dialogClickListener) {

        if (dialogClickListener == null) {
            throw new NullPointerException("Action listener cannot be null");
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);

        builder.setMessage(message).setPositiveButton(positiveBtnLabel, dialogClickListener).setNegativeButton(negativeBtnLabel, dialogClickListener).show();
    }

    /**
     * Gets the version name of the application. For e.g. 1.9.3
     **/
    public static String getApplicationVersionNumber(Context context) {

        String versionName = null;

        if (context == null) {
            return versionName;
        }

        try {
            versionName = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }

        return versionName;
    }

    /**
     * Gets the version code of the application. For e.g. Maverick Meerkat or 2013050301
     **/
    public static int getApplicationVersionCode(Context ctx) {

        int versionCode = 0;

        try {
            versionCode = ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), 0).versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }

        return versionCode;
    }

    /**
     * Gets the version number of the Android OS For e.g. 2.3.4 or 4.1.2
     **/
    public static String getOsVersion() {
        return Build.VERSION.RELEASE;
    }

    /**
     * Gets the device unique id called IMEI. Sometimes, this returns 00000000000000000 for the
     * rooted devices.
     **/
    public static String getDeviceImei(Context ctx) {
        // use application level context to avoid unnecessary leaks.
        TelephonyManager telephonyManager = (TelephonyManager) ctx.getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();
    }


    public static void share(Context ctx, String sharingMsg, String emailSubject, String title) {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);

        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_TEXT, sharingMsg);
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, emailSubject);

        ctx.startActivity(Intent.createChooser(sharingIntent, title));
    }

    /**
     * Checks the type of data connection that is currently available on the device.
     *
     * @return <code>ConnectivityManager.TYPE_*</code> as a type of internet connection on the
     * device. Returns -1 in case of error or none of
     * <code>ConnectivityManager.TYPE_*</code> is found.
     **/
    public static int getDataConnectionType(Context ctx) {

        // use application level context to avoid unnecessary leaks.
        ConnectivityManager connectivityManager = (ConnectivityManager) ctx.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager != null && connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE) != null) {
            if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected()) {
                return ConnectivityManager.TYPE_MOBILE;
            } else if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected()) {
                return ConnectivityManager.TYPE_WIFI;
            } else
                return -1;
        } else
            return -1;
    }

    /**
     * Checks if the input parameter is a valid email.
     *
     * @param email
     * @return
     */
    public static boolean isValidEmail(String email) {

        if (email == null) {
            return false;
        }

        final String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Matcher matcher;
        Pattern pattern = Pattern.compile(emailPattern);

        matcher = pattern.matcher(email);

        if (matcher != null) {
            return matcher.matches();
        } else {
            return false;
        }
    }

//    @Nullable

    /**
     * Capitalizes each word in the string.
     *
     * @param string
     * @return
     */
    public static String capitalizeString(String string) {

        if (string == null) {
            return null;
        }

        char[] chars = string.toLowerCase().toCharArray();
        boolean found = false;
        for (int i = 0; i < chars.length; i++) {
            if (!found && Character.isLetter(chars[i])) {
                chars[i] = Character.toUpperCase(chars[i]);
                found = true;
            } else if (Character.isWhitespace(chars[i]) || chars[i] == '.' || chars[i] == '\'') { // You
                // can add other
                // chars here
                found = false;
            }
        } // end for

        return String.valueOf(chars);
    }



    public static ArrayList<String> toStringArray(JSONArray jsonArr) {

        if (jsonArr == null || jsonArr.length() == 0) {
            return null;
        }

        ArrayList<String> stringArray = new ArrayList<String>();

        for (int i = 0, count = jsonArr.length(); i < count; i++) {
            try {
                String str = jsonArr.getString(i);
                stringArray.add(str);
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return stringArray;
    }

    /**
     * Convert a given list of {@link String} into a {@link org.json.JSONArray}
     **/
    public static JSONArray toJSONArray(ArrayList<String> stringArr) {
        JSONArray jsonArr = new JSONArray();

        for (int i = 0; i < stringArr.size(); i++) {
            String value = stringArr.get(i);
            jsonArr.put(value);
        }

        return jsonArr;
    }

    public static String fromCalendar(final Calendar calendar) {
        // TODO: move this method to DateUtils
        Date date = calendar.getTime();
        String formatted = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(date);
        return formatted.substring(0, 22) + ":" + formatted.substring(22);
    }

    /**
     * Gets current date and time formatted as ISO 8601 string.
     **/
    public static String now() {
        // TODO: move this method to DateUtils
        return fromCalendar(GregorianCalendar.getInstance());
    }

    /**
     * Transforms ISO 8601 string to Calendar.
     **/
    public static Calendar toCalendar(final String iso8601string) throws ParseException {
        // TODO: move this method to DateUtils
        Calendar calendar = GregorianCalendar.getInstance();
        String s = iso8601string.replace("Z", "+00:00");
        try {
            s = s.substring(0, 22) + s.substring(23);
        } catch (IndexOutOfBoundsException e) {
            // throw new org.apache.http.ParseException();
            e.printStackTrace();
        }

        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").parse(s);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        calendar.setTime(date);
        return calendar;
    }

    /**
     * Gets random color integer
     **/
    public static int getRandomColor() {
        Random random = new Random();
        int red = random.nextInt(255);
        int green = random.nextInt(255);
        int blue = random.nextInt(255);

        return Color.argb(255, red, green, blue);
    }

    public static boolean isValidURL(String url) {
        URL urlObj;

        try {
            urlObj = new URL(url);
        } catch (MalformedURLException e) {
            return false;
        }

        try {
            urlObj.toURI();
        } catch (URISyntaxException e) {
            return false;
        }

        return true;
    }


    /**
     * Removes the parameters from the query from the uri
     */
    public static String removeQueryParameters(Uri uri) {
        assert (uri.getAuthority() != null);
        assert (uri.getPath() != null);
        Uri.Builder builder = new Uri.Builder();
        builder.scheme(uri.getScheme());
        builder.encodedAuthority(uri.getAuthority());
        builder.encodedPath(uri.getPath());
        return builder.build().toString();
    }




    /**
     * @param sourceText String to be converted to bold.
     * @deprecated Use {@link #toBold(String, String)}
     * Returns {@link android.text.SpannableString} in Bold typeface
     */
    public static SpannableStringBuilder toBold(String sourceText) {

        if (sourceText == null) {
            throw new NullPointerException("String to convert cannot be bold");
        }

        final SpannableStringBuilder sb = new SpannableStringBuilder(sourceText);

        // Span to set text color to some RGB value
        final StyleSpan bss = new StyleSpan(android.graphics.Typeface.BOLD);

        // set text bold
        sb.setSpan(bss, 0, sb.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        return sb;
    }

    /**
     * Typefaces the string as bold.
     * If sub-string is null, entire string will be typefaced as bold and returned.
     *
     * @param string
     * @param subString The subString within the string to bold. Pass null to bold entire string.
     * @return {@link android.text.SpannableString}
     */
    public static SpannableStringBuilder toBold(String string, String subString) {
        if (TextUtils.isEmpty(string)) {
            return new SpannableStringBuilder("");
        }

        SpannableStringBuilder spannableBuilder = new SpannableStringBuilder(string);

        StyleSpan bss = new StyleSpan(Typeface.BOLD);
        if (subString != null) {
            int substringNameStart = string.toLowerCase().indexOf(subString);
            if (substringNameStart > -1) {
                spannableBuilder.setSpan(bss, substringNameStart, substringNameStart + subString.length(),
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        } else {
            // set entire text to bold
            spannableBuilder.setSpan(bss, 0, spannableBuilder.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        }
        return spannableBuilder;
    }

    /**
     * Formats given size in bytes to KB, MB, GB or whatever. This will work up to 1000 TB
     */
    public static String formatSize(long size) {

        if (size <= 0) return "0";

        final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }

    /**
     * Formats given size in bytes to KB, MB, GB or whatever. Preferably use this method for
     * performance efficiency.
     *
     * @param si Controls byte value precision. If true, formatting is done using approx. 1000 Uses
     *           1024 if false.
     **/
    public static String formatSize(long bytes, boolean si) {
        int unit = si ? 1000 : 1024;

        if (bytes < unit) {
            return bytes + " B";
        }

        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp - 1) + (si ? "" : "i");
        return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
    }



    public static String getName(String firstName, String lastName) {
        if (!TextUtils.isEmpty(firstName) && !TextUtils.isEmpty(lastName)) {
            return firstName + " " + lastName;
        } else if (!TextUtils.isEmpty(firstName)) {
            return firstName;
        } else if (!TextUtils.isEmpty(lastName)) {
            return lastName;
        } else {
            return null;
        }
    }

    public static Bitmap roundBitmap(Bitmap bmp, int radius) {
        Bitmap sbmp;
        if (bmp.getWidth() != radius || bmp.getHeight() != radius) {
            sbmp = Bitmap.createScaledBitmap(bmp, radius, radius, false);
        } else {
            sbmp = bmp;
        }

        Bitmap output = Bitmap.createBitmap(sbmp.getWidth(), sbmp.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, sbmp.getWidth(), sbmp.getHeight());

        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(Color.parseColor("#BAB399"));
        canvas.drawCircle(sbmp.getWidth() / 2 + 0.7f, sbmp.getHeight() / 2 + 0.7f, sbmp.getWidth() / 2 + 0.1f, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(sbmp, rect, rect, paint);

        return output;
    }



    /**
     * Hides the already popped up keyboard from the screen.
     *
     * @param context
     */
    public static void hideKeyboard(Context context) {
        try {
            // use application level context to avoid unnecessary leaks.
            InputMethodManager inputManager = (InputMethodManager) context.getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(((Activity) context).getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (Exception e) {
            Log.e(TAG, "Sigh, cant even hide keyboard " + e.getMessage());
        }
    }

    /**
     * Checks if the build version passed as the parameter is
     * lower than the current build version.
     *
     * @param buildVersion One of the values from {@link android.os.Build.VERSION_CODES}
     * @return
     */
    public static boolean isBuildBelow(int buildVersion) {
        if (Build.VERSION.SDK_INT < buildVersion) return true;
        else return false;
    }


    public static String capitalizeString(String string, int start, int offset) {
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        String formattedString = string.substring(start, offset).toUpperCase() + string.substring(offset, string.length());
        return formattedString;
    }
    public static void showDatePickerDialog(Context context, TextInputEditText textInputEditText)
    {
        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);
        // date picker dialog
        DatePickerDialog picker = new DatePickerDialog(context,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        textInputEditText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, year, month, day);
        picker.show();

    }
    public static void showTimePickerDialog(Context context,TextInputEditText textInputEditText)
    {
        final Calendar cldr = Calendar.getInstance();
        int hour = cldr.get(Calendar.HOUR_OF_DAY);
        int minutes = cldr.get(Calendar.MINUTE);
        // time picker dialog
       TimePickerDialog picker = new TimePickerDialog(context,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                        textInputEditText.setText(sHour + ":" + sMinute);
                    }
                }, hour, minutes, true);
        picker.show();
    }
}