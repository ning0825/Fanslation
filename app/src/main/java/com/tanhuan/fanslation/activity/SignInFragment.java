package com.tanhuan.fanslation.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.tanhuan.fanslation.BaseApp;
import com.tanhuan.fanslation.R;
import com.tanhuan.fanslation.entity.UserEntity;
import com.tanhuan.fanslation.util.Constants;

import io.objectbox.Box;

public class SignInFragment extends DialogFragment implements View.OnClickListener {
    private static final String TAG = "SignInFragment";

    EditText etPhone;
    EditText etPassword;
    TextView tvToSignUp;
    TextView tvCodeSign;
    Button btSignIn;

    Box<UserEntity> userBox;

    SharedPreferences sp;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userBox = BaseApp.getBoxStore().boxFor(UserEntity.class);
        sp = getContext().getSharedPreferences(Constants.SP_NAME, Context.MODE_PRIVATE);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_sign_in, null);

        etPhone = view.findViewById(R.id.et_phone);
        etPassword = view.findViewById(R.id.et_password);
        tvToSignUp = view.findViewById(R.id.tv_to_sign_up);
        tvCodeSign = view.findViewById(R.id.tv_code_sign);
        btSignIn = view.findViewById(R.id.bt_sign_in);

        btSignIn.setOnClickListener(this);
        tvToSignUp.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_to_sign_up:
                dismiss();
                new SignUpFragment().show(requireFragmentManager(), "signUpTag");
                break;
            case R.id.tv_code_sign:
                break;
            case R.id.bt_sign_in:
                signIn();
                break;
            default:
                break;
        }
    }
    private void signIn() {
        String phone = etPhone.getText().toString();
        String password = etPassword.getText().toString();
        AVUser.loginByMobilePhoneNumberInBackground(phone, password, new LogInCallback<AVUser>() {
            @Override
            public void done(AVUser user, AVException e) {
                //set user name in menu of MainActivity
                ((MainActivity)getContext()).changeName(AVUser.getCurrentUser().getUsername());
                //save objectId for upload paras
                sp.edit().putString(Constants.SP_USER_CLOUD_KEY, user.getObjectId()).apply();
                //save user to box
                UserEntity userEntity = new UserEntity(user.getUsername(), user.getMobilePhoneNumber());
                userEntity.setObjectId(user.getObjectId());
                //keep only one user
                long defaultUserId = sp.getLong(Constants.SP_CURRENT_USER_ID_KEY, 0);
                UserEntity defaultUserEntity = userBox.get(defaultUserId);
                //give defaultUser's book to signed user
                userEntity.toManyBookEntities = defaultUserEntity.toManyBookEntities;
                long currentUserID = userBox.put(userEntity);
                long currentBookId = userEntity.toManyBookEntities.get(0).Id;
                sp.edit().putLong(Constants.SP_CURRENT_USER_ID_KEY, currentUserID).apply();
                sp.edit().putLong(Constants.SP_CURRENT_BOOK_ID_KEY, currentBookId).apply();
                //todo process and done notification
                dismiss();
                Toast.makeText(getContext(), "success", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
