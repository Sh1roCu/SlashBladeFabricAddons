package com.yakumosakura.yakumoblade.utils;

import com.yakumosakura.yakumoblade.Yakumoblade;
import net.minecraft.server.MinecraftServer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 提供基于游戏刻(tick)的延迟任务执行功能。
 * <p>
 * 该类允许在指定的游戏刻数后执行 {@link Runnable} 任务，适用于服务器端定时任务处理。
 * <p>
 * 使用示例：
 * <pre>
 * WaitingTick.schedule(20, () -> {
 *     System.out.println("延迟1秒后执行");
 * });
 * </pre>
 */
public class WaitingTick {
    /**
     * 待执行任务队列。
     */
    private static final List<DelayedTask> tasks = new ArrayList<>();

    /**
     * 延迟任务内部类，封装任务执行逻辑。
     */
    private static class DelayedTask {
        int remainingTicks;
        Runnable action;

        DelayedTask(int ticks, Runnable action) {
            this.remainingTicks = ticks;
            this.action = action;
        }
    }

    /**
     * 安排一个延迟执行的任务。
     * <p>
     * 该方法将任务添加到任务队列中，等待指定的游戏刻数后执行。
     *
     * @param ticks  延迟的游戏刻数 (1 秒 = 20 刻)
     * @param action 需要执行的任务逻辑
     */
    public static void schedule(int ticks, Runnable action) {
        synchronized (tasks) {
            tasks.add(new DelayedTask(ticks, action));
        }
    }

    /**
     * 服务器刻事件处理，检查并执行到期任务。
     * <p>
     * 该方法会在每个服务器刻结束时遍历任务队列，执行已到期的任务。
     */
    public static void onServerTick(MinecraftServer server) {
        synchronized (tasks) {
            Iterator<DelayedTask> iterator = tasks.iterator();
            while (iterator.hasNext()) {
                DelayedTask task = iterator.next();
                task.remainingTicks--;

                if (task.remainingTicks <= 0) {
                    try {
                        task.action.run();
                    } catch (Exception e) {
                        Yakumoblade.LOGGER.error("Error executing delayed task: " + e.getMessage());
                        e.printStackTrace();
                    }
                    iterator.remove();
                }
            }
        }
    }
}