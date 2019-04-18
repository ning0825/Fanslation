package com.tanhuan.fanslation.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tanhuan.fanslation.BaseApp;
import com.tanhuan.fanslation.customview.MtoCView;
import com.tanhuan.fanslation.R;
import com.tanhuan.fanslation.bean.ImageBean;
import com.tanhuan.fanslation.entity.BookEntity;
import com.tanhuan.fanslation.entity.UserEntity;
import com.tanhuan.fanslation.mvp.IView;
import com.tanhuan.fanslation.mvp.ImagePresenter;
import com.tanhuan.fanslation.util.Constants;
import com.tanhuan.fanslation.util.HttpUtil;
import com.tanhuan.fanslation.util.ViewUtil;

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
    //菜单布局
    ConstraintLayout clDialog;
    //按钮放大后的外部半透明背景
    View bgMenu;
    //菜单按钮
    MtoCView btMenu;
    //背单词按钮
    Button btRecite;

    //菜单布局放大动画
    ValueAnimator clWidAnimator;
    ValueAnimator clHeiAnimator;
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
//        flMenu = findViewById(R.id.fl_menu);
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

//        getImage();

        Toast.makeText(this, HttpUtil.isConnected(this)?"connected":"no network", Toast.LENGTH_SHORT).show();
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


    /*
    显示每日一句*/
    private void getImage() {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd", Locale.CHINA);
        String date = sdf.format(new Date());
        Log.e(TAG, "getImage: " + date);
        imagePresenter.request(date);
    }

    int heightCl;

    private void animatorInit() {
        //todo measure clDialog's height
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
                        bgMenuAnimator.reverse();
                        clWidAnimator.reverse();
                        clHeiAnimator.reverse();
                        clMenuAnimator.reverse();
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
                    bgMenuAnimator.reverse();
                    clWidAnimator.reverse();
                    clHeiAnimator.reverse();
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
    public void showError(Throwable throwable) {
        Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        imagePresenter.detachView();
    }

}
