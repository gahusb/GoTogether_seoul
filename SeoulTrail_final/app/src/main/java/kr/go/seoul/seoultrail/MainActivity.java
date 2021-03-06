package kr.go.seoul.seoultrail;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TabActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.text.Html;
import android.text.SpannableString;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nethru.android.applogging.NethruAppLoggingException;
import com.nethru.android.applogging.WLAppTrackLogging;
import com.nhn.android.maps.maplib.NGeoPoint;
import com.nhn.android.maps.overlay.NMapPOIitem;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;

import kr.go.seoul.seoultrail.Common.CustomProgressDialog;
import kr.go.seoul.seoultrail.Common.DBHelper;
import kr.go.seoul.seoultrail.Common.FontUtils;
import kr.go.seoul.seoultrail.Common.PublicDefine;
import kr.go.seoul.seoultrail.Common.StampLocation;
import kr.go.seoul.seoultrail.GPS.GPSProvider;

<<<<<<< HEAD:SeoulTrail_final/app/src/main/java/kr/go/seoul/seoultrail/MainActivity.java
import static android.view.Gravity.BOTTOM;
import static android.view.Gravity.CENTER;
import static android.view.Gravity.LEFT;
import static android.view.Gravity.TOP;

=======
>>>>>>> ca4c9829395aed517e854978b33530427e00b35b:SeoulTrail/app/src/main/java/kr/go/seoul/seoultrail/MainActivity.java
public class MainActivity extends TabActivity {

    private TabHost tabHost;
    private ImageView header_image;

    final static String MAIN = "1";
    final static String COURSE = "2";
    final static String COMMUNITY = "3";
    final static String CAMERA = "4";

    private String lastTabTag = "1";     // ?????? ???????????? ?????? ?????? ?????? ?????? ?????? X

    private WebView notiWebView;
    private ArrayList<String> notiList = new ArrayList<>();
    private int notiIDX = -1;
    int cnt = 0;

<<<<<<< HEAD:SeoulTrail_final/app/src/main/java/kr/go/seoul/seoultrail/MainActivity.java
    boolean tf = false;

=======
    // ?????????????????? 17.10.24 ?????? ??????
//    private CheckBox btnMore;
//    private LinearLayout gpsBtnLayout;
//    private TextView checkMyLocation;
//    private TextView checkNMapMyLocation;
>>>>>>> ca4c9829395aed517e854978b33530427e00b35b:SeoulTrail/app/src/main/java/kr/go/seoul/seoultrail/MainActivity.java

    private GPSProvider gps;

    private Boolean stampOn = false;
    private Boolean completeAllCourse = false;
    private String lastStampInfo = "";

    private static CustomProgressDialog dialogLoading;
    private static AlertDialog alert = null;

<<<<<<< HEAD:SeoulTrail_final/app/src/main/java/kr/go/seoul/seoultrail/MainActivity.java
    private int value;
    // modify ?????? ????????? ?????????????????? ?????? ???????????? ?????? ???????????????

    public void settingText(int value) {

        TextView top_text = (TextView) findViewById(R.id.main_text_image1);
        TextView mid_text = (TextView) findViewById(R.id.main_text_image2);
        TextView bottom_text = (TextView) findViewById(R.id.main_text_image3);
        ImageView im = (ImageView) findViewById(R.id.header_image);
        LinearLayout li_text = (LinearLayout) findViewById(R.id.nav);
        //ImageView im1 = (ImageView)findViewById(R.id.middle_image);

        this.value = value;

        switch (value) {
            // modify 1. ????????? ??????
            case 1:
                // im.getLayoutParams().height = big_image_size;
                //      im1.setVisibility(View.GONE);
                im.requestLayout();
                li_text.setBackgroundResource(R.drawable.bg04_guide_nav);
                im.setImageResource(R.drawable.bg04_guide_header);
                top_text.setVisibility(View.GONE);
                top_text.setText("????????? ????????????????");
                top_text.setVisibility(View.VISIBLE);
                top_text.setGravity(Gravity.TOP | LEFT);
                top_text.setPadding(30, 30, 0, 0);

                mid_text.setVisibility(View.GONE);
                mid_text.setText("\"????????? ?????????\"");
                mid_text.setVisibility(View.VISIBLE);
                mid_text.setGravity(Gravity.CENTER | LEFT);

                bottom_text.setVisibility(View.GONE);
                bottom_text.setText("??????????????? ???????????? ????????? ????????? ???????????????. ");
                bottom_text.setVisibility(View.VISIBLE);
                bottom_text.setGravity(Gravity.BOTTOM | LEFT);
                bottom_text.setTextSize(15);
                bottom_text.setPadding(30, 0, 0, 60);

                break;

            // modify ???????????????? ??????
            case 2:
                //    im1.setVisibility(View.GONE);
                li_text.setBackgroundResource(R.drawable.bg05_history_nav);
                im.setImageResource(R.drawable.bg05_history_header);
                top_text.setVisibility(View.GONE);
                top_text.setText("????????? ???????????????");
                top_text.setVisibility(View.VISIBLE);
                top_text.setGravity(Gravity.TOP | LEFT);
                top_text.setPadding(30, 30, 0, 0);

                mid_text.setVisibility(View.GONE);
                mid_text.setText("\"?????? ?????????\"");
                mid_text.setVisibility(View.VISIBLE);
                mid_text.setGravity(Gravity.CENTER | LEFT);

                bottom_text.setVisibility(View.GONE);
                bottom_text.setText("????????? ????????? ????????? ?????? ???????????? ??? ???");
                bottom_text.setVisibility(View.VISIBLE);
                bottom_text.setGravity(Gravity.BOTTOM | LEFT);
                bottom_text.setTextSize(15);
                bottom_text.setPadding(30, 0, 0, 60);

                break;

            //modify ?????? ??????
            case 3:
                //im1.setVisibility(View.GONE);
                li_text.setBackgroundResource(R.drawable.bg06_video_nav);
                im.setImageResource(R.drawable.bg06_video_header);
                top_text.setVisibility(View.GONE);
                top_text.setText("???????????????");
                top_text.setVisibility(View.VISIBLE);
                top_text.setGravity(Gravity.LEFT | TOP);
                top_text.setPadding(30, 30, 0, 0);

                mid_text.setVisibility(View.GONE);
                mid_text.setText("\"????????? ?????????\"");
                mid_text.setVisibility(View.VISIBLE);
                mid_text.setGravity(Gravity.LEFT | CENTER);
                mid_text.setPadding(30, 0, 0, 30);


                bottom_text.setVisibility(View.GONE);
                bottom_text.setText("???????????? ???????????? ???????????????");
                bottom_text.setVisibility(View.VISIBLE);
                bottom_text.setGravity(Gravity.BOTTOM | LEFT);
                bottom_text.setTextSize(15);
                bottom_text.setPadding(30, 0, 0, 60);

                break;

            // ????????? ??????
            case 4:
                //im1.setVisibility(View.GONE);
                li_text.setBackgroundResource(R.drawable.bg07_camera_nav);
                im.setImageResource(R.drawable.bg07_camera_header);
                top_text.setVisibility(View.GONE);
                top_text.setText("???????????? ????????????");
                top_text.setVisibility(View.VISIBLE);
                top_text.setGravity(Gravity.TOP | LEFT);
                top_text.setPadding(30, 30, 0, 0);

                mid_text.setVisibility(View.GONE);
                mid_text.setText("\"????????? ?????????\"");
                mid_text.setVisibility(View.VISIBLE);
                mid_text.setGravity(Gravity.CENTER | LEFT);

                bottom_text.setVisibility(View.GONE);
                bottom_text.setText("???????????? ?????? ????????? ????????? ???????????? ???????????????.");
                bottom_text.setVisibility(View.VISIBLE);
                bottom_text.setGravity(Gravity.BOTTOM | LEFT);
                bottom_text.setTextSize(15);
                bottom_text.setPadding(30, 0, 0, 60);

                break;

            //??????????????? ?????????????????????
            case 5:
                //im1.setVisibility(View.GONE);
                li_text.setBackgroundResource(R.drawable.bg12_cafe_nav);
                im.setImageResource(R.drawable.bg12_cafe_header);
                top_text.setVisibility(View.GONE);
                top_text.setText("?????? ???????????? ?????????");
                top_text.setVisibility(View.VISIBLE);
                top_text.setGravity(Gravity.TOP | LEFT);
                top_text.setPadding(30, 30, 0, 0);

                mid_text.setVisibility(View.GONE);
                mid_text.setText("\"??????\"");
                mid_text.setVisibility(View.VISIBLE);
                mid_text.setGravity(Gravity.CENTER | LEFT);

                bottom_text.setVisibility(View.GONE);
                bottom_text.setText("Daum ?????????????????? ????????? ????????? ?????? ?????????");
                bottom_text.setVisibility(View.VISIBLE);
                bottom_text.setGravity(Gravity.BOTTOM | LEFT);
                bottom_text.setTextSize(15);
                bottom_text.setPadding(30, 0, 0, 60);

                break;

            //????????????
            case 6:
                //im1.setVisibility(View.GONE);
                li_text.setBackgroundResource(R.drawable.bg08_weather_nav);
                im.setImageResource(R.drawable.bg08_weather_header);
                top_text.setVisibility(View.GONE);
                top_text.setText("??????????????? ???????????????");
                top_text.setVisibility(View.VISIBLE);
                top_text.setGravity(Gravity.TOP | LEFT);
                top_text.setPadding(30, 30, 0, 0);

                mid_text.setVisibility(View.GONE);
                mid_text.setText("\"????????? ??????\"");
                mid_text.setVisibility(View.VISIBLE);
                mid_text.setGravity(Gravity.CENTER | LEFT);

                bottom_text.setVisibility(View.GONE);
                bottom_text.setText("???????????? ????????? ???????????? ??????????????????.");
                bottom_text.setVisibility(View.VISIBLE);
                bottom_text.setGravity(Gravity.BOTTOM | LEFT);
                bottom_text.setTextSize(15);
                bottom_text.setPadding(30, 0, 0, 60);

                break;

            //????????????
            case 7:
                //im1.setVisibility(View.GONE);
                li_text.setBackgroundResource(R.drawable.bg09_event_nav);
                im.setImageResource(R.drawable.bg09_event_header);
                top_text.setVisibility(View.GONE);
                top_text.setText("?????? ????????????");
                top_text.setVisibility(View.VISIBLE);
                top_text.setGravity(Gravity.TOP | LEFT);
                top_text.setPadding(30, 30, 0, 0);

                mid_text.setVisibility(View.GONE);
                mid_text.setText("\"????????? ??????\"");
                mid_text.setVisibility(View.VISIBLE);
                mid_text.setGravity(Gravity.CENTER | LEFT);

                bottom_text.setVisibility(View.GONE);
                bottom_text.setText("?????? ??????????????? ????????? ?????? ?????????. ?????? ?????? ??????.");
                bottom_text.setVisibility(View.VISIBLE);
                bottom_text.setGravity(Gravity.BOTTOM | LEFT);
                bottom_text.setTextSize(15);
                bottom_text.setPadding(30, 0, 0, 60);

                break;

            //FAQ
            case 8:
                //im1.setVisibility(View.GONE);
                li_text.setBackgroundResource(R.drawable.bg10_faq_nav);
                im.setImageResource(R.drawable.bg10_faq_header);
                top_text.setVisibility(View.GONE);
                top_text.setText("????????????");
                top_text.setVisibility(View.VISIBLE);
                top_text.setGravity(Gravity.TOP | LEFT);
                top_text.setPadding(30, 30, 0, 0);

                mid_text.setVisibility(View.GONE);
                mid_text.setText("\"?????? ?????????\"");
                mid_text.setVisibility(View.VISIBLE);
                mid_text.setGravity(Gravity.CENTER | LEFT);

                bottom_text.setVisibility(View.GONE);
                bottom_text.setText("???????????? ?????? ???????????? ?????????,????????? ?????? ?????? ?????????");
                bottom_text.setVisibility(View.VISIBLE);
                bottom_text.setGravity(Gravity.BOTTOM | LEFT);
                bottom_text.setTextSize(15);
                bottom_text.setPadding(30, 0, 0, 60);

                break;

            //?????? ?????? ??????
            case 9:
                //im1.setVisibility(View.GONE);
                li_text.setBackgroundResource(R.drawable.bg11_etc_nav);
                im.setImageResource(R.drawable.bg11_etc_header);
                top_text.setVisibility(View.GONE);
                top_text.setText("????????? ?????? ??????");
                top_text.setVisibility(View.VISIBLE);
                top_text.setGravity(Gravity.TOP | LEFT);
                top_text.setPadding(30, 30, 0, 0);

                mid_text.setVisibility(View.GONE);
                mid_text.setText("\"????????? ?????? ????????????\"");
                mid_text.setTextSize(35);

                mid_text.setVisibility(View.VISIBLE);
                mid_text.setGravity(Gravity.CENTER | LEFT);

                bottom_text.setVisibility(View.GONE);
                bottom_text.setText("????????? ?????? ??????");
                bottom_text.setVisibility(View.VISIBLE);
                bottom_text.setGravity(Gravity.BOTTOM | LEFT);
                bottom_text.setTextSize(15);
                bottom_text.setPadding(30, 0, 0, 60);

                break;
        }
    }

    public void settingTab(int value) {

        TextView top_text = (TextView) findViewById(R.id.main_text_image1);
        TextView mid_text = (TextView) findViewById(R.id.main_text_image2);
        TextView bottom_text = (TextView) findViewById(R.id.main_text_image3);
        ImageView im = (ImageView) findViewById(R.id.header_image);
        LinearLayout li_tab = (LinearLayout) findViewById(R.id.nav);
        //ImageView im1 = (ImageView)findViewById(R.id.middle_image);
        this.value = value;

        switch (value) {
            // modify ?????? ?????? ????????? ??????( case0~ 3????????? ????????? ???????????????)
            //  1?????? ??? ?????? ??????
            case 1:
                //header_back.setImageResource(R.drawable.bg);
                //      im1.setVisibility(View.VISIBLE);
                li_tab.setBackgroundResource(R.drawable.bg01_main_nav);
                im.setImageResource(R.drawable.bg01_main_header);
                top_text.setVisibility(View.GONE);
                top_text.setText("????????? ????????? ?????? ??????");
                top_text.setVisibility(View.VISIBLE);
                top_text.setGravity(Gravity.CENTER);
                Typeface face1 = Typeface.createFromAsset(getAssets(), "arita_bold.ttf");
                top_text.setTypeface(face1);
                top_text.setTextSize(17);
                top_text.setPadding(30, 0, 0, 40);

                //modify ??????????????? ????????? ??????

                Typeface face = Typeface.createFromAsset(getAssets(), "arita_bold.ttf");
                mid_text.setGravity(Gravity.CENTER | BOTTOM);
                mid_text.setTypeface(face);
                mid_text.setTextSize(40);
                mid_text.setPadding(30, 0, 0, 40);
                mid_text.setText("\"?????? ?????????\"");

                bottom_text.setVisibility(View.GONE);

                break;

            // modify ?????? ?????? ????????? ??????
            //2?????? ???????????? ??? ??????
            case 2:
                //    im1.setVisibility(View.GONE);

                li_tab.setBackgroundResource(R.drawable.bg02_course_nav);
                im.setImageResource(R.drawable.bg02_course_header);
                top_text.setVisibility(View.GONE);
                top_text.setText("8?????? ??????");
                top_text.setVisibility(View.VISIBLE);
                top_text.setGravity(Gravity.TOP | LEFT);
                top_text.setPadding(30, 30, 0, 0);

                mid_text.setVisibility(View.GONE);
                mid_text.setText("\"?????? ?????????\"");
                mid_text.setVisibility(View.VISIBLE);
                mid_text.setGravity(Gravity.CENTER | LEFT);

                bottom_text.setVisibility(View.GONE);
                bottom_text.setText("????????? ????????? ??? ??? ?????? ??? 157km??? ?????? ?????????");
                bottom_text.setVisibility(View.VISIBLE);
                bottom_text.setGravity(Gravity.BOTTOM | LEFT);
                bottom_text.setTextSize(15);
                bottom_text.setPadding(30, 0, 0, 60);
                break;

            // 3?????? ???????????? ??? ??????
            case 3:
                //  im1.setVisibility(View.GONE);
                li_tab.setBackgroundResource(R.drawable.bg03_community_nav);
                im.setImageResource(R.drawable.bg03_community_header);
                top_text.setVisibility(View.GONE);
                top_text.setText("???????????????");
                top_text.setVisibility(View.VISIBLE);
                top_text.setGravity(Gravity.TOP | LEFT);
                top_text.setPadding(30, 30, 0, 0);

                mid_text.setVisibility(View.GONE);
                mid_text.setText("\"?????? ????????? ??????\"");
                mid_text.setVisibility(View.VISIBLE);
                mid_text.setGravity(Gravity.CENTER | LEFT);

                bottom_text.setVisibility(View.GONE);
                bottom_text.setText("??????????????? SNS??? ?????? ???????????? ??????  ??????");
                bottom_text.setVisibility(View.VISIBLE);
                bottom_text.setGravity(Gravity.BOTTOM | LEFT);
                bottom_text.setTextSize(15);
                bottom_text.setPadding(30, 0, 0, 60);
                break;

            // 4?????? ????????? ??? ??????
            case 4:
                //im1.setVisibility(View.GONE);
                break;

            default:
                break;
        }
    }
=======
>>>>>>> ca4c9829395aed517e854978b33530427e00b35b:SeoulTrail/app/src/main/java/kr/go/seoul/seoultrail/MainActivity.java


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PublicDefine.mainActivity = this;

        WebView wb = (WebView)findViewById(R.id.webview);

        WebSettings set = wb.getSettings();

        set.setBuiltInZoomControls(true); // ???????????? ????????????

        //set.setUseWideViewPort(true); // wide viewport??? ??????
        /*
        findViewById(R.id.checkBox2).setOnClickListener(new CheckBox.OnClickListener() {
            String data = "";
            long time = System.currentTimeMillis();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
            date = sdf.format(new Date(time));
            WebView wb = (WebView)findViewById(R.id.webview);
            WebSettings set = wb.getSettings();

            int curtime = (int)time/1000;

            public void onClick(View v) {

                if(curtime> 86400) {
                    set.setDomStorageEnabled(true);
                }else{
                    set.setDomStorageEnabled(false);
                }
            }
        });
        */
        initView();
        /*modify ?????? ????????? initGPS() ?????? ??????????????? ?????????????????? ?????? ????????? ???????????? ?????????
          modify ???????????? ??????????????? ?????? GPS??? ?????? ???????????? ??? ??? ??????. ?????? ????????? ????????? ??????
        */
        initGPS();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);

        FontUtils.getInstance(this).setGlobalFont(getWindow().getDecorView());
    }

    @Override
    protected void onResume() {
        super.onResume();
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        if (gps != null) {
            gps.startGetMyLoation();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @Override
    protected void onDestroy() {
        if (gps != null) {
            gps.removeUpdate();
        }
        super.onDestroy();
    }

    private void initGPS() {
        LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        gps = new GPSProvider(MainActivity.this, mlocManager); //???????????? ??????
        gps.setMlocListener(mlocListener);
    }

    LocationListener mlocListener = new LocationListener() { // ????????? ????????? ??????????????? ????????? Event??? ?????? ???????????? ?????????????????? ??????.
        public void onLocationChanged(Location myLocation) {  // ???????????? ????????? ??????????????? ?????? ??????????????? ?????????
            checkLocation(myLocation.getLatitude(), myLocation.getLongitude());
        }

        public void onProviderDisabled(String provider) {
        }

        public void onProviderEnabled(String provider) {
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
    };

    private void checkLocation(Double latitude, Double longitude) {
        float[] t;
        int completeCourseNo = 0;
        ArrayList<StampLocation> locationList = DBHelper.getInstance(MainActivity.this).getNoCompleteStampLocation();
        for (int i = 0; i < locationList.size(); i++) {
            t = new float[2];
            Location.distanceBetween(Double.parseDouble(locationList.get(i).getCOT_COORD_Y()), Double.parseDouble(locationList.get(i).getCOT_COORD_X())
                    , latitude, longitude, t);
            if (t[0] <= 100) {
                stampOn = true;
                DBHelper.getInstance(MainActivity.this).setCompleteStamp(locationList.get(i));
                completeAllCourse = DBHelper.getInstance(MainActivity.this).getCompleteAllCourse(locationList.get(i));
                completeCourseNo = locationList.get(i).getCourseNo();
            }
        }
        if (stampOn) {
            String msg = "";
            if (completeAllCourse) {
                setInformationTourInfo();
                completeAllCourse = false;
                msg = "??????????????? ?????????????????????.";
            } else {
                setPagerToCompleteStamp(completeCourseNo + -1);
                msg = "???????????? ?????? ???????????????.";
            }
            sendNotification(msg);
            stampOn = false;
        }
    }

    private void sendNotification(String msg) {
        String result = "";
        int id = (int) System.currentTimeMillis();
        try {
            result = URLDecoder.decode(msg, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            Log.e("NTsys", "????????? ?????? ??????");
        }
        PendingIntent contentIntent = PendingIntent.getActivity(this, id,
                new Intent(this, IntroActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP),
                PendingIntent.FLAG_UPDATE_CURRENT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(getResources().getString(R.string.app_name))
                .setContentText(result)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(contentIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(id, notificationBuilder.build());
    }

    public void setCheckNMapMyLocation(NGeoPoint myLocation) {
        checkLocation(myLocation.getLatitude(), myLocation.getLongitude());
    }

    private void initView() {
        header_image = (ImageView) findViewById(R.id.header_image);

        setupTabHost();

        tabHost.getTabWidget().setDividerDrawable(null);
        setupTab(MAIN);
        setupTab(COURSE);
        setupTab(COMMUNITY);
        setupTab(CAMERA);
        tabHost.setCurrentTab(0);           // MAIN ????????? ???????????? ??????????????????
        // ????????? setCurrentTab??? ?????? ????????? ?????????????????? ????????? ??? ?????? ???????????? ???????????? ?????????.


        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tag) {
                String loggingAction = "";

                if (tag.equals(MAIN)) {
                    //header_image.setImageResource(R.drawable.bg01_main);
                    lastTabTag = MAIN;
                    loggingAction = "MAIN";
<<<<<<< HEAD:SeoulTrail_final/app/src/main/java/kr/go/seoul/seoultrail/MainActivity.java
                    //increaseHeaderImage();
                    settingTab(1);
                    if(tf == true) {
                        PublicDefine.menuActivty.check();
                        tf = false;
                    }

=======
//                    increaseHeaderImage();
>>>>>>> ca4c9829395aed517e854978b33530427e00b35b:SeoulTrail/app/src/main/java/kr/go/seoul/seoultrail/MainActivity.java
                } else if (tag.equals(COURSE)) {
                    //header_image.setImageResource(R.drawable.bg01_course);
                    lastTabTag = COURSE;
                    loggingAction = "COURSE";
<<<<<<< HEAD:SeoulTrail_final/app/src/main/java/kr/go/seoul/seoultrail/MainActivity.java
                    //reduceHeaderImage()
                    if(tf == true) {
                        PublicDefine.menuActivty.check();
                        tf = false;
                    }

                } else if (tag.equals(COMMUNITY)) {

                    settingTab(3);
                    //header_image.setImageResource(R.drawable.bg01_community);
                    lastTabTag = COMMUNITY;
                    loggingAction = "COMMUNITY";
                    //reduceHeaderImage();
                    if(tf == true) {
                        PublicDefine.menuActivty.check();
                        tf = false;
                    }
=======
//                    increaseHeaderImage();
                } else if (tag.equals(COMMUNITY)) {
                    //header_image.setImageResource(R.drawable.bg01_community);
                    lastTabTag = COMMUNITY;
                    loggingAction = "COMMUNITY";
//                    increaseHeaderImage();
>>>>>>> ca4c9829395aed517e854978b33530427e00b35b:SeoulTrail/app/src/main/java/kr/go/seoul/seoultrail/MainActivity.java
                } else if (tag.equals(CAMERA)) {
                    lastTabTag = CAMERA;
                    loggingAction = "CAMERA";
<<<<<<< HEAD:SeoulTrail_final/app/src/main/java/kr/go/seoul/seoultrail/MainActivity.java
                    Main_Move();
//                    increaseHeaderImage();

=======
                    tabHost.clearAllTabs();
                    initView();
//                    increaseHeaderImage();
>>>>>>> ca4c9829395aed517e854978b33530427e00b35b:SeoulTrail/app/src/main/java/kr/go/seoul/seoultrail/MainActivity.java
                }

                try {
                    WLAppTrackLogging.logging(MainActivity.this, PublicDefine.appLoggingActionKey + loggingAction);
                } catch (NethruAppLoggingException e) {
                    Log.e("NTsys", "AppTrackLogging ?????? ??????");
                } catch (Exception e) {
                    Log.e("NTsys", "AppTrackLogging ?????? ??????");
                }
            }
        });

        notiWebView = (WebView) findViewById(R.id.webview);
        if (notiList != null) {
            notiList.clear();
        } else {
            notiList = new ArrayList<>();
        }
        new ProcessNetworkImportantNoticeList().execute(null, null, null);
    }

    private void reduceHeaderImage() {
        int temp;
        int big_image_size = 450;
        int small_image_size = 300;

        header_image.getLayoutParams().height = small_image_size;
        header_image.requestLayout();
    }

    private void increaseHeaderImage() {
        int temp;
        int big_image_size = 500;
        int small_image_size = 300;

        temp = header_image.getLayoutParams().height;
        if(temp == small_image_size) {
            header_image.getLayoutParams().height = big_image_size;
            header_image.requestLayout();
        }
    }

    private void setupTabHost() {
        // xml resource?????? TabHost??? ??????????????? setup()??? ?????????????????????.
        tabHost = (TabHost) findViewById(android.R.id.tabhost);
        tabHost.setup();
    }

    private void setupTab(final String tag) {
        View tabview = createTabView(tabHost.getContext(), tag);

        // TabSpec??? ????????? ???????????? ???????????? ?????? ????????? ??? ?????????, TabHost??? newTabSpec???????????? ??????
        TabSpec setContent = tabHost.newTabSpec(tag).setIndicator(tabview);


        // ?????????????????? ??????????????? ????????? ???????????? ???????????? ????????????. ????????? MenuAcitivy??? ???????????????.
        if (tag.equals(MAIN)) {
            setContent.setContent(new Intent(this, Menu_Connection.class));
        }
        else if (tag.equals(COURSE)) {
            setContent.setContent(new Intent(this, Course.class));
        }
        else if (tag.equals(COMMUNITY)) {
            setContent.setContent(new Intent(this, Community.class));
        }
        else if (tag.equals(CAMERA)) {
            Intent intent = new Intent(this, CameraActivity.class);
<<<<<<< HEAD:SeoulTrail_final/app/src/main/java/kr/go/seoul/seoultrail/MainActivity.java
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
=======
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
>>>>>>> ca4c9829395aed517e854978b33530427e00b35b:SeoulTrail/app/src/main/java/kr/go/seoul/seoultrail/MainActivity.java
            setContent.setContent(intent);
        }
        tabHost.addTab(setContent);

    }


    private static View createTabView(final Context context, final String text) {
        // layoutinflater??? ????????? xml ???????????? ????????? tabview??? ??????
        View view = LayoutInflater.from(context).inflate(R.layout.tab_widget_footer_custom, null);
        ImageView img;

        img = (ImageView) view.findViewById(R.id.tabs_image);
        if (text.equals(MAIN)) {
            img.setImageResource(R.drawable.btn01_main_on);
        } else if (text.equals(COURSE)) {
            img.setImageResource(R.drawable.btn02_course_on);
        } else if (text.equals(COMMUNITY)) {
            img.setImageResource(R.drawable.btn03_community_on);
        } else if (text.equals(CAMERA)) {
            img.setImageResource(R.drawable.btn04_camera_on);
        }
        return view;
    }

    public void setInformationTourInfo() {
        if (alert != null && alert.isShowing()) {
            alert.cancel();
            alert = null;
        }
        AlertDialog.Builder alert_confirm = new AlertDialog.Builder(this);
        alert_confirm.setMessage(FontUtils.getInstance(this).typeface("??????????????? ?????????????????????.\n?????? ????????? ??????????????? ?????????????????? ?????? ????????? ??????????????????.")).setCancelable(false).setPositiveButton("??????",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tabHost.setCurrentTab(1);
                        //PublicDefine.information.setPager(1);
                        // #2 ????????????????????? ????????? ?????? ????????? ???????????????.


                    }
                }).setNegativeButton("??????",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alert = alert_confirm.create();
        final Typeface mTypeface = Typeface.createFromAsset(this.getAssets(), "arita_bold.ttf");
        alert.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                alert.getButton(Dialog.BUTTON_POSITIVE).setTypeface(mTypeface);
                alert.getButton(Dialog.BUTTON_NEGATIVE).setTypeface(mTypeface);
            }
        });
        alert.show();

    }

    public void setPagerToCompleteStamp(final int courseNo) {
        if (alert != null && alert.isShowing()) {
            alert.cancel();
            alert = null;
        }
        AlertDialog.Builder alert_confirm = new AlertDialog.Builder(this);
        alert_confirm.setMessage(FontUtils.getInstance(this).typeface("???????????? ?????? ???????????????.\n????????? ??????????????? ?????????????????? ?????? ????????? ??????????????????.")).setCancelable(false).setPositiveButton("??????",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //tabHost.setCurrentTab(0);
                        PublicDefine.course.setPagerToStamp(courseNo);
                    }
                }).setNegativeButton("??????",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alert = alert_confirm.create();
        final Typeface mTypeface = Typeface.createFromAsset(this.getAssets(), "arita_bold.ttf");
        alert.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                alert.getButton(Dialog.BUTTON_POSITIVE).setTypeface(mTypeface);
                alert.getButton(Dialog.BUTTON_NEGATIVE).setTypeface(mTypeface);
            }
        });
        alert.show();
    }

    public void showProgressDialog() {
        if (dialogLoading == null) {
            dialogLoading = CustomProgressDialog.show(this, "", "");
            dialogLoading.setCancelable(false);
        }
        if (dialogLoading != null && dialogLoading.isShowing() == false) {
            dialogLoading.show();
        }
    }

    public void cancelProgressDialog() {
        if (dialogLoading != null && dialogLoading.isShowing() == true) {
            dialogLoading.cancel();
        }
    }

    public void showPointListItem(final ArrayList<String[]> cotCoordList, final View view) {
        if (alert != null && alert.isShowing()) {
            alert.cancel();
            alert = null;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setItems(new SpannableString[]{FontUtils.getInstance(this).typeface("???????????? ??????"), FontUtils.getInstance(this).typeface("????????? ??????")}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 1) {
                    PublicDefine.courseMapFragment.showPointList(cotCoordList);
                }
                PublicDefine.courseMapFragment.setShowPointPin();
                dialog.dismiss();
            }
        }).setNegativeButton("??????", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.dismiss();
                ((CheckBox) view).setChecked(false);
            }
        });
        builder.setCancelable(false);
        alert = builder.create();
        final Typeface mTypeface = Typeface.createFromAsset(this.getAssets(), "arita_bold.ttf");
        alert.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                alert.getButton(Dialog.BUTTON_POSITIVE).setTypeface(mTypeface);
                alert.getButton(Dialog.BUTTON_NEGATIVE).setTypeface(mTypeface);
            }
        });
        alert.show();
    }

    public void notiConfirm(View view) {
        findViewById(R.id.noti_webview).setVisibility(View.GONE);
    }

    public void notiDetail(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri uri = Uri.parse("http://gil.seoul.go.kr/m/sub/introduce/notice_view.jsp?" + notiList.get(notiIDX));
        intent.setData(uri);
        startActivity(intent);
    }


    public void showDetailInfo(NMapPOIitem item) {
        new ProcessNetworkPointDetailThread().execute(item.getTag().toString(), null, null);
    }


    public class ProcessNetworkImportantNoticeList extends AsyncTask<Void, Void, String> {
        protected String doInBackground(Void... arg0) {
            // TODO Auto-generated method stub
            String content = executeClient();
            return content;
        }

        protected void onPostExecute(String result) {
            String main = result.toString();

            try {
                JSONObject jsonMain = new JSONObject(main);
                JSONArray jsonList = jsonMain.getJSONArray("list");
                for (int i = 0; i < jsonList.length(); i++) {
                    notiList.add(jsonList.getJSONObject(i).getString("IDX"));
                }
            } catch (Exception e) {
            } finally {
                new ProcessNetworkNoticeList().execute(null, null, null);
            }
        }

        // ?????? ???????????? ??????
        public String executeClient() {
            HttpResponse response = null;
            ArrayList<NameValuePair> post = new ArrayList<NameValuePair>();

            // ?????? HttpClient ?????? ??????
            HttpClient client = new DefaultHttpClient();

            // ?????? ?????? ?????? ??????, ?????? ???????????? ??????
            HttpParams params = client.getParams();
            HttpConnectionParams.setConnectionTimeout(params, 30000);
            HttpConnectionParams.setSoTimeout(params, 30000);

            HttpGet httpGet = new HttpGet("https://mplatform.seoul.go.kr/api/dule/importantNoticeList.do");
            try {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(post, "UTF-8");

                response = client.execute(httpGet);
                String resultJson = EntityUtils.toString(response.getEntity(), "UTF-8");
                return resultJson;
            } catch (ClientProtocolException e) {
                Log.e("NTsys", "?????? ?????? ?????? ??????");
                return "";
            } catch (IOException e) {
                Log.e("NTsys", "?????? ?????? ?????? ??????");
                return "";
            }
        }
    }

    // TODO ?????? ?????? ???????????? ?????? ????????? ???????????? ???????????? ????????? ??????????????????

    public class ProcessNetworkNoticeList extends AsyncTask<Void, Void, String> {
        protected String doInBackground(Void... arg0) {
            // TODO Auto-generated method stub
            String content = executeClient();
            return content;
        }

        protected void onPostExecute(String result) {
            String main = result.toString();

            try {
                JSONObject jsonMain = new JSONObject(main);
                JSONArray jsonList = jsonMain.getJSONArray("list");
                for (int i = 0; i < jsonList.length(); i++) {
                    notiList.add(jsonList.getJSONObject(i).getString("IDX"));
                }
                Collections.sort(notiList);
            } catch (Exception e) {
            } finally {
                notiIDX = 0;
                if (notiList.size() > 0) {
                    notiIDX = notiList.size() - 1;
                }
                new ProcessNetworkNotiThread().execute(notiList.get(notiIDX));
            }
        }

        // ?????? ???????????? ??????
        public String executeClient() {
            HttpResponse response = null;
            ArrayList<NameValuePair> post = new ArrayList<NameValuePair>();

            // ?????? HttpClient ?????? ??????
            HttpClient client = new DefaultHttpClient();

            // ?????? ?????? ?????? ??????, ?????? ???????????? ??????
            HttpParams params = client.getParams();
            HttpConnectionParams.setConnectionTimeout(params, 30000);
            HttpConnectionParams.setSoTimeout(params, 30000);

            HttpGet httpGet = new HttpGet("https://mplatform.seoul.go.kr/api/dule/importantNoticeList.do");
            try {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(post, "UTF-8");

                response = client.execute(httpGet);
                String resultJson = EntityUtils.toString(response.getEntity(), "UTF-8");
                return resultJson;
            } catch (ClientProtocolException e) {
                Log.e("NTsys", "?????? ?????? ?????? ??????");
                return "";
            } catch (IOException e) {
                Log.e("NTsys", "?????? ?????? ?????? ??????");
                return "";
            }
        }
    }

    //TODO ???????????? ?????? ????????? ?????? ??????
    public class ProcessNetworkNotiThread extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String content = executeClient(strings);
            return content;
        }

        protected void onPostExecute(String result) {
            String main = result.toString();
            String noti = "<html xmlns=\"http://www.w3.org/1999/xhtml\"><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><title>Lorem Ipsum</title></head><bod>";
            try {
                JSONObject jsonMain = new JSONObject(main);
                JSONObject data = jsonMain.getJSONObject("data");
                noti = noti + data.getString("ORGN_CONTENTS");
            } catch (JSONException e) {
            }

            noti = noti + "</body></html>";

            notiWebView.loadData(noti, "text/html; charset=UTF-8", null);
        }

        // ?????? ???????????? ??????
        public String executeClient(String[] strings) {
            HttpResponse response = null;
            ArrayList<NameValuePair> post = new ArrayList<NameValuePair>();

            // ?????? HttpClient ?????? ??????
            HttpClient client = new DefaultHttpClient();

            // ?????? ?????? ?????? ??????, ?????? ???????????? ??????
            HttpParams params = client.getParams();
            HttpConnectionParams.setConnectionTimeout(params, 30000);
            HttpConnectionParams.setSoTimeout(params, 30000);

            HttpGet httpGet = new HttpGet("https://mplatform.seoul.go.kr/api/dule/noticeDetail.do?" + strings[0]);
            try {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(post, "UTF-8");

                response = client.execute(httpGet);
                String resultJson = EntityUtils.toString(response.getEntity(), "UTF-8");
                return resultJson;
            } catch (ClientProtocolException e) {
                Log.e("NTsys", "?????? ?????? ?????? ??????");
                return "";
            } catch (IOException e) {
                Log.e("NTsys", "?????? ?????? ?????? ??????");
                return "";
            }
        }

    }

    // TODO ???????????? ???????????? ????????? ?????? ?????? ??????

    public class ProcessNetworkPointDetailThread extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String content = executeClient(strings);
            return content;
        }

        protected void onPostExecute(String result) {
            resutData(result);
        }

        // ?????? ???????????? ??????
        public String executeClient(String[] strings) {
            HttpResponse response = null;
            ArrayList<NameValuePair> post = new ArrayList<NameValuePair>();

            // ?????? HttpClient ?????? ??????
            HttpClient client = new DefaultHttpClient();

            // ?????? ?????? ?????? ??????, ?????? ???????????? ??????
            HttpParams params = client.getParams();
            HttpConnectionParams.setConnectionTimeout(params, 30000);
            HttpConnectionParams.setSoTimeout(params, 30000);

            HttpGet httpGet = new HttpGet("http://map.seoul.go.kr/smgis/apps/poi.do?cmd=getNewContentsDetail&key=" + PublicDefine.serviceSmgisKey + "&theme_id=100211&conts_id=" + strings[0]);
            try {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(post, "UTF-8");

                response = client.execute(httpGet);
                String resultJson = EntityUtils.toString(response.getEntity(), "UTF-8");
                return resultJson;
            } catch (ClientProtocolException e) {
                Log.e("NTsys", "?????? ?????? ?????? ??????");
                return "";
            } catch (IOException e) {
                Log.e("NTsys", "?????? ?????? ?????? ??????");
                return "";
            }
        }

    }

    private void resutData(String result) {
        String main = result.toString();
        JSONObject jsonMain = null;
        try {
            jsonMain = new JSONObject(main);
            JSONArray jsonBody = jsonMain.getJSONArray("body");

            if (jsonBody.length() > 0) {
                LayoutInflater layoutInflater = getLayoutInflater();
                View dilog = layoutInflater.inflate(R.layout.custom_point_detail_layout, null);
                ((TextView) dilog.findViewById(R.id.point_detail_name)).setText(Html.fromHtml(jsonBody.getJSONObject(0).getString("COT_CONTS_NAME")).toString());
                ((TextView) dilog.findViewById(R.id.point_detail_content)).setText(jsonBody.getJSONObject(0).getString("COT_VALUE_01"));
                if (!jsonBody.getJSONObject(0).getString("COT_IMG_MAIN_URL").equals("")) {
                    String imgUrl = "";
                    if (jsonBody.getJSONObject(0).getString("COT_IMG_MAIN_URL").startsWith("/")) {
                        imgUrl = PublicDefine.imageHostUrl + jsonBody.getJSONObject(0).getString("COT_IMG_MAIN_URL");
                    } else {
                        imgUrl = jsonBody.getJSONObject(0).getString("COT_IMG_MAIN_URL");
                    }
                    Glide.with(MainActivity.this).load(imgUrl).into(((ImageView) dilog.findViewById(R.id.point_detail_img)));
                } else {
                    (dilog.findViewById(R.id.point_detail_img_layout)).setVisibility(View.GONE);
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setView(dilog);
                builder.setNegativeButton("??????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.setCancelable(false);
                alert = builder.create();
                final Typeface mTypeface = Typeface.createFromAsset(this.getAssets(), "arita_bold.ttf");
                alert.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialogInterface) {
                        alert.getButton(Dialog.BUTTON_POSITIVE).setTypeface(mTypeface);
                        alert.getButton(Dialog.BUTTON_NEGATIVE).setTypeface(mTypeface);
                    }
                });
                alert.show();
            }
        } catch (JSONException e) {
        }

    }

    public void goMobileWeb(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri uri = Uri.parse("http://gil.seoul.go.kr/m/course/dulae_gil_list.jsp?course=2000");
        intent.setData(uri);
        startActivity(intent);
    }

    public void showMain(View view) {
        tabHost.setCurrentTab(1);
        //PublicDefine.information.setPager(0);
        // # ??????????????? ?????? ????????? ????????? ?????? ?????? ?????????
    }



}
