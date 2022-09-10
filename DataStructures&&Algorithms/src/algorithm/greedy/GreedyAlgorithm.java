package algorithm.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author Muffin_Head
 * @version 1.8.0_131
 *
 */
public class GreedyAlgorithm {
    public static void main(String[] args) {
        //HashMap存放广播电台
        HashMap<String, HashSet<String>> broadcasts = new HashMap();
        //将各个电台放入到broadcasts
        HashSet<String> hashSet1 = new HashSet();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");

        HashSet<String> hashSet2 = new HashSet();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");

        HashSet<String> hashSet3 = new HashSet();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");


        HashSet<String> hashSet4 = new HashSet();
        hashSet4.add("上海");
        hashSet4.add("天津");

        HashSet<String> hashSet5 = new HashSet();
        hashSet5.add("杭州");
        hashSet5.add("大连");

        //加入到map
        broadcasts.put("K1", hashSet1);
        broadcasts.put("K2", hashSet2);
        broadcasts.put("K3", hashSet3);
        broadcasts.put("K4", hashSet4);
        broadcasts.put("K5", hashSet5);

        //存放所有的地区
        HashSet<String> allAreas = new HashSet<>();
        for (String key : broadcasts.keySet()) {
            allAreas.addAll(broadcasts.get(key));
        }

        //存放选择的电台集合
        ArrayList<String> selects = new ArrayList<>();
        //存放当前覆盖的地区和尚未覆盖地区的交集
        HashSet<String> tempSelect = new HashSet<>();
        //保存一次遍历中能覆盖最多地区的key
        String maxKey = null;

        while(!allAreas.isEmpty()){
            maxKey = null;
            //遍历broadcasts
            for (String key : broadcasts.keySet()) {
                //清空tempSelect
                tempSelect.clear();
                tempSelect.addAll(broadcasts.get(key));
                //求出交集
                tempSelect.retainAll(allAreas);
                if(tempSelect.size() > 0 &&
                        (maxKey == null || selects.size() > broadcasts.get(key).size())){
                    maxKey = key;
                }
            }

            if(maxKey != null){
                selects.add(maxKey);
                //去除已被覆盖的地区
                allAreas.removeAll(broadcasts.get(maxKey));
            }
        }
        System.out.println("选择的地区是：" + selects);

    }
}
