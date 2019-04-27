package com.tanhuan.fanslation.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SaveCallback;
import com.tanhuan.fanslation.BaseApp;
import com.tanhuan.fanslation.customview.MtoCView;
import com.tanhuan.fanslation.R;
import com.tanhuan.fanslation.bean.ImageBean;
import com.tanhuan.fanslation.entity.BookEntity;
import com.tanhuan.fanslation.entity.ImageEntity;
import com.tanhuan.fanslation.entity.ImageEntity_;
import com.tanhuan.fanslation.entity.UserEntity;
import com.tanhuan.fanslation.entity.UserEntity_;
import com.tanhuan.fanslation.mvp.IView;
import com.tanhuan.fanslation.mvp.ImagePresenter;
import com.tanhuan.fanslation.util.Constants;
import com.tanhuan.fanslation.util.HttpUtil;
import com.tanhuan.fanslation.util.ViewUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.objectbox.Box;
import io.objectbox.BoxStore;

public class MainActivity extends AppCompatActivity implements IView<ImageBean> {
    private static final String TAG = "----MainActivity----";

    EditText etInput;

    MyClickListener myClickListener;

    //vocabulary book
    TextView tvBook;
    //menu layout
    ConstraintLayout clDialog;
    //the gray background when expand menu
    View bgMenu;
    //menu button
    MtoCView btMenu;
    //recite button
    Button btRecite;
    //layout of this activity, use for Snackbar constructor
    ConstraintLayout clMain;
    TextView tvStatistic;
    TextView tvSignOut;
    TextView tvUserName;
    ImageView ivAvator;

    //menu layout scale in animation
    ValueAnimator clWidAnimator;
    ValueAnimator clHeiAnimator;
    //gray background transparency change animation
    ObjectAnimator bgMenuAnimator;
    //menu transparency change animation
    ObjectAnimator clMenuAnimator;

    int ANIM_DURATION = 300;

    ImagePresenter imagePresenter;

    //每日一句
    TextView tvImageEn;
    TextView tvImageCn;
    Box<ImageEntity> imageBox;

    BoxStore store;
    Box<BookEntity> bookBox;
    Box<UserEntity> userBox;

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etInput = findViewById(R.id.et_input);
        clDialog = findViewById(R.id.cl_dialog);
        bgMenu = findViewById(R.id.bg_meau);
        btMenu = findViewById(R.id.bt_menu);
        tvImageEn = findViewById(R.id.tv_image_en);
        tvImageCn = findViewById(R.id.tv_image_cn);
        tvBook = findViewById(R.id.tv_book);
        btRecite = findViewById(R.id.bt_recite);
        clMain = findViewById(R.id.cl_main);
        tvStatistic = findViewById(R.id.tv_statistic);
        tvSignOut = findViewById(R.id.tv_sign_out);
        tvUserName = findViewById(R.id.tv_username);
        ivAvator = findViewById(R.id.iv_avator);

        sp = getSharedPreferences(Constants.SP_NAME, Context.MODE_PRIVATE);

        myClickListener = new MyClickListener();
        etInput.setOnClickListener(myClickListener);
        bgMenu.setOnClickListener(myClickListener);
        btMenu.setOnClickListener(myClickListener);
        tvBook.setOnClickListener(myClickListener);
        btRecite.setOnClickListener(myClickListener);
        tvStatistic.setOnClickListener(myClickListener);
        tvSignOut.setOnClickListener(myClickListener);
        tvUserName.setOnClickListener(myClickListener);
        ivAvator.setOnClickListener(myClickListener);


        animatorInit();
        boxInit();

        imagePresenter = new ImagePresenter(this);

        if (!HttpUtil.isConnected(this)) {
            Snackbar.make(clMain, "no network", Snackbar.LENGTH_SHORT).show();
        }

        getImage();

        ((TextView) clDialog.findViewById(R.id.tv_username)).setText(AVUser.getCurrentUser() != null ? AVUser.getCurrentUser().getUsername() : "sign in");
    }

    private void boxInit() {
        store = BaseApp.getBoxStore();
        bookBox = store.boxFor(BookEntity.class);
        userBox = store.boxFor(UserEntity.class);
        imageBox = store.boxFor(ImageEntity.class);

        //add a book if bookBox is empty, when you first install this app.
        if (bookBox.isEmpty()) {
            UserEntity userEntity = new UserEntity("defaultname", "00000000000");
            BookEntity bookEntity = new BookEntity("defaultBook");
            long id = bookBox.put(bookEntity);
            userEntity.toManyBookEntities.add(bookEntity);
            long defaultUserId = userBox.put(userEntity);
            sp.edit().putLong(Constants.SP_CURRENT_BOOK_ID_KEY, id)
                    .putLong(Constants.SP_CURRENT_USER_ID_KEY, defaultUserId).apply();
            Log.e(TAG, "boxInit: " + "save default book to box");
        }
    }


    /*
    显示每日一句*/
    private void getImage() {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd", Locale.CHINA);
        String date = sdf.format(new Date());

        List<ImageEntity> images = imageBox.query().equal(ImageEntity_.dateline, date).build().find();

        if (images.size() > 0) {
            tvImageEn.setText(images.get(0).getContent());
            tvImageCn.setText(images.get(0).getNote());
        } else if (HttpUtil.isConnected(this)) {
            imagePresenter.request(date);
        }
    }

    int heightCl;

    private void animatorInit() {
        //measure clDialog's height
        clDialog.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        heightCl = clDialog.getMeasuredHeight();

        clWidAnimator = ValueAnimator.ofFloat(ViewUtil.dp2px(this, 60), getWindow().getWindowManager().getDefaultDisplay().getWidth() - ViewUtil.dp2px(this, 32));
        clHeiAnimator = ValueAnimator.ofFloat(ViewUtil.dp2px(this, 60), heightCl);

        bgMenuAnimator = ObjectAnimator.ofFloat(bgMenu, View.ALPHA, 0, 0.3f);
        clMenuAnimator = ObjectAnimator.ofFloat(clDialog, View.ALPHA, 0, 1);

        clWidAnimator.setDuration(ANIM_DURATION);
        clHeiAnimator.setDuration(ANIM_DURATION);
        bgMenuAnimator.setDuration(ANIM_DURATION);
        clMenuAnimator.setDuration(ANIM_DURATION);

        clWidAnimator.setInterpolator(new AccelerateDecelerateInterpolator());

        bgMenuAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation, boolean isReverse) {
                if (isReverse) {
                    bgMenu.setClickable(false);
                } else {
                    bgMenu.setVisibility(View.VISIBLE);
                    clDialog.setClickable(false);
                    bgMenu.setClickable(false);
                }
            }

            @Override
            public void onAnimationEnd(Animator animation, boolean isReverse) {
                if (isReverse) {
                    bgMenu.setVisibility(View.GONE);
                    clDialog.setClickable(true);
                    bgMenu.setClickable(true);
                } else {
                    bgMenu.setClickable(true);
                }
            }
        });

        ViewGroup.LayoutParams params = clDialog.getLayoutParams();

        clWidAnimator.addUpdateListener((animation -> {
            params.width = ((Number) animation.getAnimatedValue()).intValue();
            clDialog.setLayoutParams(params);
        }));

        clHeiAnimator.addUpdateListener((animation -> {
            params.height = ((Number) animation.getAnimatedValue()).intValue();
            clDialog.setLayoutParams(params);
        }));
    }

    class MyClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.bt_menu:
                    Log.e("btmenu", "onClick: " + v.getId());
                    if (!btMenu.checked) {
                        reverseAnim();
                    } else {
                        clWidAnimator.start();
                        clHeiAnimator.start();
                        bgMenuAnimator.start();
                        clMenuAnimator.start();
                    }
                    break;
                case R.id.et_input:
                    startActivity(new Intent(MainActivity.this, SearchActivity.class));
                    AnimationSet animation = (AnimationSet) AnimationUtils.loadAnimation(MainActivity.this, R.anim.input_transtion);
                    etInput.startAnimation(animation);
                    break;
                case R.id.bg_meau:
                    reverseAnim();
                    btMenu.setChecked(false);
                    break;
                case R.id.tv_book:
                    Intent intent = new Intent(MainActivity.this, BookActivity.class);
                    intent.putExtra("bookId", getSharedPreferences(Constants.SP_NAME, Context.MODE_PRIVATE).getLong(Constants.SP_CURRENT_BOOK_ID_KEY, 0));
                    startActivity(intent);
                    break;
                case R.id.bt_recite:
                    startActivity(new Intent(MainActivity.this, ReciteActivity.class));
                    // TODO: 2019/4/27 test lean cloud
//                    syncPara();
                    break;
                case R.id.tv_statistic:
                    startActivity(new Intent(MainActivity.this, StatisticActivity.class));
                    reverseAnim();
                    btMenu.setChecked(false);
                    break;
                case R.id.tv_sign_out:
                    AVUser.logOut();
                    Toast.makeText(MainActivity.this, "sign out successfully", Toast.LENGTH_SHORT).show();
                    ((TextView) clDialog.findViewById(R.id.tv_username)).setText("sign in");
                    reverseAnim();
                case R.id.tv_username:
                    new SignInFragment().show(getSupportFragmentManager(), "signInTag");
                    reverseAnim();
                    break;
                case R.id.iv_avator:
                    syncPara();
                default:
                    break;
            }
        }
    }

    private void reverseAnim() {
        bgMenuAnimator.reverse();
        clWidAnimator.reverse();
        clHeiAnimator.reverse();
        clMenuAnimator.reverse();
    }

    @Override
    public void showResult(ImageBean imageBean) {
        tvImageEn.setText(imageBean.getContent());
        tvImageCn.setText(imageBean.getNote());
        imagePresenter.save(imageBean);
    }

    @Override
    public void showError(Throwable throwable) {
        Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        imagePresenter.detachView();
    }

    @Override
    public void onBackPressed() {
        if (btMenu.checked) {
            bgMenuAnimator.reverse();
            clWidAnimator.reverse();
            clHeiAnimator.reverse();
            clMenuAnimator.reverse();
            btMenu.setChecked(false);
        } else {
            super.onBackPressed();
        }

    }

    void syncPara() {
//        AVObject avObject = new AVObject("BookObject");
//        avObject.put("testKey", "testData");
//        avObject.put("bookName", "testName");
//        avObject.saveInBackground(new SaveCallback() {
//            @Override
//            public void done(AVException e) {
//                if (e != null) {
//                    Toast.makeText(MainActivity.this, "save done", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
        Toast.makeText(this, "syncing....", Toast.LENGTH_SHORT).show();
    }

    public void changeName(String name) {
        tvUserName.setText(name);
    }

}
