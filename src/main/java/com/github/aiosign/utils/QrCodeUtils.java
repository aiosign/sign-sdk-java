package com.github.aiosign.utils;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * 二维码条形码编码解码
 *
 * @author modificial
 * @since 2020/4/20
 */
public class QrCodeUtils {

    /**
     * 条形码编码
     *
     * @param contents    内容,必须为13位阿拉伯数字
     * @param width       宽度
     * @param height      高度
     * @param imgPathName 生成条形码的路径
     * @throws WriterException
     * @throws IOException
     */
    public static void barEncode(String contents, int width, int height, String imgPathName) {

        if (contents != null && contents.length() != 13) {
            throw new IllegalArgumentException("the encode contents must be 13 digits");
        }
        int codeWidth = 3 + (7 * 6) + 5 + (7 * 6) + 3;
        codeWidth = Math.max(codeWidth, width);
        BitMatrix bitMatrix;
        try {
            bitMatrix = new MultiFormatWriter().encode(contents,
                    BarcodeFormat.EAN_13, codeWidth, height, null);
            MatrixToImageWriter.writeToFile(bitMatrix, "png", new File(imgPathName));
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 条形码解码
     *
     * @param imgPathName
     * @return String
     * @throws IOException
     * @throws NotFoundException
     */
    public static String barDecode(String imgPathName)
            throws IOException, NotFoundException {

        BufferedImage image = ImageIO.read(new File(imgPathName));
        if (image == null) {
            throw new IOException("the decode image may be not exit.imgPathName:" + imgPathName);
        }
        LuminanceSource source = new BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

        Result result = new MultiFormatReader().decode(bitmap, null);
        return result.getText();
    }

    /**
     * 二维码编码
     *
     * @param contents    编码内容
     * @param width       二维码宽度
     * @param height      二维码高度
     * @param imgPathName 生成图片的路径
     */
    public static void qrEncode(String contents, int width, int height, String imgPathName) {
        Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
        // 指定纠错等级
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        // 指定编码格式
        hints.put(EncodeHintType.CHARACTER_SET, StandardCharsets.UTF_8.name());
        hints.put(EncodeHintType.MARGIN, 1);
        BitMatrix bitMatrix;
        try {
            bitMatrix = new MultiFormatWriter().encode(contents,
                    BarcodeFormat.QR_CODE, width, height, hints);
            MatrixToImageWriter.writeToFile(bitMatrix, "png", new File(imgPathName));
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 二维码解码
     *
     * @param imgPathName
     * @return String
     */
    public static String qrDecode(String imgPathName)
            throws NotFoundException, IOException {

        BufferedImage image = ImageIO.read(new File(imgPathName));
        if (image == null) {
            throw new IOException("the decode image may be not exit.imgPathName:" + imgPathName);
        }
        LuminanceSource source = new BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

        Hashtable<DecodeHintType, Object> hints = new Hashtable<>();
        hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");

        Result result = new MultiFormatReader().decode(bitmap, hints);
        return result.getText();
    }

    /**
     * @param args
     */
    public static void main(String[] args)
            throws IOException, NotFoundException {
//        // 条形码
//        String imgPath = "/Users/modificial/Desktop/pyu_phone_number.png";
//        String contents = "8618798613948";
//        int width = 105, height = 50;
//        QrCodeUtils.barEncode(contents, width, height, imgPath);
//        System.out.println("finished pyu_phone_number.png encode. ");
//
//        String decodeContent = QrCodeUtils.barDecode(imgPath);
//        System.out.println("barDecode解码内容如下： " + decodeContent);
//        System.out.println("finished zxing EAN-13 decode. ");
//
//        // 二维码
//        String imgPath2 = "/Users/modificial/Desktop/pyu_contact.png";
//        StringBuffer buffer = new StringBuffer()
//                .append("Hello pyu, welcome to shenzhen!")
//                .append(" ")
//                .append("Blog [ https://www.cnblogs.com/gzu-link-pyu ]")
//                .append(" ")
//                .append("Email [ 1203114557@qq.com ]")
//                .append(" ");
//        int width2 = 300, height2 = 300;
//        QrCodeUtils.qrEncode(buffer.toString(), width2, height2, imgPath2);
//        System.out.println("finished pyu_contact.png encode.");
//
//        String decodeContent2 = QrCodeUtils.qrDecode(imgPath2);
//        System.out.println("qrDecode解码内容如下： " + decodeContent2);
//        System.out.println("finished zxing decode.");
        String content = "http://192.168.1.32/group1/M00/00/00/wKgBIF6dab2AMgPvAAHOmVJwe6Q257.pdf";
        qrEncode(content, 300, 300, "/Users/modificial/Desktop/barcode.png");
    }

    /**
     * 条形码编码
     *
     * @param contents    内容,必须为13位阿拉伯数字
     * @param width       宽度
     * @param height      高度
     * @param imgPathName 生成条形码的路径
     * @throws WriterException
     * @throws IOException
     */
    public static void barEncode(String contents, int width, int height, String imgPathName, BarcodeFormat format) {
        int codeWidth = 3 + (7 * 6) + 5 + (7 * 6) + 3;
        codeWidth = Math.max(codeWidth, width);
        BitMatrix bitMatrix;
        try {
            bitMatrix = new MultiFormatWriter().encode(contents,
                    format, codeWidth, height, null);
            MatrixToImageWriter.writeToFile(bitMatrix, "png", new File(imgPathName));
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 生成二维码
     * @param contents
     * @param qrWidth
     * @param qrHeight
     * @return
     * @throws WriterException
     */
    public static BitMatrix encode(String contents, int qrWidth, int qrHeight) throws WriterException {
        final Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.MARGIN, 1);
        return new QRCodeWriter().encode(contents, BarcodeFormat.QR_CODE, qrWidth, qrHeight, hints);
    }

    /**
     * 获取二维码base64图片
     * @param content
     * @param qrWidth
     * @param qrHeight
     * @return
     */
    public static String getBase64(String content, int qrWidth, int qrHeight)  {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            BitMatrix bitMatrix=encode(content,qrWidth,qrHeight);
            BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix, new MatrixToImageConfig(Color.BLACK.getRGB(), Color.WHITE.getRGB()));
            //转换成png格式的IO流
            ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        byte[] bytes = byteArrayOutputStream.toByteArray();
        String base64 = Base64.getEncoder().encodeToString(bytes).trim();
        base64 = "data:image/png;base64," + base64;
        return base64;
    }

}