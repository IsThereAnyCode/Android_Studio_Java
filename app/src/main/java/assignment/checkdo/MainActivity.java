package assignment.checkdo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private DoSwipe doswipe;
    private Settings settings;
    private FragmentTransaction transaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();

        doswipe = new DoSwipe();
        settings = new Settings();

        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.main_container, doswipe).commitAllowingStateLoss();
    }
    public void onClick(View view)
    {
        transaction = fragmentManager.beginTransaction();

        switch(view.getId())
        {
            case R.id.button:
                transaction.replace(R.id.main_container, doswipe).commitAllowingStateLoss();
                break;
            case R.id.button3:
                transaction.replace(R.id.main_container, settings).commitAllowingStateLoss();
                break;
        }
    }
}