package com.epoint.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.epoint.service.PetService;

public class RandomId {

	public String getPetId() {
		/**
		 * 先生成一个符合要求的stuId
		 */
		String petId="Pet";
		Date date= new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String format = sdf.format(date);
		petId+=format;
		/**
		 * 获取最后一条记录的stuId值
		 */
		PetService service  = new PetService();
		String lastId = service.findLastRecordId();
		System.out.println("lastid="+lastId);
		//如果查询结果为空，则表的记录为空
		if (lastId == null || "".equals(lastId)){
			petId+="0001";
		}else{
			//否则就截取查询结果记录的最后四位数组
			String str = lastId.substring(9);
			int i = Integer.parseInt(str)+1;
			petId+=insertZeroToLeftId(i);//3 --> 0003
		}
		return petId;
	}
	
	/**
	 *
	 * @param i 查询出来的stuId锁截取的数字
	 * @return
	 */
	public String insertZeroToLeftId(int i){
		char[] chs = new char[4];
		for (int j = 0; j <chs.length; j++) {
			chs[j]='0';
		}
		String s = String.valueOf(i);
		for (int j = 3; j >= (chs.length - s.length()); j--) {
			chs[j] = s.charAt(j-(chs.length-s.length()));
		}
		return String.valueOf(chs);
	}
}
