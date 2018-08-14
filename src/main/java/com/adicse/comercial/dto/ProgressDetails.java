package com.adicse.comercial.dto;

import java.math.BigDecimal;
import java.util.HashMap;

public class ProgressDetails {
	   private String taskId;
	   private Integer total=0;
	   private Integer totalProcessed=0;
	   @SuppressWarnings("unused")
	private BigDecimal value = new BigDecimal(0);
	   @SuppressWarnings("unused")
	private Integer porcentaje = 0;
	   private String msgExtra;
	 
	   

	 
	public String getMsgExtra() {
		return msgExtra;
	}

	public void setMsgExtra(String msgExtra) {
		this.msgExtra = msgExtra;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getTotalProcessed() {
		return totalProcessed;
	}

	public void setTotalProcessed(int totalProcessed) {
		this.totalProcessed = totalProcessed;
	}

	// toString() method which returns progress details in JSON format
	   public String toString(){
	      return "{total:"+this.total+",totalProcessed:"+this.totalProcessed+"}";
	   }
	 
	   public BigDecimal getValue() {
			total = this.getTotal();
			totalProcessed = this.getTotalProcessed();
			return new BigDecimal(totalProcessed.doubleValue() /  total.doubleValue());  		   

	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public Integer getPorcentaje() {
		
		return (int) (this.getValue().doubleValue() * 100) ;
	}

	public void setPorcentaje(Integer porcentaje) {
		this.porcentaje = porcentaje;
	}

	// a public static HashMap, which serves as a storage to store progress of different tasks
	   // with taskId as key and ProgressDetails object as value
	public static HashMap<String, ProgressDetails> taskProgressHash = new HashMap<String, ProgressDetails>();	

}
