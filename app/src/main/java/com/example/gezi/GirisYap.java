package com.example.gezi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class GirisYap extends AppCompatActivity {

    private FirebaseAuth auth;
    Button giris;
    EditText email;
    EditText parola;
    TextView üyeol;
    TextView sifredegistir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_girisyap);
        üyeol=findViewById(R.id.txtüye);
        giris=findViewById(R.id.btngirisyap);
        email=findViewById(R.id.txtemail);
        parola=findViewById(R.id.txtparola);
        sifredegistir=findViewById(R.id.sifredeg);
        auth=FirebaseAuth.getInstance();

        sifredegistir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GirisYap.this,SifreUnut.class));
            }
        });

        üyeol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GirisYap.this, UyeOl.class));
            }
        });

        giris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailal = email.getText().toString();
                String parolaal = parola.getText().toString();

                if (TextUtils.isEmpty(emailal)) {
                    email.setError("Lütfen E-Mailinizi Giriniz");
                    return;
                }
                if (TextUtils.isEmpty(parolaal)) {
                    parola.setError("Lütfen Şifrenizi Giriniz");
                    return;
                }

                auth.signInWithEmailAndPassword(emailal, parolaal).addOnCompleteListener(GirisYap.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(GirisYap.this, AnaSayfa.class));
                        }
                        else {
                            Toast.makeText(GirisYap.this, "E-Mail veya Şifre Hatalı", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


    }

}