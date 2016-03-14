package com.example.piter.wisielec;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class chooseCategory extends Activity {

    public static String []CATEGORIES;
    public static final String CATEGORY = "CATEGORY";

    private ListView categoriesListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_category);
        CATEGORIES = getResources().getStringArray(R.array.categories);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, CATEGORIES);

        categoriesListView = (ListView) findViewById(R.id.categoriesList_lv);
        categoriesListView.setAdapter(adapter);

        categoriesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemValue = (String) categoriesListView.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(), MainScreen.class);
                intent.putExtra(CATEGORY, itemValue);
                startActivity(intent);
            }
        });
    }
}
