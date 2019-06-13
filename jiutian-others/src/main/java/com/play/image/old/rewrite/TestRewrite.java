package com.play.image.old.rewrite;

import org.opencv.core.Core;
import org.opencv.core.Mat;

import java.util.List;

public class TestRewrite {

    public void init() {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

/**
 * 测试水平切割算法
 */
    public void testCut() {
        String imgPath = "C:/Users/admin/Desktop/opencv/open/test/12/4.jpg";
        String destPath = "C:/Users/admin/Desktop/opencv/open/test/12/";
        Mat src = ImgUtils_rewrite.matFactory(imgPath);
        src = ImgUtils_rewrite.gray(src);
        src = ImgUtils_rewrite.binaryzation(src);
//ImgUtils_rewrite.saveImg(src, destPath + "binary.jpg");

        src = ImgUtils_rewrite.navieRemoveNoise(src, 1);
        src = ImgUtils_rewrite.connectedRemoveNoise(src, 10.0);

//ImgUtils_rewrite.saveImg(src, destPath + "removeNoise.jpg");

        List<Mat> x = ImgUtils_rewrite._cutImgX(src);
        System.out.println(x.size());
        for (int i = 0; i < x.size(); i++) {
            ImgUtils_rewrite.saveImg(x.get(i), destPath + "x-" + i + ".jpg");
        }


    }


/**
 * 测试垂直切割
 */
    public void testY() {
        String imgPath = "C:/Users/admin/Desktop/opencv/open/test/12/x-1.jpg";
        String destPath = "C:/Users/admin/Desktop/opencv/open/test/12/";
        Mat src = ImgUtils_rewrite.matFactory(imgPath);
        List<Mat> y = ImgUtils_rewrite._cutImgY(src);
        System.out.println(y.size());
        for (int i = 0; i < y.size(); i++) {
            ImgUtils_rewrite.saveImg(y.get(i), destPath + "y-" + i + ".jpg");
        }

    }

/**
 * 测试寻找轮廓
 */
    public void testFindContours() {
        String imgPath = "C:/Users/admin/Desktop/opencv/open/test/12/x-1.jpg";
        String destPath = "C:/Users/admin/Desktop/opencv/open/test/12/";
        Mat src = ImgUtils_rewrite.matFactory(imgPath);

        ImgUtils_rewrite.cut(src);
    }


}
