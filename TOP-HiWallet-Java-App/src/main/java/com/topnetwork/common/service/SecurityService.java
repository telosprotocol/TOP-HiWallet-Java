package com.topnetwork.common.service;

import com.common.utils.MarkImageUtils;
import com.common.utils.RedisUtils;
import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.verify.StatusEnum;
import com.topnetwork.wallet.param.wallet.verifycode.VerifyParam;
import com.topnetwork.wallet.result.wallet.verify.SecurityMarkImageResult;
import com.topnetwork.wallet.result.wallet.verify.VerifyResult;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.gitee.magic.framework.base.constant.Config;
import com.gitee.magic.framework.head.exception.BusinessException;
import com.gitee.magic.core.exception.ApplicationException;
import com.gitee.magic.core.utils.StringUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service("securityService")
public class SecurityService {

    @Value("${securityService.cacheTime:600}")
    private Integer cacheTime;

    @Autowired
    protected RedisTemplate redisTemplate;

    /**
     * 篮子距离顶部的高度
     */
    private final int marginTop = 50;
    //篮子距离底部的高度
    private final int marginBottom = 240;
    //篮子距离左边的距离
    private final int marginLeft = 10;
    //篮子距离右边的距离
    private final int marginRight = 10;
    //篮子块大小
    private final int bucketSize = 396;
    //底图的大小
    private final int orgRoudeSize = 989;

    /**
     * 生成随机Image
     *
     * @param orgImage
     * @param bucket   篮子
     * @return
     */
    public SecurityMarkImageResult buildRandomImage(BufferedImage orgImage, BufferedImage bucket) {
        int width = orgImage.getWidth();
        int height = orgImage.getHeight();
        //1、获取原图判断宽度=989和长度=989
        if (width != orgRoudeSize || height != orgRoudeSize) {
            throw new ApplicationException("底图图片尺寸有误");
        }
        int bucketwidth = bucket.getWidth();
        int bucketheight = bucket.getHeight();
        //1、获取原图判断宽度=396和长度=396
        if (bucketwidth < bucketSize || bucketheight < bucketSize) {
            throw new ApplicationException("篮子图片尺寸有误");
        }
        Random random = new Random();
        //顶
        //2、假如是height的长度，块高bucketRoudeSize，上下预留marginBottom,marginTop
        int yaxis = random.nextInt(height - bucketSize - marginBottom - marginTop) + marginTop;
        //切图的长宽
        //3、随机生成Code及marginLeft-(width-bucketSize-marginRight-marginLeft)之间的随机数xVal
        int xRandom = random.nextInt(width - bucketSize - marginRight - marginLeft) + marginLeft;
        //按照坐标叠加
        MarkImageUtils.overlapImage(orgImage, bucket, xRandom, yaxis);
        //5、并存入Redis有效期1分钟
        String code = StringUtils.random();
        //取中心点
        BigDecimal xRate = new BigDecimal(String.valueOf(xRandom + bucketwidth / 2)).divide(new BigDecimal(String.valueOf(width)), 6, RoundingMode.HALF_UP);
        BigDecimal yRate = new BigDecimal(String.valueOf(yaxis + bucketheight / 2)).divide(new BigDecimal(String.valueOf(height)), 6, RoundingMode.HALF_UP);

        redisTemplate.opsForValue().set(getRedisKey(code + "x"), xRate, cacheTime, TimeUnit.SECONDS);
        redisTemplate.opsForValue().set(getRedisKey(code + "y"), yRate, cacheTime, TimeUnit.SECONDS);
        //针对此code的重试次数
        redisTemplate.opsForValue().set(getRedisKey(code), 1, cacheTime, TimeUnit.SECONDS);

        SecurityMarkImageResult result = new SecurityMarkImageResult();
        result.setCode(code);

        String s1 = resizeImageTo30K(orgImage);
        result.setOrgImage(s1);
//        result.setOrgImage(MarkImageUtils.imageToBase64(orgImage));
        return result;
    }

    /**
     * Description: 获取混淆的图片Y坐标
     *
     * @param yaxis
     * @param size
     * @param height
     * @return: int
     * @Author: Tyrone
     * @date: 2020/3/12
     **/
    private int getRemixedY(int yaxis, int size, int height) {
        Random random = new Random();
        int gap = yaxis - 10;
        if (gap > size) {
            //如果上面空间足够，在上面随机
            return random.nextInt(yaxis - 10 - size) + 10;
        } else {
            return random.nextInt(height - yaxis - 2 * size - 10) + size + yaxis;
        }
    }

    /**
     * 校验Code值是否正确
     *
     * @param code
     * @param xvalue
     * @param yaxis
     */
    public boolean checkImage(String code, BigDecimal xvalue, BigDecimal yaxis) {
        if (!redisTemplate.hasKey(getRedisKey(code + "x"))) {
            throw new BusinessException(CodeRes.CODE_16008);
        }
        if (!redisTemplate.hasKey(getRedisKey(code + "y"))) {
            throw new BusinessException(CodeRes.CODE_16008);
        }
        BigDecimal xRate = (BigDecimal) redisTemplate.opsForValue().get(getRedisKey(code + "x"));
        BigDecimal yRate = (BigDecimal) redisTemplate.opsForValue().get(getRedisKey(code + "y"));

        //误差在..像素以内
        if (xRate.subtract(xvalue).abs().compareTo(new BigDecimal("0.02")) > 0) {
            return false;
        }
        if (yRate.subtract(yaxis).abs().compareTo(new BigDecimal("0.02")) > 0) {
            return false;
        }
        redisTemplate.delete(getRedisKey(code + "x"));
        redisTemplate.delete(getRedisKey(code + "y"));
        redisTemplate.delete(getRedisKey(code));
        return true;
    }

    public static String resizeImageTo30K(BufferedImage src) {
        try {
            BufferedImage output = Thumbnails.of(src).size(989, 989).asBufferedImage();
            String base64 = imageToBase64(output);
            while (base64.length() - base64.length() / 8 * 2 > 30000) {
                output = Thumbnails.of(output).scale(0.7f).asBufferedImage();
                base64 = imageToBase64(output);
            }
            return MarkImageUtils.imageToBase64(output);
        } catch (Exception e) {
            throw new ApplicationException(e);
        }
    }

    public static String imageToBase64(BufferedImage bufferedImage) {
        Base64 encoder = new Base64();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "png", baos);
        } catch (IOException e) {
            throw new ApplicationException(e);
        }
        return new String(encoder.encode((baos.toByteArray())));
    }

    public VerifyResult verifyImage(VerifyParam verifyParam) {
        boolean b = checkImage(verifyParam.getCode(), verifyParam.getXaxis(), verifyParam.getYaxis());
        checkRetryTime(verifyParam.getCode(), b);
        VerifyResult verifyResult = new VerifyResult();
        verifyResult.setSuccess(StatusEnum.SUCCESS);
        return verifyResult;
    }

    public void checkRetryTime(String code, boolean b) {
        if (!b) {
            //如果验证失败
            if (!redisTemplate.hasKey(getRedisKey(code))) {
                //判断有没有key
                throw new BusinessException(CodeRes.CODE_16008);
            }
            Integer increment = (Integer) redisTemplate.opsForValue().get(getRedisKey(code)) + 1;
            redisTemplate.opsForValue().set(getRedisKey(code), increment, cacheTime, TimeUnit.SECONDS);
            if (increment > 3) {
                //重试三次后删除此code
                redisTemplate.delete(getRedisKey(code + "x"));
                redisTemplate.delete(getRedisKey(code + "y"));
                redisTemplate.delete(getRedisKey(code));
                throw new BusinessException(CodeRes.CODE_16009);
            } else {
                //重试不足三次可以重新使用此图片
                throw new BusinessException(CodeRes.CODE_16010);
            }
        }
    }

    public void check(String code, BigDecimal xvalue, BigDecimal yaxis) {
        if (!Config.isSystemFlag()) {
            return;
        }
        boolean b = checkImage(code, xvalue, yaxis);
        checkRetryTime(code, b);
    }

    private String getRedisKey(String str) {
        return RedisUtils.getVerifyKey(str);
    }
}
