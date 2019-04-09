package guoshe.demo.service.impl;

import guoshe.demo.dao.OrderlistMapper;
import guoshe.demo.domain.Orderlist;
import guoshe.demo.domain.OrderlistExample;
import guoshe.demo.service.OrderListService;
import guoshe.demo.utils.DateOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderListImpl implements OrderListService {
    @Autowired
    private OrderlistMapper orderlistMapper;

    @Override
    public List<Orderlist> selectAllOrder(){
        try{
            OrderlistExample example = new OrderlistExample();
            OrderlistExample.Criteria criteria = example.createCriteria();
            criteria.andIsDeleteEqualTo(false);
            List<Orderlist> list = orderlistMapper.selectByExample(example);
            if (list.size()!=0){
                return list;
            }else {
                return null;
            }
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Boolean insert(Orderlist order){
        try{
            order.setGmtCreate(new Date());
            order.setGmtModified(new Date());
            order.setOrderId(DateOrder.getInstance().generateOrder());
            order.setStatus("待发货");
            order.setIsDelete(false);
            if (orderlistMapper.insertSelective(order)==1){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public int delete(String orderId){
        try {
            OrderlistExample example = new OrderlistExample();
            OrderlistExample.Criteria criteria = example.createCriteria();
            criteria.andOrderIdEqualTo(orderId);
            Orderlist order = new Orderlist();
            order.setIsDelete(true);
            return orderlistMapper.updateByExampleSelective(order,example);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public int updateOrderStatus(Orderlist order){
        try{
            OrderlistExample example = new OrderlistExample();
            OrderlistExample.Criteria criteria = example.createCriteria();
            criteria.andOrderIdEqualTo(order.getOrderId());
            return orderlistMapper.updateByExampleSelective(order,example);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
