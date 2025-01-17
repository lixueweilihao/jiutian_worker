package com.play.image.product;

import org.opencv.core.Core;
import org.opencv.core.Mat;

import java.util.List;
import com.play.image.GeneralUtils.*;
import com.play.image.GrayUtils.*;
import com.play.image.BinaryUtils.*;
import com.play.image.RemoveNoiseUtils.*;

public class TestProduct {

    public void init() {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public void testCut(){
        String imgPath = "C:/Users/X240/Desktop/opencv/product/p6.jpg";
        String destPath = "C:/Users/X240/Desktop/opencv/product/";

        Mat src = ImageOpencvUtils.matFactory(imgPath);

        src = ImageOpencvUtils.correct(src);

        GeneralUtils.saveImg(src , destPath + "correct.jpg");

        //灰度化
        src = GrayUtils.grayColByPartAdapThreshold(src);

        GeneralUtils.saveImg(src , destPath + "gray.jpg");

        //二值化
        src = BinaryUtils.binaryzation(src);

        GeneralUtils.saveImg(src , destPath + "binaryzation.jpg");

        //降噪
        src = RemoveNoiseUtils.connectedRemoveNoise(src , 100);

        GeneralUtils.saveImg(src , destPath + "removeNoise.jpg");

        //切割
        List<Mat> dst = ImageOpencvUtils.cut(src);

        for(int i = 0 ; i < dst.size() ; i++){
            GeneralUtils.saveImg(dst.get(i) , destPath + i + ".jpg");
        }

    }

    /**
     * 测试归一化
     */
    public void testResize(){
        for(int i = 0 ; i < 10 ; i++){
            String imgPath = "C:/Users/X240/Desktop/opencv/product/cut/"+i+".jpg";
            String destPath = "C:/Users/X240/Desktop/opencv/product/cut/";

            Mat src = ImageOpencvUtils.matFactory(imgPath);

            //灰度化
            src = ImageOpencvUtils.gray(src);

            //二值化
            src = ImageOpencvUtils.binaryzation(src);

            //归一化
            src = ImageOpencvUtils.resize(src);

            ImageOpencvUtils.saveImg(src , destPath + "resize-"+i+".jpg");

        }

    }
}
