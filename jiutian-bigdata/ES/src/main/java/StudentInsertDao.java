/**
 * Copyright @ 2018 lihao.com
 * All right reserved.
 *d
 * @author Li Hao
 * @since 2018/11/14  16:50
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.index.IndexRequest;

import java.net.UnknownHostException;


public class StudentInsertDao {
    //    private final Logger logger = LoggerFactory.getLogger(StudentInsertDao.class);
    private ESConfiguration es = new ESConfiguration();
    private BulkProcessor bulkProcessor;
    {
        try {
            bulkProcessor = es.bulkProcessor();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
    private ObjectMapper objectMapper = new ObjectMapper();
    public void insert(Student student) {
        String type = student.getAddr();
        System.out.println("write to es starting...");
        String id = student.getName() + student.getAddr() + student.getAge();
        try {
            byte[] json = objectMapper.writeValueAsBytes(student);
            IndexRequest students;
//            for(int i=0;i<100;i++) {
                students = new IndexRequest("company", "employee",id).source(json);
                bulkProcessor.add(students);
//            }
//            students.timeout(TimeValue.timeValueSeconds(5));
            System.out.println("write to es end");
        } catch (Exception e) {
            System.out.println("error");
//            logger.error("bulkProcessor failed ,reason:{}", e);
        }
    }
}
