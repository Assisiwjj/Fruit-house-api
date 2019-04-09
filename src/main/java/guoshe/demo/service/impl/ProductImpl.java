package guoshe.demo.service.impl;

import guoshe.demo.dao.ProductMapper;
import guoshe.demo.domain.Product;
import guoshe.demo.domain.ProductExample;
import guoshe.demo.service.ProductService;
import guoshe.demo.utils.Constants;
import guoshe.demo.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ProductImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> selectAllProduct() throws RuntimeException {
        try{
            List<Product> list= productMapper.selectAllProduct();
            for(Product product : list){
                if(product.getImage()!=null){
                    product.setImage("http://localhost:8080/images/" + product.getImage());
                    //http://192.168.10.110:8080/images
                }
            }
            if (list!=null && list.size()>0){
                return list;
            }else {
                return null;
            }
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Boolean insert(Product product, String suffix, String data){
        try{
            product.setGmtCreate(new Date());
            product.setGmtModified(new Date());
            product.setImage(uploadImage(suffix, null, data, true));
            product.setIsDelete(false);
            if (productMapper.insert(product)==1){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public int delete(Long pkId){
        try {
            Product product = new Product();
            product.setPkId(pkId);
            product.setIsDelete(true);
            return productMapper.updateByPrimaryKeySelective(product);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public int update(Product product){
        try {
            return productMapper.updateByPrimaryKeySelective(product);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public String uploadImage(String suffix, String FileName, String data, Boolean insert) throws RuntimeException {
        String fileName;
        try
        {
            //Base64解码
            byte[] b = Base64Utils.decodeFromString(data);
            for(int i=0;i<b.length;++i)
            {
                if(b[i]<0)
                {
                    //调整异常数据
                    b[i]+=256;
                }
            }
            if(!insert){
                fileName = FileName;
                File oldFile = new File(Constants.getCurrenPath()+fileName);
                if(oldFile.exists()){
                    oldFile.delete();
                }
                fileName =  FileName.split("\\.")[0] + suffix;
            }else{
                fileName = UUID.randomUUID().toString() + suffix;
            }
            String imgFilePath = Constants.getCurrenPath()+fileName;
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return fileName;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    @Override
    public int updateImage(Long pkId, String suffix,String data) {
        Product product = productMapper.selectByPrimaryKey(pkId);
        product.setImage(uploadImage(suffix, product.getImage(), data, false));
        return productMapper.updateByPrimaryKey(product);
    }

    @Override
    public PageBean getPageBean(Integer limit, Integer page) {
        try {
            ProductExample example = new ProductExample();
            ProductExample.Criteria criteria = example.createCriteria();
            int count = (int) productMapper.countByExample(example);
            if (count > 0) {
                PageBean pageBean = new PageBean(page, count, limit);
                example.setLimit(limit);
                example.setOffset(pageBean.getStart());
                criteria.andIsDeleteEqualTo(false);
                List<Product> productList = productMapper.selectByExample(example);
                for(Product product : productList){
                    if(product.getImage()!=null){
                        product.setImage("http://localhost:8080/images/" + product.getImage());
                        //http://192.168.10.110:8080/images/
                    }
                }
                pageBean.setList(productList);
                return pageBean;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
