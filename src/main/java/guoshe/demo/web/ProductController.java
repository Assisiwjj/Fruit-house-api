package guoshe.demo.web;

import guoshe.demo.domain.Product;
import guoshe.demo.service.ProductService;
import guoshe.demo.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("product")
public class ProductController extends BaseController {
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "allProduct",method = RequestMethod.GET)
    public Map<String ,Object> allProduct(){
        msg.clear();
        try{
            List list = productService.selectAllProduct();
            msg.put("code","1");
            msg.put("msg","成功");
            msg.put("data",list);
        }
        catch (Exception e)
        {
            msg.put("code","0");
            msg.put("message",e.getMessage());
        }
        return msg;
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Map<String, Object> insert(HttpServletRequest request, @RequestBody Product product){
        msg.clear();
        try{
            String dataPrix = "";
            String data = "";
            String base64Data = product.getImage();
            if(base64Data == null || "".equals(base64Data)){
                msg.put("code","0");
                msg.put("msg","上传失败，上传图片数据为空");
                return msg;
            }else{
                String [] d = base64Data.split("base64,");
                if(d != null && d.length == 2){
                    dataPrix = d[0];
                    data = d[1];
                    product.setImage(null);
                }else{
                    msg.put("code","0");
                    msg.put("msg","上传失败，数据不合法");
                    return msg;
                }
            }

            String suffix = "";
            if("data:image/jpeg;".equalsIgnoreCase(dataPrix)){
                //data:image/jpeg;base64,base64编码的jpeg图片数据
                suffix = ".jpg";
            } else if("data:image/x-icon;".equalsIgnoreCase(dataPrix)){
                //data:image/x-icon;base64,base64编码的icon图片数据
                suffix = ".ico";
            } else if("data:image/gif;".equalsIgnoreCase(dataPrix)){
                //data:image/gif;base64,base64编码的gif图片数据
                suffix = ".gif";
            } else if("data:image/png;".equalsIgnoreCase(dataPrix)){
                //data:image/png;base64,base64编码的png图片数据
                suffix = ".png";
            }else{
                msg.put("code","0");
                msg.put("msg","上传图片格式不合法");
                return msg;
            }


            if (product.getName()!=null && product.getIntroduction()!=null && product.getPrice()!=null
                    && product.getType()!=null){
                if (productService.insert(product,suffix,data)==true) {
                    msg.put("code", "1");
                    msg.put("msg", "成功");
                }else {
                    msg.put("code", "0");
                    msg.put("msg", "失败");
                }
            }else
            {
                msg.put("code","0");
                msg.put("msg","请传入完整参数");
            }
        }catch (Exception e){
            msg.put("code","0");
            msg.put("msg",e.getMessage());
        }
        return msg;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public Map<String, Object> delete(HttpServletRequest request) {
        msg.clear();
        try{
            Long pkId=Long.parseLong(request.getParameter("pkId"));
            if (pkId != null) {
                if (productService.delete(pkId) == 1) {
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
    public Map<String, Object> update(@RequestBody Product product) {
        msg.clear();
        try{
            if (product.getPkId() != null) {
                if (productService.update(product) == 1) {
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

    @RequestMapping(value = "/updateImage",method = RequestMethod.POST)
    public Map<String, Object> updateImage (HttpServletRequest request,@RequestBody Product product){
        msg.clear();
        try {
            String dataPrix = "";
            String data = "";
            String base64Data = product.getImage();
            if(base64Data == null || "".equals(base64Data)){
                msg.put("code","0");
                msg.put("msg","上传失败，上传图片数据为空");
                return msg;
            }else{
                String [] d = base64Data.split("base64,");
                if(d != null && d.length == 2){
                    dataPrix = d[0];
                    data = d[1];
                    product.setImage(null);
                }else{
                    msg.put("code","0");
                    msg.put("msg","上传失败，数据不合法");
                    return msg;
                }
            }

            String suffix = "";
            if("data:image/jpeg;".equalsIgnoreCase(dataPrix)){//data:image/jpeg;base64,base64编码的jpeg图片数据
                suffix = ".jpg";
            } else if("data:image/x-icon;".equalsIgnoreCase(dataPrix)){//data:image/x-icon;base64,base64编码的icon图片数据
                suffix = ".ico";
            } else if("data:image/gif;".equalsIgnoreCase(dataPrix)){//data:image/gif;base64,base64编码的gif图片数据
                suffix = ".gif";
            } else if("data:image/png;".equalsIgnoreCase(dataPrix)){//data:image/png;base64,base64编码的png图片数据
                suffix = ".png";
            }else{
                msg.put("code","0");
                msg.put("msg","上传图片格式不合法");
                return msg;
            }

                if (product.getPkId() != null){
                    if (productService.updateImage(product.getPkId(),suffix,data)>0) {
                        msg.put("code", "1");
                        msg.put("msg", "上传成功");
                    } else {
                        msg.put("code", "0");
                        msg.put("msg", "上传失败");
                    }
                }else {
                    msg.put("code", "0");
                    msg.put("msg", "上传失败,图片为空");
                }
        }catch (Exception e){
            msg.put("code","0");
            msg.put("msg",e.getMessage());
        }
        return msg;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Map<String, Object> getAllProduct(HttpServletRequest request) {
        msg.clear();
        try{
            Integer limit = Integer.parseInt(request.getParameter("limit"));
            Integer page = Integer.parseInt(request.getParameter("page"));
            PageBean pageBean =productService.getPageBean(limit, page);
            msg.put("code", "1");
            msg.put("msg", "成功");
            msg.put("data", pageBean);
        }catch (Exception e){
            msg.put("code","0");
            msg.put("msg",e.getMessage());
        }
        return msg;
    }
}
