package atguigu.demo1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created on 2020-02-14
 *
 * @author :hao.li
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SourceEvent {
    private String time;
    private String code;
    private String host;
    private String nameCN;
    private String nameEN;
    private String value;
}
