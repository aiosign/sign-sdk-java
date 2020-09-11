package com.github.aiosign.module.response;

import com.github.aiosign.base.AbstractSignResponse;
import com.github.aiosign.base.BaseSignObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Map;

/**
 * @author 侯存路
 * @description
 * @since 2020/8/18 9:58
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BaiDuAiFaceVideoVerifyRequestResponse extends AbstractSignResponse {


    private BaiduSessionCodeModel data;


    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class BaiduSessionCodeModel extends BaseSignObject {

        public String score;

        public Map<String, String> thresholds;

        public BaiduSessionCodeModelCode code;

        public List<BaiduSessionCodeModelPicList> pic_list;


        @EqualsAndHashCode(callSuper = true)
        @Data
        public static class BaiduSessionCodeModelPicList extends BaseSignObject {

            public String face_id;

            public String face_token;

            public String pic;

            public double liveness_score;
        }


        @EqualsAndHashCode(callSuper = true)
        @Data
        public static class BaiduSessionCodeModelCode extends BaseSignObject {
            public String identify;
            public double similarity;
            public String create;
        }


    }

}