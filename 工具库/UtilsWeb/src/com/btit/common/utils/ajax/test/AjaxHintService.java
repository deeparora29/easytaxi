package com.btit.common.utils.ajax.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.btit.utilsweb.test.table.dao.HibernateEntityDao;
import com.btit.utilsweb.test.table.vo.RollupEnterpriseCommbarcode;

public class AjaxHintService {

	public String queryRollupResultByName(String name) {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("name", name);
		HibernateEntityDao dao = new HibernateEntityDao();
		List<RollupEnterpriseCommbarcode> list = dao.findObjectBy(
				RollupEnterpriseCommbarcode.class, paramMap);
		StringBuffer xmlStr = new StringBuffer();
		xmlStr.append("<?xml version='1.0' encoding='utf-8'?><all>");
		int i = 0;
		if (list != null || list.size() > 0) {
			for (RollupEnterpriseCommbarcode rollup : list) {
				xmlStr.append("<response value=\"" + rollup.getCommBarcode()
						+ "\"");
				if (i == 0) {
					xmlStr.append(" result=\"共" + list.size() + "个结果\"");
				}
				xmlStr.append(">" + rollup.getName() + "</response>");
				i++;
			}
		}
		xmlStr.append("</all>");
		return xmlStr.toString();

	}

}
