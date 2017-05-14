package dao;


import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import domain.Instrument;
import domain.Kind;
import net.sf.json.JSONObject;
import service.KindService;

public interface InstrumentDao extends BaseDao<Instrument>{
	public List<Instrument> search(Map<String,String> searchSpec);
	public Instrument addInstrument(JSONObject jsonObject, Kind kind);
	public JSONArray match(JSONObject jsonObject, String type);
}
