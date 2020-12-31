package com.central.test;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * @author 武林
 * @date 2020/10/14 13:33
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("tb_test")
public class TestEntity {

    private String fdId;

    private String fdName;

    private String fdScore;

}
