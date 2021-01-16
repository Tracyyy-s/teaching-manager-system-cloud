import com.gwy.manager.mapper.StudentMapper;
import com.gwy.manager.service.impl.StudentServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


/**
 * @author Tracy
 * @date 2021/1/16 11:23
 */
@SpringBootTest(classes = MapperTest.class)
public class MapperTest {

    @Autowired
    private StudentMapper studentMapper;


    private StudentServiceImpl studentService;

    @Test
    void test() {
        System.out.println(studentMapper.selectByPrimaryKey("2018110114"));
    }
}
