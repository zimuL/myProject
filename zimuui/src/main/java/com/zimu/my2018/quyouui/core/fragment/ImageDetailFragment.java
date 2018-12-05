package com.zimu.my2018.quyouui.core.fragment;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.zimu.my2018.quyouui.R;

import java.io.File;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/26
 */
public class ImageDetailFragment extends Fragment {

    private PhotoView mImageView;
    private ProgressBar progressBar;

    private PhotoViewAttacher mAttacher;

    private String mImageUrl;
    private boolean isLocal;

    public static ImageDetailFragment newInstance(String imageUrl, boolean isLocal) {
        final ImageDetailFragment f = new ImageDetailFragment();
        final Bundle args = new Bundle();
        args.putString("url", imageUrl);
        args.putBoolean("isLocal", isLocal);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        mImageUrl = arguments != null ? arguments.getString("url") : null;
        isLocal = arguments.getBoolean("isLocal");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_image_detail_view,
                container, false);
        mImageView = v.findViewById(R.id.image);
        progressBar = v.findViewById(R.id.loading);
        mImageView.setOnClickListener(v1 -> getActivity().finish());
        mAttacher = new PhotoViewAttacher(mImageView);
        mAttacher.setOnPhotoTapListener((arg0, arg1, arg2) -> getActivity().finish());
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Uri uri = null;
        if (isLocal) {
            File file = new File(mImageUrl) ;
            uri = Uri.fromFile(file);
        } else {
            uri = Uri.parse(mImageUrl);
        }
        Glide.with(this)
                .load(uri)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        try {
                            e.printStackTrace();
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                        mImageView.setImageResource(R.mipmap.icon_pic_default_bg);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }
                }).into(mImageView);


    }
}
