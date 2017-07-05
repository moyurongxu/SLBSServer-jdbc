package com.mrx.utils.entity.page;

/**
 * 分页查询操作实体类
 * 
 */
public class Page {
	private int rowTotal;// 总记录数  
    private int pageSize = 10;// 每页记录数  
    private int count;// 当前页码  
    private int total;// 总页数  
    private int beginIndex;//起始记录下标  
    private int endIndex;//截止记录下标  
  
    /** 
     * 使用总记录数、当前页码构造 
     *  
     * @param rowTotal 
     * @param count 
     *            页码，从1开始 
     */  
    public Page(int totalRow, int count) {  
        this.rowTotal = totalRow;  
        this.count = count;  
        calculate();  
    }  
  
    /** 
     * 使用总记录数、当前页码和每页记录数构造 
     *  
     * @param rowTotal 
     * @param count 
     *            页码，从1开始 
     * @param pageSize 
     *            默认30条 
     */  
    public Page(int totalRow, int count, int pageSize) {  
        this.rowTotal = totalRow;  
        this.count = count;  
        this.pageSize = pageSize;  
        calculate();  
    }  
  
    private void calculate() {  
    	//总页数 = 总条数/每页多少条 + 总条数%每页多少条>0?0:1
        total = rowTotal / pageSize + ((rowTotal % pageSize) > 0 ? 1 : 0);  
        
        //如果当前页大于总页数
        if (count > total) {  
            count = total;  
        } else if (count < 1) {  
            count = 1;  
        }  
  
        beginIndex = (count - 1) * pageSize ;  
        endIndex = beginIndex + pageSize ;  
        if (endIndex > rowTotal) {  
            endIndex = rowTotal;  
        }  
    }

	public int getRowTotal() {
		return rowTotal;
	}

	public void setRowTotal(int rowTotal) {
		this.rowTotal = rowTotal;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getBeginIndex() {
		return beginIndex;
	}

	public void setBeginIndex(int beginIndex) {
		this.beginIndex = beginIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}  
  
}
