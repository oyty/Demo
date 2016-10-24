package com.circle.stu.ui.activity;

import android.content.Intent;
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

public class LoginActivity extends BaseActivity implements TextWatcher {

    @BindView(R.id.mAccountValue)
    EditText mAccountValue;
    @BindView(R.id.mPasswordValue)
    EditText mPasswordValue;
    @BindView(R.id.mLoginAction)
    Button mLoginAction;
    @BindView(R.id.mNewAccountAction)
    View mNewAccountAction;
    @BindView(R.id.mForgetPwdAction)
    View mForgetPwdAction;


    @Override
    protected void initToolbar() {
        setTitle(R.string.login);
    }

    @Override
    protected int initViewId() {
        return R.layout.activity_login;
    }

    @Override
    protected void process() {

        mAccountValue.addTextChangedListener(this);
        mPasswordValue.addTextChangedListener(this);
    }

    @OnClick(R.id.mLoginAction)
    void login(View view) {

    }

    @OnClick(R.id.mNewAccountAction)
    void createNewAccount(View view) {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String account = mAccountValue.getText().toString().trim();
        String pwd = mPasswordValue.getText().toString().trim();
        if(TextUtils.isEmpty(account) || TextUtils.isEmpty(pwd)) {
            mLoginAction.setEnabled(false);
        } else {
            mLoginAction.setEnabled(true);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
    }
}
