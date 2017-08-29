package test.cn.jin.forkjoin;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

/**
 * fork/join 框架
 * @author Alexander
 * @version 1.0
 * @sine 2017-08-09 14:08
 */
public class Demo1 extends RecursiveTask<List<String>> {

    private static Demo1 demo1;

    /**
     * 阈值
     */
    private int threshold;

    /**
     * 待拆分list
     */
    private List<String> list;

    public Demo1(List<String> list,int threshold) {
        this.threshold = threshold;
        this.list = list;
    }

    @Override protected List<String> compute() {
        /*如果list的数量小于阈值，直接遍历*/
        if(CollectionUtils.isEmpty(list) || list.size() < threshold){
            return list.stream().filter(s -> s.contains("a")).collect(Collectors.toList());
        }else{
            int middle = list.size() / 2;
            List<String> leftList = list.subList(0,middle);
            List<String> rightList = list.subList(middle,list.size());
            Demo1 left = new Demo1(leftList, threshold);
            Demo1 right = new Demo1(rightList, threshold);
            /*并行执行两个“小任务”*/
            left.fork();
            right.fork();
            /*把两个“小任务”的结果合并起来*/
            List<String> join = left.join();
            join.addAll(right.join());
            return join;
        }
    }

    /**
     * demo1
     * @param list  待处理List
     * @param threshold 阈值
     * @return demo1实例
     */
    public static Demo1 getInstance(List<String> list, int threshold) {
        if (demo1 == null) {
            synchronized (Demo1.class) {
                if (demo1 == null) {
                    demo1 = new Demo1(list, threshold);
                }
            }
        }
        return demo1;
    }

    public static void main(String[] args) {
        List<String> list = Lists.newArrayList("a", "ah", "b", "ba", "ab", "ac", "sd", "fd", "ar", "te", "se", "te",
                "sdr", "gdf", "df", "fg", "gh", "oa", "ah", "qwe", "re", "ty", "ui");
        ForkJoinPool pool = new ForkJoinPool();
        Demo1 demo = Demo1.getInstance(list,20);
        ForkJoinTask<List<String>> future = pool.submit(demo);
        try {
            System.out.println(new Gson().toJson(future.get()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }finally {
            pool.shutdown();
        }
    }
}
