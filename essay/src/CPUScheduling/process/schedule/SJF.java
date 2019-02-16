package process.schedule;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import controlblock.PCB;
import test.Test;

public class SJF {
    protected LinkedList<PCB> processQueue;
    protected DecimalFormat dF;
    public SJF() {
        processQueue = new LinkedList<PCB>();
        dF = new DecimalFormat("0.00");
    }

    public void schedule(){
        dF.setRoundingMode(RoundingMode.HALF_EVEN);
        sortByArriveTime(processQueue);
        PCB process,tempProcess;
        double arriveTime,needTime,minNeedTime,currentTime = 0;
        int minIndex;
        double aveRunTime = 0; //平均周转时间
        double aveWeiRunTime = 0; //平均带权周转时间
        while(!processQueue.isEmpty()){
            process = processQueue.pollFirst();
            arriveTime = process.getArriveTime();
            needTime = process.getNeedTime();

            if (currentTime < arriveTime)
                currentTime = arriveTime;

            minIndex = -1;
            minNeedTime = Test.getMaxNeedTime() + 2;

            // 要执行进程时，挑选已到达的需要作业时间最短的进程
            for (int i = 0; i < processQueue.size(); i++) {
                tempProcess = processQueue.get(i);
                // 到达时间晚于前一个进程的结束时间，不用继续考虑了
                if (tempProcess.getArriveTime() > currentTime + needTime)
                    break;
                // 到达时间相同，挑选最短作业为当前作业
                if (tempProcess.getArriveTime() == arriveTime && tempProcess.getNeedTime() < needTime) {
                    processQueue.set(i, process);
                    process = tempProcess;
                    tempProcess = processQueue.get(i);
                    needTime = process.getNeedTime();
                }
                if (tempProcess.getNeedTime() < minNeedTime) {
                    minIndex = i;
                    minNeedTime = tempProcess.getNeedTime();
                }
            }
            // 将最短作业放入队首
            if (minIndex != -1) {
                tempProcess = processQueue.remove(minIndex);
                processQueue.addFirst(tempProcess);
            }

            System.out.print("进程：" + process.getPid() + ",到达时间：" + dF.format(process.getArriveTime()) + ",需要时间:"
                    + dF.format(process.getNeedTime()) + ",开始时间：" + dF.format(currentTime) + ",");
            currentTime += needTime;
            System.out.println("结束时间：" + dF.format(currentTime));
            aveRunTime = aveRunTime+(currentTime-process.getArriveTime())/Test.getMaxProcessNum();
            aveWeiRunTime = aveWeiRunTime+(currentTime-process.getArriveTime())/process.getNeedTime()/Test.getMaxProcessNum();
        }
        System.out.println("平均周转时间为:"+dF.format(aveRunTime)+"  平均带权周转时间为:"+dF.format(aveWeiRunTime));
    }

    public void addProcess(int pid,Double arriveTime,Double needTime){
        processQueue.add(new PCB(pid,arriveTime,needTime));
    }

    public void sortByArriveTime(LinkedList<PCB> processQueue){
        Comparator<PCB> c = (PCB p1,PCB p2)->{
            Double p1Time = p1.getArriveTime();
            Double p2Time = p2.getArriveTime();
            return p1Time.compareTo(p2Time);
        };
        Collections.sort(processQueue, c);
    }
}
