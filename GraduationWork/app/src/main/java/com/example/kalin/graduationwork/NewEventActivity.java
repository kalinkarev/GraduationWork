package com.example.kalin.graduationwork;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Kalin on 19.1.2017 г..
 */

public class NewEventActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);
    }
}

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        //toolbar.setNavigationIcon(R.drawable.common_google_signin_btn_text_light_pressed);
//        toolbar.setNavigationIcon(R.drawable.arrow_left);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //Toast.makeText(MainActivity.this, "Hello World!", Toast.LENGTH_SHORT).show();
//
//
//            }
//        });
//    }

//    public void showFragment(AddFragment fragment) {
//        removeAllFragments();
//        getFragmentManager().beginTransaction()
//                .replace(R.id.fragment, fragment)
//                .commitAllowingStateLoss();
//    }
//
//    public void showFragmentWithAdd(AddFragment fragment) {
//        getFragmentManager().beginTransaction()
//                .add(R.id.fragment, fragment)
//                .commitAllowingStateLoss();
//    }
//
//    public boolean isSameFragmentShowing(BaseFragment fragment) {
//        return getFragmentManager().findFragmentById(R.id.fragment) != null &&
//                getFragmentManager().findFragmentById(R.id.fragment).getClass().equals(fragment.getClass());
//    }
//
//    public void showFragmentAndAddToBackstack(BaseFragment fragment) {
//        if (isSameFragmentShowing(fragment)) {
//            return;
//        }
//        getFragmentManager().beginTransaction()
//                .replace(R.id.fragment, fragment)
//                .addToBackStack(fragment.getClass().getName())
//                .commitAllowingStateLoss();
//    }
//
//    public void removeAllFragments() {
//        for (int i = 0; i < getFragmentManager()
//                .getBackStackEntryCount(); ++i) {
//            getFragmentManager().popBackStack();
//        }
//    }
//
//    public void removeLastFragment() {
//        getFragmentManager().popBackStack();
//    }
//
//    public void removeLastFragmentImmediate() {
//        getFragmentManager().popBackStackImmediate();
//    }
//
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
////        if (id == R.id.action_settings) {
////            return true;
////        }
//
//        return super.onOptionsItemSelected(item);
//    }
//}