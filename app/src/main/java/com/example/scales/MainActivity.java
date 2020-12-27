package com.example.scales;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import static com.example.scales.Utils.showInfoDialog;

public class MainActivity extends AppCompatActivity {
    public NotesAdapter nObjNotesAdapter;
    public ScalesData nScaleData;
    String mSNACKBAR_MESSAGE, mSCALE, mCURRENT_SCALE, mNOTE;
    View mSbContainer, mPreviousView;
    TextView mTv_displayScale;
    private final String mKeyScale = "SCALE";
    private final String mKeyNote = "NOTE";
    private final String mAddMinor = "ADDMINOR";
    private boolean addMinor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();
        setDefault();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(mKeyNote, mNOTE);
        outState.putString(mKeyScale, mCURRENT_SCALE);
        outState.putBoolean(mAddMinor, addMinor);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mNOTE = savedInstanceState.getString(mKeyNote);
        mCURRENT_SCALE = savedInstanceState.getString(mKeyScale);
        addMinor = savedInstanceState.getBoolean(mAddMinor);

        findViewById(R.id.major).setBackgroundColor(getResources().getColor(R.color.colorDefaultBackground));
        setCurrentScale();
    }


    private void setDefault() {
        mNOTE = "A";
        nScaleData = new ScalesData();
        scaleButtonHandler(findViewById(R.id.major));
    }

    public void setupViews() {
        RecyclerView objRecyclerView = findViewById(R.id.recycler_view);
        objRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager objLayoutManager = new GridLayoutManager(this, 3);
        nObjNotesAdapter = new NotesAdapter();
        objRecyclerView.setLayoutManager(objLayoutManager);
        objRecyclerView.setAdapter(nObjNotesAdapter);

        mSbContainer = findViewById(R.id.activity_main);
        mTv_displayScale = findViewById(R.id.tv_display);
    }

    @SuppressLint("ResourceAsColor")
    public void noteButtonHandler(View view) {
        if (mPreviousView != null) {
            mPreviousView.setBackgroundColor((R.color.colorDefaultButtonBackground));
        }
        view.setBackgroundColor(getResources().getColor(R.color.colorCardBackground));
        mPreviousView = view;
        Button newView = (Button) view;
        mNOTE = newView.getText().toString();
        setCurrentScale();
    }

    private void setCurrentScale() {
        switch (mCURRENT_SCALE) {
            case "major": {
                mSCALE = nScaleData.major(mNOTE);
                mTv_displayScale.setText(mSCALE);
                findViewById(R.id.major).setBackgroundColor(getResources().getColor(R.color.colorCardBackground));
                Snackbar.make(mSbContainer, mNOTE + " Major", Snackbar.LENGTH_SHORT).show();
                break;
            }
            case "minor": {
                if (addMinor) {
                    mSCALE = nScaleData.melodicAndHarmonicMinor(mNOTE);
                } else {
                    mSCALE = nScaleData.minor(mNOTE);
                }
                mTv_displayScale.setText(mSCALE);
                findViewById(R.id.minor).setBackgroundColor(getResources().getColor(R.color.colorCardBackground));
                Snackbar.make(mSbContainer, mNOTE + " Minor", Snackbar.LENGTH_SHORT).show();
                break;
            }
            case "pentatonic_major": {
                mSCALE = nScaleData.pentatonicMajor(mNOTE);
                mTv_displayScale.setText(mSCALE);
                findViewById(R.id.pentatonic_major).setBackgroundColor(getResources().getColor(R.color.colorCardBackground));
                Snackbar.make(mSbContainer, mNOTE + " Pentatonic Major", Snackbar.LENGTH_SHORT).show();
                break;
            }
            case "pentatonic_minor": {
                mSCALE = nScaleData.pentatonicMinor(mNOTE);
                mTv_displayScale.setText(mSCALE);
                findViewById(R.id.pentatonic_minor).setBackgroundColor(getResources().getColor(R.color.colorCardBackground));
                Snackbar.make(mSbContainer, mNOTE + " Pentatonic Minor", Snackbar.LENGTH_LONG).show();
                break;
            }
        }
    }

    public void scaleButtonHandler(View view) {
        view.setBackgroundColor(getResources().getColor(R.color.colorCardBackground));
        Button newView = (Button) view;
        mSNACKBAR_MESSAGE = mNOTE + " " + newView.getText().toString();
        Snackbar.make(mSbContainer, mSNACKBAR_MESSAGE, Snackbar.LENGTH_SHORT).show();
        switch (view.getId()) {

            case R.id.major: {
                mCURRENT_SCALE = "major";
                mSCALE = nScaleData.major(mNOTE);
                mTv_displayScale.setText(mSCALE);
                findViewById(R.id.minor).setBackgroundColor(getResources().getColor(R.color.colorDefaultBackground));
                findViewById(R.id.pentatonic_major).setBackgroundColor(getResources().getColor(R.color.colorDefaultBackground));
                findViewById(R.id.pentatonic_minor).setBackgroundColor(getResources().getColor(R.color.colorDefaultBackground));
                break;
            }
            case R.id.minor: {
                mCURRENT_SCALE = "minor";
                if (addMinor) {
                    mSCALE = nScaleData.melodicAndHarmonicMinor(mNOTE);
                } else {
                    mSCALE = nScaleData.minor(mNOTE);
                }
                mTv_displayScale.setText(mSCALE);
                findViewById(R.id.major).setBackgroundColor(getResources().getColor(R.color.colorDefaultBackground));
                findViewById(R.id.pentatonic_major).setBackgroundColor(getResources().getColor(R.color.colorDefaultBackground));
                findViewById(R.id.pentatonic_minor).setBackgroundColor(getResources().getColor(R.color.colorDefaultBackground));
                break;
            }
            case R.id.pentatonic_major: {
                mCURRENT_SCALE = "pentatonic_major";
                mSCALE = nScaleData.pentatonicMajor(mNOTE);
                mTv_displayScale.setText(mSCALE);
                findViewById(R.id.minor).setBackgroundColor(getResources().getColor(R.color.colorDefaultBackground));
                findViewById(R.id.major).setBackgroundColor(getResources().getColor(R.color.colorDefaultBackground));
                findViewById(R.id.pentatonic_minor).setBackgroundColor(getResources().getColor(R.color.colorDefaultBackground));
                break;
            }
            case R.id.pentatonic_minor: {
                mCURRENT_SCALE = "pentatonic_minor";
                mSCALE = nScaleData.pentatonicMinor(mNOTE);
                mTv_displayScale.setText(mSCALE);
                findViewById(R.id.minor).setBackgroundColor(getResources().getColor(R.color.colorDefaultBackground));
                findViewById(R.id.pentatonic_major).setBackgroundColor(getResources().getColor(R.color.colorDefaultBackground));
                findViewById(R.id.major).setBackgroundColor(getResources().getColor(R.color.colorDefaultBackground));
                break;

            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        if(addMinor){
            MenuItem item = menu.findItem(R.id.action_minor);
            item.setChecked(true);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_about: {
                showInfoDialog(this, R.string.app_name, R.string.about_message);
                return true;
            }
            case R.id.action_minor:
                toggleMenuItem(item);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void toggleMenuItem(MenuItem item) {
        if (item.isChecked()) {
            item.setChecked(false);
            addMinor = false;
        } else {
            item.setChecked(true);
            addMinor = true;
        }
        if (mCURRENT_SCALE.equals("minor")) {
            setCurrentScale();
        }
    }
}
