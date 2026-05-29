package scheduler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SchedulerService {
    private final ScheduledExecutorService svc = Executors.newScheduledThreadPool(2, r -> {
        Thread t = new Thread(r, "dsl-scheduler");
        return t;
    });

    public boolean hayTareasProgramadas() {
        return !svc.isShutdown() && !svc.isTerminated();
    }

    public void scheduleAtFixedRate(Runnable task, long initialDelay, long period, TimeUnit unit) {
        long safeInitial = Math.max(0L, initialDelay);
        long safePeriod = Math.max(1L, period);
        svc.scheduleAtFixedRate(task, safeInitial, safePeriod, unit);
    }

    public void shutdown() { svc.shutdown(); }
}
