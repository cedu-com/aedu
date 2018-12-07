package net.chinaedu.aedu.network.http;

import java.util.concurrent.TimeUnit;

/**
 * Created by xujianbao on 2018/03/22
 * desc   :
 */
public class AeduHttpServiceParameter {

    private int connectTimeout;// 连接超时时间
    private int readTimeout;// 读取超时时间
    private int writeTimeout;// 设置写的超时时间
    private TimeUnit timeUnit;// 时间类型

    private AeduHttpServiceParameter(int connectTimeout, int readTimeout, int writeTimeout,
            TimeUnit timeUnit) {
        this.connectTimeout = connectTimeout;
        this.readTimeout = readTimeout;
        this.writeTimeout = writeTimeout;
        this.timeUnit = timeUnit;
    }

    public static class Builder {
        private int connectTimeout;// 连接超时时间
        private int readTimeout;// 读取超时时间
        private int writeTimeout;// 设置写的超时时间
        private TimeUnit timeUnit;// 时间类型

        public Builder connectTimeout(int connectTimeout) {
            this.connectTimeout = connectTimeout;
            return this;
        }

        public Builder readTimeout(int readTimeout) {
            this.readTimeout = readTimeout;
            return this;
        }

        public Builder writeTimeout(int writeTimeout) {
            this.writeTimeout = writeTimeout;
            return this;
        }

        public Builder timeUnit(TimeUnit timeUnit) {
            this.timeUnit = timeUnit;
            return this;
        }

        public AeduHttpServiceParameter build() {
            if(connectTimeout <= 0){
                connectTimeout = 20;
            }
            if(readTimeout <= 0){
                readTimeout = 20;
            }
            if(writeTimeout <= 0){
                writeTimeout = 20;
            }
            if(timeUnit == null){
                timeUnit = TimeUnit.SECONDS;
            }
            return new AeduHttpServiceParameter(connectTimeout, readTimeout, writeTimeout, timeUnit);
        }
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public int getReadTimeout() {
        return readTimeout;
    }

    public int getWriteTimeout() {
        return writeTimeout;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }
}

