import com.demo.controller.Student;
import com.demo.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author wangbo
 * @date 2022/7/28 18:07
 */

@SpringBootTest
public class SbvApplicationTests {
    @Autowired
    private StudentMapper studentMapper;

    public void ceshi(){
        for (Student student : studentMapper.selectList(null)) {
            System.out.println(student.toString());
        }
    }

}
