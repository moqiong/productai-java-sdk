package cn.productai.api.examples;

import cn.productai.api.core.IWebClient;
import cn.productai.api.core.enums.DetectType;
import cn.productai.api.core.enums.LanguageType;
import cn.productai.api.core.exceptions.ClientException;
import cn.productai.api.pai.entity.detect.DetectByImageFileRequest;
import cn.productai.api.pai.entity.detect.DetectResponse;
import cn.productai.api.pai.response.DetectResult;

import java.io.File;

/**
 * Created by Zhong Wang on 2017/7/5.
 * 3C电器检测与定位
 * https://api-doc.productai.cn/doc/pai.html#3C电器检测与定位
 */
public class DetectExample implements IExample {

    @Override
    public void run(IWebClient client) {

        System.out.println("==>  Demo - 3C电器检测与定位  <==");
        System.out.println("See https://api-doc.productai.cn/doc/pai.html#3C电器检测与定位 for details.\r\n");

        DetectByImageFileRequest request = new DetectByImageFileRequest(DetectType.ThreeCAndElectronics);
        request.setImageFile(new File(this.getClass().getResource("/").getPath() + "images/iphone.jpg"));
        request.setLanguage(LanguageType.Chinese);
        request.setLoc("0.2-0.2-1-1");

        try {
            DetectResponse response = client.getResponse(request);

            System.out.println("==============================Result==============================");

            for (DetectResult r : response.getDetectedBoxes()) {
                // access the response directly
                System.out.println(String.format("%s - %s", r.getType(), r.getScore()));
            }

            System.out.println("==============================Result==============================");
        } catch (cn.productai.api.core.exceptions.ServerException e) {
            System.out.println(String.format("ServerException occurred. ErrorCode: %s \r\n ErrorMessage: %s",
                    e.getErrorCode(),
                    e.getErrorMessage()));
            e.printStackTrace();

        } catch (ClientException e) {
            System.out.println(String.format("ClientException occurred. ErrorCode: %s \r\n ErrorMessage: %s \r\n RequestId: %s",
                    e.getErrorCode(),
                    e.getErrorMessage(),
                    e.getRequestId()));
            e.printStackTrace();

        } catch (Exception e) {
            System.out.println(String.format("%s occurred. ErrorMessage: %s", e.getClass().getName(), e.getMessage()));
            e.printStackTrace();
        }
    }
}
