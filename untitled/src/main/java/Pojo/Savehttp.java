package Pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.ui.Model;

import javax.validation.constraints.AssertFalse;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

/**
 * @author wangbo
 * @date 2022/7/21 13:21
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Savehttp  implements Serializable {

    private static final long serialVersionUID=1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String customerName;

    private Integer customer_s_name;

    private String customer_city;

    private Integer customer_sales;

    @TableField("VERSION")
    private Integer customer_addr;

    private int customer_tax_no;



}
