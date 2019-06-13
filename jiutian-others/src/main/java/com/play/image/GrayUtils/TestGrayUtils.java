package com.play.image.GrayUtils;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import  com.play.image.GeneralUtils.*;
/**
 * 测试灰度化
 */
public class TestGrayUtils {
    public void init() {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
    /**
     * 测试opencv自带的灰度化方法
     */
    public void testGrayNative() {
        String imgPath = "H:/ideaCode/opencvHandleImg/Opencv/src/GrayUtils/img/test3/test1/1.png";
        String destPath = "H:/ideaCode/opencvHandleImg/Opencv/src/GrayUtils/img/test3/test2/";
        Mat src = GeneralUtils.matFactory(imgPath);
        src = GrayUtils.grayNative(src);
        GeneralUtils.saveImg(src, destPath + "grayNative.png");
    }

    /**
     * 测试细粒度灰度化方法
     * 均值灰度化减噪
     */
    public void testGrayColByMidle() {
        String imgPath = "H:/ideaCode/opencvHandleImg/Opencv/src/GrayUtils/img/test3/test1/1.png";
        String destPath = "H:/ideaCode/opencvHandleImg/Opencv/src/GrayUtils/img/test3/test2/";
        Mat src = GeneralUtils.matFactory(imgPath);
        src = GrayUtils.grayColByMidle(src);
        GeneralUtils.saveImg(src, destPath + "grayRowByMidle.png");
    }


    /**
     * 测试细粒度灰度化方法
     * k值灰度化减噪
     */
    public void testgrayColByKLargest() {
        String imgPath = "H:/ideaCode/opencvHandleImg/Opencv/src/GrayUtils/img/test3/test1/1.png";
        String destPath = "H:/ideaCode/opencvHandleImg/Opencv/src/GrayUtils/img/test3/test2/";

        Mat src = GeneralUtils.matFactory(imgPath);

        src = GrayUtils.grayColByKLargest(src);

        GeneralUtils.saveImg(src, destPath + "grayRowByKLargest.png");
    }

    /**
     * 测试细粒度灰度化方法
     * 局部自适应阀值灰度化减噪
     */
    public void testgrayColByPartAdapThreshold() {
        String imgPath = "H:/ideaCode/opencvHandleImg/Opencv/src/GrayUtils/img/test3/test1/1.png";
        String destPath = "H:/ideaCode/opencvHandleImg/Opencv/src/GrayUtils/img/test3/test2/";
        Mat src = GeneralUtils.matFactory(imgPath);
        src = GrayUtils.grayColByPartAdapThreshold(src);
        GeneralUtils.saveImg(src, destPath + "grayColByPartAdapThreshold.png");
    }


    /**
     * 测试细粒度灰度化方法
     * 全局自适应阀值灰度化减噪
     */
    public void testgrayColByAdapThreshold() {
        String imgPath = "H:/ideaCode/opencvHandleImg/Opencv/src/GrayUtils/img/test3/test1/1.png";
        String destPath = "H:/ideaCode/opencvHandleImg/Opencv/src/GrayUtils/img/test3/test2/";

        Mat src = GeneralUtils.matFactory(imgPath);

        src = GrayUtils.grayColByAdapThreshold(src);

        GeneralUtils.saveImg(src, destPath + "grayColByAdapThreshold.png");
    }

    public static void main(String[] args) {

    }

}
