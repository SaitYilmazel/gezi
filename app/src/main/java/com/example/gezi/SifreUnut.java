package com.example.gezi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class SifreUnut extends AppCompatActivity {

    private FirebaseAuth auth;
    Button sifirla;
    EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sifre_unut);
        auth=FirebaseAuth.getInstance();
        sifirla=findViewById(R.id.btnsifresifirla);
        email=findViewById(R.id.txtsifreemail);

        sifirla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailal=email.getText().toString().trim();

                if (TextUtils.isEmpty(emailal)) {
                    email.setError("Lütfen E-Mail Adresini Giriniz");
                    return;
                }
                auth.sendPasswordResetEmail(emailal).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(SifreUnut.this, "Yeni Parola İçin Gerekli Bağlantı Mailinize Gönderildi!", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(SifreUnut.this,GirisYap.class));
                                } else {
                                    Toast.makeText(SifreUnut.this, "Mail Gönderilemedi Tekrar Deneyin!", Toast.LENGTH_SHORT).show();
                                }


                            }
                        });
            }
        });
    }

}