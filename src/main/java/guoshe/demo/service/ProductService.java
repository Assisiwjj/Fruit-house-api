package guoshe.demo.service;

import guoshe.demo.domain.Product;
import guoshe.demo.utils.PageBean;

import java.util.Date;
import java.util.List;

public interface ProductService {
    List<Product> selectAllProduct();

    Boolean insert(Product product, String suffix, String data);

    int delete(Long pkId);

    int update(Product product);

    String uploadImage(String suffix, String FileName, String data, Boolean insert);

    int updateImage(Long pkId, String suffix,String data);

    PageBean getPageBean(Integer limit, Integer page);
}
