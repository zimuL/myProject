package com.zimu.my2018.appcase.scenic.scenicevaluate;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.EditText;

import com.zimu.my2018.appcase.R;
import com.zimu.my2018.appcase.R2;
import com.zimu.my2018.quyouapi.data.scenic.ScenicCommentData;
import com.zimu.my2018.quyoulib.utils.FileUtils;
import com.zimu.my2018.quyoulib.utils.ImageUtil;
import com.zimu.my2018.quyouui.core.activity.PhotoDetailActivity;
import com.zimu.my2018.quyouui.core.base.BaseHasPhotoActivity;
import com.zimu.my2018.quyouui.widget.data.photo.NetPhotoData;
import com.zimu.my2018.quyouui.widget.pic.PicHorizontalEditView;
import com.zimu.my2018.quyouui.widget.ratingbar.RatingBar;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * scenic evaluate
 */
public class ScenicEvaluateActivity extends BaseHasPhotoActivity implements ScenicEvaluateContract.View{

    @BindView(R2.id.rb_evaluate_class)
    RatingBar rb_evaluate_class;
    @BindView(R2.id.et_evaluate_content)
    EditText et_evaluate_content;
    @BindView(R2.id.choosePic)
    PicHorizontalEditView mChoosePic;

    @Inject
    ScenicEvaluatePresenter mPresenter;

    private List<NetPhotoData> photoList = new ArrayList<>();
    private List<File> lists = new ArrayList<>() ;
    private String mContent;
    private float mStarNum;
    private int mScenicId;


    public static Intent getDiyIntent(Context context, int scenicId) {
        Intent intent = new Intent(context, ScenicEvaluateActivity.class);
        intent.putExtra("scenicId", scenicId);
        return intent;
    }

    @Override
    protected void initParam() {
        super.initParam();
        Intent intent = getIntent();
        mScenicId = intent.getIntExtra("scenicId", 0);
    }

    @Override
    protected void initPresenter() {
        mPresenter.attachView(this);
    }

    @Override
    protected void destroyPresenter() {
        mPresenter.detachView();
    }

    @Override
    protected int getAppView() {
        return R.layout.activity_scenic_evaluate_view;
    }

    @Override
    protected void initView() {
        setTitle("景区评价", "保存", v -> {
            getParam();
        });
    }

    private void getParam() {
        mStarNum = rb_evaluate_class.getRatingNum();
        mContent = et_evaluate_content.getText().toString().trim();
        lists = getPhoneLists(photoList);
        mPresenter.addScenicComment(mScenicId, mStarNum, mContent, lists);
    }

    private List<File> getPhoneLists(List<NetPhotoData> photoList) {
        for (int i = 0; i< photoList.size();i++) {
            NetPhotoData data = photoList.get(i);
            String localPath = data.getLocalPath();
            Bitmap bitmap = FileUtils.decodeFile(localPath);
            File file = ImageUtil.compressImage(bitmap);
            lists.add(file);
        }
        return lists;
    }

    @Override
    protected void initListener() {
        super.initListener();
        mChoosePic.setListener(new PicHorizontalEditView.OptionListener() {
            @Override
            public void addPic() {
                gotoMultChoosePic(4, local -> {
                    showChoosePic(local);
                });
            }

            @Override
            public void itemClick(int position, NetPhotoData netPicInfo) {
                List<NetPhotoData> netList = mChoosePic.getList();
                startActivityForResult(PhotoDetailActivity.getDiyIntent(
                        ScenicEvaluateActivity.this, netList, position, true, true),
                        0x0100);
            }
        });
    }

    @Override
    protected void loadData() {
        showContent(true);
    }

    private void showChoosePic(List<NetPhotoData> local) {
        List<NetPhotoData> photoDataList = mChoosePic.getList();
        photoDataList.addAll(0, local);
        for (int i = 0; i< photoDataList.size();i++) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(photoDataList.get(i).getLocalPath(), options);
            int height = options.outHeight;
            int width = options.outWidth;
            NetPhotoData data = photoDataList.get(i);
            data.setHeight(height);
            data.setWidth(width);
            photoDataList.set(i, data);
        }
        mChoosePic.setData(photoDataList);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0x0100 && resultCode == RESULT_OK) {
            if (data != null) {
                photoList = (List<NetPhotoData>) data.getSerializableExtra("result");
                mChoosePic.setData(photoList);
            }
        }
    }

    @Override
    public void onAddScenicCommentSuccess(ScenicCommentData response) {
        int type = response.getType();
        if (1 == type) {
            showToast("评论成功！");
            finish();
        }
    }

    @Override
    public void onAddScenicCommentFailed(String message) {
        showToast("评论失败！");
        Log.e("hxl", "onAddScenicCommentFailed: " + message);
    }
}
