package org.huangfugui.spring.service.tools;

import org.apache.commons.collections.map.HashedMap;
import org.huangfugui.ibatis.po.Label;
import org.huangfugui.ibatis.po.PicLabel;
import org.huangfugui.ibatis.po.PicLabelData;
import org.huangfugui.ibatis.po.Picture;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by huangfugui on 2017/8/3.
 */
public class ToPicLabelData {
    public static List<PicLabelData> transfer2PicLabelData(List<PicLabel> picLabels){
        //存储一对多：一个图片对应多个标签
        Map<Picture, List<Label>> map = new HashMap<Picture, List<Label>>();
        Map<Picture, List<Float>> map2 = new HashMap<Picture, List<Float>>();
        for(PicLabel picLabel : picLabels){
            Picture picture = picLabel.getPicture();
            if(!map.containsKey(picture)){
                map.put(picture, new ArrayList<Label>());
                map2.put(picture,new ArrayList<Float>());
            }
            map.get(picture).add(picLabel.getLabel());
            map2.get(picture).add(picLabel.getScore());
        }

        List<PicLabelData> labelDataList = new ArrayList<PicLabelData>();
        for(Map.Entry<Picture,List<Label>> entry : map.entrySet()){
            PicLabelData picLabelData = new PicLabelData();
            picLabelData.setPicId(entry.getKey().getId());
            picLabelData.setPicPath(entry.getKey().getPath());
            picLabelData.setLabels(entry.getValue());
            picLabelData.setScores(map2.get(entry.getKey()));

            labelDataList.add(picLabelData);
        }
        return labelDataList;
    }
}
