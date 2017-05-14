package dao.impl;


import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.Query;
import org.json.JSONArray;

import dao.InstrumentDao;
import domain.Instrument;
import domain.Kind;
import net.sf.json.JSONObject;
import service.KindService;

public class InstrumentDaoImpl extends BaseDaoHibernate4<Instrument> implements InstrumentDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Instrument> search(Map<String, String> searchSpec) {
		String sql = "select * from Instrument where Instrument.instrumentSpec=?";
		Query query=getSessionFactory().getCurrentSession().createQuery(sql);
		return (List<Instrument>)query.setProperties(searchSpec).list();
	}

	@Override
	public Instrument addInstrument(JSONObject jsonObject,  Kind kind) {
		Iterator it = jsonObject.keys();
		Map<String, String> data = new HashMap<String, String>();
	    while (it.hasNext())  
	    {  
	    	  String key = String.valueOf(it.next());  
	    	  String value = jsonObject.get(key).toString().trim().length()>0 ? (String) jsonObject.get(key).toString().trim() : "";
	    	  System.out.println(value);
	    	  data.put(key, value.trim());
	     }
	    Instrument instrument = new Instrument();
	    instrument.setKind(kind);
	    instrument.setPrice(Double.parseDouble(data.get("Price")));
	    data.remove("Price");
	    instrument.setSerialNumber(data.get("SerialNumber"));
	    data.remove("SerialNumber");
	    instrument.setInstrumentSpec(data);
	    return instrument;
	}

	@Override
	public JSONArray match(JSONObject jsonObject, String type) {
		Iterator it = jsonObject.keys();
		Map<String, String> data = new HashMap<String, String>();
		while (it.hasNext())  
	    {  
	    	  String key = String.valueOf(it.next());  
	    	  String value = (String) jsonObject.get(key);  
	    	  data.put(key, value);
	     }
		String hql = "from Kind k where k.type=:type";
		Query query = getSessionFactory().getCurrentSession().createQuery(hql).setParameter("type", type);
		Kind kind = (Kind) query.uniqueResult();
		Set<Instrument> instrumentSet =  kind.getInstrumentSet();
		JSONArray jsonArray = new JSONArray();
		for(Instrument i : instrumentSet){
			if(i.match(data)){
				JSONObject jo = new JSONObject();
				jo.put("serialNumber", i.getSerialNumber());
				jo.put("price", i.getPrice());
				jo.put("others",i.getInstrumentSpec());
				jsonArray.put(jo);
			}
		}
		return jsonArray;
	}
	
}
