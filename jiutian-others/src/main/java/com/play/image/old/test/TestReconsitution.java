package com.play.image.old.test;

import com.play.image.old.rewrite.ImgUtils_rewrite;
import org.opencv.core.Core;
import org.opencv.core.Mat;

import java.util.List;

/**
 * 测试 重构的工具类
 *
 * @author admin
 */
public class TestReconsitution {

    public void init() {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    /**
     * 测试灰度话
     */
    public void testgray() {
        String imgPath = "C:/Users/admin/Desktop/opencv/open/reconsitution/123.jpg";
        String destPath = "C:/Users/admin/Desktop/opencv/open/reconsitution/";
        Mat src = ImgUtils_rewrite.matFactory(imgPath);
        src = ImgUtils_rewrite.gray(src);
        ImgUtils_rewrite.saveImg(src, destPath + "gray.jpg");
    }

    /**
     * 测试二值化
     */
    public void testBinary() {
        String imgPath = "C:/Users/admin/Desktop/opencv/open/reconsitution/123.jpg";
        String destPath = "C:/Users/admin/Desktop/opencv/open/reconsitution/";
        Mat src = ImgUtils_rewrite.matFactory(imgPath);
        src = ImgUtils_rewrite.gray(src);
        src = ImgUtils_rewrite.binaryzation(src);
        ImgUtils_rewrite.saveImg(src, destPath + "binary.jpg");
    }

    /**
     * 测试降噪
     */
    public void testRemoveNoise() {
        String imgPath = "C:/Users/admin/Desktop/opencv/open/reconsitution/123.jpg";
        String destPath = "C:/Users/admin/Desktop/opencv/open/reconsitution/";
        Mat src = ImgUtils_rewrite.matFactory(imgPath);
        src = ImgUtils_rewrite.gray(src);
        src = ImgUtils_rewrite.binaryzation(src);
        src = ImgUtils_rewrite.navieRemoveNoise(src, 1);
        src = ImgUtils_rewrite.connectedRemoveNoise(src, 1.0);
        ImgUtils_rewrite.saveImg(src, destPath + "removenoise.jpg");
    }

    /**
     * 测试水平切割
     */
    public void testX() {
        String imgPath = "C:/Users/admin/Desktop/opencv/open/reconsitution/123.jpg";
        String destPath = "C:/Users/admin/Desktop/opencv/open/reconsitution/";
        Mat src = ImgUtils_rewrite.matFactory(imgPath);
        src = ImgUtils_rewrite.gray(src);
        src = ImgUtils_rewrite.binaryzation(src);
        src = ImgUtils_rewrite.navieRemoveNoise(src, 1);
        src = ImgUtils_rewrite.connectedRemoveNoise(src, 1.0);
        List<Mat> list = ImgUtils_rewrite.cutImgX(src);
        for (int i = 0; i < list.size(); i++) {
            ImgUtils_rewrite.saveImg(list.get(i), destPath + "X-" + i + ".jpg");
        }
    }

    /**
     * 测试垂直切割
     */
    public void testY() {
        String imgPath = "C:/Users/admin/Desktop/opencv/open/reconsitution/X-1.jpg";
        String destPath = "C:/Users/admin/Desktop/opencv/open/reconsitution/";
        Mat src = ImgUtils_rewrite.matFactory(imgPath);
        src = ImgUtils_rewrite.gray(src);
        src = ImgUtils_rewrite.binaryzation(src);
        src = ImgUtils_rewrite.navieRemoveNoise(src, 1);
        src = ImgUtils_rewrite.connectedRemoveNoise(src, 1.0);
        List<Mat> list = ImgUtils_rewrite.cutImgY(src);
        for (int i = 0; i < list.size(); i++) {
            ImgUtils_rewrite.saveImg(list.get(i), destPath + "Y-" + i + ".jpg");
        }
    }

    /**
     * 测试归一化
     */
    public void testResize() {
        String imgPath = "C:/Users/admin/Desktop/opencv/open/reconsitution/Y-0.jpg";
        String destPath = "C:/Users/admin/Desktop/opencv/open/reconsitution/";
        Mat src = ImgUtils_rewrite.matFactory(imgPath);
        src = ImgUtils_rewrite.resize(src);
        ImgUtils_rewrite.saveImg(src, destPath + "resize.jpg");
    }

    /**
     * 测试矫正图像--旋转图像
     */
    public void testCorrect() {
        String imgPath = "C:/Users/admin/Desktop/opencv/open/x/x8.jpg";
        String destPath = "C:/Users/admin/Desktop/opencv/open/x/";
        Mat src = ImgUtils_rewrite.matFactory(imgPath);
        src = ImgUtils_rewrite.gray(src);
        src = ImgUtils_rewrite.correct(src);
        ImgUtils_rewrite.saveImg(src, destPath + "correct.jpg");
    }

    /**
     * 测试透视变换矫正图像
     */
    public void testWarpPerspective() {
        String imgPath = "C:/Users/admin/Desktop/opencv/open/reconsitution/correct.jpg";
        String destPath = "C:/Users/admin/Desktop/opencv/open/reconsitution/";
        Mat src = ImgUtils_rewrite.matFactory(imgPath);
        src = ImgUtils_rewrite.warpPerspective(src);
        ImgUtils_rewrite.saveImg(src, destPath + "warpPerspective.jpg");
    }

}
