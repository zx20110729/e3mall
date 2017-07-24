package cn.e3mall.mapper;

import cn.e3mall.pojo.OrderShipping;
import cn.e3mall.pojo.OrderShippingExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderShippingMapper {
    int countByExample(OrderShippingExample example);

    int deleteByExample(OrderShippingExample example);

    int deleteByPrimaryKey(String orderId);

    int insert(OrderShipping record);

    int insertSelective(OrderShipping record);

    List<OrderShipping> selectByExample(OrderShippingExample example);

    OrderShipping selectByPrimaryKey(String orderId);

    int updateByExampleSelective(@Param("record") OrderShipping record, @Param("example") OrderShippingExample example);

    int updateByExample(@Param("record") OrderShipping record, @Param("example") OrderShippingExample example);

    int updateByPrimaryKeySelective(OrderShipping record);

    int updateByPrimaryKey(OrderShipping record);
}