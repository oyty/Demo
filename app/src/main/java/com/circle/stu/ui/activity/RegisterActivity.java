package com.circle.stu.ui.activity;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.circle.stu.R;
import com.circle.stu.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by oyty on 10/23/16.
 */

public class RegisterActivity extends BaseActivity implements TextWatcher {

    @BindView(R.id.mMobileValue)
    EditText mMobileValue;
    @BindView(R.id.mPasswordValue)
    EditText mPasswordValue;
    @BindView(R.id.mNicknameValue)
    EditText mNicknameValue;
    @BindView(R.id.mRegisterAction)
    Button mRegisterAction;
    @BindView(R.id.mAgreementAction)
    View mAgreementAction;

    @Override
    protected void initToolbar() {
        setTitle(R.string.register);
        showBackAction(true);
    }

    @Override
    protected int initViewId() {
        return R.layout.activity_register;
    }

    @Override
    protected void process() {
        mMobileValue.addTextChangedListener(this);
        mPasswordValue.addTextChangedListener(this);
        mNicknameValue.addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String mobile = mMobileValue.getText().toString().trim();
        String pwd = mPasswordValue.getText().toString().trim();
        String nickname = mNicknameValue.getText().toString().trim();
        if(TextUtils.isEmpty(mobile) || TextUtils.isEmpty(pwd) || TextUtils.isEmpty(nickname)) {
            mRegisterAction.setEnabled(false);
        } else {
            mRegisterAction.setEnabled(true);
        }
    }

    @OnClick(R.id.mRegisterAction)
    void register(View view) {

    }

    @OnClick(R.id.mAgreementAction)
    void agreement(View view) {

    }

    @Override
    public void afterTextChanged(Editable s) {
    }
}
