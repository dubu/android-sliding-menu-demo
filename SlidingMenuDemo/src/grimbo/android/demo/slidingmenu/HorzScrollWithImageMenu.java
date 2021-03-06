/*
 * #%L
 * SlidingMenuDemo
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2012 Paul Grime
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package grimbo.android.demo.slidingmenu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * This example uses a FrameLayout to display a menu View and a HorizontalScrollView (HSV).
 * 
 * The HSV has a transparent View as the first child, which means the menu will show through when the HSV is scrolled.
 */
public class HorzScrollWithImageMenu extends Activity {
    ArrayList<ListItemData> itemlist;
    ListView listView ;
    MyHorizontalScrollView scrollView;
    View menu;
    View app;
    ImageView btnSlide;
    boolean menuOut = false;
    Handler handler = new Handler();
    int btnWidth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //맨앞페이지
        //startActivity(new Intent(this, SplashActivity.class));

        LayoutInflater inflater = LayoutInflater.from(this);
        setContentView(inflater.inflate(R.layout.horz_scroll_with_image_menu, null));

        scrollView = (MyHorizontalScrollView) findViewById(R.id.myScrollView);
        menu = findViewById(R.id.menu);
        app = inflater.inflate(R.layout.horz_scroll_app, null);
        ViewGroup tabBar = (ViewGroup) app.findViewById(R.id.tabBar);


        //ViewUtils.initListView(this, listView, "Item ", 30, android.R.layout.simple_list_item_1);

        itemlist = new ArrayList<ListItemData>();
        for (int i=0 ; i <10 ;i++){
            ListItemData cusItemData = new ListItemData();
            cusItemData.setTitleText(String.format("Line list item %d",i));
            cusItemData.setDiscriptionText(String.format("Dapibus, neque id cursus faucibus, tortor neque egestas  %d", i));
            cusItemData.setImageResourceId(R.drawable.img_profile);
            itemlist.add(cusItemData);

            ListItemData cusItemSecData = new ListItemData();
            cusItemSecData.setTitleText(String.format("Second 3-Line %d",i));
            cusItemSecData.setDiscriptionText(String.format("Dapibus, neque id cursus faucibus, tortor neque egestas arrangement... including ", i));
            cusItemSecData.setImageResourceId(R.drawable.img_profile02);
            itemlist.add(cusItemSecData);
        }

        listView = (ListView) app.findViewById(R.id.mainList);
        listView.setAdapter(new ListVeiwAdapter(this, itemlist, this));

        // menu list
        btnSlide = (ImageView) tabBar.findViewById(R.id.BtnSlide);
        ImageView imageView01 = (ImageView)findViewById(R.id.menu1);
        btnSlide.setOnClickListener(new HorzScrollWithListMenu.ClickListenerForScrolling(scrollView, menu));
        imageView01.setOnClickListener(new HorzScrollWithListMenu.ClickListenerForScrolling(scrollView, menu));

        // menu Field
        ImageView imageView02 = (ImageView)findViewById(R.id.menu2);
        imageView02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(scrollView.getContext(), HorzScrollWithFieldMenu.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }
        });

        // menu Setting& Controls
        ImageView imageView03 = (ImageView)findViewById(R.id.menu3);
        imageView03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(scrollView.getContext(), HorzScrollWithSettingMenu.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }
        });

        // menu Video
        ImageView imageView04 = (ImageView)findViewById(R.id.menu4);
        imageView04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(scrollView.getContext(), HorzScrollWithVideoMenu.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }
        });

        // menu Profile
        ImageView imageView05 = (ImageView)findViewById(R.id.menu5);
        imageView05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(scrollView.getContext(), HorzScrollWithProfileMenu.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }
        });




        // Create a transparent view that pushes the other views in the HSV to the right.
        // This transparent view allows the menu to be shown when the HSV is scrolled.
        View transparent = new TextView(this);
        transparent.setBackgroundColor(android.R.color.transparent);

        final View[] children = new View[] { transparent, app };

        // Scroll to app (view[1]) when layout finished.
        int scrollToViewIdx = 1;
        scrollView.initViews(children, scrollToViewIdx, new HorzScrollWithListMenu.SizeCallbackForMenu(btnSlide));
    }
}
