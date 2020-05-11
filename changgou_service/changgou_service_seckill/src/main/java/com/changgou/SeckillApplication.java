package com.changgou;

import com.changgou.seckill.config.TokenDecode;
import com.changgou.util.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableEurekaClient
@EnableScheduling
@MapperScan(basePackages = {"com.changgou.seckill.dao"})
public class SeckillApplication {
    public static void main(String[] args) {
        SpringApplication.run(SeckillApplication.class,args);
    }


    /**
     * 分布式id,生成秒杀订单id
     */

    @Bean
    public IdWorker idWorker(){
        return new IdWorker(1,1);
    }



    //把spring容器中原有的RedisTemplate进行覆盖。
    //允许对String类型的数据进行increment decrement操作；
    //设置redistemplate的序列化
    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        // 1.创建 redisTemplate 模版
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        // 2.关联 redisConnectionFactory
        template.setConnectionFactory(redisConnectionFactory);
        // 3.创建 序列化类
        GenericToStringSerializer genericToStringSerializer = new GenericToStringSerializer(Object.class);
        // 6.序列化类，对象映射设置
        // 7.设置 value 的转化格式和 key 的转化格式
        template.setValueSerializer(genericToStringSerializer);
        template.setKeySerializer(new StringRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }


    @Bean
    public TokenDecode tokenDecode(){
        return new TokenDecode();
    }
}