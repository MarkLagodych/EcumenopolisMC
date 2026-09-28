import java.util.ArrayList;

import it.unimi.dsi.fastutil.ints.IntArrayList;

public class Bench {
    public static void main(String[] args) {
        {
            IntArrayList list = new IntArrayList();
            for (int i = 0; i < 1000000; i++) {
                list.add(i);
            }

            ArrayList<Integer> arrayList = new ArrayList<>();
            for (int i = 0; i < 1000000; i++) {
                arrayList.add(i);
            }
        }

        var time1 = System.nanoTime();
        IntArrayList list = new IntArrayList();
        for (int i = 0; i < 1000000; i++) {
            list.add(i);
        }
        var time2 = System.nanoTime();
        System.out.println("IntArrayList time:\n" + (time2 - time1) + " ns");

        time1 = System.nanoTime();
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            arrayList.add(i);
        }
        time2 = System.nanoTime();
        System.out.println("ArrayList time:\n" + (time2 - time1) + " ns");
    }
}
