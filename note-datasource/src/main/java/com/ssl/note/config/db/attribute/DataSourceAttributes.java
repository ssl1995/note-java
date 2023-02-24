package com.ssl.note.config.db.attribute;

import com.google.common.collect.Maps;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * @Author: SongShengLin
 * @Date: 2023/02/24 15:35
 * @Describe:
 */
@ConfigurationProperties(prefix = "spring.dynamic")
public class DataSourceAttributes {

    private String defaultSource;

    public String getDefaultSource() {
        return defaultSource;
    }

    public void setDefaultSource(String defaultSource) {
        this.defaultSource = defaultSource;
    }

    private int scanType;

    private String baseScanPath;

    public int getScanType() {
        return scanType;
    }

    public String getBaseScanPath() {
        return baseScanPath;
    }

    public void setScanType(int scanType) {
        this.scanType = scanType;
    }

    public void setBaseScanPath(String baseScanPath) {
        this.baseScanPath = baseScanPath;
    }

    private Map<String, DataSourceProperties> datasource = Maps.newHashMap();

    public Map<String, DataSourceProperties> getDatasource() {
        return datasource;
    }

    public void setDatasource(Map<String, DataSourceProperties> datasource) {
        this.datasource = datasource;
    }
}
