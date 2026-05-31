package scheduler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SchedulerService {
    private ScheduledExecutorService svc = newExecutor();

    private ScheduledExecutorService newExecutor() {
        return Executors.newScheduledThreadPool(2, r -> {
        Thread t = new Thread(r, "dsl-scheduler");
        return t;
        });
    }

    private synchronized ScheduledExecutorService executor() {
        if (svc.isShutdown() || svc.isTerminated()) {
            svc = newExecutor();
        }
        return svc;
    }

    public boolean hayTareasProgramadas() {
        return !svc.isShutdown() && !svc.isTerminated();
    }

    public void scheduleAtFixedRate(Runnable task, long initialDelay, long period, TimeUnit unit) {
        long safeInitial = Math.max(0L, initialDelay);
        long safePeriod = Math.max(1L, period);
        executor().scheduleAtFixedRate(task, safeInitial, safePeriod, unit);
    }

    public ScheduledFuture<?> scheduleOnce(Runnable task, long delay, TimeUnit unit) {
        long safeDelay = Math.max(0L, delay);
        return executor().schedule(task, safeDelay, unit);
    }

    public ScheduledFuture<?> scheduleAtFixedRateWithHandle(Runnable task, long initialDelay, long period, TimeUnit unit) {
        long safeInitial = Math.max(0L, initialDelay);
        long safePeriod = Math.max(1L, period);
        return executor().scheduleAtFixedRate(task, safeInitial, safePeriod, unit);
    }

    public void shutdown() { svc.shutdown(); }
}
