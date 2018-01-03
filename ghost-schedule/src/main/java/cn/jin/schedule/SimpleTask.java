package cn.jin.userapi.schedule;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import org.springframework.stereotype.Component;

/**
 * Created by jin on 2017/3/22.
 */
@Component
public class SimpleTask implements SimpleJob{

    @Override public void execute(ShardingContext shardingContext) {
        switch (shardingContext.getShardingItem()) {
        case 0:
            System.out.println("shardingContext.getShardingItem() = 0");
            break;
        case 1:
            System.out.println("shardingContext.getShardingItem() = 1");
            break;
        case 2:
            System.out.println("shardingContext.getShardingItem() = 2");
            break;
        }
    }
}
