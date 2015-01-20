package com.fichardu.ease;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends ActionBarActivity {

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private View mAnimView;
    private static final String IN_PG_NAME = "com.fichardu.interpolator.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mAnimView = findViewById(R.id.anim_view);
        mDrawerList.setAdapter(
                new ArrayAdapter<String>(this, R.layout.drawer_list_item, mInterpolators));
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectDrawerItem(position);
            }
        });
    }

    private void selectDrawerItem(int position) {
        String name = mInterpolators[position];
        TimeInterpolator interpolator = null;
        try {
            Class clazz = Class.forName(IN_PG_NAME + name);
            interpolator = (TimeInterpolator) clazz.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        mAnimView.setTranslationY(0);
        Animator animator = ObjectAnimator.ofFloat(mAnimView, "translationY", 0f, dp2px(this, 80));
        animator.setDuration(1000);
        if (interpolator != null) {
            animator.setInterpolator(interpolator);
        }
        animator.start();

    }

    private static float dp2px(Context context, float dipValue) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, metrics);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private static final String[] mInterpolators = new String[]{
            "EaseInQuadInterpolator",
            "EaseOutQuadInterpolator",
            "EaseInOutQuadInterpolator",
            "EaseInQuartInterpolator",
            "EaseOutQuartInterpolator",
            "EaseInOutQuartInterpolator",
            "EaseInQuintInterpolator",
            "EaseOutQuintInterpolator",
            "EaseInOutQuintInterpolator",
            "EaseInCircInterpolator",
            "EaseOutCircInterpolator",
            "EaseInOutCircInterpolator",
            "EaseInCubicInterpolator",
            "EaseOutCubicInterpolator",
            "EaseInOutCubicInterpolator",
            "EaseInExpoInterpolator",
            "EaseOutExpoInterpolator",
            "EaseInOutExpoInterpolator",
            "EaseInBackInterpolator",
            "EaseOutBackInterpolator",
            "EaseInOutBackInterpolator",
            "EaseInElasticInterpolator",
            "EaseOutElasticInterpolator",
            "EaseInOutElasticInterpolator",
            "EaseInBounceInterpolator",
            "EaseOutBounceInterpolator",
            "EaseInOutBounceInterpolator",
    };
}
