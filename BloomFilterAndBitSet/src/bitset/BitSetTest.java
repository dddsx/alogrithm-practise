package bitset;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Random;

// 有1千万个随机数，随机数的范围在1到1亿之间。现在要求写出一种算法，将1到1亿之间没有在随机数中的数求出来？
public class BitSetTest {

    private static int RANGE = 10000000;

    private static int RANDOM_NUM = 1000000;

    public static void main(String[] args) {
        Random random = new Random();

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < RANDOM_NUM; i++) {
            int randomResult = random.nextInt(RANGE);
            list.add(randomResult);
        }
        System.out.println("产生的随机数有");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        BitSet bitSet = new BitSet(RANGE);
        for (int i = 0; i < RANDOM_NUM; i++) {
            bitSet.set(list.get(i));
        }
        System.out.println("0~1亿不在上述随机数中有" + bitSet.size());
        for (int i = 0; i < RANGE; i++) {
            if (!bitSet.get(i)) {
                System.out.println(i);
            }
        }
    }
}
