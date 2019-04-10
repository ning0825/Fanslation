package com.tanhuan.fanslation.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.tanhuan.fanslation.BaseApp;
import com.tanhuan.fanslation.MtoCView;
import com.tanhuan.fanslation.R;
import com.tanhuan.fanslation.bean.ImageBean;
import com.tanhuan.fanslation.entity.BookEntity;
import com.tanhuan.fanslation.entity.UserEntity;
import com.tanhuan.fanslation.mvp.IView;
import com.tanhuan.fanslation.mvp.ImagePresenter;
import com.tanhuan.fanslation.util.Constants;
import com.tanhuan.fanslation.util.ViewUtil;

import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.objectbox.Box;
import io.objectbox.BoxStore;

public class MainActivity extends AppCompatActivity implements IView<ImageBean> {
    private static final String TAG = "----MainActivity----";

    EditText etInput;

    MyClickListener myClickListener;

    //单词书
    TextView tvBook;
    //方形菜单按钮
    FrameLayout flMenu;
    //菜单布局
    ConstraintLayout clDialog;
    //按钮放大后的外部半透明背景
    View bgMenu;
    //菜单按钮
    MtoCView btMenu;
    //背单词按钮
    Button btRecite;

    //菜单布局放大动画
    ValueAnimator flAnimator;
    //菜单外部背景透明度变化动画
    ObjectAnimator bgMenuAnimator;
    //菜单透明度变化动画
    ObjectAnimator clMenuAnimator;

    int ANIM_DURATION = 300;

    ImagePresenter imagePresenter;

    //每日一句
    TextView tvImageEn;
    TextView tvImageCn;

    BoxStore store;
    Box<BookEntity> bookBox;
    Box<UserEntity> userBox;

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etInput = findViewById(R.id.et_input);
        flMenu = findViewById(R.id.fl_menu);
        clDialog = findViewById(R.id.cl_dialog);
        bgMenu = findViewById(R.id.bg_meau);
        btMenu = findViewById(R.id.bt_menu);
        tvImageEn = findViewById(R.id.tv_image_en);
        tvImageCn = findViewById(R.id.tv_image_cn);
        tvBook = findViewById(R.id.tv_book);
        btRecite = findViewById(R.id.bt_recite);

        store = BaseApp.getBoxStore();
        bookBox = store.boxFor(BookEntity.class);
        userBox = store.boxFor(UserEntity.class);

        sp = getSharedPreferences(Constants.SP_NAME, Context.MODE_PRIVATE);

        myClickListener = new MyClickListener();
        etInput.setOnClickListener(myClickListener);
        //点击菜单外部缩小菜单
        bgMenu.setOnClickListener(myClickListener);
        //菜单按钮点击事件
        btMenu.setOnClickListener(myClickListener);
        tvBook.setOnClickListener(myClickListener);
        btRecite.setOnClickListener(myClickListener);

        animatorInit();

        imagePresenter = new ImagePresenter(this);

        boxInit();
    }

    private void boxInit() {
        //如果 bookBox 中没数据，新建一本书, 通常是安装后第一次打开程序时
        if (bookBox.isEmpty()) {
            UserEntity userEntity = new UserEntity("paix", "15513616423");
            BookEntity bookEntity = new BookEntity("defaultBook");
            long id = bookBox.put(bookEntity);
            userEntity.toManyBookEntities.add(bookEntity);
            userBox.put(userEntity);
            sp.edit().putLong(Constants.SP_DEFAULT_BOOK_ID_KEY, id).apply();
            Log.e(TAG, "boxInit: " + "save default book to box");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        getImage();
    }

    /*
    显示每日一句*/
    private void getImage() {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd", Locale.CHINA);
        String date = sdf.format(new Date());
        Log.e(TAG, "getImage: " + date );
        imagePresenter.request(date);
    }

    private void animatorInit() {
        flAnimator = ValueAnimator.ofFloat(ViewUtil.dp2px(this, 60), 1000);
        bgMenuAnimator = ObjectAnimator.ofFloat(bgMenu, View.ALPHA, 0, 0.3f);
        clMenuAnimator = ObjectAnimator.ofFloat(clDialog, View.ALPHA, 0,1);

        flAnimator.setDuration(ANIM_DURATION);
        bgMenuAnimator.setDuration(ANIM_DURATION);
        clMenuAnimator.setDuration(ANIM_DURATION);

        flAnimator.setInterpolator(new AccelerateDecelerateInterpolator());

        bgMenuAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation, boolean isReverse) {
                if (isReverse) {
                    bgMenu.setClickable(false);
                } else {
                    bgMenu.setVisibility(View.VISIBLE);
                    flMenu.setClickable(false);
                    bgMenu.setClickable(false);
                }
            }

            @Override
            public void onAnimationEnd(Animator animation, boolean isReverse) {
                if (isReverse) {
                    bgMenu.setVisibility(View.GONE);
                    flMenu.setClickable(true);
                    bgMenu.setClickable(true);
                } else {
                    bgMenu.setClickable(true);
                }
            }
        });

        ViewGroup.LayoutParams params = flMenu.getLayoutParams();

        flAnimator.addUpdateListener((animation -> {
            params.width = ((Number) animation.getAnimatedValue()).intValue();
            params.height = ((Number) animation.getAnimatedValue()).intValue();
            flMenu.setLayoutParams(params);
        }));
    }

    class MyClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.bt_menu:
                    Log.e("btmenu", "onClick: " + v.getId() );
                    if (!btMenu.checked) {
                        bgMenuAnimator.reverse();
                        flAnimator.reverse();
                        clMenuAnimator.reverse();
                    } else {
                        flAnimator.start();
                        bgMenuAnimator.start();
                        clMenuAnimator.start();
                    }
                    break;
                case R.id.et_input:
                    startActivity(new Intent(MainActivity.this, SearchActivity.class ));
                    AnimationSet animation = (AnimationSet) AnimationUtils.loadAnimation(MainActivity.this, R.anim.input_transtion);
                    etInput.startAnimation(animation);
                    break;
                case R.id.bg_meau:
                    bgMenuAnimator.reverse();
                    flAnimator.reverse();
                    clMenuAnimator.reverse();
                    btMenu.setChecked(false);
                    break;
                case R.id.tv_book:
                    startActivity(new Intent(MainActivity.this, BookActivity.class));
                    break;
                case R.id.bt_recite:
                    startActivity(new Intent(MainActivity.this, ReciteActivity.class));
                default:
                    break;
            }
        }
    }

    @Override
    public void showResult(ImageBean imageBean) {
        tvImageEn.setText(imageBean.getContent());
        tvImageCn.setText(imageBean.getNote());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        imagePresenter.detachView();
    }
}
