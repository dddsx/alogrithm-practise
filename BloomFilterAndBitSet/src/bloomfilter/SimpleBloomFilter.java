package bloomfilter;

import java.util.BitSet;

/**
 * Bloom Filter 是由Howard Bloom 在 1970 年提出的二进制向量数据结构，它具有很好的空间和时间效率，被用来检测一个元素是不是集合中的一个成员。
 * 如果检测结果为是，该元素不一定在集合中；但如果检测结果为否，该元素一定不在集合中。可见Bloom filter是牺牲了正确率和时间以节省空间。
 * 开源实现：
 * Elasticsearch：org.elasticsearch.common.util.BloomFilter
 * guava：com.google.common.hash.BloomFilter
 * Hadoop：org.apache.hadoop.util.bloom.BloomFilter（基于BitSet实现）
 */
public class SimpleBloomFilter {

    private static final int DEFAULT_SIZE = 2 << 24;

    private static final int[] seeds = new int[]{7, 11, 13, 31, 37, 61};

    private BitSet bits = new BitSet(DEFAULT_SIZE);

    private SimpleHash[] hashFunc = new SimpleHash[seeds.length];

    public SimpleBloomFilter() {
        for (int i = 0; i < seeds.length; i++) {
            hashFunc[i] = new SimpleHash(DEFAULT_SIZE, seeds[i]);
        }
    }

    public void add(String value) {
        for (SimpleHash f : hashFunc) {
            bits.set(f.hash(value), true);
        }
    }

    public boolean contains(String value) {
        if (value == null) {
            return false;
        }
        boolean ret = true;
        for (SimpleHash f : hashFunc) {
            ret = ret && bits.get(f.hash(value));
        }
        return ret;
    }

    public static class SimpleHash {

        private int cap;

        private int seed;

        public SimpleHash(int cap, int seed) {
            this.cap = cap;
            this.seed = seed;
        }

        public int hash(String value) {
            int result = 0;
            int len = value.length();
            for (int i = 0; i < len; i++) {
                result = seed * result + value.charAt(i);
            }
            return (cap - 1) & result;
        }
    }

    public static void main(String[] args) {
        String value = "dddsx6324@163.com";
        SimpleBloomFilter filter = new SimpleBloomFilter();
        System.out.println(filter.contains(value));
        filter.add(value);
        System.out.println(filter.contains(value));
    }
}
