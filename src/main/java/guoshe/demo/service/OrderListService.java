package guoshe.demo.service;

import guoshe.demo.domain.Orderlist;

import java.util.List;

public interface OrderListService {
    List<Orderlist> selectAllOrder();

    Boolean insert(Orderlist order);

    int delete(String orderId);

    int updateOrderStatus(Orderlist order);
}
