package com.common.utils;

import com.gitee.magic.core.exception.ApplicationException;
import com.gitee.magic.core.utils.codec.Base64;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Iterator;

public class MarkImageUtils {

	/**
	 * 对图片裁剪，并把裁剪后的图片返回
	 */
	public static BufferedImage getMarkImage(BufferedImage image, int x, int y, int length, int width) {
		InputStream is = null;
		ImageInputStream iis = null;
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(image, "png", os);
			is = new ByteArrayInputStream(os.toByteArray());
			/*
			 * 返回包含所有当前已注册 ImageReader 的 Iterator，这些 ImageReader 声称能够解码指定格式。 参数：formatName -
			 * 包含非正式格式名称 . （例如 "jpeg" 或 "tiff"）等 。
			 */
			Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName("png");
			ImageReader reader = it.next();
			// 获取图片流
			iis = ImageIO.createImageInputStream(is);
			/*
			 * <p>iis:读取源.true:只向前搜索 </p>.将它标记为 ‘只向前搜索'。 此设置意味着包含在输入源中的图像将只按顺序读取，可能允许 reader
			 * 避免缓存包含与以前已经读取的图像关联的数据的那些输入部分。
			 */
			reader.setInput(iis, true);
			/*
			 * <p>描述如何对流进行解码的类<p>.用于指定如何在输入时从 Java Image I/O
			 * 框架的上下文中的流转换一幅图像或一组图像。用于特定图像格式的插件 将从其 ImageReader 实现的 getDefaultReadParam
			 * 方法中返回 ImageReadParam 的实例。
			 */
			ImageReadParam param = reader.getDefaultReadParam();

			/*
			 * 图片裁剪区域。Rectangle 指定了坐标空间中的一个区域，通过 Rectangle 对象 的左上顶点的坐标（x，y）、宽度和高度可以定义这个区域。
			 */
			Rectangle rect = new Rectangle(x, y, length, width);

			// 提供一个 BufferedImage，将其用作解码像素数据的目标。
			param.setSourceRegion(rect);
			/*
			 * 使用所提供的 ImageReadParam 读取通过索引 imageIndex 指定的对象，并将 它作为一个完整的 BufferedImage 返回。
			 */
			BufferedImage bi = reader.read(0, param);
			return bi;
		} catch (IOException e) {
			throw new ApplicationException(e);
		} finally {
			if (is != null)
				try {
					is.close();
				} catch (IOException e) {
					throw new ApplicationException(e);
				}
			if (iis != null)
				try {
					iis.close();
				} catch (IOException e) {
					throw new ApplicationException(e);
				}
		}

	}
	
	 /**
     * 获取扣图区域坐标，原理是用二维数组表示原图的所有像素坐标，通过抠图坐标和长宽确定抠图区域坐标，并将抠图区域坐标的值赋值为1，其他区域0.
     * @param targetLength 原图的长度
     * @param targetWidth  原图的宽度
     * @param x            裁剪区域的x坐标
     * @param y            裁剪区域的y坐标
     * @param length       抠图的长度
     * @param width        抠图的宽度
     * @return
     */
	public static int [][] getCutAreaData(int targetLength,int targetWidth,int x,int y ,int length,int width){
        int[][] data = new int[targetLength][targetWidth];
        for (int i=0;i<targetLength;i++){//1280
            for(int j=0;j<targetWidth;j++){//720
                if(i<x+length&&i>=x&&j<y+width&&j>y){
                    data[i][j]=1;
                }else {
                    data[i][j]=0;
                }
            }
        }
        return data;
    }
    
    /**
     * 通过二维数组坐标给原图的抠图区域做色彩处理，就可以得到带有阴影的原图。
     * @param oriImage
     * @param templateImage
     */
    public static void cutByTemplate(BufferedImage oriImage,int[][] templateImage){
        for (int i = 0; i < oriImage.getWidth(); i++) {
            for (int j = 0; j < oriImage.getHeight(); j++) {
                int rgb = templateImage[i][j];
                // 原图中对应位置变色处理
                int rgb_ori = oriImage.getRGB(i,  j);
                if (rgb == 1) {
                    //颜色处理
                    int r = (0xff & rgb_ori);
                    int g = (0xff & (rgb_ori >> 8));
                    int b = (0xff & (rgb_ori >> 16));
                    int Gray = r + (g << 8) + (b << 16) + (400 << 24);
//                    int Gray = (r*2 + g*5 + b*1) >> 3;
                    //原图对应位置颜色变化
                    oriImage.setRGB( i, j, Gray);
                }
            }
        }
    }
	public static final void overlapImage(BufferedImage backgroudImg, BufferedImage coinImg,int x,int y) {
		try {
			Graphics2D g = backgroudImg.createGraphics();
			g.drawImage(coinImg, x, y, coinImg.getWidth(), coinImg.getHeight(), null);
			g.dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    public static String imageToBase64(BufferedImage image){
        ByteArrayOutputStream bao=new ByteArrayOutputStream();
        try {
			ImageIO.write(image,"png",bao);
		} catch (IOException e) {
			throw new ApplicationException(e);
		}
        return Base64.encode(bao.toByteArray());
    }
    
    public static BufferedImage base64StringToImage(String base64String) {
    	 ByteArrayInputStream bais = new ByteArrayInputStream(Base64.decode(base64String));
         try {
			return ImageIO.read(bais);
		} catch (IOException e) {
			throw new ApplicationException(e);
		}
    }
    
    public static void main(String[] args) throws Exception {
    	File file=new File("/Users/start/Desktop/111.png");
    	System.out.println(file.exists());
        BufferedImage image=(BufferedImage)ImageIO.read(file);
        BufferedImage ib1= MarkImageUtils.getMarkImage(image,10,10,40,40);
        System.out.println(image.getWidth());
        System.out.println(image.getHeight());
        int[][] temp= MarkImageUtils.getCutAreaData(image.getWidth(),image.getHeight(),10,10,40,40);
        MarkImageUtils.cutByTemplate(image, temp);
        File outputfile1 = new File("/Users/start/Desktop/2222.png");
        ImageIO.write(image,"png",outputfile1);
        
        //保存图片
        File outputfile = new File("/Users/start/Desktop/saved.png");
        ImageIO.write(ib1,"png",outputfile);
	}



}
