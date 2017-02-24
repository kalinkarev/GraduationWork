package com.example.kalin.graduationwork;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.kalin.graduationwork.fragments.AddFragment;
import com.example.kalin.graduationwork.fragments.BaseFragment;
import com.example.kalin.graduationwork.fragments.HomeFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "Hello World!", Toast.LENGTH_SHORT).show();
                showFragmentAndAddToBackstack(new AddFragment());
            }
        });

        showFragment(new HomeFragment());
    }

    public void showFragment(BaseFragment fragment) {
        removeAllFragments();
        getFragmentManager().beginTransaction()
                .replace(R.id.fragment, fragment)
                .commitAllowingStateLoss();
    }

    public void showFragmentWithAdd(BaseFragment fragment) {
        getFragmentManager().beginTransaction()
                .add(R.id.fragment, fragment)
                .commitAllowingStateLoss();
    }

    public boolean isSameFragmentShowing(BaseFragment fragment) {
        return getFragmentManager().findFragmentById(R.id.fragment) != null &&
                getFragmentManager().findFragmentById(R.id.fragment).getClass().equals(fragment.getClass());
    }

    public void showFragmentAndAddToBackstack(BaseFragment fragment) {
        if (isSameFragmentShowing(fragment)) {
            return;
        }
        getFragmentManager().beginTransaction()
                .replace(R.id.fragment, fragment)
                .addToBackStack(fragment.getClass().getName())
                .commitAllowingStateLoss();
    }

    public void removeAllFragments() {
        for (int i = 0; i < getFragmentManager()
                .getBackStackEntryCount(); ++i) {
            getFragmentManager().popBackStack();
        }
    }

    public void removeLastFragment() {
        getFragmentManager().popBackStack();
    }

    public void removeLastFragmentImmediate() {
        getFragmentManager().popBackStackImmediate();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //       getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

//        Calendar calendar = Calendar.getInstance();
//        calendar.get(Calendar.DATE);

        int id = item.getItemId();

//        switch (item.getItemId()) {
//            case R.id.action_see_nextdata:
//                Toast.makeText(getApplicationContext(), "You have clicked action_see_nextdata", Toast.LENGTH_SHORT).show();
//                return true;
//
//            case R.id.action_see_previousdata:
//                Toast.makeText(getApplicationContext(), "You have clicked action_see_previousdata", Toast.LENGTH_SHORT).show();
//
//                return true;
//
//            case R.id.action_see_the_data:
//                Toast.makeText(getApplicationContext(), "You have clicked action_see_the_data", Toast.LENGTH_SHORT).show();
//                return true;
//
//            default:
//                return super.onOptionsItemSelected(item);
//        }

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

//        return super.onOptionsItemSelected(item);
//    }

        return true;
    }
}
