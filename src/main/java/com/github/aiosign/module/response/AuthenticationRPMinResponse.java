package com.github.aiosign.module.response;

import com.github.aiosign.base.AbstractSignResponse;
import com.github.aiosign.base.BaseSignObject;
import com.github.aiosign.enums.RPAuthStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 侯存路
 * @description
 * @since 2020/8/11 10:56
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AuthenticationRPMinResponse extends AbstractSignResponse {


    private AuthenticationRPMinModel data;


    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class AuthenticationRPMinModel extends BaseSignObject {

        /**
         * 认证状态，
         * 1：认证通过；
         * 2：身份证照片模糊、光线问题造成字体无法识别、身份证照片信息与需认证的身份证姓名不一致、提交的照片为非身份证照片；" +
         * "3：身份证照片模糊、光线问题造成字体无法识别、身份证照片信息与需认证的身份证号码不一致、提交的照片为非身份证照片；" +
         * "4：身份证照片上的有效期已过期（或即将过期）；" +
         * "5：人脸与身份证头像不一致；" +
         * "6：人脸与公安网照片不一致；" +
         * "7：提交的身份证照片非身份证原照片、未提交有效身份证照片；" +
         * "8：实名校验不通过；" +
         * "9：非账户本人操作；" +
         * "11：公安网照片缺失、公安网照片格式错误、公安网照片未找到人脸；" +
         * "12：公安网系统异常，无法进行照片比对等可能
         */
        private RPAuthStatus statusCode;

        /**
         * 认证过程中所提交的人脸照片和权威数据的比对分，取值范围：0~100。
         */
        private Float authorityComparisionScore;

        /**
         * 认证过程中所提交的人脸照片和身份证人像面图片上人脸的比对分，取值范围：0~100
         */
        private Float idCardFaceComparisonScore;

        /**
         * 身份证信息
         */
        private IdCardInfo idCardInfo;


        @Data
        public static class IdCardInfo {
            /**
             * 地址
             */
            private String address;
            /**
             * 签发机构
             */
            private String authority;
            /**
             * 出生日期
             * 19900101
             */
            private String birth;
            /**
             * 证件有效期开始时间。格式为：yyyymmdd。
             */
            private String startDate;
            /**
             * 20201101
             * 证件有效期结束时间。格式为：yyyymmdd。
             */
            private String endDate;
            /**
             * 姓名
             */
            private String name;
            /**
             * 民族
             */
            private String nationality;
            /**
             * 身份证号
             */
            private String number;

            /**
             * 性别，1男性，0女性
             */
            private int sex;
        }

    }


}
