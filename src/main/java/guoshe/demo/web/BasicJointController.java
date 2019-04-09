package guoshe.demo.web;

import guoshe.demo.domain.BasicJoint;
import guoshe.demo.service.basicJoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("basicjoint")
public class BasicJointController  extends BaseController{

    @Autowired
    private basicJoint basicJoint;
    @RequestMapping(value = "getById",method = RequestMethod.GET)
    public Map<String,Object> getById(Integer id){
        BasicJoint basicJoint = this.basicJoint.selectByPrimaryKey(id);
        msg.put("data",basicJoint);
        return  msg;
    }
}
