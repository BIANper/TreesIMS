package com.yuyu.tree.vo;

import com.yuyu.tree.po.TimsLog;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TimsLogVo extends TimsLog {

    private static final long serialVersionUID = 5187586901711616420L;

    private String nameZh;

    private String identifier;

}
