package kr.go.seoul.seoultrail;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
<<<<<<< HEAD:SeoulTrail_final/app/src/main/java/kr/go/seoul/seoultrail/Community.java
import com.firebase.ui.storage.images.FirebaseImageLoader;
=======
>>>>>>> ca4c9829395aed517e854978b33530427e00b35b:SeoulTrail/app/src/main/java/kr/go/seoul/seoultrail/Community.java
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import kr.go.seoul.seoultrail.Common.PublicDefine;
import kr.go.seoul.seoultrail.CommunityManager.CommunityAdapter;
import kr.go.seoul.seoultrail.CommunityManager.Post;

import static android.Manifest.permission.CAMERA;

/**
 * Created by ?????? on 2017-09-29.
 */

public class Community extends BaseActivity {
    private RecyclerView myRecyclerView;
    private Button sendBtn;
    private TextView userName;
    private EditText writeText;
    private ImageView userIcon;
    private ImageView setPicture;
    private ImageView postedPicture;
    private ProgressDialog progressDialog;

    private ImageView uploadPicture = null;

    private List<Post> postList = new ArrayList<>();
    private CommunityAdapter myAdapter;
    private RecyclerView.LayoutManager myLayoutManager;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private StorageReference storageReference;

    SharedPreferences loginPref;

    private boolean login = false;
    private boolean validity = true;
    private boolean pictureYesOrNo = true;
    private boolean iconYesOrNo = false;

    private static final int SET_ICON_CAMERA = 1;
    private static final int SET_ICON_GALLERY = 2;
    private static final int SET_PICTURE_CAMERA = 3;
    private static final int SET_PICTURE_GALLERY = 4;

    private static final int REQUEST_EXTERNAL_STORAGE = 12;
    private static final int REQUEST_CAMERA = 14;

    private int icon = 1;
    private int picture = 2;

    private Uri mCaptureURI;

    @Override
    public void onBackPressed() {
        PublicDefine.mainActivity.Main_Move();
        // PublicDefine.mainActivity.settingTab(1);
    }
    @Override
    public void onBackPressed() {
        PublicDefine.mainActivity.Main_Move();
        // PublicDefine.mainActivity.settingTab(1);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ????????????, ??? ??????
        setContentView(R.layout.community_activity);
        myRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        sendBtn = (Button) findViewById(R.id.send_btn);
        writeText = (EditText) findViewById(R.id.write_text);
        userName = (TextView) findViewById(R.id.user_name);
        userIcon = (ImageView) findViewById(R.id.user_icon);
        setPicture = (ImageView) findViewById(R.id.set_picture);
        postedPicture = (ImageView) findViewById(R.id.posted_picture);


        myRecyclerView.setHasFixedSize(true);

        // ???????????? ????????? ??????
        myLayoutManager = new LinearLayoutManager(this);
        myRecyclerView.setLayoutManager(myLayoutManager);

        // ????????? ??????
        myAdapter = new CommunityAdapter(postList);
        myRecyclerView.setAdapter(myAdapter);

        // firebase ???????????? ??????
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        FirebaseStorage storage = FirebaseStorage.getInstance();
        storageReference = storage.getReferenceFromUrl("gs://seoultrail-fc963.appspot.com/");

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("????????? ???...");

        updateBodyText();

        setPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
<<<<<<< HEAD:SeoulTrail_final/app/src/main/java/kr/go/seoul/seoultrail/Community.java
                // ??????, ????????? ?????? ??????????????? ??????
                AlertDialog.Builder dialog = null;
                dialog = new AlertDialog.Builder(Community.this);
                dialog.setMessage("????????? ?????? ???????????????.");

                dialog.setPositiveButton("??????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                String name = writeText.getText().toString();
                if(login && !name.equals("")) {
                    try {
                        postPicture(picture);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if(login && name.equals("")) {
                    dialog.show();
                } else if(!login && !name.equals("")) {
                    try {
                        postPicture(icon);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
=======
                if(login && !writeText.getText().toString().equals("")) {
                    postPicture(picture);
                } else if(login && writeText.getText().toString().equals("")) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(Community.this);
                    dialog.setMessage("????????? ?????? ???????????????.");

                    dialog.setPositiveButton("??????", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });

>>>>>>> ca4c9829395aed517e854978b33530427e00b35b:SeoulTrail/app/src/main/java/kr/go/seoul/seoultrail/Community.java
                    dialog.show();
                }
            }
        });
        /*setPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(login && !writeText.getText().toString().equals("")) {
                    try {
                        postPicture(picture);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else if(login && writeText.getText().toString().equals("")) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(Community.this);
                    dialog.setMessage("????????? ?????? ???????????????.");

                    dialog.setPositiveButton("??????", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });

                    dialog.show();
                }
            }
        });*/

        loginPref = getSharedPreferences("login", 0);
        final SharedPreferences.Editor editor = loginPref.edit();
        // ????????? ????????? ?????? ??????
//        editor.clear();
//        editor.commit();
        final String id = loginPref.getString("id", "");

        if(loginPref == null && id.equals("")) {
            login = false;
        } else if(loginPref != null && !id.equals("")){
            login = true;

<<<<<<< HEAD:SeoulTrail_final/app/src/main/java/kr/go/seoul/seoultrail/Community.java
            // ?????? ???????????? ?????? ??????
            if(iconYesOrNo) {
                StorageReference childRef = storageReference.child("icon/icon_" + id + ".jpg");
                Glide.with(this).using(new FirebaseImageLoader()).load(childRef).into(userIcon);
            } else {
                userIcon.setImageResource(R.drawable.image_profile03);
            }

=======
            // ??????, placeholder, ?????? ??????
>>>>>>> ca4c9829395aed517e854978b33530427e00b35b:SeoulTrail/app/src/main/java/kr/go/seoul/seoultrail/Community.java
            userName.setText(id);
            writeText.setHint(id + "?????? ????????? ???????????????.");
            setPicture.setImageResource(R.drawable.btn01_upload_off);
            sendBtn.setBackgroundResource(R.drawable.btn02_posting_off);
        }

        if(ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getParent(), new String[]{CAMERA}, REQUEST_CAMERA);
        }

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(login == false) {
                    final String newName = writeText.getText().toString();

                    // ????????? ????????? ??????
                    databaseReference.child("posts").orderByKey().addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if(login == true) {
                                return;
                            }
                            for(DataSnapshot nameSnapshot : dataSnapshot.getChildren()) {
                                String oldName = nameSnapshot.child("name").getValue().toString();
                                if(oldName.equals(newName)) {
                                    Toast.makeText(Community.this, "???????????? ?????? ??????????????????", Toast.LENGTH_LONG).show();
                                    validity = false;
                                    hideKeyboard();
                                    break;
                                } else if (newName.equals("")) {
                                    Toast.makeText(Community.this, "???????????? ??????????????????.", Toast.LENGTH_LONG).show();
                                    validity = false;
                                    hideKeyboard();
                                    break;
                                } else if(validity == false && !oldName.equals(newName)) {
                                    validity = true;
                                }
                            }

                            if(validity == true) {
                                // 'login'????????? id, pw ??????
                                editor.putString("id", newName);
                                editor.putBoolean("iconYesOrNo", iconYesOrNo);
                                editor.commit();
                                userName.setText(newName);
                                hideKeyboard();
                                changeLoginScene();
                                return;
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                } else {
<<<<<<< HEAD:SeoulTrail_final/app/src/main/java/kr/go/seoul/seoultrail/Community.java
                    hideKeyboard();
=======


>>>>>>> ca4c9829395aed517e854978b33530427e00b35b:SeoulTrail/app/src/main/java/kr/go/seoul/seoultrail/Community.java
                    writeBodyText();
                }

            }
        });

        userIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ???????????? ?????? ????????? ????????? => db??? icon ????????? name?????????????????? ??????
                if(login == false && !writeText.getText().toString().equals("")) {
<<<<<<< HEAD:SeoulTrail_final/app/src/main/java/kr/go/seoul/seoultrail/Community.java
                    // ????????? ?????? ??????
                    try {
                        postPicture(icon);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

=======
                    System.out.println(writeText.getText().toString() + "?????????~~~~~~~~~~~~~~~~~~");
                    postPicture(icon);
>>>>>>> ca4c9829395aed517e854978b33530427e00b35b:SeoulTrail/app/src/main/java/kr/go/seoul/seoultrail/Community.java
                } else if (login == false && writeText.getText().toString().equals("")) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(Community.this);
                    dialog.setMessage("???????????? ?????? ???????????????");

                    dialog.setPositiveButton("??????", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });

                    dialog.show();
<<<<<<< HEAD:SeoulTrail_final/app/src/main/java/kr/go/seoul/seoultrail/Community.java
                }
            }
        });

        writeText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
=======
>>>>>>> ca4c9829395aed517e854978b33530427e00b35b:SeoulTrail/app/src/main/java/kr/go/seoul/seoultrail/Community.java
                }
            }
        });

    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(writeText.getWindowToken(), 0);
    }

    private void changeLoginScene() {
        // ???????????? ???????????? ??????
        loginPref = getSharedPreferences("login", 0);
        String id = loginPref.getString("id", "");

        // ????????? ???????????? ?????????????????? ??????
        if(!iconYesOrNo) {
            userIcon.setImageResource(R.drawable.image_profile03);
        }

        userName.setText(id);
        writeText.setText("");
        writeText.setHint(id + "?????? ????????? ???????????????.");
        setPicture.setImageResource(R.drawable.btn01_upload_off);
        sendBtn.setBackgroundResource(R.drawable.btn02_posting_off);
        login = true;

        Toast.makeText(Community.this, "???????????? ?????????????????????", Toast.LENGTH_LONG).show();
    }


    // ??? ?????? ?????????
    private void writeBodyText() {
        long nowTime = new Date().getTime();
        String username = userName.getText().toString();
        String bodyText = writeText.getText().toString();
<<<<<<< HEAD:SeoulTrail_final/app/src/main/java/kr/go/seoul/seoultrail/Community.java
        if (postedPicture.getVisibility() == View.GONE) {
=======
        if(postedPicture.getVisibility() == View.GONE) {
>>>>>>> ca4c9829395aed517e854978b33530427e00b35b:SeoulTrail/app/src/main/java/kr/go/seoul/seoultrail/Community.java
            pictureYesOrNo = false;
        } else {
            pictureYesOrNo = true;
        }
<<<<<<< HEAD:SeoulTrail_final/app/src/main/java/kr/go/seoul/seoultrail/Community.java
        if(bodyText.equals("") && !pictureYesOrNo) {
            Toast.makeText(this, "?????? ??????????????????.", Toast.LENGTH_LONG).show();
            return;
        }
=======

>>>>>>> ca4c9829395aed517e854978b33530427e00b35b:SeoulTrail/app/src/main/java/kr/go/seoul/seoultrail/Community.java
        Post post = new Post(iconYesOrNo, username, nowTime, bodyText, pictureYesOrNo);
        post.setWriteTime(post.getWriteTime());
        databaseReference.child("posts").push().setValue(post);

        // ????????? ?????? ?????????
        writeText.setText("");
        postedPicture.setVisibility(View.GONE);
<<<<<<< HEAD:SeoulTrail_final/app/src/main/java/kr/go/seoul/seoultrail/Community.java
        Toast.makeText(this, "?????? ?????????????????????.", Toast.LENGTH_LONG).show();
=======
>>>>>>> ca4c9829395aed517e854978b33530427e00b35b:SeoulTrail/app/src/main/java/kr/go/seoul/seoultrail/Community.java
    }


    // ??? ?????? ?????????
    private void updateBodyText() {
        databaseReference.child("posts").orderByChild("writeTime").limitToFirst(100).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChild) {
                // ????????? ??????
                Post post = dataSnapshot.getValue(Post.class);
                postList.add(post);
                Collections.sort(postList, new Comparator<Post>() {
                    @Override
                    public int compare(Post post, Post t1) {
                        if(post.getWriteTime() > t1.getWriteTime()) {
                            return -1;
                        } else if(post.getWriteTime() < t1.getWriteTime()) {
                            return 1;
                        } else {
                            return 0;
                        }
                    }
                });
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode != RESULT_OK) return;

        switch(requestCode) {
            case SET_ICON_CAMERA: {
                if(resultCode == RESULT_OK) {
<<<<<<< HEAD:SeoulTrail_final/app/src/main/java/kr/go/seoul/seoultrail/Community.java


                    progressDialog.show();

                    // firebase ????????? ?????????
                    String iconFileName = "icon_" + writeText.getText() + ".jpg";
                    StorageReference childRef = storageReference.child("icon/" + iconFileName);
                    UploadTask uploadTask = childRef.putFile(mCaptureURI);

                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                        }
                    });

                    userIcon.setImageURI(mCaptureURI);

                    iconYesOrNo = true;


=======
                    Uri uri = data.getData();

                    if(uri != null) {
                        progressDialog.show();

                        // firebase ????????? ?????????
                        String iconFileName = "icon_" + writeText.getText() + ".jpg";
                        StorageReference childRef = storageReference.child("icon/" + iconFileName);
                        UploadTask uploadTask = childRef.putFile(uri);

                        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                progressDialog.dismiss();
                            }
                        });

                        userIcon.setImageURI(uri);

                        iconYesOrNo = true;
                    }

                }
                break;
            }
            case SET_ICON_GALLERY: {
                if(resultCode == RESULT_OK) {
                    Uri uri = data.getData();

                    if(uri != null) {
                        progressDialog.show();

                        // firebase ????????? ?????????
                        String iconFileName = "icon_" + writeText.getText() + ".jpg";
                        StorageReference childRef = storageReference.child("icon/" + iconFileName);
                        UploadTask uploadTask = childRef.putFile(uri);

                        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                progressDialog.dismiss();
                            }
                        });

                        Bitmap bitmap = null;
                        try {
                            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        userIcon.setImageBitmap(bitmap);
                        iconYesOrNo = true;
                    }
>>>>>>> ca4c9829395aed517e854978b33530427e00b35b:SeoulTrail/app/src/main/java/kr/go/seoul/seoultrail/Community.java
                }
                break;
            }
            case SET_ICON_GALLERY: {
                if(resultCode == RESULT_OK) {
                    Uri uri = data.getData();

                    if(uri != null) {
                        progressDialog.show();

                        // firebase ????????? ?????????
<<<<<<< HEAD:SeoulTrail_final/app/src/main/java/kr/go/seoul/seoultrail/Community.java
                        String iconFileName = "icon_" + writeText.getText() + ".jpg";
                        StorageReference childRef = storageReference.child("icon/" + iconFileName);
=======
                        System.out.println("writeTExt??? ~~~~~~~~~~~~~~~~~~" +writeText.getText().toString());
                        String fileName = userName.getText().toString() + "_" + writeText.getText().toString() + ".jpg";
                        StorageReference childRef = storageReference.child("image/image_" + fileName);
>>>>>>> ca4c9829395aed517e854978b33530427e00b35b:SeoulTrail/app/src/main/java/kr/go/seoul/seoultrail/Community.java
                        UploadTask uploadTask = childRef.putFile(uri);

                        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                progressDialog.dismiss();
                            }
                        });

                        Bitmap bitmap = null;
                        try {
                            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        userIcon.setImageBitmap(bitmap);
                        iconYesOrNo = true;
                    }
                }
                break;
            }
            case SET_PICTURE_CAMERA: {
                if(resultCode == RESULT_OK) {

                    progressDialog.show();

                    // firebase ????????? ?????????
                    String fileName = userName.getText().toString() + "_" + writeText.getText().toString() + ".jpg";
                    StorageReference childRef = storageReference.child("image/image_" + fileName);
                    UploadTask uploadTask = childRef.putFile(mCaptureURI);

                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                        }
                    });

                    // ??????????????? ?????????
                    postedPicture.setImageURI(mCaptureURI);
                    postedPicture.setVisibility(View.VISIBLE);
                }

                break;
            }
            case SET_PICTURE_GALLERY: {
                if(resultCode == RESULT_OK) {
                    Uri uri = data.getData();

                    if(uri != null) {
                        progressDialog.show();

                        // firebase ????????? ?????????
                        String fileName = userName.getText().toString() + "_" + writeText.getText().toString() + ".jpg";
                        StorageReference childRef = storageReference.child("image/image_" + fileName);
                        UploadTask uploadTask = childRef.putFile(uri);

                        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                progressDialog.dismiss();
                            }
                        });

                        Bitmap bitmap = null;
                        try {
                            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        postedPicture.setImageBitmap(bitmap);
                        postedPicture.setVisibility(View.VISIBLE);
                    }
                }
                break;
            }
        }
    }

    private void postPicture(int iconOrPicture) throws IOException{
        final AlertDialog.Builder pictureDialog = new AlertDialog.Builder(Community.this);

        pictureDialog.setTitle("????????? ???????????????");

        final Dialog dialog = pictureDialog.create();

        if(iconOrPicture == icon) {
            pictureDialog.setPositiveButton("????????????", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    takePictureIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                    String fileName = new SimpleDateFormat("yyyyMMdd_HHmmss").format(System.currentTimeMillis());
                    String imageFileName = fileName + ".jpg";
                    File stroageDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM/Dulle/" + imageFileName);
                    mCaptureURI = FileProvider.getUriForFile(getApplicationContext(), "kr.go.seoul.seoultrail.provider", stroageDir);
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mCaptureURI);
                    startActivityForResult(takePictureIntent, SET_ICON_CAMERA);
                }
            });

            pictureDialog.setNeutralButton("????????????", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    galleryIntent.setType("image/*");
                    if (galleryIntent.resolveActivity(getPackageManager()) != null) {
                        startActivityForResult(galleryIntent, SET_ICON_GALLERY);
                    }else{
                        System.out.println("??????????????? ?????????~~~~~~~~~~~~~~~~");
                    }
                }
            });
        } else if (iconOrPicture == picture) {
            pictureDialog.setPositiveButton("????????????", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    takePictureIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                    String fileName = new SimpleDateFormat("yyyyMMdd_HHmmss").format(System.currentTimeMillis());
                    String imageFileName = fileName + ".jpg";
                    File stroageDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM/Dulle/" + imageFileName);
                    mCaptureURI = FileProvider.getUriForFile(getApplicationContext(), "kr.go.seoul.seoultrail.provider", stroageDir);
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mCaptureURI);
                    startActivityForResult(takePictureIntent, SET_PICTURE_CAMERA);
                }
            });

            pictureDialog.setNeutralButton("????????????", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    if (galleryIntent.resolveActivity(getPackageManager()) != null) {
                        startActivityForResult(galleryIntent, SET_PICTURE_GALLERY);
                    }else{
                        System.out.println("??????????????? ?????????~~~~~~~~~~~~~~~~");
                    }
                }
            });
        }

        pictureDialog.setNegativeButton("??????", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialog.dismiss();
            }
        });

        pictureDialog.show();
    }



}