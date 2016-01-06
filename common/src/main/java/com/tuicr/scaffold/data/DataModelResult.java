package com.tuicr.scaffold.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * restful返回数据模型
 *
 * @author ylxia
 * @version 1.0
 * @package com.woawi.wx
 * @date 15/11/26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataModelResult<T> {


    public DataModelResult(long stateCode, T result, String message) {
        this.stateCode = stateCode;
        this.result = result;
        this.message = message;
    }

    /**
     * 状态码
     */
    private long stateCode;


    /**
     * 数据结果
     */
    private T result;

    /**
     * 返回状态消息内容
     * 错误消息
     */
    private String message;


    /**
     * 时间戳
     */
    private String timestamp = String.valueOf(System.nanoTime());

}
