package org.example;
import java.util.*;

public class Shop {
    ArrayList<Clock> listClocks = new ArrayList<>();
    Shop() {
        Clock clock1 = new Clock("Casio", 1500);
        Clock clock2 = new ClockSec("Чайка", 1600);
        Clock clock3 = new ClockSec("Q&Q", 1200);
        Clock clock4 = new Clock("fsf", 451, 4, 20);

        listClocks.add(clock1);
        listClocks.add(clock2);
        listClocks.add(clock3);
        listClocks.add(clock4);

        try {
            clock1.SetTime(Hand.HOUR, 18);
            clock1.SetTime(Hand.MINUTE, 30);
            clock1.AddTime(Hand.MINUTE, 45);

            clock2.SetTime(Hand.HOUR, 17);
            clock2.SetTime(Hand.MINUTE, 55);
            clock2.SetTime(Hand.SECOND, 10);
            clock2.AddTime(Hand.SECOND, 1);
        } catch (ThrowOutException e) {
            System.out.print(e);
            System.exit(1);
        }
    }
        void printAll() {
            for (int i = 0; i < listClocks.size(); i++)
                System.out.println(listClocks.get(i));
        }
        Clock getClockMax() {
            Clock clockMax;
            clockMax = Collections.max(listClocks, new Comparator<Clock>() {
                @Override
                public int compare(Clock o1, Clock o2) {
                    return o1.price - o2.price;
                }
            });
            return clockMax;
        }
        public void SetAllTime(int h, int m) throws ThrowOutException {
            for(Clock cl: listClocks) {
                cl.SetTime(Hand.HOUR, h);
                cl.SetTime(Hand.MINUTE, m);
                if (cl instanceof ClockSec)
                    cl.SetTime(Hand.SECOND, 0);
            }
        }
        Map.Entry<String, Integer> MaxCounter() {
//    String MaxCounter() {
//  ->      встречающиеся бренды и их количество
            Map<String, Integer> counter = new HashMap<>();
            for (Clock cl : listClocks) {
                int newValue = counter.getOrDefault(cl.stamp, 0) + 1;
                counter.put(cl.stamp, newValue);
            }
// -> макимальное вхождение
            Map.Entry<String, Integer> maxEntry = null;
            for (Map.Entry<String, Integer> entry : counter.entrySet())
                if (maxEntry == null || entry.getValue() > maxEntry.getValue())
                    maxEntry = entry;
//        return maxEntry.getKey();
            return maxEntry;
        }
        Set<String> getUniqueStamp() {
            Set<String> ustamp = new HashSet<>();
            for (Clock cl: listClocks)
                ustamp.add(cl.stamp);
            return ustamp;
        }
        Set<String> seporateClocks() {
            HashMap<String, List<Integer>>
                    map_stamp = new HashMap<>();
            Set<String> ustamp = getUniqueStamp();
            for (String m: ustamp)
                map_stamp.put(m, new ArrayList<>());

            for (int i = 0; i < listClocks.size(); i++)
                map_stamp.get(listClocks.get(i).stamp).add(listClocks.get(i).price);

            Map<String, List<Integer>> sortedMap = new TreeMap<>(map_stamp);
            sortedMap.putAll(map_stamp);
            return sortedMap.keySet();
//            return sortedMap;
        }
}