package per.xgt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 100:参数错误
 * 105:重复数据
 * 200：成功
 * 300：重定向
 * 400：登陆失败
 * 401：权限不足，访问被拒绝
 * 402: 返回结果集错误
 * 500：服务器内部错误
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultMessage {

    private Integer status;
    private String message;

}
