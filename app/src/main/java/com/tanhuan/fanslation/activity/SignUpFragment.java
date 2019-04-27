package com.tanhuan.fanslation.activity;

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
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVMobilePhoneVerifyCallback;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SignUpCallback;
import com.tanhuan.fanslation.BaseApp;
import com.tanhuan.fanslation.R;
import com.tanhuan.fanslation.entity.UserEntity;
import com.tanhuan.fanslation.util.Constants;

import io.objectbox.Box;

public class SignUpFragment extends DialogFragment implements View.OnClickListener {
    private static final String TAG = "SignUpFragment";

    EditText etUserName, etPhone, etPassword, etCode;
    Button btSendCode, btSignUp;

    Box<UserEntity> userBox;

    SharedPreferences sp;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userBox = BaseApp.getBoxStore().boxFor(UserEntity.class);
        sp = getContext().getSharedPreferences(Constants.SP_NAME, Context.MODE_PRIVATE);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_sign_up, null);

        etUserName = view.findViewById(R.id.et_up_user_name);
        etPhone = view.findViewById(R.id.et_up_phone);
        etPassword = view.findViewById(R.id.et_up_password);
        etCode = view.findViewById(R.id.et_up_code);
        btSendCode = view.findViewById(R.id.bt_send_code);
        btSignUp = view.findViewById(R.id.bt_sign_up);

        btSignUp.setOnClickListener(this);
        btSendCode.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_send_code:
                AVUser user = new AVUser();
                user.setUsername(etUserName.getText().toString());
                user.setPassword(etPassword.getText().toString());
                user.put("mobilePhoneNumber", etPhone.getText().toString());
                user.signUpInBackground(new SignUpCallback() {
                    public void done(AVException e) {
                        if (e == null) {
                            // successfully
                            Toast.makeText(getContext(), "sent successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            // failed
                            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
            case R.id.bt_sign_up:
                AVUser.verifyMobilePhoneInBackground(etCode.getText().toString(), new AVMobilePhoneVerifyCallback() {
                    @Override
                    public void done(AVException e) {
                        if(e == null){
                            AVUser user = AVUser.getCurrentUser();
                            ((MainActivity)getContext()).changeName(AVUser.getCurrentUser().getUsername());
                            //save objectId for upload paras
                            sp.edit().putString(Constants.SP_USER_CLOUD_KEY, user.getObjectId()).apply();
                            //save user to box
                            UserEntity userEntity = new UserEntity(user.getUsername(), user.getMobilePhoneNumber());
                            userEntity.setObjectId(user.getObjectId());
                            //keep only one user
                            long defaultUserId = sp.getLong(Constants.SP_DEFAULT_USER_ID_KEY, 0);
                            UserEntity defaultUserEntity = userBox.get(defaultUserId);
                            //give defaultUser's book to signed user
                            userEntity.toManyBookEntities = defaultUserEntity.toManyBookEntities;
                            long currentUserID = userBox.put(userEntity);
                            sp.edit().putLong(Constants.SP_CURRENT_USER_ID_KEY, currentUserID).apply();
                            //todo process and done notification
                            dismiss();
                            Toast.makeText(getContext(), "success", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.d("SMS", "Verified failed!");
                        }
                    }
                });
                break;
            default:
                break;
        }
    }
}
