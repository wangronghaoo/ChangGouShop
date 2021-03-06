package com.changgou.seckill.dao;

import com.changgou.seckill.pojo.SeckillOrder;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

public interface SeckillOrderMapper extends Mapper<SeckillOrder> {

    @Select("select * from tb_seckill_order where seckill_id = #{id} and  user_id = #{username}")
    SeckillOrder getSeckillOrderByUserAndId(String username, Long id);
}
