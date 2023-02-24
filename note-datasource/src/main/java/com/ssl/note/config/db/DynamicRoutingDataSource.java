package com.ssl.note.config.db;

import com.ssl.note.config.context.DynamicDataSourceContextHolder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @Author: SongShengLin
 * @Date: 2023/02/24 15:32
 * @Describe:
 */
public class DynamicRoutingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.peek();
    }
}
