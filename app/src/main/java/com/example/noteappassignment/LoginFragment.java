package com.example.noteappassignment;

import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.noteappassignment.databinding.FragmentLoginBinding;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.BeginSignInResult;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;

public class LoginFragment extends Fragment {

    private static final int REQ_ONE_TAP = 100;

    private FragmentLoginBinding fragmentLoginBinding;
    private SignInClient oneTapClient;
    private BeginSignInRequest signInRequest;


    public LoginFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // Initializing SignInClient and BeginSignInRequest for google signing
        String webClientId = "262400419019-d2rprkqu8l6tu7cek26739ksc9jnqelh.apps.googleusercontent.com";

        oneTapClient = Identity.getSignInClient(requireActivity());
        signInRequest = BeginSignInRequest.builder()
                .setPasswordRequestOptions(BeginSignInRequest.PasswordRequestOptions.builder()
                        .setSupported(true)
                        .build())
                .setGoogleIdTokenRequestOptions(BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                        .setSupported(true)
                        .setServerClientId(webClientId)
                        .setFilterByAuthorizedAccounts(false)
                        .build())
                .setAutoSelectEnabled(true)
                .build();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentLoginBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);

        return fragmentLoginBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Setting Google login method
        fragmentLoginBinding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //calling Google login method
                googleLogin();
            }
        });
    }

    private void googleLogin() {
        oneTapClient.beginSignIn(signInRequest)
                .addOnSuccessListener(requireActivity(), new OnSuccessListener<BeginSignInResult>() {
                    @Override
                    public void onSuccess(BeginSignInResult result) {
                        try {
                            startIntentSenderForResult(
                                    result.getPendingIntent().getIntentSender(), REQ_ONE_TAP,
                                    null, 0, 0, 0, null);
                        } catch (IntentSender.SendIntentException e) {
                            Log.e("LoginFragment", "Couldn't start One Tap UI: " + e.getLocalizedMessage());
                        }
                    }
                })
                .addOnFailureListener(requireActivity(), new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("LoginFragment", "Google Sign-In failed: " + e.getLocalizedMessage());
                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_ONE_TAP) {
            try {
                SignInCredential credential = oneTapClient.getSignInCredentialFromIntent(data);
                String idToken = credential.getGoogleIdToken();
                String username = credential.getId();
                String password = credential.getPassword();

                saveLoginState(true ,username);

                if (idToken != null) {
                    Log.i("LoginFragment", "Got ID token.");

                } else if (password != null) {
                    Log.i("LoginFragment", "Got password.");
                }
            } catch (ApiException e) {
                Log.e("LoginFragment", "Google Sign-In failed: " + e.getLocalizedMessage());
            }
        }
    }

    public void saveLoginState(Boolean isLogin ,String username){

        SharedPreferences loginState = requireActivity().getSharedPreferences("checkLogin", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= loginState.edit();

        editor.putBoolean("isLogin", isLogin);
        editor.putString("getUid", username);
        editor.apply();

        Intent i = new Intent(requireContext(), MainActivity.class);
        i.putExtra("fragNum",0);
        startActivity(i);
    }
}