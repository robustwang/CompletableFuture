package com.wangjun.completablefuture;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class TestThenCompose {

    private static final Logger logger = LoggerFactory.getLogger(TestThenCompose.class);

    private static Optional<List<Integer>> longTask(Integer i) {
        if (i > 0) {
            List<Integer> list = new ArrayList<>();
            for (int pc = 0; pc < i; pc++)
                list.add(pc);
            return Optional.of(list);
        } else
            return Optional.empty();
    }

    private static CompletableFuture<Long> getResultFuture(Optional<List<Integer>> op) {
        return CompletableFuture.supplyAsync(() -> {
            if (op.isPresent())
                return op.get().stream()
                        .map(Integer::toUnsignedLong)
                        .reduce(0L, (x, y) -> x + y);
            else
                return -1L;
        });
    }

    @Test
    public void testThenCompose() throws ExecutionException, InterruptedException {
        CompletableFuture<Long> f = CompletableFuture.supplyAsync(() -> longTask(1000000))
                .thenComposeAsync(TestThenCompose::getResultFuture);
        Long result = f.get();
        System.out.println(result);
    }

    public static void main(String[] args) {
        logger.info(logger.getName());
    }
}