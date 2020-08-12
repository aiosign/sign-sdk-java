package com.github.aiosign.bean;

import com.github.aiosign.enums.PictureType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 侯存路
 * @description
 * @since 2020/8/11 16:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Picture {


    /**
     * 图片类型
     */
    private PictureType pictureType = PictureType.Url;
    /**
     * 图片
     */
    private Object content;


}
