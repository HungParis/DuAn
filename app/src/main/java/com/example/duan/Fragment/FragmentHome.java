package com.example.duan.Fragment;


import android.app.ActionBar;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.bumptech.glide.Glide;

import com.example.duan.MODE.MyAdapter;
import com.example.duan.MODE.Review;
import com.example.duan.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;
import com.android.volley.Request;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import kotlin.jvm.functions.Function2;
import technolifestyle.com.imageslider.FlipperLayout;
import technolifestyle.com.imageslider.FlipperView;
import kotlin.Unit;
/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHome extends Fragment {

    RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_fragment_home, container, false);
        final FlipperLayout flipperLayout = v.findViewById(R.id.fli);
        recyclerView = v.findViewById(R.id.recyeview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        LinearLayoutManager horizontalLayoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);
        Review itemsData[] = {new Review(R.drawable.ic_gio, "aa"),
                new Review(R.drawable.ic_account, "aa"),
                new Review(R.drawable.ic_home, "aaa"),
                new Review(R.drawable.ic_dangxuat, "aaa"),
                new Review(R.drawable.ic_news, "aaa"),
                new Review(R.drawable.ic_thongtin, "aaa")};

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        Toolbar toolbar =(Toolbar)v.findViewById(R.id.toolbar);
        //for crate home button
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        String[] url = {"https://www.tampacific.vn/wp-content/uploads/2019/01/tat-ca-ma-giam-gia-accesstrade-cong-nghe-tampacific.png",
                "https://cdn1.vienthonga.vn/image/2018/12/4/100000_05-12-vinid-rewwardsavt.jpg",
                "https://cdn.tgdd.vn/Files/2017/08/10/1011941/1_760x367.jpg"
        };
        for (int i=0; i< url.length; i++) {
            FlipperView view = new FlipperView(getContext());
            if(i==0){
                view.setDescription("Mã Giảm Giá Công Nghệ")
                        .setDescriptionBackgroundColor(Color.TRANSPARENT)
                        .resetDescriptionTextView();
            }
            if(i==1){
                view.setDescription("Ưu đãi giảm giá 3% với VinID Rewards")
                        .setDescriptionTextColor(Color.BLACK)
                        .resetDescriptionTextView();
            }
            if(i==2){
                view.setDescription("Top 5 phụ kiện đang giảm giá đến 49%")
                        .setDescriptionBackgroundColor(Color.TRANSPARENT)
                        .resetDescriptionTextView();
            }
            flipperLayout.setCircleIndicatorHeight(150);
            flipperLayout.setCircularIndicatorLayoutParams(10,10);
            flipperLayout.setCircleIndicatorWidth(300);
            flipperLayout.removeCircleIndicator();
            flipperLayout.showCircleIndicator();
            view.setOnFlipperClickListener(new FlipperView.OnFlipperClickListener() {
                @Override
                public void onFlipperClick(@NotNull FlipperView flipperView) {

                }
            });
            try {
                view.setImage(url[i], new Function2<ImageView, Object, Unit>() {
                    @Override
                    public Unit invoke(ImageView imageView, Object image) {
                        Picasso.get().load((String)image).into(imageView);
                        return Unit.INSTANCE;
                    }
                });
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            flipperLayout.addFlipperView(view);
            MyAdapter mAdapter = new MyAdapter(itemsData);
            recyclerView.setAdapter(mAdapter);
        }

        return v;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu3cham, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.btthongtin) {
            return true;
        }
        if (id == R.id.btdangxuat) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
