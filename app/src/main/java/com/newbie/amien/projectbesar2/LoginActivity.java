package com.newbie.amien.projectbesar2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.newbie.amien.projectbesar2.data.rest.ApiClient;
import com.newbie.amien.projectbesar2.data.rest.ApiInterface;
import com.newbie.amien.projectbesar2.data.retrofit.GetUser;
import com.newbie.amien.projectbesar2.data.retrofit.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {



    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean status=attemptLogin();
//                Toast.makeText(LoginActivity.this, ""+status, Toast.LENGTH_SHORT).show();
                if(!status==true){
                    ApiInterface mApiInterface = ApiClient.login().create(ApiInterface.class);
                    Call<GetUser> kostCall = mApiInterface.postLogin(new User(mEmailView.getText().toString(),mPasswordView.getText().toString()));
                    kostCall.enqueue(new Callback<GetUser>() {
                        @Override
                        public void onResponse(Call<GetUser> call, Response<GetUser> response) {
//                            Toast.makeText(getApplicationContext(), ""+response.body().getJumlah(), Toast.LENGTH_SHORT).show();
                            if(response.body().getJumlah()>0) {
//                                Toast.makeText(LoginActivity.this, "" + response.body().getPemilik().get(0).getId(), Toast.LENGTH_SHORT).show();
                                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                                SharedPreferences.Editor editor = pref.edit();
                                editor.putString("Id", response.body().getUser().get(0).getId());
                                editor.putString("Email", response.body().getUser().get(0).getEmail());
                                editor.putString("Nama", response.body().getUser().get(0).getNamaUser());
                                editor.putString("Alamat", response.body().getUser().get(0).getAlamatUser());
                                editor.putString("Telepon", response.body().getUser().get(0).getTelepon());
                                editor.putString("Password", response.body().getUser().get(0).getPassword());
                                editor.commit();
                                Toast.makeText(LoginActivity.this, "" + response.body().getUser().get(0).getId(), Toast.LENGTH_SHORT).show();
                                SharedPreferences pref2 = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                                Toast.makeText(LoginActivity.this, "" + pref2.getString("Id", null), Toast.LENGTH_SHORT).show();
                                finish();
                            }else{
                                Toast.makeText(LoginActivity.this, "Login Gagal", Toast.LENGTH_SHORT).show();
                            }



                        }

                        @Override
                        public void onFailure(Call<GetUser> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Failde     "+t, Toast.LENGTH_SHORT).show();
                            Log.e("Retrofit Get", t.toString());
                        }
                    });

                }
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        Button registerbutton = (Button) findViewById(R.id.register_button);
        registerbutton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Register.class);
                startActivity(i);
            }
        });
    }



    private boolean attemptLogin() {

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        }
        return cancel;
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() >= 1;
    }





}

