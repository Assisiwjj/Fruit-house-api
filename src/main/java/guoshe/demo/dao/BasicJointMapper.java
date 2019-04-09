package guoshe.demo.dao;

import guoshe.demo.domain.BasicJoint;
import guoshe.demo.domain.BasicJointExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface BasicJointMapper {
    long countByExample(BasicJointExample example);

    int deleteByExample(BasicJointExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BasicJoint record);

    int insertSelective(BasicJoint record);

    List<BasicJoint> selectByExample(BasicJointExample example);

    BasicJoint selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BasicJoint record, @Param("example") BasicJointExample example);

    int updateByExample(@Param("record") BasicJoint record, @Param("example") BasicJointExample example);

    int updateByPrimaryKeySelective(BasicJoint record);

    int updateByPrimaryKey(BasicJoint record);
}