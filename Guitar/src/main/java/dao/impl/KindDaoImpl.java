package dao.impl;


import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.json.JSONArray;
import org.json.JSONObject;

import dao.KindDao;
import domain.Kind;

public class KindDaoImpl extends BaseDaoHibernate4<Kind> implements KindDao {

	@Override
	public Kind getByType(String type) {
		String hql = "from Kind k where k.type=:type";
		Query query = getSessionFactory().getCurrentSession().createQuery(hql);
		query.setParameter("type", type);
		return (Kind)query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public InputStream getAllKind() {
		String hql = "from Kind";
		List<Kind> kindList = (List<Kind>)getSessionFactory().getCurrentSession().createQuery(hql).list();
		JSONArray jsonArray = new JSONArray();
		for(Kind kind : kindList){
			JSONObject jo = new JSONObject();
			jo.put("Type", kind.getType());
			List<String> characters = kind.getInstrumentSpec();
//			String content1 = "";
//			String content = "";
//			String content3 = "";
//			for (String str : characters) {
//					content += "<div><label>" + str +"： </label><input type='text' name='" + str + "' " + " required/></div>";
//			        content1 += "<div><label>乐器属性名： </label><input type='text' name='" + str + "' value='" + str +"' required/><span class='glyphicon glyphicon-plus' onclick='add()'></span><span class='glyphicon glyphicon-remove' onclick='remove(this)'></span></div>";
//			}
//			String content2 = "<div><label>SerialNumber： </label><input type='text' name='SerialNumber' required /></div><div><label>Price： </label><input type='number' min='0' name='Price' required /></div>";
//			content2 += content;
//			content3 += content;
//			jo.put("Content1", content1) ;
//			jo.put("Content2", content2) ;
//			jo.put("Content3", content3) ;
			jo.put("jsonData", characters);
			jsonArray.put(jo);		
		}
		InputStream inputStream = new ByteArrayInputStream("success".getBytes());
		try {
			inputStream = new ByteArrayInputStream(jsonArray.toString().getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return inputStream;
	}

	@Override
	public Kind save(net.sf.json.JSONObject jsonObject, String type) {
		// TODO Auto-generated method stub
		List<String> data = new ArrayList<String>();
		 Iterator it = jsonObject.keys();
	       while (it.hasNext())  
	       {  
	    	  String key = String.valueOf(it.next());  
	    	  String value = (String) jsonObject.get(key);  
	    	  if(!key.equals("spec")){
		          data.add(value);  
	    	  }
	       }
	    String hql = "from Kind k where k.type=:type";
		Query query = getSessionFactory().getCurrentSession().createQuery(hql).setParameter("type", type);
	    Kind kind = (Kind) query.uniqueResult();
	    if(kind == null){
	    	kind = new Kind();
	    	kind.setType(type);
	    }
	    kind.setInstrumentSpec(data);
	    return kind;
	}
	

}
