package com.example.jenice.myplanapp.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;

import com.avos.avoscloud.AVCloudQueryResult;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.CloudQueryCallback;
import com.example.jenice.myplanapp.R;
import com.example.jenice.myplanapp.adapter.CardAdapter;
import com.example.jenice.myplanapp.entity.Card;
import com.example.jenice.myplanapp.task.updatePlanImgTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class activityFragment extends Fragment {

    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private List<ImageView> list = new ArrayList<ImageView>();
    private GridView main_list;
    private List<Card> MainList=new ArrayList<Card>();
    private View view;

    ImageView slide1;
    ImageView slide2;
    ImageView slide3;

    public activityFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_activity, container, false);
        slide1=new ImageView(view.getContext());
        slide1.setScaleType(ImageView.ScaleType.FIT_XY);
        slide2=new ImageView(view.getContext());
        slide2.setScaleType(ImageView.ScaleType.FIT_XY);
        slide3=new ImageView(view.getContext());
        slide3.setScaleType(ImageView.ScaleType.FIT_XY);

        ViewPaperInit();
        autoBanner();
        MainListInit();


        return view;
    }

    public void ViewPaperInit()
    {
        viewPager = (ViewPager)view.findViewById(R.id.vp);

        final List<Bitmap> bm_list=new ArrayList<Bitmap>();
        String cql = "select url from _File ";
        AVQuery.doCloudQueryInBackground(cql, new CloudQueryCallback<AVCloudQueryResult>() {
            @Override
            public void done(AVCloudQueryResult avCloudQueryResult, AVException e) {
                //开启异步线程加载图片
                new updatePlanImgTask(slide1).execute(avCloudQueryResult.getResults().get(0).getString("url"),avCloudQueryResult.getResults().get(0).getString("name"));
                new updatePlanImgTask(slide2).execute(avCloudQueryResult.getResults().get(1).getString("url"),avCloudQueryResult.getResults().get(1).getString("name"));
                new updatePlanImgTask(slide3).execute(avCloudQueryResult.getResults().get(2).getString("url"),avCloudQueryResult.getResults().get(2).getString("name"));
                FillList();
            }
        });
    }

    //根据图片填充list
    private void FillList(){
        list.add(slide1);
        list.add(slide2);
        list.add(slide3);

        adapter = new ViewPagerAdapter();
        viewPager.setAdapter(adapter);
    }


    private class ViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup view, int position, Object object) {
            // TODO Auto-generated method stub
            view.removeView(list.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup view, int position) {
            // TODO Auto-generated method stub
            view.addView(list.get(position));
            return list.get(position);
        }

    }

    //自动播放
    private void autoBanner() {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if (list.size() > 1) {
                    MainActivity parent = (MainActivity) getActivity();
                    //多于1个，才循环
                    parent.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            int index = viewPager.getCurrentItem();
                            index = (index + 1) % list.size();
                            viewPager.setCurrentItem(index, true);
                            //第二个参数布尔值：True to smoothly scroll to the new item, false to transition immediately
                        }
                    });
                }
            }
        };
        //开始一个定时任务,首次执行延迟3000毫秒调用run方法，执行后每2500毫秒执行一次
        timer.schedule(timerTask, 3000, 2500);
    }

    public void MainListInit()
    {
        String cql = "select * from UserCard";
        AVQuery.doCloudQueryInBackground(cql, new CloudQueryCallback<AVCloudQueryResult>() {
            @Override
            public void done(AVCloudQueryResult avCloudQueryResult, AVException e) {
                MainList = (List<Card>)avCloudQueryResult.getResults();
                CardAdapter group_adapter=new CardAdapter(view.getContext(),R.layout.user_card,MainList);
                main_list=(GridView)view.findViewById(R.id.main_list);
                main_list.setAdapter(group_adapter);
            }
        }, Card.class);
    }


}
