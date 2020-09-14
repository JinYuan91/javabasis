package com.java.basis.datastructure.linkedlist.limiting;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author ：无始
 * @date ：Created in 2020/9/14 10:11 上午
 * @description：
 * @modified By：
 * @version:
 */
@Component
public class LimitManage {


    /**
     * 头指针
     */
    private LimitNode head;

    /**
     * 当前时间所属子窗口指针
     */
    private LimitNode current;

    /**
     * 上一年轮最大时间值
     */
    private Long previousAnnualRingTimeMax;
    /**
     * 当前年轮最大时间值
     */
    private Long annualRingTimeMax;

    /**
     * 当前年轮值
     */
    private AtomicLong annualRing = new AtomicLong();

    /**
     * 当前窗口请求值总和
     */
    private AtomicLong currentLimit = new AtomicLong();

    /**
     * 子窗口范围
     */
    private AtomicLong windowPartyTimeRange = new AtomicLong();

    /**
     * 窗口范围
     */
    private AtomicLong windowTimeRange = new AtomicLong();

    /**
     * 初始化 阈值
     */
    private Long limit;

    /**
     * 子窗口数量
     */
    private AtomicInteger windowsSize = new AtomicInteger();

    /**
     * 初始化完成标识
     */
    private volatile boolean initComplete = true;


    private AtomicInteger lock = new AtomicInteger();

    public LimitManage() {
        init(10, 10000L, 20L, 1L, null);
    }

    /**
     * @Description: 初始化单向环形链表 节点代表窗口页
     * @Param: * @param windowsSize 窗口大小
     * @Param: * @param windowTimeRange 时间跨度范围 单位毫秒
     * @Param: * @param limit  限流阈值
     * @Param: * @param annualRing 年轮值
     * @Param: * @param currentTime 当前请求时间
     * @return:
     * @Author: 无始
     * @Date: 2020/9/14+10:27 上午
     */
    public void init(Integer windowsSize, Long windowTimeRange, Long limit, Long annualRing, Long currentTime) {
        if (windowsSize > windowTimeRange) {
            throw new RuntimeException();
        }
        this.annualRing.set(annualRing);
        this.windowsSize.set(windowsSize);
        this.windowTimeRange.set(windowTimeRange);
        this.limit = limit;

        this.currentLimit.set(0L);
        if (currentTime == null) {
            currentTime = System.currentTimeMillis();
        }
        Long windowsRange = windowTimeRange / windowsSize;
        windowPartyTimeRange.set(windowsRange);
        LimitNode limitNode = new LimitNode();
        current = limitNode;
        head = limitNode;
        for (int i = 0; i < windowsSize; i++) {
            LimitNode nextNode = new LimitNode();
            if (i == 0) {
                limitNode.initLimitNode(currentTime + (windowsRange * i), currentTime + (windowsRange * (i + 1)), this.annualRing.get());
                limitNode.setIndex(i);
                head = limitNode;
            } else {
                nextNode.initLimitNode(currentTime + (windowsRange * i) + 1, currentTime + (windowsRange * (i + 1)), this.annualRing.get());
                nextNode.setIndex(i);
                current.setNext(nextNode);
                current = nextNode;
            }
        }
        current.setNext(head);
        current = head;
        annualRingTimeMax = currentTime + windowTimeRange;
    }

    public Boolean limitOperation(Long currentTime) {
        if (currentTime > annualRingTimeMax) {
            if (lock.compareAndSet(0, 1)) {
                initComplete = false;
                if (currentTime >= annualRingTimeMax + windowTimeRange.get()) {
                    //init all
                    head.setNext(null);
                    head = null;
                    current = null;

                    init(windowsSize.get(), windowTimeRange.get(), limit, annualRing.incrementAndGet(), currentTime);
                } else {
                    //init party
                    current = head;
                    initWindowParty(currentTime, annualRing.incrementAndGet());
                    if (currentTime > annualRingTimeMax) {
                        previousAnnualRingTimeMax = annualRingTimeMax;
                        annualRingTimeMax = annualRingTimeMax + windowTimeRange.get();
                    }
                }
                initComplete = true;
                lock.compareAndSet(1, 0);
            }
        }

        while (!initComplete) {
        }

        if (!current.validateCurrentAnnualRing(this.annualRing.get())) {
            initWindowParty(currentTime, annualRing.get());
            if (currentTime > annualRingTimeMax) {
                annualRingTimeMax = annualRingTimeMax + windowTimeRange.get();
            }
        }

        if (current.validateCurrentTimeInWindows(currentTime)) {
            if (currentLimit.get() + current.getCurrentWindowTotal().get() < limit) {
                current.increaseRequestTotal();
                return true;
            } else {
                return false;
            }
        } else {
            currentLimit.set(currentLimit.get() + current.getCurrentWindowTotal().get());
            current = current.getNext();
            return limitOperation(currentTime);
        }
    }

    /**
    * @Description: 初始化子窗口 并且滑动
    * @Param:  * @param null
    * @return:
    * @Author: 无始
    * @Date: 2020/9/14+7:31 下午
    */
    private void initWindowParty(Long currentTime, Long annualRing) {
        if(currentTime>annualRingTimeMax) {
            currentLimit.set(currentLimit.get() - current.getCurrentWindowTotal().get());
            current.initLimitNode(annualRingTimeMax + (windowPartyTimeRange.get() * current.getIndex()), annualRingTimeMax + (windowPartyTimeRange.get() * (current.getIndex() + 1)), annualRing);
            if (!current.validateCurrentTimeInWindows(currentTime)) {
                current = current.getNext();
                initWindowParty(currentTime, annualRing);
            }
        }
        else
        {
            currentLimit.set(currentLimit.get() - current.getCurrentWindowTotal().get());
            current.initLimitNode(previousAnnualRingTimeMax + (windowPartyTimeRange.get() * current.getIndex()), previousAnnualRingTimeMax + (windowPartyTimeRange.get() * (current.getIndex() + 1)), annualRing);
            if (!current.validateCurrentTimeInWindows(currentTime)) {
                current = current.getNext();
                initWindowParty(currentTime, annualRing);
            }
        }

    }

}
