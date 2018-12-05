package com.zimu.my2018.appcase.scenic.scenicevaluate;

import com.zimu.my2018.appmodel.repository.ScenicRepository;
import com.zimu.my2018.quyouapi.core.netcallback.RequestCallback;
import com.zimu.my2018.quyouapi.data.scenic.ScenicCommentData;

import java.io.File;
import java.util.List;

import javax.inject.Inject;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/25
 */
public class ScenicEvaluatePresenter implements ScenicEvaluateContract.Presenter {

    @Inject
    ScenicRepository mScenicRepository ;

    private ScenicEvaluateContract.View mView;

    @Inject
    public ScenicEvaluatePresenter() {

    }

    @Override
    public void attachView(ScenicEvaluateContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    // scenic comment
    @Override
    public void addScenicComment(int scenic_id, float score, String comment_content, List<File> files) {
        mScenicRepository.addScenicComment(scenic_id, score, comment_content, files, new RequestCallback<ScenicCommentData>() {
            @Override
            public void onSuccess(ScenicCommentData response) {
                if (null != mView) {
                    mView.onAddScenicCommentSuccess(response);
                }
            }

            @Override
            public void onError(String msg) {
                if (null != mView) {
                    mView.onAddScenicCommentFailed(msg);
                }
            }
        });
    }
}
