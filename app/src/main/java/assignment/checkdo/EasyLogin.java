package assignment.checkdo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EasyLogin extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;     // 파이어베이스 인증
    private DatabaseReference mDatabaseRef; // 실시간 데이터베이스
    private EditText mEtEmail, mEtPwd;      // 로그인 입력필드

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_login);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("CheckDo");

        mEtEmail = findViewById(R.id.edit_email);
        mEtPwd = findViewById(R.id.edit_password);

        Button loginbtn = (Button)findViewById(R.id.login);
        Button registerbtn = (Button)findViewById(R.id.gotoregister);



        // 로그인 요청 버튼
        loginbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String strEmail = mEtEmail.getText().toString();    // 사용자가 입력한 이메일 값을 가져옴
                String strPwd = mEtPwd.getText().toString();        // 사용자가 입력한 비밀번호 값을 가져옴

                mFirebaseAuth.signInWithEmailAndPassword(strEmail, strPwd).addOnCompleteListener(EasyLogin.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            // 로그인 성공!!
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            Toast.makeText(EasyLogin.this, "로그인에 성공했습니다.", Toast.LENGTH_SHORT).show();
                            finish();   // 현재 액티비티 파괴
                        }
                        else {
                            Toast.makeText(EasyLogin.this, "!!로그인 실패!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });


        // 회원가입 창으로 이동
        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}