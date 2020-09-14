package com.java.basis.datastructure.linkedlist.limiting;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author ：无始
 * @date ：Created in 2020/9/14 9:51 上午
 * @description：窗口页
 * @modified By：
 * @version:
 */
public class LimitNode {
    private LimitNode next;
    private Long timeWindowRangeMin;
    private Long timeWindowRangeMax;
    private AtomicLong currentWindowTotal = new AtomicLong();
    private Long annualRing;

    private Integer index;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public LimitNode getNext() {
        return next;
    }

    public void setNext(LimitNode next) {
        this.next = next;
    }

    public Long getTimeWindowRangeMin() {
        return timeWindowRangeMin;
    }

    public void setTimeWindowRangeMin(Long timeWindowRangeMin) {
        this.timeWindowRangeMin = timeWindowRangeMin;
    }

    public Long getTimeWindowRangeMax() {
        return timeWindowRangeMax;
    }

    public void setTimeWindowRangeMax(Long timeWindowRangeMax) {
        this.timeWindowRangeMax = timeWindowRangeMax;
    }

    public AtomicLong getCurrentWindowTotal() {
        return currentWindowTotal;
    }

    public void setCurrentWindowTotal(AtomicLong currentWindowTotal) {
        this.currentWindowTotal = currentWindowTotal;
    }

    public Long getAnnualRing() {
        return annualRing;
    }

    public void setAnnualRing(Long annualRing) {
        this.annualRing = annualRing;
    }

    /**
     * @Description: 判断当前请求是否属于当前窗口内
     * @Param: * @param null
     * @return:
     * @Author: 无始
     * @Date: 2020/9/14+10:32 上午
     */
    public Boolean validateCurrentTimeInWindows(Long currentTime) {
        if (currentTime >= timeWindowRangeMin && currentTime <= timeWindowRangeMax) {
            return true;
        }
        return false;
    }

    /**
    * @Description: 判断当前子窗口年轮
    * @Param:  * @param null
    * @return:
    * @Author: 无始
    * @Date: 2020/9/14+7:36 下午
    */
    public Boolean validateCurrentAnnualRing(Long annualRing) {
        if (annualRing > this.annualRing) {
            return false;
        }
        return true;
    }

    /**
     * @Description: 增加请求数量
     * @Param: * @param null
     * @return:
     * @Author: 无始
     * @Date: 2020/9/14+10:32 上午
     */
    public void increaseRequestTotal() {
        currentWindowTotal.incrementAndGet();
    }

    /**
     * @Description: 初始化
     * @Param: * @param null
     * @return:
     * @Author: 无始
     * @Date: 2020/9/14+10:32 上午
     */
    public void initLimitNode(Long timeWindowRangeMin, Long timeWindowRangeMax, Long annualRing) {
        this.timeWindowRangeMin = timeWindowRangeMin;
        this.timeWindowRangeMax = timeWindowRangeMax;
        this.annualRing = annualRing;
    }
}
