package process.schedule;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

import controlblock.PCB;
import test.Test;

public class FCFS {
    protected LinkedList<PCB> processQueue;
    protected DecimalFormat dF;
    public FCFS() {
        processQueue = new LinkedList<PCB>();
        dF = new DecimalFormat("0.00");
    }
    public void schedule() {
        dF.setRoundingMode(RoundingMode.HALF_EVEN);
        sortByArriveTime(processQueue);
        double currentTime = 0;
        double arriveTime;
        double aveRunTime = 0; //平均周转时间
        double aveWeiRunTime = 0; //平均带权周转时间
        PCB process;
        Iterator<PCB> iter = processQueue.iterator();
        while (iter.hasNext()) {
            process = iter.next();
            arriveTime = process.getArriveTime();
            System.out.print("进程：" + process.getPid() + ",");
            System.out.print("到达时间：" + dF.format(process.getArriveTime()) + ",");
            System.out.print("需要时间:" + dF.format(process.getNeedTime()) + ",");

            if (arriveTime > currentTime)
                currentTime = arriveTime;
            System.out.print("开始时间：" + dF.format(currentTime) + ",");
            currentTime += process.getNeedTime();
            System.out.println("结束时间：" + dF.format(currentTime));

            aveRunTime = aveRunTime+(currentTime-arriveTime)/Test.getMaxProcessNum();
            aveWeiRunTime = aveWeiRunTime+(currentTime-arriveTime)/process.getNeedTime()/Test.getMaxProcessNum();
            iter.remove();
        }
        System.out.println("平均周转时间为:"+dF.format(aveRunTime)+"  平均带权周转时间为:"+dF.format(aveWeiRunTime));
    }

    /**
     * 将新的进程加入队列
     * @param pid
     * @param arriveTime
     * @param needTime
     */
    public void addProcess(int pid, double arriveTime, double needTime) {
        processQueue.push(new PCB(pid, arriveTime, needTime));
    }

    /**
     * 对进程队列按到达时间排序
     * @param processQueue
     */

    //λ表达式有三部分组成：参数列表，箭头（->），以及一个表达式或语句块。λ表达式的目标类型是函数接口
    /*private <T> void sortByArriveTime(LinkedList<PCB> processQueue) {
        processQueue.sort((p1, p2) -> {
            Integer p1Time = p1.getArriveTime();
            Integer p2Time = p2.getArriveTime();
            return p1Time.compareTo(p2Time);
        });
    } */

    private <T> void sortByArriveTime(LinkedList<PCB> processQueue) {
        Comparator<PCB> c = (PCB p1,PCB p2) -> {
            Double p1Time = p1.getArriveTime();
            Double p2Time = p2.getArriveTime();
            return p1Time.compareTo(p2Time);
        };
        processQueue.sort(c);
        //Collections.sort(processQueue, c);
    }
}
