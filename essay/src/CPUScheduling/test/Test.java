package CPUScheduling.test;

import process.schedule.FCFS;
import process.schedule.RR;
import process.schedule.SJF;

public class Test {
    //private static final Double MAX_ARRIVE_TIME = (double) 5;
    private static final Double MAX_NEED_TIME = (double) 10;
    private static final int MAX_PROCESS_NUM = 4;
    // private static final int MAX_PRIORITY = 10;
    // private static final int MAX_PID = 10000;

    public static void main(String[] args) {
        System.out.println("先来先服务法:");
        FCFSSchedule();
        System.out.println("短作业优先调度法：");
        SJFSchedule();
        System.out.println("时间轮转法：");
        RRSchedule();
    }

    /**
     * 先到先服务
     */
    private static void FCFSSchedule() {
        FCFS ps = new FCFS();
        /*Random random = new Random();

        for (int i = 0; i < MAX_PROCESS_NUM; i++) {
            ps.addProcess(random.nextInt(MAX_PID), random.nextInt(MAX_ARRIVE_TIME), random.nextInt(MAX_NEED_TIME) + 1);
        }*/
        ps.addProcess(1, 8.00, 2.00);
        ps.addProcess(2, 8.50, 0.50);
        ps.addProcess(3, 9.00, 0.10);
        ps.addProcess(4, 9.50, 0.20);
        ps.schedule();
    }

    /**
     * 短作业优先调度
     */
    private static void SJFSchedule() {
        SJF ps = new SJF();
        ps.addProcess(1, 8.00, 2.00);
        ps.addProcess(2, 8.50, 0.50);
        ps.addProcess(3, 9.00, 0.10);
        ps.addProcess(4, 9.50, 0.20);
        ps.schedule();
    }

    private static void RRSchedule(){
        RR ps = new RR();
        ps.addProcess(1, 8.00, 2.00);
        ps.addProcess(2, 8.50, 0.50);
        ps.addProcess(3, 9.00, 0.10);
        ps.addProcess(4, 9.50, 0.20);
        ps.schedule();
    }
    public static Double getMaxNeedTime(){
        return MAX_NEED_TIME;
    }

    public static int getMaxProcessNum() {
        return MAX_PROCESS_NUM;
    }
}
