package assignment.checkdo;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Settings extends Fragment {

    private FirebaseAuth mFirebaseAuth;     // 파이어베이스 인증
    private DatabaseReference mDatabaseRef; // 실시간 데이터베이스

    private TextView showEmail;
    private EditText newPwd;
    private Button changePwd, deleteAct;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_settings, container, false);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("CheckDo");

        showEmail = v.findViewById(R.id.showEmail);
        String userEmail = mFirebaseAuth.getCurrentUser().getEmail();
        showEmail.setText(userEmail);

        changePwd = v.findViewById(R.id.changePasswordBtn);
        deleteAct = v.findViewById(R.id.deleteAccountBtn);

        newPwd = v.findViewById(R.id.et_changePassword);

        changePwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFirebaseAuth.getCurrentUser().updatePassword(newPwd.getText().toString());
                Toast.makeText(getActivity(), "변경 성공! 다시 로그인 해주세요!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), EasyLogin.class);
                startActivity(intent);
                // Fragent를 Finish()하는 방법이다.
                getActivity().getSupportFragmentManager().beginTransaction().remove(Settings.this).commit();

            }
        });

        deleteAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFirebaseAuth.getCurrentUser().delete();
                Toast.makeText(getActivity(), "!회원탈퇴가 되었습니다!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), EasyLogin.class);
                startActivity(intent);
                // Fragent를 Finish()하는 방법이다.
                getActivity().getSupportFragmentManager().beginTransaction().remove(Settings.this).commit();

            }
        });
        return v;
    }
}