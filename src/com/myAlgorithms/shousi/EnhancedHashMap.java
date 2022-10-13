package com.myAlgorithms.shousi;

import java.util.HashMap;

public class EnhancedHashMap {
    // insert(key, value)
    // get(key)
    // addToKey(x) add x to all keys
    // addToValue(y) add y to all values
    public int applyMapAndGetSum (String[] queryType, int[][] query) {
        int sum = 0;
        int keyAdd = 0;
        int valueAdd = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < queryType.length; i ++) {
            String type = queryType[i];
            if (type.equals("insert")) {
                // 从底层数组角度考虑，key存在的整体右移，相当于key不存在的位置整体左移，因此后加入的数字也要平行左移；数字同理，存在的value + 1 相当于负空间-1
                map.put(query[i][0] - keyAdd, query[i][1] - valueAdd);
            } else if (type.equals("get")) {
                sum += map.get(query[i][0] - keyAdd) + valueAdd;
            } else if (type.equals("addToKey")) {
                keyAdd += query[i][0];
            } else  {
                valueAdd += query[i][0];
            }
        }
        return sum;
    }
}
