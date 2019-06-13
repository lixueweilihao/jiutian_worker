package com.play.image.BinaryUtils;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import com.play.image.GeneralUtils.*;
import  com.play.image.GrayUtils.*;

public class TestBinaryUtils {
    public static void init() {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
    /**
     * 测试opencv自带的二值化
     */
    public static void testBinaryNative() {
        String imgPath = "D:/WorkerCode/play_demo_work/image/src/main/java/BinaryUtils/test1/1.png";
        String destPath = "D:/WorkerCode/play_demo_work/image/src/main/java/BinaryUtils/test2/";
        Mat src = GeneralUtils.matFactory(imgPath);
        src = GrayUtils.grayColByAdapThreshold(src);
        src = BinaryUtils.binaryNative(src);
        GeneralUtils.saveImg(src, destPath + "binaryNative.png");
    }

    /**
     * 测试自定义二值化
     */
    public void testBinaryzation() {
        String imgPath = "H:/ideaCode/opencvHandleImg/Opencv/src/main/java/BinaryUtils/test1/1.png";
        String destPath = "H:/ideaCode/opencvHandleImg/Opencv/src/main/java/BinaryUtils/test2/";
        Mat src = GeneralUtils.matFactory(imgPath);
        src = GrayUtils.grayColByAdapThreshold(src);
        src = BinaryUtils.binaryzation(src);
        GeneralUtils.saveImg(src, destPath + "binaryzation.png");
    }

    /**
     * 先局部自适应，然后在其基础上进行全局自适应阈值
     */
    public void testPartBinaryzation() {
//        String imgPath = "H:/ideaCode/opencvHandleImg/Opencv/src/BinaryUtils/img/test1/1.png";
//        String destPath = "H:/ideaCode/opencvHandleImg/Opencv/src/BinaryUtils/img/test2/";
        for (int i = 1; i <= 183; i++) {
            String imgPath = "C:/Users/X240/Desktop/cut/car1/" + i + ".png";
            String destPath = "C:/Users/X240/Desktop/cut/car1binary/";
            Mat src = GeneralUtils.matFactory(imgPath);
            src = GrayUtils.grayColByAdapThreshold(src);
            src = BinaryUtils.binaryNative(src);
            src = BinaryUtils.partBinaryzation(src);
            src = BinaryUtils.binaryzation(src);
            GeneralUtils.saveImg(src, destPath + "partBinaryzation" + i + ".png");
        }
    }

    public static void main(String[] args) {
        init();
        testBinaryNative();
    }


}
