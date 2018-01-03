package test.cn.jin.math;

import org.junit.Test;

import com.google.gson.Gson;

/**
 * 排序
 *
 * @author Alexander
 * @version 1.0
 * @sine 2017-07-26 10:45
 */
public class Sort {

    private static Integer[] datas;

    static {
        datas = new Integer[] { 2,7,1, 12, 41, 213, 321, 12, 325, 32, 13, 56 };
    }

    /**
     *
     * 插入排序
     */
    @Test
    public void InsertSort() {
        int temp=0;
        for(int i=1;i<datas.length;i++){
            int j=i-1;
            temp=datas[i];
            for(;j>=0&&temp<datas[j];j--){
                datas[j+1]=datas[j];
            }
            datas[j+1]=temp;
        }
        System.out.println(new Gson().toJson(datas));
    }

    /**
     * 希尔排序
     */
    @Test
    public void shellSort(){

    }

    /**
     * 冒泡排序(比较相邻的两个数,每次都将最大的往尾部放)
     */
    @Test
    public void BlueSort() {
        for (int i = 0; i < datas.length-1; i++) {
            for (int j = 0; j < datas.length - 1-i; j++) {
                int temp1 = datas[j];
                int temp2 = datas[j + 1];
                if (temp1 > temp2) {
                    datas[j + 1] = temp1;
                    datas[j] = temp2;
                }
            }
        }
        System.out.println(new Gson().toJson(datas));
    }
}
