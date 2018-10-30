package com.example.admin.galgegit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.admin.galgegit.dummy.DummyContent;

public class Main3Activity extends AppCompatActivity implements View.OnClickListener, BlankFragment.OnFragmentInteractionListener, ItemFragment.OnListFragmentInteractionListener

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

    }


        @java.lang.Override
        public void onFragmentInteraction(Uri uri) {

        }

        @java.lang.Override
        public void onListFragmentInteraction(DummyContent.DummyItem item) {

        }

        @Override
        public void onClick(View v) {


        }


}

