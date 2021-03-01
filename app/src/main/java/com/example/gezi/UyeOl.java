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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class UyeOl extends AppCompatActivity {

    private FirebaseAuth auth;
    Button uyeol;
    EditText email;
    EditText parola;
    EditText parolaonay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uyeol);
        uyeol=findViewById(R.id.btnuyeol);
        email=findViewById(R.id.txtüyeemail);
        parola=findViewById(R.id.txtüyeparola);
        parolaonay=findViewById(R.id.txtüyeparolaonay);
        auth=FirebaseAuth.getInstance();

        uyeol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailal = email.getText().toString();
                String parolaal = parola.getText().toString();
                String parolaonayal = parolaonay.getText().toString();

                if(TextUtils.isEmpty(emailal)){
                    email.setError("Lütfen E-Mailinizi Giriniz");
                    return;
                }
                if(TextUtils.isEmpty(parolaal)){
                    parola.setError("Lütfen Şifrenizi Giriniz");
                    return;
                }
                if (parolaal.length()<6){
                    parola.setError("Şifreniz En az 6 Haneli Olmalıdır");
                    return;
                }
                if (!parolaal.equalsIgnoreCase(parolaonayal)){
                    parolaonay.setError("Şifreniz Uyuşmuyor");
                    return;
                }

                auth.createUserWithEmailAndPassword(emailal,parolaal).addOnCompleteListener(UyeOl.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(UyeOl.this, "Yeniden Deneyiniz",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(UyeOl.this, "Başarıyla Üye Oldunuz", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(UyeOl.this, GirisYap.class));
                            finish();
                        }
                    }
                });
            }
        });
    }
}
