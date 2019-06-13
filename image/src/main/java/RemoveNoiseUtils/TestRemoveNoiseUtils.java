package RemoveNoiseUtils;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import GeneralUtils.*;
import GrayUtils.*;
import BinaryUtils.*;
/**
 * 测试降噪
 */
public class TestRemoveNoiseUtils {
    public static void init() {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static void main(String[] args) {
        init();
        testEghitRemoveNoise();
    }

    /**
     * 测试8邻域降噪
     */
    public static void testEghitRemoveNoise(){
        String imgPath = "D:/WorkerCode/play_demo_work/image/src/main/RemoveNoiseUtils/test1/1.png";
        String destPath = "D:/WorkerCode/play_demo_work/image/src/main/RemoveNoiseUtils/test2/";

        Mat src = GeneralUtils.matFactory(imgPath);

        src = GrayUtils.grayColByPartAdapThreshold(src);

        src = BinaryUtils.binaryzation(src);

        // 8邻域降噪
        src = RemoveNoiseUtils.eghitRemoveNoise(src , 1);

        GeneralUtils.saveImg(src , destPath + "eghitRemoveNoise.png");

    }

    /**
     * 连通域降噪
     */
    public void testConnectedRemoveNoise(){
        String imgPath = "H:/ideaCode/opencvHandleImg/Opencv/src/RemoveNoiseUtils/test1/1.png";
        String destPath = "H:/ideaCode/opencvHandleImg/Opencv/src/RemoveNoiseUtils/test2/";

        Mat src = GeneralUtils.matFactory(imgPath);

        src = GrayUtils.grayColByPartAdapThreshold(src);

        src = BinaryUtils.binaryzation(src);

        // 连通域降噪
        src = RemoveNoiseUtils.connectedRemoveNoise(src , 1);

        GeneralUtils.saveImg(src , destPath + "connectedRemoveNoise.png");

    }

}
