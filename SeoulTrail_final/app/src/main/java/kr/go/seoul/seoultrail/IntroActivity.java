package kr.go.seoul.seoultrail;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.util.Log;

import com.nethru.android.applogging.NethruAppLoggingException;
import com.nethru.android.applogging.WLAppTrackLogging;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import kr.go.seoul.seoultrail.Common.CustomProgressDialog;
import kr.go.seoul.seoultrail.Common.DBHelper;
import kr.go.seoul.seoultrail.Common.FontUtils;
import kr.go.seoul.seoultrail.Common.PublicDefine;
import kr.go.seoul.seoultrail.Common.StampLocation;

/**
 * Created by ntsys on 2016-10-04.
 */
public class IntroActivity extends BaseActivity {

    private CustomProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        try {
            WLAppTrackLogging.startLogging(this, PublicDefine.appKey, PublicDefine.appLoggingUrl);
            WLAppTrackLogging.logging(this, PublicDefine.appLoggingActionKey + "APP_START");
        } catch (NethruAppLoggingException e) {
            Log.e("NTsys", "AppTrackLogging ?????? ??????");
        } catch (Exception e) {
            Log.e("NTsys", "AppTrackLogging ?????? ??????");
        }

        loading = CustomProgressDialog.show(this, "", "");
        loading.setCancelable(false);
        loading.show();

        if (Build.VERSION.SDK_INT >= 23) {
            checkPermission(Manifest.permission.ACCESS_FINE_LOCATION);
        } else {
            Handler h = new Handler();
            h.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (loading != null && loading.isShowing()) {
                        loading.cancel();
                    }
                    startApp();
                }
            }, 3000);
        }
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        FontUtils.getInstance(this).setGlobalFont(getWindow().getDecorView());
    }

    private void startApp() {
        new ProcessNetworkThread().execute(null, null, null);
    }

    public static final int per1 = 1;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void checkPermission(String checkPermission) {
        int requestCode = 0;
        String message = "";
        if (checkPermission.equals(Manifest.permission.ACCESS_FINE_LOCATION)) {
            requestCode = per1;
            message = "???????????? ??????";
        }

        if (ContextCompat.checkSelfPermission(IntroActivity.this,
                checkPermission)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(IntroActivity.this,
                    checkPermission)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                final int finalRequestCode = requestCode;
                builder.setMessage(FontUtils.getInstance(this).typeface("??????????????? v6.0?????? ????????? ?????? ??????????????? ???????????????.\n??? ?????? ?????? " + message + " ??????\n????????? ?????? ???????????? ????????????."))
                        .setCancelable(false)

                        .setNegativeButton("?????? ??????", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finishApp();
                            }
                        });
                builder.setPositiveButton("??? ??????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        try {
                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                                    .setData(Uri.parse("package:" + getPackageName()));
                            startActivity(intent);
                            finish();
                        } catch (ActivityNotFoundException e) {
                            Intent intent = new Intent(Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS);
                            startActivity(intent);
                            finish();
                        }

                    }
                });
                final AlertDialog alert = builder.create();
                final Typeface mTypeface = Typeface.createFromAsset(this.getAssets(), "arita_bold.ttf");
                alert.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialogInterface) {
                        alert.getButton(Dialog.BUTTON_POSITIVE).setTypeface(mTypeface);
                        alert.getButton(Dialog.BUTTON_NEGATIVE).setTypeface(mTypeface);
                    }
                });
                alert.show();
            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(IntroActivity.this,
                        new String[]{checkPermission},
                        requestCode);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            if (checkPermission.equals(Manifest.permission.ACCESS_FINE_LOCATION)) {
                if (loading != null && loading.isShowing()) {
                    loading.cancel();
                }
                startApp();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case per1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (loading != null && loading.isShowing()) {
                        loading.cancel();
                    }
                    startApp();
                } else {
                    finishApp();
                }
                break;
            }

        }

    }

    public void finishApp() {
        AlertDialog.Builder ad = new AlertDialog.Builder(IntroActivity.this);
        ad.setTitle(R.string.app_name)        // ?????? ??????
                .setMessage(FontUtils.getInstance(this).typeface("??????????????? v6.0?????? ????????? ?????? ??????????????? ???????????????.\n?????? ????????? ???????????? ??????\nAPP???  ???????????????."))        // ????????? ??????
                .setCancelable(false)        // ?????? ?????? ????????? ?????? ?????? ??????
                .setPositiveButton("??????", new DialogInterface.OnClickListener() {
                    // ?????? ?????? ????????? ??????
                    public void onClick(DialogInterface dialog, int whichButton) {
                        if (loading != null && loading.isShowing()) {
                            loading.cancel();
                        }
                        dialog.dismiss();
                        finish();
                    }
                });
        final AlertDialog dialog = ad.create();    // ????????? ?????? ??????
        final Typeface mTypeface = Typeface.createFromAsset(this.getAssets(), "arita_bold.ttf");
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                dialog.getButton(Dialog.BUTTON_POSITIVE).setTypeface(mTypeface);
            }
        });
        dialog.show();
    }

    public class ProcessNetworkThread extends AsyncTask<Void, Void, String> {

        protected String doInBackground(Void... arg0) {
            // TODO Auto-generated method stub
            String content = executeClient();
            return content;
        }

        protected void onPostExecute(String result) {
            ResultData(result);
        }

        // ?????? ???????????? ??????
        public String executeClient() {
            HttpResponse response = null;

            // ?????? HttpClient ?????? ??????
            HttpClient client = new DefaultHttpClient();

            // ?????? ?????? ?????? ??????, ?????? ???????????? ??????
            HttpParams params = client.getParams();
            HttpConnectionParams.setConnectionTimeout(params, 5000);
            HttpConnectionParams.setSoTimeout(params, 5000);

            HttpGet httpGet = new HttpGet("http://map.seoul.go.kr/smgis/apps/theme.do?cmd=getContentsList&page_no=1&page_size=999&key=" + PublicDefine.serviceSmgisKey + "&coord_x=127.0245909&coord_y=37.5669845&distance=200000&search_type=0&search_name=&theme_id=100211&subcate_id=100211,17");

            try {
                response = client.execute(httpGet);
                String resultJson = EntityUtils.toString(response.getEntity(), "UTF-8");
                // insert ?????? ?????? ?????????
                Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();

                return resultJson;

            } catch (ClientProtocolException e) {
                Log.e("NTsys", "intro1 ?????? ?????? ?????? ??????");
                return "";
            } catch (IOException e) {
                Log.e("NTsys", "intro2 ?????? ?????? ?????? ??????");
                return "";
            }
        }

    }

    private void ResultData(String result) {
        String main = result.toString();

        try {
            JSONObject jsonMain = new JSONObject(main);
            JSONArray jsonBody = jsonMain.getJSONArray("body");
            String jsonCOT_CONTS_ID;
            String jsonCOT_COORD_X;
            String jsonCOT_COORD_Y;
            String jsonCOT_CONTS_NAME;

            for (int i = jsonBody.length() - 1; i >= 0; i--) {
                jsonCOT_CONTS_ID = jsonBody.getJSONObject(i).getString("COT_CONTS_ID");
                jsonCOT_COORD_Y = jsonBody.getJSONObject(i).getString("COT_COORD_Y");       //lat
                jsonCOT_COORD_X = jsonBody.getJSONObject(i).getString("COT_COORD_X");       //lng
                jsonCOT_CONTS_NAME = Html.fromHtml(jsonBody.getJSONObject(i).getString("COT_CONTS_NAME")).toString();
                DBHelper.getInstance(this).updateLocation(new StampLocation(jsonCOT_CONTS_ID, Integer.parseInt(jsonCOT_CONTS_ID.substring(4, 6)), 0
                        , jsonCOT_COORD_Y, jsonCOT_COORD_X, jsonCOT_CONTS_NAME));
                if (jsonCOT_CONTS_ID.equals("gil_01018")) {
                    DBHelper.getInstance(this).updateLocation(new StampLocation("gil_02010", 02, 0
                            , jsonCOT_COORD_Y, jsonCOT_COORD_X, jsonCOT_CONTS_NAME));
                }
            }
            DBHelper.getInstance(this).sortAndUpdateStampNo();
        } catch (Exception e) {
        } finally {
            Message msg = new Message();
            // modify ??????????????? ?????? ?????? ????????????.

            appStartHandle.sendMessageDelayed(msg, 0);
        }
    }
    // modify ?????? ?????? ????????? ?????? ????????? ????????? ?????? ?????????2 ????????? ! ??????????????? ?????????????????? ?????? ????????????.

    public Handler appStartHandle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            if (!lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(IntroActivity.this);
                alertDialogBuilder.setMessage(FontUtils.getInstance(IntroActivity.this).typeface("GPS??? ????????? ?????? ?????????.\n?????????????????? ????????? ???????????????????"));
                alertDialogBuilder.setPositiveButton("??????", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    }
                });
                alertDialogBuilder.setNegativeButton("??????", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(intent);
                        dialog.dismiss();
                        finish();
                    }
                });
                final AlertDialog alertDialog = alertDialogBuilder.create();
                final Typeface mTypeface = Typeface.createFromAsset(IntroActivity.this.getAssets(), "arita_bold.ttf");
                alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialogInterface) {
                        alertDialog.getButton(Dialog.BUTTON_POSITIVE).setTypeface(mTypeface);
                        alertDialog.getButton(Dialog.BUTTON_NEGATIVE).setTypeface(mTypeface);
                    }
                });
                alertDialog.show();
            } else {
                Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        }
    };

}
