package com.zimu.my2018.appcase.scenic.scenicevaluate;

import com.zimu.my2018.quyouapi.data.scenic.ScenicCommentData;
import com.zimu.my2018.quyoulib.core.di.mvp.BasePresenter;

import java.io.File;
import java.util.List;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/25
 */
public class ScenicEvaluateContract {

    interface View {
        void onAddScenicCommentSuccess(ScenicCommentData response);
        void onAddScenicCommentFailed(String message);
    }

    interface Presenter extends BasePresenter<View> {
        void addScenicComment(int scenic_id, float score, String comment_content, List<File> files);
    }
}
