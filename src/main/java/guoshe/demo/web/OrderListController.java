package guoshe.demo.web;

import guoshe.demo.domain.Orderlist;
import guoshe.demo.service.OrderListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("order")
public class OrderListController extends BaseController{
    @Autowired
    private OrderListService orderlistService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Map<String, Object> getAllOrder(HttpServletRequest request) {
        msg.clear();
        try{
            List list = orderlistService.selectAllOrder();
            if (list!=null) {
                msg.put("code", "1");
                msg.put("msg", "成功");
                msg.put("data", list);
            }else {
                msg.put("code", "0");
                msg.put("msg", "数据为空");
            }
        }catch (Exception e){
            msg.put("code","0");
            msg.put("msg",e.getMessage());
        }
        return msg;
    }

    @RequestMapping(value = "insert",method = RequestMethod.POST)
    public Map<String,Object> insert(@RequestBody Orderlist order) {
        msg.clear();
        try {
            if (order.getUserId() != null && order.getName()!=null && order.getNumber()!=null
                    && order.getPrice()!=null && order.getProductName()!=null && order.getAddress()!=null) {
                if (orderlistService.insert(order) == true) {
                    msg.put("code", "1");
                    msg.put("msg", "成功");
                } else {
                    msg.put("code", "0");
                    msg.put("msg", "失败");
                }
            } else {
                msg.put("code", "0");
                msg.put("msg", "传参不正确");
            }
        } catch (Exception e) {
            msg.put("code", "0");
            msg.put("msg", e.getMessage());
        }
        return msg;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public Map<String, Object> delete(HttpServletRequest request) {
        msg.clear();
        try{
            String orderId=request.getParameter("orderId");
            if (orderId != null) {
                if (orderlistService.delete(orderId) == 1) {
                    msg.put("code", "1");
                    msg.put("msg", "成功");
                } else {
                    msg.put("code", "0");
                    msg.put("msg", "失败");
                }
            } else {
                msg.put("code", "0");
                msg.put("msg", "pkId为空");
            }
        }catch (Exception e){
            msg.put("code","0");
            msg.put("msg", e.getMessage());
        }
        return msg;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Map<String, Object> update(@RequestBody Orderlist order) {
        msg.clear();
        try{
            if (order.getOrderId() != null) {
                if (orderlistService.updateOrderStatus(order) == 1) {
                    msg.put("code", "1");
                    msg.put("msg", "成功");
                } else {
                    msg.put("code", "0");
                    msg.put("msg", "失败");
                }
            } else {
                msg.put("code", "0");
                msg.put("msg", "orderId为空");
            }
        }catch (Exception e){
            msg.put("code","0");
            msg.put("msg", e.getMessage());
        }
        return msg;
    }
}
