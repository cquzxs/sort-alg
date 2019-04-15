package com.zxs.ssh.template.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Project Name:sort-alg
 * File Name:SortUtil
 * Package Name:com.zxs.ssh.template.util
 * Date:2018/11/28
 * Author:zengxueshan
 * Description:8大排序算法
 * Copyright (c) 2018, 重庆云凯科技有限公司 All Rights Reserved.
 */


public class SortUtil {
    private static final Logger logger = LoggerFactory.getLogger(SortUtil.class);

    /**
     * 冒泡排序(升序)
     * 最坏情况：本身就是降序，此时比较次数相同n²，交换次数是最多的n²
     * 最好情况：本身就是升序，此时比较次数相同n²，交换次数是最少的0
     * 数组长度为10万时，花费时间大于10秒
     * 性质：1、时间复杂度：O(n2)  2、空间复杂度：O(1)  3、稳定排序  4、原地排序
     *
     * @param a      待排序数组
     * @param length 数组长度
     * @return 1表示排序成功，0表示排序失败
     */
    public static int bubbleSort(int[] a, int length) {
        for (int i = 0; i < length; i++) {
            for (int j = length - 1; j > i; j--) {
                if (a[j] < a[j - 1]) {
                    int temp = a[j];
                    a[j] = a[j - 1];
                    a[j - 1] = temp;
                }
            }
        }
        return length;
    }

    /**
     * 选择排序(升序)
     * 比较次数相同n²，交换次数相同n
     * 数组长度为20万时，花费时间大于10秒
     * 性质：1、时间复杂度：O(n2)  2、空间复杂度：O(1)  3、非稳定排序  4、原地排序
     *
     * @param a      待排序数组
     * @param length 数组长度
     */
    public static int selectSort(int[] a, int length) {
        int selectedIndex = 0;
        for (int i = 0; i < length; i++) {
            selectedIndex = i;
            for (int j = i + 1; j < length; j++) {
                if (a[j] < a[selectedIndex]) {
                    selectedIndex = j;
                }
            }
            int temp = a[i];
            a[i] = a[selectedIndex];
            a[selectedIndex] = temp;
        }
        return length;
    }

    /**
     * 插入排序(升序)
     * 最坏情况：本身就是降序，此时比较次数(n²)和交换次数(n²/2)都是最多的
     * 最好情况：本身就是升序，此时比较次数(n²)和交换次数(0)都是最少的
     * 数组长度为30万时，花费时间大于10秒
     * 性质：1、时间复杂度：O(n2)  2、空间复杂度：O(1)  3、稳定排序  4、原地排序
     *
     * @param a      待排序数组
     * @param length 数组长度
     */
    public static int insertSort(int[] a, int length) {
        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j > 0; j--) {
                if (a[j] < a[j - 1]) {
                    int temp = a[j];
                    a[j] = a[j - 1];
                    a[j - 1] = temp;
                } else {
                    break;
                }
            }
        }
        return length;
    }

    /**
     * 快速排序(升序)
     * 数组长度为2百万时，抛异常java.lang.StackOverflowError
     * 性质：1、时间复杂度：O(nlogn)  2、空间复杂度：O(logn)  3、非稳定排序  4、原地排序
     *
     * @param a      待排序数组
     * @param length 数组长度
     */
    public static int quickSort(int[] a, int length) {
        try {
            int leftIndex = 0;
            int rightIndex = length - 1;
            quickSortHelp(a, leftIndex, rightIndex);
        } catch (Exception e) {
            logger.info("快速排序异常", e);
        }
        return length;
    }

    /**
     * 快速排序递归
     *
     * @param a          待排序数组
     * @param leftIndex  左索引
     * @param rightIndex 右索引
     */
    private static void quickSortHelp(int[] a, int leftIndex, int rightIndex) {
        try {
            if (leftIndex >= rightIndex) {
                return;
            }
            //取最左边、最右边、中间三数的第二大值作为轴值
            int pivotIndex = (leftIndex + rightIndex + 1) / 2;
            if (a[rightIndex] < a[leftIndex]) {
                swap(a, leftIndex, rightIndex);
            }
            if (a[rightIndex] < a[pivotIndex]) {
                swap(a, pivotIndex, rightIndex);
            }
            if (a[pivotIndex] < a[leftIndex]) {
                swap(a, leftIndex, pivotIndex);
            }
            int pivotValue = a[pivotIndex];
            //将轴值移到最右边
            swap(a, rightIndex, pivotIndex);
            int leftOrigin = leftIndex;
            int rightOrigin = rightIndex;
            while (leftIndex < rightIndex) {
                while (leftIndex < rightIndex && a[leftIndex] <= pivotValue) {
                    leftIndex++;
                }
                while (leftIndex < rightIndex && a[rightIndex] >= pivotValue) {
                    rightIndex--;
                }
                if (leftIndex < rightIndex) {
                    swap(a, leftIndex, rightIndex);
                }
            }
            //将轴值移到左索引和右索引相交处，移完后，轴值左边都是比轴值小的，轴值右边都是比轴值大的(或者相等)
            swap(a, rightOrigin, rightIndex);
            quickSortHelp(a, leftOrigin, rightIndex - 1);
            quickSortHelp(a, rightIndex + 1, rightOrigin);
        } catch (Exception e) {
            logger.info("快速排序异常", e);
        }

    }

    /**
     * 归并排序(升序)
     * 数组长度为5千万时，抛异常java.lang.OutOfMemoryError: Java heap space
     * 性质：1、时间复杂度：O(nlogn)  2、空间复杂度：O(n)  3、稳定排序 4、非原地排序
     *
     * @param a      待排序数组
     * @param length 数组长度
     */
    public static int mergeSort(int[] a, int length) {
        try {
            int leftIndex = 0;
            int rightIndex = length - 1;
            mergeSortHelp(a, leftIndex, rightIndex);
        } catch (Exception e) {
            logger.info("归并排序异常", e);
        }
        return length;
    }

    /**
     * 归并排序递归
     *
     * @param a          待排序数组
     * @param leftIndex  左索引
     * @param rightIndex 右索引
     */
    private static void mergeSortHelp(int[] a, int leftIndex, int rightIndex) {
        int midIndex = (leftIndex + rightIndex) / 2;
        if (leftIndex < rightIndex) {
            mergeSortHelp(a, leftIndex, midIndex);
            mergeSortHelp(a, midIndex + 1, rightIndex);
            merge(a, leftIndex, rightIndex);
        }
    }

    /**
     * 归并，对部分有序的数组排序
     *
     * @param a          待排序数组
     * @param leftIndex  左索引
     * @param rightIndex 右索引
     */
    private static void merge(int[] a, int leftIndex, int rightIndex) {
        int[] temp = new int[rightIndex - leftIndex + 1];
        int mid = (leftIndex + rightIndex) / 2;
        int i = leftIndex;
        int j = mid + 1;
        int k = 0;
        //将较小的数移到新数组中
        while (i <= mid && j <= rightIndex) {
            if (a[i] < a[j]) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
            }
        }
        //将左边剩余的数移到新数组中
        while (i <= mid) {
            temp[k++] = a[i++];
        }
        //将右边剩余的数移到新数组中
        while (j <= rightIndex) {
            temp[k++] = a[j++];
        }
        //将新数组覆盖原数组
        k = 0;
        for (int m = leftIndex; m <= rightIndex; m++) {
            a[m] = temp[k++];
        }
    }

    /**
     * 希尔排序(升序)
     * 本质是插入排序
     * 数组长度为5千万时，抛异常java.lang.OutOfMemoryError: Java heap space
     * 性质：1、时间复杂度：O(n的1.3次方)  2、空间复杂度：O(1)  3、非稳定排序  4、原地排序
     *
     * @param a      待排序数组
     * @param length 数组长度
     */
    public static int shellSort(int[] a, int length) {
        try {
            //分组,确定步长和组数，初始步长为length / 2，组数为stepLength
            //假如数组长度为8，步长 = 4，组数 = 4
            for (int stepLength = length / 2; stepLength > 0; stepLength /= 2) {
                //插入排序
                for (int i = 0; i < length - stepLength; i++) {
                    for (int j = i + stepLength; j >= stepLength; j -= stepLength) {
                        if (a[j] < a[j - stepLength]) {
                            swap(a, j, j - stepLength);
                        } else {
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.info("希尔排序异常", e);
        }
        return length;
    }

    /**
     * 堆排序(升序)
     * 算法步骤：1.构造最大堆  2.将根结点与最后一个节点交换（即去除根节点）  3.将剩余节点调整为最大堆  4.重复2和3
     * 数组长度为5千万时，抛异常java.lang.OutOfMemoryError: Java heap space
     * 性质：1、时间复杂度：O(nlogn)  2、空间复杂度：O(1)  3、非稳定排序  4、原地排序
     *
     * @param a      待排序数组
     * @param length 数组长度
     */
    public static int heapSort(int[] a, int length) {
        //构造最大堆
        buildMaxHeap(a, length);
        //将根结点与最后一个节点交换
        swap(a, 0, length - 1);
        for (int count = length - 1; count > 1; count--) {
            buildMaxHeapHelp(a, count, 0);  //此时只有根节点不满足最大堆特性
            swap(a, 0, count - 1);
        }
        return length;
    }


    /**
     * 构造节点个数为count的最大堆
     *
     * @param a     堆（完全二叉树）
     * @param count 节点个数
     */
    private static void buildMaxHeap(int[] a, int count) {
        //完全二叉树特性：最后一个节点的索引为count-1,其父节点索引为(count-1-1)/2
        //最后一层都为叶子节点，所以从倒数第二层的最后一个节点开始构造最大堆
        for (int parentIndex = (count - 1 - 1) / 2; parentIndex >= 0; parentIndex--) {
            buildMaxHeapHelp(a, count, parentIndex);
        }
    }

    /**
     * 构建最大堆递归
     * 最大堆：父节点比左右两个子节点都大
     *
     * @param a           堆（完全二叉树）
     * @param count       节点个数
     * @param parentIndex 节点索引
     */
    private static void buildMaxHeapHelp(int[] a, int count, int parentIndex) {
        //完全二叉树特性：左边子节点位置 = 当前父节点的两倍 + 1，右边子节点位置 = 当前父节点的两倍 + 2
        int leftIndex = 2 * parentIndex + 1;
        int rightIndex = 2 * parentIndex + 2;
        //计算父节点、左子节点、右子节点最大者索引
        int maxValueIndex = parentIndex;
        if (leftIndex <= count - 1 && a[leftIndex] > a[maxValueIndex]) {
            maxValueIndex = leftIndex;
        }
        if (rightIndex <= count - 1 && a[rightIndex] > a[maxValueIndex]) {
            maxValueIndex = rightIndex;
        }
        if (parentIndex != maxValueIndex) {
            swap(a, parentIndex, maxValueIndex);
            buildMaxHeapHelp(a, count, maxValueIndex);  //子节点也可能为其他节点的父节点
        }
    }

    /**
     * 基数排序(升序)
     * 数组长度为3千万时，抛异常java.lang.OutOfMemoryError: Java heap space
     * 性质：1、时间复杂度：O(kn)  2、空间复杂度：O(n+k)  3、稳定排序  4、非原地排序
     *
     * @param a      待排序数组
     * @param length 数组长度
     */
    public static int radixSort(int[] a, int length) {
        int currentLength = 1;//当前位数
        int maxNumberLength = getMaxNumberLength(a, length);//最大位数
        int[][] bucket = new int[10][length];//排序桶用于保存每次排序后的结果，这一位上排序结果相同的数字放在同一个桶里
        int[] order = new int[10];//用于保存每个桶里有多少个数字
        while (currentLength <= maxNumberLength) {
            for (int i = 0; i < length; i++) {
                int digit = (a[i] / ((int) (Math.pow(10, currentLength - 1)))) % 10;
                bucket[digit][order[digit]] = a[i];
                order[digit]++;
            }
            int currentIndex = 0;
            for (int j = 0; j < 10; j++) {  //共有10个桶
                for (int k = 0; k < order[j]; k++) { //每个桶里有order[j]个数
                    a[currentIndex] = bucket[j][k]; //从第j个桶中取第k个数
                    currentIndex++;
                }
                order[j] = 0;
            }
            currentLength++;
        }
        return length;
    }

    /**
     * 获取数组中最大数的长度（即位数）
     * 条件：a[i] >= 0
     *
     * @param a      待排序数组
     * @param length 数组长度
     * @return 数组中最大数的长度（即位数）
     */
    private static int getMaxNumberLength(int[] a, int length) {
        int res = 0;
        int maxValue = a[0];
        for (int i = 1; i < length; i++) {
            if (a[i] > maxValue) {
                maxValue = a[i];
            }
        }
        res = (maxValue + "").length();
        return res;
    }

    /**
     * 数组交换位置
     *
     * @param a 数组
     * @param i 第一个数索引
     * @param j 第二个数索引
     */
    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    /**
     * Math.random()取值[0，1）左闭右开
     *
     * @param args 系统参数
     */
    public static void main(String[] args) {
        OriginData originData = buildOriginData();

        printExecuteTime(System.nanoTime(),bubbleSort(originData.a[0], originData.length),"冒泡排序");
        System.out.println("isSorted:"+isSorted(originData.a[0],true));
        printExecuteTime(System.nanoTime(),selectSort(originData.a[1], originData.length),"选择排序");
        System.out.println("isSorted:"+isSorted(originData.a[1],true));
        printExecuteTime(System.nanoTime(), insertSort(originData.a[2], originData.length), "插入排序");
        System.out.println("isSorted:" + isSorted(originData.a[2], true));
        printExecuteTime(System.nanoTime(),quickSort(originData.a[3], originData.length),"快速排序");
        System.out.println("isSorted:"+isSorted(originData.a[3],true));
        printExecuteTime(System.nanoTime(),mergeSort(originData.a[4], originData.length),"归并排序");
        System.out.println("isSorted:"+isSorted(originData.a[4],true));
        printExecuteTime(System.nanoTime(),shellSort(originData.a[5], originData.length),"希尔排序");
        System.out.println("isSorted:"+isSorted(originData.a[5],true));
        printExecuteTime(System.nanoTime(),heapSort(originData.a[6], originData.length),"堆排序");
        System.out.println("isSorted:"+isSorted(originData.a[6],true));
        printExecuteTime(System.nanoTime(),radixSort(originData.a[7], originData.length),"基数排序");
        System.out.println("isSorted:"+isSorted(originData.a[7],true));
    }

    private static OriginData buildOriginData() {
        OriginData originData = new OriginData();
        int length = 10000;
        int[][] a = new int[8][length];//8种排序算法的初始数据
        for (int i = 0; i < length; i++) {
            a[0][i] = (new Double(Math.random() * 100)).intValue();
            for (int j = 1; j < 8; j++) {
                a[j][i] = a[0][i];
            }
        }
        originData.a = a;
        originData.length = length;
        originData.value = 0;
        return originData;
    }

    private static class OriginData {
        int[][] a;  //初始数组
        int length; //数组长度
        int value;  //随机查找值
    }

    /**
     * 计算复杂度，包括时间复杂度（最差情况、平均情况、最优情况）、空间复杂度、稳定性
     *
     * @param expression 表达式
     */
    private static void caculateComplexity(String expression) {

    }

    /**
     * 打印方法执行时间
     *
     * @param startTime 开始时间
     * @param result    执行方法
     * @param title     标题
     */
    private static void printExecuteTime(long startTime, int result, String title) {
        long endTime = System.nanoTime();
        double executeTime = (endTime - startTime) / 1000000d;
        System.out.println("【" + title + "】执行时间：" + executeTime + "ms  result=" + result);
    }

    /**
     * 打印数组
     *
     * @param a 数组
     */
    private static void printArr(int[] a) {
        String res = "";
        for (int i = 0; i < a.length; i++) {
            res = res + a[i] + "\t";
        }
        System.out.println(res);
    }

    /**
     * 验证是否有序
     *
     * @param a 数组
     */
    private static boolean isSorted(int[] a, boolean asc) {
        if (asc) {
            for (int i = 0; i < a.length - 1; i++) {
                if (a[i + 1] < a[i]) {
                    return false;
                }
            }
        } else {
            for (int i = 0; i < a.length - 1; i++) {
                if (a[i + 1] > a[i]) {
                    return false;
                }
            }
        }
        return true;
    }

}
