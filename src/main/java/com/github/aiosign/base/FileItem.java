package com.github.aiosign.base;

import com.github.aiosign.utils.MineUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 文件元数据。
 *
 * @author modificial
 * @version $Id: $Id
 */
public class FileItem {

    private String fileName;
    private String mimeType;
    private byte[] content;
    private File file;

    /**
     * 基于本地文件的构造器。
     *
     * @param file 本地文件
     */
    public FileItem(File file) {
        this.file = file;
    }

    /**
     * 基于文件绝对路径的构造器。
     *
     * @param filePath 文件绝对路径
     */
    public FileItem(String filePath) {
        this(new File(filePath));
    }

    /**
     * 基于文件名和字节流的构造器。
     *
     * @param fileName 文件名
     * @param content  文件字节流
     */
    public FileItem(String fileName, byte[] content) {
        this.fileName = fileName;
        this.content = content;
    }

    /**
     * 基于文件名、字节流和媒体类型的构造器。
     *
     * @param fileName 文件名
     * @param content  文件字节流
     * @param mimeType 媒体类型
     */
    public FileItem(String fileName, byte[] content, String mimeType) {
        this(fileName, content);
        this.mimeType = mimeType;
    }

    /**
     * <p>Getter for the field <code>fileName</code>.</p>
     *
     * @return a {@link String} object.
     */
    public String getFileName() {
        if (this.fileName == null && this.file != null && this.file.exists()) {
            this.fileName = file.getName();
        }
        return this.fileName;
    }

    /**
     * <p>Getter for the field <code>mimeType</code>.</p>
     *
     * @return a {@link String} object.
     * @throws IOException if any.
     */
    public String getMimeType() throws IOException {
        if (this.mimeType == null) {
            this.mimeType = MineUtils.getMimeType(getContent());
        }
        return this.mimeType;
    }

    // /**
    //  * <p>Getter for the field <code>content</code>.</p>
    //  *
    //  * @return an array of {@link byte} objects.
    //  * @throws java.io.IOException if any.
    //  */
    // public byte[] getContent() throws IOException {
    //     if (this.content == null && this.file != null && this.file.exists()) {
    //         InputStream in = null;
    //         ByteArrayOutputStream out = null;
    //
    //         try {
    //             in = new FileInputStream(this.file);
    //             out = new ByteArrayOutputStream();
    //             int ch;
    //             while ((ch = in.read()) != -1) {
    //                 out.write(ch);
    //             }
    //             this.content = out.toByteArray();
    //         } finally {
    //             if (out != null) {
    //                 out.close();
    //             }
    //             if (in != null) {
    //                 in.close();
    //             }
    //         }
    //     }
    //     byte[] content = this.content;
    //     if (null != content && content.length > 0) {
    //         return content;
    //     }
    //     throw new RuntimeException("获取文件流失败,请检查文件是否存在");
    // }

    /**
     * 使用NIO方式
     * <p>Getter for the field <code>content</code>.</p>
     *
     * @return an array of {@link byte} objects.
     * @throws IOException if any.
     */
    public byte[] getContent() throws IOException {
        if (this.content == null && this.file != null && this.file.exists()) {
            //获取文件输入流
            FileInputStream inputStream = new FileInputStream(this.file);
            //从文件输入流获取通道
            FileChannel inputStreamChannel = inputStream.getChannel();

            //创建一个byteBuffer，小文件所以就直接一次读取，不分多次循环了
            ByteBuffer byteBuffer = ByteBuffer.allocate((int)this.file.length());
            this.content = byteBuffer.array();
            //把输入流通道的数据读取到缓冲区
            inputStreamChannel.read(byteBuffer);
            //关闭通道
            inputStream.close();
            inputStreamChannel.close();
        }
        byte[] content = this.content;
        if (null != content && content.length > 0) {
            return content;
        }
        throw new RuntimeException("获取文件流失败,请检查文件是否存在");
    }

}
