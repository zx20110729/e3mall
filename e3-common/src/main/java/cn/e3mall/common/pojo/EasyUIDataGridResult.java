package cn.e3mall.common.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ZX on 2017/7/25 13:54.
 */
public class EasyUIDataGridResult implements Serializable{
    private static final long serialVersionUID = 2042280482739974978L;
    private long total;
    private List rows;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}
